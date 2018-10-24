package com.zhu.entity;

public class TaskClasses {
	private String developer;
	private String assignment;
	private String labelClasses;
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
	public String getLabelClasses() {
		return labelClasses;
	}
	public void setLabelClasses(String labelClasses) {
		this.labelClasses = labelClasses;
	}
	public TaskClasses() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaskClasses(String developer, String assignment, String labelClasses) {
		super();
		this.developer = developer;
		this.assignment = assignment;
		this.labelClasses = labelClasses;
	}
	
}
