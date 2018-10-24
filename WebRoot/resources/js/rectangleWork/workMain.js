
$(function(){
	isPCUser();
	if(!main.isPc){		
	    $("#description").remove();
	    $(".pc").remove();
	    setMobileBt();
	    clickList();
	 }else{
	     clickList();
	 } 
  
	$.ajax({
	    url:"http://localhost:8080/aibrainer_2.1.0/go/work",
	    async:false,
	    type:"get",
	    data:{developer:getUrlParam("developer"),assignment:getUrlParam("assignment"),taskType:getUrlParam("taskType"),ispublic:true},
	    dataType:'json',
	    success:function(data){
	    	task = data.data.task;
	    	main.developer=task.developer;
	    	main.assignment = task.assignment;
	    	main.taskType = task.taskType;
	    	main.labelList = data.data.labelList;
	    	$("#developer").text(main.developer);
	    	$("#assignment").text(main.assignment);
	    	$("#description").text(task.description);
	    	$("#mymarked").text(data.data.usermarked);
	    	$("#marked").text(data.data.marked);
	    	$("#sum").text(data.data.task.sum);
	    	main.ispublic=task.ispublic;
	    	main.imgsrc = `pictures/${main.developer}/${main.assignment}/data/`
	    	console.log(data);
	    }
	});
	ajaxRequestData("http://localhost:8080/aibrainer_2.1.0/go/getdata.first");

});

main={
	isFirst:true,
	isPc:true,
	operate:false,
	imgIndex:0,
    imgsrc:null,
    labelList:null,
    developer:null,
    assignment:null,
    taskType:null,
    isSend:true,
    currentNum:5,
    ispublic:false,
    dtoTemp:{dataName:null,rectDatas:[]},
    currentWidth:0,
    currentHeight:0
}

workObj=[];
dtoObj=[];
preObj =[];

function getUrlParam(key) {
    // 获取参数
    var url = window.location.search;
    // 正则筛选地址栏
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    // 匹配目标参数
    var result = url.substr(1).match(reg);
    //返回参数值
    console.log(result ? decodeURIComponent(result[2]) : null);
    return result ? decodeURIComponent(result[2]) : null;
}
function isPCUser(){
    var mobile_flag ;
    var mobileAgents = [ "Android", "iPhone", "SymbianOS", "Windows Phone","iPod"];
    $.each(mobileAgents, function(index,val){
        if(navigator.userAgent.indexOf(val)>0){
          main.userAgent = val;
          main.isPc = false;
          return;
        }
    });
}

function ajaxRequestData(url){
	$.ajax({
	    url:url,
	    async:true,
	    type:"get",
	    data:{developer:main.developer,assignment:main.assignment,taskType:main.taskType},
	    dataType:'json',
	    success:function(data){		
	    	preObj = [];
        	$.each(data.data.datas,function(index,val){
        		preObj.push({
        			img:{dataName:val,currentScale:null},labels:[]
        		});
	        });
        	if(main.isFirst){
        		 main.isFirst=false;
        		 workObj=preObj;
        		 img.imgLoad(main.imgsrc+workObj[0].img.dataName);
        		 if(data.data.datas.length<5){
	        		main.currentNum=data.data.datas.length;
	        	}
        	}
        	
        		
	    }
	});
}
function ajaxPackage(url,params=null,async=true,method='get',container=null){
	$.ajax({
	    url:url,
	    async:async,
	    type:method,
	    data:params,
	    dataType:'json',
	    success:function(data){		
	    	container = data;
        		
	    }
	});
}

function sendData(url){
	reAbjust();
	$.each(workObj,function(index,val){
		dtoObj.push({dataName:val.img.dataName,rectDatas:[]});
		$.each(val.labels,function(i,v){
			dtoObj[index].rectDatas.push({
				developer:main.developer,
				assignment:main.assignment,
				username:null,
				dataName:val.img.dataName,
				width:v.actualW,
				height:v.actualH,
				dataLabel:v.label,
				xmin:v.x,
				ymin:v.y,
				xmax:v.x+v.width,
				ymax:v.y+v.height				
			});
		});
	});
	params = dtoObj;
	$.ajax({
	    url:url,
	    async:true,
	    type:"post",
	    contentType: 'application/json; charset=UTF-8',
	    data:JSON.stringify(params),
	    dataType:'json',
	    success:function(data){		        	        
	    	console.log(data);
	    	dtoObj=[];
	    }
	});
}