const workObj ={
	workData:[],
	preData:[],
	imgPath:"http://localhost:8080/aibrainer_2.1.0/pictures/",
	developer:null,
	assignment:null,
	taskType:null,
	index:0,
	img:null,
	isSend:true,
	publicFirst:true,
	lastPage:null,
	lastPageData:null
}
var expObj;

function experienceDo(expObj){
	expObj.timeUp = new Date().getTime();
	var srcPath = `${workObj.imgPath}${workObj.workData[0].developer}/${workObj.workData[0].assignment}/data/`;
	workObj.img=$("#imgs");
	workObj.img.attr("src",srcPath+workObj.workData[0].dataName);				
	$(".list-group-item").each(function(index,val){
		$(val).click(function(){	
			if(workObj.index==workObj.workData.length){
				showResult();
				return ;	
			}
						
			if(workObj.workData[workObj.index].dataLabel == $(val).text()){
				expObj.right+=1;
			}else{
				expObj.wrongData[workObj.index]=workObj.workData[workObj.index];
				expObj.wrongData[workObj.index].dataLabel=$(val).text();	
				expObj.wrong+=1;
			}
			if(workObj.index==workObj.workData.length-1){
				expObj.marked=workObj.index=workObj.workData.length;
				$("#marked").text(workObj.index);
				expObj.marked=workObj.index;
				expObj.accuracy = parseFloat(expObj.right/expObj.sum).toFixed(2)*100+"%";
				expObj.avgTime = parseFloat((new Date().getTime()-expObj.timeUp)/1000/expObj.sum).toFixed(2)+" s";
				showResult();
			}
			workObj.img.attr("src", srcPath+workObj.workData[workObj.index<workObj.workData.length-1?++workObj.index:workObj.workData.length-1].dataName);
			workObj.index<=expObj.sum?$("#marked").text(workObj.index):"";
			expObj.marked=workObj.index;					
		});
	});
}

function publicLabelDo(cont){
	$(".list-group-item").each(function(index,val){
		$(val).click(function(){		
			if(workObj.workData[2].dataLabel&&workObj.index==3){
				getData("http://localhost:8080/aibrainer_2.1.0/go/getpublicdata");
				
			}	
			workObj.workData[workObj.index].dataLabel=$(val).text();			
			if(workObj.workData[workObj.workData.length-1].dataLabel!=null){
				
				sendLabelData("http://localhost:8080/aibrainer_2.1.0/go/recordlabel",workObj.workData);
				workObj.index=-1;
				workObj.workData=workObj.preData;
			}	
			var srcPath = `${workObj.imgPath}/${workObj.developer}/${workObj.assignment}/data/`;
			workObj.img.attr("src", srcPath+workObj.workData[workObj.isSend?++workObj.index:workObj.workData.length-1].dataName);					
		});
	});
	$("#comeBack").click(function(){
		$("#mycontent").remove();
		cont.append(main.oldPage);
		main.data = workObj.lastPageData;
		listClick(cont);
		workObj.lastPageData=null;
		main.oldPage=null;	
		suitPage();
		$("body,html").scrollTop($("#j-slider").height());
	});
}

function labelDo(){
	$(".list-group-item").each(function(index,val){
		$(val).click(function(){
			if(workObj.workData.length==10){
				if(workObj.workData[4].dataLabel&&workObj.index==5){
					getData("http://localhost:8080/aibrainer_2.1.0/go/getdata.prestrain");
					
				}	
			}
			if(workObj.isSend){
				workObj.workData[workObj.index].dataLabel=$(val).text();
				
				//���ͱ��������
				if(workObj.workData[workObj.workData.length-1].dataLabel!=null){
					
					sendLabelData("http://localhost:8080/aibrainer_2.1.0/go/senddata.action",workObj.workData);
					workObj.index=-1;
					if(workObj.workData.length<10||workObj.preData.length==0){
						workObj.isSend=false;
						main.ajax("http://localhost:8080/aibrainer_2.1.0/go/sendisover.action","get",{developer:workObj.developer,assignment:workObj.assignment},true,false);
						alert("该任务已完成，进入等候审核期！您可以参加其他任务");				
						return;
					}
					workObj.workData=workObj.preData;
				}	
			}else{
				alert("该任务已完成，进入等候审核期！您可以参加其他任务");				
				return;
			}	
			$("#marked").text(parseInt($("#marked").text())+1);
			var srcPath = `${workObj.imgPath}/${workObj.developer}/${workObj.assignment}/data/`;
			workObj.img.attr("src", srcPath+workObj.workData[workObj.isSend?++workObj.index:workObj.workData.length-1].dataName)
		});
	});
}

function getData(url,username=null,async=true,satistic=false){
	var imgPath = `${workObj.imgPath}${workObj.developer}/${workObj.assignment}/`;
	const params = {
			developer:workObj.developer,
			assignment:workObj.assignment,
			taskType:workObj.taskType,
			username:username
	}
	$.ajax({
		url:url,
		type:"get",
		data:params,
		async:async,
		dataType:"json",
		contentType:"application/json",
		success:function(data){			
			let img = new Image();
			if(!satistic){
				workObj.preData=[];
				console.log(data.data.datas);
				$.each(data.data.datas,function(index,val){					
					img.src=imgPath+"data"+"/"+val.dataName;			
					workObj.preData.push(val);
				});
			}else{
				workObj.preData=null;
				$.each(data.data.datas,function(index,val){	
					if(workObj.taskType=="图像分类")
						img.src=imgPath+val.dataLabel+"/"+val.dataName;	
					else if(workObj.taskType=="拉框标注")
						img.src=imgPath+"data/"+val.dataName;	
				});
				workObj.preData=data.data;
			}
			
			if(workObj.publicFirst||url.lastIndexOf("first")!=-1){
				workObj.publicFirst = false;
				workObj.workData=workObj.preData;
				if(satistic){
					let name = workObj.workData.datas[0].dataName;					
					if(workObj.taskType=="图像分类"){
						workObj.img.attr("src",imgPath+workObj.workData.datas[0].dataLabel+"/"+workObj.workData.datas[0].dataName);
						userLabelList(workObj.workData.datas[0],workObj.workData.utlist[name]);
						$("#anno").text(workObj.workData.datas[0].dataLabel);
					}else if(workObj.taskType=="拉框标注"){
						console.log(imgPath+'data/'+workObj.workData.datas[0].dataName)
						canvasPic(workObj.workData.datas[0],imgPath+'data/'+workObj.workData.datas[0].dataName); 
						let label = workObj.workData.datas[0].dataLabel
						if(label=="-1") $("#anno").text("未检测到目标或图像非正常");
						else $("#anno").text(workObj.workData.datas[0].dataLabel);
					}
					$("#dataname").text(workObj.workData.datas[0].dataName);
//					userLabelList(workObj.workData.datas,workObj.workData.utlist);
				}					
				else{
					if(workObj.workData.length>0){
						console.log(workObj)
						if(workObj.taskType=="图像分类")
							workObj.img.attr("src",imgPath+"data"+"/"+workObj.workData[0].dataName);
						else if(workObj.taskType=="拉框标注")
							canvasPic(workObj.workData[0],imgPath+"data"+"/"+workObj.workData[0].dataName);
						$("#anno").text(workObj.workData[workObj.index].dataLabel);
						$("#dataname").text(workObj.workData[workObj.index].dataName);
					}
				}				
			}
		}
	});
}

function sendLabelData(url,markedData){
	$.ajax({
		url:url,
		async:true, 
		type:"post",
		dataType:"json",
		data:JSON.stringify(markedData),
		contentType:"application/json",
		success:function(data){
						
		}
	});
}

function initObj(){
	workObj.workData=[];
	workObj.preData=[];
	workObj.imgPath="http://localhost:8080/aibrainer_2.1.0/pictures/";
	workObj.developer=null;
	workObj.assignment=null;
	workObj.index=0;
	workObj.img=null;
	workObj.isSend=true;
	workObj.publicFirst=true;
	workObj.taskType=null;
}

function showResult(){
	alert(`标注正确个数：${expObj.right} \n标注错误个数：${expObj.wrong} \n准确率：${expObj.accuracy} \n标注单个数据平均用时为：${expObj.avgTime}`);
}
