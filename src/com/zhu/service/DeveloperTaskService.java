package com.zhu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhu.dto.AddTaskBody;
import com.zhu.dto.HomeDataBody;
import com.zhu.entity.DeveloperTask;

/**
 * @description:����������service�ӿڣ�
 * @function:��ȡ�����������б���ȡ������������������ȡ��ҳ����
 * �����ȡ5�����ͽ�������ӹ���������,��ȡ�������Լ���ӵ�е�����
 * ���濪���߷���������ɾ�������Ƴ������ߣ���������ж����������Ƿ���֮ǰ����������ͬ��
 * ��ȡͳ��ҳ�����ݣ���ȡ����ҳ�����ݣ���������
 * @create in 2018.08.22
 * by Unow
 * */
public interface DeveloperTaskService {
	
	Map<String,Object> getTaskNameByDeveloper(String developer);
	
	/**
	 * @description:��ȡ���еĿ���������
	 * @parameters:pageҳ����rowÿҳ������
	 * @return:List<DeveloperTask>
	 * */
	List<DeveloperTask> getAllTask(int page,int rows);
	/**
	 * @description:��ȡ��������������
	 * @return:int
	 * */
	int getTaskNum();
	/**
	 * @description:���5�����ͽ𿪷�������
	 * @return:List<DeveloperTask>
	 * */
	List<DeveloperTask> getRandTask();
	
	/**
	 * @description:����鿴һ���ͽ𿪷������������
	 * @parameters:����������������
	 * @return:List<DeveloperTask>
	 * */
	DeveloperTask getDetailOne(String developer,String assignment);
	
	/**
	 * @description:���й����߼���ʱ�������
	 * @parameters:HttpServletRequest reuqest
	 * */
	void addWorkers(HttpServletRequest reuqest);
	
	/**
	 * @description:��ȡ�������Լ���ӵ�е�����
	 * @parameters:HttpServletRequest reuqest
	 * */
	List<DeveloperTask> getSelfTasks(String developer);
	
	/** 
	 * @description:��ӿ����߷�����������Ϣ�����ݿ��
	 * @parameters:�Լ���
	 * */
	void addDeveloperTask(AddTaskBody addTaskBody,int dataNum,String developer);
	
	/**
	 * @description:ɾ������
	 * @parameters:�Լ���
	 * @return : boolean
	 * */
	boolean deleteTask(String developer,String assignment,boolean isPublic,String taskType,String sourceFilePath);
	
	/**
	 * @description:��ӿ����߷�����������Ϣ�����ݿ��
	 * @parameters:����������,Ԥ��֧��
	 * @return:�Լ���
	 * */
	int enoughAdd(String developer,double money);
	
	void removeWorkers();
	
	/**
	 * @description:�������
	 * @parameters:���������֣�������
	 * @return: true or false
	 * */
	boolean setTaskOver(String developer,String assignment);
	
	/**
	 * @description:�ж����������Ƿ���֮ǰ����������ͬ
	 * @parameters:���������֣������������ Ĭ������1
	 * @return: ������������
	 * */
	String isExist(String developer,String assignment,int i);
	
	/**
	 * @description:��ȡ��ҳ����
	 * @parameters:
	 * @return: HomeDataBody
	 * */
	HomeDataBody getHomeData();
	
	/**
	 * @description:��ȡ�����עҳ�������
	 * @parameters:�����ߣ�������
	 * @return: Map<String,Object>
	 * */
	Map<String,Object> exprience(String developer,String assignment);
	
	/**
	 * @description:��ȡͳ��ҳ������
	 * @parameters:�����ߣ�������
	 * @return: Map<String,Object>
	 * */
	Map<String,Object> getSatisiticData(String developer,String assignment);
	
	/**
	 * @description:��ȡ����ҳ������
	 * @parameters:�����ߣ�������
	 * @return: Map<String,Object>
	 * */
	Map<String,Object> workPageData(HttpServletRequest request);
	
	/**
	 * @description:��������
	 * @parameters:�����ߣ����������������ͣ�·�����Ƿ�Ϊ�ͽ�����
	 * @return: Map<String,Object>
	 * */
	void caculateData(String developer,String assignment,String taskType,String path,boolean isPublic);
}
