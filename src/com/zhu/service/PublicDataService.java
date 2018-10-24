package com.zhu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhu.entity.DeveloperData;
import com.zhu.entity.PublicData;

/**
 * @description:���ͽ���������service�ӿڣ�
 * @function:��ȡ5����������ݣ���¼��ɵı�ע���ݣ� �������ݷ���
 * @create in 2018.09.05
 * by Unow
 * */
public interface PublicDataService {
	/**
	 * @description : ��¼��ɵı�ע����
	 * @parameters : PublicData
	 * */
	void insert(List<PublicData> publicDatas);
	
	/**
	 * @description : ��ȡ5�����������
	 * @parameters : request
	 * @return : List<DeveloperData>
	 * */
	List<DeveloperData> getRandomData(HttpServletRequest request);
	
	/**
	 * @description : �������ݷ���
	 * @parameters : ����������������
	 * @return : null
	 * */
	void classifyLabel(String developer,String assignment,String realPath);
	
	
}
