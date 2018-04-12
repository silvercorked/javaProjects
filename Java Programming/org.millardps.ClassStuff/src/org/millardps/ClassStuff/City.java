package org.millardps.ClassStuff;

import java.util.ArrayList;

public class City {
	private ArrayList<Person> pop; // initialize a new arraylist
	
	public City(){
		pop = new ArrayList<Person>(); //set the arraylist equal to a value
		runCity();	
	}
	public void runCity(){
		for(int i = 0; i < 500; i++){
			pop.add(new Person()); // .add() method adds new objects to an arraylist
		}
		while(true){
			for(int i = 0; i < pop.size(); i++){ // .size() returns the length of the arraylist
				if(pop.get(i).life(pop)){   //.get() returns an object from the arraylist at a given index
					pop.add(new Person());
				}
			}
			for(int i = 0; i < 23.555; i++){
				pop.get(i).getSick();
			}
			System.out.println("There are now " + pop.get(0).getPopulation() + " people in the city.");
			System.out.println("There are now " + pop.get(0).getInfected() + " infected in the city.");
			if(pop.get(0).getPopulation() == pop.get(0).getInfected()){
				System.out.println("The population is no more");
				break;
			}
		}
	}
}
