package com.zhu.entity;

public class UserLabel {
	private String username;
	private String developer;
	private String assignment;
	private String dataName;
	private String dataLabel;
	private boolean isAdopt;
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
	public UserLabel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserLabel(String username, String developer, String assignment,
			String dataName, String dataLabel) {
		super();
		this.username = username;
		this.developer = developer;
		this.assignment = assignment;
		this.dataName = dataName;
		this.dataLabel = dataLabel;
	}
	public boolean isAdopt() {
		return isAdopt;
	}
	public void setAdopt(boolean isAdopt) {
		this.isAdopt = isAdopt;
	}
	
}
