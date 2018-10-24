package com.zhu.service;


import com.zhu.entity.Developer;

/**
 * @description:开发者service接口，
 * @function:登录，注册，获取开发者详情信息
 * @create in 2018.08.22
 * by Unow
 * */
public interface DeveloperService {
	/**
	 * @description:登录
	 * @parameters:HttpServletRequest
	 * @return:boolean
	 * */
	boolean developerLogin(String developer,String password);
	/**
	 * @description:注册
	 * @parameters:HttpServletRequest
	 * @return:boolean
	 * */
	boolean developerRegister(String developer,String password,String email);
	/**
	 * @description:获取开发者详情信息
	 * @parameters:开发者名
	 * @return:Developer
	 * */
	Developer getDeveloperMsg(String developer);
}
