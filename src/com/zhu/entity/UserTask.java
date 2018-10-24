package com.zhu.entity;

public class UserTask {
	private String username;
	private String developer;
	private String assignment;
	private int markedNum;
	private int dataSum;
	private double taskMoney;
	private boolean isover;
	private double accuracy;
	private int adoptNum;
	private String taskType;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public int getMarkedNum() {
		return markedNum;
	}
	public void setMarkedNum(int markedNum) {
		this.markedNum = markedNum;
	}
	public double getTaskMoney() {
		return taskMoney;
	}
	public void setTaskMoney(double taskMoney) {
		this.taskMoney = taskMoney;
	}
	public UserTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isIsover() {
		return isover;
	}
	public void setIsover(boolean isover) {
		this.isover = isover;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	public int getDataSum() {
		return dataSum;
	}
	public void setDataSum(int dataSum) {
		this.dataSum = dataSum;
	}
	public int getAdoptNum() {
		return adoptNum;
	}
	public void setAdoptNum(int adoptNum) {
		this.adoptNum = adoptNum;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public UserTask(String username, String developer, String assignment,
			int markedNum, int dataSum, double taskMoney, boolean isover,
			double accuracy, int adoptNum, String taskType) {
		super();
		this.username = username;
		this.developer = developer;
		this.assignment = assignment;
		this.markedNum = markedNum;
		this.dataSum = dataSum;
		this.taskMoney = taskMoney;
		this.isover = isover;
		this.accuracy = accuracy;
		this.adoptNum = adoptNum;
		this.taskType = taskType;
	}
	public UserTask(String username, String developer, String assignment,
			 int dataSum, double taskMoney, boolean isover,
			double accuracy, int adoptNum) {
		super();
		this.username = username;
		this.developer = developer;
		this.assignment = assignment;
		this.dataSum = dataSum;
		this.taskMoney = taskMoney;
		this.isover = isover;
		this.accuracy = accuracy;
		this.adoptNum = adoptNum;
	}
}
