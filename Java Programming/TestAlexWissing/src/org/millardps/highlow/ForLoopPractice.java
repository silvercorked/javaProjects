package org.millardps.highlow;

import java.util.Random;

public class ForLoopPractice {

	public static void main(String[] args) {
		String test = "This is a test";
		//strings are 0th indedexed meaning that the capitol "T" is the 0th index and the "h" is the first index or character
		//The length of this string is 13 characters, but because it is 0th indexed it is actaully 14
		
		for(int i = 0; i < test.length(); i++){
			System.out.println(test.charAt(i));
		}
		for(int i = 0; i < test.length(); i++){
			for(int l = 0; l < test.length(); l++){
				System.out.print(test.charAt(l));
			}
			System.out.println(test.charAt(i));
			//this is an attempt at a "nested for loop. it didn't work
			
		}
		Random rand = new Random();
		for(int i = 0; i < 50; i++){
			int ran = rand.nextInt(100);
			System.out.println(ran);
		}
		int x = 10;
		for(int t = 0; t < x; t++){
			for(int i = 0; i < x - t; i++){
				//this will increase the amount of things put out by 1 each time
				System.out.print("*");
		}
			System.out.println("");
		}
		for(int t = 0; t < x; t++){
			for(int i = 0; i < x + t; i++){
				//this will increase the amount of things put out by 1 each time
				System.out.print("*");
		}	
			System.out.println("");
		}
		
	}
}
