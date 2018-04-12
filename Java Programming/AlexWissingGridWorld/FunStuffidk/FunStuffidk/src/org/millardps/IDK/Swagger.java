package org.millardps.IDK;

import java.util.Scanner;

public class Swagger {
	//attributes
	private int hp;
	private int gold;
	private String gender;
	
	//constructor
	public Swagger(){
		hp = 100;
		gold = 0;
		gender = getGender();
	}
	//method
	/*
	 * gets the character's gender
	 */
	public String getGender(){
		while(true){
			Scanner scan1 = new Scanner(System.in);
			System.out.println("What gender are you?");
			String gend = scan1.nextLine().toLowerCase();
			if(gend.equals("male") && gend.equals("female")){
				return gend;
			}
		}
	}
	public void adventure(){
		System.out.println("You walk down a path.");
	}
}
