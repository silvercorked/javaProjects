package org.millardps.ClassStuff;

import java.util.Random;

public class Runner {

	public static void main(String[] args) {
//		Pirate p = new Pirate("BlackBeard");
//		
//		Wizard w = new Wizard();
//		while(p.getAlive()){
//			if(w.castASpell(p)){
//				Ninja n = new Ninja();
//			}
//		}
//		
//		Ninja bob = new Ninja();
//		while(p.getAlive() == true && bob.getStars() > 0){
//			for(int i = 0; i < bob.getStars(); i = 0){
//				bob.throwStar(p);
//			}
//			if(p.getAlive() == true){
//				System.out.println(p.getName() + " has survived!");
//			}
//			else{
//				System.out.println(bob.getName() + " has killed " + p.getName() + "!");
//			}
//			
//		}
		Pirate one = new Pirate("BlackBeard");
		Pirate two = new Pirate("BlueBeard");
		
		PirateShip first = new PirateShip(one);
		PirateShip second = new PirateShip(two);
		
		while(first.getCaptain().getAlive() && first.livingCrew() && second.getCaptain().getAlive() && second.livingCrew()){
			Random rand = new Random();
			if(rand.nextBoolean() == true){
				System.out.println(first.getCaptain().getName() + " is about to first!");
				first.fireCannons(second);
			}
			else if(rand.nextBoolean() == false){
				System.out.println(second.getCaptain().getName() + " is about to first!");
				second.fireCannons(first);
			}
			
		}
		if(first.getCaptain().getAlive() && first.livingCrew() == false){
			System.out.println(second.getCaptain().getName() + " has defeated " + first.getCaptain().getName() + "!");
		}
		else if (second.getCaptain().getAlive() && second.livingCrew() == false){
			System.out.println(first.getCaptain().getName() + " has defeated " + second.getCaptain().getName() + "!");
		}
	
	}

}
