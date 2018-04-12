package org.millardps.ClassStuff;

import java.util.Random;

public class PirateShip {
	//attributes
	private Pirate captain;
	private Pirate[] crew;
	private int cannonBalls;
	private Random rand;
	
	//constructor
	public PirateShip(Pirate p){
		captain = p;
		cannonBalls = 400;
		rand = new Random();
		crew = new Pirate[10];
		addCrew();
	}
	
	//methods
	public Pirate getCaptain(){
		return this.captain;
	}
	public void addCrew(){
		for(int i = 0; i < crew.length; i++){
			String crewName = "Person" + (i+1) + " of " + captain.getName() + "'s crew";		
			crew[i] = new Pirate(crewName);
		}
	}
	public boolean livingCrew(){
		int dead = 0;
		for(int i = 0; i < crew.length; i++){
			if(!(crew[i].getAlive())){
				dead++;
			}
		}
		if(dead == crew.length){
			return false;
		}
		else{
			return true;
		}
	}
	public void fireCannons(PirateShip target){
		if(this.cannonBalls > 0){
			cannonBalls -= 2;
			
			if(rand.nextInt(100) < 90){
				System.out.println(captain.getName() + " hit " + target.getCaptain().getName());
				target.captain.loseALimb();
			}
			else{
				System.out.println(captain.getName() + " missed " + target.getCaptain().getName());
			}
			
			for(int i = 0; i < target.crew.length; i++){
				if(rand.nextBoolean()){
					System.out.println(crew[i].getName() + " hit " + target.getCaptain().getName() + "'s crew !");
					crew[i].loseALimb();
				}
			}
		}
		else{
			System.out.println(captain.getName() + "'s ship is out of cannonballs!");
		}
	}
	
	
	
	
	
	
	
	
	
}
