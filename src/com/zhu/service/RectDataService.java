package com.zhu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhu.dto.RectDataTransport;

public interface RectDataService {
	List<String> getRectDataFirst(HttpServletRequest request);
	List<String> getRectDataSecond(HttpServletRequest request);
	void insertMarkerDatas(List<RectDataTransport> rectDatas,String username);
}
