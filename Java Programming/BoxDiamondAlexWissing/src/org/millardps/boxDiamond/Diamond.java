package org.millardps.boxDiamond;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Diamond {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true){
			int size = 0;
			while(true){
				try{
					System.out.println("How large of a diamond would you like to make?");
					size = scanner.nextInt();
					break;
				}
				catch(InputMismatchException e) {
					System.out.println("Please type only numbers");
					scanner.next();
					//clears scanners buffer. without this, the scanner still has the none-int response from the user and will continually trigger the catch and just loop infinitely
				}
			}
			//top cap of the diamond
			for(int i = 0; i < size + 1; i++){
				System.out.print(" ");
			}
			System.out.println("^");
			
			//top portion of the diamond
			for(int i = 0; i < size; i++){
				for(int t = 0; t < size - i; t++){
					System.out.print(" ");
				}
				System.out.print("/");
				
				for(int t = size; t < size + i; t++){
					System.out.print(" ");
				}
				System.out.print("|");
				for(int t = size; t < size + i; t++){
					System.out.print(" ");
				}
				System.out.println("\\");
			}
			//middle of the diamond
			System.out.print("<");
			for(int i = 0; i < size; i++){
				System.out.print("-");
			}
			System.out.print("+");
			for(int i = 0; i < size; i++){
				System.out.print("-");
			}
			System.out.println(">");
			//bottom portion of the diamond
			for(int i = 0; i < size; i++){
				for(int t = size; t < size + i; t++){
					System.out.print(" ");
				}
				System.out.print(" \\");
				
				for(int t = 0; t < ((size - i))-1; t++){
					System.out.print(" ");
				}
				System.out.print("|");
				for(int t = 0; t < ((size - i))-1; t++){
					System.out.print(" ");
				}
				System.out.println("/");
			}
			//bottom cap of the diamond
			for(int i = 0; i < size + 1; i++){
				System.out.print(" ");
			}
			System.out.println("V");
		}
		
	}

}
