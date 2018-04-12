package org.millardps.AntFarm;

import java.util.ArrayList;

import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class SpiderCritter extends Critter {
	//attributes
	
	//constructor
	public SpiderCritter() {
		
	}
	//methods
	public void act(){
		findFlower();
		death();
	}
	
	public void findFlower(){
		ArrayList<Actor> actors = getActors();
		for(Actor h : actors){
			if(h instanceof Flower){
				Location aa = h.getLocation();
				int r = aa.getRow();
				int c = aa.getCol();
				Location b = this.getLocation();
				int ro = b.getRow();
				int co = b.getCol();
				int distance = (int) Math.sqrt((Math.pow(ro-r, 2))+(Math.pow(co-c, 2)));
				if(distance > 1){
					this.moveTo(aa);
				}
				else{
					waitAndJump();
				}
			}
		}
	}
	public void waitAndJump(){
		ArrayList<Actor> actors = getActors();
		for(Actor h : actors){
			if(h instanceof AntCritter){
				Location aa = h.getLocation();
				int r = aa.getRow();
				int c = aa.getCol();
				Location b = this.getLocation();
				int ro = b.getRow();
				int co = b.getCol();
				int distance = (int) Math.sqrt((Math.pow(ro-r, 2))+(Math.pow(co-c, 2)));
				if(distance <= 2){
					if(distance <= 1){
						this.moveTo(aa);
						h.removeSelfFromGrid();
					}
					//can only be 2
					else{
						this.moveTo(aa);
						h.removeSelfFromGrid();
						//this might make it jump
					}
				}
				else{
					//this will make this wait, i think
				}
			}
		}
	}
	/**
	 * if an ant gets within one square of the spider, it is added to a list. If there are 3 in the list, the spider dies. The list is filtered through eachtime to make sure the ants are still within one space
	 */
	public void death(){
		ArrayList<Actor> actors = getActors();
		ArrayList<Actor> ants = new ArrayList<>();
		for(Actor h : actors){
			if(h instanceof AntCritter){
				Location aa = h.getLocation();
				int r = aa.getRow();
				int c = aa.getCol();
				Location b = this.getLocation();
				int ro = b.getRow();
				int co = b.getCol();
				int distance = (int) Math.sqrt((Math.pow(ro-r, 2))+(Math.pow(co-c, 2)));
				if(distance <= 1){
					ants.add(h);
				}
				for(Actor j : ants){
					int distance2 = (int) Math.sqrt((Math.pow(ro-r, 2))+(Math.pow(co-c, 2)));
					if(distance2 >= 2){
						ants.remove(j);
					}
				}
				if(ants.size() >= 3){
					Flower hhhh = new Flower();
					hhhh.putSelfInGrid(getGrid(), this.getLocation());
					this.removeSelfFromGrid(); //this shouldn't run because putSelfInGrid kills <code> this </code>
				}
			}
		}
	}
	

}
