package org.millardps.Decrypter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cipher {
	private String message;
	private String finalEncrypt;
	private int[] shift= new int[3];
	private Scanner scan= new Scanner(System.in); 
	private Scanner scan2= new Scanner(System.in); 
	
	public Cipher(){
		message=getMessage();
		getShift();
		performShift();
	}
	
	/**
	 * Asks the user for a message and checks to make sure it contains only letters and spaces. 
	 * @return A String containing only lowercase letters and spaces 
	 */
	public String getMessage(){
		String myMessage=""; 
		while(true){
			System.out.println("What message would you like to encrypt?");
			myMessage= scan.nextLine().toLowerCase();
			boolean useIt= true;
			for(int i =0; i<myMessage.length();i++){
				if(!(((int)myMessage.charAt(i)>=97) && ((int)myMessage.charAt(i)<=122)) && myMessage.charAt(i) != ' '){
					useIt=false;
				}
			}
			if(useIt){
				break;
			}
			else{
				System.out.println("The message may only contain letters and spaces.");
			}
		}
		return myMessage; 
	}
	
	/**
	 * Retrieves three numbers from the user and stores them into an int array.
	 */	
	public void getShift(){
		while(true){
			try{
				for(int i =0; i <3;i++){
					System.out.println("shift #" + (i+1) + "/3?");
					shift[i]= scan2.nextInt();
				}
				break;
			}
			catch(InputMismatchException e){
				System.out.println("Only numbers.");
				scan2.next();
			}
		}
	}
	
	/**
	 * Uses the int array and message to encrypt the message and print it out for the user. 
	 */
	public void performShift(){
		String encrypted="";
		int count=0;
		for(int i = 0; i<message.length();i++){
			if(message.charAt(i)==' '){
				encrypted+= message.charAt(i);
			}
			else{
				int temp= (int)message.charAt(i); 
				temp+=shift[count];
				count++;
				if(count>2){
					count=0;
				}
				if (temp >122){
					temp-=97;
					temp=temp%26;
					temp+=97; 
				}
				char next= (char)temp; 
				encrypted+=next; 
			}
		}
		finalEncrypt=encrypted;
		System.out.println("The new message is ...");
		System.out.println(encrypted);
	}
	public String getFinalEncrypt(){
		return finalEncrypt;
	}
}
