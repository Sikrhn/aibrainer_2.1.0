package com.zhu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhu.entity.UserLabel;

/**
 * @description:��ͨ�û�������������service�ӿڣ�
 * @function:�����������,���빤��ҳ���ȡ�������ݣ�
 * 			Ԥ�����������ݣ����±����ϵ����ݵ����ݿ��,
 * 			����������鿴�û���ע���,��ȡ5�����ݵ����й����ߵı�ע���
 * 			��ɾ���û����������ݣ���ȡ5����ע�߱�ע��label
 * @create in 2018.08.22
 * by Unow
 * */
public interface UserLabelService {
	/**
	 * @description:����ͨ�û���������󣬻�ȡ������Դ������Լ������ݿ��
	 * @parameters:HttpServletRequest request
	 * */
	void copyDeveloperData(HttpServletRequest request);
	
	/**
	 * @description:����ͨ�û����빤��ҳ��ʱ����
	 * @parameters:HttpServletRequest request
	 * @return:List<UserLabel>
	 * */
	List<UserLabel> firstGetData(HttpServletRequest request);
	
	/**
	 * @description:��ͨ�û�����ʱԤ����
	 * @parameters:HttpServletRequest request
	 * @return:List<UserLabel>
	 * */
	List<UserLabel> preGetData(HttpServletRequest request);
		
	/**
	 * @description:���±����ϵ����ݵ����ݿ��
	 * @parameters:HttpServletRequest request
	 * */
	void sendFinishData(List<UserLabel> list);
	
	/**
	 * @description:����������鿴�û���ע���
	 * @parameters:HttpServletRequest request
	 * @return:List<UserLabel>
	 * */
	List<UserLabel> getRandUserLabel(HttpServletRequest request);
	
	/**
	 * @description:��ȡĳ�������������
	 * @parameters: request����
	 * @return:�������� !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!����Ҫɾ��
	 * */
	int getDataNum(String username,String developer,String assignment);
	
	/**
	 * @description:��ȡ5�����ݵ����й����ߵı�ע���
	 * @parameters:���������������������ݼ���
	 * @return:map<String,List<UserLabel>>
	 * */
	Map<String,List<UserLabel>> getFiveDataAllUserLabel(String assignment,String developer,List<String> datas);
	
	/**
	 * @description:ɾ���û�����������
	 * @parameters: ����������������
	 * */
	void deleteUserData(String developer,String assignment);
	
	/**
	 * @description:��ȡ5����ע�߱�ע��label
	 * @parameters: �������������������������ͣ��û���
	 * @return: Map<String,Object>
	 * */
	Map<String,Object> getLabelOne(HttpServletRequest request);
}
