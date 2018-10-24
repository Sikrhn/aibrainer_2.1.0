package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.User;

/**
 * @description:��ͨ�û�dao�ӿڣ�
 * @function:��ѯ�û��˻���ƥ����ͨ�û��˺����룬ע����ͨ�û��˻���ƥ���޸�������˻����޸�����
 * 			���»������Ľ��������ݿ�����������ͽ�
 * @create in 2018.08.20
 * by Unow
 * */
public interface UserDao {
	/**
	 * @description:ע���������
	 * @parameters: User Type
	 * */
	void registerUser(@Param("user") User user);
	/**
	 * @description:��¼ƥ��
	 * @parameters:username��password
	 * @return User Type or null
	 * */
	User matchUser(@Param("username")String username,@Param("password")String password);
	/**
	 * @description:��ѯ�Ƿ���ڸ��û�
	 * @parameters:username��password
	 * @return: User Type or null
	 * */
	User queryUser(@Param("username")String username);
	
	/**
	 * @description:���»������Ľ��������ݿ��
	 * @parameters:�û�������ý��
	 * */
	void getReward(@Param("User")User user);
	
	/**
	 * @description:�����ͽ�
	 * @parameters:�û����������ͽ�
	 * */
	void exportBalance(@Param("username")String username,@Param("money")double money);
	
	/**
	 * @description: ��ȡ���п����ߵ�׼ȷ��
	 * @return: List<Double>
	 * */
	List<Double> allUserAccuracy(); 
	
	/**
	 * @description: ƥ���޸�������˻�
	 * @parameters: �û�������������
	 * @return: User
	 * */
	User findUser(@Param("username")String username,@Param("email")String email);
	
	/**
	 * @description: �޸�����
	 * @parameters: �˺ţ�����
	 * @return: ��
	 * */
	void updatePassword(@Param("username")String username,@Param("password")String password);
}
