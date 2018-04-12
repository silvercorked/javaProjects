package org.millardps.Tictactoe;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.err.println("How big bro?");
		int size = scan.nextInt();
		TictackToe billy = new TictackToe(size);
	}
}
