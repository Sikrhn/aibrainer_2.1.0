package com.zhu.service;


import com.zhu.entity.Developer;

/**
 * @description:������service�ӿڣ�
 * @function:��¼��ע�ᣬ��ȡ������������Ϣ
 * @create in 2018.08.22
 * by Unow
 * */
public interface DeveloperService {
	/**
	 * @description:��¼
	 * @parameters:HttpServletRequest
	 * @return:boolean
	 * */
	boolean developerLogin(String developer,String password);
	/**
	 * @description:ע��
	 * @parameters:HttpServletRequest
	 * @return:boolean
	 * */
	boolean developerRegister(String developer,String password,String email);
	/**
	 * @description:��ȡ������������Ϣ
	 * @parameters:��������
	 * @return:Developer
	 * */
	Developer getDeveloperMsg(String developer);
}
