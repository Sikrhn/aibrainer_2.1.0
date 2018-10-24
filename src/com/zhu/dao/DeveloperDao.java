package com.zhu.dao;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.Developer;

/**
 * @description:������dao�ӿڣ�
 * @function:��ѯ�������˺ţ�ƥ�俪�����˺����룬ע�Ὺ�����˻�����ѯ�������û�����
 * 			��ֵ��֧��
 * @create in 2018.08.20
 * by Unow
 * */
public interface DeveloperDao {
	/**
	 * @description:��ѯ�Ƿ��Ѵ��ڸÿ�����
	 * @parameters:�������û���
	 * @return:Developer or null
	 * */
	Developer queryDeveloper(@Param("developer")String developer);
	/**
	 * @description:ƥ�������˺������Ƿ���ȷ
	 * @parameters:�������û���
	 * @return:Developer or null
	 * */
	Developer matchDeveloper(@Param("developer")String developer,@Param("password")String password);
	/**
	 * @description:ע���˻�
	 * @parameters:Developer Type
	 * */
	void registerDeveloper(@Param("developer")Developer developer);
	
	/**
	 * @description:֧�����
	 * @parameters:����������֧�����
	 * */
	void exportBalance(@Param("developer")String developer,@Param("money")double money);
	
	/**
	 * @description:��ֵ
	 * @parameters:������������ֵ���
	 * */
	void importBalance(@Param("developer")String developer,@Param("money")double money);
	
	/**
	 * @description:��ѯ�������û�����
	 * @return: ����������
	 * */
	int developerNum(); 
	
	
}
