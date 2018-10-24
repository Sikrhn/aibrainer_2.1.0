package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.dto.TaskBody;
import com.zhu.dto.UserMsgBody;
import com.zhu.dto.UserSimpleBody;
import com.zhu.entity.UserTask;

/**
 * @description:获取标注者自己的所有任务的名字,查询任务是否完成和是否为赏金任务,普通用户任务dao接口，
 * @function:插入参加的工作信息,获取用户拥有的任务简单信息，获取用户任务信息,删除usertask
 * 		获取所有真正进行相同任务的User,获取所有正在进行工作中的任务信息，更新任务完成		
 * 		查询所有的工作者是否完成任务，更新标记数量，查询标记总数和被采纳的数量，插入准确率，移除特指的工作者
 * 		让该任务下的所有工作者的完成工作状态改为非完成
 * @create in 2018.08.21
 * by Unow
 * */


public interface UserTaskDao {
	/**
	 * @description:删除usertask
	 * @Parameters:开发者，任务
	 * */
	void deleteUserTask(@Param("assignment")String assignment,@Param("developer")String developer);
	
	/**
	 * @description:获取标注者自己的所有任务的名字,查询任务是否完成和是否为赏金任务
	 * @Parameters:普通用户名
	 * @return: TaskBody
	 * */
	List<TaskBody> getTaskSimple(@Param("username")String username);
	
	/**
	 * @description:插入用户添加的任务
	 * @parameter:用户名，开发者名，任务名
	 * */
	void insertUserTask(@Param("userTask")UserTask userTask);
	
	/**
	 * @description:获取用户拥有的任务简单信息
	 * @parameter: 任务名，开发者名
	 * @return: List<UserTask> Type or null
	 * */	
	List<UserSimpleBody> getUserSimpleByTask(@Param("assignment")String assignment,@Param("developer")String developer);
	
	/**
	 * @description:获取用户拥有的任务信息
	 * @parameter: 用户名
	 * @return: UserTask Type or null
	 * */
	List<UserTask> getUserTask(@Param("username")String username);
	
	/**
	 * @description: 获取所有正在进行相同任务的User
	 * @parameter:  开发者，任务名
	 * @return: UserTask Type or null
	 * */
	List<UserTask> getSameUserTask(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description: 获取所有正在进行工作中的任务信息
	 * @parameter:  用户名，开发者，任务名
	 * @return: UserTask Type or null
	 * */
	UserTask getWorkingTask(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description: 更新任务完成
	 * @parameter:  用户名，开发者，任务名
	 * @return:  null
	 * */
	void updateIsOver(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	/**
	 * @description: 查询所有的工作者是否完成任务
	 * @parameter: 开发者，任务名
	 * @return: List<Boolean> or null
	 * */
	List<Boolean> getAllUserIsOver(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description: 更新标记数量
	 * @parameter:  用户名，开发者，任务名
	 * */
	void updateMarkedNum(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment,@Param("num")int num);
	
	/**
	 * @description: 查询标记总数和被采纳的数量
	 * @parameter: 用户名，开发者，任务名
	 * @return: UserMsgBody or null
	 * */
	UserMsgBody getMarkedDetail(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:插入准确率,获得赏金,被采纳数量
	 * @parameters:用户名，开发者名，任务名，准确率
	 * */
	void insertAccuracyMoney(@Param("UserTask")UserTask userTask);
	
	/**
	 * @description:移除特指的工作者
	 * @parameters:用户名，开发者名，任务名
	 * */
	void deleteUserTaskByName(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:让该任务下的所有工作者的完成工作状态改为非完成
	 * @parameters:开发者名，任务名
	 * */
	void reoverUserTask(@Param("developer")String developer,@Param("assignment")String assignment);
}
