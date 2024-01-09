package com.boot.features.practice;

public class ReverseString {
	
	public static void main(String[] args) {

		String sentence = "my name is chitrarth";
		String[] str = sentence.split(" ");
		String reverseStr = "";
		for(int i = 0; i <= str.length - 1; i++) {
			String[] s = str[i].split("");
			
			for(int j = str[i].length() - 1; j >= 0; j--) {
//				reverseStr = reverseStr + s[j]; 
				str[i] = s[j];
				if(j != 0) {
					str[i] = s[j];
				}
			}
			reverseStr = reverseStr + " ";
		}
		for(String s : str) {
			System.out.println(s);
		}
	}
}
