package org.millardps.PetProject;

public class Troll {
	//attributes
	private int hp = 30;
	private DragonPet trollPet;
	private int gold;
	
	
	public Troll(DragonPet x){
		trollPet=x;
		gold = hp * 3 + trollPet.level;
	}
}
