package com.zhu.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhu.dao.RectDataDao;
import com.zhu.dao.UserTaskDao;
import com.zhu.dto.RectDataTransport;
import com.zhu.entity.RectData;
import com.zhu.service.RectDataService;
@Service
public class RectDataServiceImpl implements RectDataService{
	@Autowired
	private RectDataDao rddao;
	@Autowired
	private UserTaskDao utdao;
	public List<String> getRectDataFirst(HttpServletRequest request) {
		String developer = request.getParameter("developer");
		String assignment =request.getParameter("assignment");
		if(developer!=null&&assignment!=null)
			return rddao.getRectDataFirst(developer, assignment);
		else
			return null;
	}

	public List<String> getRectDataSecond(HttpServletRequest request) {
		String developer = request.getParameter("developer");
		String assignment =request.getParameter("assignment");
		if(developer!=null&&assignment!=null)
			return rddao.getRectDataSecond(developer, assignment);
		else
			return null;
	}

	@Transactional
	public void insertMarkerDatas(List<RectDataTransport> rectDatas,String username) {
		String developer = rectDatas.get(0).getRectDatas().get(0).getDeveloper();
		String assignment = rectDatas.get(0).getRectDatas().get(0).getAssignment();
		if(developer!=null&&assignment!=null){
			utdao.updateMarkedNum(username, developer, assignment, rectDatas.size());
			for(RectDataTransport item : rectDatas){					
				rddao.markedDtData(developer,assignment,item.getDataName());
				for(RectData i:item.getRectDatas())
					rddao.insertMarkerData(i,username);
			}
		}
		
	}
	
	
	
	
}
