package com.zhu.entity;

public class DeveloperData {
	private String developer;
	private String assignment;
	private String dataName;
	private String dataLabel;
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
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getDataLabel() {
		return dataLabel;
	}
	public void setDataLabel(String dataLabel) {
		this.dataLabel = dataLabel;
	}
	public DeveloperData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeveloperData(String developer, String assignment, String dataName,
			String dataLabel) {
		super();
		this.developer = developer;
		this.assignment = assignment;
		this.dataName = dataName;
		this.dataLabel = dataLabel;
	}
	
}
