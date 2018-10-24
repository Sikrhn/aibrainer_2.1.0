package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.dto.TaskBody;
import com.zhu.entity.DeveloperTask;

/**
 * @description:��ȡ�������Լ���������������ֺ��Ƿ���ɺ��Ƿ�Ϊ�ͽ����񣬿���������dao�ӿڣ�
 * @function:��ȡ���п��������񣬻�ȡ�����5�����ͽ𿪷�������,
 * 		�鿴ָ����������,��ȡ�������Լ����������񣬲��뿪���߷���������
 * 		��������1������������ѯ�����Ƿ����
 * 		��ѯ��������	��ɾ�����񣬸����ͽ������Ѿ���ɣ�֧�����ʣ���ȡ��������ӵ�е����������
 * 		,��ѯ������ʣ���ʣ���ѯ�����Ƿ���ڣ�������������1��������Ϣ�����ݿ�����¸�����Ҫ������������ֵ
 * @create in 2018.08.21
 * by Unow
 * */
public interface DeveloperTaskDao {
	
	/**
	 * @description:��ȡ�������Լ����������������,��ѯ�����Ƿ���ɺ��Ƿ�Ϊ�ͽ�����
	 * @Parameters:��������
	 * @return: List<TaskBody>
	 * */
	List<TaskBody> getTaskSimple(@Param("developer")String developer);
	
	/**
	 * @description:��ȡ������
	 * @return: ��������int
	 * */
	int getTaskNum();
	
	/**
	 * @description:��ȡ��������ӵ�е����������
	 * @return: ��������int
	 * */
	int getDeveloperTaskNum(String developer);
	
	/**
	 * @description:��ȡ���еĿ���������
	 * @parameters: start��ʼ��end��ֹ
	 * @return List<Developer> or null
	 * */
	List<DeveloperTask> getAllTask(@Param("page")int start,@Param("rows")int end);
	
	/**
	 * @description:�����ȡ5�������߷����ķ��ͽ�����
	 * @return List<Developer> or null
	 * */
	List<DeveloperTask> getPublicTask();
	
	/**
	 * @description:��ȡ��ϸ��һ���������Ϣ
	 * @parameters:����������assignment������
	 * @return DeveloperTask Type or null
	 * */
	DeveloperTask getDetailOne(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:��ȡ�������Լ�����������
	 * @parameters:��������
	 * @return:List<Developer>(Developer[assignment,isover,ispublic,dete,wokers,need_workers])
	 * */
	List<DeveloperTask> getSelfTask(@Param("developer")String developer);
	
	/**
	 * @description:���뷢�����������ݿ��
	 * @parameters:DeveloperTask TYPE
	 * */
	void insertTask(@Param("developerTask")DeveloperTask developerTask);
	
	
	/**
	 * @description:������������1��������Ϣ�����ݿ��
	 * @parameters:��������,������
	 * */
	void addWokerNum(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:������������1��������Ϣ�����ݿ��
	 * @parameters:��������,������
	 * */
	void reduceWokerNum(@Param("developer")String developer,@Param("assignment")String assignment);

	
	/**
	 * @description:��ѯ�Ƿ���Ҫ������
	 * @parameters:����������������
	 * @return:DeveloperTask
	 * */
	DeveloperTask isNeedWorker(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:ɾ������
	 * @parameters:����������������
	 * */
	void deleteTaskByAssignment(@Param("developer")String developer,@Param("assignment")String assignment);
	
	
	/**
	 * @description:�����ͽ������Ѿ����
	 * @parameters:����������������
	 * */
	void overTask(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:֧������
	 * @parameters:����������������������
	 * */
	void payMoney(@Param("developer")String developer,@Param("assignment")String assignment,@Param("money")double money);
	
	/**
	 * @description:��ѯ������ʣ����
	 * @parameters:����������������
	 * @return:�������
	 * */
	double queryMoney(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:��ѯ�����Ƿ����
	 * @parameters:����������������
	 * @return: 
	 * */
	boolean taskIsOver(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:��ѯ�����Ƿ����
	 * @parameters:����������������
	 * @return: List<DeveloperTask>
	 * */
	DeveloperTask queryTaskExist(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:���¸�����Ҫ������������ֵ
	 * @parameters:����������������������
	 * @return: List<DeveloperTask>
	 * */
	void updateWorkerNum(@Param("developer")String developer,@Param("assignment")String assignment,@Param("num")int num);
}
