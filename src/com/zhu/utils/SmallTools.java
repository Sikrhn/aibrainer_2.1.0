package com.zhu.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.List;

import com.zhu.entity.RectData;


public class SmallTools {
	 public static double getTwoDecimal(double num) {
         DecimalFormat dFormat=new DecimalFormat("#.00");
         String yearString=dFormat.format(num);
         Double temp= Double.valueOf(yearString);
         return temp;
    }
	 
	public static void exportCsv(String path,String assignment,List<RectData> list){
		 BufferedWriter bw = null;
		 File csvFile =new File(path+"result"+File.separator+assignment+".csv");	
		 
		 try {
			csvFile.createNewFile();
			 //ÎÚ°àÍ¼ÏÂ¸ÄÎª utf-8
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(  
					csvFile), "GBK"), 1024);
			String[] head = {"data_name","width","height","data_label","xmin","ymin","xmax","ymax"};
			for(int i =0;i<head.length ;i++){
				bw.write(head[i]);
				if(i<head.length-1)
					bw.write(",");
			}
			bw.newLine();
			for(int i =0;i<list.size();i++){
				if(list.get(i).getDataLabel().equals("-1"))
					continue;
				bw.write(list.get(i).getDataName());
				bw.write(",");
				bw.write(list.get(i).getWidth()+"");
				bw.write(",");
				bw.write(list.get(i).getHeight()+"");
				bw.write(",");
				bw.write(list.get(i).getDataLabel());
				bw.write(",");
				bw.write(list.get(i).getXmin()+"");
				bw.write(",");
				bw.write(list.get(i).getYmin()+"");
				bw.write(",");
				bw.write(list.get(i).getXmax()+"");
				bw.write(",");
				bw.write(list.get(i).getYmax()+"");
				bw.newLine();
			}
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(bw!=null)
					bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
	 }

}
