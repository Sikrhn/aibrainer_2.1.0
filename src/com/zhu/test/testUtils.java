package com.zhu.test;

import java.util.ArrayList;
import java.util.List;

import com.zhu.entity.UserLabel;
import com.zhu.utils.FileSpillException;

public class testUtils {
	static boolean [][] areFirend={{true,true,false,false},{true,true,true,true},{false,true,true,true},{false,true,true,true}};
	static boolean [] taken = {false,false,false,false};
	static int n = 4;
	public static void main(String[] args) {
		
		int a = c(2);
		System.out.println(a);
	}
	public static int c(int n){
		if(n==2){
			throw new FileSpillException("error");
		}
		return n;
		
	}
	public static int coutPairing(boolean[] taken){
		int firstFree = -1;
		for(int i =0;i<taken.length;i++){
			if(!taken[i]){
				firstFree = i;
				System.out.println(i);
				break;
			}
		}
		
		if(firstFree == -1)
			return 1;
		int reg = 0;
		for(int i=firstFree+1;i<n;i++){
			if(areFirend[firstFree][i]&&!taken[i]){
				taken[i]=taken[firstFree]=true;
				reg+=coutPairing(taken);
				taken[i]=taken[firstFree]=false;
			}
		}
		
		return reg;
		
	}
	
	public static String isExist(String developer ,String assignment,int i){
		if(i<4){
			if(i>1)
				assignment = assignment.substring(0, assignment.length()-3) +"("+i+")";
			else
				assignment = assignment +"("+i+")";	
			
			return isExist(developer, assignment,++i);			
		}
		else{
			return assignment;
		}
		
		
	}
	public static int test2(int i){
		
		if(i<2){
			test2(++i);
		}
		return i;
	}
	public static String matchLabel(List<UserLabel> list,List<UserLabel> packList,int index){
		if(index == list.size()) 
			return packList.get(0).getDataLabel();
		if(packList.contains(list.get(index))==true) 
			return matchLabel(list,packList,++index);
		List<UserLabel> newPackList = new ArrayList<UserLabel>();
		newPackList.add(list.get(index));
		for(int i = 0;i < list.size();i++){
			UserLabel item = list.get(i);
			if(packList.contains(item))
				continue;
			if(list.get(index).getDataLabel().equals(list.get(i).getDataLabel()))
				newPackList.add(item);
		}
		if(newPackList.size()>(list.size()/2))
			return newPackList.get(0).getDataLabel();		
		return packList.size()>newPackList.size()?matchLabel(list,packList,++index):matchLabel(list,newPackList,++index);
	}

}
