
function taskDetails(cont,data){
	main.oldPage = $("#mycontent");
	$("#mycontent").remove();
	detailsText(cont,data);
	detailClickFun(cont,data);
	$("body,html").scrollTop($("#j-slider").height());
	$("#cont").css("height","");
}	

function detailsText(cont,data){
	var status;
	var bt = ``;	
	if(!data.isover)
		status = "进行中";
	else
		status = "完成";
	if( !main.isDeveloper&&data.ispublic){
		bt = `&nbsp; <tr style="text-align: center;"><td colspan="2">
		<button id="work" class="btn btn-success btn-lg ">进行标注</button></td>
		</tr>`;
	}else if(!main.isDeveloper){
		bt = `&nbsp; <tr style="text-align: center;"><td colspan="2">
		<button id="joinTask" class="btn btn-success btn-lg ">我要参加该任务</button></td>
		</tr>`;
	}else{
		bt=``;
	}
	//主要html文本
	var content=`<div id="mycontent" class="container">
      	<h1>
      		&nbsp;<span class="pull-left">
				<span id="comeBack" class="glyphicon glyphicon-chevron-left"></span>
			</span>
		</h1>	
     	 <div class="suitbox" style="
			    width:  70%;
			    margin-left:  auto;
			    margin-right:  auto;
						"><table id="detailsTable" class="table table-hover" style=" margin-top: 20px;
			   				 box-shadow: 0px 0px 10px 0px #747474;"> 
					    <tbody><tr>
						    <td class="ordTd"><h4>任务名:</h4></td>
						    <td><h4>${data.assignment}</h4></td>
					    </tr>
					     <tr>
						    <td class="ordTd"><h4>开发者:</h4></td>
						    <td><h4>${data.developer}</h4></td>
					    </tr>
					    <tr>
					    <td class="ordTd"><h4>任务类型:</h4></td>
					    <td><h4>${data.taskType}</h4></td>
					    </tr>
					     <tr>
						    <td><h4>描述:</h4></td>
						    <td><h4><p>${data.description}</p></h4></td>
					    </tr>
					     <tr>
						    <td class="ordTd"><h4>exp:</h4></td>
						    <td><h4>${data.exp}</h4></td>
					    </tr>
					     <tr>
						    <td class="ordTd"><h4>单价￥:</h4></td>
						    <td><h4>${data.unitPrice}￥/张</h4></td>
					    </tr>
					     <tr>
						    <td class="ordTd"><h4>数据量总数:</h4></td>
						    <td><h4>${data.sum}</h4></td>
					    </tr>
					     <tr>
						    <td class="ordTd"> <h4>目前工作者数量:</h4></td>
						    <td><h4>${data.workers}</h4></td>
					    </tr>
					    <tr>
						    <td class="ordTd"> <h4>需要的工作者数量:</h4></td>
						    <td><h4>${data.needWorkers}</h4></td>
					    </tr>
					      <tr>
						    <td class="ordTd"><h4>要求等级:</h4></td>
						    <td><h4>${data.levelRank}</h4></td>
					    </tr>
					     <tr>
						    <td class="ordTd"><h4>发布时间:</h4></td>
						    <td><h4>${data.date}</h4></td>
					    </tr>
					    <tr>
					    	<td class="ordTd"><h4>任务性质:</h4></td>
					    	<td><h4>${data.ispublic?"非赏金":"赏金"}</h4></td>
					    </tr>
					     <tr>
						    <td class="ordTd"><h4>任务状态:</h4></td>
						    <td><h4>${status}</h4></td>
					    </tr>
			
			${bt}

					</tbody></table>
			</div>   	
       <hr class="" style=" border-top: 1px solid #555555;">
    </div>
    `;
    cont.append(content);
    if(!main.isPc){
    	$(".suitbox").css("width","");
    	$(".ordTd").css("width","40%");
    }
}

function detailClickFun(cont,val){
	$("#comeBack").click(function(){
		$("#mycontent").remove();
		cont.append(main.oldPage);
		listClick(cont);
		suitPage();
		main.oldPage=null;	
		$("body,html").scrollTop($("#j-slider").height());
	});
	$("#joinTask").click(function(){
		var param = {developer:val.developer,assignment:val.assignment,dataSum:val.sum,taskType:val.taskType};
		main.ajax("http://localhost:8080/aibrainer_2.1.0/session/jointask.action","get",param,false,false,null,"欢迎成为本任务的标注者，前往个人中心点击任务开始标注吧！","加入失败！");
	});
	$("#work").click(function(){
		if(val.taskType=="图像分类"){
			var param = {developer:val.developer,assignment:val.assignment,taskType:val.taskType,ispublic:val.ispublic};
			workObj.lastPage = main.oldPage;
			workObj.lastPageData = main.data;
			workObj.taskType=val.taskType;
			main.ajax("http://localhost:8080/aibrainer_2.1.0/go/work","get",param,false,true);
			workPage(cont,param);
			$("body,html").scrollTop($("#j-slider").height());
		}else if(val.taskType=="拉框标注"){
			window.open(`http://localhost:8080/aibrainer_2.1.0/work.html?developer=${val.developer}&assignment=${val.assignment}&taskType=${val.taskType}&ispublic=1`);
		}
	});
}