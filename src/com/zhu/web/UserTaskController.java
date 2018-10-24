package com.zhu.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhu.dto.DataBody;
import com.zhu.entity.User;
import com.zhu.entity.UserTask;
import com.zhu.service.UserLabelService;
import com.zhu.service.UserService;
import com.zhu.service.UserTaskService;

/**
 * @description:�û�����Controller
 * @function:�û�������빤��ʱ���������¼������͸������ĵ�����
 * 			,��ʾ�μ�ͬһ������û��б�,�鿴�û���ɱ�ע���飬�Ƴ���ע��
 * @Create in 2018.08.23
 * by Unow
 * */

@Controller
@RequestMapping("/session")
public class UserTaskController {
	@Autowired
	private UserTaskService utService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserLabelService ulService;
	
	@ResponseBody
	@RequestMapping(value="/jointask.action",method=RequestMethod.GET)
	public DataBody joinTask(HttpServletRequest request){
		int code = utService.joinTask(request);
		if(code == 412){
			return new DataBody(code,"�������ע����������",false,true,null);			
		}else if(code ==416){
			return new DataBody(code,"����δ��ɱ�ע������",false,true,null);		
		}else if(code ==406){
			return new DataBody(code,"���Ѿ��Ǹ�����ı�ע�ߣ�",false,true,null);	
		}else if(code ==200){
			String taskType=request.getParameter("taskType");
			if(taskType.equals("ͼ�����"))
				ulService.copyDeveloperData(request);
			return new DataBody(code,"success",false,true,null);			
		}		
		return new DataBody(code,"failure",false,true,null);	
	}
	
	@ResponseBody
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public DataBody userCenter(HttpServletRequest request){
		String username = (String) request.getSession().getAttribute("username");
		if(username!=null){
			User user = userService.getUserMsg(username);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("taskList",utService.getUserTask(username));
			map.put("user", user);
			return new DataBody(200,"success",false,true,map);
		}else{
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/userlist",method=RequestMethod.GET)
	public DataBody getUserList(HttpServletRequest request){
		String developer = (String) request.getSession().getAttribute("developer");
		String assignment = request.getParameter("assignment");
		List<UserTask> userList = utService.getUserSameTask(developer, assignment);
		boolean isCalculate = true;
		for(UserTask item : userList){
			if(!item.isIsover()){
				isCalculate = false;
				break;
			}				
		}		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("readyCalculate", isCalculate);
		map.put("userList", userList);
		return new DataBody(200,"success",true,true,map);
	}
	
	@ResponseBody
	@RequestMapping(value="/userdetails",method=RequestMethod.GET)
	public DataBody getUserDetails(HttpServletRequest request){
		
		return new DataBody(200,"success",true,true,ulService.getLabelOne(request));
	}
	
	@ResponseBody
	@RequestMapping(value="/removeWorker.action",method=RequestMethod.GET)
	public DataBody removeUser(HttpServletRequest request){
		Boolean isDeveloper = (Boolean) request.getSession().getAttribute("isdeveloper");
		if(isDeveloper!=null&&isDeveloper){
			utService.removeUser(request);
			return new DataBody(200,"success",true,true,null);
		}
		return new DataBody(412,"failure",true,true,null);
		
	} 
}
