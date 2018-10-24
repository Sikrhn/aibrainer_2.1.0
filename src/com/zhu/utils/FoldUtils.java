package com.zhu.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * @description:用于对开发者在服务器文件夹的操作
 * @function:创建文件夹，直接创建到用户目录下的任务
 * @Create in 2018.08.23
 * by Unow
 * */
public class FoldUtils {
	public static void createLabelFold(String path){
		File dir = new File(path);
		if(!dir.exists())
			dir.mkdirs();		
	}
	
	public static String createLabelFoldOneWay(String developer,String assignment,String realPath){
		FoldUtils.createLabelFold(realPath);
		realPath += File.separator+developer;
		FoldUtils.createLabelFold(realPath);
		realPath += File.separator+assignment;
		FoldUtils.createLabelFold(realPath);
		return realPath;
	}
	
	public static void moveFile(String ordPath,String fileName,String fileLabel){
		String dataPath = ordPath+File.separator+"data";
		String filePath = dataPath+File.separator+fileName;
		String destPath = ordPath+File.separator+fileLabel+File.separator+fileName;
		File file = new File(filePath);			
		file.renameTo(new File(destPath));			
				
	}

	public static void compress(File f, String baseDir, ZipOutputStream zos,boolean todelete){
		if(!f.exists()){
			System.out.println("待压缩的文件目录或文件"+f.getName()+"不存在");
			return;
		}
		File[] fs = f.listFiles();
		BufferedInputStream bis = null;   
		byte[] bufs = new byte[1024*10];
		FileInputStream fis = null;	
		try{
			for(int i=0; i<fs.length; i++){
				String fName =  fs[i].getName();   
				if(fs[i].isFile()){
					ZipEntry zipEntry = new ZipEntry(baseDir+fName);
					zos.putNextEntry(zipEntry);	
					//读取待压缩的文件并写进压缩包里
					fis = new FileInputStream(fs[i]);
					bis = new BufferedInputStream(fis, 1024*10);
					int read = 0;
					while((read=bis.read(bufs, 0, 1024*10))!=-1){
						zos.write(bufs, 0, read);
					}
					//如果需要删除源文件，则需要执行下面2句
					if(todelete){
						fis.close();
						fs[i].delete();
					}
				
				}
				
				else if(fs[i].isDirectory()){
					compress(fs[i], baseDir+fName+"/", zos,todelete);
				}
			}//end for
			if(f.exists()){
				f.delete();
			}
		}catch  (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//关闭流
			try {
				if(null!=bis)
					bis.close();
				//if(null!=zos)
					//zos.close();
				if(null!=fis)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}
	
	public static void zipDir(boolean isPublic,String sourceFilePath){		
		File sourceDir = new File(sourceFilePath);
		File zipFile = new File(sourceFilePath+".zip");
		ZipOutputStream zos = null;
		try {
			 zos = new ZipOutputStream(new FileOutputStream(zipFile));
			 String baseDir = sourceFilePath.substring(sourceFilePath.lastIndexOf(File.separator)+1)+File.separator;
			 compress(sourceDir, baseDir, zos,isPublic);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(zos!=null)
				try {
					zos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static void delFolder(String folderPath) {
	     try {
	        delAllFile(folderPath); //删除完里面所有内容
	        String filePath = folderPath;
	        filePath = filePath.toString();
	        File myFilePath = new File(filePath);
	        myFilePath.delete(); //删除空文件夹
	     } catch (Exception e) {
	       e.printStackTrace(); 
	     }
	}
	public static boolean delAllFile(String path) {	
	       boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path +  File.separator  + tempList[i]);//先删除文件夹里面的文件
	             delFolder(path + File.separator + tempList[i]);//再删除空文件夹
	             flag = true;
	          }
	       }
	       return flag;
	}
}

