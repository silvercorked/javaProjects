package org.millardps.Notes;

import java.util.ArrayList;

public class Person {
	//attributes
	private double dailyCalories, dailyFat, dailyProtein, dailySodium, dailyFiber;
	private String[] nutrition = {"calories", "fat", "protein", "sodium", "fiber"};
	private double[] nutrition2 = {dailyCalories, dailyFat, dailyProtein, dailySodium, dailyFiber};
	private final double[] diet = {2000.0, 65.0, 50.0, 2400.0, 25.0};
	//constructor
	public Person(){
		newDay();
		
	}
	
	
	
	
	//method
	public void newDay(){
		for(int i = 0; i < nutrition2.length; i++){
			nutrition2[i] = diet[i];
		}
	}
	
	public void eatFood(ArrayList<Inheritiance> food){
		for(Inheritiance meal: food){
			nutrition2[0] -= meal.getCalories();
			nutrition2[1] -= meal.getFat();
			nutrition2[2] -= meal.getProtein();
			nutrition2[3] -= meal.getSodium();
			nutrition2[4] -= meal.getFiber();
		}
	}
	
	public void printDay(){
		for(int i = 0; i < nutrition.length; i++){
			System.out.println("You have " + nutrition2[i] + " of " + diet[i] + " " + nutrition[i] + " left for today.");
			System.out.println("You can eat " + (diet[i] - nutrition2[i]) + " more " + nutrition[i] + " today.");
		}
	}
	
	
	
}
