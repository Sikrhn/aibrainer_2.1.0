package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.TaskClasses;

/**
 * @description:�����label���dao�ӿڣ�
 * @function:��ȡ��Ӧ�����label�������Ӧ�����label,ɾ����Ӧ�����label
 * @create in 2018.08.21
 * by Unow
 * */
public interface TaskClassesDao {
	/**
	 * @description:�������ߴ�����������labelʱ��
	 * ����label�������ݿ��
	 * @parameter:��������,������
	 * */
	void insertLabel(@Param("taskClasses")TaskClasses taskClasses);
	/**
	 * @description:�������߽����עҳ��ʱ��
	 * ��ȡ�������label
	 * @parameter:��������,������
	 * @return: List<String>
	 * */
	List<String> getLabel(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:ɾ����Ӧ�����label
	 * @parameter:��������,������
	 * */
	void deleteLabels(@Param("developer")String developer,@Param("assignment")String assignment);
}
