
$("#loginLi").click(function(){
	 if(!main.isPc){
   	  $(".icon-bar").click();
     } 
    openLoginModal();
});
$("#registerLi").click(function(){
	 if(!main.isPc){
   	  $(".icon-bar").click();
     } 
    openRegisterModal();
});

function showLoginForm(){    
    $(".tab-content").html(loginText);
    $('.modal-title').html('登录');
    $('#otherPage').attr("href","javascript:openRegisterModal()");
    $('#loginModal .registerBox').fadeOut('fast',function(){            
        });       
    $('.error').removeClass('alert alert-danger').html('');
    user_login();
}

function showRegisterForm(){  
	$(".tab-content").html(registerText);
    $('.modal-title').html('注册'); 
    $('#otherPage').attr("href","javascript:openLoginModal()"); 
    $('#loginModal .registerBox').fadeOut('fast',function(){              
        });       
    $('.error').removeClass('alert alert-danger').html(''); 
    user_reg();
}


function openLoginModal(){
    showLoginForm();
    setTimeout(function(){
        $('#loginModal').modal('show');    
    }, 230);
    
}
function openRegisterModal(){
    showRegisterForm();
    setTimeout(function(){
        $('#loginModal').modal('show');    
    }, 230);
    
}

function user_login(){
  
  $("#login_btn").click(function(){
      if($("#username").val()&&$("#password").val()){
       var data = {
        "username": $("#username").val(),
        "password": $("#password").val(),
        "isdeveloper": $("input[name='isdeveloper']:checked").val() == 'true' ? true:false
       
        }
      }else{
          alert("请填入账号密码！");
          return;
        }    

    main.ajax("http://localhost:8080/aibrainer_2.1.0/login",'post',data,false,true,"application/json",null,"登录失败，");
     if(main.data.code==200){

    	 window.location.href="http://localhost:8080/aibrainer_2.1.0";
     }
  });
}


function user_reg(){
  $("#reg_btn").click(function(){
	  if(matchInput())
		return;
    var data = {
      "username": $("#username").val(),
      "password": $("#password").val(),
      "isdeveloper": $("input[name='isdeveloper']:checked").val() === 'true' ? true:false,
      "email":$("#email").val()
    }
    main.ajax("http://localhost:8080/aibrainer_2.1.0/register",'post',data,false,true,"application/json","注册成功，去登录吧！","注册失败！");
    main.data.code==200?openLoginModal():"";
  });
}

var loginText=
        `<div><form class="form-signin"  method="post" >
                      <label for="inputUser" class="sr-only">Username</label>
                      <input type="text" name="username" id="username" class="form-control" placeholder="账号" required autofocus>
                      <label for="inputPassword" class="sr-only">Password</label>
                      <input type="password" name="password" id="password" class="form-control" placeholder="密码" required>
                      <div class="checkbox">
                        <label>
                          <input type="radio" name="isdeveloper" checked="checked" value=false />标注者&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          <input type="radio" name="isdeveloper" value=true />开发者&nbsp;&nbsp;&nbsp;
                        </label>
                      </div>
                      <span style="color: red"></span><br/>
                      
                    </form> 
                    <button id="login_btn" class="btn btn-lg btn-primary btn-block" style="margin-left: 0;" >登录</button>
                      <a id="otherPage" href="javascript:void(0)" class="btn btn-lg btn-primary btn-block" style="margin-left: 0">没有账号？注册</a>
                      <br>
                      <a href="http://localhost:8080/aibrainer_2.1.0/losskey.html" target="_blank" >忘记密码？</a> <div>`;
var registerText=` <div>
        <form class="form-signin"  method="post">  
        <label>
          <input type="radio" name="isdeveloper" checked="checked" value=false />标注者&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         开发者暂未开放注册
        </label>    
        <label for="inputUser" class="sr-only">Username</label>
        <input type="text" id="username" name="username"  class="form-control" placeholder="用户名" required autofocus>
        <span style="color: red"></span>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="password" class="form-control" placeholder="密码" required>
        <label for="inputPassword" class="sr-only"></label>
        <input type="password" id="repassword" class="form-control" placeholder="再次确认密码" required>
        <span style="color: red"></span>
        <label for="inputEmail" class="sr-only"></label>
        <input type="text" id="email" class="form-control" placeholder="电子邮箱" required>
        <br>
        
      </form>
      <button id="reg_btn"  class="btn btn-lg btn-primary btn-block" style="margin-left: 0;"  >注册</button>
        <a  id="otherPage" class="btn btn-lg btn-primary btn-block" type="submit" style="margin-left: 0;" >已有账号？登录</a>
        </div>`;                
function matchInput(){
	if( $("#username").val()&&$("#password").val()&&$("#repassword").val()&&$("#email").val()){		
		if($("#password").val()!=$("#repassword").val()){
			alert("两次密码不一致");
			return true;
		}
	}else{
		alert("请填入完整信息");
		return true;
	}
	return false;
}        
function sendParam(url,params,msg,errmsg){
        	$.ajax({
        		url:url,
        		method:"post",
        		contentType: 'application/json; charset=UTF-8',
        		data:params,
        		dataType:"json",
        		success:function(data){
        			if(data.code==200){
        				alert(msg);
        				window.location.href="http://localhost:8080/aibrainer_2.1.0";
        			}
        			else
        				alert(errmsg)
        		
        		}
        	});
        }
$("#btn1").click(function(){
	if(matchInput())
		return;
	var params = {
    		username:$("#username").val(),
    		password:$("#password").val(),
    		email:$("#email").val(),
    		isdeveloper:$("#isdeveloper")[0].checked      		
    }
	
	sendParam("http://localhost:8080/aibrainer_2.1.0/changePassword.action",params,"密码重置成功！","输入的账号邮箱错误或不存在！");
});

$("#develop_btn").click(function(){
	if(matchInput())
		return;
	var params = {
    		username:$("#username").val(),
    		password:$("#password").val(),
    		email:$("#email").val(),
    		isdeveloper:true,
    		mob:$("#mob").val(),
    		identity:$("#identity").val()
    }	
	sendParam("http://localhost:8080/aibrainer_2.1.0/register",JSON.stringify(params),"账号注册成功！","账号已存在！");

});
   
