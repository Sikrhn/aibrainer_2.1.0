var dataObj = {
		assignment:null,
		labels:null,
		description:null,
		isPublic:null,
		levelRank:null,
		needWorkers:null,
		unitPrice:null,
		fileList:null,
		predictExpend:null,
		send:true
}
var form = new FormData();
var isDeleteFlag=false; 

function calculatePross(){
	let task = $("#tasktype  option:selected").text();
	if(task=="图像分类"){
		if($("#unitPrice").val()&&$("#needWorkers").val()&&$("#input-id")[0].files.length>0){
			var expend = $("#unitPrice").val()*$("#needWorkers").val()*$("#input-id")[0].files.length;
			$("#predict_expend").text(parseFloat(expend).toFixed(2));
			$("input[name=predictExpend]").val(parseFloat(expend).toFixed(2));
		}
	}else if(task == "拉框标注"){
		if($("#unitPrice").val()&&$("#input-id")[0].files.length>0){
			var expend = $("#unitPrice").val()*$("#input-id")[0].files.length;
			$("#predict_expend").text(parseFloat(expend).toFixed(2));
			$("input[name=predictExpend]").val(parseFloat(expend).toFixed(2));
		}
	}
	
}


function getLabelList(){
  var list = $("input[name='labels']");
  if(list.length>0){
    var array = [];
    var flag = true;
    $("input[name='labels']").each(function(index,val){
        if($(val).val().length>0)
          array.push($(val).val())
        else
          flag = false;      
    });
    if(isDeleteFlag){
      array.push("其他");
    }
     return flag?array:false;
  }else{
    return false;
  }
}

function sumbitData(){

  var labelList = getLabelList();
  if(!labelList){
    alert("请添加标注按钮！");
    return;
  }   
  var assignment=$("#taskName").val();
  var fileList = $("#input-id")[0].files;
  if($("#profit")[0].checked){
      var levelRank = $("input[name='levelRank']").val()-0;
      var needWorkers = $("#needWorkers").val()-0;
      var unitPrice = $("#unitPrice").val()-0;
      if(assignment&&levelRank&&needWorkers&&unitPrice>=0&&fileList.length>0){
        }else{
          alert("请完善信息");
          return; 
        }     
      }else{
        if(assignment&&fileList.length>0){
        }else{
          alert("请完善信息");
          return;   
        }
    }
    if($("#tips").text().length>0){
    	alert("文件数量不得超过1000和文件大小不得超过50MB");
    	return;
    }
    
    if(dataObj.send){
    	$("#pros").css("display","");  
    	$(this).attr("disabled","disabled");
    	dataObj.send=false;
    	$.ajax({
  		url:"http://localhost:8080/aibrainer_2.1.0/session/addtask",
  		type:"post",
  		data:new FormData($("#form-data")[0]),
  		processData:false,
  		contentType:false,
  		xhr:function(){                        
             myXhr = $.ajaxSettings.xhr();
             if(myXhr.upload){ // check if upload property exists
                 myXhr.upload.addEventListener('progress',function(e){                            
                     var loaded = e.loaded;                  //已经上传大小情况 
                     var total = e.total;                      //附件总大小 
                     var percent = Math.floor(100*loaded/total)+"%";     //已经上传的百分比  
                                  
                     $(".progress-bar").css("width",percent);                                                                
                 }, false); // for handling the progress of the upload
             }
             return myXhr;
         }, 
  		success:function(data){
  			main.data=data;
  			if(data.code==200){
		  		alert("发布成功");
		  		window.location.href="http://localhost:8080/aibrainer_2.1.0/";
		  	}else{
		  		alert("发布失败！"+main.data.msg);
		  	}
	  	}
	  });
    }
	
  	
}

function fileSize(){
	var filesCount=1000;
	var filesSize=50;
	$("#input-id").change(function(e){
		$("#tips").text("");
		actual_filesSize=0;
	  var files = e.target.files;		              
	  actual_filesCount = files.length;
	  	if(actual_filesCount > filesCount){
		  $("#tips").text("文件数量超过"+filesCount);
		   $("#tips").css("color","red");
		 	 return ;
			}		   
	  for (var i = 0; i< files.length; ++i){
	  	  actual_filesSize=actual_filesSize+files[i].size/1024/1024;
		  if(actual_filesSize > filesSize){
			  $("#tips").text("文件总大小超过"+filesSize+"M");
			   $("#tips").css("color","red");
			  return ;
		  }
		}
	});
}
  	
  

