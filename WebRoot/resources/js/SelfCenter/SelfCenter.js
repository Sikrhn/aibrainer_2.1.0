
function DeveCenterPage(cont){
	main.ajax("http://localhost:8080/aibrainer_2.1.0/session/developer","get",null,false,true);
	var data = main.data.data;
	var developer = data.developer;
	var pcsize = main.isPc?3:4;	
	var suitPadding = main.isPc?``:`style="padding-top:8%;"`;
	var content = ` 
	<div id="mycontent" class="container">
      	<div style="text-align:center;">
		      	<h${pcsize} ${suitPadding} class="title">个人中心</h${pcsize}>
				<hr class=""    style=" border-top: 1px solid #555555;">
		      	<h${pcsize}>用户名：${developer.developer}</h${pcsize}>
		      	<h${pcsize}>身份：开发者</h${pcsize}> 
		      	<h${pcsize}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;余额:￥${developer.balance}<button id="import" class="btn btn-warning" style="margin-left: 2%;">充值</button></h${pcsize}>
				<h${pcsize}><span class="label label-primary" >我发布的任务</span></h${pcsize}>
			</div>
			<div class="panel-group" id="taskList" >
		      		${taskText(data.taskList)} 		            
	        </div>
		        <h4 style="text-align:center; margin-top:4%;"><a href="http://localhost:8080/aibrainer_2.1.0/losskey.html" target="_blank" >修改密码</a></h4>
		        <h4 style="text-align:center; margin-top:4%;"><a id="logoutA" href="javascript:void(0)">注销</a></h4>
	              <hr class=""    style=" border-top: 1px solid #555555;">
	            </div> 
	          </div>
	         </div>	       
	         `;
	    cont.append(content);
	    suitPage($("#taskList"));
	    developerInspectClick(cont);
	    clickFun(cont);
	    importAndExport();
	    $("#cont").css("height","");
	    logout();
}


function UserCenterPage(cont){
	main.ajax("http://localhost:8080/aibrainer_2.1.0/session/user","get",null,false,true);
	var data = main.data.data;
	var user = data.user;
	var pcsize = main.isPc?3:4;
	var suitPadding = main.isPc?``:`style="padding-top:8%;"`;
	var content = ` 
			<div id="mycontent" class="container">
		      	<div style="text-align:center;">
				      	<h${pcsize} class="title" ${suitPadding}>个人中心</h${pcsize}>
						<hr class="" style=" border-top: 1px solid #555555;">
				      	<h${pcsize}>用户名：${user.username}</h${pcsize}>
				      	<h${pcsize}>身份：标注者</h${pcsize}>
				      	<h${pcsize}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;余额:￥${user.balance}<button id="export" class="btn btn-warning" style="margin-left: 2%;">提现</button></h${pcsize}>
						<h${pcsize}>等级：Lv.${user.rank}</h${pcsize}>
						<h${pcsize}>exp:${user.exp}</h${pcsize}>
						<h${pcsize}>准确率:${user.accuracy}%</h${pcsize}>
						<h${pcsize}><span class="label label-primary" >我参加的任务</span></h${pcsize}>
					</div>
					<div class="panel-group" id="taskList" >
				        ${taskText(data.taskList,user.username)}  		            
			        </div>
				        <h4 style="text-align:center; margin-top:4%;"><a href="http://localhost:8080/aibrainer_2.1.0/losskey.html" target="_blank" >修改密码</a></h4>
				        <h4 style="text-align:center; margin-top:4%;"><a id="logoutA" href="javascript:void(0)">注销</a></h4>
			              <hr class=""    style=" border-top: 1px solid #555555;">
			        </div> 
			    </div>
			  </div>
	        `;
	    cont.append(content);
	    suitPage($("#taskList"));
	    clickWorkPage(cont,null);
	    importAndExport();
	    clickFun(cont);
	    logout();
}

function taskText(data,username){
	var isOver="";
	var task = "";
	var lookOverBt="";	
	if($.isEmptyObject(data)){
		task=`
				<h1 style="text-align:center;">目前没有进行中的任务</h1>
			`;
		return task;
	}else {	
		var i = 0;
		$.each(data,function(key,val){
			var flag =i==0?"in":"collapse";
			let userlist=``;
			var assignment = key;
			var currentFinish =true;
			let btObj ={
				downloadBt : "前往统计页面下载数据并查看统计结果",
				calculateBt:"确认完成任务并进行数据统计",			
			}
			if(!main.isPc){
				btObj.downloadBt="前去统计页面下载";
				btObj.calculateBt="统计数据";
			}
			taskOver = val.task.isover;
			if(val.userList&&val.userList.length>0){
				$.each(val.userList,function(index,val){					
					if(!$.isEmptyObject(val)){
						var userOver = val.isover?"完成":"进行中";
						var overFlag;
						if(val.isover){
							overFlag="success";							
						}else{
							currentFinish=false; 
							
							overFlag="info";
						}
						var userLink = main.isDeveloper&&(!taskOver)?`<a class="userLabelLink" name="${assignment}" href="javascript:void(0)">${val.username}</a>`:`<span class="label label-info" id="userLevel">${val.username}</span>`;
						userlist += ` <tr class="userList">
				                        <td>${userLink}</td>
				                        <td><span class="label label-info" id="userLevel">${val.rank}</span></td>
				                        <td><span class="label label-info" id="marked_num">${val.markedNum}</span></td>
				                        <td><span class="label label-${overFlag}" id="marked_num">${userOver}</span></td>		                        
				                      </tr>`;				                      
					}
				});	
				
			}else{	
				currentFinish=false; 
				userlist +=` <tr class="userList">	
                <td colspan="2" ><h4><span class="label label-info" id="userLevel">已标记次数</span>：
                <span class="label label-info" id="marked_num">${val.markedNum}</span></h4></td>	                        
              </tr>`;
			}
			
			if(!val.task.ispublic){
				var thead = `<thead>
				                <tr>
				                <th>用户名</th>
				                <th>等级</th>
				                <th>标记数量</th>
				                <th>是否完成</th>
				              </tr>
				            </thead>`;
				if(val.userList.length==0)
					userlist = `<tr><td colspan=4><h4>暂无标注者加入本工作</h4></td><tr>`;			
				if(main.isDeveloper){
					if(val.task.isover){
						isOver=`✔`;
						
						lookOverBt=`<button class="btn btn-success satistic"  name="${val.task.assignment}" value="${val.task.ispublic}">${btObj.downloadBt}</button>`;
					}else if(currentFinish&&(val.task.taskType=="图像分类"&&val.task.workers==val.task.needWorkers||val.task.taskType=="拉框标注")){					
						isOver=`等待确认`;	
						lookOverBt=`<button class="btn btn-success calculate" name="${val.task.assignment}" value="${val.task.ispublic}">${btObj.calculateBt}</button>
								`;
					}else{
						isOver=`数据总量：${val.task.sum}`
						lookOverBt=`<h3><span class="label label-default">任务进行中...</span></h3>`;
					}
					
		            userlist += `<tr class="downloadBox">
								<td colspan=3>
									${lookOverBt}					
								</td>					
								<td >
									<button class="btn btn-danger delete" name="${val.task.assignment}" value="${val.task.ispublic}" >删除任务</button>
								</td>
								</tr>`; 
				}else{
					if(!val.task.isover){					
						if(isFinish(username,val)){
							lookOverBt = `<td colspan="4" style="text-align:center;"><h3>等待审核中...</h3></td>
							</tr>`;
							isOver = `等待审核`;		
						}else{
							lookOverBt =`<tr>
							<td colspan="4" ><button class="btn btn-primary" id="working" name="${val.task.assignment}" value="${val.task.developer}">前往标注</button></td>
							</tr>`;	
							isOver = `进行中`;					
						}					
					}else{
						isOver =`✔`;
						lookOverBt = `<tr>
						<td colspan="4" ><button class="btn btn-success satistic" name="${val.task.assignment}" value="${val.task.developer}">查看统计结果</button></td>
						</tr>`;						
					}
					userlist+=lookOverBt;
				}
			}else{
				isOver = val.task.isover?`✔`:`数据总量：${val.task.sum}`;
				var thead=`<h4 style="text-align:center">本任务为非赏金任务</h4>`;
				userlist += `<tr class="downloadBox">
					<td >
						<button class="btn btn-success download" name="${val.task.assignment}" value="${val.task.ispublic}">结束该任务并且下载结果</button>					
					</td>					
					<td >
						<button class="btn btn-danger delete" name="${val.task.assignment}" value="${val.task.ispublic}">删除任务</button>
					</td>
				</tr>`;
			}
			
			
			task+=`	<div class="panel panel-default taskLink ${key}" >
		              <div data-parent="#taskList"," data-toggle="collapse" data-target="#task${i+1}" class="panel-heading">
		                <h3 class="panel-title" name="${key}" value="${val.task.developer}">${key}&nbsp;&nbsp;<span class="label label-primary">${val.task.taskType}</span><span class="badge pull-right">${isOver}</span></h3>
		              </div>
		              <div class="${flag}" id="task${i+1}">
		                <div class="panel-body">
		                  <table class="table table-striped">
		                    ${thead}
		                    <tbody>
		                      ${userlist}
		                    </tbody>
		                  </table>
		                </div>
		               </div>
		              </div> 
		                `;				
		     i++;           
		});
		return task;
	}
}

