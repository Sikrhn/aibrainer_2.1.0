var canvas = null;
var ctx = null;
function userLabelDetail(cont,data){
	main.oldPage = $("#mycontent");
	$("#mycontent").remove();
	workObj.developer=data.task.developer;
	workObj.assignment=data.task.assignment;
	workObj.taskType=data.task.taskType;
	userLabelDetailPage(cont,data);
	workObj.img=$("#pic");	
	if(data.task.taskType=="拉框标注"){
		canvas = document.getElementById("canvas");
		ctx = canvas.getContext("2d");
	}
	getData("http://localhost:8080/aibrainer_2.1.0/session/userdetails",data.user.username);
	let params = {developer:workObj.developer,assignment:workObj.assignment,taskType:workObj.taskType,username:data.user.username}
	clickList(cont,params);
	$("body,html").scrollTop($("#j-slider").height());	
}

function userLabelDetailPage(cont,data){
	var text = `<div id="mycontent" class="container">
		  			<div style="text-align:center;">
				      	<h1>${data.user.username}的标注情况</h1>	
						<hr class="" style=" border-top: 1px solid #555555;">						
						<div class="panel panel-default" style="margin:auto;">
						<div class="panel-heading">
							&nbsp;&nbsp;&nbsp;数据图像：<span id="dataname"></span>
							<span class="pull-left">							
							<span id="backBt" class="glyphicon glyphicon-chevron-left"></span>						
							</span>&nbsp;<span class="badge pull-right">进度:${data.user.markedNum}/${data.task.sum}</span>
						</div>
						<div class="panel-body" style="text-align: center">
							<p id="imgarea" style="">${imgContent()}
							</p>
						</div>
						<ul class="list-group " style="text-align: center;">
							<li class="list-group-item" value="">${data.user.username}标记为：<span id="anno"></span></li>
							<a class="list-group-item active" id="next" href="javascript:void(0)">再看一张<span class="glyphicon glyphicon-chevron-right"></span></a>
							<a class="list-group-item danger" id="removeUser" href="javascript:void(0)">将该标注者从本任务中移除</a>
						</ul>
					</div>
				</div>
				<hr class="" style=" border-top: 1px solid #555555;">
				</div>`;
			
	cont.append(text);
			
	if(main.isPc&&data.task.taskType=="图像分类")
		$(".panel-default").css("width","60%");
	}

function imgContent(){
	var imgtext = ``;
	if(workObj.taskType=="图像分类"){
		imgtext=`<img id="pic" src="" class="img-thumbnail">`;
	}else if(workObj.taskType=="拉框标注"){
		imgtext=`<canvas id="canvas" src="" style="" class="img-thumbnail">`;
	}
	console.log(imgtext)
	return imgtext;
}

function backPage(cont){
	$("#backBt").click(function(){
		$("#mycontent").remove();
		cont.append(main.oldPage);
		developerInspectClick(cont);
		clickFun(cont);
		importAndExport();
		main.oldPage=null;
		suitPage();
		initObj();
		logout();
	});
}

function clickList(cont,params){
	backPage(cont);
	$("#removeUser").click(function(){
		deleteUser(cont,params);
	});
	$("#next").click(function(){
		console.log("asd")
		if(workObj.workData.length>0)
			nextPic("http://localhost:8080/aibrainer_2.1.0/session/userdetails");
	})
}

function deleteUser(cont,params){
	let flag = confirm("确定移除该标注者吗？");
	if(flag){
		main.ajax("http://localhost:8080/aibrainer_2.1.0/session/removeWorker.action",'get',params,false,false);
		$("#mycontent").remove();
		main.oldPage = null;
		DeveCenterPage(cont);
		initObj();
	}
}

function canvasPic(data,src){
	 
	 var img = new Image();
		img.src=src;
		img.onload=()=>{
			canvas.style["background-image"]=`url(${img.src})`;
			let width = $("#imgarea").width();
			if(width<img.width){
				scale=parseFloat(width/img.width).toFixed(2);
				height=img.height*scale						
			}else{
				scale=1;
				width=img.width
				height=img.height
			}
	    	ctx.clearRect(0,0,canvas.width,canvas.height);			
	     	canvas.style.width=width;
	    	canvas.style.height=height;
	    	canvas.width=width;
	    	canvas.height = height;	    	
	    	ctx.strokeStyle="#79FF79";
	    	ctx.strokeWidth=3;
	    	if(data.dataLabel==-1){
	    		$("#anno").text("图像中无目标或非正常显示");
	    	}
	    	console.log(data.xmin,data.ymin,data.xmax,data.ymax,data.width,data.height);
	    	ctx.strokeRect(data.xmin*scale,data.ymin*scale,(data.xmax-data.xmin)*scale,(data.ymax-data.ymin)*scale);
		}
}

function nextPic(url,satistic=false){
	 workObj.index++;
	 if(workObj.index==workObj.workData.length-2)
		 getData(url,workObj.workData[0].username,true,satistic);
	 if(workObj.index==workObj.workData.length){
		 workObj.workData=workObj.preData;
		 workObj.index=0;
	 }
	 let srcPath = `${workObj.imgPath}${workObj.developer}/${workObj.assignment}/data/`;
	 if(workObj.taskType=="图像分类")
		 workObj.img.attr("src", srcPath+workObj.workData[workObj.index].dataName);
	 else if(workObj.taskType=="拉框标注")
		 canvasPic(workObj.workData[workObj.index],srcPath+workObj.workData[workObj.index].dataName);
	 console.log(11)
	 $("#anno").text(workObj.workData[workObj.index].dataLabel);
	 $("#dataname").text(workObj.workData[workObj.index].dataName);
}	