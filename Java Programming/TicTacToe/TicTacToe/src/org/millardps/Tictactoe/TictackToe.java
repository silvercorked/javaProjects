package org.millardps.Tictactoe;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TictackToe {
	private int tempCount;
	private int tempCountO;
	private Coordinate[][] dankdos;
	
	public TictackToe(int size){
		tempCount = 0;
		tempCountO = 0;
		dankdos = new Coordinate[size][size];
		for(int i = 0; i < dankdos.length; i++){
			for(int t = 0; t < dankdos.length; t++){
			dankdos[t][i] = new Coordinate();
			}
		}
		dankdos = coordPopulater(dankdos);
		if(!twoPlayer()){
			MainLoop:
			while(true){
				ticMaker(size);
				Coordinate playerMove = getUserAction();
				Coordinate AIMove = AIAction(size);
				while(checkDank(playerMove, AIMove) || checkPreviousPointsPlayer(playerMove) || checkPreviousPointsAI(AIMove)){
					if(checkPreviousPointsAI(AIMove)){
						AIMove = AIAction(size);
					}
					else if(checkPreviousPointsPlayer(playerMove)){
						playerMove = getUserAction();
					}
					else{
						playerMove = getUserAction();
						AIMove = AIAction(size);
					}
				}
				placeActions(playerMove, AIMove, size);
				if(getPos(dankdos, size)){
					ticMaker(size);
					break MainLoop;
				}
			}
		}
		else{
			MainLoop:
			while(true){
				ticMaker(size);
				Coordinate playerMove = getUserAction();
				Coordinate playerMove2 = getUserAction();
				while(checkDank(playerMove, playerMove2) || checkPreviousPointsPlayer(playerMove) || checkPreviousPointsAI(playerMove2)){
					if(checkPreviousPointsAI(playerMove2)){
						playerMove2 = getUserAction();;
					}
					else if(checkPreviousPointsPlayer(playerMove)){
						playerMove = getUserAction();
					}
					else{
						playerMove = getUserAction();
						playerMove2 = getUserAction();;
					}
				}
				placeActions2(playerMove, playerMove2, size);
				if(getPos(dankdos, size)){
					ticMaker(size);
					break MainLoop;
				}
			}
		}
	}
	public Coordinate[][] coordPopulater(Coordinate[][] c){
		for(int cols = 0; cols < c.length; cols++){
			for(int rows = 0; rows < c[cols].length; rows++){
				c[rows][cols].newX(rows);
				c[rows][cols].newY(cols);
			}
		}
		return c;
	}
	public boolean twoPlayer(){
		Scanner scan = new Scanner(System.in);
		System.out.println("2 player? yes/no");
		String players = scan.nextLine().toLowerCase();
		if(players.equals("yes")){
			return true;
		}
		else{
			return false;
		}
	}
	public void ticMaker(int z){
	int counter2 = 0;
		for(int cols = 0; cols < z; cols++){
			for(int rows = 0; rows < z; rows++){
				if(rows != z-1){
					System.out.print("" + dankdos[rows][cols].display() + "|");
				}
				else{
					dankyness:
					while(true){
						if(counter2 == 1){
							for(int i = 0; i < z; i++){
								if(i == z-1){
									System.out.println("________");
									counter2 = 0;
									break dankyness;
								}
								else{
									System.out.print("_________|");
								}
							}
						}
						else{
							if(cols == z-1){
								System.out.println("" + dankdos[rows][cols].display() + "");
								break;
							}
							else{
								System.out.println("" + dankdos[rows][cols].display() + "");
								counter2++;								
							}
						}
					}
				}
			}	
		}
	}
	public Coordinate getUserAction(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Where would you like to go? (give x corrdinate)");
		int position = scan.nextInt();
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Where would you like to go? (give y corrdinate)");
		int position2 = scan.nextInt();
		Coordinate dankocity = new Coordinate();
		dankocity.newX(position);
		dankocity.newY(position2);
		return dankocity;
	}
	public Coordinate AIAction(int z){
		Random rand = new Random();
		int newX = rand.nextInt(z);
		int newY = rand.nextInt(z);
		Coordinate dankosity = new Coordinate();
		dankosity.newX(newX);
		dankosity.newY(newY);
		return dankosity;
	}
	public boolean checkDank(Coordinate player, Coordinate AI){
		if(player.equals(AI)){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean checkPreviousPointsPlayer(Coordinate player){
		for(Coordinate[] cordArray : dankdos){
			for(Coordinate c : cordArray){
				if((c.isOccupied().equals("X") && player.compare(c)) || (c.isOccupied().equals("O") && player.compare(c))){
					return true;
				}
			}
		}
		return false;
	}
	public boolean checkPreviousPointsAI(Coordinate AI){
		for(Coordinate[] cordArray : dankdos){
			for(Coordinate c : cordArray){
				if((c.isOccupied().equals("O") && AI.compare(c)) || (c.isOccupied().equals("X") && AI.compare(c))){
					return true;
				}
			}
		}
		return false;
	}
	public void placeActions(Coordinate player, Coordinate AI, int z){
		System.out.println(player.display());
		System.out.println(AI.display());
		for(Coordinate[] cordArray : dankdos){
			for(Coordinate c : cordArray){
				if(player.compare(c)){
					if(c.isOccupied().equals("nope")){
						c.newIsOccupied("X");
					}
				}
			}
		}
		
		for(Coordinate[] cordArray : dankdos){
			for(Coordinate c : cordArray){
				if(AI.compare(c)){
					if(c.isOccupied().equals("nope")){
						c.newIsOccupied("O");
					}
				}
			}
		}
	}
	public void placeActions2(Coordinate player, Coordinate player2, int z){
		System.out.println(player.display());
		System.out.println(player2.display());
		for(Coordinate[] cordArray : dankdos){
			for(Coordinate c : cordArray){
				if(player.compare(c)){
					if(c.isOccupied().equals("nope")){
						c.newIsOccupied("X");
					}
				}
			}
		}
		for(Coordinate[] cordArray : dankdos){
			for(Coordinate c : cordArray){
				if(player2.compare(c)){
					if(c.isOccupied().equals("nope")){
						c.newIsOccupied("O");
					}
				}
			}
		}
	}
	
	public boolean getPos(Coordinate[][] Cords, int z) {
		//horizontal checker
		for(int cols = 0; cols < Cords.length; cols++){
			int tempCount = 0;
			int tempCountO = 0;
			for(int rows = 0; rows < Cords.length; rows++){
				if(Cords[cols][rows].isOccupied() == "X"){
					tempCount++;
					tempCountO = 0;
				}
				else if(Cords[cols][rows].isOccupied() == "O"){
					tempCountO++;
					tempCount = 0;
				}
				else {
					tempCount = 0;
					tempCountO = 0;
				}
				if(z == 3){
					if(tempCount == 3){
						System.out.println("X's win!");
						return true;
					}
					else if(tempCountO == 3){
						System.out.println("O's win!");
						return true;
					}
				}
				else if(z > 3 && z < 7){
					if(tempCount == 4){
						System.out.println("X's win!");
						return true;
					}
					else if(tempCountO == 4){
						System.out.println("O's win!");
						return true;
					}
				}
				else if(z >= 7){
					if(tempCount == 5){
						System.out.println("X's win!");
						return true;
					}
					else if(tempCountO == 5){
						System.out.println("O's win!");
						return true;
					}
				}
			}
		}
		//vertical
		for(int rows = 0; rows < Cords.length; rows++){
			int tempCount = 0;
			int tempCountO = 0;
			for(int cols = 0; cols < Cords.length; cols++){
				if(Cords[cols][rows].isOccupied() == "X"){
					tempCount++;
					tempCountO = 0;
				}
				else if(Cords[cols][rows].isOccupied() == "O"){
					tempCountO++;
					tempCount = 0;
				}
				else {
					tempCount = 0;
					tempCountO = 0;
				}
				if(z == 3){
					if(tempCount == 3){
						System.out.println("X's win!");
						return true;
					}
					else if(tempCountO == 3){
						System.out.println("O's win!");
						return true;
					}
				}
				else if(z > 3 && z < 7){
					if(tempCount == 4){
						System.out.println("X's win!");
						return true;
					}
					else if(tempCountO == 4){
						System.out.println("O's win!");
						return true;
					}
				}
				else if(z >= 7){
					if(tempCount == 5){
						System.out.println("X's win!");
						return true;
					}
					else if(tempCountO == 5){
						System.out.println("O's win!");
						return true;
					}
				}
			}
		}
		//diagonal -- descending
		int tempR = 0;
		int tempC = 0;
		int tempRO = 0;
		int tempCO = 0;
		for(int cols = 0; cols < Cords.length; cols++){
			for(int rows = 0; rows < Cords.length; rows++){
				if(Cords[cols][rows].isOccupied() == "X"){
					tempCountO = 0;
					tempR = rows;
					for(tempC = cols; tempC <= z; tempC++){
						if(tempC >= z || tempR >= z){
							break;
						}
						if(Cords[tempC][tempR].isOccupied() == "X"){
							tempCount++;
							if(z == 3){
								if(tempCount == 3){
									System.out.println("X's win!");
									return true;
								}
							}
							else if(z > 3 && z < 7){
								if(tempCount == 4){
									System.out.println("X's win!");
									return true;
								}
							}
							else if(z >= 7){
								if(tempCount == 5){
									System.out.println("X's win!");
									return true;
								}
							}
						}
						else{
							tempCount = 0;
						}
						tempR++;
					}
				}
				else if(Cords[cols][rows].isOccupied() == "O"){
					tempCount = 0;
					tempRO = rows;
					for(tempCO = cols; tempCO <= z; tempCO++){
						if(tempCO >= z || tempRO >= z){
							break;
						}
						if(Cords[tempCO][tempRO].isOccupied() == "O"){
							tempCountO++;
							if(z == 3){
								 if(tempCountO == 3){
									System.out.println("O's win!");
									return true;
								}
							}
							else if(z > 3 && z < 7){
								if(tempCountO == 4){
									System.out.println("O's win!");
									return true;
								}
							}
							else if(z >= 7){
								if(tempCountO == 5){
									System.out.println("O's win!");
									return true;
								}
							}
						}
						else{
							tempCountO = 0;
						}
						tempRO++;
					}
				}
				else {
					tempCount = 0;
					tempCountO = 0;
				}
				
			}
		}
		System.out.println("ignore above");
		//diagnonal adcending
		for(int cols = 0; cols < Cords.length; cols++){
			for(int rows = 0; rows < Cords.length; rows++){
				if(Cords[cols][rows].isOccupied() == "X"){
					tempCountO = 0;
					tempR = rows;
					for(tempC = cols; tempC <= z; tempC++){
						if(tempC >= z || tempR < 0){
							break;
						}
						if(Cords[tempC][tempR].isOccupied() == "X"){
							tempCount++;
							if(z == 3){
								if(tempCount == 3){
									System.out.println("X's win!");
									return true;
								}
								else if(tempCountO == 3){
									System.out.println("O's win!");
									return true;
								}
							}
							else if(z > 3 && z < 7){
								if(tempCount == 4){
									System.out.println("X's win!");
									return true;
								}
								else if(tempCountO == 4){
									System.out.println("O's win!");
									return true;
								}
							}
							else if(z >= 7){
								if(tempCount == 5){
									System.out.println("X's win!");
									return true;
								}
								else if(tempCountO == 5){
									System.out.println("O's win!");
									return true;
								}
							}
						}
						else{
							tempCount = 0;
						}
						tempR--;
					}
				}
				else if(Cords[cols][rows].isOccupied() == "O"){
					tempCount = 0;
					tempRO = rows;
					for(tempCO = cols; tempCO <= z; tempCO++){
						if(tempCO >= z || tempRO < 0){
							break;
						}
						if(Cords[tempCO][tempRO].isOccupied() == "O"){
							tempCountO++;
							if(z == 3){
								if(tempCountO == 3){
									System.out.println("O's win!");
									return true;
								}
							}
							else if(z > 3 && z < 7){
								if(tempCountO == 4){
									System.out.println("O's win!");
									return true;
								}
							}
							else if(z >= 7){
								if(tempCountO == 5){
									System.out.println("O's win!");
									return true;
								}
							}
						}
						else{
							tempCountO = 0;
						}
						tempRO--;
					}
				}
				else {
					tempCount = 0;
					tempCountO = 0;
				}
			}
		}
		return false;
	}
}