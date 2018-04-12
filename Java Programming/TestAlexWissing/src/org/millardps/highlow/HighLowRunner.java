package org.millardps.highlow;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class HighLowRunner {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		int rNum = random.nextInt(50);
		int count = 0;
		while(count < 3){
			try{
				System.out.println("Pick a number between 1 and 50");
				int num = scanner.nextInt();
				if(num == rNum){
					System.out.println("Congradulations! That's correct! The number was " + rNum);
					Scanner scanner1 = new Scanner(System.in);
					System.out.println("Do you want to play again?");
					String playagain = scanner1.nextLine().toLowerCase();
						//yes those can be combined. Don't feel bad I didn't know either
					if(playagain.equals("yes")){
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println("Get ready!");
						System.out.println();
					}
					else{
						System.out.println("Goodbye, Play again soon!");
						count = 3;
					}
					
				}
				else if(num < rNum && num > 0 && count < 3){
					System.out.println("Close, but its higher than " + rNum);
					count++;
					System.out.println("You have tried " + count + ", at 3 tries you lose!");
					System.out.println();
				}
				else if(num > rNum && num < 50 && count < 3){
					System.out.println("Close, but its lower than " + rNum);
					count++;
					System.out.println("You have tried " + count + ", at 3 tries you lose!");
					System.out.println();
				}
				else if((num > 50 || num < 0) && count < 3){
					System.out.println("Guess within 1 and 50.");
					count++;
					System.out.println("You have tried " + count + ", at 3 tries you lose!");
					System.out.println();
				}
				else{
					System.out.println("You did it wrong!");
					count++;
					System.out.println("You have tried " + count + ", at 3 tries you lose!");
					System.out.println();
				}
			
			}
			catch(InputMismatchException e){
				System.out.println("Please type only numbers.");
				scanner.next();
			}
			if(count >= 3){
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("You Lose!");
			}
		}
		scanner.close();
	}

}
