package com.zhu.dto;

//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.web.multipart.MultipartFile;
/**
 * @description:接收添加开发者任务的参数，方便传输数据信息的数据传输对象
 * @create in 2018.08.23
 * 	by Unow
 * */
public class AddTaskBody {
	private String assignment;
	private String description;
	private String taskType;
	private boolean isPublic;
	private int levelRank;
	private int needWorkers;
	private double unitPrice;
	private double predictExpend;

	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public int getLevelRank() {
		return levelRank;
	}
	public void setLevelRank(int levelRank) {
		this.levelRank = levelRank;
	}
	public int getNeedWorkers() {
		return needWorkers;
	}
	public void setNeedWorkers(int needWorkers) {
		this.needWorkers = needWorkers;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
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
	public String toString() {
		return "AddTaskBody [assignment=" + assignment + ", description="
				+ description + ", taskType=" + taskType + ", isPublic="
				+ isPublic + ", levelRank=" + levelRank + ", needWorkers="
				+ needWorkers + ", unitPrice=" + unitPrice + ", predictExpend="
				+ predictExpend + "]";
	}
	
}
