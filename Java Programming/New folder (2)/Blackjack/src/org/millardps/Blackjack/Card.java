package org.millardps.Blackjack;

public class Card {
	//attributes
	private String value;
	private String suit;
	private int numValue;
	
	//constructor
	public Card(String value2, String suit2, int num){
		value = value2;
		suit = suit2;
		numValue = num;
	}
	//methods
	public String getValue(){
		return this.value;
	}
	public String getSuit(){
		return this.suit;
	}
	public int getNumValue(){
		return numValue;
	}
}
