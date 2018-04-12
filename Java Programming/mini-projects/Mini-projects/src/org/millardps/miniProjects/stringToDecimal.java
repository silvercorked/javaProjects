package org.millardps.miniProjects;

import java.util.Scanner;

public class stringToDecimal {
	public stringToDecimal(){
		changer(getString());
		
	}
	
	public String getString(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Giff meh a string!");
		String words = scan.nextLine();
		scan.close();
		return words;
	}
	public void changer(String xx){
		char[] arraylist = xx.toCharArray();
		byte changed;
		for(char x : arraylist){
			changed = (byte)x;
			System.out.println(changed);
			System.out.println(x);
		}
	}
}
