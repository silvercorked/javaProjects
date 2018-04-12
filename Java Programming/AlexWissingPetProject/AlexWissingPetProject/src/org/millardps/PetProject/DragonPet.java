package org.millardps.PetProject;

import java.util.Random;
import java.util.Scanner;

public class DragonPet {
	//attributes
	private int hp;
	private String name;
	private String mastername;
	private int food;
	private int sleep;
	private String[] commands = {"eat", "sleep", "bathroom", "backflip", "frontflip", "fire breath", "vanquish foes", "fly", "go to lair", "read"};
	private String[] eatOptions = {"lamb", "hamburger", "mr. young", "spicy burrito"};
	private String[] vanquishOptions = {"knights", "villagers", "dank memes", "mr. young"};
	private String[] dogeOptions = {"fight", "leave", "die"};
	private String[] readOptions = {"john locke", "thomas hobbes", "plato", "the little engine that could"};
	private boolean burrito;
	private boolean glasses;
	private boolean learnt;
	private boolean night;
	private Dice backFlipDice = new Dice();
	private Dice frontFlipDice = new Dice();
	private Dice fireBreathDice = new Dice();
	private Dice flyDice = new Dice();
	private int selfEsteem;
	private String usercommand;
	private StopWatch lifeTime = new StopWatch();
	private StopWatch happyTime = new StopWatch();
	private StopWatch pottyTime = new StopWatch();
	private String gender1;
	private String gender2;
	private String eating;
	private int gold;
	private int exp;
	public int level;
	
	//constructor
	public DragonPet(){
		while(true){
			burrito = false;
			glasses = false;
			night = true;
			name = getName();
			mastername = getMasterName();
			hp = 100;
			food = 100;
			sleep = 100;
			selfEsteem = 100;
			lifeTime.start();
			happyTime.start();
			pottyTime.start();
			gender1 = getGender();
			gender2 = getGender2(gender1);
			gold = 0;
			
//			Random rand = new Random();
//			int gold1 = rand.nextInt(99);
//			System.out.println("You have found " + gold1 + " gold!");
//			gold += gold1;
			exp = 0;
			level = 1;
				while (true){
					checkStats();
					if(checkStats() == false){
						break;
					}
					else{	
						dragActionRequest();
						checkCommand(usercommand);
						if(usercommand.equals(commands[0]) || usercommand.equals("1")){
							while(true){
								dragonEat();
								if(checkEatOptions(eating) == true){
									dragonEat2();
									break;
								}
							}
						}
						else if(usercommand.equals(commands[1]) || usercommand.equals("2")){
							dragonSleep();
						}
						else if(usercommand.equals(commands[2]) || usercommand.equals("3")){
							dragonBathroom();
						}
						else if(usercommand.equals(commands[3]) || usercommand.equals("4")){
							dragonBackflip();
						}
						else if(usercommand.equals(commands[4]) || usercommand.equals("5")){
							dragonFrontflip();
						}
						else if(usercommand.equals(commands[5]) || usercommand.equals("6")){
							dragonBreathfire();
						}
						else if(usercommand.equals(commands[6]) || usercommand.equals("7")){
							dragonVanquish();
						}
						else if(usercommand.equals(commands[7]) || usercommand.equals("8")){
							dragonFly();
						}
						else if(usercommand.equals(commands[8]) || usercommand.equals("9")){
							dragonLair();
						}
						else if(usercommand.equals(commands[9]) || usercommand.equals("10")){
							dragonRead();
						}
//						else if(usercommand.equals(commands[10])){
//							//dragonAdventure();
//						}
						
					}
				}
		
		if(!(reTry())){
			break;
		}
		}	
	}
	
	
	//methods
	/**
	 * makes sure the dragon is still alive
	 * @return true or false as to whether the dragon is alive
	 */
	public boolean getAlive(){
		if(hp > 0 && food > 0 && sleep > 0){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * gets the name of the dragon from the user
	 * @return a string containing the dragons name
	 */
	public String getName() {
			Scanner scanner1 = new Scanner(System.in);
			System.out.println("What is the Dragon's name?");
			String dragname = scanner1.nextLine();
			System.out.println(dragname + " is a wonderful name!");
			return dragname;
	}
	/**
	 * gets the name of the player of the alias
	 * @return a string containing the user's name
	 */
	public String getMasterName() {
		Scanner scanner4 = new Scanner(System.in);
		System.out.println("What is your name?");
		String mastername = scanner4.nextLine();
		return mastername;
	}
	/**
	 * this asks the user what gender their pet is so that I can use the articles his and her.
	 * @return a string his or her
	 */
	public String getGender(){
		Scanner scanner8 = new Scanner(System.in);
		while(true){
			System.out.println("What gender will your dragon be? Male or Female?");
			String gender = scanner8.nextLine().toLowerCase();
			if(gender.equals("male")){
				System.out.println("You know own " + this.name + ", a male dragon!");
				String gender3 = "his";
				return gender3;
			}
			else if(gender.equals("female")){
				System.out.println("You know own " + this.name + ", a female dragon!");
				String gender4 = "her";
				return gender4;
			}
			else{
				System.out.println("That isn't male or female!");
			}
		}
	}
	/**
	 * will reset exp to 0 
	 *  input will look like: levelUp(level, exp);
	 * @return int for levelUp
	 */
	public int levelUp(int i, int x){
		int levelCap = 100;
		for(int ii = i, xx = x; ii < 20 ; ii++){
			if(xx > levelCap){
				x = x - levelCap;
				levelCap = (levelCap * 5 / 2) - 6;
				i++;
				System.out.println("You have leveled up! You are now level " + i + "!");
				return i;
			}
		}
		return 0;
	}
	/**
	 * this takes getGender's result as an argument and allows me further use of articles
	 * @param x getGender result or a string
	 * @return a string he or she
	 */
	public String getGender2(String x){
		while(true){
			if(x.equals("his")){
				String gender5 = "he";
				return gender5;
			}
			else if(x.equals("her")){
				String gender6 = "she";
				return gender6;
			}
			else{
				System.out.println("That isn't male or female!");
			}
		}
	}
	/**
	 * prints string arrays in an easy to reaad manner
	 */
	public void printCommands(String[] var){
		for(int i = 0; i < var.length; i++){
			System.out.print("#" + (i+1) + ": " +  var[i] + " ");
			}
		System.out.println("");
		}
	/**
	 * checks the stats on the dragon 
	 */
	public boolean checkStats(){
		if(hp <= 0){
			System.out.println(this.name + " has died of lack of hp!");
			return false;
		}
		else if(hp <= 35){
			System.out.println(this.name + " is low on HP!");
			happyTime.pause();
			return true;
		}
		if(sleep <= 0){
			System.out.println(this.name + " is out of sleep!");
			return false;
		}
		else if(sleep <= 35){
			System.out.println(this.name + " is nearing sleep deprivation!");
			happyTime.pause();
			return true;
		}
		if(food <= 0){
			System.out.println(this.name + " died of hunger!");
			return false;
		}
		else if(food <= 35){
			System.out.println(this.name + " is hungery!");
			happyTime.pause();
			return true;
		}
		if(pottyTime.getElapsedTime() > 600){
			System.out.println(this.name + " couldn't hold it in and died!");
			return false;
		}
		else if(pottyTime.getElapsedTime() > 300){
			System.out.println(this.name + " needs a bathroom break!");
			happyTime.pause();
			return true;
		}
		else{
			if(!(happyTime.getRunning() && pottyTime.getElapsedTime() > 300 && sleep <= 35 && food <= 35 && hp <= 35 && selfEsteem <= 35))
				happyTime.start();
		}
		if(!(happyTime.getElapsedTime() <= 600)){
			System.out.println(this.name + " is really depressed and disappeared!");
			return false;
		}
		else if(!(happyTime.getElapsedTime() <= 300)){
			System.out.println(this.name + " is depressed");
			return true;
		}
		else{
			return true;
		}
	}
	/**
	 * asks the user what they would like to do with their pet
	 * @return a string 'usercommand' that is the user's choice of command for their pet
	 */
	public String dragActionRequest() {
		Scanner scanner2 = new Scanner(System.in);
		System.out.println("What would you like to do with " + this.name);
		printCommands(commands);
		usercommand = scanner2.nextLine().toLowerCase();
		return usercommand;
	}
	/**
	 * find the max damage the player has
	 * @param x the current level of the player
	 * @return an int that is the max damage
	 */
	public int checkDamageMax(int x){
		int f = x *2 + (x*3);
		return f;
	}
	/**
	 * find the min damage a player has
	 * @param x the current player level
	 * @return an int that is the min damage
	 */
	public int checkDamageMin(int x){
		int f = x/2;
		return f;
	}
	/**
	 * checks if an attack hits or misses
	 * @param x current player level
	 * @return true or false / hit or miss
	 */
	public boolean hitOrMiss(int x){
		//want f to be 50% at level 1 and slowly get larger
		int f = (x * 2) + 60;
		// the return will be whether on not we hit
		Random rand99 = new Random();
		int c = rand99.nextInt(99);
		if(f > c){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * finds the amount of damage between the min and max
	 * @param x the max damage
	 * @param z the min damage
	 * @return damage value as int
	 */
	public int checkDamageAmount(int x, int z){
		Random rand16 = new Random();
		int b = rand16.nextInt(z);
		int c = x - b;
		return c;
	}
	/**
	 * Checks to makes sure that the user's choice from commands exists in commands
	 * @param x user's choice in commands
	 * @return true or false
	 */
	public boolean checkCommand(String x){
		for(int i = 1; i < commands.length + 1; i++){
			String temp = "" + i;
			if(x.equals(temp)){
				return true;
			}
		}
		for(int i = 0; i < commands.length; i++){
			if(commands[i].contains(x)){
				return true;
			}
		}
		System.out.println("That isn't a command.");
		return false;
	}
	/**
	 * Checks to make sure the users choice of food from eatOption exists in eatOption
	 * @param x takes user food choice from eatOption
	 * @return true or false
	 */
	public boolean checkEatOptions(String x){
		while(true){
			for(int i = 0; i < eatOptions.length; i++){
				if(eatOptions[i].contains(x)){
					return true;
				}
			}
			System.out.println("That ins't food choice.");
			printCommands(eatOptions);
			return false;
		}
	}
	/**
	 * Asks the user which of the options in eatOptions they would like their dragon to eat.
	 * @return their choice of what to eat from eatOptions
	 */
	public String dragonEat(){
			System.out.println("Time to feed your dragon!");
			Scanner scanner3 = new Scanner(System.in);
			System.out.println("What would you like " + this.name + " to eat?");
			printCommands(eatOptions);
			eating = scanner3.nextLine().toLowerCase();
			return eating;
	}
	/** 
	 * Allows the user to have the dragon eat something which resets food and raises hp.
	 */
	public void dragonEat2(){
		while(true){
			if(eating.equals(eatOptions[0])){
				System.out.println(this.name + " loved that " + eating + "!");
				food =100;
				hp += 10;
				selfEsteem += 50;
				break;
			}
			else if(eating.equals(eatOptions[1])){
				System.out.println(this.name + " loved that " + eating + "!");
				food =100;
				hp += 10;
				selfEsteem += 50;
				break;
			}
			else if(eating.equals(eatOptions[2])){
				System.out.println(this.name + " grabs Mr. Young, but Mr. Young came prepared. Mr. Young pulls a gun out of no where and aims at " + this.name + "!");
				System.out.println("Mr. Young speaks 'Your getting the same chance Rachel got. Fifty.... Fifty.... *he throws a coin into the air");
				Scanner scanner5 = new Scanner(System.in);
				System.out.println("Heads or Tails?");
				String headtail = scanner5.nextLine().toLowerCase();
				Random rand = new Random();
				Random rand2 = new Random();
				boolean headtails = rand.nextBoolean();
				boolean headtails2 = rand2.nextBoolean();
				if(headtails2 == true){
					String heads = "heads";
					if(heads.equals(headtail)){
						System.out.println("Not so lucky punk. *Mr. Young fires. " + this.name + " has died.");
						hp = 0;
						break;
					}
					else{
						System.out.println("You got lucky, " + this.name + ", punk!");
						System.out.println("But how about your master?");
						Scanner scanner6 = new Scanner(System.in);
						System.out.println("Heads or Tails?");
						String headtail2 = scanner6.nextLine().toLowerCase();
						Random rand3 = new Random();
						Random rand4 = new Random();
						boolean headtails3 = rand3.nextBoolean();
						boolean headtails4 = rand4.nextBoolean();
						if(headtails3 == true){
							heads = "heads";
						}
						else{
							heads = "tails";
						}
						if(heads.equals(headtail2)){
							System.out.println("Not so lucky punk. *Mr. Young fires. You got shot.");
							hp = 0;
							break;
						}
						else{
							System.out.println("Lucky. with that type of luck I shall gladley be eaten!");
							System.out.println(this.name + " ate Mr. Young!");
							food = 100;
							hp += 10;
							selfEsteem += 50;
							break;
						}
					}
				}
				else{
					String heads = "tails";
					if(heads.equals(headtail)){
						System.out.println("Not so lucky punk. *Mr. Young fires. " + this.name + " has died.");
						hp = 0;
						break;
					}
					else{
						System.out.println("You got lucky, " + this.name + "!");
						System.out.println("But how about your master?");
						Scanner scanner6 = new Scanner(System.in);
						System.out.println("Heads or Tails?");
						String headtail2 = scanner5.nextLine().toLowerCase();
						Random rand3 = new Random();
						Random rand4 = new Random();
						boolean headtails3 = rand3.nextBoolean();
						boolean headtails4 = rand4.nextBoolean();

							heads = "tails";
						
						if(heads.equals(headtail2)){
							System.out.println("Not so lucky punk. *Mr. Young fires. You got shot.");
							hp = 0;
							break;
						}
						else{
							System.out.println("Lucky. With that type of luck I shall gladley be eaten!");
							System.out.println(this.name + " ate Mr. Young!");
							food = 100;
							hp += 10;
							selfEsteem += 50;
							break;
						}
					}
				}
			}
			else if(eating.equals(eatOptions[3])){
				burrito = true;
				System.out.println(this.name + " loved  that Spicy burrito!");
				food =100;
				hp += 10;
				selfEsteem += 50;
				break;
			}
			else{
				System.out.println("You must have spelled that wrong.");
				
			}
		}
	}
	/**
	 * Allows the user to make the dragon rest for a period of time which resets sleeps to 100.
	 */
	public void dragonSleep(){
		Scanner scanner7 = new Scanner(System.in);
		System.out.println("How long do you want to have " + this.name + " sleep?");
		Float rest = scanner7.nextFloat();
		if(rest < 16){
			System.out.println("Seriously? In what game is the dragon are the dragons active this much? They are always sleeping!");
			System.out.println(this.name + " should die for your inselense! Actually, guess what " + gender2 + " is dead now.");
			sleep = 0;
		}
		else if(rest > 100){
			System.out.println(this.name + " died of lack of sleep depravation!");
			sleep = 0;
		}
		else{
			System.out.println("Your dragon slept for " + rest + " and has woken up!");
			sleep = 100;
		}
	}
	/**
	 * Will select number 1 or number 2 based on a boolean and go through the process. Don't eat the burrito.
	 */
	public void dragonBathroom(){
		System.out.println("It's time for " + this.name + " to go to the bathroom!");
		Random rand3 = new Random();
		boolean bathroom = rand3.nextBoolean();
		if(bathroom == true){
			System.out.println(this.name + " has to go number 1.");
			System.out.println(this.name + " just went number 1.");
			pottyTime.start();
		}
		else if(bathroom == false){
			System.out.println(this.name + " has to go number 2!");
			if(burrito == true){
				System.out.println("Oh no, that spicy burrito may have some effects!");
				System.out.println("*fart*");
				System.out.println("Damn, don't feed " + this.name + " that again!");
				burrito = false;
				pottyTime.start();
			}
			else{
				System.out.println(this.name + " has gone number 2!");
				pottyTime.start();
			}
		}
		else{
			System.out.println(this.name + " has to go number 1.");
			System.out.println(this.name + " just went number 1.");
			pottyTime.start();
		}
		
	}
	/**
	 * allows the dragon to proficiently learn to back flip.
	 */
	public void dragonBackflip(){
		System.out.println(this.name + " is about to do a Backflip!");
		if(backFlipDice.roll()){
			System.out.println(this.name + " jumps and backflips magnificently!");
		}
		else{
			System.out.println(this.name + " jumps and falls right on " + gender1 + " face!");
			System.out.println(this.name + " has " + backFlipDice.getChance() + " chance of doing it correclty next time!");
			hp-= 10;
		}
		food-=10;
		sleep-=10;
	}
	/**
	 * allows the dragon to proficiently learn to front flip.
	 */
	public void dragonFrontflip(){
		System.out.println(this.name + " is about to do a Frontflip!");
		if(frontFlipDice.roll()){
			System.out.println(this.name + " jumps and frontflips magnificently!");
		}
		else{
			System.out.println(this.name + " jumps and falls right on " + this.gender1 + " face!");
			System.out.println(this.name + " has " + frontFlipDice.getChance() + " chance of doing it correclty next time!");
			hp-= 10;
		}
		food-=10;
		sleep-=10;
	}
	/**
	 * allows the dragon to breath fire and feel the consequences
	 */
	public void dragonBreathfire(){
		System.out.println(this.name + " is about to breath fire!");
		if(fireBreathDice.roll()){
			System.out.println(this.name + " breaths fire and burns the grass!");
			Random rand6 = new Random();
			int firebreath = rand6.nextInt(99);
			if(firebreath < 39){
				if(fireBreathDice.getChance().equals("100%")){
					System.out.println("The fire is perfect! It hits a nearby cow. You and " + this.name + " eat hamburgers.");
					hp += 10;
					food += 40;
				}
				else{
					System.out.println(this.name + "'s fire is too strong! It's going to hit you!");
					System.out.println("You died!");
					hp = 0;
				}
			}
			else{
				System.out.println(this.name + " gets winded and stops breathing fire. ");
				food -= 10;
				sleep -= 10;
			}
		}
		else{
			System.out.println(this.name + " puffs a wimpy flame and cries");
			selfEsteem -= 10;
		}
	}
	/**
	 * Give the user a choice of how to vanquish. Each one has various results.
	 */
	public void dragonVanquish(){
		System.out.println("Who would you like " + this.name + " to vanquish?");
		Scanner scanner9 = new Scanner(System.in);
		printCommands(vanquishOptions);
		String vanq = scanner9.nextLine().toLowerCase();
		if(vanq.equals("knights")){
			if(learnt == false){
				System.out.println(this.name + " charges like a fat lady after a twinky. The knights assemble, but your dragon is not very smart so it instead tries to fight Night, as in the time of day!");
				System.out.println(this.name + " has vanquished night by desroying the moon...");
				System.out.println("If " + this.name + " has smarter then those knights wouldnt of stood a chance. The factories of the region have declared an eternal work day...");
				night = false;
			}
			else{
				System.out.println(this.name + " fights the valient knights to the end. " + gender2 + " is victorious!");
				Random rand = new Random();
				int gold1 = rand.nextInt(99);
				System.out.println("You have found " + gold1 + " gold!");
				gold += gold1;
			}
		}
		else if(vanq.equals("villagers")){
			System.out.println("Your dragon charges like a mad dog at helpless school children! The villagers don't stand a chance. Your dragon burns down the village!");
			if(glasses == false){
				while(true){
					Scanner scanner10 = new Scanner(System.in);
					System.out.println("One of the villagers dropped her reading glasses, why don't you steal those while your at it?");
					System.out.println("Yes/No?");
					String glasses1 = scanner10.nextLine().toLowerCase();
					if(glasses1.equals("yes")){
						System.out.println("Yeah, I guess those are yours now.");
						glasses = true;
						System.out.println("All the villagers are dead. Good job... jerk!");
						break;
					}
					else if(glasses1.equals("no")){
						System.out.println("Well, he is dead; they will just go to waste...");
						glasses = false;
						System.out.println("All the villagers are dead. Good job... jerk!");
						break;
					}
					else{
						System.out.println("That isn't yes or no!");
					}
				}
			}
			else{
				System.out.println("All the villagers are dead... again.");
			}
		}
		
		else if(vanq.equals("dank memes")){
			System.out.println("Ok, finally! " + this.name + " is about to rid the world of all those stupid memes on the internet.");
			System.out.println("Oh shit here comes DOGE!");
			while(true){
				System.out.println("What will you do?");
				Scanner scanner12 = new Scanner(System.in);
				printCommands(dogeOptions);
				String doge = scanner12.nextLine().toLowerCase();
				if(doge.equals("fight")){
					System.out.println("DOGE uses Mountain Dew and Doritoes to destroy you.");
					hp =0;
					break;
				}
				else if(doge.equals("leave")){
					System.out.println("Good choice. DOGE is the numba juan most dank meme ever.");
					break;
				}
				else if(doge.equals("die")){
					System.out.println("You died.");
					hp = 0;
					break;
				}
				else{
					System.out.println("Please choose one of the following!");
					printCommands(dogeOptions);
				}
			}
		}
		else if(vanq.equals("mr. young")){
			System.out.println("This guys should be an easy vanquish.");
			System.out.println("Mr. Young jumps out of a bush and stabs " + this.name + " in the neck!");
			hp -= 50;
			System.out.println("RUN!");
		}
		else{
			System.out.println("Please select one of the following!");
		}
		
	}
	/**
	 * Will simulate a scenario when the dragon is flying
	 */
	public void dragonFly(){
		System.out.println("You asked " + this.name + " to fly!");
		if(flyDice.roll() == true){
			System.out.println(this.name + " takes off and soars high above the ground");
			Random rand5 = new Random();
			int dragonMissle = rand5.nextInt(99);
			if(dragonMissle < 9){
				System.out.println("An F-16 comes out of no where and fires missles at your dragon!");
				if(flyDice.getChance() == "100%"){
					System.out.println(this.name + " dodged the missles and destroyed the jet!");
				}
				else{
					System.out.println(this.name + " is hit by a missle and " + gender2 + " exploded." );
					hp = 0;
				}
			}
			else{
				System.out.println(this.name + " descends!");
			}
		}
		else{
			System.out.println(this.name + " runs forward and jumps off a small hill and begins tumbling down the hill.");
			hp -= 20;
		}
		
	}
	/**
	 * Allows the dragon to go away from the master if the master is displeased.
	 */
	public void dragonLair(){
		System.out.println(this.name + " is about to depart for his lair!");
		while(true){
			Scanner scanner10 = new Scanner(System.in);
			System.out.println("Would you like him to fly or walk?");
			String answer = scanner10.nextLine().toLowerCase();
			if(answer.equals("fly")){
				dragonFly();
				System.out.println(gender2 +  " walks into " + gender1  +  " lair, lonely.");
				System.out.println("Unable to withstand the loneliness, " + this.name + " flies back to you.");
				break;
			}
			else if(answer.equals("walk")){
				System.out.println(this.name + " walks to " + gender1 + " lair, feeling like " + gender2 + " did something wrong.");
				selfEsteem -= 10;
				System.out.println("So lonely, in fact, that " + gender2 + " walks back to you looking for you to care about " + gender1 + ".");
				break;
			}
			else{
				System.out.println("Please answer fly or walk.");
			}
		}
	}
	/**
	 * allows your dragon to read from a few books or get depressed
	 */
	public void dragonRead(){
		System.out.println(this.name + " is about to read a passage.");
		while(true){
			Scanner scanner11 = new Scanner(System.in);
			System.out.println("What would you like to have read to you?");
			printCommands(readOptions);
			String readOp = scanner11.nextLine().toLowerCase();
			if(glasses == true){
				learnt = true;
				if(readOp.equals(readOptions[0])){
					System.out.println("New opinions are always suspected, and usually opposed, without any other reason but because they are not already common.");
					break;
				}
				else if(readOp.equals(readOptions[1])){
					System.out.println("A man's conscience and his judgment is the same thing; and as the judgment, so also the conscience, may be erroneous.");
					break;
				}
				else if(readOp.equals(readOptions[2])){
					System.out.println("We can easily forgive a child who is afraid of the dark; the real tragedy of life is when men are afraid of the light.");
					break;
				}
				else if(readOp.equals(readOptions[3])){
					System.out.println("As it neared the top of the grade, which had so discouraged the larger engines, it went more slowly. However, it still kept saying, \"I—think—I—can, I—think—I—can.");
					System.out.println("It reached the top by drawing on bravery and then went on down the grade, congratulating itself by saying, \"I thought I could, I thought I could.\"");
					break;
				}
				else{
					System.out.println("Please choose one fo the following.");
				}
			}
			else{
				if(readOp.contains(readOptions[0]) || readOp.contains(readOptions[1]) || readOp.contains(readOptions[2]) || readOp.contains(readOptions[3])){
					System.out.println("Dragons can't read, stupid. " + this.name + " throws the book down in anger and sadness!");
					selfEsteem -= 40;
					break;
				}
				else{
					System.out.println("Please give one fo the following.");
				}
			}
		}
	}
	/**
	 * this is not done but basically will be almost an entire adventure untilizing the above functions
	 */
	public void dragonAdventure() {
		int Max = checkDamageMax(level);
		int Min = checkDamageMin(Max);
		System.out.println(this.name + " is about to go on an adventure!");
		Scanner scanner1 = new Scanner(System.in);
		boolean x = true;
		while(x == true){
			System.out.println("Which direction should you go? Left or Right?");
			String path = scanner1.nextLine().toLowerCase();
			Random rand1 = new Random();
			boolean pathLep = rand1.nextBoolean();
			if(path.equals("right")){
				System.out.println("You and " + this.name + " walk to the right.");
				if(pathLep == true){
					System.out.println("You see a leprechaun in a tree!");
					Scanner scanner2 = new Scanner(System.in);
					while(true){
						System.out.println("Should you chase? yes or no?");
						String choice1 = scanner2.nextLine().toLowerCase();
						if(choice1.equals("yes")){
							System.out.println("You and " + this.name + " give chase to the leprechaun!");
							Random rand2 = new Random();
							boolean lepEvade = rand2.nextBoolean();
							if(lepEvade == true){
								System.out.println("The leprechaun is too fast! He got away!");
								break;
							}
							else if(lepEvade == false){
								System.out.println("The leprechaun tires. " + this.name + " dives forward and catches him!");
								Random rand = new Random();
								int gold1 = rand.nextInt(99);
								System.out.println("You have found " + gold1 + " gold!");
								gold += gold1;
								break;
							}
							else{
								System.out.println("If this message appears, it be broke!");
								break;
							}
						}
						else if(choice1.equals("no")){
							System.out.println("You watch as the leprechaun gets away. He may of had some gold.");
							break;
						}
						else{
							System.out.println("Please pick yes or no!");
						}
					}
				}
			}
			else if(path.equals("left")){
				System.out.println("You and " + this.name + " walk tothe left.");
				if(pathLep == true){
					System.out.println("You see a leprechaun in a tree!");
					Scanner scanner2 = new Scanner(System.in);
					while(true){
						System.out.println("Should you chase? yes or no?");
						String choice1 = scanner2.nextLine().toLowerCase();
						if(choice1.equals("yes")){
							System.out.println("You and " + this.name + " give chase to the leprechaun!");
							Random rand2 = new Random();
							boolean lepEvade = rand2.nextBoolean();
							if(lepEvade == true){
								System.out.println("The leprechaun is too fast! He got away!");
								break;
							}
							else if(lepEvade == false){
								System.out.println("The leprechaun tires. " + this.name + " dives forward and catches him!");
								Random rand = new Random();
								int gold1 = rand.nextInt(99);
								System.out.println("You have found " + gold1 + " gold!");
								gold += gold1;
								break;
							}
							else{
								System.out.println("If this message appears, it be broke!");
								break;
							}
						}
						else if(choice1.equals("no")){
							System.out.println("You watch as the leprechaun gets away. He may of had some gold.");
							break;
						}
						else{
							System.out.println("Please pick yes or no!");
						}
					}
				}
			}
			else{
				System.out.println("Please choose yes or no!");
			}
			System.out.println("You come to a bridge with a troll patrolling.");
			trollEncounter();
		}
	}
	/**
	 * Allows the user to play again after failing
	 * @return returns a boolean. True = play again  False = Game Over
	 */
	public boolean reTry(){
		if(!(getAlive())){
			lifeTime.stop();
			System.out.println("You killed " + this.name + " in only " + lifeTime.getElapsedTime() + " seconds!");
			System.out.println("GAME OVER");
			boolean x = true;
			while(x == true){
				Scanner redo = new Scanner(System.in);
				System.out.println("Try again?");
				System.out.println("Yes/No");
				String round2 = redo.nextLine().toLowerCase();
				if(round2.equals("yes")){
						return true;
				}
				else if(round2.equals("no")){
						return false;
				}
				else{
						System.out.println("That isn't yes or no!");
				}
			}
		}
			return false;
	}
	public void trollEncounter(){
		while(true){
			System.out.println("The troll yells. \"Hey, you and your dragon can only cross with 50 gold!\"");
			Scanner scanner2 = new Scanner(System.in);
			System.out.println("Will you give the troll 50 gold?");
			String answer = scanner2.nextLine().toLowerCase();
			if(answer.equals("yes")){
				if(gold >= 50){
					System.out.println("You give the troll 50 gold and continue on your journey.");
					gold -= 50;
					break;
				}
				else{
					do{
						Scanner scanner3 = new Scanner(System.in);
						System.out.println("You don't have enough gold. Will you try to talk to the troll of fight him? talk/fight");
						String troll1 = scanner3.nextLine().toLowerCase();
						if(troll1.equals("talk")){
							System.out.println("After talking to the troll, he realizes that the times have been hard on the towns people thanks to the ");
							System.out.println("redistribution of wealth thanks to new taxes by the lord. Being an honest tax payer himself, the troll lets ");
							System.out.println("you through on the condition that you use his bridge again.");
							return;
						}
						else if(troll1.equals("fight")){
							System.out.println("You begin to fight the troll!");
							
						}
						else{
							System.out.println("Please type fight or talk.");
							
						}
					} while(true);
				}
			}
			else if(answer.equals("no")){
				
				break;
			}
			else{
				System.out.println("Please answer yes or no.");
			}
		}
	}
}
