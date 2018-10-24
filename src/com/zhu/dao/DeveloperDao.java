package com.zhu.dao;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.Developer;

/**
 * @description:开发者dao接口，
 * @function:查询开发者账号，匹配开发者账号密码，注册开发者账户，查询开发者用户数量
 * 			充值，支出
 * @create in 2018.08.20
 * by Unow
 * */
public interface DeveloperDao {
	/**
	 * @description:查询是否已存在该开发者
	 * @parameters:开发者用户名
	 * @return:Developer or null
	 * */
	Developer queryDeveloper(@Param("developer")String developer);
	/**
	 * @description:匹配输入账号密码是否正确
	 * @parameters:开发者用户名
	 * @return:Developer or null
	 * */
	Developer matchDeveloper(@Param("developer")String developer,@Param("password")String password);
	/**
	 * @description:注册账户
	 * @parameters:Developer Type
	 * */
	void registerDeveloper(@Param("developer")Developer developer);
	
	/**
	 * @description:支出金额
	 * @parameters:开发者名，支出金额
	 * */
	void exportBalance(@Param("developer")String developer,@Param("money")double money);
	
	/**
	 * @description:充值
	 * @parameters:开发者名，充值金额
	 * */
	void importBalance(@Param("developer")String developer,@Param("money")double money);
	
	/**
	 * @description:查询开发者用户数量
	 * @return: 开发者数量
	 * */
	int developerNum(); 
	
	
}
