package com.zhu.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhu.dto.DataBody;
import com.zhu.dto.HomeDataBody;
import com.zhu.dto.UserDeveBody;
import com.zhu.entity.DeveloperTask;
import com.zhu.service.DeveloperService;
import com.zhu.service.DeveloperTaskService;
import com.zhu.service.UserService;

/**
 * description:登录主页Controller
 * function:注册，登录，查看公共的任务列表,加载首页时返回的数据,修改密码或找回密码
 * Create in 2018.08.23
 * by Unow
 * */

@Controller
@RequestMapping("")
public class LogAndRegController {
	@Autowired
	private UserService userService;
	@Autowired
	private DeveloperService developerService;
	@Autowired
	private DeveloperTaskService dtService;
	
	@ResponseBody
	@RequestMapping(value="/login")
	public DataBody Login(@RequestBody UserDeveBody udBody,HttpSession session){
		boolean isdeveloper = udBody.isIsdeveloper();
		String name = udBody.getUsername();
		String password = udBody.getPassword();
		System.out.println(name+","+password);
		System.out.println(udBody.isIsdeveloper());
		if(isdeveloper){
			if(developerService.developerLogin(name, password)){
				session.setAttribute("isdeveloper", isdeveloper);
				session.setAttribute("developer", name);
				return new DataBody(200,"success",true,true,null);
			}
		}else{
			if(userService.userLogin(name, password)){
				session.setAttribute("isdeveloper", isdeveloper);
				session.setAttribute("username", name);
				return new DataBody(200,"success",false,true,null);
			}
		}
		return new DataBody(403,"账号或密码错误！",false,null);
	}
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public DataBody Register(@RequestBody UserDeveBody udBody){
		boolean isdeveloper = udBody.isIsdeveloper();
		String name = udBody.getUsername();
		String password = udBody.getPassword();
		String email = udBody.getEmail();
		if(isdeveloper){
			if(developerService.getDeveloperMsg(name)==null){
				String mob = udBody.getMob();
				String identity = udBody.getIdentity();
				developerService.developerRegister(name, password,email,identity,mob);
				return new DataBody(200,"success",false,null);
			}	
			return new DataBody(412,"账号已存在",false,null);
		}else{
			if(userService.getUserMsg(name)==null){
				userService.userRegister(name, password,email);
				return new DataBody(200,"success",false,null);
			}
		}
		return new DataBody(403,"账号已存在",false,null);
	}
	
	@ResponseBody
	@RequestMapping("/logout")
	public DataBody Logout(HttpSession session){
		Boolean flag = (Boolean) session.getAttribute("isdeveloper");
		if(flag !=null){
			if(flag){
				session.removeAttribute("developer");
				session.removeAttribute("isdeveloper");
			}else{
				session.removeAttribute("username");
				session.removeAttribute("isdeveloper");
			}
		}
		
		return new DataBody(200,"success",false,null);
	}
	
	@ResponseBody
	@RequestMapping(value="/taskview",method=RequestMethod.GET)
	public DataBody taskView(HttpServletRequest request){
		List<DeveloperTask> list;
		Map<String,Object> map = new HashMap<String,Object>();
		Boolean isdeveloper =(Boolean) request.getSession().getAttribute("isdeveloper");
		if(isdeveloper!=null){		
			list = dtService.getAllTask(1, 10);		
			map.put("taskList", list);
			return new DataBody(200,"success",isdeveloper,true,map);	
		}else{
			list = dtService.getRandTask();
			map.put("taskList", list);
			return new DataBody(200,"success",false,map);	
		}				
	}
	
	@ResponseBody
	@RequestMapping(value="/homeData",method=RequestMethod.GET)
	public DataBody getHome(HttpSession session,HttpServletRequest request){

		HomeDataBody hdb = dtService.getHomeData();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("homeData", hdb);
		if(session.getAttribute("isdeveloper")!=null)	{	
			boolean isdeveloper;			
			if(isdeveloper = (Boolean) session.getAttribute("isdeveloper")){
				String developer = (String) session.getAttribute("developer");
				map.put("developer",developerService.getDeveloperMsg(developer));
				return new DataBody(200,"success",isdeveloper,true,map);
			}
			else{
				String username = (String) session.getAttribute("username");
				map.put("user", userService.getUserMsg(username));
				return new DataBody(200,"success",isdeveloper,true,map);
			}
		}else{
			return new DataBody(202,"success",false,map);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/experience",method=RequestMethod.GET)
	public DataBody experience(HttpServletRequest request){
		if(request.getSession().getAttribute("isdeveloper")!=null){
			return new DataBody(200,"success",false,true,dtService.exprience("admin", "test_1"));
		}else{
			return new DataBody(200,"success",false,dtService.exprience("admin", "test_1"));	
		}
				
	}
	@ResponseBody
	@RequestMapping(value="/changePassword.action",method=RequestMethod.POST)
	public DataBody changePassword(HttpServletRequest request){
		if(request.getSession().getAttribute("isdeveloper")!=null){
			boolean isdeveloper = (Boolean)request.getSession().getAttribute("isdeveloper");
			return new DataBody(userService.changePassword(request),"",isdeveloper,true,null);				
		}			
		else
			return new DataBody(userService.changePassword(request),"success",false,null);	
		
	}
}
