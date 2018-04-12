package org.wissing.CharacterRandomization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class randomizer {
	private String r;
	private String c;
	private String bg;
	private String align;
	private String sr;
	private String sc;
	private int[] statistics;
	private String[] raceNames = {"Dwarf","Elf","Halfling","Gnome","DragonBorn","Human","Half-Elf","Half-Orc","Tiefling"};
	private String[] classNames = {"Barbarian","Bard","Cleric","Druid","Fighter","Monk","Paladin","Ranger","Rogue","Sorcerer","Warlock","Wizard"};
	private String[] bgNames = {"Acolyte","Charaltan","Criminal","Entertainer","Folk Hero","Guild Artisan","Hermit","Noble","Outlander","Sage","Sailor","Soldier","Urchin",};
	private Random rand;
	
	public randomizer(){
		rand = new Random();
		statistics = stats();
		r = race();
		sr = getSubRace(r);
		c = classes();
		sc = getSubClass(c);
		bg = background();
		align = alignment();
	}
	public int[] stats(){
		int[] stats = {stat(),stat(),stat(),stat(),stat(),stat()};
		return stats;
	}
	public String race(){
		int ran = rand.nextInt(9);
		String Race = raceNames[ran];
		return Race;
	}
	public String getSubRace(String x){
		String subRace = null;
		if(x == "Dwarf"){
			String[] all = {"Hill","Mountain"};
			subRace = all[rand.nextInt(2)];
		}
		else if(x == "Elf"){
			String[] all = {"High","Wood","Drow"};
			subRace = all[rand.nextInt(3)];
		}
		else if(x == "Halfling"){
			String[] all = {"Lightfoot","Stout"};
			subRace = all[rand.nextInt(2)];
		}
		else if(x == "Gnome"){
			String[] all = {"Forest","Rock"};
			subRace = all[rand.nextInt(2)];
		}
		return subRace;
	}
	public String classes(){
		String classs = classNames[rand.nextInt(12)];
		return classs;
	}
	public String getSubClass(String x){
		switch(x){
			case "Barbarian": 
				String[] a = {"Path of the Berserker", "Path of the Totem Warrior"};
				return a[rand.nextInt(2)];
			case "Bard":
				String[] b = {"College of Lore", "College of Valor"};
				return b[rand.nextInt(2)];
			case "Cleric":
				String[] c = {"Knowledge Domain", "Life Domain", "Light Domain", "Luck Domain", "Nature Domain", "Tempest Domain", "Trickery Domain", "War Domain"};
				return c[rand.nextInt(8)];
			case "Druid":
				String[] d = {"Circle of the Beast", "Circle of the Land","Circle of the Moon"};
				return d[rand.nextInt(3)];
			case "Fighter":
				String[] e = {"Champion","Battle Master","Eldritch Knight"};
				return e[rand.nextInt(3)];
			case "Monk":
				String[] f = {"Way of the Open Hand", "Way of Shadow", "Way of Four Elements"};
				return f[rand.nextInt(3)];
			case "Paladin":
				String[] g = {"Oath of Devotion", "Oath of Ancients", "Oath of Vengeance"};
				return g[rand.nextInt(3)];
			case "Ranger":
				String[] h = {"Hunter", "Beast Master"};
				return h[rand.nextInt(2)];
			case "Rogue":
				String[] i = {"Theif","Assassin","Arcane Trickster"};
				return i[rand.nextInt(3)];
			case "Sorcerer":
				String[] j = {"Draconic Bloodline","Wild Magic"};
				return j[rand.nextInt(2)];
			case "Warlock":
				String[] k = {"Pact of the Archfey","Pact of the Fiend","Pact of the Great Old One"};
				return k[rand.nextInt(3)];
			case "Wizard":
				String[] l = {"School of Abjuration", "School of Divination", "School of Enchantment", "School of Evocation", "School of Illusion", "School of Necromancy", "School of Transmutation"};
				return l[rand.nextInt(7)];
			default: return "Something messed up in subclass";
		}
	}
	public String background(){
		return bgNames[rand.nextInt(13)];
	}
	private int stat(){
		int a = rand.nextInt(5) + 1;
		int b = rand.nextInt(5) + 1;
		int c = rand.nextInt(5) + 1;
		int d = rand.nextInt(5) + 1;
		int e = rand.nextInt(5) + 1;
		ArrayList<Integer> all = new ArrayList<Integer>();
		all.add(a);all.add(b);all.add(c);all.add(d);all.add(e);
		int sum = 0;
		for(int i = 0; i < 3; i++){
			sum += Collections.max(all);
			all.remove(Collections.max(all));
		}
		return sum;
	}
	public String alignment(){
		String[] a = {"Good", "Evil", "Neutral"};
		String[] b = {"Lawful", "Chaotic", "Neutral"};
		return a[rand.nextInt(3)] + " " + b[rand.nextInt(3)];
	}
	public String getClasss(){return c;}
	public String getRace(){return r;}
	public String getBG(){return bg;}
	public String getAlign(){return align;}
	public int[] getStatistics(){return statistics;}
	public String getRealSubClass(){return sc;}
	public String getRealSubRace(){return sr;}
}