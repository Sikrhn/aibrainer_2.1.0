package com.zhu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhu.entity.RectData;

public interface RectDataDao {
	List<String> getRectDataFirst(@Param("developer")String developer,@Param("assignment")String assignment);
	List<String> getRectDataSecond(@Param("developer")String developer,@Param("assignment")String assignment);
	void markedDtData(@Param("developer")String developer,@Param("assignment")String assignment,@Param("dataName")String dataName);
	void insertMarkerData(@Param("rectData")RectData rectData,@Param("username")String username);
	void deleteDataByUsername(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	List<RectData> getRandomFiveLabelByUser(@Param("username")String username,@Param("developer")String developer,@Param("assignment")String assignment);
	List<RectData> getLabelList(@Param("developer")String developer,@Param("assignment")String assignment);
	List<RectData> getRandomBySatistic(@Param("developer")String developer,@Param("assignment")String assignment);
	void deleteAllByAssignment(@Param("developer")String developer,@Param("assignment")String assignment);
}
