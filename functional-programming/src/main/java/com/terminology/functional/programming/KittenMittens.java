package com.terminology.functional.programming;

public class KittenMittens {

	public static void main(String[] args) {
		
		System.out.println(kittenMittens(5));
		System.out.println(6%2);
		System.out.println(6/2);

	}
	
	public static int kittenMittens(int count){
		int noOfMittens = 0;
		for(int i=1;i<=count;i++){
			if(i%2==0){
				noOfMittens+=3;
			}else{
				noOfMittens+=4;
			}
		}
		return noOfMittens;
	}

}
