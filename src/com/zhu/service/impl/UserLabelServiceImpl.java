package com.zhu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhu.dao.DeveloperDataDao;
import com.zhu.dao.RectDataDao;
import com.zhu.dao.UserLabelDao;
import com.zhu.dao.UserTaskDao;
import com.zhu.entity.DeveloperData;
import com.zhu.entity.UserLabel;
import com.zhu.service.UserLabelService;
@Service
public class UserLabelServiceImpl implements UserLabelService {
	@Autowired
	private UserLabelDao uldao;
	@Autowired
	private DeveloperDataDao dddao;
	@Autowired
	private UserTaskDao utdao;
	@Autowired
	private RectDataDao rddao;
	public void copyDeveloperData(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
		String developer = request.getParameter("developer");
		String assignment = request.getParameter("assignment");
		List<DeveloperData> list = dddao.getDataName(developer, assignment);
		for(DeveloperData item:list){
			uldao.insertData(username, item);
		}
	}

	public List<UserLabel> firstGetData(HttpServletRequest request) {
		String developer = request.getParameter("developer");
		String assignment = request.getParameter("assignment");
		String username = (String) request.getSession().getAttribute("username");
		return uldao.firstGetDataName(username,developer, assignment);
	}

	public List<UserLabel> preGetData(HttpServletRequest request) {
		String developer = request.getParameter("developer");
		String assignment = request.getParameter("assignment");
		String username = (String) request.getSession().getAttribute("username");
		return uldao.preGetDataName(username,developer, assignment);
	}

	public void sendFinishData(List<UserLabel> list) {
		for(UserLabel item:list){ 
			uldao.updateLabel(item);
		}
		UserLabel temp = list.get(0);
		utdao.updateMarkedNum(temp.getUsername(), temp.getDeveloper(), temp.getAssignment(), list.size());
	}

	public List<UserLabel> getRandUserLabel(HttpServletRequest request) {
		String developer = (String) request.getSession().getAttribute("developer");
		String username = request.getParameter("username");
		String assignment = request.getParameter("assignment");
		return uldao.getRandUserLabel(developer,username, assignment);
	}

	public int getDataNum(String username, String developer, String assignment) {
		return uldao.getDataNum(username, developer, assignment);
	}


	public Map<String, List<UserLabel>> getFiveDataAllUserLabel(
			String assignment, String developer, List<String> datas) {
		Map<String,List<UserLabel>> map = new HashMap<String,List<UserLabel>>();
		for(String data : datas){
			List<UserLabel> list = uldao.getAllUserByDataName(developer, assignment, data);
			map.put(data, list);
		}
		return map;
	}

	public void deleteUserData(String developer, String assignment) {
		uldao.deleteUserData(developer, assignment);
	}

	@Override
	public Map<String,Object> getLabelOne(HttpServletRequest request) {
		
		String taskType= request.getParameter("taskType");
		if(taskType!=null){
			String assignment = request.getParameter("assignment");
			String developer = request.getParameter("developer");
			String username = request.getParameter("username");
			Map<String,Object> map = new HashMap<String,Object>();
			if(taskType.equals("图像分类"))	
				map.put("datas", uldao.getRandUserLabel(developer, username, assignment));
			else if(taskType.equals("拉框标注")){
				map.put("datas", rddao.getRandomFiveLabelByUser(username, developer, assignment));
				System.out.println(username+developer+assignment);
			}
				
			return map;
		}
		return null;
	}


}
