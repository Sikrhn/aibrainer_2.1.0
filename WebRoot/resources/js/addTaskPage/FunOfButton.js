/*
	Create By U_now 2018.08.25
 */

function add(id,val){
	var label = id==0?"如：小猫":"annotation";
	if(val!=null){
		var Bhtml=`<li id="label${id}" class="setlabel">
	        	<div class="my-input" >
	        		<div class="input-group">
						<span class="input-group-addon">Label ${id}</span>
						<input 	id="val${id}" name="labels" type="text" class="form-control" value="${val}" placeholder="${label}">
						<button id="del${id}" type="button" class="btn btn-danger" style="margin-left:5%;" onclick="delNode('label${id}')">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
					</div>				
				</div>
			</li> `
	}else{
		var Bhtml=`<li id="label${id}" class="setlabel">
	        	<div class="my-input">
	        		<div class="input-group">
						<span class="input-group-addon">Label ${id}</span>
						<input  id="val${id}" name="labels" type="text" class="form-control" value="" placeholder="${label}">
						<button id="del${id}" type="button" class="btn btn-danger" style="margin-left:5%;" onclick="delNode('label${id}')">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
					</div>				
				</div>
			</li> `
	}
	
	return Bhtml;
}
function addButton(){
	$('#btList').append(add($('.setlabel').length))
}
function delNode(id){
	$("#"+id).remove();
	reSetId();
}
function reSetId(){
	var lastSet = $(".setlabel");
	$('#btList').empty();
	lastSet.each(function(index,val){
		$('#btList').append(add($('.setlabel').length,val.children[0].children[0].children[1].value));
	});
}

function decision(id){
	$("#val"+id).attr('disabled',true);
	$('#yes'+id).css('display','none');
	$('#del'+id).css('display','none');
	$('#reset'+id).css('display','inline-block');
}
function update(id){
	$("#val"+id).attr('disabled',false);
	$('#yes'+id).css('display','');
	$('#del'+id).css('display','');
	$('#reset'+id).css('display','none');
}

		
function delActivaty(){
	if(isDeleteFlag){
		$("#delBt").removeClass("btn-success").addClass("btn-default");
		$("#delValue").attr("name","");
		alert("取消了其他标签！");	  			
		isDeleteFlag=false;
		
	}else{
		$("#delBt").removeClass("btn-default").addClass("btn-success");
		$("#delValue").attr("name","labels");
		alert("成功设置了其他标签！");
		isDeleteFlag=true;
	}
}







