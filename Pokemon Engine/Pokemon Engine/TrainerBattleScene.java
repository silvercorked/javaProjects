import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Image.*;
import java.util.*;
import java.awt.geom.*;
import java.io.*;
import java.net.*;

public class TrainerBattleScene {
	
	private Pokemon game;
	private Font pokefont = new Font("pokesl1", Font.PLAIN, 18);
	private Random r = new Random();
	
	public boolean playerTurn;
	public int elapsedTurns;
	public boolean inMain = true;
	public boolean inFight = false;
	public boolean inItem = false;
	public boolean inPokemon = false;
	public boolean inRun = false;
	public boolean playerWon = false;
	public boolean pokemonfainted = false;
	public boolean confirmBattleEnd = false;
	public int currentSelectionMainX;
	public int currentSelectionMainY;
	public int currentSelectionFightX;
	public int currentSelectionFightY;
	public NPC trainer;
	public Monsters[] playerPokemonParty;
	public Monsters[] enemyPokemonParty;
	public Monsters playerPokemon;
	public Monsters enemyPokemon;
	public Items[] battleItems;
	
	private Image BG = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/BG.png"));
	private Image battleMainBG = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/Battle.png"));
	private Image battleFightBG = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/Battle2.png"));
	private Image arrow = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/Arrow.png"));
	
	private Image statusPAR = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/StatusPAR.png"));
	private Image statusBRN = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/StatusBRN.png"));
	private Image statusPSN = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/StatusPSN.png"));
	private Image statusSLP = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/StatusSLP.png"));
	private Image statusFRZ = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/StatusFRZ.png"));
	
	private JukeBox col = new JukeBox();

    public TrainerBattleScene(Pokemon pkmn, NPC npc, Monsters[] playerparty, Monsters[] enemyParty, Items[] items) {
    	game = pkmn;
    	trainer = npc;
    	playerPokemonParty = playerparty;
    	playerPokemon = playerparty[0];
    	enemyParty = enemyPokemonParty;
    	enemyPokemon = enemyParty[0];
    	battleItems = items;
    	playerTurn = true;
    	col.loadClip("Audio/SE/Damage.wav", "Damage", 1);
    	Start();
    }
    
    public void Start() {
    	System.out.println("Player's Pokemon: " + playerPokemon.getName() + 
    		" Level: " + playerPokemon.getLevel() +
    		" HP: " + playerPokemon.getCurrentHP() +
    		" / " + playerPokemon.getHP());
    	System.out.println("Wild Pokemon: " + enemyPokemon.getName() + 
    		" Level: " + enemyPokemon.getLevel() +
    		" HP: " + enemyPokemon.getCurrentHP() +
    		" / " + enemyPokemon.getHP());
    	currentSelectionMainX = 0;
    	currentSelectionFightX = 0;
    	currentSelectionMainY = 0;
    	currentSelectionFightY = 0;
    	inMain = true;
    }
    
    public void Fight() {
    	inMain = false;
    	inFight = true;
    	System.out.println("Fight");
    }
    
    public void Item() {
    	//inMain = false;
    	inItem = true;
    	System.out.println("Item");
    }
    
    public void Pokemon() {
    	//inMain = false;
    	inPokemon = true;
    	System.out.println("Pokemon");
    }
    
    public void playerSwitchPokemon(int partyslot) {
    	if (partyslot == 0) {
    		//No Switch
    	}
    	else if (partyslot == 1) {
    		//Slot 2
    		playerPokemon = playerPokemonParty[1];
    	}
    	else if (partyslot == 2) {
    		//Slot 3
    		playerPokemon = playerPokemonParty[2];
    	}
    	else if (partyslot == 3) {
    		//Slot 4
    		playerPokemon = playerPokemonParty[3];
    	}
    	else if (partyslot == 4) {
    		//Slot 5
    		playerPokemon = playerPokemonParty[4];
    	}
    	else if (partyslot == 5) {
    		//Slot 6
    		playerPokemon = playerPokemonParty[5];
    	}
    }
    
    public void enemySwitchPokemon(int partyslot) {
    	if (partyslot == 0) {
    		//No Switch
    	}
    	else if (partyslot == 1) {
    		//Slot 2
    		enemyPokemon = enemyPokemonParty[1];
    	}
    	else if (partyslot == 2) {
    		//Slot 3
    		enemyPokemon = enemyPokemonParty[2];
    	}
    	else if (partyslot == 3) {
    		//Slot 4
    		enemyPokemon = enemyPokemonParty[3];
    	}
    	else if (partyslot == 4) {
    		//Slot 5
    		enemyPokemon = enemyPokemonParty[4];
    	}
    	else if (partyslot == 5) {
    		//Slot 6
    		enemyPokemon = enemyPokemonParty[5];
    	}
    }
    
    public void giveEXP() {
    	playerPokemon.cur_exp += 47;
    	if (playerPokemon.cur_exp >= playerPokemon.exp) {
    		playerPokemon.exp += 93;
    		playerPokemon.cur_exp = playerPokemon.exp - playerPokemon.cur_exp;
    		playerPokemon.level++;
    		playerPokemon.hp += 3;
    		playerPokemon.attack += 2;
    		playerPokemon.spAttack += 2;
    		playerPokemon.def += 2;
    		playerPokemon.spDef += 2;
    		playerPokemon.cur_HP += 3;
    		playerPokemon.cur_attack = playerPokemon.attack;
    		playerPokemon.cur_spAttack = playerPokemon.spAttack;
    		playerPokemon.cur_def = playerPokemon.def;
    		playerPokemon.cur_spDef = playerPokemon.spDef;
    	}
    	System.out.println("Current EXP: " + playerPokemon.cur_exp + " / " + playerPokemon.exp);
    }
    
    public void Run() {
    	inMain = false;
    	inRun = true;
    	enemyPokemon.statusEffect = 0;
    	game.currentBGM.stop();
		game.currentBGM = game.lastBGM;
		game.currentBGM.start();
	    game.inBattle = false;
	    System.out.println("Got away safely!");
    }
    
    public void Win() {
    	giveEXP();
    	inMain = false;
    	inRun = true;
    	enemyPokemon.statusEffect = 0;
    	game.currentBGM.stop();
		game.currentBGM = game.lastBGM;
		game.currentBGM.start();
	    game.inBattle = false;
    }
    
    public void Lose() {
    	inMain = false;
    	inRun = true;
    	enemyPokemon.statusEffect = 0;
    	game.currentBGM.stop();
		game.currentBGM = game.lastBGM;
		game.currentBGM.start();
	    game.inBattle = false;
    }
    
    public void whiteOut() {
    	pokemonfainted = true;
    	Lose();
    }
    
	public void enemyTurn() {
		if (playerWon == false) {
			int i = 0;
			if (enemyPokemon.statusEffect == 4 || enemyPokemon.statusEffect == 5) {
				Random rr = new Random();
				int wakeupthaw = rr.nextInt(5);
				if (wakeupthaw <= 1) {
				    if (enemyPokemon.statusEffect == 4) {
				    	System.out.println(enemyPokemon.name + " has woken up.");
				    }
				    if (enemyPokemon.statusEffect == 5) {
				    	System.out.println(enemyPokemon.name + " has broken free from the ice.");
				    }
				    enemyPokemon.statusEffect = 0;
				}
				else {
					if (enemyPokemon.statusEffect == 4) {
				    	System.out.println(enemyPokemon.name + " is still asleep.");
				    }
				    if (enemyPokemon.statusEffect == 5) {
				    	System.out.println(enemyPokemon.name + " is frozen solid.");
				    }
				}
			}
			else {
				i = r.nextInt(4) + 1;
				System.out.println("Enemy chose move " + i);	
			}
			if (enemyPokemon.statusEffect == 1) {
				Random r = new Random();
				int rand = r.nextInt(2);
				if (rand <= 0) {
					if (i == 1) {
						playerPokemon.setCurrentHP(new Attacks(enemyPokemon.move1).getDamage(playerPokemon));
					}
					if (i == 2) {
						playerPokemon.setCurrentHP(new Attacks(enemyPokemon.move2).getDamage(playerPokemon));
					}
					if (i == 3) {
						playerPokemon.setCurrentHP(new Attacks(enemyPokemon.move3).getDamage(playerPokemon));
					}
					if (i == 4) {
						playerPokemon.setCurrentHP(new Attacks(enemyPokemon.move4).getDamage(playerPokemon));
					}
					col.playClip("Damage");
					System.out.println("Enemy's turn is over");
				}
				else {
					System.out.println(enemyPokemon.name + " is paralyzed. It can't move.");
				}
			}
			else {
				if (i == 1) {
					playerPokemon.setCurrentHP(new Attacks(enemyPokemon.move1).getDamage(playerPokemon));
				}
				if (i == 2) {
					playerPokemon.setCurrentHP(new Attacks(enemyPokemon.move2).getDamage(playerPokemon));
				}
				if (i == 3) {
					playerPokemon.setCurrentHP(new Attacks(enemyPokemon.move3).getDamage(playerPokemon));
				}
				if (i == 4) {
					playerPokemon.setCurrentHP(new Attacks(enemyPokemon.move4).getDamage(playerPokemon));
				}
				col.playClip("Damage");
				System.out.println("Enemy's turn is over");
			}
			if (enemyPokemon.statusEffect == 2) {
				enemyPokemon.cur_HP -= 2;
				System.out.println(enemyPokemon.name + " has been hurt by its burn");
			}
			if (enemyPokemon.statusEffect == 3) {
				enemyPokemon.cur_HP -= 2;
				System.out.println(enemyPokemon.name + " has been hurt by its poison");
			}
			playerTurn = true;
		}
	}
    
    public void paint(Graphics g) {
    	g.setFont(pokefont);
    	g.setColor(Color.BLACK);
    	g.drawImage(BG,0,0,null);
    	//HUD
    	g.drawString(playerPokemon.getName(), 316, 174);
	    g.drawString("" + playerPokemon.getLevel(), 401, 174);
		g.drawString("" + playerPokemon.getCurrentHP(),361,207);
		g.drawString("" + playerPokemon.getHP(),403,207);
		g.drawImage(playerPokemon.getBackSprite(),30,113,null);
		g.drawString(enemyPokemon.getName(), 24, 26);
		g.drawString("" + enemyPokemon.getLevel(), 144, 26);
		g.drawString("" + enemyPokemon.getCurrentHP(),70,45);
		g.drawString("" + enemyPokemon.getHP(),112,45);
		g.drawImage(enemyPokemon.getFrontSprite(),300,25,null);
		//Status Effect Icons
		if (playerPokemon.statusEffect == 1) {
			g.drawImage(statusPAR,415,140,null);
		}
		else if (playerPokemon.statusEffect == 2) {
			g.drawImage(statusBRN,415,140,null);
		}
		else if (playerPokemon.statusEffect == 3) {
			g.drawImage(statusPSN,415,140,null);
		}
		else if (playerPokemon.statusEffect == 4) {
			g.drawImage(statusSLP,415,140,null);
		}
		else if (playerPokemon.statusEffect == 5) {
			g.drawImage(statusFRZ,415,140,null);
		}
		if (enemyPokemon.statusEffect == 1) {
			g.drawImage(statusPAR,18,60,null);
		}
		else if (enemyPokemon.statusEffect == 2) {
			g.drawImage(statusBRN,18,60,null);
		}
		else if (enemyPokemon.statusEffect == 3) {
			g.drawImage(statusPSN,18,60,null);
		}
		else if (enemyPokemon.statusEffect == 4) {
			g.drawImage(statusSLP,18,60,null);
		}
		else if (enemyPokemon.statusEffect == 5) {
			g.drawImage(statusFRZ,18,60,null);
		}
		//Battle Main Interface
    	if (inMain == true) {
    		g.drawImage(battleMainBG,0,0,null);
    		g.drawString("Trainer sent out " + enemyPokemon.getName() + "!", 30, 260);
			g.drawString("FIGHT", 290,260);
			g.drawString("PKMN", 400,260);
			g.drawString("ITEM", 290,290);
			g.drawString("RUN", 400,290);
			if (currentSelectionMainX == 0 && currentSelectionMainY == 0) {
				g.drawImage(arrow, 274, 240, null);
			}
			else if (currentSelectionMainX == 0 && currentSelectionMainY == 1) {
				g.drawImage(arrow, 274, 270, null);
			}
			else if (currentSelectionMainX == 1 && currentSelectionMainY == 0) {
				g.drawImage(arrow, 384, 240, null);
			}
			else if (currentSelectionMainX == 1 && currentSelectionMainY == 1) {
				g.drawImage(arrow, 384, 270, null);
			}
    	}
    	//Battle Fight Interface
    	if (inFight == true) {
    		g.drawImage(battleFightBG,0,0,null);
			g.drawString("Select a Move", 30, 260);
			g.drawString(playerPokemon.move1, 200,260);
			g.drawString(playerPokemon.move2, 345,260);
			g.drawString(playerPokemon.move3, 200,290);
			g.drawString(playerPokemon.move4, 345,290);
			if (currentSelectionFightX == 0 && currentSelectionFightY == 0) {
				g.drawImage(arrow, 184, 240, null);
			}
			else if (currentSelectionFightX == 0 && currentSelectionFightY == 1) {
				g.drawImage(arrow, 184, 270, null);
			}
			else if (currentSelectionFightX == 1 && currentSelectionFightY == 0) {
				g.drawImage(arrow, 329, 240, null);
			}
			else if (currentSelectionFightX == 1 && currentSelectionFightY == 1) {
				g.drawImage(arrow, 329, 270, null);
			}
    	}
    	/*if (inRun == true) {
    		g.drawString("Got away successfully!", 30, 260);
    	}
    	if (pokemonfainted == true) {
    		g.drawString(game.gold.getName() + " is all out of usable Pokemon!", 30, 260);
    	}*/
    }
}