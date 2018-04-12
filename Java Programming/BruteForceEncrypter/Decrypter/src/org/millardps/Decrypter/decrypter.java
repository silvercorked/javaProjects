package org.millardps.Decrypter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class decrypter {
	private String[] basics = {"the", "one", "ate", "eat", "are", "the", "pet", "set", "bet", "met", "you", "and", "big", "bad", "bed", "bag", "rag", "tag", "car", "war"};
	private Cipher bill = new Cipher();
	public decrypter(){
		
		removeShift(bill.getFinalEncrypt());
		
	}
	
	public void removeShift(String xx){
		System.out.println(xx);
		File file = new File("/newTextFile.txt");
		String[] newMessage = xx.split(" ");
		try{
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i = 0; i < newMessage.length; i++){
			for(int z = 0; z < basics.length; z++){
				if(newMessage[i].length() == 3){
					System.out.println(newMessage[i]);
					int something = ((int)newMessage[i].charAt(0));
					int something1 = ((int)newMessage[i].charAt(1));
					int something2 = ((int)newMessage[i].charAt(2));
					for(String word : basics){
						int someStuff = ((int)word.charAt(0));
						int someStuff1 = ((int)word.charAt(1));
						int someStuff2 = ((int)word.charAt(2));
						int firstShift = someStuff - something;
						int secondShift = someStuff1 - something1;
						int thirdShift = someStuff2 - something2;

						System.out.println(firstShift);
						System.out.println(secondShift);
						System.out.println(thirdShift);
						String content = performShift(firstShift, secondShift, thirdShift, xx);
						try{
						
						bw.write(content + "            ");
						} catch(IOException E){
							System.out.println("It dun brokeded");
							System.out.println(E);
						}
					}
				}		
			}		
		}

		
		bw.close();
		}catch(IOException E){
			System.out.println("It broke");
		}
		//find 3 letter words, then find the index of the first letter of that words, then find shift for each letter in that compared to the array of Strings above (basics), then reverse the shift. write to buffered writer, need to continueally refresh
	}
	public String performShift(int first, int second, int third, String message){
		String encrypted="";
		int count=0;
		int[] shift = { first, second, third};
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
		System.out.println("The new message is ...");
		System.out.println(encrypted);
		System.out.println();
		System.out.println();
		return encrypted;
		
	}
}
