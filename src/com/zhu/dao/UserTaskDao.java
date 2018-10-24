package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.dto.TaskBody;
import com.zhu.dto.UserMsgBody;
import com.zhu.dto.UserSimpleBody;
import com.zhu.entity.UserTask;

/**
 * @description:��ȡ��ע���Լ����������������,��ѯ�����Ƿ���ɺ��Ƿ�Ϊ�ͽ�����,��ͨ�û�����dao�ӿڣ�
 * @function:����μӵĹ�����Ϣ,��ȡ�û�ӵ�е��������Ϣ����ȡ�û�������Ϣ,ɾ��usertask
 * 		��ȡ��������������ͬ�����User,��ȡ�������ڽ��й����е�������Ϣ�������������		
 * 		��ѯ���еĹ������Ƿ�������񣬸��±����������ѯ��������ͱ����ɵ�����������׼ȷ�ʣ��Ƴ���ָ�Ĺ�����
 * 		�ø������µ����й����ߵ���ɹ���״̬��Ϊ�����
 * @create in 2018.08.21
 * by Unow
 * */


public interface UserTaskDao {
	/**
	 * @description:ɾ��usertask
	 * @Parameters:�����ߣ�����
	 * */
	void deleteUserTask(@Param("assignment")String assignment,@Param("developer")String developer);
	
	/**
	 * @description:��ȡ��ע���Լ����������������,��ѯ�����Ƿ���ɺ��Ƿ�Ϊ�ͽ�����
	 * @Parameters:��ͨ�û���
	 * @return: TaskBody
	 * */
	List<TaskBody> getTaskSimple(@Param("username")String username);
	
	/**
	 * @description:�����û���ӵ�����
	 * @parameter:�û���������������������
	 * */
	void insertUserTask(@Param("userTask")UserTask userTask);
	
	/**
	 * @description:��ȡ�û�ӵ�е��������Ϣ
	 * @parameter: ����������������
	 * @return: List<UserTask> Type or null
	 * */	
	List<UserSimpleBody> getUserSimpleByTask(@Param("assignment")String assignment,@Param("developer")String developer);
	
	/**
	 * @description:��ȡ�û�ӵ�е�������Ϣ
	 * @parameter: �û���
	 * @return: UserTask Type or null
	 * */
	List<UserTask> getUserTask(@Param("username")String username);
	
	/**
	 * @description: ��ȡ�������ڽ�����ͬ�����User
	 * @parameter:  �����ߣ�������
	 * @return: UserTask Type or null
	 * */
	List<UserTask> getSameUserTask(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description: ��ȡ�������ڽ��й����е�������Ϣ
	 * @parameter:  �û����������ߣ�������
	 * @return: UserTask Type or null
	 * */
	UserTask getWorkingTask(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description: �����������
	 * @parameter:  �û����������ߣ�������
	 * @return:  null
	 * */
	void updateIsOver(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	/**
	 * @description: ��ѯ���еĹ������Ƿ��������
	 * @parameter: �����ߣ�������
	 * @return: List<Boolean> or null
	 * */
	List<Boolean> getAllUserIsOver(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description: ���±������
	 * @parameter:  �û����������ߣ�������
	 * */
	void updateMarkedNum(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment,@Param("num")int num);
	
	/**
	 * @description: ��ѯ��������ͱ����ɵ�����
	 * @parameter: �û����������ߣ�������
	 * @return: UserMsgBody or null
	 * */
	UserMsgBody getMarkedDetail(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:����׼ȷ��,����ͽ�,����������
	 * @parameters:�û�����������������������׼ȷ��
	 * */
	void insertAccuracyMoney(@Param("UserTask")UserTask userTask);
	
	/**
	 * @description:�Ƴ���ָ�Ĺ�����
	 * @parameters:�û���������������������
	 * */
	void deleteUserTaskByName(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:�ø������µ����й����ߵ���ɹ���״̬��Ϊ�����
	 * @parameters:����������������
	 * */
	void reoverUserTask(@Param("developer")String developer,@Param("assignment")String assignment);
}
