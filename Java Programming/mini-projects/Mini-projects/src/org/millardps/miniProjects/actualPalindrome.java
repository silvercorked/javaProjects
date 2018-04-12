package org.millardps.miniProjects;

import java.util.Scanner;

public class actualPalindrome {
	public actualPalindrome(){
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Give me a palindrome!");
		String input = scan1.nextLine();
		boolean output = checkPalindrome(input);
		if(output == true){
			System.out.println("That is a palindrome");
		}
		else{
			System.out.println("That isn't a Palindrome");
		}
	}
	public boolean checkPalindrome(String x){
		boolean first = false;
		boolean second = false;
		boolean third = false;
		boolean fourth = false;
		x.replace(" ", "");
		x.replace("!", "");
		x.replace(".", "");
		x.replace("?", "");
		x.replace("\"", "");
		x.replace(",", "");
		int length = x.length();
		System.out.println(length);
		char[] letters = x.toCharArray();
		System.out.println(letters);
		if(letters[0] == letters[length-1]){
			first = true;
		}
		if(length >= 4){
			if(letters[1] == letters[length-2]){
				second = true;
			}
			if(length >= 6){
				if(letters[2] == letters[length-3]){
					third = true;
				}
				if(length >= 8){
					if(letters[3] == letters[length-4]){
						fourth = true;
					}
				}
			}
		}
		if(length < 4 && first == true){
			return true;
		}
		else if(length >=4 && length <6 && first == true && second == true){
			return true;
		}
		else if(length >=6 && length <8 && first == true && second == true && third == true){
			return true;
		}
		else if(length >= 8 && first == true && second == true && third == true && fourth == true){
			return true;
		}
		else{
			return false;
		}
	}
}