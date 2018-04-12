package org.wissing.CharacterRandomization;

import java.util.Random;

public class Race extends Classes {
	private String race;
	private String classs;
	private String backG;
	private String align;
	private Random rand;
	private String subRace;
	private String subClass;
	private int HP;
	private String[] simpleMelee = new String[10];
	private String[] simpleMeleeDamage = new String[10];
	private String[] simpleMeleeProp = new String[10];
	private String[] simpleRanged = new String[4];
	private String[] simpleRangedDamage = new String[4];
	private String[] simpleRangedProp = new String[4];
	private String[] martialMelee = new String[18];
	private String[] martialMeleeDamage = new String[18];
	private String[] martialMeleeProp = new String[18];
	private String[] martialRanged = new String[5];
	private String[] martialRangedDamage = new String[5];
	private String[] martialRangedProp = new String[5];
	private String[] burglars = new String[14];
	private String[] diplomats = new String[11];
	private String[] dungeoneers = new String[9];
	private String[] entertainers = new String[7];
	private String[] explorers = new String[8];
	private String[] priests = new String[10];
	private String[] scholars = new String[7];
	private int gold;
	public Race(String r, String c, String bg, String a, int[] st, String sc, String sr){
		createInventoryTables();
		Statistics = st;
		rand = new Random();
		race = r;
		classs = c;
		backG = bg;
		align = a;
		subRace = sr;
		subClass = sc;
		HP = 0;
		gold = getGold(c);
		System.out.println("BASE STATS:");
		this.printStats();
		System.out.println("MODIFIERS:");
		modif = getModifiers();
		this.prinModifiers();
		System.out.println(hitDice(1));
		System.out.println(HP(1) + " is your max hp at level " + 1);
		System.out.println(getProficiencyBonus(1) + " is your proficiency bonus");
		System.out.println("Saving throws are:");
		System.out.println(getSavingThrows());
		System.out.print("Race: ");
		if(subRace == null){System.out.println(race);}
		else{System.out.println(subRace + " " + race);};
		System.out.print("Class:");
		System.out.println(classs);
		System.out.print("Sub-Class:");
		System.out.println(subClass);
		System.out.print("BackGround:");
		System.out.println(backG);
		System.out.print("Alignment:");
		System.out.println(align);
		if(spellCastingAbility(c, sc) == "none"){
			System.out.println("You are not a spell caster, so you don't have a spell casting ability or a spell attack bonus");
		}
		else{
			System.out.println("Your spell casting ability is " + spellCastingAbility(c, sc));
			System.out.println("Your spell attack bonus is " + spellAttackBonus(spellCastingAbility(c, sc)));
			System.out.println("Your spell save DC is " + spellSaveDC(c, sc));
		}
		System.out.print("Speed:");
		System.out.println(speed(race,subRace));
		System.out.print("Age:");
		System.out.println(age(race) + " years old");
		System.out.print("Weight:");
		System.out.println(weight(race) + " lbs");
		System.out.print("Height:");
		System.out.println(heightInch(height(race)) + "\"" + " (feet'inches\")");
		printInventory(c);
		
	}
	public void addStatBonuses(){
		switch (race) {
			case "Dwarf" :
				Statistics[2] += 2;
				break;
			case "Elf" :
				Statistics[1] += 2;
				break;
			case "Halfling" :
				Statistics[1] += 2;
				break;
			case "Human" :
				for(int stat : Statistics){
					stat += 1;
				}
				break;
			case "DragonBorn" :
				Statistics[0] += 2;
				Statistics[5] += 1;
				break;
			case "Gnome" :
				Statistics[3] += 2;
				break;
			case "Half-Elf" :
				Statistics[5] += 2;
				System.out.println("You get to add 2 points to whichever ability score you would like because you are a half-elf");
				break;
			case "Half-Orc" :
				Statistics[0] += 2;
				Statistics[2] += 1;
				break;
			case "Tiefling" :
				Statistics[3] += 1;
				Statistics[5] += 2;
				break;
		}
		switch (subRace) {
			case "Hill" : 
				Statistics[4] += 1;
			case "Mountain" :
				Statistics[0] += 2;
			case "High" :
				Statistics[3] += 1;
			case "Wood" :
				Statistics[4] += 1;
			case "Drow" :
				Statistics[5] += 1;
			case "Lightfoot" :
				Statistics[5] += 1;
			case "Stout" :
				Statistics[2] += 1;
			case "Forest" :
				Statistics[1] += 1;
			case "Rock" :
				Statistics[2] += 1;
		}
	}
	public String hitDice(int lvl){
		System.out.println("Your hit dice are...");
		switch (classs){
			case "Barbarian" : 
				return "" + lvl + "d12 + Constitution (min 7)";
			case "Bard" :
			case "Cleric" :
			case "Druid" :
			case "Monk" :
			case "Rogue" :
			case "Warlock" :
				return "" + lvl + "d8 + Constitution (min 5)";
			case "Fighter" :
			case "Paladin" :
			case "Ranger" :
				return "" + lvl + "d10 + Constitution (min 6)";
			case "Sorcerer" :
			case "Wizard" :
				return "" + lvl + "d6 + Constitution (min 4)";
			default : 
				return "Something got messed up on hit dice";
		}
	}
	public int hitDiceInt(int lvl){
		switch (classs){
			case "Barbarian" : 
				return 12;
			case "Bard" :
			case "Cleric" :
			case "Druid" :
			case "Monk" :
			case "Rogue" :
			case "Warlock" :
				return 8;
			case "Fighter" :
			case "Paladin" :
			case "Ranger" :
				return 10;
			case "Sorcerer" :
			case "Wizard" :
				return 6;
			default : 
				return 0;
		}
	}
	public int HP(int lvl){
		int sum = 0;
		int max = hitDiceInt(lvl);
		for(int i = 0; i < lvl; i++){
			int amount = rand.nextInt(max+1);
			if(subRace != "Hill"){
				if(amount < (max/2)+1){
					i--;
					sum-= amount;
				}
			}
			else {
				if(amount < (max/2)+2){
					i--;
					sum-= amount;
				}
			}
			sum+=amount;
		};
		return sum;
	}
	public int getProficiencyBonus(int lvl){
		if(lvl > 0 && lvl <=4){
			return 2;
		}
		else if(lvl >4 && lvl <=8){
			return 3;
		}
		else if(lvl > 8 && lvl <= 12){
			return 4;
		}
		else if(lvl > 12 && lvl <= 16){
			return 5;
		}
		else{
			return 6;
		}
	}
	public int speed(String r, String sr){
		System.out.print("!!!!! These are unmodified base move speeds, they are probably correct but im not sure !!!!!");
		switch (r) {
			case "Human" :
			case "DragonBorn" :
			case "Half-Elf" :
			case "Half-Orc" :
			case "Tiefling" :
			case "Elf" :
				if(sr == "Wood"){
					return 35;
				}
				else{
					return 30;
				}
			case "Halfling" :
			case "Gnome" :
			case "Dwarf" :
				return 25;
			default :
				return 0;
		}
	}
	public int[] getModifiers(){
		int[] mods = new int[6];
		for(int i = 0; i < mods.length; i++){
			mods[i] = (Statistics[i] >= 10) ? (int) Math.floor(((Statistics[i]-10)/2)): (int) Math.floor(((Statistics[i]-11)/2));
		}
		return mods;
	}
	public String getSavingThrows(){
		System.out.println("!!!!! This does not consider Feats !!!!!");
		switch (classs){
			case "Barbarian" : 
			case "Fighter" :
				return "Strength and Constitution";
			case "Bard" :
				return "Dexterity and Charisma";
			case "Cleric" :
			case "Paladin" :
			case "Warlock" :
				return "Wisdom and Charisma";
			case "Druid" :
			case "Wizard" :
				return "Intelligence and Wisdom";
			case "Monk" :
			case "Ranger" :
				return "Strength and Dexterity";
			case "Rogue" :
				return "Dexterity and Intelligence";
			case "Sorcerer" :
				return "Constitution and Charisma";
			default : 
				return "Something got messed up on saving throws";
		}
	}
	public Double height(String r){
		switch (r) {
			case "Dwarf" :
				return (9 + rand.nextGaussian())/2;
			case "Elf" :
				return (21 + rand.nextGaussian())/4;
			case "Halfling" :
				return (3 + rand.nextGaussian());
			case "Human" :
				return (12 + rand.nextGaussian())/2;
			case "DragonBorn" :
				return (14.2 + rand.nextGaussian())/2;
			case "Gnome" :
				return (3.5 + rand.nextGaussian());
			case "Half-Elf" :
				return (5.5 + rand.nextGaussian());
			case "Half-Orc" :
				return (6.5 + rand.nextGaussian());
			case "Tiefling" :
				return (11.4 + rand.nextGaussian())/2;
			default :
				return 0.0;
		}
	}
	public Double weight(String r){
		switch (r) {
			case "Dwarf" :
				return ((150 * 50) + rand.nextGaussian())/50;
			case "Elf" :
				return ((123 * 40) + rand.nextGaussian())/40;
			case "Halfling" :
				return ((30 * 5) + rand.nextGaussian())/5;
			case "Human" :
				return ((170 * 100) + rand.nextGaussian())/100;
			case "DragonBorn" :
				return ((250 * 50) + rand.nextGaussian())/50;
			case "Gnome" :
				return ((40 * 5) + rand.nextGaussian())/5;
			case "Half-Elf" :
				return ((170 * 70) + rand.nextGaussian())/70;
			case "Half-Orc" :
				return ((230 * 40) + rand.nextGaussian())/40;
			case "Tiefling" :
				return ((150 * 40) + rand.nextGaussian())/40;
			default :
				return 0.0;
		}
	}
	public int age(String r){
		int age = 0;
		switch (r) {
			case "Dwarf" :
				age = rand.nextInt(230) + 10;
				if(age < 50){ System.out.println("You are considered a child amonst Dwarfs");};
				break;
			case "Elf" :
				age = rand.nextInt(670) + 20;
				if(age < 100){ System.out.println("You are considered a child amonst Elfs");};
				break;
			case "Halfling" :
				age = rand.nextInt(135) + 10;
				if(age < 20){ System.out.println("You are considered a child amonst Halflings");};
				break;
			case "Human" :
				age = rand.nextInt(90) + 7;
				if(age < 16){ System.out.println("You are considered a child amonst Humans");};
				break;
			case "DragonBorn" :
				age = rand.nextInt(70) + 6;
				if(age < 15){ System.out.println("You are considered a child amonst the DragonBorn");};
				break;
			case "Gnome" :
				age = rand.nextInt(430) + 15;
				if(age < 40){ System.out.println("You are considered a child amonst Gnomes");};
				break;
			case "Half-Elf" :
				age = rand.nextInt(165) + 10;
				System.out.println(age);
				if(age < 20){ System.out.println("You are considered a child amonst Half-Elfs");};
				break;
			case "Half-Orc" :
				age = rand.nextInt(60) + 10;
				if(age < 14){ System.out.println("You are considered a child amonst Half-Orcs");};
				break;
			case "Tiefling" :
				age = rand.nextInt(100) + 8;
				if(age < 16){ System.out.println("You are considered a child amonst Tieflings");};
				break;
		}
		return age;
	}
	public String heightInch(Double h){
		long feet = (long) Math.floor(h);
		Double inchf = (h - feet) * 12;
		String inch = Double.toString(inchf);
		if(inchf < 10.0){inch = inch.substring(0, 3);}
		else{inch = inch.substring(0, 4);};
		System.out.print(feet + "\'");
		return inch;
	}
	public void printInventory(String c){
		System.out.println("!!!!! Now Randomizing inventory and printing constraints !!!!!");
		switch (c) {
			case "Barbarian" :
				System.out.println("(a) a greataxe or (b) any martial melee weapon");
				System.out.println("(a) two handaxes or (b) any simple weapon");
				System.out.println("An explorer's pack and four javelins");
				if(rand.nextBoolean()){
					System.out.println(printInventoryEntry(3, true, false));
				}
				else{ 
					int x= rand.nextInt(18);
					System.out.println(printInventoryEntry(x, true, false));
				}
				if(rand.nextBoolean()){
					System.out.println("2x " + printInventoryEntry(3, true, true));
				}
				else{
					int y = rand.nextInt(10);
					System.out.println(printInventoryEntry(y, true, true));
				}
				System.out.println("An explorer's pack consisting of ");
				printPack(explorers);
				System.out.println("And 4x " + printInventoryEntry(4, true, true));
				System.out.println("background items:");
				printPack(backGroundItems(backG, gold));
				break;
			case "Bard":
				System.out.println("(a) a rapier, (b) a longsword, or (c) any simple weapon");
                System.out.println("(a) a diplomat's pack or (b) an entertainer's pack");
                System.out.println("(a) a lute or (b) any other musical instrument");
                System.out.println("Leather armor and a dagger");
                if(rand.nextInt(3) == 2){
                	if(rand.nextBoolean()){
                		int x = rand.nextInt(10);
                    	System.out.println(printInventoryEntry(x, true, true));
                	}
                	else{
                		int x = rand.nextInt(4);
                		System.out.println(printInventoryEntry(x, false, true));
                	}
                }
                else if(rand.nextBoolean()){
                	System.out.println(printInventoryEntry(11, true, false));
                }
                else{
                	System.out.println(printInventoryEntry(7, true, false));
                }
                if(rand.nextBoolean()){
                	System.out.println("A diplomat's pack consisting of ");
                	printPack(diplomats);
                }
                else{
                	System.out.println("A entertainer's pack consisting of ");
                	printPack(entertainers);
                }
                if(rand.nextBoolean()){
                	System.out.println("A Lute");
                }
                else{
                	System.out.println("Any musical intrument");
                }
                System.out.println("Leather armor and a dagger");
                printPack(backGroundItems(backG, gold));
                break;
			case "Cleric" :
				System.out.println("(a) a mace or (b) a warhammer (if proficient)");
				System.out.println("(a) scale mail, (b) leather armor, or (c) chain mail (if proficient)");
				System.out.println("(a) a light erossbow and 20 bolts or (b) any simple weapon");
				System.out.println("(a) a priest's pack or (b) an explorer's pack");
				System.out.println("shield and a holy symbol");
				if(rand.nextBoolean()){
					System.out.println(printInventoryEntry(6, true, true));
				}
				else{
					System.out.println(printInventoryEntry(16, true, false));
				}
				if(rand.nextInt(3) == 2){
					System.out.println("Scale Mail");
				}
				else if(rand.nextBoolean()){
					System.out.println("Leather Armor");
				}
				else{
					System.out.println("Chain Mail");
				}
				if(rand.nextBoolean()){
					System.out.println(printInventoryEntry(0, false, true) + " and 20 bolts");
				}
				else{
					if(rand.nextBoolean()){
						int x = rand.nextInt(10);
						System.out.println(printInventoryEntry(x, true, true));
					}
					else{
						int x = rand.nextInt(4);
						System.out.println(printInventoryEntry(x, false, true));
					}
				}
				if(rand.nextBoolean()){
					System.out.println("A priest's pack consisting of ");
					printPack(priests);
				}
				else{
					System.out.println("An explorer's pack consisting of ");
					printPack(explorers);
				}
				System.out.println("shield and a holy symbol");
				printPack(backGroundItems(backG, gold));
				break;
			case "Druid" :
				System.out.println("(a) a wooden shield or (b) any simple weapon");
				System.out.println("(a) a scimitar or (b) any simple melee weapon");
				System.out.println("Leather armor, an explorer's pack, and a druidic focus");
				if(rand.nextBoolean()){
					if(rand.nextBoolean()){
                		int x = rand.nextInt(10);
                    	System.out.println(printInventoryEntry(x, true, true));
                	}
                	else{
                		int x = rand.nextInt(4);
                		System.out.println(printInventoryEntry(x, false, true));
                	}
				}
				else{
					System.out.println("A wooden shield");
				}
				if(rand.nextBoolean()){
					int x = rand.nextInt(10);
                	System.out.println(printInventoryEntry(x, true, true));
				}
				else{
					System.out.println(printInventoryEntry(12, true, false));
				}
				System.out.println("An explorer's pack consisting of ");
				printPack(explorers);
				System.out.println("Leather armor and a druidic focus");
				printPack(backGroundItems(backG, gold));
				break;
			case "Fighter" :
				System.out.println("(a) chain mail or (b) leather, longbow, and 20 arrows");
				System.out.println("(a) a martial weapon and a shield or (b) two martial weapons");
				System.out.println("(a) a Light crossbow and 20 bolts or (b) two handaxes");
				System.out.println("(a) a dungeoneer's pack or (b) an explorer's pack");
				if(rand.nextBoolean()){
					System.out.println("Chain Mail");
				}
				else{
					System.out.println("Leathor Armor " + printInventoryEntry(3, false, false) + " 20 arrows");
				}
				if(rand.nextBoolean()){
					if(rand.nextBoolean()){
                		int x = rand.nextInt(18);
                    	System.out.println(printInventoryEntry(x, true, true));
                	}
                	else{
                		int x = rand.nextInt(5);
                		System.out.println(printInventoryEntry(x, false, true));
                	}
					System.out.println("A Shield");
				}
				else{
					for(int i = 0; i < 2; i++){
						if(rand.nextBoolean()){
	                		int x = rand.nextInt(10);
	                    	System.out.println(printInventoryEntry(x, true, true));
	                	}
	                	else{
	                		int x = rand.nextInt(4);
	                		System.out.println(printInventoryEntry(x, false, true));
	                	}
					}
				}
				if(rand.nextBoolean()){
					System.out.println(printInventoryEntry(0, false, true) + " and 20 bolts");
				}
				else{
					System.out.println("2x " + printInventoryEntry(3, true, true));
				}
				if(rand.nextBoolean()){
					System.out.println("A dungeoneer's pack consisting of ");
					printPack(dungeoneers);
				}
				else{
					System.out.println("A explorer's pack consisting of ");
					printPack(explorers);
				}
				printPack(backGroundItems(backG, gold));
				break;
			case "Monk" :
				System.out.println("(a) a shortsword or (b) any simple weapon");
                System.out.println("(a) a dungeoneer's pack or (b) an explorer's pack");
                System.out.println("10 darts");
                if(rand.nextBoolean()){
                	System.out.println(printInventoryEntry(13, true, false));
                }
                else{
                	if(rand.nextBoolean()){
                		int x = rand.nextInt(10);
                    	System.out.println(printInventoryEntry(x, true, true));
                	}
                	else{
                		int x = rand.nextInt(4);
                		System.out.println(printInventoryEntry(x, false, true));
                	}
                }
                if(rand.nextBoolean()){
					System.out.println("A dungeoneer's pack consisting of ");
					printPack(dungeoneers);
				}
				else{
					System.out.println("A explorer's pack consisting of ");
					printPack(explorers);
				}
                System.out.println("10 darts");
                printPack(backGroundItems(backG, gold));
                break;
			case "Paladin" :
				System.out.println("(a) a martial weapon and a shield or (b) two martial weapons");
				System.out.println("(a) five javelins or (b) any simple melee weapon");
				System.out.println("(a) a priest's pack or (b) an explorer's pack");
                System.out.println("Chain mail and a holy symbol");
                if(rand.nextBoolean()){
                	int x = rand.nextInt(18);
                	System.out.println(printInventoryEntry(x, true, true) + " and a shield");
                }
                else{
                	for(int i = 0; i < 2; i++){
                		int x = rand.nextInt(18);
                    	System.out.println(printInventoryEntry(x, true, true));
                	}
                }
                if(rand.nextBoolean()){
                	System.out.println("5x " + printInventoryEntry(4, true, true));
                }
                else{
                	int x = rand.nextInt(10);
                	System.out.println(printInventoryEntry(x, true, true));
                }
                if(rand.nextBoolean()){
					System.out.println("A priest's pack consisting of ");
					printPack(priests);
				}
				else{
					System.out.println("An explorer's pack consisting of ");
					printPack(explorers);
				}
                System.out.println("Chain mail and a holy symbol");
                printPack(backGroundItems(backG, gold));
                break;
			case "Ranger" :
				System.out.println("(a) scalemail or (b) leather armor");
                System.out.println("(a) two shortswords or (b) two simple melee weapons");
                System.out.println("(a) a dungeoneer's pack or (b) an explorer's pack");
                System.out.println("A longbow and a quiver of 20 arrows");
                if(rand.nextBoolean()){
                	System.out.println("Scale Mail");
                }
                else{
                	System.out.println("Leather Armor");
                }
                if(rand.nextBoolean()){
                	System.out.println("2x " +printInventoryEntry(13, true, false));
                }
                else{
                	for(int i = 0; i < 2; i++){
                		int x = rand.nextInt(10);
                    	System.out.println(printInventoryEntry(x, true, true));
                	}
                }
                if(rand.nextBoolean()){
                	System.out.println("A dungeoneer's pack consisting of ");
                	printPack(dungeoneers);
                }
                else{
                	System.out.println("An Explorer's pack consisting of ");
                	printPack(explorers);
                }
                System.out.println("A longbow and a quiver of 20 arrows");
                printPack(backGroundItems(backG, gold));
                break;
			case "Rogue" :
				System.out.println("(a) a rapier or (b) a shortsword");
				System.out.println("(a) a shortbow and quiver of 20 arrows or (b) a shortsword");
				System.out.println("(a) a burglar's pack, (b) a dungeoneer's pack, or (c) an explorer's pack");
				System.out.println("Leather armor, two daggers, and thieves' tools");
				if(rand.nextBoolean()){
					System.out.println(printInventoryEntry(11, true, false));
				}
				else{
					System.out.println(printInventoryEntry(13, true, false));
				}
				if(rand.nextBoolean()){
					System.out.println(printInventoryEntry(13, true, false));
				}
				else{
					System.out.println(printInventoryEntry(2, false, true) + " and 20 arrows");
				}
				if(rand.nextInt(3) == 2){
					System.out.println("A burglar's pack consisting of");
					printPack(burglars);
				}
				else if(rand.nextBoolean()){
					System.out.println("A dungeoneer's pack consisting of");
					printPack(dungeoneers);
				}
				else{
					System.out.println("A explorer's pack consisting of");
					printPack(explorers);
				}
				System.out.println("Leather armor, two daggers, and thieves' tools");
				printPack(backGroundItems(backG, gold));
				break;
			case "Sorcerer" :
				System.out.println("(a) a light crossbow and 20 bolts or (b) any simple weapon");
				System.out.println("(a) a component pouch or (b) an arcane focus");
				System.out.println("(a) a dungeoneer's pack or (b) an explorer's pack");
				System.out.println("Two daggers");
				if(rand.nextBoolean()){
					System.out.println(printInventoryEntry(0, false, true) + " and 20 bolts");
				}
				else{
					int x = rand.nextInt(10);
                	System.out.println(printInventoryEntry(x, true, true));
				}
				if(rand.nextBoolean()){
					System.out.println("A component pouch");
				}
				else{
					System.out.println("An Arcane Focus");
				}
				if(rand.nextBoolean()){
					System.out.println("A dungeoneer's pack consisting of");
					printPack(dungeoneers);
				}
				else{
					System.out.println("A explorer's pack consisting of");
					printPack(explorers);
				}
				System.out.println("Two daggers");
				printPack(backGroundItems(backG, gold));
				break;
			case "Warlock" :
				System.out.println("(a) a lighl crossbow and 20 bolts or (b) any simple weapon");
				System.out.println("(a) a component pouch or (b) an arcane focus");
				System.out.println("(a) a scholar's pack or (b) a dungeoneer's pack");
				System.out.println("Lealher armor, any simple weapon, and two daggers");
				if(rand.nextBoolean()){
					System.out.println(printInventoryEntry(0, false, true) + " and 20 bolts");
				}
				else{
					int x = rand.nextInt(10);
                	System.out.println(printInventoryEntry(x, true, true));
				}
				if(rand.nextBoolean()){
					System.out.println("A component pouch");
				}
				else{
					System.out.println("An Arcane Focus");
				}
				if(rand.nextBoolean()){
					System.out.println("A dungeoneer's pack consisting of");
					printPack(dungeoneers);
				}
				else{
					System.out.println("A scholar's pack consisting of");
					printPack(scholars);
				}
				int x = rand.nextInt(10);
            	System.out.println(printInventoryEntry(x, true, true));
				System.out.println("Lealher armor and two daggers");
				printPack(backGroundItems(backG, gold));
				break;
			case "Wizard" :
				System.out.println("(a) a quarterstaff or (b) a dagger");
				System.out.println("(a) a component pouch or (b) an arcane focus");
				System.out.println("(a) a scholar's pack or (b) an explorer's pack");
				System.out.println("A spellbook");
				if(rand.nextBoolean()){
					System.out.println(printInventoryEntry(7, true, true));
				}
				else{
					System.out.println(printInventoryEntry(1, true, true));
				}
				if(rand.nextBoolean()){
					System.out.println("A component pouch");
				}
				else{
					System.out.println("An Arcane Focus");
				}
				if(rand.nextBoolean()){
					System.out.println("A explorer's pack consisting of");
					printPack(explorers);
				}
				else{
					System.out.println("A scholar's pack consisting of");
					printPack(scholars);
				}
				System.out.println("A spellbook");
				printPack(backGroundItems(backG, gold));
				break;
			default:
				System.out.println("Ur class aint a class so no inventory");
		}
	}
	public void createInventoryTables(){
		for(int i = 0; i < simpleMelee.length; i++){
			switch (i) {case 0 :simpleMelee[0] = "Club";break;case 1:simpleMelee[1] = "Dagger";break;case 2:simpleMelee[2] = "Greatclub";break;case 3:simpleMelee[3] = "Handaxe";break;case 4:simpleMelee[4] = "Javelin";break;case 5:simpleMelee[5] = "Light Hammer";break;case 6:simpleMelee[6] = "Mace";break;case 7:simpleMelee[7] = "Quarterstaff";break;case 8:simpleMelee[8] = "Sickle";break;case 9:simpleMelee[9] = "Spear";break;default :System.out.println("You can't count in simpleMelee");}
			switch (i) {case 0 :simpleMeleeDamage[0] = "1d4 bludgeoning";break;case 1:simpleMeleeDamage[1] = "1d4 peircing";break;case 2:simpleMeleeDamage[2] = "1d8 bludgeoning";break;case 3:simpleMeleeDamage[3] = "1d6 slashing";break;case 4:simpleMeleeDamage[4] = "1d6 piercing";break;case 5:simpleMeleeDamage[5] = "1d4 bludgeoning";break;case 6:simpleMeleeDamage[6] = "1d6 bludgeoning";break;case 7:simpleMeleeDamage[7] = "1d6 bludgeoning";break;case 8:simpleMeleeDamage[8] = "1d4 slashing";break;case 9:simpleMeleeDamage[9] = "1d6 slashing";break;default :System.out.println("You can't count in simpleMeleeDamage");}
			switch (i) {case 0 :simpleMeleeProp[0] = "Light";break;case 1:simpleMeleeProp[1] = "Finesse, light, thrown(range 20/60)";break;case 2:simpleMeleeProp[2] = "Two-handed";break;case 3:simpleMeleeProp[3] = "Light, thrown(range 20/60)";break;case 4:simpleMeleeProp[4] = "Thrown(range 30/120)";break;case 5:simpleMeleeProp[5] = "Light, thrown(range 20/60)";break;case 6:simpleMeleeProp[6] = "";break;case 7:simpleMeleeProp[7] = "Versatile(1d8)";break;case 8:simpleMeleeProp[8] = "Light";break;case 9:simpleMeleeProp[9] = "Thrown(range 20/60), versatile(1d8)";break;default :System.out.println("You can't count in simpleMeleeProp");}}
		for(int i = 0; i < simpleRanged.length; i++){
			switch (i) {case 0 :simpleRanged[0] = "Light Crossbow";break;case 1:simpleRanged[1] = "Dart";break;case 2:simpleRanged[2] = "Shortbow";break;case 3:simpleRanged[3] = "Sling";break;default :System.out.println("You can't count in simpleRanged");}
			switch (i) {case 0 :simpleRangedDamage[0] = "1d8 piercing";break;case 1:simpleRangedDamage[1] = "1d4 piercing";break;case 2:simpleRangedDamage[2] = "1d6 piercing";break;case 3:simpleRangedDamage[3] = "1d4 blugeoning";break;default :System.out.println("You can't count in simpleRangedDamage");}
			switch (i) {case 0 :simpleRangedProp[0] = "Ammunition(range 80/320), loading, two-handed";break;case 1:simpleRangedProp[1] = "Finesse, thrown(range 20/60)";break;case 2:simpleRangedProp[2] = "Ammunition(range 80/320), two-handed";break;case 3:simpleRangedProp[3] = "Ammunition(range 30/120)";break;default :System.out.println("You can't count in simpleRangedProp");}}
		for(int i = 0; i < martialMelee.length; i++){
			switch (i) {case 0 :martialMelee[0] = "Battleaxe";break;case 1:martialMelee[1] = "Flail";break;case 2:martialMelee[2] = "Glaive";break;case 3:martialMelee[3] = "Greataxe";break;case 4:martialMelee[4] = "Greatsword";break;case 5:martialMelee[5] = "Halberd";break;case 6:martialMelee[6] = "Lance";break;case 7:martialMelee[7] = "Longsword";break;case 8:martialMelee[8] = "Maul";break;case 9:martialMelee[9] = "Morningstar";break;case 10:martialMelee[10] = "Pike";break;case 11:martialMelee[11] = "Rapier";break;case 12:martialMelee[12] = "Scimitar";break;case 13:martialMelee[13] = "Shortsword";break;case 14:martialMelee[14] = "Trident";break;case 15:martialMelee[15] = "War pick";break;case 16:martialMelee[16] = "Warhammer";break;case 17:martialMelee[17] = "Whip";break;default :System.out.println("You can't count in martialMelee");}
			switch (i) {case 0 :martialMeleeDamage[0] = "1d8 Slahsing";break;case 1:martialMeleeDamage[1] = "1d8 bludgeoning";break;case 2:martialMeleeDamage[2] = "1d10 slashing";break;case 3:martialMeleeDamage[3] = "1d12 slashing";break;case 4:martialMeleeDamage[4] = "2d6 slashing";break;case 5:martialMeleeDamage[5] = "1d10 slashing";break;case 6:martialMeleeDamage[6] = "1d12 piercing";break;case 7:martialMeleeDamage[7] = "1d8 slashing";break;case 8:martialMeleeDamage[8] = "2d6 bludgeoning";break;case 9:martialMeleeDamage[9] = "1d8 piercing";break;case 10:martialMeleeDamage[10] = "1d10 piercing";break;case 11:martialMeleeDamage[11] = "1d8 piercing";break;case 12:martialMeleeDamage[12] = "1d6 slashing";break;case 13:martialMeleeDamage[13] = "1d6 piercing";break;case 14:martialMeleeDamage[14] = "1d6 piercing";break;case 15:martialMeleeDamage[15] = "1d8 piercing";break;case 16:martialMeleeDamage[16] = "1d8 bludgeoning";break;case 17:martialMeleeDamage[17] = "1d4 slashing";break;default :System.out.println("You can't count in martialMeleeDamage");}
			switch (i) {case 0 :martialMeleeProp[0] = "Versatile(1d10)";break;case 1:martialMeleeProp[1] = "";break;case 2:martialMeleeProp[2] = "Heavy, reach, two-handed";break;case 3:martialMeleeProp[3] = "Heavy, two-handed";break;case 4:martialMeleeProp[4] = "Heavy, two-handed";break;case 5:martialMeleeProp[5] = "Heavy, reach, two-handed";break;case 6:martialMeleeProp[6] = "Reach, special";break;case 7:martialMeleeProp[7] = "Versatile(1d10)";break;case 8:martialMeleeProp[8] = "Heavy, two-handed";break;case 9:martialMeleeProp[9] = "";break;case 10:martialMeleeProp[10] = "Heavy, reach, two-handed";break;case 11:martialMeleeProp[11] = "Finesse";break;case 12:martialMeleeProp[12] = "Finesse, light";break;case 13:martialMeleeProp[13] = "Finesse, light";break;case 14:martialMeleeProp[14] = "Thrown(range 20/60), versatile(1d8)";break;case 15:martialMeleeProp[15] = "";break;case 16:martialMeleeProp[16] = "Versatile(1d10)";break;case 17:martialMeleeProp[17] = "Finesse, reach";break;default :System.out.println("You can't count in martialMeleeProp");}}
		for(int i = 0; i < martialRanged.length; i++){
			switch (i) {case 0 :martialRanged[0] = "Blowgun";break;case 1:martialRanged[1] = "Hand Crossbow";break;case 2:martialRanged[2] = "Heavy Crossbow";break;case 3:martialRanged[3] = "Longbow";break;case 4:martialRanged[4] = "Net";break;default :System.out.println("You can't count in martialRanged");}
			switch (i) {case 0 :martialRangedDamage[0] = "1 piercing";break;case 1:martialRangedDamage[1] = "1d6 piercing";break;case 2:martialRangedDamage[2] = "1d10 piercing";break;case 3:martialRangedDamage[3] = "1d8 piercing";break;case 4:martialRangedDamage[4] = "";break;default :System.out.println("You can't count in martialRangedDamage");}
			switch (i) {case 0 :martialRangedProp[0] = "Ammunition(range 25/100), loading";break;case 1:martialRangedProp[1] = "Ammunition(range 30/120), light, loading";break;case 2:martialRangedProp[2] = "Ammunition(range 100/400), heavy, loading, two-handed";break;case 3:martialRangedProp[3] = "Ammunition(range 150/600),  heavy, two-handed";break;case 4:martialRangedProp[4] = "Special, thrown(range 5/15)";break;default :System.out.println("You can't count in martialRangedProp");}}
		for(int i = 0; i < burglars.length; i++){
			switch (i) {case 0: burglars[0] = "backpack";break;case 1:burglars[1] = "1,000 ball bearings";break;case 2:burglars[2] = "10 feet of string";break;case 3: burglars[3] = "a bell";break;case 4: burglars[4] = "5 candles";break;case 5:burglars[5] = "a crowbar";break;case 6:burglars[6] = "a hammer";break;case 7: burglars[7] = "10 pitions";break;case 8:burglars[8] = "a hooded latern";break;case 9:burglars[9] = "2 flasks of oil";break;case 10:burglars[10] = "5 days rations";break;case 11:burglars[11] = "a tinderbox";break;case 12:burglars[12] = "a waterskin";break;case 13:burglars[13] = "50 feet of hempen rope";break;default:System.out.println("You can't count in burglars");}}
		for(int i = 0; i < diplomats.length; i++){
			switch (i) {case 0: diplomats[0] = "chest";break;case 1:diplomats[1] = "2x cases for maps and scrolls (empty?)";break;case 2:diplomats[2] = "a set of fine clothes";break;case 3: diplomats[3] = "bottle of ink";break;case 4: diplomats[4] = "ink pen";break;case 5:diplomats[5] = "lamp";break;case 6:diplomats[6] = "2 flasks of oil";break;case 7: diplomats[7] = "5 sheets of paper";break;case 8:diplomats[8] = "vial of perfume";break;case 9:diplomats[9] = "sealing wax";break;case 10:diplomats[10] = "soap";break;default:System.out.println("You can't count in diplomats");}}
		for(int i = 0; i < dungeoneers.length; i++){
			switch (i) {case 0: dungeoneers[0] = "backpack";break;case 1:dungeoneers[1] = "a crowbar";break;case 2:dungeoneers[2] = "a hammer";break;case 3: dungeoneers[3] = "10 pitons";break;case 4: dungeoneers[4] = "10 torches";break;case 5:dungeoneers[5] = "a tinderbox";break;case 6:dungeoneers[6] = "10 days of rations";break;case 7: dungeoneers[7] = "waterskin";break;case 8:dungeoneers[8] = "50 feet of hempen rope";break;default:System.out.println("You can't count in dungeoneers");}}
		for(int i = 0; i < entertainers.length; i++){
			switch (i) {case 0: entertainers[0] = "backpack";break;case 1:entertainers[1] = "bedroll";break;case 2:entertainers[2] = "2 costumes";break;case 3: entertainers[3] = "5 candles";break;case 4: entertainers[4] = "5 days of rations";break;case 5:entertainers[5] = "waterskin";break;case 6:entertainers[6] = "disguise kit";break;default:System.out.println("You can't count in entertainers");}}
		for(int i = 0; i < explorers.length; i++){
			switch (i) {case 0: explorers[0] = "backpack";break;case 1:explorers[1] = "bedroll";break;case 2:explorers[2] = "mess kit";break;case 3: explorers[3] = "tinderbox";break;case 4: explorers[4] = "10 torches";break;case 5:explorers[5] = "10 days of rations";break;case 6:explorers[6] = "waterskin";break;case 7:explorers[7] = "50 feet of hempen rope";break;default:System.out.println("You can't count in explorers");}}
		for(int i = 0; i < priests.length; i++){
			switch (i) {case 0: priests[0] = "backpack";break;case 1:priests[1] = "blanket";break;case 2:priests[2] = "10 candles";break;case 3: priests[3] = "tinderbox";break;case 4: priests[4] = "alms box";break;case 5:priests[5] = "2 blocks of incense";break;case 6:priests[6] = "a censer";break;case 7: priests[7] = "vestments";break;case 8:priests[8] = "2 days of rations";break;case 9:priests[9] = "waterskin";break;default:System.out.println("You can't count in priests");}}
		for(int i = 0; i < scholars.length; i++){
			switch (i) {case 0: scholars[0] = "backpack";break;case 1:scholars[1] = "book of lore";break;case 2:scholars[2] = "bottle of ink";break;case 3: scholars[3] = "ink pen";break;case 4: scholars[4] = "10 sheets of parchment";break;case 5:scholars[5] = "little bag of sand";break;case 6:scholars[6] = "small knife";break;default:System.out.println("You can't count in scholars");}}
	}
	public String printInventoryEntry(int index, boolean melee, boolean simple){
		if(melee && simple){
			return simpleMelee[index] + " " + simpleMeleeDamage[index] + " " + simpleMeleeProp[index];
		}
		else if(!melee && simple){
			return simpleRanged[index] + " " + simpleRangedDamage[index] + " " + simpleRangedProp[index];
		}
		else if(melee && !simple){
			return martialMelee[index] + " " + martialMeleeDamage[index] + " " + martialMeleeProp[index];
		}
		else if(!melee && !simple){
			return martialRanged[index] + " " + martialRangedDamage[index] + " " + martialRangedProp[index];
		}
		else{
			return "Something went wrong while printing inventory entry";
		}
	}
	public void printPack(String[] pack){
		for(String str : pack){
			System.out.println(" - " + str);
		}
	}
	public int getGold(String c){
		switch (classs){
			case "Barbarian" : 
				return rand.nextInt(5)*10 + rand.nextInt(5)*10;
			case "Bard" :
				return rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10;
			case "Cleric" :
				return rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10;
			case "Druid" :
				return rand.nextInt(5)*10 + rand.nextInt(5)*10;
			case "Fighter" :
				return rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10;
			case "Monk" :
				return rand.nextInt(5) + rand.nextInt(5) + rand.nextInt(5) + rand.nextInt(5) + rand.nextInt(5);
			case "Paladin" :
				return rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10;
			case "Ranger" :
				return rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10;
			case "Rogue" :
				return rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10;
			case "Sorcerer" :
				return rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10;
			case "Warlock" :
				return rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10;
			case "Wizard" :
				return rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10 + rand.nextInt(5)*10;
			default : 
				return 0;
		}
	}
	public int spellSaveDC(String c, String sc){
		switch (c) {
			case "Bard":
			case "Paladin":
			case "Sorcerer":
			case "Warlock":
				return 8+2+modif[5];
			case "Cleric":
			case "Druid":
			case "Ranger":
				return 8+2+modif[4];
			case "Fighter":
				if(sc == "Eldritch Knight"){ return 8+2+modif[3];}
			case "Rogue":
				if(sc == "Arcane Trickster"){ return 8+2+modif[3];}
			case "Wizard":
				return 8+2+modif[3];
			default :
				return 0;
		}
	}
	public String spellCastingAbility(String c, String sc){
		switch (c) {
			case "Bard":
			case "Paladin":
			case "Sorcerer":
			case "Warlock":
				return "Charisma";
			case "Cleric":
			case "Druid":
			case "Ranger":
				return "Wisdom";
			case "Fighter":
				if(sc == "Eldritch Knight"){ return "Intelligence";}
			case "Rogue":
				if(sc == "Arcane Trickster"){ return "Intelligence";}
			case "Wizard" :
				return "Intelligence";
			default:
				return "none";
		}
	}
	public int spellAttackBonus(String mod){
		switch (mod) {
			case "Strength":
				return 2+modif[0];
			case "Dexterity":
				return 2+modif[1];
			case "Constitution":
				return 2+modif[2];
			case "Intelligence":
				return 2+modif[3];
			case "Wisdom":
				return 2+modif[4];
			case "Charisma":
				return 2+modif[5];
			default:
				return -100;
		}
	}
	public String[] backGroundItems(String bg, int g){
		g+=10;
		switch (bg) {
			case "Acolyte":
				g+=5;
				return new String[] {"A holy symbol(gifted)", "A prayer book", "5 sticks of incense", "vestments", "set of common clothes", "15gp"};
			case "Charaltan":
				g+=5;
				String[] x= {"ten stoppered bottles filled with colored liquid", "a set of weighted dice", "a deck of marked cards","a signet ring of an imaginary duke"};
				return new String[] {"A set of fine c1othes", "a disguise kit", x[rand.nextInt(4)],"15 gp"};
			case "Criminal":
				g+=5;
				return new String[] {"A crowbar"," a set of dark common clothes including a hood", "15 gp"};
			case "Entertainer":
				g+=5;
				String[] a = {"love letter","lock of hair","trinket"};
				return new String[] {"a musical instrument",a[rand.nextInt(3)],"costume","15gp"};
			case "Folk Hero":
				return new String[] {"a set of artisan's tools", "a shover","an iron pot","a set of common clothes","10gp"};
			case "Guild Artisan":
				g+=5;
				return new String[] {"set of artisan's tools","a letter of introduction from your guild","set of traveler's clothes","15gp"};
			case "Hermit":
				g-=5;
				return new String[] {"a scroll case stuffed full of notes from studies or prayers","a winter blanket","a set of common clothes","herbalism kit","5gp"};
			case "Noble":
				g+=15;
				return new String[] {"a set of fine clothes","a signet ring","a scroll of predigree","25gp"};
			case "Outlander":
				return new String[] {"a staff","a hunting trap","a trophy from an animal you killed","a set of traveler's clothes","10gp"};
			case "Sage":
				return new String[] {"bottle of black ink","a quill","small knife","letter from a dead colleague posing a question you have not yet been able to answer","common clothes","10gp"};
			case "Sailor":
				return new String[] {"A belaying pin(club)", "50 feet of silk rope", "lucky charm", "common clothes", "10gp"};
			case "Soldier":
				String [] z = {"dagger","broken blade","piece of a banner"};
				String [] y = {"a set of bone dice", "deck of cards"};
				return new String[] {"An insignia of rank","trophy from fallen enemy(" + z[rand.nextInt(3)] + ")", y[rand.nextInt(2)], "common clothes", "10gp"};
			case "Urchin":
				return new String[] {"A small knife","a map of the city you grew up in","a pet mouse","a token to remember your parents by","set of common clothes","10gp"};
			default:
				return new String[] {"Something went wrong int background items"};
		}
	}
	public String[] backGroundPersonality(String bg){
		return null;
	}
	public String[] printProficiencies(String c, String bg){
		return null;
	}	
	/**
	 * to do list
	 * 1) artisan's tools
	 * 2) instruments
	 * 3) specialities for backgrounds
	 */
}

