package com.zhu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhu.dao.UserDao;
import com.zhu.entity.User;
import com.zhu.service.UserService;
import com.zhu.utils.SmallTools;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	public boolean userLogin(String username,String password) {
		if(userDao.matchUser(username, password)!=null)
			return true;
		else
			return false;
	}

	public boolean userRegister(String username,String password,String email) {
		
		double temp = SmallTools.getTwoDecimal(new Random().nextDouble());
		int temp2 = new Random().nextInt(15);
		double accuracy = (80.0+temp2+temp);
		accuracy = SmallTools.getTwoDecimal(accuracy);
		if(userDao.queryUser(username)==null){
			System.out.println(accuracy);
			userDao.registerUser(new User(username,password,0,0.0,accuracy,new SimpleDateFormat("yyyy-MM-dd").format(new Date()),0,email));
			return true;
		}			
		else{
			return false;
		}
			
	}

	public User getUserMsg(String username) {		
		return userDao.queryUser(username);
	}

	
	public int changePassword(HttpServletRequest request) {
		String isdeveloper = request.getParameter("isdeveloper");
		if(isdeveloper!=null){
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password=request.getParameter("password");
			if(isdeveloper.equals("false")){				
				if(userDao.findUser(username, email)!=null){
					userDao.updatePassword(username,password);
					return 200;
				}
			}else{
				return 404;
			}
		}
		return 412;
		
	}

}
