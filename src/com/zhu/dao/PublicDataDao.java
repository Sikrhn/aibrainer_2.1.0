package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.DeveloperData;
import com.zhu.entity.PublicData;

/**
 * @description : 非赏金任务标注时对数据的处理, 获取随机的被标记没有超过num的5条数据，获取所有被标注的数据，多数则只拿其中的一样，
 * 					获取被标注的数量,删除任务数据
 * @function:
 * */
public interface PublicDataDao {
	/**
	 * @description : 插入被标注的数据
	 * @parameters : PublicTask类型
	 * */
	void insertData(@Param("publicData") PublicData publicData);
	
	/**
	 * @description : 获取随机的被标记没有超过num的5条数据
	 * @parameters : 任务名，开发者名
	 * @return : List<DeveloperData>
	 * */
	List<DeveloperData> getRandFive(@Param("num")int num,@Param("assignment")String assignment,@Param("developer")String developer);
	
	/**
	 * @description : 获取所有被标注的数据，多数则只拿其中的一样
	 * @parameters : 任务名，开发者名
	 * @return : List<PublicData>
	 * */
	List<PublicData> getMarkedLabel(@Param("assignment")String assignment,@Param("developer")String developer);
	
	/**
	 * @description : 获取被标注的数量
	 * @parameters : 任务名，开发者名
	 * @return : int
	 * */
	int getMarkedNumber(@Param("assignment")String assignment,@Param("developer")String developer);
	
	/**
	 * @description : 删除任务数据
	 * @parameters : 任务名，开发者名
	 * @return : void
	 * */
	void deleteDataByAssignment(@Param("developer")String developer,@Param("assignment")String assignment);
}
