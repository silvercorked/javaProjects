package org.millardps.miniProjects;

import java.util.Random;
import java.util.Scanner;

public class hangmanGame {
	private String[] wordBank = {"dank", "swaglord", "platitudinous", "you are bad at games"};
	private int mistakes;
	private String word;
	private String newWord;
	
	
	public hangmanGame(){
		newWord = "";
		mistakes = 0;
		pickWord();
		makeNewWord();
		while(true){
			printInProgressWord(checkProgress(getLetters()));
			makeStickMan();
			if(Win()){
				break;
			}
		}
	}
	public void makeNewWord(){
		for(int i = 0; i < word.length(); i++){
			newWord = newWord + "*";
		}
	}
	//creates the in-progress stickman
	public void makeStickMan(){
		if(mistakes >= 1){
			System.out.println("    O");
			if(mistakes >= 2){
				System.out.println("   -|-");
				if(mistakes >= 3){
					System.out.println("    |");
					if(mistakes >= 4){
						System.out.println("   / \\");
					}
				}
			}
		}
	}
	public void printInProgressWord(String xx){
		System.out.println(newWord);
		for(int i = 0; i < word.length(); i++){
			if(xx.equals(word.charAt(i) + "")){
				newWord= newWord.substring(0, i) + xx + newWord.substring(i+1, newWord.length()); 	
				System.out.println(newWord);
			}
		}
	}
	public boolean Win(){
		if(newWord.equals(word)){
			System.out.println("You win");
			return true;
		}
		else if(mistakes == 4){
			System.out.println("You lose");
			return true;
		}
		else{
			return false;
		}
	}
	public void pickWord(){
		Random rand = new Random();
		double picker = rand.nextInt(100);
		if(picker <= 25){
			word = wordBank[0];
		}
		else if(picker > 25 && picker <= 50){
			word = wordBank[1];
		}
		else if(picker > 50 && picker <= 75){
			word = wordBank[2];
		}
		else if(picker > 75 && picker <= 100){
			word = wordBank[3];
		}
		else{
			word = "what did you do";
		}
	}
	//if two letters are given, then all subsequint inputs are considered more than 1 letter
	public String getLetters(){
		while(true){
			Scanner scan = new Scanner(System.in);
			System.out.println("Give me a letter");
			String letter = scan.nextLine();
			if(letter.length() != 1){
				System.out.println("only 1 letter at a time!");
			}
			else{
				return letter;
			}
		}
		
	}
	public String checkProgress(String xx){
		if(word.contains(xx + "")){
			System.out.println("That letter is in the word!");
			return xx;
		}
		else{
			System.out.println("That letter isn't in the word.");
			mistakes++;
			return "nope";
		}
	}
}
