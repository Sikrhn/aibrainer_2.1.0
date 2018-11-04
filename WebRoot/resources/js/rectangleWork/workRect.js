EventList = {   
	preRect:function(){
		$("#pre").click(function(){
			if(workObj[main.imgIndex].labels.length){
				let obj=workObj[main.imgIndex].labels.pop();
				$("#canvas").removeLayer(obj.name);
				$("#canvas").drawLayers();
				operateDOM.deleteRectDom(obj);
				layer--;
				
			}	    				    				    			
		});
	},
	showCurrentLabel:function(){
		$(".labelTitle").each(function(){
			let now = $(this);
			if(now.hasClass("in")){
				now.removeClass("in");
				now.addClass("collapse");
			}
		});
	},
	nextImg:(improper=false)=>{
		layer=0;
		labellist=workObj[main.imgIndex].labels
		$.each(labellist,function(index,val){
			jqCanves.removeLayer(val.name);
		});
		if(main.currentNum==5){
			if(main.imgIndex==3){
				ajaxRequestData("http://localhost:8080/aibrainer_2.1.0/go/getdata.prestrain");			
			}
		}
		if(main.isSend){
			blackout=beforeInspect();
			console.log("----",workObj);
			console.log("----",main.imgIndex);
			if(blackout&&(!improper)){
				alert("请注上标签！");
				return;
			}
			if(main.currentNum>0&&main.imgIndex==main.currentNum-1){			
				if(!main.ispublic)
					sendData("http://localhost:8080/aibrainer_2.1.0/go/sendrectdata.action");
				else
					sendData("http://localhost:8080/aibrainer_2.1.0/go/sendpublicrectdata.action");				
				main.index=-1;
				if(main.currentNum<5||preObj.length==0){
					main.isSend=false;
					$("#marked").text(parseInt($("#marked").text())+1);
					$("#mymarked").text(parseInt($("#mymarked").text())+1);
					if(!main.ispublic){
						ajaxPackage("http://localhost:8080/aibrainer_2.1.0/go/sendisover.action",{developer:main.developer,assignment:main.assignment,taskType:main.taskType},false);
						alert("该任务已完成，进入等候审核期！您可以参加其他任务");		
					}else{
						ajaxPackage("http://localhost:8080/aibrainer_2.1.0/go/sendisover.action",{developer:main.developer,assignment:main.assignment,ispublic:1},false);
						alert("该任务已完成，感谢您的参与！");		
					}
					return;
				}
				
					
				main.currentNum = preObj.length;
				workObj=preObj;
			}	
		}else{
			alert("该任务已完成，进入等候审核期！您可以参加其他任务");			
			return;
		}	
		
		$("#marked").text(parseInt($("#marked").text())+1);
		$("#mymarked").text(parseInt($("#mymarked").text())+1);
		img.imgLoad(main.imgsrc+workObj[main.imgIndex>=main.currentNum-1?main.imgIndex=0:++main.imgIndex].img.dataName);		
		$(".labelLink").remove();
	}
}


	
	
 operateDOM={
	addRectDom:function(obj){
		EventList.showCurrentLabel();
    	setRectMsg($("#labelList"),obj);
    	selectChange();
    },
    deleteRectDom:function(obj){
    	$("#"+obj.name).remove();
    	selectChange();
    },
    setRectObj:function(index,name,actualW,actualH,x,y,width,height,label=null){
    	return {
    		index:index,
    		name:name,
    		actualW:actualW,
    		actualH:actualH,
    		x:x,
    		y:y,
    		width:width,
    		height:height,
    		label:label
    	}
 	}

}

$("#next").click(function(){
	EventList.nextImg();		
});
$("#improper").click(function(){
	filterImg();
})


 function drawPen(){
        var color = "#79FF79";
        var width = 3;
        CanvasExt.touchDrawRect(color,width);
        CanvasExt.mouseDrawRect(color,width);
        
    }
function clickList(){
	
	$("#operate").click(function(){
	if(!main.operate){
		$(this).text("暂停标注");
		$(this).addClass("active");
		main.operate=true;
	}else{
		main.operate=false;
		$(this).text("开始标注");
		$(this).removeClass("active");
	} 
	drawPen();
	});
	EventList.preRect();
}

function beforeInspect(){
	labels = workObj[main.imgIndex].labels;
	console.log(labels,main.imgIndex);
	blackout = false;
	if(labels.length>0){
		$.each(labels,function(index,val){
			console.log(val)
			if(val.label==null||val.label==""){
				blackout=true;
				return;
			}
		});
		return blackout;
	}else{
		
		console.log(labels,main.imgIndex)
		return true;
	}
	
}

function reAbjust(){
	$.each(workObj,function(index,val){
		if(val.label){
			scale = val.img.currentScale;
			$.each(val.labels,function(i,v){
				if(v.width<0&&v.height<0){
					v.x = (v.x+v.width);
					v.y = (v.y+v.height);
					v.width=(v.width*(-1));
					v.height=(v.height*(-1));
				}else if(v.width<0){
					v.x = (v.x+v.width);
					v.width=(v.width*(-1));
				}else if(v.height<0){
					v.y = (v.y+v.height);
					v.height=(v.height*(-1));
				}
				v.x=parseInt(v.x/=scale);
				v.y=parseInt(v.y/=scale);
				v.width=parseInt(v.width/=scale);
				v.height=parseInt(v.height/=scale);
			});
		}
	});
}


function setMobileBt(){
  let text=`
    <a id="operate" class="list-group-item " href="javascript:void(0)">开始标注</a>        
    <a id="pre" class="list-group-item " href="javascript:void(0)">撤回到上一步</a>
    <a id="improper"  class="list-group-item " href="javascript:void(0)">图像非正常</a>
    <a id="next" class="list-group-item active" href="javascript:void(0)">下一张</a>
  `;
  $(".btlist").append(text);

}




function setRectMsg(container,obj){
  let labeltext = ``;
  $.each(main.labelList,function(index,val){
	  labeltext+=`<option value="${val}">${val}</option>`; 
  });
  let text=`
            <div class="panel panel-default labelLink" id="${obj.name}">
              <div data-parent="#labelList"  data-toggle="collapse" data-target="#label${obj.index}" class="panel-heading">
                <h3 class="panel-title">开始标注${obj.index}</h3>
              </div>
              <div class="labelTitle in" id="label${obj.index}"">
                <div class="panel-body">
                  <ul>
                    <li>label
                      <select class="labels">  
                        <option value="" selected></option>             
                        ${labeltext}                  
                      </select>
                    </li>
                    <li>
                      起点坐标：&nbsp;(${obj.x},${obj.y})
                    </li>
                    <li>
                      宽度：&nbsp;${obj.width}px
                    </li>
                    <li>
                      高度：&nbsp;${obj.height}px
                    </li>
                  </ul>
                </div>
               </div>
              </div> 
        `;
        container.append(text);
}

function selectChange(){
  $.each(workObj[main.imgIndex].labels,function(index,val){
    $(".labels").eq(index).change(function(){
      val.label=$(this).val();
    })
  })
}

function filterImg(){
	var flag = confirm("此图像归类为非正常并且进入下一张？");
	if(flag){
		workObj[main.imgIndex].labels.push(operateDOM.setRectObj(0,null,0,0,0,0,0,0,-1));
		EventList.nextImg(true);
	}
	
}