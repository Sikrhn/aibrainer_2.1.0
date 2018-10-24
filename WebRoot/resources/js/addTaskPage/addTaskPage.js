function addTask(cont){

  var text=`<div id="mycontent" class="container" style="border-radius: 5px;
      background-color: #ebebeb;">
      <h1>添加任务</h1>
       <hr class="" style=" border-top: 1px solid #555555;">
    <div class="row">
     <div class="col-sm-6 col-sm-offset-3 col-md-6 col-md-offset-3" >
          <form id="form-data" enctype="multipart/form-data">
            <h3>任务名称:</h3>
            <input id="taskName" type="text"  class="form-control" style="width: 60%;" name="assignment" placeholder="Project Name" required/>
            <h3>任务类型:</h3>
            <select class="form-control" name="taskType" id="tasktype" style="width: 60%;">               
	            <option>图像分类</option>            
	            <option>拉框标注</option>                    
            </select>
           
            <h3>请载入需要标注的数据集文件夹</h3>         
            <div class="form-group">
                 <div class="panel panel-primary">
                   <div class="panel-heading" align="center">
                     <label style="text-align: center;font-size: 18px;">文 件 上 传</label>
                   </div>
                 <div class="panel-body">
                   <div class="col-sm-12">
                      <input id="input-id" name="fileList" multiple type="file" data-show-caption="true" webkitdirectory>
                     
                   </div>
                 </div>
                </div>
              </div> 
              <p id="tips"></p>  
            <br>
           
              <b><input id="profit" class="styled" type="radio" name="ispublic" value=false checked="checked">赏金任务</b>&nbsp;&nbsp;
              <b><input id="public" class="styled" type="radio" name="ispublic" value=true >非赏金任务</b>
            <p>
            <div id="content">
             <table id="profitArea">         
              <tr >
                <td>等级要求：</td>
                <td><input type="number"  name="levelRank" value="1" disabled required></td>
              </tr>
              <tr >
                <td>需要的工作者数量：</td>
                <td><input type="number" id="needWorkers" name="needWorkers" 
                 width="20%" required /></td>
              </tr>
              <tr >
                <td>赏金单价：</td>
                <td><input type="number" id="unitPrice" name="unitPrice" width="20%"  placeholder="按数据数量计算，如0.1￥/张" value=0 disabled required >￥/张</td>
              </tr>
              <tr >
                <td>预计支出：</td>
                <td><span id="predict_expend">0</span>￥</td>
                <input name="predictExpend" type="hidden" value="" />
              </tr>
              
            </table>
            </div>
            <h3>任务描述：</h3>
            <textarea class="form-control" rows="3" name="description" placeholder="Project Description"></textarea>      
          <br>
        
          <h3>请添加数据标签</h3>
            <p>请在这里添加本数据集中所有可能的标签，以便标注者方便标注</p> 
            <p>建议（对于“图像分类”）:设置一个<span style="color:red;">其他<span>标签，方便筛选出本数据集中超出范畴的异类</p>    
            <button type="button" id="addBt" class="btn btn-primary btn-lg" style="margin:10px 0px;" onclick="addButton()">添加</button>
            <button type="button" id="delBt" class="btn btn-default btn-lg" style="margin:10px 20px;"  onclick="delActivaty()">设置“其他”标签</button>
            <ul id="btList" class="my-ul nav nav-pills nav-stacked " style="padding: 10px 10px 10px; border-radius: 5px;  box-shadow: 0px 0px 10px 0px #747474;">  
            	<input id="delValue" type="hidden" value="其他"/>
            </ul>
            <hr class="hidden-sm hidden-md hidden-lg">
            <br>      
        
          </form>  
      		<div id="pros" class="progress progress-striped active" style="display:none">
				<div class="progress-bar progress-bar-success" role="progressbar"
					 aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"
					 style="width: 0%;">
					<span class="sr-only">100% 完成</span>
				</div>
			</div>
          <div style="text-align:center;"> 
            <button id="submit" class=" btn btn-success" value="0">发布任务</button>
          </div> 
        </div>    
    </div>
       <hr class="" style=" border-top: 1px solid #555555;">
    </div>
    `;
    cont.append(text);
    isEdit();
    changeFn();
    $("#submit").click(function(){
        sumbitData();
    });
    suitPage();
    if(main.isPc){
     
    }
}


function isEdit(){
  const profitArea = $("#profitArea");
  $("#profit").change(function(){
	  $("#content").append(profitArea);
   }); 
  $("#public").change(function(){
    profitArea.remove();
    
  });  
}

function changeFn(){
    $("#needWorkers").onlyNum();
    $("#labelNum").onlyNum();
    $("#needWorkers").change(function(){
      var num = $(this).val();
      $(this).val(num <0?0:$(this).val());
      calculatePross();
    });
    $("#unitPrice").change(function(){
        var num = $(this).val();
        $(this).val(num <0?0:$(this).val());
        calculatePross();
    });
    $("#input-id").change(function(){
        calculatePross();
    });
    $("#tasktype").change(function(){
    	calculatePross();
    });
}


$.fn.onlyNum = function () {
    $(this).keypress(function (event) {
        var eventObj = event || e;
        var keyCode = eventObj.keyCode || eventObj.which;
        if ((keyCode >= 48 && keyCode <= 57))
            return true;
        else
            return false;
    }).focus(function () {
        //禁用输入法
        this.style.imeMode = 'disabled';
    }).bind("paste", function () {
        //获取剪切板的内容
        var clipboard = window.clipboardData.getData("Text");
        if (/^\d+$/.test(clipboard))
            return true;
        else
            return false;
    });
};

