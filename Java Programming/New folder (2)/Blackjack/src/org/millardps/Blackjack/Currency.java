package org.millardps.Blackjack;

public class Currency {
	//attributes
	private int value;
	
	//constructor
	public Currency(){
		value = 100;
	}
	//methods
	/**
	 * removes the bet amount for currency
	 */
	public void betAmountRemove(int x){
		value = value - x;
		
	}
	/**
	 * places the winnings in currency
	 */
	public void betAmountGain(int x){
		value = value + x;
	}
	public int getCurrency(){
		return this.value;
	}
}
