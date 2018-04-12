package org.millardps.AntFarm;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;

import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class AntCritter extends Critter{
	//attributes
	private boolean isCarryingRock;
	private boolean isCarryingFlower;
	private Color color1;
	private Location home;
	private Actor Enemymain;
	//constructor
	public AntCritter(Color color, Location nest){
		color1 = color;
		setColor(color1);
		isCarryingRock = false;
		isCarryingFlower = false;
		home = nest;
		Enemymain = null;
	}
	//methods
	public void act(){
		if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        if(isCarryingRock){
			carryRock();
		}
		else if(isCarryingFlower){
			carryFlowerSpawner();
		}
		else if(Enemymain instanceof AntCritter || Enemymain instanceof Queen){
			fight(Enemymain);
		}
		else{
			Location nextMove = selectMoveLocation(getMoveLocations());
			for(Actor aa : actors){
				if(aa instanceof AntCritter && aa.getLocation() == nextMove){
					nextMove = selectMoveLocation(getMoveLocations());
				}
				else{
					makeMove(nextMove);
				}
			}
		}
		processActors(actors);
	}
	/**
	 * looks at adjacent actors, kills them, and make a new black death critter in their place
	 */
	public void processActors(ArrayList<Actor> actors){
		for (Actor a : actors){
        	if(a instanceof Rock){
        		isCarryingRock = true;
        		a.removeSelfFromGrid();
        	}
        	else if(a instanceof Flower){
        		isCarryingFlower = true;
        		a.removeSelfFromGrid();
        	}
        	else if((a instanceof AntCritter || a instanceof Queen)){
        		Color enemy = a.getColor();
        		if(!(enemy.equals(color1))){
        			if(getDistance(a) < 4){
        				Enemymain = a;
        			}
        		}
        	}
        }
	}
	/**
	 * looks for rocks and flowers
	 */
	//dont use distance formula unless extra time, just make it get a list of actors and move to rocks and flowerspawners
	public Location findStuffExceptEnemies(){
		ArrayList<Actor> actors = getActors();
		for(Actor h : actors){
			if(h instanceof Flower || h instanceof Rock){
				return h.getLocation();
			}
		}
		return home;
	}
	/**
	 * walks to a region containing the queen and puts the antCritter on the border of it or further back depending on how many rocks exist there
	 */
	public void carryRock(){
		Location x = this.getLocation();
		int r = x.getRow();
		int c = x.getCol();
	//change this for loop so that the ant critter finds its (same colored) queen.
		Location b = home;
		int ro = b.getRow();
		int co = b.getCol();
		int distance = (int) Math.sqrt((Math.pow(ro-r, 2))+(Math.pow(co-c, 2)));
		//change to if distance if greater than 4 but less than 10
		if(distance == 6){
			int direction = this.getLocation().getDirectionToward(home);
			isCarryingRock = false;
			Location placement = this.getLocation().getAdjacentLocation(direction);
			Grid<Actor> gr = getGrid();
			gr.put(placement, new Rock());
		}
		else{
			ArrayList<Location> locs = getMoveLocations();
			ArrayList<Integer> distances = new ArrayList<>();
			for(Location ll : locs){
				int roo = ll.getRow();
				int coo = ll.getCol();
				int row = home.getRow();
				int col = home.getCol();
				int distance1 = (int) Math.sqrt((Math.pow(row-roo, 2))+(Math.pow(col-coo, 2)));
				distances.add(distance1);
			}
			int nextMove2 = minIndex(distances);
			for(Location ll : locs){
				int roo1 = ll.getRow();
				int coo1 = ll.getCol();
				int row1 = home.getRow();
				int col1 = home.getCol();
				int distance1 = (int) Math.sqrt((Math.pow(row1-roo1, 2))+(Math.pow(col1-coo1, 2)));
				if(distance1 == nextMove2){
					this.moveTo(ll);
				}
			}
		}  
	}
	/**
	 * Takes the critter to a location inside of the region containinng the queen
	 */
	//not done at all, will be fixed after rock
	public void carryFlowerSpawner(){
		Location x = this.getLocation();
		int r = x.getRow();
		int c = x.getCol();
		Location b = home;
		int ro = b.getRow();
		int co = b.getCol();
		int distance = (int) Math.sqrt((Math.pow(ro-r, 2))+(Math.pow(co-c, 2)));
		if(distance <= 2 && findIfCanDrop()){
			int direction = this.getLocation().getDirectionToward(home);
			isCarryingFlower = false;
			Location placement = this.getLocation().getAdjacentLocation(direction);
			Grid<Actor> gr = getGrid();
			gr.put(placement, new Flower());
		}
		else{
			ArrayList<Location> locs = getMoveLocations();
			ArrayList<Integer> distances = new ArrayList<>();
			for(Location ll : locs){
				int roo = ll.getRow();
				int coo = ll.getCol();
				int row = home.getRow();
				int col = home.getCol();
				int distance1 = (int) Math.sqrt((Math.pow(row-roo, 2))+(Math.pow(col-coo, 2)));
				distances.add(distance1);
			}
			int nextMove2 = minIndex(distances);
			for(Location ll : locs){
				int roo1 = ll.getRow();
				int coo1 = ll.getCol();
				int row1 = home.getRow();
				int col1 = home.getCol();
				int distance1 = (int) Math.sqrt((Math.pow(row1-roo1, 2))+(Math.pow(col1-coo1, 2)));
				if(distance1 == nextMove2){
					this.moveTo(ll);
				}
			}
			
		}
	}
	public static int minIndex(ArrayList<Integer> list) {
		  return list.indexOf (Collections.min(list)); 

	}
	public int getDistance(Actor hh){
		Location x = this.getLocation();
		int r = x.getRow();
		int c = x.getCol();
		Location b = hh.getLocation();
		System.out.println(b);
		int ro = b.getRow();
		int co = b.getCol();
		int distance = (int) Math.sqrt((Math.pow(ro-r, 2))+(Math.pow(co-c, 2)));
		return distance;
	}
	public void fight(Actor otherFighter){
		this.moveTo(otherFighter.getLocation());
		if(getDistance(otherFighter) < 2){
			if(otherFighter instanceof Queen){
				double queenrekter = Math.random();
				if(queenrekter < .30){
					otherFighter.removeSelfFromGrid();
				}
				else{
					this.removeSelfFromGrid();
				}
			}
			else if(otherFighter instanceof AntCritter){
				double fighter = Math.random();
				if(fighter < .49){
					this.removeSelfFromGrid();
				}
			}
		}
	}
	/**
	 * develops a range (4x4) around home 
	 * @return true if within space, false if not
	 */
	public boolean findIfCanDrop(){
        if (this.getLocation().getRow() - home.getRow() < 4 && this.getLocation().getRow() - home.getRow() < -4)
             if (this.getLocation().getCol() - home.getCol() < 4 && this.getLocation().getCol() - home.getCol() < -4)
            	 return true;
             else{
            	 return false;
             }
        else{
        	return false;
        } 
    }
}
