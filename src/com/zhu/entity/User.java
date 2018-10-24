package com.zhu.entity;

public class User {
	private String username;
	private String password;
	private int rank;
	private double balance;
	private double accuracy;
	private String date;
	private int exp;
	private String email;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, int rank, double balance,
			double accuracy, String date, int exp, String email) {
		super();
		this.username = username;
		this.password = password;
		this.rank = rank;
		this.balance = balance;
		this.accuracy = accuracy;
		this.date = date;
		this.exp = exp;
		this.email = email;
	}
	public User(String username, String password, int rank, double balance,
			double accuracy, String date, int exp) {
		super();
		this.username = username;
		this.password = password;
		this.rank = rank;
		this.balance = balance;
		this.accuracy = accuracy;
		this.date = date;
		this.exp = exp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
