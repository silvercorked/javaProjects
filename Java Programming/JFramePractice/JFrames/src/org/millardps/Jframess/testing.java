package org.millardps.Jframess;

import java.util.Scanner;

public class testing {
	public testing(){
		thing();
	}
	public int thing(){
		int k = 0;
		int prod = 1;
		
		while(k >= 0){
			Scanner scan = new Scanner(System.in);
			System.out.println("enter a number: " );
			k = scan.nextInt();
			prod = prod * k;
		}
		return prod;
	}
}
