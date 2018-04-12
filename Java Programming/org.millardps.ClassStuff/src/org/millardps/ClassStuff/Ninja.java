package org.millardps.ClassStuff;

import java.util.Random;
import java.util.Scanner;

public class Ninja {
	//Attributes
	private String name;
	private int stars;
	private Random rand;
	private Scanner xx = new Scanner(System.in);
	
	//Constructor
	public Ninja(){
		stars = 7;
		name = setName();
		rand = new Random();
	}
	
	//Method
	/**
	 * Sets the Ninja object name String to user input
	 * @return String for the name
	 */
	public String setName(){
		System.out.println("What is this ninja's name?");
		String n = xx.nextLine();
		System.out.println("This ninja is now named " + n + ".");
		return n;
	}
	/**
	 * get the ninja's current amount of stars
	 * @return int stars
	 */
	public int getStars(){
		return this.stars;
	}
	/**
	 * throws a star, if the ninja has stars, at a pirate. 
	 * Random chance to hit the pirate
	 * @param p Pirate object as a target
	 */
	public void throwStar(Pirate p){
		if(this.stars > 0){
			System.out.println(this.name + " has thrown a star at " + p.getName() + ".");
			this.stars--;
			if(rand.nextBoolean()){
				//nextBoolean calls for a random boolean thanks to the Random function. this can be true or false so its 50/50
				p.loseALimb();
			}
			else{
				System.out.println("Miss.");
				
			}
		}
		else{
			System.out.println(this.name + " is out of stars!");
		}
	}
	/**
	 * gets the ninja's name
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}
}
