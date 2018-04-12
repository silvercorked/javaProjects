package org.millardps.ClassStuff;

import java.util.Random;
import java.util.Scanner;

public class Wizard {
	//Attributes
	private int mana;
	private String name;
	private Scanner xx = new Scanner(System.in);
	//Scanner doesnt like being assigned in the constructor so we have to do it here in attributes
	private Random rand;
	
	//Constructor
	public Wizard(){
		name = setName();
		rand = new Random();
		mana = 10 + rand.nextInt(90);
	}
	
	//Method
	/**
	 * Sets the Wizard's object name String to user input
	 * @return String for the name
	 */
	public String setName(){
		System.out.println("What is this wizard's name?");
		String n = xx.nextLine();
		System.out.println("This wizard is now named " + n + ".");
		return n;
	}
	
	public boolean castASpell(Pirate p){
		if(this.mana > 10){
			mana -= 10;
			System.out.println("What spell would you like to cast? [fireball, summon]");
			String spell = xx.nextLine().toLowerCase();
			if(spell.contains("fireball")){
				System.out.println(p.getName() + " has been attacked by " + this.name + "!");
				p.loseALimb();
				p.loseALimb();
				return false;
			}
			else if(spell.contains("summon")){
				System.out.println(this.name + " has summoned a Ninja.");
				return true;
			}
			else{
				mana += 10;
				System.out.println("Not a command!");
				return false;
			}
		}
		else{
			System.out.println("Out of mana!");
			return false;
		}
	}
	
	
	
}
