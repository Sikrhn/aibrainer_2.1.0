package com.zhu.dto;

/**
 * @description:在开发者或普通用户登录注册时，对其包装简化
 * @create in 2018.08.23
 * 	by Unow
 * */
public class UserDeveBody {
	private String username;
	private String password;
	private boolean isdeveloper;
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
	public boolean isIsdeveloper() {
		return isdeveloper;
	}
	public void setIsdeveloper(boolean isdeveloper) {
		this.isdeveloper = isdeveloper;
	}
	public UserDeveBody() {
		super();
	}
	public UserDeveBody(String username, String password, boolean isdeveloper) {
		super();
		this.username = username;
		this.password = password;
		this.isdeveloper = isdeveloper;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
