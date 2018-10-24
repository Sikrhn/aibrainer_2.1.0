package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.DeveloperData;
import com.zhu.entity.UserLabel;

/**
 * @description:��ͨ�û�dao�ӿڣ�
 * @function:��������������Ϣ���û��������һ������ʱ��ȡ10��δ��ע���ݣ�
 * Ԥ����ʱ��ȡ10��δ��ע���ݣ���ȡ�����5��userlabel����ȡ������������
 * 	,ɾ����ͨ�û������ݣ����±����ɵ����ݵ����ݿ��
 * @create in 2018.08.21
 * by Unow
 * */
public interface UserLabelDao {
	/**
	 * @description:�û����빤��ʱ�����Ӧ��������Ϣ
	 * @parameters:DeveloperData Type,�û���
	 * */
	void insertData(@Param("username")String username,@Param("developerData")DeveloperData developerData);
	
	/**
	 * @description:�û���һ�δ�ҳ���ȡ10������
	 * @parameters:��������������,�û���
	 * @return: List<UserLabel>
	 * */
	List<UserLabel> firstGetDataName(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:�û�Ԥ����ʱ��ȡ10������
	 * @parameters:��������������,�û���
	 * @return: List<UserLabel>
	 * */
	List<UserLabel> preGetDataName(@Param("username")String username, @Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:ÿ�����һ�����µ����ݿ���
	 * @parameters:UserLabel Type
	 * */
	void updateLabel(@Param("userLabel")UserLabel userLabel);
	
	/**
	 * @description:��ȡ�����5��userlabel
	 * @parameters:�û�����������
	 * @return:UserLabel
	 * */
	List<UserLabel> getRandUserLabel(@Param("developer")String developer,@Param("username")String username,@Param("assignment")String assignment);
	
	/**
	 * @description:��ȡ������������
	 * @parameters:�û����������ߣ�������
	 * @return:��������
	 * */
	int getDataNum(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);

	/**
	 * @description:��ȡ��ͬ�������µ������û���ע���
	 * @parameters:������������������������
	 * @return:UserLabel����
	 * */
	List<UserLabel> getAllUserByDataName(@Param("developer")String developer,@Param("assignment")String assignment,@Param("dataName")String dataName);
	
	/**
	 * @description:ɾ����ͨ�û�������
	 * @parameters:����������������
	 * */
	void deleteUserData(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:ɾ����ָ����ͨ�û�����������
	 * @parameters:����������������,�û���
	 * */
	void deleteUserDataByName(@Param("developer")String developer,@Param("assignment")String assignment,@Param("username")String username);

	/**
	 * @description:���±����ɵ����ݵ����ݿ��
	 * @parameters:�������������������û�����������
	 * */
	void adoptLabel(@Param("developer")String developer,@Param("assignment")String assignment,@Param("username")String username,@Param("dataName")String dataName);
}
