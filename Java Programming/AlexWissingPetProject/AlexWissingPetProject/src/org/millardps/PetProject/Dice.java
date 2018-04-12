package org.millardps.PetProject;

import java.util.Random;

public class Dice {
	//attributes
	private Random rand;
	private int value;
	private int chance;
	private String chanceVal;
	
	//constructor
	public Dice(){
		chance = 1;
		value = 0;
		rand = new Random();
	}
	
	//methods
	/**
	 * rolls the dice
	 * @return if the roll is successful
	 */
	public boolean roll(){
		this.value = rand.nextInt(6);
		if(this.value <= this.chance){
			this.chance++;
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * gets the % chance a pet will do a trick
	 * @return String of the %
	 */
	public String getChance(){
		if(this.chance == 1){
			this.chanceVal = "16%";
		}
		else if(this.chance == 2){
			this.chanceVal = "33%";
		}
		else if(this.chance == 3){
			this.chanceVal = "50%";
		}
		else if(this.chance == 4){
			this.chanceVal = "66%";
		}
		else if(this.chance == 5){
			this.chanceVal = "83%";
		}
		else{
			this.chanceVal = "100%";
		}
		return this.chanceVal;
	}
	
	
	
}
