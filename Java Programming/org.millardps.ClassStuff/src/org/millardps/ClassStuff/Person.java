package org.millardps.ClassStuff;

import java.util.ArrayList;
import java.util.Random;

public class Person {
	//attriuvtes
	private boolean alive;
	private boolean sick;
	private int health;
	private Random rand = new Random();
	public static int population = 0;
	public static int infected = 0;
	
	//constructor
	public Person(){
	alive = true;
	sick = false;
	health = 100;
	population++;
	}
	//methods
	public int getPopulation(){
		return population;
	}
	
	public int getInfected(){
		return this.infected;
	}
	
	public boolean life(ArrayList<Person> city){
		if(this.alive){
			if(this.sick){
				this.health -= rand.nextInt(50);
				if(population > infected){
					infect(city);
				}
				if(this.health <= 0){
					this.alive = false;
					population--;
					infected--;
				}
			}
			else if(population > 2){
				if(rand.nextBoolean()){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isSick(){
		return this.sick;
	}
	
	public void getSick(){
		infected++;
		this.sick = true;
	}
	
	public void infect(ArrayList<Person> city){
		for(Person peep : city){
			if(peep.isSick()){
				
			}
			else if(rand.nextInt(10) < 2){
				peep.getSick();
				break;
			}
		}
	}

}
