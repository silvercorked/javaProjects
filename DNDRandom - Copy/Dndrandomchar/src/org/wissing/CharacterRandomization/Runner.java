package org.wissing.CharacterRandomization;

public class Runner {

	public static void main(String[] args) {
		randomizer alex = new randomizer();
		
		Race r = new Race(alex.getRace(), alex.getClasss(), alex.getBG(), alex.getAlign(), alex.getStatistics(), alex.getRealSubClass(), alex.getRealSubRace());
	}

}