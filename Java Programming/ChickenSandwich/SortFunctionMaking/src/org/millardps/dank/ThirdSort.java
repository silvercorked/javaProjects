package org.millardps.dank;

import java.util.ArrayList;

public class ThirdSort {
	String c;

	public ThirdSort(ArrayList<String> x) {
		for (int i = 0; i < x.size(); i++) {
			c = x.get(i);

			for (int z1 = 0; z1 < i; z1++) {
				if (x.get(z1).compareTo(c) > 1) {
					System.out.println(x.get(z1)
							+ " was moved to the right by " + c);
					for (int v = 0; v <= z1; v++) {
						System.out.println(x.get(0) +" " + x.get(0).compareTo(c) + " " + c);
						if (z1 - v == 0 && x.get(0).compareTo(c) > 0) {
							x.remove(c);
							x.add(0, c);
						}
						if (x.get(z1 - v).compareTo(c) > 0) {
							x.remove(c);
							x.add(z1 - v, c);
						}
					}
					System.out.println(x);
					System.out.println(i + " and  " + z1);
					System.out.println();
				}
			}
		}
	}
}
