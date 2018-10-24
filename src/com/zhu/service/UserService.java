package com.zhu.service;


import javax.servlet.http.HttpServletRequest;

import com.zhu.entity.User;

/**
 * description:普通用户service接口，
 * function:登录，注册，获取用户详情信息
 * create in 2018.08.22
 * by Unow
 * */
public interface UserService {
	/**
	 * description:登录
	 * parameters:HttpServletRequest
	 * return:boolean
	 * */
	boolean userLogin(String username,String password);
	
	/**
	 * description:注册
	 * parameters:HttpServletRequest
	 * return:boolean
	 * */
	boolean userRegister(String username,String password,String email);
	
	/**
	 * description:获取用户信息
	 * parameters:username
	 * return:User
	 * */
	User getUserMsg(String username);
	
	/**
	 * description:修改密码
	 * parameters:request
	 * return:状态码
	 * */
	int changePassword(HttpServletRequest request);
}
