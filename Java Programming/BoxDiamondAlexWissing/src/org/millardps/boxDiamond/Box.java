package org.millardps.boxDiamond;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Box {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true){
			int size = 0;
			while(true){
				try{
					System.out.println("How large of a box would you like to make?");
					size = scanner.nextInt();
					break;
				}
				catch(InputMismatchException e) {
					System.out.println("Please type only numbers");
					scanner.next();
					//clears scanners buffer. without this, the scanner still has the none-int response from the user and will continually trigger the catch and just loop infinitely
				}
			}
			//top of the box
			System.out.print("*");
			for(int i = 0; i < size; i++){
				System.out.print(" *");
			}
			System.out.println(" *");
			
			//middle of the box
			for(int t = 0; t < size; t++){
				System.out.print("*");
				for(int i = 0; i < size; i++){
					System.out.print("  ");
				}
				System.out.println(" *");
			}
			
			//bottom of the box
			System.out.print("*");
			for(int i = 0; i < size; i++){
				System.out.print(" *");
			}
			System.out.println(" *");
		}
		
	}
}
