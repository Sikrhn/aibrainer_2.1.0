package com.zhu.dto;

/**
 * @description 用于简单包装开发者任务的某些属性
 * @Create in 2018.09.01
 * by Unow
 * */
public class TaskBody {
	private String assignment;
	private String developer;
	private boolean isover;	
	private boolean ispublic;
	private int sum;
	private int needWorkers;
	private int workers;
	private String taskType;
	public int getNeedWorkers() {
		return needWorkers;
	}
	public void setNeedWorkers(int needWorkers) {
		this.needWorkers = needWorkers;
	}
	public int getWorkers() {
		return workers;
	}
	public void setWorkers(int workers) {
		this.workers = workers;
	}
	public boolean isIsover() {
		return isover;
	}
	public void setIsover(boolean isover) {
		this.isover = isover;
	}
	public boolean isIspublic() {
		return ispublic;
	}
	public void setIspublic(boolean ispublic) {
		this.ispublic = ispublic;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public TaskBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskBody(String assignment, String developer, boolean isover,
			boolean ispublic, int sum, int needWorkers, int workers,
			String taskType) {
		super();
		this.assignment = assignment;
		this.developer = developer;
		this.isover = isover;
		this.ispublic = ispublic;
		this.sum = sum;
		this.needWorkers = needWorkers;
		this.workers = workers;
		this.taskType = taskType;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
}
