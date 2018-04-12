package org.millardps.miniProjects;

public class randomThing {
	private String xx = "this is an example.i";
	private String yy = "is";
	public randomThing(){
		thing(xx,yy);
	}
	public int thing(String x, String y){
		int count = 0;
		//while(x.contains(y)){
			//x.replaceFirst(y,"");
			//count++;
			//System.out.println(x);
		//}
		for(int i = 0; i < x.length(); i++){
			if(x.substring(i, x.length()).length() < y.length()){
				break;
			}
			else{
				if(x.substring(i, y.length()).equals(y)){
					count++;
				}
			}
		}
		return count;
	}
}
