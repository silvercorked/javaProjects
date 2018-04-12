package org.millardps.Tictactoe;

import java.util.ArrayList;

public class Coordinate {
	private int x, y;
	private String isOccupied;
	public Coordinate(){
		isOccupied = "nope";
		x = 0;
		y = 0;
		
	}
	public String display(){
		if(isOccupied != "nope"){
			return "    " + isOccupied + "    ";
		}
		else if(x <= 9 && y <= 9){
			return "  (" + x + "," + y + ")  ";
		}
		else if(x <= 9 && y > 9){
			return "  (" + x + "," + y + ") ";
		}
		else if(x > 9 && y <= 9){
			return " (" + x + "," + y + ")  ";
		}
		else if(x > 99 && y <= 99){
			return "(" + x + "," + y + ") ";
		}
		else if(x <= 99 && y > 99){
			return " (" + x + "," + y + ")";
		}
		else if(x > 99 && y > 99){
			return "(" + x + "," + y + ")";
		}
		else{
			return " (" + x + "," + y + ") ";
		}
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public String isOccupied(){
		return isOccupied;
	}
	public void newIsOccupied(String newOcc){
		isOccupied = newOcc;
	}
	public void newX(int d){
		this.x = d;
	}
	public void newY(int d){
		this.y = d;
	}
	public boolean compare(Coordinate z){
		if(this.getX() == z.getX() && this.getY() == z.getY()){
			return true;
		}
		else{
			return false;
		}
	}
	public ArrayList<Integer> checkDifference(Coordinate z){
		int x = this.getX() - z.getX();
		int y = this.getY() - z.getY();
		ArrayList<Integer> difference = new ArrayList<>();
		difference.add(x);
		difference.add(y);
		return difference;
	}
	public int directionTo(Coordinate z){
		if((this.x - z.getX() == -1 && this.y == z.getY()) || (this.x - z.getX() == 1 && this.y == z.getY())){
			System.out.println("Horizontal");
			return 0;
		}
		else if((this.y - z.getY() == -1 && this.x == z.getX()) || (this.y - z.getY() == 1 && this.x == z.getX())){
			System.out.println("Vertical");
			return 1;
		}
		else if(this.y - z.getY() == -1 && this.x - z.getX() == -1){
			System.out.println("Diagonal decending if this == above compared");
			return 2;
		}
		else if(this.y - z.getY() == -1 && this.x - z.getX() == 1){
			System.out.println("Diagonal ascending if this == above compared");
			return -2;
		}
		return -1;
	}
}
