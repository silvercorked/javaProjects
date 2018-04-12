package org.millardps.Notes;

public class Inheritiance {
	//attributes
	protected double calories, fat, protein, sodium, fiber;
	protected String type;
	
	//constructor
	public Inheritiance(String type2){
		System.out.println("You have eaten " + type2 + ".");
	}
	//method
	public String getType(){
		return this.type;
	}
	public double getCalories(){
		return this.calories;
	}
	public double getFat(){
		return this.fat;
	}
	public double getProtein(){
		return this.protein;
	}
	public double getSodium(){
		return this.sodium;
	}
	public double getFiber(){
		return this.fiber;
	}
	
}
