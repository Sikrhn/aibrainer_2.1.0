package com.zhu.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description:开发者任务数据service接口，
 * @function:保存开发者任务数据，随机获取5条数据，统计数据筛选出最终label并进行数据处理
 * 				删除数据
 * @create in 2018.08.22
 * by Unow
 * */
public interface DeveloperDataService {
	
	/**
	 * @description:添加数据
	 * @parameters:开发者名，任务名，文件集合，父级路径
	 * @return: data数量
	 * */
	int addData(String developer,String assignment,List<MultipartFile> fileList,String realPath);
	
	/**
	 * @description:随机获取5条数据
	 * @parameters:开发者名,任务名
	 * @return:5条Data
	 * */
	List<String>  getFiveData(String developer,String assignment);
	
	/**
	 * @description:删除数据
	 * @parameters:开发者名,任务名
	 * */
	void deleteData(String developer,String assignment);
	
	/**
	 * @description:展示数据标签
	 * @parameters:开发者名,任务名,任务类型
	 * @return map
	 * */
	Map<String,Object> viewDataLabels(String developer,String assignment,String task_type);
	
	/**
	 * @description:统计数据筛选出最终label
	 * @parameters:开发者名,任务名
	 * */
	void scanFinalLabel(String ordPath,String developer,String assignment);
	
}
