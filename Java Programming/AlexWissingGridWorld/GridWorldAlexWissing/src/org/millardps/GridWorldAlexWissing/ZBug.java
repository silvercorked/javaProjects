package org.millardps.GridWorldAlexWissing;

import info.gridworld.grid.Location;
import info.gridworld.actor.*;

import java.util.ArrayList;

public class ZBug extends BoxBug {
	//attributes
	private int length;
	private int turn;
	private int topzz;
	private int diagonalzz;
	//constructor

	public ZBug(int length2) {
		super(length2);
		length=length2;
		turn = 0;
		topzz = 0;
		diagonalzz = 0;
	}
	public void act()
    {
        if (turn < length && canMove())
        {
            move();
            turn++;
        }
        else
        {
        	if(topzz<1){
	            turn();
	            turn();
	            turn();
	            turn = 0;
	            topzz++;
        	}
        	else if(diagonalzz < 1){
        		turn();
        		turn();
        		turn();
        		turn();
        		turn();
        		turn = 0;
        		diagonalzz++;
        	}
        	else{
        		turn();
        	}
        }
    }
}
