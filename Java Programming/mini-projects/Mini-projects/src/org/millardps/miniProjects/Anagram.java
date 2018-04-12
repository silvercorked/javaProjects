package org.millardps.miniProjects;

import java.util.Scanner;
import java.util.Arrays;

public class Anagram {
	private boolean works;
	public Anagram(){
		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Give me the first part of the anagram!");
		String first = scan1.nextLine();
		System.out.println("Give me the second part of the anagram!");
		String second = scan2.nextLine();
		boolean answer = checkAnagram(first, second);
		if(answer == true){
			System.out.println("Thats an anagram.");
		}
		else{
			System.out.println("That isnt an anagram");
		}
	}
	public boolean checkAnagram(String x, String xx){
		x.replace(" ", "");
		xx.replace(" ", "");
		char[] first = x.toCharArray();
		char[] second = xx.toCharArray();
		Arrays.sort(first);
		Arrays.sort(second);
		if(Arrays.equals(first, second)){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
}
