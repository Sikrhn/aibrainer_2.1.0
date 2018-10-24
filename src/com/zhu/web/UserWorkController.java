package com.zhu.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhu.dto.DataBody;
import com.zhu.dto.RectDataTransport;
import com.zhu.entity.PublicData;
import com.zhu.entity.UserLabel;
import com.zhu.service.DeveloperTaskService;
import com.zhu.service.PublicDataService;
import com.zhu.service.RectDataService;
import com.zhu.service.TaskClassesService;
import com.zhu.service.UserLabelService;
import com.zhu.service.UserTaskService;

/**
 * @description:用户工作页面Controller
 * @function:显示工作标记页面，第一次获取数据，预加载获取数据，
 * 			发送处理完毕的数据，获取非赏金任务数据，记录非赏金任务数据的标记
 * @Create in 2018.08.23
 * by Unow
 * */

@Controller
@RequestMapping("/go")
public class UserWorkController {
	@Autowired
	private TaskClassesService tcService;
	@Autowired
	private UserLabelService ulService;
	@Autowired
	private	UserTaskService utService;
	@Autowired
	private PublicDataService pdService;
	@Autowired
	private RectDataService rdService;
	@Autowired
	private DeveloperTaskService dtService;

	@ResponseBody
	@RequestMapping(value="/work",method=RequestMethod.GET)
	public DataBody workDetails(HttpServletRequest request){
		Map<String,Object> map = dtService.workPageData(request);
		if(map!=null)			
			return new DataBody(200,"success",false,true,map);
		else
			return new DataBody(412,"failure",false,false,null);
	}
	
	@ResponseBody
	@RequestMapping(value="/getdata.first",method=RequestMethod.GET)
	public DataBody getData(HttpServletRequest request){
		String taskType = request.getParameter("taskType");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type",taskType);
		if(taskType.equals("拉框标注")){
			map.put("datas", rdService.getRectDataFirst(request));		
		}else if(taskType.equals("图像分类")){		
			map.put("datas",  ulService.firstGetData(request));
		}		
		return new DataBody(200,"success",false,true,map);		
	}
	
	@ResponseBody
	@RequestMapping(value="/getdata.prestrain",method=RequestMethod.GET)
	public DataBody preGetData(HttpServletRequest request){
		String taskType = request.getParameter("taskType");
		Map<String,Object> map = new HashMap<String,Object>();
		if(taskType.equals("拉框标注")){
			map.put("datas", rdService.getRectDataSecond(request));								
		}else if(taskType.equals("图像分类")){
			map.put("datas",  ulService.preGetData(request));
		}
		return new DataBody(200,"success",false,true,map);		
	}
	
	@ResponseBody
	@RequestMapping(value="/senddata.action",method=RequestMethod.POST)
	public  DataBody sendTaggedData(@RequestBody List<UserLabel> dataList){
		ulService.sendFinishData(dataList);
		return new DataBody(200,"success",false,true,null);			
	}
	@ResponseBody
	@RequestMapping(value="/sendpublicrectdata.action",method=RequestMethod.POST)
	public DataBody sendPubliRectLabel(@RequestBody List<RectDataTransport> dataList){		
		rdService.insertMarkerDatas(dataList,null);
		return new DataBody(200,"success",false,true,null);				
	}
	
	@ResponseBody
	@RequestMapping(value="/sendrectdata.action",method=RequestMethod.POST)
	public DataBody sendRectLabelData(@RequestBody List<RectDataTransport> dataList,HttpServletRequest request){
		String username = (String) request.getSession().getAttribute("username");
		if(username!=null){
			rdService.insertMarkerDatas(dataList,username);
		}		
		return new DataBody(200,"success",false,true,null);		
	}
	
	@ResponseBody
	@RequestMapping(value="/sendisover.action",method=RequestMethod.GET)
	public DataBody sendIsOver(HttpServletRequest request){
		String developer = request.getParameter("developer");
		String assignment = request.getParameter("assignment");
		String ispublic = request.getParameter("ispublic");
		String taskType = request.getParameter("taskType");
		if(ispublic!=null&&ispublic.equals("1")){
			dtService.setTaskOver(developer, assignment);
			return new DataBody(200,"success",false,true,null);
		}
		String username = (String) request.getSession().getAttribute("username");		
		if(username!=null){
			if(utService.isover(developer,assignment,username,taskType)){				
				return new DataBody(200,"success",false,true,null);
			}else{
				return new DataBody(405,"failure",false,false,null);
			}
		}else{
			return new DataBody(412,"failure",false,true,null);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getpublicdata",method=RequestMethod.GET)
	public DataBody getPublicData(HttpServletRequest request){		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("datas", pdService.getRandomData(request));
		if(request.getSession().getAttribute("isDeveloper")!=null){
			Boolean isDeveloper = (Boolean) request.getSession().getAttribute("isDeveloper");
			return new DataBody(200,"success",isDeveloper,true,map);
		}
		return new DataBody(200,"success",false,map);		
	}
	
	@ResponseBody
	@RequestMapping(value="/recordlabel",method=RequestMethod.POST)
	public DataBody recordData(@RequestBody List<PublicData> publicDatas){
		pdService.insert(publicDatas);
		return new DataBody(200,"success",false,null);		
	}
}
