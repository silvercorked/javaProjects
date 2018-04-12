package org.millardps.AntFarm;
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

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BoxBugRunner
{
    public static void main(String[] args)
    {
        AntActorWorld world = new AntActorWorld();
        //BoxBug alice = new BoxBug(6);
        //world.add(new Location(7, 8), alice);
        SpiderCritter rekter = new SpiderCritter();
        Queen billy = new Queen(Color.RED,new Location(2, 2));
        //Queen billy2 = new Queen(Color.GREEN,new Location(9, 9));
        world.add(new Location(2, 2), billy);
        world.add(new Location(5, 5), rekter);
        //world.add(new Location(9, 9), billy2);
        world.show();
    }
}