package org.millardps.Blackjack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLogic {
	//attributes
	private Deck deck = new Deck();
	private Currency currency = new Currency();
	private ArrayList<Card> playerHand = new ArrayList<Card>();
	private ArrayList<Card> dealerHand = new ArrayList<Card>();
	private int bet;
	private int debt;
	private int hits;
	
	//constructor
	public GameLogic(){
		debt = 0;
		while(true){
			bet = 0;
			getBet();
			dealHandStart();
			System.out.println("Let's view your hand.");
			viewHand();
			getValue();
			getValueDealer();
			hitStay();
			if(!(playAgain())){
				break;
			}
		}
		
		
	}
	//methods
	/**
	 * gets the amount of currency being bet
	 */
	public void getBet(){
		deck.makeDeck();
		while(true){
			try{
					Scanner scan1 = new Scanner(System.in);
					broke();
					System.out.println("How much would like to bet? You have " + currency.getCurrency() + " chips");
					bet = scan1.nextInt();
					if(bet > currency.getCurrency()){
						System.out.println("You don't have that much money!");
					}
					else if(bet < 1){
						System.out.println("You have to bet a positive integer!");
					}
					else{
						System.out.println("You bet " + bet + ".");
						currency.betAmountRemove(bet);
						System.out.println("You now have " + currency.getCurrency() + " chips remaining.");
						break;
					}
				} catch(InputMismatchException e){
					System.out.println("That isn't an integer!");
			}
		}
	}
	/**
	 * gives each player 2 cards
	 */
	public void dealHandStart(){
		dealerHand.add(deck.deal());
		playerHand.add(deck.deal());
		dealerHand.add(deck.deal());
		playerHand.add(deck.deal());
	}
	/**
	 * hits the player
	 */
	public void dealHandHit(){
		playerHand.add(deck.deal());
	}
	/**
	 * hits the dealer
	 */
	public void dealHandDealerHit(){
		dealerHand.add(deck.deal());
	}
	/**
	 * allows the player to view their hand with the card values, suits, and numeric values
	 */
	public void viewHand(){
		for(int i = 0; i < playerHand.size();i++){
			System.out.print(playerHand.get(i).getValue());
			System.out.print(" of ");
			System.out.println(playerHand.get(i).getSuit());
			
		}
	}
	/**
	 * gets the player hand value 
	 * @return player hand value
	 */
	public int getValue(){
		int total = 0;
		int totalAces=0;
		int counter = 1;
		for(int i = 0; i < playerHand.size();i++){
			counter++;
			total+= playerHand.get(i).getNumValue();
			if(playerHand.get(i).getNumValue() == 11){
				totalAces++;
				total -= 10;
				}
			}
		
		if(totalAces > 0 && total < 11){
			total+=10;
		}
		return total;
	}
	/**
	 * prints the player hand value
	 * @return player hand value
	 */
	public int printValue(){
		int total = 0;
		int totalAces=0;
		for(int i = 0; i < playerHand.size();i++){
			total+= playerHand.get(i).getNumValue();
			if(playerHand.get(i).getNumValue() == 11){
				totalAces++;
				total -= 10;
				}
			}
		if(totalAces > 0 && total < 11){
			total+=10;
		}
		if(hits > 0){
			System.out.println("Your total value is " + total + " against the dealer's " + printValueDealer());
		}
		else{
			System.out.println("Your total value is " + total);
		}
		return total;
	}
	/**
	 * gets and prints the dealers hand value
	 * @return dealer hand value
	 */
	public int getValueDealer(){
		int total = 0;
		int counter = 1;
		int totalAces = 0;
		for(int i = 0; i < dealerHand.size();i++){
			if(playerNoHit()){
				if(counter < 2){
					System.out.print("The dealer's " + counter + "th card's value is ");
					System.out.println(dealerHand.get(i).getNumValue());
				}
				else{
					System.out.println("The dealer's " + counter + "th card is hidden");
				}
			}
			counter++;
			total += dealerHand.get(i).getNumValue();
			if(dealerHand.get(i).getNumValue() == 11){
				totalAces++;
				total -= 10;
				}
			}
		if(totalAces > 0 && total < 11){
			total+=10;
		}
		
		return total;
	}
	/**
	 * gets the dealers hand and checks its value
	 * @return dealer's hand value
	 */
	public int printValueDealer(){
		int total = 0;
		int totalAces = 0;
		for(int i = 0; i < dealerHand.size();i++){
			total += dealerHand.get(i).getNumValue();
			if(dealerHand.get(i).getNumValue() == 11){
				totalAces++;
				total -= 10;
				}
			}
		if(totalAces > 0 && total < 11){
			total+=10;
		}
		return total;
	}
	/**
	 * asks the player if they would like to hit or stay
	 */
	public void hitStay(){
		while(true){
			Scanner scan2 = new Scanner(System.in);
			System.out.println("Would you like to hit or stay?");
			if(printValue() <= 21){
				String hitstay = scan2.nextLine();
				if(hitstay.equals("hit")){
					dealHandHit();
					viewHand();
					System.out.println(printValue());
					
				}
				else if(hitstay.equals("stay")){
					hits++;
					while(printValueDealer() < 17){
						dealerHit();
					}
					bust();
					break;
				}
			}
			else{
				bust();
				break;
			}
		}
	}
	/**
	 * gives the dealer an extra card if they are below a value of 17
	 */
	public void dealerHit(){
		if(printValueDealer() <= 17){
			dealHandDealerHit();
			getValueDealer();
		}
		else{
			bust();
		}
	}
	/**
	 * checks the hands of either player and makes sure that they haven't lost
	 */
	public void bust(){
		while(true){
			int val= printValue();
			int valDeal = printValueDealer();
			if(val > 21){
				lose();
				break;
			}
			else if(val == 21 && playerHand.size() == 2){
				winBlackjack();
				break;
			}
			else if(val > valDeal){
				win();
				break;
			}
			else if(valDeal > 21){
				win();
				break;
			}
			else if(valDeal > val){
				loseDealerVal();
				return;
			}
			else if(val == valDeal && valDeal > 17 && valDeal < 22){
				push();
				break;
			}
			else{
				hitStay();
			}
		}
	}
	/**
	 * alerts the player that they have gone over 21 and then ends the game
	 */
	public void lose(){
		System.out.println("Your card total is over 21!");
		System.out.println("You Lose!");
	}
	/**
	 * alerts the player that the dealer's hand value is higher than theirs and then ends the game
	 */
	public void loseDealerVal(){
		System.out.println("The dealer's card value is higher than yours!");
		printValue();
		System.out.println("You Lose!");
	}
	/**
	 * alerts the player that they have won by getting blackjack and then ends the game
	 */
	public void winBlackjack(){
		System.out.println("You have exactly 21 with 2 cards! BlackJack! You get 1.5x payout!");
		bet = (bet * 2) + (bet/2);
		System.out.println("You gain " + bet + " chips!");
		currency.betAmountGain(bet);
	}
	/**
	 * alerts the play that they have won by beating the dealer without getting blackjack and then ends the game
	 */
	public void win(){
		System.out.println("You beat the dealer! You gain " + bet + "!");
		printValue();
		currency.betAmountGain(bet * 2);
	}
	/**
	 * gives back the player's bet
	 */
	public void push(){
		System.out.println("The dealers value is equal to your value!");
		System.out.println("You get your bet back!");
		currency.betAmountGain(bet);
	}
	/**
	 * asks if the use would like to play again
	 * @return true or false which determines whether or not to play again
	 */
	public boolean playAgain(){
		while(true){
			Scanner scan0 = new Scanner(System.in);
			System.out.println("Would you like to play again?");
			String redo = scan0.nextLine().toLowerCase();
			if(redo.equals("yes")){
				deck.resetDeck();
				playerHand.clear();
				dealerHand.clear();
				hits = 0;
				return true;
			}
			else if(redo.equals("no")){
				return false;
			}
			else{
				System.out.println("That isn't yes or no!");
			}
		}
	}
	/** 
	 * gives your addicted ass more money or breaks your legs depending on the debt variable
	 */
	public void broke(){
		if(currency.getCurrency() == 0){
			System.out.println("The bank will loan you money!");
			System.out.println("Debt increased!");
			
			currency.betAmountGain(100);
			debt += 100;
			if(debt > 500){
				System.out.println("Someone named \"Don\" says you havent been making your payments, thus you got your legs broken!");
				System.out.println("Don't worry, they have a great hospitol.");
			}
		}
	}
	public boolean playerNoHit(){
		if(hits == 0){
			return true;
		}
		else{
			return false;
		}
	}
}
