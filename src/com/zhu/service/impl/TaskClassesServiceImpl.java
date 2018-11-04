package com.zhu.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhu.dao.TaskClassesDao;
import com.zhu.entity.TaskClasses;
import com.zhu.service.TaskClassesService;
import com.zhu.utils.FoldUtils;
@Service
public class TaskClassesServiceImpl implements TaskClassesService{
	@Autowired
	private TaskClassesDao tcdao;
	
	public List<String> getLabels(String developer, String assignment) {
		return tcdao.getLabel(developer, assignment);
	}

	public void addLabels(String[] labels,String developer, String assignment,String filePath,String taskType) {
		for(String label:labels){
			String labelPath = filePath+File.separator+label;
			System.out.println(labelPath);
			FoldUtils.createLabelFold(labelPath);
			tcdao.insertLabel(new TaskClasses(developer,assignment,label));
		}
	}

	public void deleteLabels(String developer, String assignment) {		
		tcdao.deleteLabels(developer, assignment);
	}
	
}
