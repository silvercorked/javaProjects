/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */
package org.millardps.GridWorldAlexWissing;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BoxBugRunner{
	
    public static void main(String[] args){
        ActorWorld world = new ActorWorld();
        world.setGrid(new UnboundedGrid<Actor>());
        //world.setGrid(new ArrayList<Actor> (50, 50));
        BoxBug alice = new BoxBug(6);
        alice.setColor(Color.ORANGE);
        BoxBug bob = new BoxBug(3);
        bob.setColor(Color.GREEN);
        ZBug jim = new ZBug(9);
        jim.setColor(Color.BLACK);
        BoxBug martha = new BoxBug(40);
        martha.setColor(Color.BLUE);
        SpiralBug yona = new SpiralBug(2);
        yona.setColor(Color.YELLOW);
        ZBug Alex = new ZBug(4);
        FireCritter fatty = new FireCritter();
        Bug runner1 = new Bug();
        Bug runner2 = new Bug();
        Bug runner3 = new Bug();
        Bug runner4 = new Bug();
        Bug runner5 = new Bug();
        Bug runner6 = new Bug();
        Bug runner7 = new Bug();
        Bug runner8 = new Bug();
        BlackDeathCritter death = new BlackDeathCritter();
        BlackDeathCritter death1 = new BlackDeathCritter();
        BlackDeathCritter death2 = new BlackDeathCritter();
        BlackDeathCritter death3 = new BlackDeathCritter();
        BlackDeathCritter death4 = new BlackDeathCritter();
        world.add(new Location(10, 10), runner1);
        world.add(new Location(20, 20), runner2);
        world.add(new Location(30, 30), runner3);
        world.add(new Location(40, 40), runner4);
        world.add(new Location(50, 50), runner5);
        world.add(new Location(60, 60), runner6);
        world.add(new Location(11, 10), runner7);
        world.add(new Location(66, 34), runner8);
        world.add(new Location(7, 8), alice);
        world.add(new Location(5, 5), bob);
        world.add(new Location(9, 9), jim);
        world.add(new Location(3, 9), martha);
        world.add(new Location(5, 6), yona);
        world.add(new Location(1, 1), Alex);
        world.add(new Location(2, 2), fatty);
        world.add(new Location(3, 4), death);
        world.add(new Location(4, 2), death1);
        world.add(new Location(6, 8), death2);
        world.add(new Location(8, 5), death3);
        world.add(new Location(8, 8), death4);
        world.show();
    }
    
}