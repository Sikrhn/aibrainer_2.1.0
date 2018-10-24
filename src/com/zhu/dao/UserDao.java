package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.User;

/**
 * @description:普通用户dao接口，
 * @function:查询用户账户，匹配普通用户账号密码，注册普通用户账户，匹配修改密码的账户，修改密码
 * 			更新获得任务的奖励到数据库表，更新提现赏金，
 * @create in 2018.08.20
 * by Unow
 * */
public interface UserDao {
	/**
	 * @description:注册插入数据
	 * @parameters: User Type
	 * */
	void registerUser(@Param("user") User user);
	/**
	 * @description:登录匹配
	 * @parameters:username，password
	 * @return User Type or null
	 * */
	User matchUser(@Param("username")String username,@Param("password")String password);
	/**
	 * @description:查询是否存在该用户
	 * @parameters:username，password
	 * @return: User Type or null
	 * */
	User queryUser(@Param("username")String username);
	
	/**
	 * @description:更新获得任务的奖励到数据库表
	 * @parameters:用户名，获得金额
	 * */
	void getReward(@Param("User")User user);
	
	/**
	 * @description:提现赏金
	 * @parameters:用户名，提现赏金
	 * */
	void exportBalance(@Param("username")String username,@Param("money")double money);
	
	/**
	 * @description: 获取所有开发者的准确率
	 * @return: List<Double>
	 * */
	List<Double> allUserAccuracy(); 
	
	/**
	 * @description: 匹配修改密码的账户
	 * @parameters: 用户名，电子邮箱
	 * @return: User
	 * */
	User findUser(@Param("username")String username,@Param("email")String email);
	
	/**
	 * @description: 修改密码
	 * @parameters: 账号，密码
	 * @return: 无
	 * */
	void updatePassword(@Param("username")String username,@Param("password")String password);
}
