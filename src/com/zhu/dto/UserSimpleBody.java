package com.zhu.dto;
/**
 * @description:���������ҵ������б�����ʾ�û��б�����ݣ������װ��
 * @create in 2018.08.31
 * 	by Unow
 * */
public class UserSimpleBody {
	private String username;
	private int rank;
	private int markedNum;
	
	private boolean isover;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getMarkedNum() {
		return markedNum;
	}
	public void setMarkedNum(int markedNum) {
		this.markedNum = markedNum;
	}
	public boolean isIsover() {
		return isover;
	}
	public void setIsover(boolean isover) {
		this.isover = isover;
	}
	public UserSimpleBody() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserSimpleBody(String username, int rank, int markedNum,
			boolean isover) {
		super();
		this.username = username;
		this.rank = rank;
		this.markedNum = markedNum;
		this.isover = isover;
	}
}
