package com.zhu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhu.entity.UserLabel;

/**
 * @description:普通用户任务数据详情service接口，
 * @function:添加任务数据,进入工作页面获取任务数据，
 * 			预加载任务数据，更新标记完毕的数据到数据库表,
 * 			开发者随机查看用户标注情况,获取5条数据的所有工作者的标注情况
 * 			，删除用户拷贝的数据，获取5条标注者标注的label
 * @create in 2018.08.22
 * by Unow
 * */
public interface UserLabelService {
	/**
	 * @description:当普通用户加入任务后，获取其数据源添加入自己的数据库表
	 * @parameters:HttpServletRequest request
	 * */
	void copyDeveloperData(HttpServletRequest request);
	
	/**
	 * @description:当普通用户进入工作页面时加载
	 * @parameters:HttpServletRequest request
	 * @return:List<UserLabel>
	 * */
	List<UserLabel> firstGetData(HttpServletRequest request);
	
	/**
	 * @description:普通用户工作时预加载
	 * @parameters:HttpServletRequest request
	 * @return:List<UserLabel>
	 * */
	List<UserLabel> preGetData(HttpServletRequest request);
		
	/**
	 * @description:更新标记完毕的数据到数据库表
	 * @parameters:HttpServletRequest request
	 * */
	void sendFinishData(List<UserLabel> list);
	
	/**
	 * @description:开发者随机查看用户标注情况
	 * @parameters:HttpServletRequest request
	 * @return:List<UserLabel>
	 * */
	List<UserLabel> getRandUserLabel(HttpServletRequest request);
	
	/**
	 * @description:获取某任务的数据条数
	 * @parameters: request对象
	 * @return:数据数量 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!可能要删除
	 * */
	int getDataNum(String username,String developer,String assignment);
	
	/**
	 * @description:获取5条数据的所有工作者的标注情况
	 * @parameters:开发者名，任务名，数据集合
	 * @return:map<String,List<UserLabel>>
	 * */
	Map<String,List<UserLabel>> getFiveDataAllUserLabel(String assignment,String developer,List<String> datas);
	
	/**
	 * @description:删除用户拷贝的数据
	 * @parameters: 开发者名，任务名
	 * */
	void deleteUserData(String developer,String assignment);
	
	/**
	 * @description:获取5条标注者标注的label
	 * @parameters: 开发者名，任务名，任务类型，用户名
	 * @return: Map<String,Object>
	 * */
	Map<String,Object> getLabelOne(HttpServletRequest request);
}
