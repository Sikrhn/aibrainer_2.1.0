

// 一些功能
function func(){	
	if(main.isPc){
		$(window).scroll(function () {		
		  if ($(".navbar").offset().top > 50) {
		  	$(".navbar-fixed-top").addClass("top-nav");
		  }else {
		  	$(".navbar-fixed-top").removeClass("top-nav");
		  }
	  	})
	}else{
		$(".navbar-fixed-top").addClass("top-nav");
	}
	
  //关闭click.bs.dropdown.data-api事件，使顶级菜单ui可点击
	$(document).off('click.bs.dropdown.data-api');
	//自动展开
	$('.nav .dropdown').mouseenter(function(){
	 $(this).addClass('open');
	});
	//自动关闭
	$('.nav .dropdown').mouseleave(function(){
	 $(this).removeClass('open');
	});
}

// 用户信息下拉窗
function user_bar(){
	var user = $('#user-bar');
	var user_cont = $('#user_cont');
	user_cont.hide();
	user.mouseenter(function(){
		user_cont.show();
	})
	user.mouseleave(function(){
		user_cont.hide();
	})
}
function setWorkStepSuit(){
	var width = this.innerWidth;
}


