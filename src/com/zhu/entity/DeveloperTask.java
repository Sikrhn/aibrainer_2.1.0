package com.zhu.entity;

public class DeveloperTask {
	private String developer;
	private String assignment;
	private String taskType;
	private int needWorkers;
	private String date;
	private int workers;
	private int levelRank;
	private int sum;
	private String description;
	private boolean ispublic;
	private double unitPrice;
	private int exp;
	private boolean isover;
	private double predictExpend;
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
	public int getNeedWorkers() {
		return needWorkers;
	}
	public void setNeedWorkers(int needWorkers) {
		this.needWorkers = needWorkers;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getWorkers() {
		return workers;
	}
	public void setWorker(int workers) {
		this.workers = workers;
	}
	public int getLevelRank() {
		return levelRank;
	}
	public void setLevelRank(int levelRank) {
		this.levelRank = levelRank;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIspublic() {
		return ispublic;
	}
	public void setIspublic(boolean ispublic) {
		this.ispublic = ispublic;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public boolean isIsover() {
		return isover;
	}
	public void setIsover(boolean isover) {
		this.isover = isover;
	}
	public DeveloperTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeveloperTask(String developer, String assignment,String taskType, int needWorkers,
			String date, int workers, int levelRank, int sum,
			String description, boolean ispublic, double unitPrice, int exp,
			boolean isover,double predictExpend) {
		super();
		this.developer = developer;
		this.assignment = assignment;
		this.taskType = taskType;
		this.needWorkers = needWorkers;
		this.date = date;
		this.workers = workers;
		this.levelRank = levelRank;
		this.sum = sum;
		this.description = description;
		this.ispublic = ispublic;
		this.unitPrice = unitPrice;
		this.exp = exp;
		this.isover = isover;
		this.predictExpend = predictExpend;
	}
	public double getPredictExpend() {
		return predictExpend;
	}
	public void setPredictExpend(double predictExpend) {
		this.predictExpend = predictExpend;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
}
