package org.millardps.miniProjects;

import java.util.Random;
import java.util.Scanner;

public class madLibGen {
	private boolean done;
	
	public madLibGen(){
		pickScenario();
		scenario1();
		done = false;
	}
	
	public void pickScenario(){
		Random rand = new Random();
		int picker = rand.nextInt(100);
		if(picker <= 33){
			scenario1();
		}
		else if(picker > 33 && picker  <= 66){
			scenario2();
		}
		else{
			scenario3();
		}
		if(done){
			return;
		}
	}
	public void scenario1(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Please give me a adverb.");
		String answerFirst = scan.nextLine().toLowerCase();
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Please give me a noun.");
		String answerSecond = scan1.nextLine().toLowerCase();
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Please give me a adjective.");
		String answerThird = scan2.nextLine().toLowerCase();
		Scanner scan3 = new Scanner(System.in);
		System.out.println("Please give me a noun.");
		String answerFourth = scan3.nextLine().toLowerCase();
		System.out.println("You are " + answerFirst + " through the forest when suddenly a " + answerSecond + " jumps at you and chases you!");
		System.out.println("You " + answerThird + " run from the " + answerSecond + " when you come across a " + answerFourth + "!");
		System.out.println("You turn and face the " + answerSecond + ". " + answerSecond.toUpperCase().charAt(0) + answerSecond.substring(1, answerSecond.length()) + " charges!");
		System.out.println("You attack with your new " + answerFourth + " and the " + answerSecond + " dies.");
		done = true;
	}
	public void scenario2(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Please give me a place.");
		String answerFirst = scan.nextLine().toLowerCase();
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Please give me a noun.");
		String answerSecond = scan1.nextLine().toLowerCase();
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Please give me a thing.");
		String answerThird = scan2.nextLine().toLowerCase();
		Scanner scan3 = new Scanner(System.in);
		System.out.println("Please give me a noun.");
		String answerFourth = scan3.nextLine().toLowerCase();
		Scanner scan4 = new Scanner(System.in);
		System.out.println("Please give me a verb.");
		String answerFifth = scan3.nextLine().toLowerCase();
		System.out.println("You want into a " + answerFirst + ". The " + answerSecond + " asks for your " + answerThird + ". You give it to him.");
		System.out.println("He looks at it, but then he suddenly becomes a " + answerFourth + " and starts to attack people nearby!");
		System.out.println("You " + answerFifth + " and grab the " + answerFourth + ". The people nearby run and the " + answerFourth + " bites you!");
		System.out.println("As the police arrive and capture the " + answerFourth + ", you die of blood loss. ");
		done = true;
	}
	public void scenario3(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Please give me a noun.");
		String answerFirst = scan.nextLine().toLowerCase();
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Please give me a verb.");
		String answerSecond = scan1.nextLine().toLowerCase();
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Please give me a verb.");
		String answerThird = scan2.nextLine().toLowerCase();
		Scanner scan3 = new Scanner(System.in);
		System.out.println("Please give me a noun.");
		String answerFourth = scan3.nextLine().toLowerCase();
		System.out.println("You walk through the forest, bow in hand. You see a wild " + answerFirst + ". You grab an arrow from the quiver and pull back the string.");
		System.out.println("With the arrow loosed, you jump down and " + answerSecond + " after the " + answerFirst + ". You follow the trail of blood to a small cave.");
		System.out.println("Inside the cave, you see a man hung upon the room of the cave, stuck in snow. You see the " + answerFirst + " snuggling against a larger " + answerFirst + " in the corner.");
		System.out.println("The larger " + answerFirst + " wakes and spots you! He " + answerThird + " toward you! He passes the man, but the man falls from the ceiling");
		System.out.println("while using some power to pull a light sword to him. He " + answerFourth + " the " + answerFirst + ". Then you both have a hardy feast.");
		done = true;
	}
}
	
