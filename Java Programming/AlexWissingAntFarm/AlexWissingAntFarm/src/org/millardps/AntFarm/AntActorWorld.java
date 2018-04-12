package org.millardps.AntFarm;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import java.util.Random;

public class AntActorWorld extends ActorWorld {
	private Random rand= new Random();
	
; 	public AntActorWorld(){
	}
	public void step(){
		super.step();
		try{
			for(int i=0; i<50;i++){
				if(rand.nextInt(100)<5){
					Flower f = new Flower();
					f.putSelfInGrid(getGrid(), getRandomEmptyLocation());
				}
			}
		} catch(NullPointerException E){
			System.out.println("All locations are possessed by an actor");
		}
	}
}


