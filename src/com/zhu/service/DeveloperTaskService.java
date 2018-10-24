package com.zhu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhu.dto.AddTaskBody;
import com.zhu.dto.HomeDataBody;
import com.zhu.entity.DeveloperTask;

/**
 * @description:开发者任务service接口，
 * @function:获取开发者任务列表，获取开发者任务总数，获取首页属性
 * 随机获取5条非赏金任务，添加工作者人数,获取开发者自己所拥有的任务，
 * 保存开发者发布的任务，删除任务，移除工作者，完成任务，判断任务名字是否与之前的任务有相同，
 * 获取统计页面数据，获取工作页面数据，计算数据
 * @create in 2018.08.22
 * by Unow
 * */
public interface DeveloperTaskService {
	
	Map<String,Object> getTaskNameByDeveloper(String developer);
	
	/**
	 * @description:获取所有的开发者任务
	 * @parameters:page页数，row每页的条数
	 * @return:List<DeveloperTask>
	 * */
	List<DeveloperTask> getAllTask(int page,int rows);
	/**
	 * @description:获取开发者任务总数
	 * @return:int
	 * */
	int getTaskNum();
	/**
	 * @description:随机5条非赏金开发者任务
	 * @return:List<DeveloperTask>
	 * */
	List<DeveloperTask> getRandTask();
	
	/**
	 * @description:点击查看一个赏金开发者任务的详情
	 * @parameters:开发者名，任务名
	 * @return:List<DeveloperTask>
	 * */
	DeveloperTask getDetailOne(String developer,String assignment);
	
	/**
	 * @description:当有工作者加入时添加人数
	 * @parameters:HttpServletRequest reuqest
	 * */
	void addWorkers(HttpServletRequest reuqest);
	
	/**
	 * @description:获取开发者自己所拥有的任务
	 * @parameters:HttpServletRequest reuqest
	 * */
	List<DeveloperTask> getSelfTasks(String developer);
	
	/** 
	 * @description:添加开发者发布的任务信息到数据库表
	 * @parameters:自己看
	 * */
	void addDeveloperTask(AddTaskBody addTaskBody,int dataNum,String developer);
	
	/**
	 * @description:删除任务
	 * @parameters:自己看
	 * @return : boolean
	 * */
	boolean deleteTask(String developer,String assignment,boolean isPublic,String taskType,String sourceFilePath);
	
	/**
	 * @description:添加开发者发布的任务信息到数据库表
	 * @parameters:开发者名字,预计支出
	 * @return:自己看
	 * */
	int enoughAdd(String developer,double money);
	
	void removeWorkers();
	
	/**
	 * @description:完成任务
	 * @parameters:开发者名字，任务名
	 * @return: true or false
	 * */
	boolean setTaskOver(String developer,String assignment);
	
	/**
	 * @description:判断任务名字是否与之前的任务有相同
	 * @parameters:开发者名字，任务名，序号 默认输入1
	 * @return: 处理后的任务名
	 * */
	String isExist(String developer,String assignment,int i);
	
	/**
	 * @description:获取首页属性
	 * @parameters:
	 * @return: HomeDataBody
	 * */
	HomeDataBody getHomeData();
	
	/**
	 * @description:获取体验标注页面的数据
	 * @parameters:开发者，任务名
	 * @return: Map<String,Object>
	 * */
	Map<String,Object> exprience(String developer,String assignment);
	
	/**
	 * @description:获取统计页面数据
	 * @parameters:开发者，任务名
	 * @return: Map<String,Object>
	 * */
	Map<String,Object> getSatisiticData(String developer,String assignment);
	
	/**
	 * @description:获取工作页面数据
	 * @parameters:开发者，任务名
	 * @return: Map<String,Object>
	 * */
	Map<String,Object> workPageData(HttpServletRequest request);
	
	/**
	 * @description:计算数据
	 * @parameters:开发者，任务名，任务类型，路径，是否为赏金任务
	 * @return: Map<String,Object>
	 * */
	void caculateData(String developer,String assignment,String taskType,String path,boolean isPublic);
}
