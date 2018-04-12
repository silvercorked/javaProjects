package org.millardps.dank;

import java.util.ArrayList;
import java.util.Collections;


public class FourthSort {
	
	public FourthSort(ArrayList<String> x){
		boolean swapped;
		do{
			swapped = false;
			for(int i = 0; i < x.size()-1; i++){
				if(x.get(i).compareTo(x.get(i+1)) > 1){
					//System.out.println(x.get(i).compareTo(x.get(i+1)));
					Collections.swap(x, i, i+1);
					System.out.println(x);
					swapped = true;
				}
			}
		}while(swapped);
	}
}

