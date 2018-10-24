package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.DeveloperData;
import com.zhu.entity.UserLabel;

/**
 * @description:普通用户dao接口，
 * @function:插入任务数据信息到用户任务表，第一次请求时获取10条未标注数据，
 * 预加载时获取10条未标注数据，获取随机的5条userlabel，获取任务数据条数
 * 	,删除普通用户的数据，更新被采纳的数据到数据库表
 * @create in 2018.08.21
 * by Unow
 * */
public interface UserLabelDao {
	/**
	 * @description:用户加入工作时插入对应的数据信息
	 * @parameters:DeveloperData Type,用户名
	 * */
	void insertData(@Param("username")String username,@Param("developerData")DeveloperData developerData);
	
	/**
	 * @description:用户第一次打开页面获取10条数据
	 * @parameters:任务名，开发者,用户名
	 * @return: List<UserLabel>
	 * */
	List<UserLabel> firstGetDataName(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:用户预加载时获取10条数据
	 * @parameters:任务名，开发者,用户名
	 * @return: List<UserLabel>
	 * */
	List<UserLabel> preGetDataName(@Param("username")String username, @Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:每标记完一批更新到数据库里
	 * @parameters:UserLabel Type
	 * */
	void updateLabel(@Param("userLabel")UserLabel userLabel);
	
	/**
	 * @description:获取随机的5条userlabel
	 * @parameters:用户名，任务名
	 * @return:UserLabel
	 * */
	List<UserLabel> getRandUserLabel(@Param("developer")String developer,@Param("username")String username,@Param("assignment")String assignment);
	
	/**
	 * @description:获取任务数据条数
	 * @parameters:用户名，开发者，任务名
	 * @return:数量数量
	 * */
	int getDataNum(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);

	/**
	 * @description:获取相同数据名下的所有用户标注情况
	 * @parameters:开发者名，任务名，数据名
	 * @return:UserLabel集合
	 * */
	List<UserLabel> getAllUserByDataName(@Param("developer")String developer,@Param("assignment")String assignment,@Param("dataName")String dataName);
	
	/**
	 * @description:删除普通用户的数据
	 * @parameters:开发者名，任务名
	 * */
	void deleteUserData(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:删除特指的普通用户的所有数据
	 * @parameters:开发者名，任务名,用户名
	 * */
	void deleteUserDataByName(@Param("developer")String developer,@Param("assignment")String assignment,@Param("username")String username);

	/**
	 * @description:更新被采纳的数据到数据库表
	 * @parameters:开发者名，任务名，用户名，数据名
	 * */
	void adoptLabel(@Param("developer")String developer,@Param("assignment")String assignment,@Param("username")String username,@Param("dataName")String dataName);
}
