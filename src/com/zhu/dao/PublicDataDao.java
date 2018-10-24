package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.DeveloperData;
import com.zhu.entity.PublicData;

/**
 * @description : ���ͽ������עʱ�����ݵĴ���, ��ȡ����ı����û�г���num��5�����ݣ���ȡ���б���ע�����ݣ�������ֻ�����е�һ����
 * 					��ȡ����ע������,ɾ����������
 * @function:
 * */
public interface PublicDataDao {
	/**
	 * @description : ���뱻��ע������
	 * @parameters : PublicTask����
	 * */
	void insertData(@Param("publicData") PublicData publicData);
	
	/**
	 * @description : ��ȡ����ı����û�г���num��5������
	 * @parameters : ����������������
	 * @return : List<DeveloperData>
	 * */
	List<DeveloperData> getRandFive(@Param("num")int num,@Param("assignment")String assignment,@Param("developer")String developer);
	
	/**
	 * @description : ��ȡ���б���ע�����ݣ�������ֻ�����е�һ��
	 * @parameters : ����������������
	 * @return : List<PublicData>
	 * */
	List<PublicData> getMarkedLabel(@Param("assignment")String assignment,@Param("developer")String developer);
	
	/**
	 * @description : ��ȡ����ע������
	 * @parameters : ����������������
	 * @return : int
	 * */
	int getMarkedNumber(@Param("assignment")String assignment,@Param("developer")String developer);
	
	/**
	 * @description : ɾ����������
	 * @parameters : ����������������
	 * @return : void
	 * */
	void deleteDataByAssignment(@Param("developer")String developer,@Param("assignment")String assignment);
}
