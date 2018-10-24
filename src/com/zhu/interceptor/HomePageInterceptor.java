package com.zhu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HomePageInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		Boolean isDeveloper = (Boolean) request.getSession().getAttribute("isdeveloper");
		System.out.println();
		if(isDeveloper!=null){
			if(isDeveloper){
				if(request.getSession().getAttribute("developer")!=null||request.getSession().getAttribute("username")!=null)					
					return true;
			}
		}
		System.out.println("ÎÒÔÚ×÷¹Ö"+request.getContextPath());
		response.sendRedirect("/");
		return false;
	}
	
}
