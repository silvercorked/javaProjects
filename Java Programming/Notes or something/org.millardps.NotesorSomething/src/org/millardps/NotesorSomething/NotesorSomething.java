package org.millardps.NotesorSomething;

import java.util.Random;

public class NotesorSomething {
	public static void main(String[] args) {
		for(int i = 0; i < 100; changeNumber(i)){
			System.out.println(i);
			System.out.println(changeNumber(i));
		}
	}
	public static int changeNumber(int s){
		Random rand = new Random();
		int b = rand.nextInt(100);
		if(b < 50){
			s--;
		}
		else{
			s++;
		}
		return s;
	}
	
}

