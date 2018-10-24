package com.zhu.dto;

public class HomeDataBody {
	private int userNum;
	private int developerNum;
	private double allAccuracy;
	private int taskNum;
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public int getDeveloperNum() {
		return developerNum;
	}
	public void setDeveloperNum(int developerNum) {
		this.developerNum = developerNum;
	}
	public double getAllAccuracy() {
		return allAccuracy;
	}
	public void setAllAccuracy(double allAccuracy) {
		this.allAccuracy = allAccuracy;
	}
	public int getTaskNum() {
		return taskNum;
	}
	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}
	public HomeDataBody(int userNum, int developerNum, double allAccuracy,
			int taskNum) {
		super();
		this.userNum = userNum;
		this.developerNum = developerNum;
		this.allAccuracy = allAccuracy;
		this.taskNum = taskNum;
	}
	public HomeDataBody() {
		super();
		// TODO Auto-generated constructor stub
	}
}
