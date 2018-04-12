package org.millardps.Matrix;

import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) {
		// Matrix basically is an arraylist array[of[arrays][][][]]
		int [][] matrice1 = new int[3][3];
		int [][] matrice2 = {{1,2,3},{1,2,3},{1,2,3}};
		
		for(int rows = 0; rows < matrice2.length; rows++){
			for(int cols = 0; cols < matrice2[rows].length; cols++){
				System.out.print(matrice2[rows][cols]);
			}
		}
		System.out.println("");

		
	}
//	public static void removedups(){
//		ArrayList<Integer> myData = new ArrayList<Integer>();
//		myData.add(2);
//		myData.add(7);
//		myData.add(5);
//		myData.add(5);
//		myData.add(5);
//		myData.add(5);
//		myData.add(6);
//		myData.add(6);
//		myData.add(3);
//		myData.add(3);
//		myData.add(3);
//		int k = 1;
//		while(k < myData.size()){
//			if(myData.get(k).equals(myData.get(k-1))){
//				myData.remove(k);
//			}
//			else{
//				k++;	
//			}
//			
//		}
//		System.out.println(myData);
//	}

}
