package com.zhu.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description:��������������service�ӿڣ�
 * @function:���濪�����������ݣ������ȡ5�����ݣ�ͳ������ɸѡ������label���������ݴ���
 * 				ɾ������
 * @create in 2018.08.22
 * by Unow
 * */
public interface DeveloperDataService {
	
	/**
	 * @description:�������
	 * @parameters:�������������������ļ����ϣ�����·��
	 * @return: data����
	 * */
	int addData(String developer,String assignment,List<MultipartFile> fileList,String realPath);
	
	/**
	 * @description:�����ȡ5������
	 * @parameters:��������,������
	 * @return:5��Data
	 * */
	List<String>  getFiveData(String developer,String assignment);
	
	/**
	 * @description:ɾ������
	 * @parameters:��������,������
	 * */
	void deleteData(String developer,String assignment);
	
	/**
	 * @description:չʾ���ݱ�ǩ
	 * @parameters:��������,������,��������
	 * @return map
	 * */
	Map<String,Object> viewDataLabels(String developer,String assignment,String task_type);
	
	/**
	 * @description:ͳ������ɸѡ������label
	 * @parameters:��������,������
	 * */
	void scanFinalLabel(String ordPath,String developer,String assignment);
	
}
