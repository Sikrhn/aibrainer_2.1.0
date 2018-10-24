package com.zhu.service.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhu.dao.DeveloperDataDao;
import com.zhu.dao.PublicDataDao;
import com.zhu.entity.DeveloperData;
import com.zhu.entity.PublicData;
import com.zhu.service.PublicDataService;
import com.zhu.utils.FoldUtils;

@Service
public class PublicDataServiceImpl implements PublicDataService{
	@Autowired
	private DeveloperDataDao dddao;
	@Autowired
	private PublicDataDao pddao;

	 
	public void insert(List<PublicData> publicDatas) {
		for(PublicData pd:publicDatas){
			pddao.insertData(pd);
		}		
	}

	 
	public List<DeveloperData> getRandomData(HttpServletRequest request) {
		String developer = request.getParameter("developer");
		String assignment = request.getParameter("assignment");
		int sum = dddao.getDataName(developer, assignment).size();
		int labelSum= pddao.getMarkedNumber(assignment, developer);
		if(labelSum>=sum&&labelSum<=sum*5){

			return pddao.getRandFive(5,assignment, developer);
			
		}else if(labelSum<sum){

			return pddao.getRandFive(1,assignment, developer);
		}else{
			return dddao.getRandFive(developer, assignment);
		}
		
	}
	
	public void classifyLabel(String developer,String assignment,String realPath){
		List<PublicData> list = pddao.getMarkedLabel(assignment, developer);	
		if(new File(realPath).exists()){
			for(PublicData item:list){
				FoldUtils.moveFile(realPath, item.getDataName(), item.getDataLabel());
			}
		}
	}
}
