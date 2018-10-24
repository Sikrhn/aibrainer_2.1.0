package com.zhu.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhu.dto.AddTaskBody;
import com.zhu.dto.DataBody;
import com.zhu.entity.Developer;
import com.zhu.entity.DeveloperTask;
import com.zhu.entity.UserLabel;
import com.zhu.service.DeveloperDataService;
import com.zhu.service.DeveloperService;
import com.zhu.service.DeveloperTaskService;
import com.zhu.service.PublicDataService;
import com.zhu.service.TaskClassesService;
import com.zhu.service.UserLabelService;
import com.zhu.service.UserTaskService;
import com.zhu.utils.FoldUtils;

/**
 * @description:开发者任务Controller
 * @function:通过页面数来查看任务列表，传送开发者任务详情，
 * 			开发者个人中心,添加开发者任务,查看参加该任务的所有工作者,展示统计界面,一对多的数据展示
 * 			获取某条数据的所有工作者的标注情况，删除任务，手动点击触发系统统计计算数据,
 * 			非赏金任务的下载
 * @Create in 2018.08.23
 * by Unow
 * */

@Controller
@RequestMapping("/session")
public class DevTaskController {
	@Autowired
	private DeveloperDataService ddService;
	@Autowired
	private DeveloperTaskService dtService;
	@Autowired
 	private DeveloperService developerService;
	@Autowired
	private TaskClassesService tcService;
	@Autowired
	private UserLabelService ulService;
	@Autowired
	private UserTaskService utService;
	@Autowired
	private PublicDataService pdService;
	
	@ResponseBody
	@RequestMapping(value="/gettask",method=RequestMethod.GET)
	public DataBody getTask(HttpServletRequest request){
		Boolean isDeveloper = (Boolean) request.getSession().getAttribute("isdeveloper");
		Map<String,Object> map = new HashMap<String,Object>();
		String param = request.getParameter("page");
		if(isDeveloper!=null){
			int page;
			try{
				page = Integer.parseInt(param);
			}catch(NumberFormatException e){
				return new DataBody(403,"failure",isDeveloper,true,null);
			}			
			List<DeveloperTask>	list = dtService.getAllTask(page, 10);
			map.put("taskList", list);
			if(page == 1){
				int sumPage = dtService.getTaskNum();//修改
				sumPage = (int) Math.ceil(sumPage/10.0);
				map.put("sumPage", sumPage);
			}
			return new DataBody(200,"success",isDeveloper,true,map);		
		}else{
			List<DeveloperTask>	list = dtService.getRandTask();
			map.put("taskList", list);
			return new DataBody(200,"success",false,map);	
		}
	}
	@ResponseBody
	@RequestMapping(value="/taskdetails",method=RequestMethod.GET)
	public DataBody getTaskdetails(HttpServletRequest request){
		Boolean isDeveloper = (Boolean) request.getSession().getAttribute("isdeveloper");
		String developer = request.getParameter("developer");
		String assignment = request.getParameter("assignment");
		DeveloperTask developerTask = dtService.getDetailOne(developer,assignment);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("task", developerTask);
		return new DataBody(200,"success",isDeveloper,true,map);
	}
	
	@ResponseBody
	@RequestMapping(value="/developer",method=RequestMethod.GET)
	public DataBody getDeveloper(HttpSession session){
		String developerName = (String) session.getAttribute("developer");
		if(developerName!=null){
			Developer developer = developerService.getDeveloperMsg(developerName);			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("developer", developer);		
			map.put("taskList", dtService.getTaskNameByDeveloper(developerName));
			return new DataBody(200,"success",true,true,map);	
		}else{
			return new DataBody(405,"failure",false,null);
		}
	}
		
	
	@ResponseBody
	@RequestMapping(value="/addtask",method=RequestMethod.POST)
	public DataBody addTask(@ModelAttribute AddTaskBody addTaskBody,@RequestParam("fileList")List<MultipartFile> fileList,HttpServletRequest request){
		String developer = (String) request.getSession().getAttribute("developer");
		System.out.println(developer+","+addTaskBody);
		int judge = dtService.enoughAdd(developer,addTaskBody.getPredictExpend());
		if(judge==0)
			return new DataBody(405,"添加失败，用户已拥有的任务总数超过5",true,true,null);	
		else if(judge==-1)
			return new DataBody(405,"添加失败，余额不足",true,true,null);
		
		String[] labels = request.getParameterValues("labels");
		addTaskBody.setPublic(request.getParameter("ispublic").equals("true"));
		String assignment = dtService.isExist(developer, addTaskBody.getAssignment(), 1);
		addTaskBody.setAssignment(assignment);
		
		if(fileList!=null&&labels!=null&&assignment!=null){		
			String realPath =  request.getServletContext().getRealPath("/WEB-INF/upload");				
			String taskPath = FoldUtils.createLabelFoldOneWay(developer, assignment, realPath);			
			tcService.addLabels(labels, developer, assignment,taskPath,addTaskBody.getTaskType());			
			int dataNum = ddService.addData(developer, assignment, fileList, taskPath);	
			
			if(dataNum==0)
				return new DataBody(405,"添加失败，文件数量不得超过2000和文件大小不得超过50MB",true,true,null);
			
			dtService.addDeveloperTask(addTaskBody,dataNum,developer);
			return new DataBody(200,"success",true,true,null);
		}		
		return new DataBody(405,"failure",true,true,null);
	}

	@ResponseBody
	@RequestMapping(value="/datadetails",method=RequestMethod.GET)
	public  DataBody getDataDetails(HttpServletRequest request){
		String developer = (String) request.getSession().getAttribute("developer");
		String assignment = request.getParameter("assignment");
		List<String> datas = ddService.getFiveData(developer, assignment);
		Map<String,List<UserLabel>> allMap = ulService.getFiveDataAllUserLabel(assignment, developer, datas);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dataDetails", allMap);
		return new DataBody(200,"success",true,true,map);
	}
	
	@ResponseBody
	@RequestMapping(value="/deletetask.action",method=RequestMethod.GET)
	public DataBody deleteTask(HttpServletRequest request){
		String realPath =  request.getServletContext().getRealPath("/WEB-INF/upload");					
		String developer = (String) request.getSession().getAttribute("developer");
		String assignment = request.getParameter("assignment");
		String taskType = request.getParameter("taskType");
		realPath = realPath+File.separator+developer+File.separator+assignment;		
		boolean isPublic = request.getParameter("ispublic").equals("true")?true:false;		
		if(developer!=null&&(Boolean)request.getSession().getAttribute("isdeveloper")){
			if(isPublic){
				dtService.deleteTask(developer, assignment, isPublic,taskType, realPath);
				return new DataBody(200,"success",true,true,null);
			}else{
				boolean isSuccess = dtService.deleteTask(developer, assignment, isPublic,taskType, realPath);
				if(isSuccess)
					return new DataBody(200,"success",true,true,null);
				else
					return new DataBody(412,"该任务已有标注者加入工作！",true,true,null);				
			}
		}
		return new DataBody(412,"failure",false,false,null);			
	}
	
	@ResponseBody
	@RequestMapping(value="/calculate",method=RequestMethod.GET)
	public DataBody calculate(HttpServletRequest request){
		String realPath =  request.getServletContext().getRealPath("/WEB-INF/upload");			
		String assignment = request.getParameter("assignment");
		String developer = (String) request.getSession().getAttribute("developer");
		realPath = realPath+File.separator+developer+File.separator+assignment;
		if(utService.isAllIsOver(developer, assignment)){
			String taskType=request.getParameter("taskType");
			if(taskType.equals("图像分类")){
				ddService.scanFinalLabel(realPath,developer, assignment);
				return new DataBody(200,"success",true,true,null);
			}else if(taskType.equals("拉框标注")){
				dtService.caculateData(developer, assignment, taskType, realPath,false);
				return new DataBody(200,"success",true,true,null);
			}			
		}			
		return new DataBody(405,"failure",true,true,null);		
	}
	
	@ResponseBody
	@RequestMapping(value="/satistic")
	public DataBody showSatistic(HttpServletRequest request){
		String developer = request.getParameter("developer");
		String assignment = request.getParameter("assignment");		
		Map<String,Object> map = dtService.getSatisiticData(developer, assignment);
		if(map!=null)
			return new DataBody(200,"success",true,true,map);
		else
			return new DataBody(412,"failure",true,true,null);
				
	}
	
	@ResponseBody
	@RequestMapping(value="/viewlabels")
	public DataBody showOneForMore(HttpServletRequest request){
		String developer = request.getParameter("developer");
		String assignment = request.getParameter("assignment");
		String taskType = request.getParameter("taskType");		
		return new DataBody(200,"success",true,true,ddService.viewDataLabels(developer, assignment, taskType));		
	}
//	
//	@ResponseBody
//	@RequestMapping(value="/viewpart")
//	public DataBody showOneUserLabel(HttpServletRequest request){
//		return new DataBody(200,"success",true,true,ulService.getLabelOne(request));		
//	}
	
	
	@ResponseBody
	@RequestMapping(value="/download.action")
	public ResponseEntity<byte[]> downloadData(HttpServletRequest request) throws IOException{		
		String developer = (String) request.getSession().getAttribute("developer");	
		if(developer!=null){			
			String realPath =  request.getServletContext().getRealPath("/WEB-INF/upload");	
			String assignment = request.getParameter("assignment");
			String taskType = request.getParameter("taskType");	
			String isPublic = request.getParameter("isPublic");
			realPath = realPath+File.separator+developer+File.separator+assignment;
			if(isPublic.equals("1")){
				if(taskType.equals("图像分类")){
					if(dtService.setTaskOver(developer, assignment)){
						pdService.classifyLabel(developer, assignment, realPath);
						if(!new File(realPath+".zip").exists()){
							FoldUtils.zipDir(true,realPath);
						}	
					}	
				}else if(taskType.equals("拉框标注")){
					dtService.caculateData(developer, assignment, taskType,realPath,true);
					realPath = realPath +File.separator+"result";
				}
			}else{
				if(dtService.getDetailOne(developer, assignment).isIsover()){
					if(taskType.equals("拉框标注"))				
						realPath = realPath +File.separator+"result";
				}
				else{
					return null;
				}
			}
			
									
			HttpHeaders headers = new HttpHeaders(); 
			File file = new File(realPath+".zip");
			if(!file.exists())
				FoldUtils.zipDir(false, realPath);
			String filename = new String(file.getName().getBytes("utf-8"),"iso8859-1");
			headers.setContentDispositionFormData("attachment",filename);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);		
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.OK);
		}		
		return new ResponseEntity<byte[]>(HttpStatus.NOT_ACCEPTABLE);		
	}
	
}
