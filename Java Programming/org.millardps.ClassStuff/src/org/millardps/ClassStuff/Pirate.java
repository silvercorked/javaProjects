package org.millardps.ClassStuff;

import java.util.Scanner;

public class Pirate {
	//Attributes - the parts of an object
	private String name;
	private int limbs;
	private boolean alive;
	Scanner yy = new Scanner(System.in);
	
	//Constructor - Making the instance of an object
	public Pirate(String n){
		limbs = 4;
		name = n;
		alive = true;
		
	}
	
	//Method - What an object does
	public String printName(){
		System.out.println("What be this Pirate's name?");
		String n = yy.nextLine();
		System.out.println("I be " + n + ".");
		return n;
	}
	
	public String getName(){
		return this.name;
	}
	public int getLimbs(){
		return this.limbs;
	}
	public void loseALimb(){
		if(this.limbs>0){
			limbs --;
			System.out.println("Argggg me limb! T'iz Lost!");
		}
		if(this.limbs == 0){
			this.alive = false;
		}
	}
	public boolean getAlive(){
		return this.alive;
	}
}
