package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.dto.TaskBody;
import com.zhu.entity.DeveloperTask;

/**
 * @description:获取开发者自己的所有任务的名字和是否完成和是否为赏金任务，开发者任务dao接口，
 * @function:获取所有开发者任务，获取随机的5条非赏金开发者任务,
 * 		查看指定任务详情,获取开发者自己发布的任务，插入开发者发布的任务，
 * 		更新增加1工作者数，查询任务是否完成
 * 		查询工作人数	，删除任务，更新赏金任务已经完成，支出工资，获取开发者所拥有的任务的数量
 * 		,查询任务所剩工资，查询任务是否存在，工作者数量减1并更新信息到数据库表，重新更新需要工作者人数的值
 * @create in 2018.08.21
 * by Unow
 * */
public interface DeveloperTaskDao {
	
	/**
	 * @description:获取开发者自己的所有任务的名字,查询任务是否完成和是否为赏金任务
	 * @Parameters:开发者名
	 * @return: List<TaskBody>
	 * */
	List<TaskBody> getTaskSimple(@Param("developer")String developer);
	
	/**
	 * @description:获取总条数
	 * @return: 任务总数int
	 * */
	int getTaskNum();
	
	/**
	 * @description:获取开发者所拥有的任务的数量
	 * @return: 任务总数int
	 * */
	int getDeveloperTaskNum(String developer);
	
	/**
	 * @description:获取所有的开发者任务
	 * @parameters: start起始，end终止
	 * @return List<Developer> or null
	 * */
	List<DeveloperTask> getAllTask(@Param("page")int start,@Param("rows")int end);
	
	/**
	 * @description:随机获取5条开发者发布的非赏金任务
	 * @return List<Developer> or null
	 * */
	List<DeveloperTask> getPublicTask();
	
	/**
	 * @description:获取详细的一条任务的信息
	 * @parameters:开发者名，assignment任务名
	 * @return DeveloperTask Type or null
	 * */
	DeveloperTask getDetailOne(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:获取开发者自己发布的任务
	 * @parameters:开发者名
	 * @return:List<Developer>(Developer[assignment,isover,ispublic,dete,wokers,need_workers])
	 * */
	List<DeveloperTask> getSelfTask(@Param("developer")String developer);
	
	/**
	 * @description:插入发布的任务到数据库表
	 * @parameters:DeveloperTask TYPE
	 * */
	void insertTask(@Param("developerTask")DeveloperTask developerTask);
	
	
	/**
	 * @description:工作者数量加1并更新信息到数据库表
	 * @parameters:开发者名,任务名
	 * */
	void addWokerNum(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:工作者数量减1并更新信息到数据库表
	 * @parameters:开发者名,任务名
	 * */
	void reduceWokerNum(@Param("developer")String developer,@Param("assignment")String assignment);

	
	/**
	 * @description:查询是否需要工作者
	 * @parameters:开发者名，任务名
	 * @return:DeveloperTask
	 * */
	DeveloperTask isNeedWorker(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:删除任务
	 * @parameters:开发者名，任务名
	 * */
	void deleteTaskByAssignment(@Param("developer")String developer,@Param("assignment")String assignment);
	
	
	/**
	 * @description:更新赏金任务已经完成
	 * @parameters:开发者名，任务名
	 * */
	void overTask(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:支出工资
	 * @parameters:开发者名，任务名，工资
	 * */
	void payMoney(@Param("developer")String developer,@Param("assignment")String assignment,@Param("money")double money);
	
	/**
	 * @description:查询任务所剩工资
	 * @parameters:开发者名，任务名
	 * @return:任务余额
	 * */
	double queryMoney(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:查询任务是否完成
	 * @parameters:开发者名，任务名
	 * @return: 
	 * */
	boolean taskIsOver(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:查询任务是否存在
	 * @parameters:开发者名，任务名
	 * @return: List<DeveloperTask>
	 * */
	DeveloperTask queryTaskExist(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:重新更新需要工作者人数的值
	 * @parameters:开发者名，任务名，人数
	 * @return: List<DeveloperTask>
	 * */
	void updateWorkerNum(@Param("developer")String developer,@Param("assignment")String assignment,@Param("num")int num);
}
