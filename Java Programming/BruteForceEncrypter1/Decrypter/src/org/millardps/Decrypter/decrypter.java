package org.millardps.Decrypter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class decrypter{
	private String[] b={"the", "one", "ate", "eat", "are", "the", "pet", "set", "bet", "met", "you", "and", "big", "bad", "bed", "bag", "rag", "tag", "car", "war"};
	private Cipher bill = new Cipher();
	public decrypter(){
		removeShift(bill.getFinalEncrypt());
	}
	public void removeShift(String x){System.out.println(x);
	File f = new File("/newTextFile.txt");
	String[] m = x.split(" ");
	try{
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i = 0; i < m.length; i++){
			for(int z = 0; z < b.length; z++){
				if(m[i].length() == 3){
					System.out.println(m[i]);
					int s = ((int)m[i].charAt(0));
					int s1 = ((int)m[i].charAt(1));
					int s2 = ((int)m[i].charAt(2));
					for(String w : b){
						int a = ((int)w.charAt(0));
						int a1 = ((int)w.charAt(1));
						int a2 = ((int)w.charAt(2));
						int v1 = a - s;int v2 = a1 - s1;
						int v3 = a2 - s2;System.out.println(v1);
						System.out.println(v2);
						System.out.println(v3);
						String content = performShift(v1, v2, v3, x);
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
			}catch(IOException E){System.out.println("It broke");
		}
	}
	public String performShift(int n, int m, int o, String p){
		String g="";int u=0;int[] t = { n, m, o};
		for(int i = 0; i<p.length();i++){
			if(p.charAt(i)==' '){g+= p.charAt(i);
			}
			else{
				int l= (int)p.charAt(i);
				l+=t[u];	
				u++;
				if(u>2){
					u=0;
					}
				if (l >122){
					l-=97;l=l%26;l+=97;
				}
				char d= (char)l; 
				g+=d;
				}
			}
		System.out.println("The new message is ...");
		System.out.println(g);
		return g;
		}
	}