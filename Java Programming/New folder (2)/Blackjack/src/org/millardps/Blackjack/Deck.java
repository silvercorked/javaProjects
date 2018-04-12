package org.millardps.Blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	//attributes
	private ArrayList<Card> deck = new ArrayList<Card>();
	private String[] suits = {"Spades", "Diamonds", "Clubs", "Hearts"};
	private String[] faceCards = {"K", "Q", "J", "A"};
	private Random rand = new Random();
	
	//constructor
	public Deck(){
		makeDeck();
	}
	//methods
	public void makeDeck(){
		for(int i = 0; i < suits.length; i++){
			int counter = 0;
			for(int v = 2; v < 14; v++){
				if(v < 11){
					String val = v + "";
					deck.add(new Card(val, suits[i], v));
				}
				else if(v < 14){
					deck.add(new Card(faceCards[counter], suits[i], 10));
					counter++;
				}
				else{
					deck.add(new Card(faceCards[counter], suits[i], 11));
					counter++;
				}
			}
		}
	}
	public void resetDeck(){
		deck.clear();
		makeDeck();
	}
	
	public ArrayList<Card> getDeck(){
		return this.deck;
	}
	
	public Card deal(){
		Card card = deck.get(rand.nextInt(deck.size()));
		deck.remove(card);
		return card;
	}
}
