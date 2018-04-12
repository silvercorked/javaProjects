package org.millardps.GridWorldAlexWissing;

import java.util.ArrayList;

import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class ZCritter extends Critter {
	//attributes
	
	//constructor
	public ZCritter(){
		
	}
	//method
	public Location selectMoveLocation(ArrayList<Location> locs)
    {
        int n = locs.size();
        if (n == 0)
            return getLocation();
        int r = (int) (Math.random() * n);
        return locs.get(r);
        
    }
	
}
