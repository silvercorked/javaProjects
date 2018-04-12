package org.millardps.dank;

import java.util.ArrayList;

public class SecondSort {

	public SecondSort(ArrayList<String> x){
		ArrayList<String> m = new ArrayList<>();
		int a = 0;
		int n = x.size()-2;
		for(int i = 0; i <= x.size()-1; i++){
			String c = x.get(0);
			for(String z : x){
				
				if(c.compareTo(z) < 1 && !(m.contains(z))){
					System.out.println(c.compareTo(z));
					c = z;
				}
			}
			String v = c;
			System.out.println(v);
			a++;
			String b = x.get(a);
			if(b == v || m.contains(b)){
				b = x.get(1);
			}
			System.out.println(b);
	
			x.remove(x.indexOf(v));
			x.remove(b);
			System.out.println(x);
			
			x.add(n, v);
			x.add(n, b);
			n--;
			m.add(v);
			System.out.println(m);
			System.out.println(x);
			if(n == -1){
				return;
			}
			
			
			}
	}
}
