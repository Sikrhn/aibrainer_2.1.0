package com.zhu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhu.dto.UserSimpleBody;
import com.zhu.entity.UserTask;

/**
 * @description:��ͨ�û�����service�ӿڣ�
 * @function:�������񣬻�ȡ�û�ӵ�е������б�
 * 			��ȡ��ͬ�����µ������û�,��ȡ��ͬ�����µ������û��ļ���Ϣ��
 * 			��ȡ�����е�����,��ѯ�����û��Ƿ��Ѿ���ɸ������Ƴ���ע��
 * 			
 * @create in 2018.08.22
 * by Unow
 * */
public interface UserTaskService {
	/**
	 * @description:���뿪�����ͽ�����
	 * @parameters:HttpServletRequest request
	 * @return: DataBody
	 * */
	int joinTask(HttpServletRequest request);
	
	/**
	 * @description:��ȡ�û���ӵ�е�����
	 * @parameters:�û���
	 * @return:Map<String,Object>
	 * */
	Map<String,Object> getUserTask(String username);
	
	/**
	 * @description:��ȡ��ͬ�����µ������û�
	 * @parameters:����������������
	 * @return:List<UserTask>
	 * */
	List<UserTask> getUserSameTask(String developer,String assignment);
	
	/**
	 * @description:��ȡ��ͬ�����µ������û��ļ���Ϣ
	 * @parameters:����������������
	 * @return:List<UserTask>
	 * */
	List<UserSimpleBody> getSimpleUserSameTask(String developer,String assignment);
	/**
	 * @description:��ȡ�����е�����
	 * @parameters:�û���������������������
	 * @return:UserTask TYPE
	 * */
	UserTask getWorkingTask(String username,String developer,String assignment);
	
	
	/**
	 * @description:��ѯ���й������Ƿ��Ѿ���ɸ�����
	 * @parameters:����������������
	 * @return: boolean
	 * */
	boolean isAllIsOver(String developer,String assignment);
	
	/**
	 * @description:��¼���������
	 * @parameters:ͨ��request��ȡ�������������������û���,��������
	 * @parameters: boolean
	 * */	
	boolean isover(String developer,String assingment,String username,String taskType);
	
	/**
	 * @description:�Ƴ���ע��
	 * @parameters:ͨ��request��ȡ�������������������û���
	 * */
	void removeUser(HttpServletRequest request);
}
