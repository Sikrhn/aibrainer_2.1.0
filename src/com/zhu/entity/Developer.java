package com.zhu.entity;

public class Developer {
	private String developer;
	private String password;
	private double balance;
	private String date;
	private String email;
	private String identity;
	private String mob;
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
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public Developer(String developer, String password, double balance,
			String date, String email, String identity, String mob) {
		super();
		this.developer = developer;
		this.password = password;
		this.balance = balance;
		this.date = date;
		this.email = email;
		this.identity = identity;
		this.mob = mob;
	}
	
}
