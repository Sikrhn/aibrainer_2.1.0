package com.zhu.web;

import java.util.Map;

import org.springframework.util.AntPathMatcher;

public class SpringWebConfig extends AntPathMatcher  {

	  @Override  
	    protected boolean doMatch(String pattern, String path, boolean fullMatch, Map<String, String> uriTemplateVariables) {  
	        System.err.println(pattern + " -- " + path);  
	        return super.doMatch(pattern.toLowerCase(), path.toLowerCase(), fullMatch, uriTemplateVariables);  
	    }  
}