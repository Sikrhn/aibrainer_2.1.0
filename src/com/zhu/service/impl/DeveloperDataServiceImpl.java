package com.zhu.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zhu.dao.DeveloperDao;
import com.zhu.dao.DeveloperDataDao;
import com.zhu.dao.DeveloperTaskDao;
import com.zhu.dao.RectDataDao;
import com.zhu.dao.UserDao;
import com.zhu.dao.UserLabelDao;
import com.zhu.dao.UserTaskDao;
import com.zhu.dto.UserMsgBody;
import com.zhu.entity.DeveloperData;
import com.zhu.entity.DeveloperTask;
import com.zhu.entity.User;
import com.zhu.entity.UserLabel;
import com.zhu.entity.UserTask;
import com.zhu.service.DeveloperDataService;
import com.zhu.utils.FoldUtils;
import com.zhu.utils.SmallTools;
@Service
public class DeveloperDataServiceImpl implements DeveloperDataService{
	@Autowired
	private DeveloperDataDao dddao;
	@Autowired
	private UserLabelDao uldao;
	@Autowired
	private DeveloperTaskDao dtdao;
	@Autowired
	private UserTaskDao utdao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DeveloperDao developerDao;
	@Autowired
	private RectDataDao rddao;
	@Transactional
	public int addData(String developer, String assignment, List<MultipartFile> fileList,
			String realPath) {
		String dataPath = realPath+File.separator+"data";		
		FoldUtils.createLabelFold(dataPath);	
		int num = fileList.size();
		long size = 0;
		if(num<=2000){
			for(int i=0;i < num;i++){
				MultipartFile file = fileList.get(i);
				size+=file.getSize()/1024/1024;
				if(size>50){
					FoldUtils.delFolder(dataPath);
					dddao.deleteDataByAssignment(developer, assignment);
					return 0;
				}
				String filename = file.getOriginalFilename();			
				File targetfile = new File(dataPath,filename);
				try {
					file.transferTo(targetfile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dddao.insertData(developer, assignment, filename);
			}
			return num;
		}else{
			return 0;
		}
		
		
	}
	
	public List<String> getFiveData(String developer, String assignment) {
		List<DeveloperData> list = dddao.getRandFive(developer, assignment);
		List<String> dataNames = new ArrayList<String>();
		for(DeveloperData msg : list){
			dataNames.add(msg.getDataName());
		}
		return dataNames;
	}
	
	public void deleteData(String developer, String assignment) {
		dddao.deleteDataByAssignment(developer, assignment);		
	}
	
	public Map<String,Object> viewDataLabels(String developer,String assignment,String taskType){
		Map<String,Object> map = new HashMap<String,Object>();		
		if(taskType.equals("图像分类")){
			Map<String,Object> map2 = new HashMap<String,Object>();
			List<DeveloperData> dd = dddao.getDataByNumber(5, developer, assignment);
			map.put("datas", dd);
			for(DeveloperData item : dd){				
				List<UserLabel> list = uldao.getAllUserByDataName(developer, assignment, item.getDataName());
				map2.put(item.getDataName(), list);
			}
			map.put("utlist", map2);
			return map;
		}else if(taskType.equals("拉框标注")){
			map.put("datas",rddao.getRandomBySatistic(developer, assignment));
			return map;
		}
		return null;		
	}
	
	@Transactional
	public void scanFinalLabel(String ordPath,String developer, String assignment) {
		
		List<DeveloperData> list = dddao.getDataName(developer, assignment);
		//进行筛选label
		int dataNum = list.size();
		for(int i = 0;i < dataNum;i++){
			String dataName = list.get(i).getDataName();
			List<UserLabel> tempList =  uldao.getAllUserByDataName(developer, assignment, dataName);
			UserLabel label;
			if(tempList.size()>1)
				label = matchLabel(tempList,new ArrayList<UserLabel>(),0);
			else
				label = tempList.get(0);
			System.out.println(label.getDataLabel());
			dddao.updateLabel(label);
			FoldUtils.moveFile(ordPath, dataName,label.getDataLabel());			
			for(UserLabel ul : tempList){
				if(ul.getDataLabel().equals(label.getDataLabel()))
					uldao.adoptLabel(developer, assignment, ul.getUsername(), dataName);
			}			
		}	
		List<UserTask> userTasks = utdao.getSameUserTask(developer, assignment);
		for(UserTask ut:userTasks){
			finishAccuracyAndReward(ut.getUsername(), ut.getDeveloper(),ut.getAssignment());
		}
		
		double remainder = dtdao.queryMoney(developer, assignment);
		if(remainder>0){
			//剩余赏金转入开发者账户余额
			developerDao.importBalance(developer,remainder);
		}
		dtdao.overTask(developer, assignment);
		if(!new File(ordPath+".zip").exists()){
			FoldUtils.zipDir(false,ordPath);
		}
	}
	
	/**
	 * @description:完成对用户准确率的计算并插入和对用户的评测
	 * @parameters:用户名，开发者名，任务名
	 * */
	private void finishAccuracyAndReward(String username, String developer,
			String assignment) {
		DeveloperTask developerTask = dtdao.getDetailOne(developer, assignment);
		UserMsgBody umb = utdao.getMarkedDetail(username, developer, assignment);
		double markedNum = umb.getMarkedNum();
		double adoptNum = umb.getAdoptNum();
		double money = adoptNum*developerTask.getUnitPrice();
		double accuracy=0.0;
		if(adoptNum>0)
			accuracy = adoptNum/markedNum*100;		
		accuracy=SmallTools.getTwoDecimal(accuracy);
		int exp = developerTask.getExp();
		User user;
		UserTask userTask;
		if(accuracy>60){
			userTask = new UserTask(username,developer,assignment,0,money,true,accuracy,(int)adoptNum);
			user = new User(username,null,1,money,accuracy,null,exp);
			dtdao.payMoney(developer, assignment, money);
		}else{
			userTask = new UserTask(username,developer,assignment,0,0,true,accuracy,(int)adoptNum);
			user = new User(username,null,0,0,accuracy*100,null,exp);
		}
		utdao.insertAccuracyMoney(userTask);
		userDao.getReward(user);
	}
	
	/**
	 * @description: 匹配选择最多的Label
	 * @parameter: 要匹配的UserLabel集合，用于存放相同label的集合(只需传入空集合)，元素下标(只需传入0)
	 * @return: 最终匹配出的Label
	 * */
	private UserLabel matchLabel(List<UserLabel> list,List<UserLabel> packList,int index){
		if(index == list.size()) 
			return packList.get(0);
		if(packList.contains(list.get(index))==true) 
			return matchLabel(list,packList,++index);
		List<UserLabel> newPackList = new ArrayList<UserLabel>();
		newPackList.add(list.get(index));
		for(int i = 0;i < list.size();i++){
			UserLabel item = list.get(i);
			if(packList.contains(item))
				continue;
			if(list.get(index).getDataLabel().equals(list.get(i).getDataLabel()))
				newPackList.add(item);
		}
		if(newPackList.size()>(list.size()/2))
			return newPackList.get(0);		
		return packList.size()>newPackList.size()?matchLabel(list,packList,++index):matchLabel(list,newPackList,++index);
	}
	
}
