package com.zhu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhu.dao.DeveloperDataDao;
import com.zhu.dao.DeveloperTaskDao;
import com.zhu.dao.RectDataDao;
import com.zhu.dao.UserLabelDao;
import com.zhu.dao.UserTaskDao;
import com.zhu.dto.TaskBody;
import com.zhu.dto.UserSimpleBody;
import com.zhu.entity.RectData;
import com.zhu.entity.UserLabel;
import com.zhu.entity.UserTask;
import com.zhu.service.UserTaskService;
@Service
public class UserTaskServiceImpl implements UserTaskService{
	@Autowired
	private DeveloperTaskDao dtdao;
	@Autowired
	private UserTaskDao utdao;
	@Autowired
	private UserLabelDao uldao;
	@Autowired
	private RectDataDao rddao;
	@Autowired
	private DeveloperDataDao dddao; 
	
	public int joinTask(HttpServletRequest request) {
		String username =  (String) request.getSession().getAttribute("username");
		if(username != null){
			String developer = request.getParameter("developer");
			String assignment = request.getParameter("assignment");
			System.out.println("任务名："+assignment+"，开发者："+developer+"，用户："+username);
			String dataSum = request.getParameter("dataSum");
			String taskType=request.getParameter("taskType");
			if(utdao.getWorkingTask(username, developer, assignment)!=null)
				return 406;
			if(dtdao.isNeedWorker(developer, assignment)==null)
				return 412;
			List<UserTask> list = utdao.getUserTask(username);
			if(list.size()>0){
				for(UserTask ut:list){
					if(!ut.isIsover())
						return 416;
				}
			}
			UserTask userTask = new UserTask(username,developer,assignment,0,Integer.parseInt(dataSum),0,false,0,0,taskType);
			utdao.insertUserTask(userTask);
			dtdao.addWokerNum(developer, assignment);
			return 200;
		}else{
			return 401;
		}
		
	}
	
	public Map<String,Object> getUserTask(String username) {

		Map<String,Object> map = new HashMap<String,Object>(); 
		List<TaskBody> taskList = utdao.getTaskSimple(username);
		for(TaskBody task : taskList){
			Map<String,Object> map1 = new HashMap<String,Object>(); 
			map1.put("task", task);
			map1.put("userList", utdao.getUserSimpleByTask(task.getAssignment(), task.getDeveloper()));
			map.put(task.getAssignment(),map1 );
		}
		return map;
	}

	public List<UserTask> getUserSameTask(String developer, String assignment) {		
		return utdao.getSameUserTask(developer, assignment);
	}

	public UserTask getWorkingTask(String username, String developer,
			String assignment) {
		return utdao.getWorkingTask(username, developer, assignment);
	}

	public boolean isAllIsOver(String developer, String assignment) {
		List<Boolean> list = utdao.getAllUserIsOver(developer, assignment);
		for(boolean flag : list){
			if(!flag)
				return false;
		}
		return true;
	}
	private boolean allowOver(String username,String developer,String assignment){
		UserTask ut = utdao.getWorkingTask(username, developer, assignment);
		if(ut.getMarkedNum()+5>=ut.getDataSum()||ut.getMarkedNum()+10>=ut.getDataSum())
			return true;
		else
			return false;
	}
	@Transactional
	public boolean isover(String developer,String assignment,String username,String taskType) {
		if(allowOver(username,developer,assignment)){			
			if(taskType==null||taskType.equals("图像分类")){
				utdao.updateIsOver(username, developer, assignment);
				return true;
			}
			else if(taskType.equals("拉框标注")){
				List<UserTask> list = utdao.getSameUserTask(developer, assignment);
				for(UserTask ut : list){
					utdao.updateIsOver(ut.getUsername(),developer, assignment);
				}
				dtdao.updateWorkerNum(developer, assignment, list.size());
			}
			return true;
		}else{
			return false;
		}
		
	}

	public List<UserSimpleBody> getSimpleUserSameTask(String developer,
			String assignment) {
		return utdao.getUserSimpleByTask(assignment, developer);
	}
	@Transactional
	public void removeUser(HttpServletRequest request) {
		String developer = (String) request.getSession().getAttribute("developer");
		String assignment = request.getParameter("assignment");
		String username = request.getParameter("username");
		String taskType = request.getParameter("taskType");		
		dtdao.reduceWokerNum(developer, assignment);
		if(taskType.equals("图像分类")){			
			uldao.deleteUserDataByName(developer, assignment, username);
		}else if(taskType.equals("拉框标注")){
			dtdao.updateWorkerNum(developer, assignment, 5);
			List<RectData> rdlist = rddao.getLabelList(developer, assignment);
			for(RectData i : rdlist){
				dddao.updateLabel(new UserLabel("",developer,assignment,i.getDataName(),null));
			}
			if(utdao.getAllUserIsOver(developer,assignment).get(0))
				utdao.reoverUserTask(developer, assignment);
			rddao.deleteDataByUsername(username, developer, assignment);			
		}
		utdao.deleteUserTaskByName(username, developer, assignment);
		
	}




}
