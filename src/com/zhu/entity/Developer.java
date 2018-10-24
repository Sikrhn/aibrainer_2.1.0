package com.zhu.entity;

public class Developer {
	private String developer;
	private String password;
	private double balance;
	private String date;
	private String email;
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Developer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Developer(String developer, String password, double balance,
			String date, String email) {
		super();
		this.developer = developer;
		this.password = password;
		this.balance = balance;
		this.date = date;
		this.email = email;
	}
}
