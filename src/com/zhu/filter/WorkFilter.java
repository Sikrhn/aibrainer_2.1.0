package com.zhu.filter;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class WorkFilter implements Filter {
	
	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/ai_brainer?userUnicode=true&characterEncoding=utf-8";
	    String username = "root";
	    String password = "root";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	private static boolean ispublicWork(String developer,String assignment){
		Connection conn = getConn();
		String sql = "select ispublic from developer_task where developer = ? and assignment = ?";
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, developer);
			pstmt.setString(2, assignment);
			rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return false;		
	}
	
	private static boolean selectObj(String username,String developer,String assignment){
		Connection conn = getConn();
		String sql = "select username from user_task where username = ? and developer = ? and assignment = ?";
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, developer);
			pstmt.setString(3, assignment);
			rs = pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return false;
		
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		String username = (String) request.getSession().getAttribute("username");
		String developer = request.getParameter("developer");
		String assignment = request.getParameter("assignment");
		System.out.println(username+developer+assignment);
		boolean flag = false;
		boolean ispublic = ispublicWork(developer,assignment);
		if(ispublic){
			arg2.doFilter(arg0, arg1);
		}else{
			if(username!=null){
				flag = selectObj(username,developer,assignment);
			}
		}		
		if(flag){
			arg2.doFilter(arg0, arg1);
		}else{
			HttpServletResponse repsonse = (HttpServletResponse)arg1;
			repsonse.sendRedirect("http://localhost:8080/aibrainer_2.1.0/");
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
