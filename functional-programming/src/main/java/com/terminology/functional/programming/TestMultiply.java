package com.terminology.functional.programming;

import java.util.ArrayList;
import java.util.List;

public class TestMultiply {

	public static void main(String args[]){
		String[] array = testMultiply(10, 15);
		for(String s:array){
			System.out.println(s);
		}
	}
	
	public static String[] testMultiply(int start,int end){
		List<String> list = new ArrayList<String>();
		for(int i=start;i<=end;i++){
			if(i%3==0 && i%5==0){
				list.add("Water");
			}else if(i%3==0){
				list.add("Fire");
			}else if(i%5==0){
				list.add("Ice");
			}else{
				list.add(String.valueOf(i));
			}
		}
		String[] s = list.toArray(new String[list.size()]);
		return s;
	}
}
