package com.zhu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhu.dao.DeveloperDao;
import com.zhu.entity.Developer;
import com.zhu.service.DeveloperService;
@Service
public class DeveloperServiceImpl implements DeveloperService {
	@Autowired
	private DeveloperDao developerDao;
	public boolean developerLogin(String developer,String password) {
		if(developerDao.matchDeveloper(developer, password)!=null)
			return true;
		else
			return false;
	}

	public boolean developerRegister(String developer,String password,String email) {
		if(developerDao.matchDeveloper(developer, password)==null){
			developerDao.registerDeveloper(new Developer(developer,password,0.0,new SimpleDateFormat("yyyy-MM-dd").format(new Date()),email));
			return true;
		}		
		else{
			return false;
		}
	}

	public Developer getDeveloperMsg(String developer) {		
		return developerDao.queryDeveloper(developer);
	}



}
