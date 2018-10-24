package com.zhu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhu.dto.UserSimpleBody;
import com.zhu.entity.UserTask;

/**
 * @description:普通用户任务service接口，
 * @function:加入任务，获取用户拥有的任务列表，
 * 			获取相同任务下的所有用户,获取相同任务下的所有用户的简单信息，
 * 			获取进行中的任务,查询所有用户是否已经完成该任务，移除标注者
 * 			
 * @create in 2018.08.22
 * by Unow
 * */
public interface UserTaskService {
	/**
	 * @description:加入开发者赏金任务
	 * @parameters:HttpServletRequest request
	 * @return: DataBody
	 * */
	int joinTask(HttpServletRequest request);
	
	/**
	 * @description:获取用户已拥有的任务
	 * @parameters:用户名
	 * @return:Map<String,Object>
	 * */
	Map<String,Object> getUserTask(String username);
	
	/**
	 * @description:获取相同任务下的所有用户
	 * @parameters:开发者名，任务名
	 * @return:List<UserTask>
	 * */
	List<UserTask> getUserSameTask(String developer,String assignment);
	
	/**
	 * @description:获取相同任务下的所有用户的简单信息
	 * @parameters:开发者名，任务名
	 * @return:List<UserTask>
	 * */
	List<UserSimpleBody> getSimpleUserSameTask(String developer,String assignment);
	/**
	 * @description:获取进行中的任务
	 * @parameters:用户名，开发者名，任务名
	 * @return:UserTask TYPE
	 * */
	UserTask getWorkingTask(String username,String developer,String assignment);
	
	
	/**
	 * @description:查询所有工作者是否已经完成该任务
	 * @parameters:开发者名，任务名
	 * @return: boolean
	 * */
	boolean isAllIsOver(String developer,String assignment);
	
	/**
	 * @description:记录任务已完成
	 * @parameters:通过request获取开发者名，任务名，用户名,任务类型
	 * @parameters: boolean
	 * */	
	boolean isover(String developer,String assingment,String username,String taskType);
	
	/**
	 * @description:移除标注者
	 * @parameters:通过request获取开发者名，任务名，用户名
	 * */
	void removeUser(HttpServletRequest request);
}
