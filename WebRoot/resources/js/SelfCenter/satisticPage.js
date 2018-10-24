
function showSatistic(cont,params){
	if(cont==undefined)
		return;
	$("#mycontent").remove();
	workObj.assignment=params.assignment;
	workObj.developer=params.developer;
	workObj.taskType=params.taskType;	
	main.ajax("http://localhost:8080/aibrainer_2.1.0/session/satistic","get",params,false,true);
	let data = main.data.data;
	AllUserLabelDetailPage(cont,data);
	workObj.img=$("#pic");
	$("body,html").scrollTop($("#j-slider").height());
	if(workObj.taskType=="拉框标注"){
		canvas = document.getElementById("canvas");
		ctx = canvas.getContext("2d");
	}
	getData("http://localhost:8080/aibrainer_2.1.0/session/viewlabels",null,false,true);
	satisticClickList(params);
}

function AllUserLabelDetailPage(cont,data){
	let total=0;
	let title=1;
	
	let btDownload = ``;
	if(main.isDeveloper){
		$.each(data.utList,function(index,val){
			total+=val.taskMoney;
		});
		btDownload = `
		 <li class="list-group-item"><h4><span class="statisticLabel label label-warning">总支出</span>:￥${total}</h4>	</li>
		 <li class="list-group-item"><h4><span class="statisticLabel label label-warning">剩还金额</span>:￥${data.predictExpend}</h4></li>
		 <li class="list-group-item"><button class="btn btn-success" id="download"><h4>数据已整理完毕，点击下载</h4></button></li>`;
	}	if(!main.isPc){
		title=4;
	}
	let rectStyle=workObj.taskType=="图像分类"?"col-sm-6 col-md-5":"col-sm-12 col-md-10";
	var content = `<div class="container" style="background-color: #ebebeb;">
				<div style="text-align:center;">
			    <h${title}>			    	
					${data.assignment}——统计结果
				</h${title}>
				<hr class="" style=" border-top: 1px solid #555555;">
			</div>
			<div class="row">
				<div class="col-sm-5 col-md-3 col-lg-3 col-md-offset-1 col-lg-offset-1">
					<ul class="list-group">
						<li class="list-group-item active"><h4><span class="statisticLabel label label-info">任务</span>：${data.assignment}</h4></li>
						<li class="list-group-item"><h4><span class="statisticLabel label label-info">开发者</span>：${data.developer}</h4></li>
						<li class="list-group-item"><h4><span class="statisticLabel label label-info">任务类型</span>：${data.utList[0].taskType}</h4></li>					   
						${btDownload}
					</ul>					
				</div>
				<div class="col-sm-7 col-md-7">
					 <div class="panel">
					 <table class="table table-hover"><caption><h4>标注者用户详情</h4></caption>
					    <thead>
					        <tr>
					            <th>标注者</th>
					            <th>被采纳数量</th>
					            <th>获取赏金</th>
					            <th>准确率</th>
					        </tr>
					    </thead>
					    <tbody>
					    	${userListRes(data.utList)}
					    </tbody>
					    </table>
			         </div>
				</div>
			</div>
				<div class="row" style="padding-top: 20px;">
				    <div class="${rectStyle} col-md-offset-1">
					    <div class="panel panel-primary">
							<div class="panel-heading">&nbsp;<span class="pull-left">数据图像：<span id="dataname"></span></span>
							</div>
							<div class="panel-body" style="text-align: center">
								<p id="imgarea" style="">${imgContent()}
								</p>
								
							</div>				
							<ul class="list-group " style="text-align: center;">
								<li class="list-group-item" value=""><h2><span class="label label-info">label：<span id="anno"></span></span></h2></li>
								<a class="list-group-item active" id="next" value="" href="javascript:void(0)">再看一张<span class="glyphicon glyphicon-chevron-right"></span></a>
							</ul>
						</div>
				    </div>
					${showUserLabels(data)}
			  </div>
			</div>`;
			if(cont)
				cont.append(content);
	}

function showUserLabels(data){
	let text=``;
	if(workObj.taskType=="图像分类"){
	text=`<div class="col-sm-6 col-md-5 ">
				   <div class="panel panel-primary">
			  		<div class="panel-heading">&nbsp;<span class="pull-left">标注者标签</span>
					</div>
					<div class="panel-body">
						<table class="table">
							<thead>
								<tr>
									<th>标注者名</th>
									<th>对该图像标注的标签</th>
								</tr>
							</thead>
							<tbody>
								${userLabelText(data.utList)}
							</tbody>
						</table>
					</div>									
				</div>
			</div>`;
	}
			return text;
}

function userListRes(data){
	let context=``;
	$.each(data,function(index,val){
		let color='';
		if(val.accuracy<60)
			color='danger';
		else if(val.accuracy<80)
			color='warning';
		else if(val.accuracy<90)
			color='info';
		else
			color='success';
		context+=`
		<tr>
        <td>${val.username}</td>
        <td>${val.adoptNum}</td>
        <td>￥${val.taskMoney}</td>
        <td>
            <div class="progress">
                <div class="progress-bar progress-bar-${color}" role="progressbar" aria-valuenow="${val.accuracy}" aria-valuemin="0" aria-valuemax="100" style="width: ${val.accuracy}%">${val.accuracy}%</div>
            </div>
        </td>
    </tr>`;
	});
	return context;
}
function satisticClickList(params){
	$("#next").click(function(){
		satisticNext("http://localhost:8080/aibrainer_2.1.0/session/viewlabels");
	});
	$("#download").click(function(){
		window.open(`http://localhost:8080/aibrainer_2.1.0/session/download.action?developer=${params.developer}&assignment=${params.assignment}&taskType=${params.taskType}&isPublic=0`);
	});
}
//�����û���label
function userLabelText(data){
	let context = ``;
	$.each(data,function(index,val){
		context+=`
		<tr id="${val.username}" class="success">
			<td>${val.username}</td>
			<td id="${val.username}Label"></td>
		</tr>`;
	})
	return context;
} 

function satisticNext(url){
	 satisticNextPic("http://localhost:8080/aibrainer_2.1.0/session/viewlabels");
	 let name = workObj.workData.datas[workObj.index].dataName;
	 if(workObj.taskType=="图像分类")
		 userLabelList(workObj.workData.datas[workObj.index],workObj.workData.utlist[name]);
}

function satisticNextPic(url){
	 workObj.index++;	
	 if(workObj.index==workObj.workData.datas.length-2)
		 getData(url,null,true,true);
	 if(workObj.index==workObj.workData.datas.length){
		 workObj.workData=workObj.preData;
		 workObj.index=0;
	 }
	 currentData=workObj.workData.datas[workObj.index];
	 let srcPath = `${workObj.imgPath}${workObj.developer}/${workObj.assignment}/`;
	 if(workObj.taskType=="图像分类")
		 workObj.img.attr("src", srcPath+currentData.dataLabel+"/"+currentData.dataName);
	 else if(workObj.taskType=="拉框标注")
		 canvasPic(currentData,srcPath+"data/"+currentData.dataName); 
	 $("#anno").text(currentData.dataLabel);
	 $("#dataname").text(currentData.dataName);
}	

function userLabelList(dataObj,userDict){
	console.log(dataObj,userDict);
	$.each(userDict,function(index,val){
		console.log(val);
		$(`#${val.username}Label`).text(val.dataLabel);
		$(`#${val.username}`).removeClass("success");
		$(`#${val.username}`).removeClass("danger");
		if(val.dataLabel!=dataObj.dataLabel)			
			$(`#${val.username}`).addClass("danger");
		else
			$(`#${val.username}`).addClass("success");			
	});
}