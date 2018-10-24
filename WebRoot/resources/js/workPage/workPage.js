function workPage(cont,data){
	
	$("#mycontent").remove();
	initObj();
	if(!data){
		workObj.assignment = main.data.data.userTask.assignment;
		workObj.developer = main.data.data.userTask.developer;
		workObj.taskType= main.data.data.userTask.taskType;
		workText(cont,data,main.data.data.labelList);
		getData("http://localhost:8080/aibrainer_2.1.0/go/getdata.first");
		labelDo();
	}else{
		workObj.assignment = data.assignment;
		workObj.developer = data.developer;	
		workObj.taskType = data.taskType;
		workText(cont,data,main.data.data.labelList);
		getData("http://localhost:8080/aibrainer_2.1.0/go/getpublicdata");
		publicLabelDo(cont);
	}
	workObj.img=$("#imgs");
	suitPage();
	$("body,html").scrollTop($("#j-slider").height());
}

function exprience(cont){
	initObj();
	expObj={
		wrongData:[],
		sum:0,
		marked:0,
		wrong:0,
		right:0,
		accuracy:0,
		timeUp:0,
		avgTime:0
	}
	main.ajax("http://localhost:8080/aibrainer_2.1.0/experience","get",null,false,true);
	workObj.workData=main.data.data.datas;
	labels = main.data.data.labels;
	expObj.sum = workObj.workData.length;
	var text=`<div id="mycontent" class="container">
		<div id="workArea" style="text-align:center;">
	      	<h1>练习模式</h1>
	      	<h4>进度：<span id="marked">${expObj.marked}</span>/${expObj.sum}</h4>	      
			<hr style=" border-top: 1px solid #555555;">
			${imgArea(labels)}
        </div>   		
		<hr class="" style=" border-top: 1px solid #555555;">
		</div>
		`;
	cont.append(text);	
	experienceDo(expObj);
	suitPage();
	}

function workText(cont,data,labels){
	if(main.isPc){
		var suitTitle = 1;
	}else{
		var suitTitle = 4;
	}
	if(!data){
		var markedNum = main.data.data.userTask.markedNum;
		var sum = main.data.data.userTask.dataSum;
		var process = `<span class="badge pull-right">进度:<span id="marked">${markedNum}</span>/<span id="sum">${sum}</span></span>`;
	}else{
		if(suitTitle==4)size='200%';
		else size='';
		var process = `
  		&nbsp;<span class="pull-left">
		<span id="comeBack" style="font-size:${size};" class="glyphicon glyphicon-chevron-left"></span>
		</span>
	`;
			
	}
	
	var text=`<div id="mycontent" class="container">
  			<div id="workArea" >
		      	<h${suitTitle}>	${process}任务：${workObj.assignment}</h{suitTitle}>
		      	<h4>&nbsp&nbsp&nbsp&nbsp开发者：${workObj.developer}</h4>		      
				<hr style=" border-top: 1px solid #555555;">				
				${imgArea(labels)}  
		      </div>   		     
     </div>
     <hr class="" style=" border-top: 1px solid #555555;">
    `;
    cont.append(text);
    if(!main.isPc){
		$("td").each(function(index,val){
			$(val).css("width","");
		});
		$("table").css("width","100%");
		$("table").css("padding-top","20px");
	}
}
function imgArea(labels){
	var text=`<div class="row" id="imgArea">
		
		        <div class="col-sm-9 col-md-9" style=" padding-top: 10%;" >         
		           <div class="fakeimg">
		            <img id="imgs" src="" class="img-responsive img-thumbnail" alt="Cinque Terre">
		           </div>         
		        </div>
		    
		        <div class="col-sm-3 col-md-3" style=" padding-top: 10%;" id="">
		          <div class="list-group">
		          ${labelList(labels)}
		          </div>
		        </div>
		   </div> `;
	return text;
}
function labelList(labels){
	var labelText=``;
	var lastLabel =``;
	$.each(labels,function(index,val){		
		if(val=="其他")
			lastLabel+=`<a class="list-group-item" style="background-color:#d9534f; color:white;" value="${val}" href="javascript:void(0)">${val}</a>`;
		else
			labelText+=`<a class="list-group-item" value="${val}"  href="javascript:void(0)">${val}</a>`;		
	});
	labelText+=lastLabel;
	return labelText;
}

