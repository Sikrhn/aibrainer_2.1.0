var taskListObj = {
		currentPage:null,	
		sumPage:null,
}
function taskList(cont,page){
	if(!main.isLogin){
		main.ajax("http://localhost:8080/aibrainer_2.1.0/taskview","get",null,false,true);
	}
	else{
		if(page==null){
			main.ajax("http://localhost:8080/aibrainer_2.1.0/session/gettask","get",{page:1},false,true);
			taskListObj.sumPage=main.data.data.sumPage;
			taskListObj.currentPage=1;
		}
		else{
			main.ajax("http://localhost:8080/aibrainer_2.1.0/session/gettask","get",{page:page},false,true);
			taskListObj.currentPage=page;
		}
	}
	//获取任务列表数据
	var taskList = main.data.data.taskList;
	//html任务列表文本
	var taskText = "";
	//登录查看更多赏金任务
	var warmLogin = main.isLogin?``:`<h4 style="text-align:center;">登录查看更多任务,<a id="login" href="javaScript:void(0)">登录</a></h4>`;
	//分页
	if(main.isLogin){
		var pageLi = '';
		for(var i = 1 ; i <= taskListObj.sumPage;i++){
			pageLi += `<li class="pageli"><a  href="javaScript:void(0)">${i}</a></li>`;
		}
		var splitPage = `<div class=" text-center">
							 	<p><ul  class="pagination">
								<li><a id="previous" href="javaScript:void(0)">&laquo;</a></li>
								${pageLi}
								<li><a id="next" href="javaScript:void(0)">&raquo;</a></li>
							</ul></p> 
							</div>
							</div>`;
	}else{
		var splitPage = "";
	}
	//迭代任务列表
	$.each(taskList,function(index,val){
		var flag = val.isover?"完成":"进行中";
		var taskProperty = val.ispublic?"非赏金":"赏金";
		if(main.isPc){
			taskText += `<tr>
							<td><a class="taskDetails" href="javascript:void(0)" >${val.assignment}</a></td>
							<td>${val.developer}</td>
							<td>${val.unitPrice}￥/张</td>
							<td>${val.exp}</td>
							<td>${flag}</td>						
							<td>${val.workers}/${val.needWorkers}人</td>
							<td>${val.date}</td>
							<td>${taskProperty}</td>
						</tr>`;
		}else{
			taskText += `<tr>
							<td><a class="taskDetails" href="javascript:void(0)" >${val.assignment}</a></td>
							<td>${val.unitPrice}￥/张</td>					
							<td>${val.workers}/${val.needWorkers}人</td>
							<td>${taskProperty}</td>
						</tr>`;	
		}
		
	});
	//主要html文本容器
	var content=`<div id="mycontent"  class="container" style="text-align:center;">
			<div class="table-responsive">
			<table class="table table-hover" style="  margin-top: 20px;
   				 box-shadow: 0px 0px 10px 0px #080808;">
				<caption><h1>开发者任务</h1></caption>
				<thead>
					<tr>
						${suitList()}
					</tr>
				</thead>
				<tbody>
					${taskText}
					
				</tbody>
		</table>
		</div>  	
		${warmLogin}
     	<hr class=""    style=" border-top: 1px solid #555555;">
     	${splitPage}
     </div>
		`;
	//加载到页面	
    cont.append(content);
    //页面高度==window高度
    $("#cont").height()<$(window).height()? $("#cont").css("height",$(window).height()):"";
    //加载点击事件
    listClick(cont);
    $(".pageli").each(function(index,val){
    	$(val).removeClass("active");
    	if(index==taskListObj.currentPage-1)
    		$(val).addClass("active");
    });
    suitPage();
}
//适应屏幕模式显示数据
function suitList(){
	if(main.isPc){
		var text=`<th>任务名</th>
						<th>开发者</th>
						<th>单价</th>
						<th>经验值</th>
						<th>状态</th>
						<th>人数</th>
						<th>日期</th>
						<th>任务性质</th>`;
		return text;
	}else{
		var text=`<th>任务名</th>
						<th>单价</th>
						<th>人数</th>
						<th>任务性质</th>`;
		return text;
	}
}
//点击事件
function listClick(cont){
	$(".taskDetails").each(function(index,val){
		$(val).click(function(){
			//任务详情
			taskDetails(cont,main.data.data.taskList[index]);
		});
	});
	$(".pageli").each(function(index,val){
		$(val).click(function(){
			$("#mycontent").remove();
			taskListObj.page = index+1;
			taskList(cont,index+1);
		});
	});
	$("#previous").click(function(){	
		$("#mycontent").remove();
		taskList(cont,taskListObj.page>1?taskListObj.page-1:taskListObj.page);
	});
	$("#next").click(function(){
		$("#mycontent").remove();
		taskList(cont,taskListObj.currentPage<taskListObj.sumPage?taskListObj.currentPage+1:taskListObj.currentPage);
	});	
	$("#login").click(function(){
		openLoginModal();
	})
}