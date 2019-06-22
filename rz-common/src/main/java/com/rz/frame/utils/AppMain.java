package com.rz.frame.utils;

public class AppMain {
	public static void main(String[] args) {
		int i=17;
		int j=5;
		int sum=i+j;
		System.out.println(add(i,j));
	}
	
	
	static int add(int i, int j){
		if(j == 0)
			return i;
		int sum = i ^ j;//得到个位相加
		int carry = (i & j) << 1;//得到进位相加
		return add(sum, carry);
	}
	
}
