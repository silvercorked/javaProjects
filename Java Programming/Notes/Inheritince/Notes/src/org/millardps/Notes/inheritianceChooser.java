package org.millardps.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class inheritianceChooser {
	private ArrayList<Inheritiance> food = new ArrayList<Inheritiance>();
	private Scanner scanner = new Scanner(System.in);
	private Person person = new Person();
	
	//constructor
	public inheritianceChooser(){
		while(true){
			choose();
			dayRunner();
		}
	}
	
	//method
	public void dayRunner(){
		person.eatFood(food);
		person.printDay();
		person.newDay();
		food.clear();
	}
	public void choose(){
		for(int i = 0; i < 3; i++){
			while(true){
				System.out.println("Choose a meal for the day.");
				String choice = scanner.nextLine().toLowerCase();
				if(choice.contains("pizza")){
					food.add(new Pizza());
					break;
				}
				else if(choice.contains("pie")){
					food.add(new Pie());
					break;
				}
				else if(choice.contains("cereal")){
					food.add(new Cereal());
					break;
				}
			}
		}
	}
	
}
