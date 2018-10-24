package com.zhu.dto;

import java.util.Map;
/**
 * @description:用于对返回给前端的数据包装对象，方便传输数据信息的数据传输对象
 * @create in 2018.08.23
 * 	by Unow
 * */
public class DataBody {
	private int code;
	private String msg;	
	private boolean isDeveloper;
	private boolean isLogin;
	private Map<String,Object> data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public boolean isDeveloper() {
		return isDeveloper;
	}
	public void setDeveloper(boolean isDeveloper) {
		this.isDeveloper = isDeveloper;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public DataBody() {

	}
	public DataBody(int code, String msg,
			boolean isDeveloper, boolean isLogin, Map<String, Object> data) {
		this.code = code;
		this.msg = msg;	
		this.isDeveloper = isDeveloper;
		this.isLogin = isLogin;
		this.data = data;
	}
	public DataBody(int code, String msg,
			 boolean isLogin, Map<String, Object> data) {
		this.code = code;
		this.msg = msg;	
		this.isLogin = isLogin;
		this.data = data;
	}
	


	
}
