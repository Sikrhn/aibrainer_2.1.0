
function help(cont){
	var text=`<div id="mycontent" class="container" style="text-align:center;">		
			
		  <hr class="" style=" border-top: 1px solid #555555;">
		  <h1>__(￣▽￣)__等我有心情了再写....</h1>
    	</div>
    			`;
     	 cont.append(text);
}
function showHelp(cont){
	help(cont);
	cont.css("height",$(window).height())
	$("#mycontent").css("height",$(window).height()/2)
	$("body,html").scrollTop($("#j-slider").height());
	suitPage();
}

function invisableHome(){
	  $("#service").css("display","none");
	  $("#statics").css("display","none");
	  $("#clients").css("display","none");
}

function showHome(){
	  $("#cont").remove();
	  $("#service").css("display","block");
	  $("#statics").css("display","block");
	  $("#clients").css("display","block");
}