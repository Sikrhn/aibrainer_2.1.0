package com.zhu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhu.entity.DeveloperData;
import com.zhu.entity.PublicData;

/**
 * @description:非赏金任务数据service接口，
 * @function:获取5条随机的数据，记录完成的标注数据， 进行数据分类
 * @create in 2018.09.05
 * by Unow
 * */
public interface PublicDataService {
	/**
	 * @description : 记录完成的标注数据
	 * @parameters : PublicData
	 * */
	void insert(List<PublicData> publicDatas);
	
	/**
	 * @description : 获取5条随机的数据
	 * @parameters : request
	 * @return : List<DeveloperData>
	 * */
	List<DeveloperData> getRandomData(HttpServletRequest request);
	
	/**
	 * @description : 进行数据分类
	 * @parameters : 开发者名，任务名
	 * @return : null
	 * */
	void classifyLabel(String developer,String assignment,String realPath);
	
	
}
