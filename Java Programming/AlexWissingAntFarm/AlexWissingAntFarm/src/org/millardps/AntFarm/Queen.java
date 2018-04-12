package org.millardps.AntFarm;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;

import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class Queen extends Critter{
	//only moves 4 squares from start area. creates AntCritters when eating flowers. should not eat rocks or her** subjects
	//attributes
		//private int count;
		private Location startLocation;
		private Color color1;
		private ArrayList<Location> hive; 
		private boolean fix= true;
		//constructor
		public Queen(Color color, Location startLoc){
			//count = 0;
			this.color1 = color;
			setColor(color1);
			startLocation = startLoc;
		}
		//methods
		public ArrayList<Location> getHive(){
			ArrayList<Location> newHive= getGrid().getValidAdjacentLocations(startLocation);
			newHive.add(startLocation);
			return newHive;
		}
		public Location selectMoveLocation(ArrayList<Location> locs)
	    {
			if(fix){
				hive= getHive();
				fix=false;
			}
	        int n = locs.size();
	        if (n == 0)
	            return getLocation();
	        int r = (int) (Math.random() * n);
	        if(hive.contains(locs.get(r))){
	        	return locs.get(r);
	        }
	        else{
	        	return startLocation;
	        }
	    }
		/**
		 * will have the critter act by using other functions
		 */
		public void act(){
			if (getGrid() == null)
	            return;
	        ArrayList<Actor> actors = getActors();
	        processActors(actors);
	        //Location moveloc = findNearbySquares4();
	        //makeMove(moveloc);
	        ArrayList<Location> moveLocs = getMoveLocations();
	        Location loc = selectMoveLocation(moveLocs);
	        
	        makeMove(loc);
	        }
		/**
		 * looks at adjacent actors, kills them, and make a new black death critter in their place
		 */
		public void processActors(ArrayList<Actor> actors){
			Grid<Actor> gr = getGrid();
			for (Actor a : actors){
	        	if(a instanceof Flower){
		        	AntCritter bh = new AntCritter(this.getColor(), startLocation);
		        	Location newloc = a.getLocation();
		        	a.removeSelfFromGrid();
		        	bh.putSelfInGrid(gr, newloc);
		        	//removes FlowerSpawners from the grid and replaces them with AntCritters
	        	}
	        }
	    }
		/**
		 * find actors within 4 tiles of our current location
		 */
		public Location findNearbySquares4(){
			ArrayList<Actor> flowers = getFlowers();
			System.out.println(startLocation);
			int r = startLocation.getRow();
			int c = startLocation.getCol();
			for(Actor h : flowers){
				Location b = h.getLocation();
				int ro = b.getRow();
				int co = b.getCol();
				int distance = (int) Math.sqrt((Math.pow(ro-r, 2))+(Math.pow(co-c, 2)));
				if(distance <= 4){
					System.out.println(b);
					return b;
				}
			}
			return startLocation;
		}
		/**
		 * finds the minimum value of a list of integers
		 * @param list a list of integers
		 * @return the index containing the smallest integer
		 */
		public static int minIndex(ArrayList<Integer> list) {
			  return list.indexOf (Collections.min(list)); 

		}
		/**
		 * if this is the first time asked for startLocation, it will take this.getLocation() and call it startLocation
		 * @return 1st time: returns this.getLocation(); 2nd time: returns x, which is that original location
		 */
		//public Location startLocation(){
		//	System.out.println("startlocation");
		//	if(count == 0){
		//		
		//		Location x = this.getLocation();
		//		System.out.println(x);
		//		count++;
		//		return x;
		//	}
		//	else{
		//		return getStartingLocation();
		//	}
		//}
		public Location getStartingLocation(){
			return startLocation;
		}
		//add an hp recovery function if time
		public ArrayList<Actor> getFlowers(){
	    	ArrayList<Actor> flowers = new ArrayList<>();
	    	ArrayList<Actor> actors = getActors();
	    	for(Actor a : actors){
	    		if(a instanceof Flower){
	    			flowers.add(a);
	    			
	    		}
	    	}
	    	return flowers;
	    }
}
