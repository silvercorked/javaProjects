package org.wissing.CharacterRandomization;

public abstract class Classes {
	public int[] Statistics = new int[5];
	public int[] modif = new int[5];
	public int getStrength(){
		return Statistics[0];
	}
	public int getDex(){
		return Statistics[1];
	}
	public int getCon(){
		return Statistics[2];
	}
	public int getInt(){
		return Statistics[3];
	}
	public int getWisdom(){
		return Statistics[4];
	}
	public int getChar(){
		return Statistics[5];
	}
	public void setStrength(int x){
		Statistics[0] = x;
	}
	public void setDex(int x){
		Statistics[1] = x;
	}
	public void setCon(int x){
		Statistics[2] = x;
	}
	public void setInt(int x){
		Statistics[3] = x;
	}
	public void setWisdom(int x){
		Statistics[4] = x;
	}
	public void setChar(int x){
		Statistics[5] = x;
	}
	public void printStats(){
		for(int i = 0; i < Statistics.length; i++){
			if(i == 0)
				System.out.println("Strength: " + Statistics[i]);
			else if(i == 1)
				System.out.println("Dexterity: " + Statistics[i]);
			else if(i == 2)
				System.out.println("Constitution: " + Statistics[i]);
			else if(i == 3)
				System.out.println("Intelligence: " + Statistics[i]);
			else if(i == 4)
				System.out.println("Wisdom: " + Statistics[i]);
			else if(i == 5)
				System.out.println("Charisma: " + Statistics[i]);
		}
	}
	public void prinModifiers(){
		for(int i = 0; i < modif.length; i++){
			if(i == 0)
				System.out.println("Strength: " + modif[i]);
			else if(i == 1)
				System.out.println("Dexterity: " + modif[i]);
			else if(i == 2)
				System.out.println("Constitution: " + modif[i]);
			else if(i == 3)
				System.out.println("Intelligence: " + modif[i]);
			else if(i == 4)
				System.out.println("Wisdom: " + modif[i]);
			else if(i == 5)
				System.out.println("Charisma: " + modif[i]);
		}
	}
}
