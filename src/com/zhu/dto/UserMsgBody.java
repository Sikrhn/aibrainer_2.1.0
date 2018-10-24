package com.zhu.dto;

/**
 * @description:用于接收的数据库查询标注采纳情况的对象，方便传输数据信息的数据传输对象
 * @create in 2018.08.25
 * 	by Unow
 * */
public class UserMsgBody {
	private int markedNum;
	private int adoptNum;
	public int getMarkedNum() {
		return markedNum;
	}
	public void setMarkedNum(int markedNum) {
		this.markedNum = markedNum;
	}
	public int getAdoptNum() {
		return adoptNum;
	}
	public void setAdoptNum(int adoptNum) {
		this.adoptNum = adoptNum;
	}
	
}
