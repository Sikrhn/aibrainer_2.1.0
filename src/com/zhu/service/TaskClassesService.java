package com.zhu.service;

import java.util.List;

/**
 * @description:���������õ�Label���service�ӿڣ�
 * @function:��ȡlabel��𣬽��������ϴ�����ʱ���õ�label�������ݿ��
 * 			������ݿ������Ӧ�����label
 * @create in 2018.08.22
 * by Unow
 * */
public interface TaskClassesService {
	/**
	 * @description:ͨ������������������ȡ��Ӧ��label��
	 * @parameters:����������������
	 * @return:List<String>
	 * */
	List<String> getLabels(String developer,String assignment);
	
	/**
	 * @description:���������ϴ�����ʱ���õ�label�������ݿ��
	 * @parameters:String[] labels,developer,assignment,path,taskType
	 * */
	void addLabels(String[] labels,String developer, String assignment,String filePath,String taskType);
	
	/**
	 * @description:������ݿ������Ӧ�����label
	 * @parameters:����������������
	 * */
	void deleteLabels(String developer,String assignment);
}
