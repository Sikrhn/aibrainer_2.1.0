/*
 * 标注者查看统计页面，还有那个 标注者确认完成任务后查看单个标注用户单个数据的src的修改
 * 
 * 
 * */
window.onload=function(){	  
	  if(!main.isLogin){
		  main.ajax('http://localhost:8080/aibrainer_2.1.0/homeData','get',null,false,true);
		  $("#waiting").css("display","none");
		  $("#Loading").css("display","none");
		  main.isLogin = main.data.login;
		  main.isDeveloper = main.data.developer;
	  }  
	  isPCUser();
	  drawPage();
	  nav_event();
	  func();
	  user_bar();
	  if(main.isLogin){
		  
		  if(main.isDeveloper){  			 
			  $.getScript("resources/js/addTaskPage/addTaskPage.js");  
			  $.getScript("resources/js/addTaskPage/upload.js");	
			  $.getScript("resources/js/addTaskPage/FunOfButton.js");
			 
		  }
		  $.getScript("resources/js/SelfCenter/SelfCenter.js");
		  $.getScript("resources/js/SelfCenter/CenterFun.js");
		  $.getScript("resources/js/SelfCenter/userLabelDetail.js");
		  $.getScript("resources/js/SelfCenter/satisticPage.js");
	  }	 
}  
const main = {
    userAgent:navigator.userAgent,
    isPc:true,
    isLogin:false,
    isDeveloper:false,
    oldPage:null,
    oldFooter:null,
    user:null,
    data:null,
    ajax:function(url,method,params,async,storage,contentType='application/x-www-form-urlencoded',successMsg=null,failMsg=null){
      if(params&&contentType=="application/json")
    	  params = JSON.stringify(params);
      let newPath = url.substring(url.lastIndexOf("/")+1);
      $.ajax({
        url:url,
        async:async,
        type:method,
        data:params,
        contentType: contentType+';charset=UTF-8',
        dataType:'json',
        success:function(data){
        	storage?main.data=data:main.data;
        	if(data.code==200){
        		successMsg?alert(successMsg):"";
        		
        	}else{
        		failMsg?alert(failMsg+data.msg):"";
        	}        		
        }
      });
    }
}

function drawPage(){
	var fourData = main.data.data.homeData;
	$("#tasks").text(fourData.taskNum);
	$("#developers").text(fourData.developerNum);
	$("#users").text(fourData.userNum);
	$("#accuracys").text(fourData.allAccuracy);
	$(".button").unbind("click");
	$("#joinUs").click(function(){
		openLoginModal();
	});
	if(main.isLogin){
		$("#joinUs").unbind("click");
		$("#joinUs").text("WELCOME");
		$("#joinUs").click(function(){
			alert('(￣▽￣)"呵呵哒');
		});
		$("#registerLi").remove();
		$("#loginLi").remove();
		var text=`<li><a class="change-cont" href="javascript:void(0)" ><span class="glyphicon glyphicon-user"></span>个人中心</a></li>`;
		$("#user-bar").append(text);
	}
}

function isPCUser(){
      var mobile_flag ;
      var mobileAgents = [ "Android", "iPhone", "SymbianOS", "Windows Phone", "iPad","iPod"];
      $.each(mobileAgents, function(index,val){
          if(navigator.userAgent.indexOf(val)>0){
            main.userAgent = val;
            main.isPc = false;
            return;
          }
      });
}

function nav_event(){
  $(".change-cont").each(function(index,val){
	if(index==2&&main.isLogin){
		main.isDeveloper?$(val).html("添加任务"):$(val).html("练习");
	}  
	$(val).click(function(){
		if(index!=3){
		      invisableHome(); 
			  var cont = document.querySelector("#cont"); 
		      if(cont==null){
		        linkPage($(val),cont,index);
		      }else{
		        cont.remove();
		        linkPage($(val),cont,index);
		      }
		      if(index!=0)
		        $("body,html").scrollTop($("#j-slider").height());
		      else
		        $("body,html").scrollTop(0);
		      if(!main.isPc){
		    	  $(".icon-bar").click();
		      } 
		     
		}     
    });
  });  
}
function linkPage(val,cont,index){
	cont = $("<div></div>");
	cont.attr("id","cont");
	cont.css("background","url(resources/images/body-bg.jpg) repeat");       
	$("#operate").append(cont);
        if(main.isLogin){
        	initObj();
            switch(index){
              case 0: 
            	showHome();
                break;
              case 1:
            	taskList(cont,null);
                break;
              case 2:
            	if(main.isDeveloper){
            		addTask(cont)
            	}else{
            		exprience(cont);
            	}
                break;
              case 3:
                window.open("http://localhost:8080/aibrainer_2.1.0/help.html" );
                break;
              case 4:
                main.isDeveloper?DeveCenterPage(cont):UserCenterPage(cont);
                break;
          }
            
        }else{
          switch(index){
              case 0:  
            	showHome();
                break;
              case 1:
                taskList(cont);
                break;
              case 2:
            	exprience(cont);
                break;
              case 3:
	            window.open("http://localhost:8080/aibrainer_2.1.0/help.html" );
	            break;
          	}
        }
}
function suitPage(val){
	
	if($("#mycontent").height() <= $(window).height()-200){		
		console.log($("#mycontent").height(),$(window).height())
	    	$("#cont").css("height",$(window).height());
			$("#mycontent").css("height",$(window).height()-100);
	}else{
		console.log($("#mycontent").height(),$(window).height())
		$("#cont").css("height","");
	}
    if(main.isPc&&val!=null){
		val.css("margin-right","auto");
    	val.css("margin-left","auto");
    	val.css("width","60%");
    }
    
    if(!main.isPc){
    	if($("#mycontent").height() <= $(window).height()){			
	    	$("#cont").css("height",$(window).height());
		}else{
			$("#cont").css("height","");
		}
    	$("#cont").css("padding-top","20%");
    	$(".imgtd").each(function(index,val){
			$(val).css("width","");
		});
		$("#imgArea").css("width","100%");
		$("#imgArea").css("padding-top","20px");
    }else{
    	$("#imgs").css("width","80%");
    	$($(".imgtd")[1]).css("height","500px");
    }
}

