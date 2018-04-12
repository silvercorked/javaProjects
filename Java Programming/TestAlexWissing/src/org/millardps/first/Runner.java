package org.millardps.first;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		// Print statements
		System.out.println("Hello World");
		System.out.print(5 + 5);
		System.out.println("5 + 5");
		System.out.print("5 + 5" + "= 10");
		System.out.println("5 + 5 =" + 10);
		System.out.println();
		System.out.println("She said, 'Hello.'");
		System.out.println("She said, \"Hello\".");
		System.out.println("End of Print Statements Section");
		
		System.out.println();
		System.out.println();
		
		//Variables
		String x = "Hello";
		String y = " World";
		System.out.println(x + y);
		String t;
		t="The previous statement declares the strings existance, but this defines it";
		System.out.println(t + " " + x + y);
			//the " " in the previous statement adds a space to the phrase
		
		System.out.println();
		System.out.println();
		
		//Method
		t= t.toLowerCase();
		System.out.println(t);
			//this redefines the variable t to be in lower case characters. This overwrites above code but will be overwritten by code below
		t= t.toUpperCase();
		System.out.println(t);
			//this redefines the variable t to be in lower case characters even when its been previously redefined
		
		System.out.println();
		System.out.println();
		
		//primitives
		int s = 456;            //whole numbers, does have a limit
		System.out.println(s);
		short g = 5;            // small numbers
		System.out.println(g);
		long f = 29039203;      //really big values
		System.out.println(f);
		byte r = 23;            //little bigger than short, has a point but not really
		System.out.println(r);
		double k = 7.75;        //numbers with decimals
		System.out.println(k);
		float u = 3.6f;         //acts similar to a double (doesn't print the 'f' but is necessary)
		System.out.println(u);
		char p = 'b';           //character (string is a lot of chars)
		System.out.println(p);
		boolean i = true;       //true or false
		System.out.println(i);
		
		char m = 'a';
		char n = 'd';
		System.out.println(p + m + n);
			//another example
		
		System.out.println();
		System.out.println();
		
		//Scanner use
		Scanner scanner2 = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);
			//declares a new object that is a scanner and accepts the argument system.in
		System.out.println("What is your name?");
			//a phrase that prompts the desired response
		String name = scanner.nextLine();
			//this take the next line as a string variable called name
		System.out.println("Hello " + name + "!");
			//this prints a message that uses both the variable name and hello
		
		System.out.println();
		
		System.out.println("What is your favorite color?");
			//prompts the console for info
		String color = scanner.nextLine();
			//grabs the response the the previous question
		color = color.toLowerCase();
		//Conditionals
		if(color.equals("red")){
			//equal is only for strings, numbers/ints require ==
			System.out.println("What a stupid color");
			System.out.println("Get gooder with colors, " + name + "!");
		}   //curly brace = ownership to an above thing (ie. the if statement)
		else if(color.equals("blue")){
			System.out.println("Good luck nerd");
			//notice that else if or else are not within the original if statement
		}
		System.out.println("How old are you?");
		int age = scanner2.nextInt();
			//nextLine = string. nextInt = int.
		System.out.println(age);
		if(age == 15){
			System.out.println("no");
			}
		else if(age <= 14){ //looks for ints less that 14
			System.out.println("go away");
		}
		else{
			System.out.println("you must be above 15, so u are bad");
		}
		Scanner scanner3 = new Scanner(System.in);
		System.out.println("What is your favorite pet?");
		String pet = scanner3.nextLine();
		pet= pet.toLowerCase();
		if(pet.equals("dog")){
			System.out.println("I hate dogs!!");
		}
		else if(pet.equals("cat")){
			System.out.println("I hate cats!!");
		}
		else if(pet.equals("i like any pets")){
			System.out.println("Why are you here; Leave!");
		}
		else{
			System.out.println("That's cool, I wish I had a " + pet + "!");
		}
		Scanner scanner4 = new Scanner(System.in);
		System.out.println("Do you have another favorite?");
		while(i = true){
			String pet2 = scanner4.nextLine();
			pet2 = pet2.toLowerCase();
			if(pet2.equals("rat")){
				System.out.println("What? That's weird.");
				Scanner scanner5 = new Scanner(System.in);
				System.out.println("Any others?");
				String pet3 = scanner5.nextLine();
				pet3 = pet3.toLowerCase();
				if(pet3.equals("yes")){
					System.out.println("kool!");
					System.out.println("What is it?");
				}
				else if(pet3 != "no"){
					System.out.println("that's ok");
					break;
					//kills while... can also do (i = false;) because the while loop is... while(i = true){... if it was... while(true){... then break is the only option
				}
				else{
					System.out.println("I hate " + pet3 + "!");
					System.out.println("Anything else?");
				}
			}
			else if(pet2.equals("no")){
				break;
			}
		}
		int count = 0;
		while(true){
			System.out.println("looping");
			count += 2; //can also use (x++; "which adds only 1") (x+=2; "makes x = 2 then 4 then 6") (x=x +4; "adds 4 each time x comes around")... (can also do x-=2;
			System.out.println(count);
			if(count >= 50){
				break;
			}
		}
		int count2 = 0;
		Scanner scanner6 =new Scanner(System.in);
		while(true){
			count2 +=3;
			int income = scanner6.nextInt();
			if(income<=20000 && income>0){
				System.out.println("That is below average");
				break;
			}
			else if(income>=20001 && income<=60000){
				System.out.println("That is about the normal range for income");
				break;
			}
			else if(income>60000 && income<=150000){
				System.out.println("Wow that's a lot of money!");
				break;
			}
			else{ //in progress (play with seperate loops and endings
				System.out.println("Wow you are rich");
				Scanner scanner7 =new Scanner(System.in);
				System.out.println("Do you want to be friends?");
				String friend = scanner7.nextLine();
				friend = friend.toLowerCase();
				if(friend.equals("yes")){
					Scanner scanner8 = new Scanner(System.in);
					System.out.println("Great; hey, now that we are friends, can I have some money?");
					String rich = scanner8.nextLine();
					rich = rich.toLowerCase();
					if(rich.equals("sure") || rich.equals("ok") || rich.equals("yes")){
						Scanner scanner9 = new Scanner(System.in);
						System.out.println("Sweet, how much can I have?");
						int amount = scanner9.nextInt();
						if(amount > 0 && amount < 100) {
							System.out.println("Wow, thanks!");
							break;
						}
						else if(amount >= 100 && amount < 10000){
							System.out.println("Wow, that's really generous! Thank you so much!");
							break;
						}
						else if(amount <= 0){
							System.out.println("I was thinking more of a positive number, like a hundred million :)");
						}
						else if(amount >= 10000){
							System.out.println("That's a bit too much; I would take a lower amount however.");
						}
					}
				}
				else if(friend.equals("no")){
					System.out.println("Oh, that's too bad, see ya!");
					break;
				}
				else{
					System.out.println("Answer yes or no plz.");
				}
			}
			
		}
		//Compound booleans
			//! is not the thing so != is not equal to and !&& is one of them must fail for code below to activate
			//&& is and... both sides must be true
			//|| is or so one or the other
			//look above for examples
		//for loops
			//look at "ForLoopPractice.java
			//int declares i a variable, i is the variable, i<5 shows the endpoint, i++ is the increment
			// this will loop 5 times
	}
}
