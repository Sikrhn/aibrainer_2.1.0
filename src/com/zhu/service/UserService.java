package com.zhu.service;


import javax.servlet.http.HttpServletRequest;

import com.zhu.entity.User;

/**
 * description:��ͨ�û�service�ӿڣ�
 * function:��¼��ע�ᣬ��ȡ�û�������Ϣ
 * create in 2018.08.22
 * by Unow
 * */
public interface UserService {
	/**
	 * description:��¼
	 * parameters:HttpServletRequest
	 * return:boolean
	 * */
	boolean userLogin(String username,String password);
	
	/**
	 * description:ע��
	 * parameters:HttpServletRequest
	 * return:boolean
	 * */
	boolean userRegister(String username,String password,String email);
	
	/**
	 * description:��ȡ�û���Ϣ
	 * parameters:username
	 * return:User
	 * */
	User getUserMsg(String username);
	
	/**
	 * description:�޸�����
	 * parameters:request
	 * return:״̬��
	 * */
	int changePassword(HttpServletRequest request);
}
