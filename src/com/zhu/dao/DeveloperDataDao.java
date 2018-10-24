package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.DeveloperData;
import com.zhu.entity.UserLabel;

/**
 * @description:开发者数据dao接口，
 * @function:查询任务的数据,插入数据文件名，
 * 			删除开发者的某个任务下的所有数据，更新label，体验标注任务，随机查询5条数据,
 * 			获取被标注的条数
 * @create in 2018.08.20
 * by Unow
 * */
public interface DeveloperDataDao {
	/**
	 * @description:查询某任务下的所有数据
	 * @parameters:开发者名，任务名
	 * @return: List<DeveloperData> or null
	 * */
	List<DeveloperData> getDataName(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:插入该任务的数据
	 * @parameters:开发者名，任务名，数据文件名
	 * */
	void insertData(@Param("developer")String developer,@Param("assignment")String assignment,@Param("dataName")String dataName);

	/**
	 * @description:随机查询5条数据
	 * @parameters:开发者名，任务名
	 * @return: List<DeveloperData> or null
	 * */
	List<DeveloperData> getRandFive(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:获取指定数量的数据
	 * @parameters:数量，开发者名，任务名
	 * @return: List<DeveloperData>
	 * */
	List<DeveloperData> getDataByNumber(@Param("num")int num,@Param("developer")String developer,@Param("assignment")String assignment);
	/**
	 * @description:删除开发者的某个任务下的所有数据
	 * @parameters:开发者名，任务名
	 * */
	void deleteDataByAssignment(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:更新label
	 * @parameters:UserLabel
	 * */
	void updateLabel(@Param("userLabel")UserLabel userLabel);

	/**
	 * @description:体验标注任务
	 * @parameters:开发者名，任务名
	 * @return: List<DeveloperData>
	 * */
	List<DeveloperData> getExprience(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:获取被标注的条数
	 * @parameters:开发者名，任务名
	 * @return: int
	 * */
	int getMarkedNum(@Param("developer")String developer,@Param("assignment")String assignment);
	
	
}
