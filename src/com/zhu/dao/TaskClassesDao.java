package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.TaskClasses;

/**
 * @description:任务的label类别dao接口，
 * @function:获取对应任务的label，插入对应任务的label,删除对应任务的label
 * @create in 2018.08.21
 * by Unow
 * */
public interface TaskClassesDao {
	/**
	 * @description:当开发者创建任务设置label时，
	 * 将其label插入数据库表
	 * @parameter:开发者名,任务名
	 * */
	void insertLabel(@Param("taskClasses")TaskClasses taskClasses);
	/**
	 * @description:当工作者进入标注页面时，
	 * 获取该任务的label
	 * @parameter:开发者名,任务名
	 * @return: List<String>
	 * */
	List<String> getLabel(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:删除对应任务的label
	 * @parameter:开发者名,任务名
	 * */
	void deleteLabels(@Param("developer")String developer,@Param("assignment")String assignment);
}
