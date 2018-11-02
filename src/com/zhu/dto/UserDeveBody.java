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
	private String mob;
	private String identity;
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
	}
	public UserDeveBody(String username, String password, boolean isdeveloper) {
		super();
		this.username = username;
		this.password = password;
		this.isdeveloper = isdeveloper;
	}
	
	public UserDeveBody(String username, String password, boolean isdeveloper,
			String email, String mob, String identity) {
		super();
		this.username = username;
		this.password = password;
		this.isdeveloper = isdeveloper;
		this.email = email;
		this.mob = mob;
		this.identity = identity;
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
	
}
