package com.zhu.service;

import java.util.List;

/**
 * @description:任务所设置的Label类别service接口，
 * @function:获取label类别，将开发者上传任务时设置的label插入数据库表，
 * 			清空数据库表中相应任务的label
 * @create in 2018.08.22
 * by Unow
 * */
public interface TaskClassesService {
	/**
	 * @description:通过开发者名任务名获取对应的label组
	 * @parameters:开发者名，任务名
	 * @return:List<String>
	 * */
	List<String> getLabels(String developer,String assignment);
	
	/**
	 * @description:将开发者上传任务时设置的label插入数据库表
	 * @parameters:String[] labels,developer,assignment,path,taskType
	 * */
	void addLabels(String[] labels,String developer, String assignment,String filePath,String taskType);
	
	/**
	 * @description:清空数据库表中相应任务的label
	 * @parameters:开发者名，任务名
	 * */
	void deleteLabels(String developer,String assignment);
}
