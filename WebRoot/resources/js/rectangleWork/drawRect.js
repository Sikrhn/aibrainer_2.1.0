

var canvas=document.getElementById("canvas");
var jqCanves = $("#canvas");
var layer=0;
var scale=1;

img={
	imgLoad:(src)=>{
	    var img = new Image();
		img.src=src;
		img.onload=()=>{
			canvas.style["background-image"]=`url(${img.src})`;
			let width = $("#imgarea").width();
			main.currentWidth = img.width;
			main.currentHeight=img.height;
			if(width<img.width){
				scale=parseFloat(width/img.width).toFixed(2);
				height=img.height*scale						
			}else{
				scale=1;
				width=img.width
				height=img.height
			}
            workObj[main.imgIndex].img.currentScale=scale;		
		    canvas.style.width=width;
			canvas.style.height=height;
			canvas.width=width;
			canvas.height = height;
			
			drawPen();
		}
	}

}

CanvasExt = {

	LayerObj:(type,color,penWidth,name,x,y,w,h)=>{
        return {type: type,
                strokeStyle: color,
                strokeWidth: penWidth,
                name:name,
                fromCenter: false,
                x: x, y: y,
                width: w,
                height: h};
    },
	
    mouseDrawRect:function(penColor,strokeWidth){

        var that=this;
        var color=penColor;
        var penWidth=penWidth;
    	
       

        //canvas �ľ��ο�
        var canvasRect = canvas.getBoundingClientRect();
        //���ο�����Ͻ����
        var canvasLeft=canvasRect.left;
        var canvasTop=canvasRect.top;

        
        // console.log(layerIndex)
        var layerName="layer";
        var x=0;
        var y=0;
		if(!main.operate){
			canvas.onmousedown=null;
			canvas.onmouseup=null;
		    return;
		}
        //����������¼�����ͼ׼��
        canvas.onmousedown=function(e){
        	var layerIndex=layer;
            layerIndex++;
            layer++;
            tempName = layerName+layerIndex;
            x = parseInt(e.clientX-canvasLeft);
            y = parseInt(e.clientY-canvasTop);

            jqCanves.addLayer(that.LayerObj('rectangle',color,penWidth,tempName,x,y,1,1));
            jqCanves.drawLayers();
            //����ƶ��¼�����ͼ
            canvas.onmousemove=function(e){
                width = e.clientX-canvasLeft-x;
                height = e.clientY-canvasTop-y;
                jqCanves.removeLayer(tempName);
                jqCanves.addLayer(that.LayerObj('rectangle',color,penWidth,tempName,x,y,width,height));
                jqCanves.drawLayers();
            }
        };

        canvas.onmouseup=function(e){
            canvas.onmousemove=null;

            width = parseInt(e.clientX-canvasLeft-x);
            height = parseInt(e.clientY-canvasTop-y);

            jqCanves.removeLayer(tempName);
            jqCanves.addLayer(that.LayerObj('rectangle',color,penWidth,tempName,x,y,width,height));
            jqCanves.drawLayers();
           	let obj = operateDOM.setRectObj(layer,tempName,main.currentWidth,main.currentHeight,x,y,width,height);
            
            workObj[main.imgIndex].labels.push(obj);
            operateDOM.addRectDom(obj);
       		}
    },
    touchDrawRect:function(penColor,strokeWidth){
    	
    	var that=this;
        var color=penColor;
        var penWidth=penWidth;
        //canvas �ľ��ο�
        var canvasRect = canvas.getBoundingClientRect();
        //���ο�����Ͻ����
        var canvasLeft=canvasRect.left;
        var canvasTop=canvasRect.top;

        var layerIndex=layer;
        var layerName="layer";
        var x=0;
        var y=0;

        //����������¼�����ͼ׼��
        if(!main.operate){
			canvas.ontouchstart=null;
			canvas.ontouchend=null;
		    return;
		}
        canvas.ontouchstart=(e)=>{

			layerIndex++;
            layer++;
            tempName=layerName+layerIndex;
            x = parseInt(e.touches[0].clientX-canvasLeft);

            y = parseInt(e.touches[0].clientY-canvasTop);
            jqCanves.addLayer(that.LayerObj('rectangle',color,penWidth,tempName,x,y,1,1));
            jqCanves.drawLayers();
            jqCanves.saveCanvas();
            //����ƶ��¼�����ͼ
            canvas.ontouchmove=function(e){
                width =  e.touches[0].clientX-canvasLeft-x;
                height =  e.touches[0].clientY-canvasTop-y;
                jqCanves.removeLayer(tempName);
                jqCanves.addLayer(that.LayerObj('rectangle',color,penWidth,tempName,x,y,width,height));
                jqCanves.drawLayers();
            }
        }
        	
        canvas.ontouchend=function(e){
            canvas.ontouchmove=null;

            width = parseInt(e.changedTouches[0].clientX-canvasLeft-x);
            height =  parseInt(e.changedTouches[0].clientY-canvasTop-y);
            console.log(e)
            jqCanves.removeLayer(tempName);
            jqCanves.addLayer(that.LayerObj('rectangle',color,penWidth,tempName,x,y,width,height));
            jqCanves.saveCanvas();
            let obj = operateDOM.setRectObj(layer,tempName,main.currentWidth,main.currentHeight,x,y,width,height);
            workObj[main.imgIndex].labels.push(obj);
            operateDOM.addRectDom(obj);
       	}
    }   
}
	   