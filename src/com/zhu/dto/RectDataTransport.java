package com.zhu.dto;

import java.util.List;

import com.zhu.entity.RectData;

public class RectDataTransport {
	private String dataName;
	private List<RectData> rectDatas;

	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public List<RectData> getRectDatas() {
		return rectDatas;
	}
	public void setRectDatas(List<RectData> rectDatas) {
		this.rectDatas = rectDatas;
	}
	
}
