package org.millardps.GridWorldAlexWissing;



import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;

import info.gridworld.actor.*;
import info.gridworld.grid.*;


public class BlackDeathCritter extends Critter {
	//attributes
	//constructor
	public BlackDeathCritter(){
		this.setColor(Color.BLACK);
	}
	//methods
	/**
	 * will have the critter act by using other functions
	 */
	public void act(){
		if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        findNearbyActors2();
        if(checkNoWorldConquest(actors)){
        	return;
        	//System.breakAllTheShit;
        }
        
	}
	/**
	 * looks at adjacent actors, kills them, and make a new black death critter in their place
	 */
	public void processActors(ArrayList<Actor> actors){
		Grid<Actor> gr = getGrid();
        for (Actor a : actors){
        	if(!(a instanceof BlackDeathCritter)){
	        	BlackDeathCritter gh = new BlackDeathCritter();
	        	Location x = a.getLocation();
	            gh.putSelfInGrid(gr, x); 
        	}
        }
    }
	/**
	 * find actors within 2 tiles of our current location
	 */
	public void findNearbyActors2(){
		Location x = this.getLocation();
		ArrayList<Actor> actors = getActors();
		int r = x.getRow();
		int c = x.getCol();
		for(Actor h : actors){
			Location b = h.getLocation();
			int ro = b.getRow();
			int co = b.getCol();
			int distance = (int) Math.sqrt((Math.pow(ro-r, 2))+(Math.pow(co-c, 2)));
			if(distance <= 2 && !(h instanceof BlackDeathCritter)){
				ArrayList<Location> locs = getGrid().getValidAdjacentLocations(b);
				ArrayList<Integer> nearTargetDistances = new ArrayList<>();
				for(Location d : locs){
					int row = d.getRow();
					int col = d.getCol();
					int distance1 = (int) Math.sqrt((Math.pow(row-r, 2))+(Math.pow(col-c, 2)));
					nearTargetDistances.add(distance1);
				}
				int v = minIndex(nearTargetDistances);
				this.moveTo(locs.get(v));
			}
			/*this isn't running
			will make it move randomly if nothing is nearby*/
			else{
				ArrayList<Location> moveLocs = getMoveLocations();
		        Location loc = selectMoveLocation(moveLocs);
		        makeMove(loc);
			}
		}
	}
	/**
	 * finds the minimum value of a list of ints
	 * @param list a list of ints
	 * @return the index containing the smallest int
	 */
	public int  minIndex(ArrayList<Integer> list) {
		  return list.indexOf (Collections.min(list)); 

	}
	/**
	 * if all the actors are blackdeathcritters
	 * @param actors a list of actors
	 * @return true if all actors are blackdeathcritter, false if not
	 */
	public boolean checkNoWorldConquest(ArrayList<Actor> actors){
		int color = 0;
		for (Actor currentCircle: actors) {
		    if (currentCircle instanceof BlackDeathCritter) {
		       color++;
		    }
		    if(color >= actors.size()){
		    	return true;
		    }
		}
		return false;
	}
}
