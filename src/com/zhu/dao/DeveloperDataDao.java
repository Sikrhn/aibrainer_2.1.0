package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.DeveloperData;
import com.zhu.entity.UserLabel;

/**
 * @description:����������dao�ӿڣ�
 * @function:��ѯ���������,���������ļ�����
 * 			ɾ�������ߵ�ĳ�������µ��������ݣ�����label�������ע���������ѯ5������,
 * 			��ȡ����ע������
 * @create in 2018.08.20
 * by Unow
 * */
public interface DeveloperDataDao {
	/**
	 * @description:��ѯĳ�����µ���������
	 * @parameters:����������������
	 * @return: List<DeveloperData> or null
	 * */
	List<DeveloperData> getDataName(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:��������������
	 * @parameters:�����������������������ļ���
	 * */
	void insertData(@Param("developer")String developer,@Param("assignment")String assignment,@Param("dataName")String dataName);

	/**
	 * @description:�����ѯ5������
	 * @parameters:����������������
	 * @return: List<DeveloperData> or null
	 * */
	List<DeveloperData> getRandFive(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:��ȡָ������������
	 * @parameters:����������������������
	 * @return: List<DeveloperData>
	 * */
	List<DeveloperData> getDataByNumber(@Param("num")int num,@Param("developer")String developer,@Param("assignment")String assignment);
	/**
	 * @description:ɾ�������ߵ�ĳ�������µ���������
	 * @parameters:����������������
	 * */
	void deleteDataByAssignment(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:����label
	 * @parameters:UserLabel
	 * */
	void updateLabel(@Param("userLabel")UserLabel userLabel);

	/**
	 * @description:�����ע����
	 * @parameters:����������������
	 * @return: List<DeveloperData>
	 * */
	List<DeveloperData> getExprience(@Param("developer")String developer,@Param("assignment")String assignment);
	
	/**
	 * @description:��ȡ����ע������
	 * @parameters:����������������
	 * @return: int
	 * */
	int getMarkedNum(@Param("developer")String developer,@Param("assignment")String assignment);
	
	
}
