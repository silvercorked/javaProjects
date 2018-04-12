package org.millardps.GridWorldAlexWissing;

import java.util.ArrayList;
import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import info.gridworld.actor.ActorWorld;


//work in progress
public class FireCritter extends Critter{
	//attributes
	private int count;
	//Constructor
	public FireCritter(){
		count = 0;
	}
	//methods
	/**
	 * Determines if the critter spreads
	 * @return boolean
	 */
	public boolean spread(){
		double spreadyes = Math.random();
		if(spreadyes < .5){
			return true;
		}
		else{
			return false;
		}
	}
	public void act(){
		//moves in a square increasing in size
		ArrayList<Location> movelocs = getMoveLocations();
		Grid<Actor> gr = getGrid();
		ArrayList<Actor> actors = getActors();
		ArrayList<Location> x = gr.getOccupiedAdjacentLocations(this.getLocation());
		count++;
		for(Location k : x){
			if(!(gr.get(k) instanceof Rock)|| !(gr.get(k) instanceof Critter)){
				FireCritter fc= new FireCritter();
				gr.get(k).removeSelfFromGrid();
				fc.putSelfInGrid(gr, k);
				
			}
	       
		}
		for(Location l : movelocs){
			FireCritter fc= new FireCritter();
			if(spread() == true){
				
		        fc.putSelfInGrid(gr, l);
		    }
		}
		 if(count >= 5){
			 this.removeSelfFromGrid();
		 }
		    
		
		
		
	}
	
}
