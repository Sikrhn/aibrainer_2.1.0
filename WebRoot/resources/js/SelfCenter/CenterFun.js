
function importAndExport(){
	$("#import").click(function(){
		alert('(￣▽￣)"呵呵哒');
	});
	$("#export").click(function(){
		alert('(￣▽￣)"呵呵哒');
	});
}
function isFinish(name,list){
	var flag = false;
	$.each(list.userList,function(key,val){		
			if(name == val.username){
				flag = val.isover;
			}	
	});
	return flag;
}

function clickWorkPage(cont){
	$("#working").click(function(){
		let obj = $(this);
		let params = main.data.data.taskList[obj.attr("name")].task;
		console.log(params);
		if(params.taskType=="图像分类"){
			main.ajax("http://localhost:8080/aibrainer_2.1.0/go/work","get",params,false,true);
			workPage(cont,null);
		}else if(params.taskType=="拉框标注"){
			window.open(`http://localhost:8080/aibrainer_2.1.0/work.html?assignment=${params.assignment}&developer=${params.developer}&taskType=${params.taskType}`);
		}
	});
}
function logout(){
	$("#logoutA").click(function(){
		main.ajax("http://localhost:8080/aibrainer_2.1.0/logout","get",null,true,true);
		window.location.href="http://localhost:8080/aibrainer_2.1.0";
	});
}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!
function developerInspectClick(cont){
	$(".userLabelLink").each(function(index,val){
		$(val).click(function(){
			let params = main.data.data.taskList[$(this).attr("name")].userList;			
			var obj = {task:main.data.data.taskList[$(this).attr("name")].task,user:""};
			let username = $(val).text();
			$.each(params,function(index,val){
				if(val.username==username){
					obj.user=val;
					userLabelDetail(cont,obj);
					return;
				}					
			});
		});
	});	
}

function clickFun(cont){
	let array = ["download","satistic","delete","calculate"];
	let taskList = main.data.data.taskList;
	$.each(array,function(i,v){
		$.each($("."+v),function(index,val){
			$(val).click(function(){
				let obj = $(this);
				let assignment = obj.attr("name");
				let params = taskList[assignment].task;
				console.log(params);
				verifyRequest(cont,obj,v,params);
				
			});
		});
	});	
} 

function verifyRequest(cont,obj,operate,params){
	switch(operate){
	case "download":
		if(params.ispublic){
			if(!params.isover){
				if(params.sum*5>main.data.data.taskList[params.assignment].markedNum)
					var warm = `\n被标记次数达到${params.sum*5}之后完成任务能使标注结果能加准确`;
				else
					var warm = `可能需要一定的时间进行统计数据`;
				let flag = confirm("确定完成任务并且下载标注结果吗？"+warm);
				if(flag)	
					window.open(`http://localhost:8080/aibrainer_2.1.0/session/download.action?developer=${params.developer}&assignment=${params.assignment}&taskType=${params.taskType}&isPublic=1`);
			}else{
				window.open(`http://localhost:8080/aibrainer_2.1.0/session/download.action?developer=${params.developer}&assignment=${params.assignment}&taskType=${params.taskType}&isPublic=1`);
			}
			
		}else{
			alert("download!");
			//ajax
		}
		break;
	case "delete":
		if(params.ispublic){
			let flag = confirm("确定删除任务吗？删除后将无法找回");
			if(flag){					
				$.ajax({
					url:'http://localhost:8080/aibrainer_2.1.0/session/deletetask.action',
					async:true,
					type:'get',
					data:params,
					dataType:'json',
					success:function(data){							
						$(`.${params.assignment}`).remove();
					}
				});					
			}
		}else{
			let flag = confirm("确定删除任务吗？删除后将无法找回");
			if(flag){					
				$.ajax({
					url:'http://localhost:8080/aibrainer_2.1.0/session/deletetask.action',
					async:true,
					type:'get',
					data:params,
					dataType:'json',
					success:function(data){	
						if(data.code==200){
							$(`.${params.assignment}`).remove();
							alert("删除成功！");
						}							
						else
							alert("删除失败！"+data.msg)
					}
				});					
			}
		}
		break;
	case "satistic":
		if(params.ispublic){
				
			alert("satisitic!");
			//ajax				
			
		}else{
			showSatistic(cont,params);
		}
		break;
	case "calculate":
		obj.attr("disabled","disabled");
		obj.text("统计中");
		$.ajax({
			url:"http://localhost:8080/aibrainer_2.1.0/session/calculate",
			data:params,
			async:true,
			dataType:'json',
			success:function(data){
				obj.attr("disabled",false);
				obj.text("前往统计页面下载数据");
				obj.unbind();
				obj.removeClass("calculate");
				obj.addClass("satisitic");
				obj.click(function(){showSatistic(cont,params);});
			}
		});
		break;
	}	
} 
function publicClickFun(data){
	var index = 0;
	$.each(data,function(key,val){				
		if(val.task.ispublic){
			var params = {developer:val.task.developer,assignment:key,ispublic:val.task.ispublic};
			$(`#publicdownload${index}`).attr("value",`${index}`);
			$(`#publicdeleteTask${index}`).attr("value",`${index}`);
			if(val.task.sum*5>val.markedNum)
				var warm = `\n被标记次数达到${val.task.sum*5}之后完成任务能使标注结果能加准确`;
			else
				var warm = `可能需要一定的时间进行统计数据`;	
			$(`#publicdownload${index}`).click(function(){
				var flag = confirm("确定完成任务并且下载标注结果吗？"+warm);
				if(flag){		
					window.open(`http://localhost:8080/aibrainer_2.1.0/session/download.action?developer=${params.developer}&assignment=${params.assignment}`);
				}
			});
			$(`#publicdeleteTask${index}`).click(function(){
				var flag = confirm("确定删除任务吗？删除后将无法找回");
				let i = $(this).val();
				if(flag){					
					$.ajax({
						url:'http://localhost:8080/aibrainer_2.1.0/session/deletetask.action',
						async:true,
						type:'get',
						data:params,
						dataType:'json',
						success:function(data){							
							$($(`#taskLink${i}`)).remove();
						}
					});					
				}
			});		
		}
		index++;
	});
}
