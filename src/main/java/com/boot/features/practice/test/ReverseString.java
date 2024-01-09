package com.boot.features.practice.test;

import java.util.List;
import java.util.stream.Collectors;

public class ReverseString {

	public static void main(String[] args) {

		String sentence = "my name is chitrarth";

		String[] str = sentence.split(" ");

		String reverseStr = "";

		String[] rev = new String[str.length];

		for (int i = 0; i <= str.length - 1; i++) {

			String[] s = str[i].split("");

			for (int j = str[i].length() - 1; j >= 0; j--) {

//				reverseStr = reverseStr + s[j];

				if (!(j < 0)) {

					// rev[i] = "";
					if(rev[i] == null) {
						rev[i] = "";
					}
					rev[i] =  rev[i]  + s[j];

				}

			}

		}

		for(int i = 0; i < rev.length; i++) {
			System.out.print(rev[i] + " ");
		}

		
		List<Integer> list = List.of(1,2,3,4,5,54,64,456);
		System.out.println("result is " );
		System.out.println(list.stream().filter(n->(n %2 != 0)).anyMatch(n->(n %2 != 0)));
	}

}