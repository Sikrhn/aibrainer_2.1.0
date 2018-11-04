package com.zhu.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhu.dao.DeveloperDao;
import com.zhu.dao.DeveloperDataDao;
import com.zhu.dao.DeveloperTaskDao;
import com.zhu.dao.PublicDataDao;
import com.zhu.dao.RectDataDao;
import com.zhu.dao.TaskClassesDao;
import com.zhu.dao.UserDao;
import com.zhu.dao.UserLabelDao;
import com.zhu.dao.UserTaskDao;
import com.zhu.dto.AddTaskBody;
import com.zhu.dto.HomeDataBody;
import com.zhu.dto.TaskBody;
import com.zhu.dto.UserMsgBody;
import com.zhu.entity.DeveloperTask;
import com.zhu.entity.RectData;
import com.zhu.entity.User;
import com.zhu.entity.UserTask;
import com.zhu.service.DeveloperTaskService;
import com.zhu.utils.FoldUtils;
import com.zhu.utils.SmallTools;
@Service
public class DeveloperTaskServiceImpl implements DeveloperTaskService {
	@Autowired
	private UserDao userdao;
	@Autowired
	private UserTaskDao utdao;
	@Autowired
	private DeveloperTaskDao dtdao;
	@Autowired
	private DeveloperDao developerDao;
	@Autowired
	private PublicDataDao pddao;
	@Autowired
	private DeveloperDataDao dddao;
	@Autowired
	private TaskClassesDao tcdao;
	@Autowired
	private UserLabelDao uldao;
	@Autowired
	private RectDataDao rddao;
	
	public List<DeveloperTask> getAllTask(int page, int rows) {
		int start = (page-1)*rows;
		List<DeveloperTask> list = dtdao.getAllTask(start, rows);
		return list;
	}

	public int getTaskNum() {		
		return dtdao.getTaskNum();
	}

	public List<DeveloperTask> getRandTask() {		
		return dtdao.getPublicTask();
	}

	public DeveloperTask getDetailOne(String developer,String assignment) {
		DeveloperTask developerTask = dtdao.getDetailOne(developer, assignment);
		return developerTask;
	}

	public void addWorkers(HttpServletRequest request) {
		String developer = request.getParameter("developer");
		String assignment = request.getParameter("assignment");
		dtdao.addWokerNum(developer, assignment);
	}

	public List<DeveloperTask> getSelfTasks(String developer) {		
		return dtdao.getSelfTask(developer);
	}
	
	@Transactional
	public void addDeveloperTask(AddTaskBody addTaskBody,int dataNum,String developer) {

		if(addTaskBody.isPublic()){
			DeveloperTask dt = new DeveloperTask();
			dt.setAssignment(addTaskBody.getAssignment());
			dt.setTaskType(addTaskBody.getTaskType());
			dt.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			dt.setDescription(addTaskBody.getDescription());
			dt.setDeveloper(developer);
			dt.setIsover(false);
			dt.setSum(dataNum);
			dt.setIspublic(addTaskBody.isPublic());
			dtdao.insertTask(dt);
		}else{
			int exp = dataNum/10;
			DeveloperTask dt = new DeveloperTask(developer, addTaskBody.getAssignment(),addTaskBody.getTaskType() ,addTaskBody.getNeedWorkers(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
					0, addTaskBody.getLevelRank(), dataNum, addTaskBody.getDescription(), false, addTaskBody.getUnitPrice(), exp, false,addTaskBody.getPredictExpend());
			developerDao.exportBalance(developer, addTaskBody.getPredictExpend());
			dtdao.insertTask(dt);
		}
	}
	@Transactional
	public boolean deleteTask(String developer, String assignment,boolean isPublic,String taskType,String sourceFilePath) {
		FoldUtils.delFolder(sourceFilePath);
		if(!isPublic){	
			DeveloperTask dt = dtdao.getDetailOne(developer, assignment);			
			if(!dt.isIsover()){		
				if(dt.getWorkers()>0)
					return false;	
				developerDao.importBalance(developer, dt.getPredictExpend());				
			}else{
				File file = new File(sourceFilePath+".zip");
				if(!file.exists()){
					FoldUtils.zipDir(true,sourceFilePath);					
				}
//				file.renameTo(new File("/home/unow/aibrainer"));
			}
			if(taskType.equals("图像分类")) 			
				uldao.deleteUserData(developer, assignment);
			else if(taskType.equals("拉框标注"))
				rddao.deleteAllByAssignment(developer,assignment);	
			utdao.deleteUserTask(assignment, developer);
			
		}else{
			FoldUtils.delFolder(sourceFilePath+".zip");
			if(taskType.equals("图像分类"))			
				pddao.deleteDataByAssignment(developer, assignment);
			else if(taskType.equals("拉框标注"))
				rddao.deleteDataByUsername(null, developer, assignment);
		}
		dtdao.deleteTaskByAssignment(developer, assignment);
		dddao.deleteDataByAssignment(developer, assignment);		
		tcdao.deleteLabels(developer, assignment);

		FoldUtils.delFolder(sourceFilePath);						
		System.out.println(developer+","+"delete task"+assignment);				
		return true;
	}

	 
	public int enoughAdd(String developer,double money) {
		double balance = developerDao.queryDeveloper(developer).getBalance();
		if(balance<money)
			return -1;
		if(dtdao.getDeveloperTaskNum(developer)>=5)
			return 0;
		else
			return 1;
	}

	
	public void removeWorkers() {
		// TODO Auto-generated method stub
		
	}


	public Map<String,Object> getTaskNameByDeveloper(String developer) {
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<TaskBody> taskList =  dtdao.getTaskSimple(developer);	
		for(TaskBody task : taskList){
			Map<String,Object> map1 = new HashMap<String,Object>(); 
			map1.put("task", task);	
			if(task.isIspublic()){
				if(task.getTaskType().equals("图像分类"))
					map1.put("markedNum", pddao.getMarkedNumber(task.getAssignment(), developer));
				else if(task.getTaskType().equals("拉框标注"))
					map1.put("markedNum",dddao.getMarkedNum(developer, task.getAssignment()));
			}
			else
				map1.put("userList", utdao.getUserSimpleByTask(task.getAssignment(), developer));
			map.put(task.getAssignment(),map1 );
		}
		return map;
	}

	 
	public boolean setTaskOver(String developer, String assignment) {
		if(dtdao.taskIsOver(developer, assignment)){
			return false;
		}else{
			dtdao.overTask(developer, assignment);
			return true;
		}		
	}
	
	public String isExist(String developer ,String assignment,int i){
		if(dtdao.queryTaskExist(developer, assignment)!=null){
			if(i>1)
				assignment = assignment.substring(0, assignment.length()-3) +"("+i+")";
			else
				assignment = assignment +"("+i+")";				
			return isExist(developer, assignment,++i);			
		}
		return assignment;
	}
	
	public HomeDataBody getHomeData(){
		List<Double> list = userdao.allUserAccuracy();
		int userNum = list.size();
		int developerNum = developerDao.developerNum();
		int taskNum = dtdao.getTaskNum();
		double sumAcc = 0;
		double avgAcc = 0;
		if(userNum!=0){
			for(int i=0;i<userNum;i++){
				if(list.get(i)!=null)
					sumAcc+=list.get(i);
			}
			avgAcc = SmallTools.getTwoDecimal(sumAcc/userNum);
		}else{
			avgAcc=80.88;
		}		
		
		return new HomeDataBody(userNum,developerNum,avgAcc,taskNum);
	}
	
	public Map<String,Object> exprience(String developer,String assignment){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("datas",dddao.getExprience(developer, assignment));
		map.put("labels",tcdao.getLabel(developer, assignment));
		return map;
	}
	
	@Transactional
	public void caculateData(String developer,String assignment,String taskType,String path,boolean isPublic){
		dtdao.overTask(developer, assignment);
		if(taskType.equals("拉框标注")){
			List<RectData> rdlist = rddao.getLabelList(developer, assignment);
			if(!isPublic){
				DeveloperTask dt =  dtdao.getDetailOne(developer, assignment);
				List<UserTask> utlist = utdao.getSameUserTask(developer, assignment);
				for(int i = 0;i<utlist.size();i++){
					int marked=utlist.get(i).getMarkedNum();
					double taskmoney =  marked*dt.getUnitPrice();
					utlist.get(i).setAccuracy(100.0);
					utlist.get(i).setAdoptNum(marked);
					utlist.get(i).setTaskMoney(taskmoney);
					utdao.insertAccuracyMoney(utlist.get(i));
					dtdao.payMoney(developer, assignment, taskmoney);
					userdao.getReward(new User(utlist.get(i).getUsername(),"",1,taskmoney,96.8,"",marked/10));
				}
			}
			SmallTools.exportCsv(path, assignment,rdlist);
		}
	}
	
	public Map<String,Object> getSatisiticData(String developer,String assignment){
		DeveloperTask dt = dtdao.getDetailOne(developer, assignment);
		if(!dt.isIsover())
			return null;
		List<UserTask> utList = utdao.getSameUserTask(developer, assignment);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("assignment", assignment);
		map.put("developer", developer);
		map.put("predictExpend",dt.getPredictExpend());
		map.put("utList",utList);
		return map;
	}
	
	public Map<String,Object> workPageData(HttpServletRequest request){
		String username = (String) request.getSession().getAttribute("username");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean ispublic = request.getParameter("ispublic").equals("true");
		
		if(username!=null || ispublic){
			String taskType= request.getParameter("taskType");
			String developer = request.getParameter("developer");
			String assignment = request.getParameter("assignment");	
			System.out.println(taskType+developer+assignment);
			List<String> labelList = tcdao.getLabel(developer, assignment);	
			map.put("labelList", labelList);
			UserMsgBody umb = utdao.getMarkedDetail(username, developer, assignment);
			if(taskType.equals("图像分类")){		
				if(umb!=null){
					UserTask userTask = utdao.getWorkingTask(username, developer, assignment);								
					map.put("userTask", userTask);
					return map;
				}
				
			}else if(taskType.equals("拉框标注")){
				if(umb!=null){
					map.put("marked", dddao.getMarkedNum(developer, assignment));
					map.put("task", dtdao.getDetailOne(developer, assignment));
					if(ispublic)
						return map;
					map.put("usermarked", utdao.getWorkingTask(username, developer, assignment).getMarkedNum());
					
					return map;
				}
			}
			return null;
		}else{
			return null;
		}
		
	}
}
