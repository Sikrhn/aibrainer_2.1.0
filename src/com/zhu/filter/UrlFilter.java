package com.zhu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description :过滤访问需要登录的Url
 * @Create by Unow
 * in 2018.09.13
 * 
 */

public class UrlFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("url过滤器销毁！");
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		Boolean isDeveloper = (Boolean) request.getSession().getAttribute("isdeveloper");
		if(isDeveloper!=null){
			if(isDeveloper){
				String developer = (String) request.getSession().getAttribute("developer");
				if(developer!=null)
					arg2.doFilter(arg0, arg1);
				else
					response.sendRedirect(request.getContextPath());
			}else{
				String developer = (String) request.getSession().getAttribute("username");
				if(developer!=null)
					arg2.doFilter(arg0, arg1);
				else
					response.sendRedirect(request.getContextPath());
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("url过滤器启动！");
	}
	
}
