import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Image.*;
import java.util.*;
import java.awt.geom.*;
import java.io.*;
import java.net.*;

public class Pokemon extends JPanel implements KeyListener, ActionListener {
	//-----------------------------------------------------------------
	// Pokemon: Metallic Silver
	//-----------------------------------------------------------------
	// Zach Harsh
	// Ron Groom
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	// DEBUG Variables
	//-----------------------------------------------------------------
		private boolean noClip = false;
		private boolean noBattle = false;
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	// Window and Window Accesories
	//-----------------------------------------------------------------
		public static JFrame jf;
		public Toolkit tk = jf.getToolkit();
		private javax.swing.Timer gameTimer;
		private Font pokefont = new Font("pokesl1", Font.PLAIN, 18);
		private Image titlescreen = tk.createImage(getClass().getResource("Graphics/Titles/Pic_2.png"));
		private Image start_symbol = tk.createImage(getClass().getResource("Graphics/Titles/Start.png"));
		private Image continuescreen = tk.createImage(getClass().getResource("Graphics/Pictures/Continue.png"));
		private boolean atTitle = true;
		private boolean atContinueScreen = false;
		private boolean start_visible = true;
		private boolean gamestarted = false;
		private int offsetX = 0, offsetY = 0;
		private int TILE_WIDTH_PIXELS = 32;
		private int TILE_HEIGHT_PIXELS = 32;
		private int concurrentMenuItem = 0;
		public long seconds = 0;
		public long oldTime;
	 	public long minutes = 0;
	 	public long hours = 0;
		private MidiPlayer title = new MidiPlayer("Audio/BGM/Title.mid",true);
		private MidiPlayer continuebgm = new MidiPlayer("Audio/BGM/Continue.mid",true);
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	// Player Variables
	//-----------------------------------------------------------------
		public String name = "Gold";
		public int trainerID;
		public Image cardSprite = tk.createImage(getClass().getResource("Graphics/Characters/Battle/trainer000.png"));
		private Image player = tk.createImage(getClass().getResource("Graphics/Characters/Player/Down.png"));
		private Image playerUp = tk.createImage(getClass().getResource("Graphics/Characters/Player/Up.png"));
	    private Image playerUp1 = tk.createImage(getClass().getResource("Graphics/Characters/Player/Up1.png"));
	    private Image playerUp2 = tk.createImage(getClass().getResource("Graphics/Characters/Player/Up2.png"));
	    private Image playerDown = tk.createImage(getClass().getResource("Graphics/Characters/Player/Down.png"));
	    private Image playerDown1 = tk.createImage(getClass().getResource("Graphics/Characters/Player/Down1.png"));
	    private Image playerDown2 = tk.createImage(getClass().getResource("Graphics/Characters/Player/Down2.png"));
	    private Image playerLeft = tk.createImage(getClass().getResource("Graphics/Characters/Player/Left.png"));
	    private Image playerLeft1 = tk.createImage(getClass().getResource("Graphics/Characters/Player/Left1.png"));
	    private Image playerLeft2 = tk.createImage(getClass().getResource("Graphics/Characters/Player/Left2.png"));
	    private Image playerRight = tk.createImage(getClass().getResource("Graphics/Characters/Player/Right.png"));
	    private Image playerRight1 = tk.createImage(getClass().getResource("Graphics/Characters/Player/Right1.png"));
	    private Image playerRight2 = tk.createImage(getClass().getResource("Graphics/Characters/Player/Right2.png"));
		public Player gold = new Player(10,9,name,player);
		private boolean running = false;
		private int lastdir = 1;
		private int movespritepixels = 0;
		private boolean walking = false;
		private boolean moving = false;
		private boolean up = false;
		private boolean down = false;
		private boolean left = false;
		private boolean right = false;
		private boolean movable_up = true;
		private boolean movable_down = true;
		private boolean movable_left = true;
		private boolean movable_right = true;
		private boolean talkable = false;
		private boolean disable_talk = true;
		private boolean collision = false;
		private boolean footsprite = false;
		private String text = "";
		private int posX = 224; //Multiple of 32
		private int posY = 118; //-10 because height is 42, not 32.
		private int currentX_loc; //Starting Location of player in terms of rows
		private int currentY_loc; //Starting Location of player in terms of columns
		private int posX_tile; //Location of player in terms of rows
		private int posY_tile; //Location of player in terms of columns
		private boolean movable = true;
		private static Random randGen = new Random();
		private int stepscount = 0;
		public int badges = 0;
		public int money = 2000;
		public long timePlayed = 0;
		public long currentTime = 0;
		public Monsters[][] pokedex = new Monsters[493][40];
		public Monsters wildPokemon = new Monsters();
		public Monsters playerPokemon1 = new Monsters();
		public Monsters playerPokemon2 = new Monsters();
		public Monsters playerPokemon3 = new Monsters();
		public Monsters playerPokemon4 = new Monsters();
		public Monsters playerPokemon5 = new Monsters();
		public Monsters playerPokemon6 = new Monsters();
		public Monsters pokemonparty[] = new Monsters[6];
		public Items[] items = new Items[2];
		public Items[][] mainitems = new Items[30][99];
		public Items[][] balls = new Items[30][99];
		public Items[][] keyitems = new Items[30][99];
		private Items potion = new Items(1);
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	// Map Variables
	//-----------------------------------------------------------------
		private Image[] tileset = new Image[1112];
		private String Johto = "Data/Johto.map";
		private String currentMap = "Cherrygrove City";
		private String currentMapName = Johto;
		private boolean changemap = false;
		private boolean tilesLoaded = false;
		private int[] impassibleTiles = new int[] {
			3,4,5,6,7,8,11,12,13,14,15,16,18,19,
			20,21,22,23,24,40,41,42,48,49,50,51,
			52,56,57,58,59,60,61,64,65,66,67,68,
			69,70,71,72,73,74,75,76,77,78,79,80,
			81,82,83,84,86,87,88,89,90,91,92,93,
			94,95,96,97,98,99,100,101,102,103,104,
			105,106,107,108,109,110,111,112,113,114,
			115,116,117,118,119,120,121,122,123,124,
			125,126,127,128,129,130,131,132,134,
			135,136,137,138,139,140,141,142,143,144,
			145,146,147,148,149,150,151,152,153,154,
			155,156,157,158,159,160,161,162,163,164,
			166,167,168,169,170,171,172,173,174,
			175,176,177,178,179,180,181,182,183,184,
			185,186,187,188,189,190,191,192,193,194,
			195,196,197,198,199,200,201,202,203,204,
			205,206,207,208,209,210,211,212,213,214,
			215,216,218,219,220,221,222,223,224,
			225,226,227,228,229,230,231,232,234,
			235,236,237,238,239,240,241,242,243,244,
			245,246,247,248,249,251,252,253,296,471,
			477,478,479,486,494,495,501,503,509,808,
			809,810,811,812,813,816,817,818,819,820,
			821,824,825,861,864,870,871,872,880,888,
			890,896,914,922,728,729,730,731,732,733,
			736,737,738,739,740,741,742,743,746,770,
			771,772,773,774,775,798,762,764,765,769,
			515,512,520,521,528,529};
		private int[] currentMap0 = new int[12500];
		private int[] currentMap1 = new int[12500];
		private StaticTile[] currentMapStaticTiles = new StaticTile[12500];
    	private int mapTilesX;
   	 	private int mapTilesY;
		private int x_coor = 0;
		private int y_coor = 0;
		private int tile_number = 0;
		private boolean showmessagebox = false;
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	// NPC's
	//-----------------------------------------------------------------
		private Image baldman = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Baldman.png"));
		private Image beauty = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Beauty.png"));
		private Image bill = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Bill.png"));
		private Image birdkeeperl = tk.createImage(getClass().getResource("Graphics/Characters/NPC/BirdKeeperL.png"));
		private Image birdkeeperr = tk.createImage(getClass().getResource("Graphics/Characters/NPC/BirdKeeperR.png"));
		private Image boy = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Boy.png"));
		private Image bugcatcher = tk.createImage(getClass().getResource("Graphics/Characters/NPC/BugCatcher.png"));
		private Image camperd = tk.createImage(getClass().getResource("Graphics/Characters/NPC/CamperD.png"));
		private Image camperl = tk.createImage(getClass().getResource("Graphics/Characters/NPC/CamperL.png"));
		private Image falkner = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Falkner.png"));
		private Image fatman = tk.createImage(getClass().getResource("Graphics/Characters/NPC/FatMan.png"));
		private Image fisher = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Fisher.png"));
		private Image guidegent = tk.createImage(getClass().getResource("Graphics/Characters/NPC/GuideGent.png"));
		private Image lass = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Lass.png"));
		private Image mom = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Mom.png"));
		private Image mrpokemon = tk.createImage(getClass().getResource("Graphics/Characters/NPC/MrPokemon.png"));
		private Image nurse = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Nurse.png"));
		private Image picknicker = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Picknicher.png"));
		private Image oak = tk.createImage(getClass().getResource("Graphics/Characters/NPC/ProfOak.png"));
		private Image shopkeep = tk.createImage(getClass().getResource("Graphics/Characters/NPC/ShopKeep.png"));
		private Image youngster = tk.createImage(getClass().getResource("Graphics/Characters/NPC/Youngster.png"));
		
		private NPC viloet_citizen1 = new NPC (42,30,"Citizen","We care about the traditional buildings around here.",baldman,null);
		private NPC viloet_citizen2 = new NPC (34,34,"Citizen","It is rumored that there are ghost pokemon in the Sprout Tower.",bill,null);
		private NPC viloet_citizen3 = new NPC (30,23,"Citizen","Hey, your a pokemon trainer! If you beat the gym leader, you'll be ready for the big time.",camperl,null);
		private NPC viloet_citizen4 = new NPC (24,26,"Citizen","Falkner, from Violet City pokemon gym, is a fine trainer.",boy,null);
		private NPC viloet_citizen5 = new NPC (11,23,"Citizen","You can't have your pokemon out with you in all places.",fatman,null);
		
		private NPC violet_indoors1 = new NPC (8,108,"Citizen","There are many wild Pokemon in the tall grass.",baldman,null);
		private NPC violet_indoors2 = new NPC (3,107,"Citizen","Do you want to trade Pokemon?",boy,null);
		
		private NPC school_student1 = new NPC (23,90,"Citizen","I want to learn how to become a Pokemon Master.",youngster,null);
		private NPC school_student2 = new NPC (25,90,"Citizen","What type of Pokemon is Pikachu?",boy,null);
		private NPC school_student3 = new NPC (26,90,"Citizen","Are you a Pokemon trainer?",beauty,null);
		private NPC school_student4 = new NPC (23,88,"Citizen","The Sprout Tower is a shrine to Bellsprout.",youngster,null);
		private NPC school_student5 = new NPC (25,86,"Citizen","Whadaya want from me!",camperl,null);
		private NPC school_student6 = new NPC (24,84,"Citizen","You're never too old to learn about Pokemon.",baldman,null);
		
		private NPC mart_customer1 = new NPC (24,106,"Citizen","I wonder if they carry Pokeballs.",youngster,null);
		private NPC store_clerk = new NPC (20,107,"Citizen","Can I help you with something?",shopkeep,null);
		
		private NPC center_visitor1 = new NPC (40, 107,"Citizen","You can heal your Pokemon by talking to Nurse Joy.",fatman,null);
		private NPC nurse_joy = new NPC (42,105,"Citizen","We hope to see you again!",nurse,null);
		
		private NPC bird_keeper1 = new NPC (5,89,"Citizen","I want to be like Falkner.",birdkeeperr,null);
		private NPC bird_keeper2 = new NPC (10,85,"Citizen","Falkner is the best.",birdkeeperl,null);
		private NPC leader_faulkner = new NPC (7,81,"Citizen","My precious bird Pokemon are unstopable.",falkner,null);
		
		private NPC old_man1= new NPC (88, 102,"Citizen","I can guide you around cherrygrove!",guidegent,null);
		private NPC cherrygrove_citizen1= new NPC (84, 105,"Citizen","I'm hungry.",fatman,null);
		private NPC cherrygrove_citizen2= new NPC (83, 108,"Citizen","Cherrygrove City is beautiful.",lass,null);
		private NPC cherrygrove_citizen3= new NPC (78, 104,"Citizen","Nothing interesting happens here.",boy,null);	
		
		private NPC[] currentMapNPC = new NPC[] {
			viloet_citizen1,
			viloet_citizen2,
			viloet_citizen3,
			viloet_citizen4,
			viloet_citizen5,
			violet_indoors1,
			violet_indoors2,
			school_student1,
			school_student2,
			school_student3,
			school_student4,
			school_student5,
			school_student6,
			mart_customer1,
			store_clerk,
			center_visitor1,
			nurse_joy,
			bird_keeper1,
			bird_keeper2,
			leader_faulkner,
			old_man1,
			cherrygrove_citizen1,
			cherrygrove_citizen2,
			cherrygrove_citizen3	
		};
	//-----------------------------------------------------------------
		
	//-----------------------------------------------------------------
	// Battle Variables
	//-----------------------------------------------------------------
		private TrainerBattleScene trainerencounter;
		public Monsters trainerparty[] = new Monsters[6];
		private BattleScene encounter;
		public boolean inBattle = false;
		private int r;
		private int rndwildmodify = 15;
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	// Menu Variables
	//-----------------------------------------------------------------
		private MenuScene menu;
		public boolean inMenu = false;
		private boolean disable_start = false;
		private Image arrow = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/Arrow.png"));
		private Image messagebox = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/Message_Text.png"));
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	// Sound Variables
	//-----------------------------------------------------------------
		public MidiPlayer currentBGM;
		public MidiPlayer lastBGM;
		private MidiPlayer newbarktown = new MidiPlayer("Audio/BGM/NewBarkTown.mid",true);
		private MidiPlayer route29 = new MidiPlayer("Audio/BGM/Route29.mid",true);
		private MidiPlayer cherrygrovecity = new MidiPlayer("Audio/BGM/CherrygroveCity.mid",true);
		private MidiPlayer route30 = new MidiPlayer("Audio/BGM/Route30.mid",true);
		private MidiPlayer violetcity = new MidiPlayer("Audio/BGM/VioletCity.mid",true);
		private MidiPlayer pokecenter = new MidiPlayer("Audio/BGM/PokemonCenter.mid",true);
		private MidiPlayer pokemart = new MidiPlayer("Audio/BGM/PokeMart.mid",true);
		private MidiPlayer gym = new MidiPlayer("Audio/BGM/Gym.mid",true);
		private MidiPlayer championBGM = new MidiPlayer("Audio/BGM/ChampionBattle.mid",true);
		private MidiPlayer battleBGM = new MidiPlayer("Audio/BGM/WildBattle.mid",true);
		private MidiPlayer victoryjingle = new MidiPlayer("Audio/SE/Wild_Victory.mid",false);
		private JukeBox col = new JukeBox();
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	// Pokemon Constructor
	//-----------------------------------------------------------------
	
	public Pokemon(){
		currentBGM = title;
		currentBGM.start();
		menu = new MenuScene(this);
		col.loadClip("Audio/SE/Collision.wav", "Collision", 1);
	    col.loadClip("Audio/SE/Select.wav", "Select", 1);
	    col.loadClip("Audio/SE/Menu.wav", "Menu", 1);
	    col.loadClip("Audio/SE/Damage.wav", "Damage", 1);
		setBackground(Color.BLACK);
	    setPreferredSize(new Dimension(480,320));
		addKeyListener(this);
		gameTimer = new javax.swing.Timer(350, this);
	    gameTimer.start();
	}
	
	//-----------------------------------------------------------------
	// ActionListener
	//-----------------------------------------------------------------
	
	public void actionPerformed (ActionEvent e) {
		currentTime = java.lang.System.currentTimeMillis();
		if (gamestarted == true) {
			//-----------------------------------------------------------------
			// Battle Scene
			//-----------------------------------------------------------------
			if (inBattle == true) {
				if (encounter.playerPokemon.getCurrentHP() <= 0) {
					//Whited Out
					System.out.println("Player Pokemon has fainted");
					System.out.println(gold.getName() + " is all out of usable Pokemon!");
					System.out.println(gold.getName() + " whited out.");
					encounter.whiteOut();
					currentX_loc += 42-posX_tile;
					currentY_loc += 107-posY_tile;
					posX_tile = 42;
					posY_tile = 107;
					gold.setSprite(playerUp);
					lastdir = 1;
					playerPokemon1.healPokemon();
					changeBGM(pokecenter);
				}
				if (encounter.enemyPokemon.getCurrentHP() <= 0) {
					encounter.playerWon = true;
					System.out.println("Wild Pokemon has fainted");
					encounter.Win();
				}
				if (encounter.playerTurn == false) {
					wait(1);
					encounter.enemyTurn();
				}
			}
			//-----------------------------------------------------------------
		 	seconds = (currentTime-timePlayed)/1000;
			gold.setCurrentX(posX_tile);
			gold.setCurrentY(posY_tile);
			//Random Encounter Variables
			r = (int)((5-1)*Math.random()+1);
			rndwildmodify = randGen.nextInt(22) + 11;
			//Wild Pokemon Encounter Check
			checkBattle();
			//Teleport Code
			transfer();
			//Can't walk outside of the Map Array
			if (posX_tile <= 0) {
				movable_left = false;
			}
			if (posX_tile >= mapTilesX-1) {
				movable_right = false;
			}
			if (posY_tile <= 0) {
				movable_up = false;
			}
			if (posY_tile >= mapTilesY-1) {
				movable_down = false;
			}
			//Crashtesting with NPC's
			movable_up = true;
			movable_down = true;
			movable_left = true;
			movable_right = true;
			if (noClip == false) {
				for (int i = 0; i < currentMapNPC.length; i++) {
					if (gold.crashTest(currentMapNPC[i]) == 1) {
						movable_up = false;
						disable_talk = false;
					}
					else if (gold.crashTest(currentMapNPC[i]) == 2) {
						movable_down = false;
						disable_talk = false;
					}
					else if (gold.crashTest(currentMapNPC[i]) == 3) {
						movable_left = false;
						disable_talk = false;
					}
					else if (gold.crashTest(currentMapNPC[i]) == 4) {
						movable_right = false;
						disable_talk = false;
					}
					if (gold.crashTest(currentMapNPC[i]) != 0) {
						if (collision == true) {
							col.playClip("Collision");
							collision = false;
						}
					}
				}
				//Crashtesting with Inpassible Objects
				for (int i = 0; i < currentMapStaticTiles.length; i++) {
					if (gold.crashTest(currentMapStaticTiles[i]) == 1) {
						movable_up = false;
					}
					if (gold.crashTest(currentMapStaticTiles[i]) == 2) {
						movable_down = false;
					}
					if (gold.crashTest(currentMapStaticTiles[i]) == 3) {
						movable_left = false;
					}
					if (gold.crashTest(currentMapStaticTiles[i]) == 4) {
						movable_right = false;
					}
					if (gold.crashTest(currentMapStaticTiles[i]) != 0) {
						if (collision == true) {
							col.playClip("Collision");
							collision = false;
						}
					}
				}
			}
			//Movement Scrolling
			if (walking == true) {
				movespritepixels++;
				if (up == true && movable_up == true) {
			    	offsetY+=2;
			    }
			    if (down == true && movable_down == true) {
			    	offsetY-=2;
			    }
			    if (left == true && movable_left == true) {
			    	offsetX+=2;
			    }
			    if (right == true && movable_right == true) {
			    	offsetX-=2;
			    }
		    }
		    //Movement Reset
		    if (movespritepixels >= 16) {
				movespritepixels = 0;
				walking = false;
				if (up == true && movable_up == true) posY_tile -= 1;
				if (down == true && movable_down == true) posY_tile += 1;
				if (left == true && movable_left == true) posX_tile -= 1;
				if (right == true && movable_right == true) posX_tile += 1;
				up = false;
				down = false;
				left = false;
				right = false;
				footsprite = !footsprite;
				if (playerPokemon1.cur_HP <= 0) {
					System.out.println(playerPokemon1.name + " has fainted.");
					System.out.println(gold.getName() + " is all out of usable Pokemon.");
					System.out.println(gold.getName() + " whited out.");
					currentX_loc += 42-posX_tile;
					currentY_loc += 107-posY_tile;
					posX_tile = 42;
					posY_tile = 107;
					playerPokemon1.healPokemon();
					gold.setSprite(playerUp);
				}
				if (playerPokemon1.statusEffect == 2 || playerPokemon1.statusEffect == 3) {
					playerPokemon1.cur_HP -= 1;
				}
			}
			//Player Sprite Animations (Up)
		    if (up == true && movable_up == true) {
		        if (movespritepixels >= 0 && movespritepixels < 4) {
			        gold.setSprite(playerUp);
		        }
		        else if (movespritepixels > 4 && movespritepixels < 8) {
		        	gold.setSprite(playerUp);
		        }
		        else if (movespritepixels > 8 && movespritepixels < 12) {
		        	if (footsprite == false) {
		        		gold.setSprite(playerUp1);
		        	}
		        	else {
		        		gold.setSprite(playerUp2);
		        	}
		        }
		        else if (movespritepixels >= 12 && movespritepixels < 15) {
			        if (footsprite == false) {
		        		gold.setSprite(playerUp1);
		        	}
		        	else {
		        		gold.setSprite(playerUp2);
		        	}
		        }
		        else {
		        	gold.setSprite(playerUp);
		        }
		    }
		    //Player Sprite Animations (Down)
		    if (down == true && movable_down == true) {
		        if (movespritepixels >= 0 && movespritepixels < 4) {
			        gold.setSprite(playerDown);
		        }
		        else if (movespritepixels > 4 && movespritepixels < 8) {
			        gold.setSprite(playerDown);
		        }
		        else if (movespritepixels > 8 && movespritepixels < 12) {
			        if (footsprite == false) {
		        		gold.setSprite(playerDown1);
		        	}
		        	else {
		        		gold.setSprite(playerDown2);
		        	}
		        }
		        else if (movespritepixels >= 12 && movespritepixels < 15) {
			        if (footsprite == false) {
		        		gold.setSprite(playerDown1);
		        	}
		        	else {
		        		gold.setSprite(playerDown2);
		        	}
		        }
		        else {
		        	gold.setSprite(playerDown);
		        }
		    }
		    //Player Sprite Animations (Left)
		    if (left == true && movable_left == true) {
		        if (movespritepixels >= 0 && movespritepixels < 4) {
			        gold.setSprite(playerLeft);
		        }
		        else if (movespritepixels > 4 && movespritepixels < 8) {
			        gold.setSprite(playerLeft);
		        }
		        else if (movespritepixels > 8 && movespritepixels < 12) {
			        if (footsprite == false) {
		        		gold.setSprite(playerLeft1);
		        	}
		        	else {
		        		gold.setSprite(playerLeft2);
		        	}
		        }
		        else if (movespritepixels >= 12 && movespritepixels < 15) {
			        if (footsprite == false) {
		        		gold.setSprite(playerLeft1);
		        	}
		        	else {
		        		gold.setSprite(playerLeft2);
		        	}
		        }
		        else {
		        	gold.setSprite(playerLeft);
		        }
		    }
		    //Player Sprite Animations (Right)
		    if (right == true && movable_right == true) {
		        if (movespritepixels >= 0 && movespritepixels < 4) {
			        gold.setSprite(playerRight);
		        }
		        else if (movespritepixels > 4 && movespritepixels < 8) {
			        gold.setSprite(playerRight);
		        }
		        else if (movespritepixels > 8 && movespritepixels < 12) {
			        if (footsprite == false) {
		        		gold.setSprite(playerRight1);
		        	}
		        	else {
		        		gold.setSprite(playerRight2);
		        	}
		        }
		        else if (movespritepixels >= 12 && movespritepixels < 15) {
			        if (footsprite == false) {
		        		gold.setSprite(playerRight1);
		        	}
		        	else {
		        		gold.setSprite(playerRight2);
		        	}
		        }
		        else {
		        	gold.setSprite(playerRight);
		        }
		    }
		}
		else {
			//Title Screen
			start_visible = !start_visible;
		}
		repaint();
	}
	
	//-----------------------------------------------------------------
	// Paint Code
	//-----------------------------------------------------------------
	
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D)g;
		AffineTransform at = new AffineTransform();
		g2.setTransform(at);
    	if (atTitle == true) {
    		g.drawImage(titlescreen,0,0,null);
    		if (start_visible == true) {
    			g.drawImage(start_symbol,0,260,null);
    		}
    	}
    	else if (atContinueScreen == true) {
    		g.drawImage(continuescreen,0,0,null);
    		if (concurrentMenuItem == 0) {
	    	 	g.drawImage(arrow, 13, 20, null);
	    	 }
	    	else if (concurrentMenuItem == 1) {
	    	    g.drawImage(arrow, 13, 52, null);
	    	}
	    	else if (concurrentMenuItem == 2) {
	    	    g.drawImage(arrow, 13, 84, null);
	    	}
    	}
    	else {
    		if (inBattle == false) {
    	  		//Draw the Map
	            g2.setClip(new Rectangle(posX - 240, posY - 160, posX + 480, posY + 320));
	            g2.translate(offsetX-(currentX_loc*32), offsetY-(currentY_loc*32));
	    	 	for (int y = 1; y <= mapTilesY; y++) {
	                 for (int x = 1; x <= mapTilesX; x++) {
	                 	//Layer 0
	                 	if (currentMap0[tile_number] != 0) {
	                 		g.drawImage(tileset[currentMap0[tile_number]-1],x_coor,y_coor,null);
	                 	}
	                 	//Layer 1
	                 	if (currentMap1[tile_number] != 0) {
	                 		g.drawImage(tileset[currentMap1[tile_number]-1],x_coor,y_coor,null);	
	                 	}
	                 	//Impassible Tiles
	                 	if (tilesLoaded == false) {
	                 		for (int i = 0; i < impassibleTiles.length; i++) {
		                 		if (currentMap0[tile_number] == impassibleTiles[i] || 
		                 			currentMap1[tile_number] == impassibleTiles[i]) {
		                 			if (noClip == false) {
		                 				currentMapStaticTiles[tile_number] = new StaticTile(x_coor/32,y_coor/32,null);
		                 			}
		                    	}
		                 	}
	                 	}
	                    x_coor = x_coor + 32;
	                    tile_number = tile_number + 1;
	                 }
	                 x_coor = 0;
	                 y_coor = y_coor + 32;
	              }
	              tilesLoaded = true;
	              tile_number = 0;
	              x_coor = 0;
	              y_coor = 0;
	              //NPC Sprites
	              for (int i = 0; i < currentMapNPC.length; i++) {
	              	 g.drawImage(currentMapNPC[i].getSprite(),
	              		currentMapNPC[i].getCurrentX()*TILE_WIDTH_PIXELS,
	              		currentMapNPC[i].getCurrentY()*TILE_HEIGHT_PIXELS-10,null);
	              	/*g.drawImage(currentMapNPC[i].getSprite(),
	              	currentMapNPC[i].getCurrentX()*32,
	              	currentMapNPC[i].getCurrentY()*32,
	              	currentMapNPC[i].getWidth(),
	              	currentMapNPC[i].getHeight(),
	              	currentMapNPC[i].getWidth(),
	              	currentMapNPC[i].getHeight(),
	              	null);*/
	              }
	              
	              //Reset to 0,0
	              g2.translate(-offsetX, -offsetY);
	              //Player Sprites
	              g2.setTransform(at);
	              g.drawImage(gold.getSprite(),posX,posY,null);
	              g.setFont(pokefont);
	              g.setColor(Color.WHITE);
	              g.drawString("" + posX_tile + "," + posY_tile,10,25);
	              showMessageBox(g);
	    	 }
	    	 else {
	    	 	encounter.paint(g);
	    	 }
	    	 if (inMenu == true) {
	    		menu.paint(g);
	         }
     	 }
     }
     
     //-----------------------------------------------------------------
	 // KeyListener Code
	 //-----------------------------------------------------------------

     public void keyPressed(KeyEvent e) {
     	int keyCode = e.getKeyCode();
     	if (atTitle == true) {
     		if (keyCode == KeyEvent.VK_ENTER) {
     			gameTimer.setDelay(20);
	        	atTitle = false;
	        	currentBGM.stop();
				currentBGM = continuebgm;
				currentBGM.start();
	        	atContinueScreen = true;
	        }
     	}
     	else if (atContinueScreen == true && atTitle == false) {
			if (keyCode == KeyEvent.VK_UP) {
				button_pressed();
				if (concurrentMenuItem > 0) {
					concurrentMenuItem--;
				}
	        }
	        else if (keyCode == KeyEvent.VK_DOWN) {
				button_pressed();
				if (concurrentMenuItem < 2) {
					concurrentMenuItem++;
				}
	        }
	        if (keyCode == KeyEvent.VK_Z) {
				button_pressed();
				if (concurrentMenuItem == 0) {
					//Continue
					startgame(true);
				}
				else if (concurrentMenuItem == 1) {
					//New Game
					startgame(false);
				}
				else if (concurrentMenuItem == 2) {
					//Options
				}
	        }
     	}
     	else if (gamestarted == true) {
			if (inMenu == false && movable == true && inBattle == false) {
				if (walking == false) {
			        if (keyCode == KeyEvent.VK_UP) {
			        	crashTest(currentMap0);
			        	crashTest(currentMap1);
			        	collision = true;
			        	if (movable_up == true) {
				        	up = true;
				        	walking = true;
			        	}
			        	else {
			        		gold.setSprite(playerUp);
			        	}
			        }
			        else if (keyCode == KeyEvent.VK_DOWN) {
			        	crashTest(currentMap0);
			        	crashTest(currentMap1);
			        	collision = true;
			        	if (movable_down == true) {
			        		down = true;
			        		walking = true;
			        	}
			        	else {
			        		gold.setSprite(playerDown);
			        	}
			        }
			        else if (keyCode == KeyEvent.VK_LEFT) {
			        	crashTest(currentMap0);
			        	crashTest(currentMap1);
			        	collision = true;
			        	if (movable_left == true) {
			        		left = true;
			        		walking = true;
			        	}
			        	else {
			        		gold.setSprite(playerLeft);
			        	}
			        }
			        else if (keyCode == KeyEvent.VK_RIGHT) {
			        	crashTest(currentMap0);
			        	crashTest(currentMap1);
			        	collision = true;
			        	if (movable_right == true) {
				        	right = true;
				        	walking = true;
			        	}
			        	else {
			        		gold.setSprite(playerRight);
			        	}
			        }
			        else if (keyCode == KeyEvent.VK_ENTER) {
			        	System.out.println("Menu Button");
						col.playClip("Menu");
						menu.inMain = true;
			        	inMenu = true;
			        }
			        if (keyCode == KeyEvent.VK_Z) {
			        	System.out.println("Action Button");
			        	if (disable_talk == false) {
			        		talkable = !talkable;
				        	movable_up = !movable_up;
				        	movable_down = !movable_down;
				        	movable_left = !movable_left;
				        	movable_right = !movable_right;
			        	}
			        }
			        else if (keyCode == KeyEvent.VK_X) {
			        	System.out.println("Cancel Button");
			        }
				}
			}
			//-----------------------------------------------------------------
		    // Menu Scene
		    //-----------------------------------------------------------------
			if (inMenu == true) {
				if (menu.inMain == true) {
				    if (keyCode == KeyEvent.VK_UP) {
				    	if (menu.currentSelectionMain > 0) {
				    		menu.currentSelectionMain--;
				    	}
				    	button_pressed();
				    }
				    else if (keyCode == KeyEvent.VK_DOWN) {
				    	if (menu.currentSelectionMain < 7) {
				    		menu.currentSelectionMain++;
				    	}
				    	button_pressed();
				    }
				    if (keyCode == KeyEvent.VK_Z) {
				    	if (menu.currentSelectionMain == 0) {
				    		menu.PokeDex();
				    	}
						if (menu.currentSelectionMain == 1) {
				    		menu.Pokemon();
				    	}
				    	if (menu.currentSelectionMain == 2) {
				    		menu.Bag();
				    	}
				    	if (menu.currentSelectionMain == 3) {
				    		menu.PokeGear();
				    	}
				    	if (menu.currentSelectionMain == 4) {
				    		menu.TrainerCard();
				    	}
				    	if (menu.currentSelectionMain == 5) {
				    		menu.Save();
				    	}
				    	if (menu.currentSelectionMain == 6) {
				    		menu.Option();
				    	}
				    	if (menu.currentSelectionMain == 7) {
				    		menu.Exit();
				    	}
				    	button_pressed();
				    }
				    if (keyCode == KeyEvent.VK_X) {
				    	menu.Exit();
				    	button_pressed();
				    }
		    	}
		    	if (menu.inPokeDex == true) {
				    if (keyCode == KeyEvent.VK_X) {
				    	menu.inPokeDex = false;
				    	menu.inMain = true;
				    	button_pressed();
				    }
		    	}
		    	if (menu.inPokemon == true) {
				    if (keyCode == KeyEvent.VK_X) {
				    	menu.inPokemon = false;
				    	menu.inMain = true;
				    	button_pressed();
				    }
		    	}
		    	if (menu.inBag == true) {
				    if (keyCode == KeyEvent.VK_X) {
				    	menu.inBag = false;
				    	menu.inMain = true;
				    	button_pressed();
				    }
		    	}
		    	if (menu.inPokeGear == true) {
		    		if (keyCode == KeyEvent.VK_UP) {
				    	if (menu.currentSelectionPokeGear > 0) {
				    		menu.currentSelectionPokeGear--;
				    	}
				    	button_pressed();
				    }
				    else if (keyCode == KeyEvent.VK_DOWN) {
				    	if (menu.currentSelectionPokeGear < 3) {
				    		menu.currentSelectionPokeGear++;
				    	}
				    	button_pressed();
				    }
				    if (keyCode == KeyEvent.VK_Z) {
				    	if (menu.currentSelectionPokeGear == 0) {
				    		System.out.println("Map");
				    	}
				    	else if (menu.currentSelectionPokeGear == 1) {
				    		System.out.println("Radio");
				    	}
				    	else if (menu.currentSelectionPokeGear == 2) {
				    		System.out.println("Phone");
				    	}
				    	else if (menu.currentSelectionPokeGear == 3) {
				    		System.out.println("Exit");
				    		menu.currentSelectionPokeGear = 0;
				    		menu.inPokeGear = false;
				    		menu.inMain = true;
				    	}
				    	button_pressed();
				    }
				    if (keyCode == KeyEvent.VK_X) {
				    	menu.inPokeGear = false;
				    	menu.inMain = true;
				    	button_pressed();
				    }
		    	}
		    	if (menu.inTrainerCard == true) {
				    if (keyCode == KeyEvent.VK_X) {
				    	menu.inTrainerCard = false;
				    	menu.inMain = true;
				    	button_pressed();
				    }
		    	}
		    	if (menu.inSave == true) {
		    		if (keyCode == KeyEvent.VK_UP) {
				    	menu.currentSelectionSave = 0;
				    	button_pressed();
				    }
				    else if (keyCode == KeyEvent.VK_DOWN) {
				    	menu.currentSelectionSave = 1;
				    	button_pressed();
				    }
				    if (keyCode == KeyEvent.VK_Z) {
				    	if (menu.currentSelectionSave == 0) {
				    		saveGame();
				    		System.out.println(name + "'s Game has been saved!");
				    		//menu.inSave = false;
				    		//menu.inMain = true;
				    	}
				    	else {
				    		menu.inSave = false;
				    		menu.inMain = true;
				    	}
				    	button_pressed();
				    }
				    if (keyCode == KeyEvent.VK_X) {
				    	menu.inSave = false;
				    	menu.inMain = true;
				    	button_pressed();
				    }
		    	}
		    	if (menu.inOption == true) {
		    		if (keyCode == KeyEvent.VK_UP) {
				    	if (menu.currentSelectionOption > 0) {
				    		menu.currentSelectionOption--;
				    	}
				    	button_pressed();
				    }
				    else if (keyCode == KeyEvent.VK_DOWN) {
				    	if (menu.currentSelectionOption < 5) {
				    		menu.currentSelectionOption++;
				    	}
				    	button_pressed();
				    }
				    if (keyCode == KeyEvent.VK_Z) {
				    	button_pressed();
				    }
				    if (keyCode == KeyEvent.VK_X) {
				    	menu.inOption = false;
				    	menu.inMain = true;
				    	button_pressed();
				    }
		    	}
		    }
		    //-----------------------------------------------------------------
		    // Battle Scene
		    //-----------------------------------------------------------------
		    if (inBattle == true) {
		    	if (encounter.inMain == true) {
		    		if (encounter.playerTurn == true) {
				    	if (keyCode == KeyEvent.VK_UP) {
				    		encounter.currentSelectionMainY = 0;
				    		button_pressed();
				    	}
				    	else if (keyCode == KeyEvent.VK_DOWN) {
				    		encounter.currentSelectionMainY = 1;
				    		button_pressed();
				    	}
				    	else if (keyCode == KeyEvent.VK_LEFT) {
				    		encounter.currentSelectionMainX = 0;
				    		button_pressed();
				    	}
				    	else if (keyCode == KeyEvent.VK_RIGHT) {
				    		encounter.currentSelectionMainX = 1;
				    		button_pressed();
				    	}
				    	if (keyCode == KeyEvent.VK_Z) {
				    		if (encounter.currentSelectionMainX == 0 && encounter.currentSelectionMainY == 0) {
				    			encounter.Fight();
				    		}
				    		if (encounter.currentSelectionMainX == 1 && encounter.currentSelectionMainY == 0) {
				    			encounter.Pokemon();
				    		}
				    		if (encounter.currentSelectionMainX == 0 && encounter.currentSelectionMainY == 1) {
				    			encounter.Item();
				    		}
				    		if (encounter.currentSelectionMainX == 1 && encounter.currentSelectionMainY == 1) {
				    			encounter.Run();
				    		}
				    		button_pressed();
				    	}
		    		}
		    	}
		    	if (encounter.inFight == true) {
		    		if (encounter.playerTurn == true) {
				    	if (keyCode == KeyEvent.VK_UP) {
				    		encounter.currentSelectionFightY = 0;
				    		button_pressed();
				    	}
				    	else if (keyCode == KeyEvent.VK_DOWN) {
				    		encounter.currentSelectionFightY = 1;
				    		button_pressed();
				    	}
				    	else if (keyCode == KeyEvent.VK_LEFT) {
				    		encounter.currentSelectionFightX = 0;
				    		button_pressed();
				    	}
				    	else if (keyCode == KeyEvent.VK_RIGHT) {
				    		encounter.currentSelectionFightX = 1;
				    		button_pressed();
				    	}
				    	if (keyCode == KeyEvent.VK_Z) {
				    		Random rr = new Random();
				    		int wakeupthaw = rr.nextInt(5);
				    		if (wakeupthaw <= 1) {
				    			if (encounter.playerPokemon.statusEffect == 4) {
				    				System.out.println(encounter.playerPokemon.name + " has woken up.");
				    			}
				    			if (encounter.playerPokemon.statusEffect == 5) {
				    				System.out.println(encounter.playerPokemon.name + " has broken free from the ice.");
				    			}
				    			encounter.playerPokemon.statusEffect = 0;
				    		}
				    		if (encounter.playerPokemon.statusEffect != 4 || encounter.playerPokemon.statusEffect != 5) {
					    		if (encounter.currentSelectionFightX == 0 && encounter.currentSelectionFightY == 0) {
					    			//Attack 1 seems to be bugged
					    			//It is also auto selected after FIGHT
					    			System.out.println("Attack 1 Selected");
					    		}
					    		if (encounter.currentSelectionFightX == 1 && encounter.currentSelectionFightY == 0) {
					    			System.out.println("Attack 2 Selected");
					    			if (encounter.playerPokemon.statusEffect == 1) {
					    				Random r = new Random();
					    				int rand = r.nextInt(2);
					    				if (rand <= 0) {
					    					encounter.enemyPokemon.setCurrentHP(new Attacks(encounter.playerPokemon.move2).getDamage(encounter.enemyPokemon));
					    					System.out.println(encounter.enemyPokemon.getCurrentHP());
					    				}
					    				else {
					    					System.out.println(encounter.playerPokemon.name + " is paralyzed. It can't move.");
					    				}
					    			}
					    			else {
					    				encounter.enemyPokemon.setCurrentHP(new Attacks(encounter.playerPokemon.move2).getDamage(encounter.enemyPokemon));
					    				System.out.println(encounter.enemyPokemon.getCurrentHP());
					    			}
					    			if (encounter.playerPokemon.statusEffect == 2) {
										encounter.playerPokemon.cur_HP -= 2;
										System.out.println(encounter.playerPokemon.name + " has been hurt by its burn");
									}
									else if (encounter.playerPokemon.statusEffect == 3) {
										encounter.playerPokemon.cur_HP -= 2;
										System.out.println(encounter.playerPokemon.name + " has been hurt by its poison");
									}
					    			encounter.playerTurn = false;
					    			encounter.inMain = true;
					    			encounter.inFight = false;
					    			encounter.currentSelectionMainX = 0;
					    			encounter.currentSelectionMainY = 0;
					    			encounter.currentSelectionFightX = 0;
					    			encounter.currentSelectionFightY = 0;
					    			col.playClip("Damage");
					    		}
					    		if (encounter.currentSelectionFightX == 0 && encounter.currentSelectionFightY == 1) {
					    			System.out.println("Attack 3 Selected");
					    			if (encounter.playerPokemon.statusEffect == 1) {
					    				Random r = new Random();
					    				int rand = r.nextInt(2);
					    				if (rand <= 0) {
					    					encounter.enemyPokemon.setCurrentHP(new Attacks(encounter.playerPokemon.move3).getDamage(encounter.enemyPokemon));
					    					System.out.println(encounter.enemyPokemon.getCurrentHP());
					    				}
					    				else {
					    					System.out.println(encounter.playerPokemon.name + " is paralyzed. It can't move.");
					    				}
					    			}
					    			else {
					    				encounter.enemyPokemon.setCurrentHP(new Attacks(encounter.playerPokemon.move3).getDamage(encounter.enemyPokemon));
					    				System.out.println(encounter.enemyPokemon.getCurrentHP());
					    			}
					    			if (encounter.playerPokemon.statusEffect == 2) {
										encounter.playerPokemon.cur_HP -= 2;
										System.out.println(encounter.playerPokemon.name + " has been hurt by its burn");
									}
									else if (encounter.playerPokemon.statusEffect == 3) {
										encounter.playerPokemon.cur_HP -= 2;
										System.out.println(encounter.playerPokemon.name + " has been hurt by its poison");
									}
					    			encounter.playerTurn = false;
					    			encounter.inMain = true;
					    			encounter.inFight = false;
					    			encounter.currentSelectionMainX = 0;
					    			encounter.currentSelectionMainY = 0;
					    			encounter.currentSelectionFightX = 0;
					    			encounter.currentSelectionFightY = 0;
					    			col.playClip("Damage");
					    		}
					    		if (encounter.currentSelectionFightX == 1 && encounter.currentSelectionFightY == 1) {
					    			System.out.println("Attack 4 Selected");
					    			if (encounter.playerPokemon.statusEffect == 1) {
					    				Random r = new Random();
					    				int rand = r.nextInt(2);
					    				if (rand <= 0) {
					    					encounter.enemyPokemon.setCurrentHP(new Attacks(encounter.playerPokemon.move4).getDamage(encounter.enemyPokemon));
					    					System.out.println(encounter.enemyPokemon.getCurrentHP());
					    				}
					    				else {
					    					System.out.println(encounter.playerPokemon.name + " is paralyzed. It can't move.");
					    				}
					    			}
					    			else {
					    				encounter.enemyPokemon.setCurrentHP(new Attacks(encounter.playerPokemon.move4).getDamage(encounter.enemyPokemon));
					    				System.out.println(encounter.enemyPokemon.getCurrentHP());
					    			}
					    			if (encounter.playerPokemon.statusEffect == 2) {
										encounter.playerPokemon.cur_HP -= 2;
										System.out.println(encounter.playerPokemon.name + " has been hurt by its burn");
									}
									else if (encounter.playerPokemon.statusEffect == 3) {
										encounter.playerPokemon.cur_HP -= 2;
										System.out.println(encounter.playerPokemon.name + " has been hurt by its poison");
									}
					    			encounter.playerTurn = false;
					    			encounter.inMain = true;
					    			encounter.inFight = false;
					    			encounter.currentSelectionMainX = 0;
					    			encounter.currentSelectionMainY = 0;
					    			encounter.currentSelectionFightX = 0;
					    			encounter.currentSelectionFightY = 0;
					    			col.playClip("Damage");
					    		}
				    		}
				    		else {
				    			System.out.println("Can't Attack");
				    		}
				    		button_pressed();
				    	}
				    	if (keyCode == KeyEvent.VK_X) {
				    		encounter.currentSelectionMainX = 0;
				    		encounter.currentSelectionMainY = 0;
				    		encounter.currentSelectionFightX = 0;
				    		encounter.currentSelectionFightY = 0;
				    		encounter.inFight = false;
				    		encounter.inMain = true;
				    		button_pressed();
				    	}
		    		}
		    	}
		    	if (encounter.inRun == true) {
		    		if (keyCode == KeyEvent.VK_Z) {
		    			encounter.confirmBattleEnd = true;
		    		}
		    	}
		    }
		    //-----------------------------------------------------------------
     	 }
     }
     
     public void keyReleased(KeyEvent e) {
     	int keyCode = e.getKeyCode();
     	if (keyCode == KeyEvent.VK_UP) {
			lastdir = 1;
		}
		else if (keyCode == KeyEvent.VK_DOWN) {
			lastdir = 2;
		}
		else if (keyCode == KeyEvent.VK_LEFT) {
			lastdir = 3;
		}
		else if (keyCode == KeyEvent.VK_RIGHT) {
			lastdir = 4;
     	}
     }
	 public void keyTyped(KeyEvent e){}
	 
	 public void button_pressed() {
		 col.playClip("Select");
     }
	 
	 public void crashTest(int[] map) {
	 	//Wild Pokemon Grass
        if (map[(posY_tile*mapTilesX)+posX_tile] == 17) {
        	stepscount++;
        }
	 }
	 
	 public void collision() {
		if (collision == true) {
			col.playClip("Collision");
			collision = false;
		}
	 }
	 
	 public void transfer() {
	 	//Music
	 	/*if (posX_tile == 73 && posY_tile == 94) {
	 		if (down == true) {
	 			changeBGM(cherrygrovecity);
	 		}
	 		if (up == true) {
	 			changeBGM(route30);
	 		}
	 	}*/
	 	//Cherrygrove to PokeCenter
	 	if (posX_tile == 85 && posY_tile == 99) {
		    currentX_loc -= 43;
			currentY_loc += 11;
			posX_tile = 42;
			posY_tile = 110;
			playerPokemon1.healPokemon();
			changeBGM(pokecenter);
	 	}
	 	//Cherrygrove to PokeMart
	 	if (posX_tile == 79 && posY_tile == 99) {
		    currentX_loc -= 58;
			currentY_loc += 11;
			posX_tile = 21;
			posY_tile = 110;
			changeBGM(pokemart);
	 	}
	 	//Cherrygrove City to House
	 	if (posX_tile == 73 && posY_tile == 103) {
		    currentX_loc -= 33;
			currentY_loc -= 10;
			posX_tile = 40;
			posY_tile = 93;
	 	}
	 	//Cherrygrove City to Violet City
	 	if (posX_tile == 40 && posY_tile == 94) {
		    currentX_loc += 33;
			currentY_loc += 10;
			posX_tile = 73;
			posY_tile = 104;
	 	}
	 	//Cherrygrove City to Player Home
	 	if (posX_tile == 81 && posY_tile == 105) {
		    currentX_loc -= 54;
			currentY_loc -= 36;
			posX_tile = 27;
			posY_tile = 69;
	 	}
	 	//Player Home to Cherrygrove City
	 	if (posX_tile == 27 && posY_tile == 70) {
		    currentX_loc += 54;
			currentY_loc += 36;
			posX_tile = 81;
			posY_tile = 106;
	 	}
	 	//Route 30 to Berry House
	 	if (posX_tile == 73 && posY_tile == 81) {
		    currentX_loc -= 34;
			currentY_loc -= 4;
			posX_tile = 39;
			posY_tile = 77;
	 	}
	 	//Berry House to Route 30
	 	if (posX_tile == 39 && posY_tile == 78) {
		    currentX_loc += 34;
			currentY_loc += 4;
			posX_tile = 73;
			posY_tile = 82;
	 	}
	 	//Violet City to PokeCenter
	 	if (posX_tile == 37 && posY_tile == 31) {
		    currentX_loc += 5;
			currentY_loc += 79;
			posX_tile = 42;
			posY_tile = 110;
			playerPokemon1.healPokemon();
			changeBGM(pokecenter);
	 	}
	 	//Violet City to PokeMart
	 	if (posX_tile == 15 && posY_tile == 23) {
		    currentX_loc += 6;
			currentY_loc += 87;
			posX_tile = 21;
			posY_tile = 110;
			changeBGM(pokemart);
	 	}
	 	//PokeCenter to Town
	 	if (posX_tile == 42 && posY_tile == 111) {
	 	    if (currentMap == "Cherrygrove City") {
		        currentX_loc += 43;
			    currentY_loc -= 11;
			    posX_tile = 85;
			    posY_tile = 100;
			    changeBGM(cherrygrovecity);
	 	    }
	 	    else if (currentMap == "Violet City") {
		        currentX_loc -= 5;
			    currentY_loc -= 79;
			    posX_tile = 37;
			    posY_tile = 32;
			    changeBGM(violetcity);
	 	    }
	 	    else {
	 	    	System.out.println("Haaaaaaaaaaaaaaaaaaaax");
	 	    }
	 	}
	 	//PokeMart to Town
	 	if (posX_tile == 21 && posY_tile == 111) {
	 		if (currentMap == "Cherrygrove City") {
		        currentX_loc += 58;
			    currentY_loc -= 11;
			    posX_tile = 79;
			    posY_tile = 100;
			    changeBGM(cherrygrovecity);
	 		}
	 		else if (currentMap == "Violet City") {
		        currentX_loc -= 6;
			    currentY_loc -= 87;
			    posX_tile = 15;
			    posY_tile = 24;
			    changeBGM(violetcity);
	 		}
	 		else {
	 	    	System.out.println("Haaaaaaaaaaaaaaaaaaaax");
	 	    }
	 	}
	 	//Violet City to House
	 	if (posX_tile == 27 && posY_tile == 35) {
		    currentX_loc -= 21;
			currentY_loc += 75;
			posX_tile = 6;
			posY_tile = 110;
	 	}
	 	//House to Violet City
	 	if (posX_tile == 6 && posY_tile == 111) {
		    currentX_loc += 21;
			currentY_loc -= 75;
			posX_tile = 27;
			posY_tile = 36;
	 	}
	 	//Violet City to Violet Gym
	 	if (posX_tile == 24 && posY_tile == 23) {
		    currentX_loc -= 17;
			currentY_loc += 70;
			posX_tile = 7;
			posY_tile = 93;
			changeBGM(gym);
	 	}
	 	//Violet Gym to Violet City
	 	if (posX_tile == 7 && posY_tile == 94) {
		    currentX_loc += 17;
			currentY_loc -= 70;
			posX_tile = 24;
			posY_tile = 24;
			changeBGM(violetcity);
	 	}
	 	//Violet City to Pokemon School
	 	if (posX_tile == 36 && posY_tile == 23) {
		    currentX_loc -= 12;
			currentY_loc += 70;
			posX_tile = 24;
			posY_tile = 93;
	 	}
	 	//Pokemon School to Violet City
	 	if (posX_tile == 24 && posY_tile == 94) {
		    currentX_loc += 12;
			currentY_loc -= 70;
			posX_tile = 36;
			posY_tile = 24;
			changeBGM(violetcity);
	 	}
	 	//Route 31 to Violet City
	 	if (posX_tile == 50 && posY_tile == 31) {
		    currentX_loc -= 6;
			currentY_loc -= 0;
			posX_tile = 44;
			posY_tile = 31;
			changeBGM(violetcity);
	 	}
	 	if (posX_tile == 50 && posY_tile == 30) {
		    currentX_loc -= 6;
			currentY_loc -= 0;
			posX_tile = 44;
			posY_tile = 30;
			changeBGM(violetcity);
	 	}
	 	//Violet City to Route 31
		if (posX_tile == 45 && posY_tile == 31) {
		    currentX_loc += 6;
			currentY_loc += 0;
			posX_tile = 51;
			posY_tile = 31;
			changeBGM(route30);
	 	}
	 	if (posX_tile == 45 && posY_tile == 30) {
		    currentX_loc += 6;
			currentY_loc += 0;
			posX_tile = 51;
			posY_tile = 30;
			changeBGM(route30);
	 	}
	 	//Player Room to Player House
		if (posX_tile == 11 && posY_tile == 64) {
		    currentX_loc += 18;
			currentY_loc += 0;
			posX_tile = 29;
			posY_tile = 64;
			gold.setSprite(playerDown);
			//changeBGM(route30);
	 	}
	 	//Player House to Player Room
	 	if (posX_tile == 29 && posY_tile == 63) {
		    currentX_loc -= 18;
			currentY_loc += 2;
			posX_tile = 11;
			posY_tile = 65;
			gold.setSprite(playerDown);
			//changeBGM(route30);
	 	}
	 }
	 
	 public void changeBGM(MidiPlayer newBGM) {
	 	currentBGM.stop();
	 	currentBGM = newBGM;
	 	currentBGM.start();
	 }
	 
	 public void showMessageBox(Graphics g) {
	 	g.setColor(Color.BLACK);
	 	//Trainer Pokemon Battles
	 	/*if (talkable == true) {
	 		for (int i = 0; i < currentMapNPC.length; i++) {
	 			if (currentMapNPC[i].getTalkable(gold) == true) {
	 				lastBGM = currentBGM;
			 		currentBGM.stop();
			 		currentBGM = battleBGM;
			 		currentBGM.start();
			 		//Trainer
			 		if (currentMapNPC[i] == bird_keeper1) {
			 			wildPokemon.create(198);
			 			trainerparty[0] = wildPokemon;
			 		}
			 		//Trainer
			 		wait(1);
			 		inBattle = true;
			 		disable_start = true;
			 		trainerencounter = new TrainerBattleScene(this,currentMapNPC[i],pokemonparty,trainerparty,items);
			 		try{Thread.sleep(500);}
		    		catch(InterruptedException e){}
	 			}
	 		}
     	}*/
     	//NPC Talking
	 	if (talkable == true) {
	 		for (int i = 0; i < currentMapNPC.length; i++) {
	 			if (currentMapNPC[i].getTalkable(gold) == true) {
	 				text = currentMapNPC[i].getText(gold);
	 			}
	 		}
     	}
     	if (talkable == true && movable_up == false) {
     		if (gold.getSprite() == playerUp) {
     			g.drawImage(messagebox,0,0,null);
	 		    g.drawString(text,25,255);
     		}
     	}
     	if (talkable == true && movable_down == false) {
     		if (gold.getSprite() == playerDown) {
     			g.drawImage(messagebox,0,0,null);
	 		    g.drawString(text,25,255);
     		}
     	}
     	if (talkable == true && movable_left == false) {
     		if (gold.getSprite() == playerLeft) {
     			g.drawImage(messagebox,0,0,null);
	 		    g.drawString(text,25,255);
     		}
     	}
     	if (talkable == true && movable_right == false) {
     		if (gold.getSprite() == playerRight) {
     			g.drawImage(messagebox,0,0,null);
	 		    g.drawString(text,25,255);
     		}
     	}
	 }
	 
	 //-----------------------------------------------------------------
	 // Battle Code
	 //-----------------------------------------------------------------
	 
	 public void checkBattle() {
	 	if (noBattle == false) {
	 		if (stepscount >= rndwildmodify) {
		 	 	lastBGM = currentBGM;
		 		currentBGM.stop();
		 		currentBGM = battleBGM;
		 		currentBGM.start();
		 		if (r == 1) {
		 		 	wildPokemon.create(198); //Creates a wild Murkrow
			 	}
			 	else if (r == 2) {
			 		wildPokemon.create(4); //Creates a wild Charmander
			 	}
			 	else if (r == 3) {
			 		wildPokemon.create(25); //Creates a wild Pikachu
			 	}
			 	else if (r == 4) {
			 		wildPokemon.create(220); //Creates a wild Swinub
			 	 }
			 	else {
			 		wildPokemon.create(158); //Creates a wild Totodile
			 	}
		 		wait(1);
		 		inBattle = true;
		 		disable_start = true;
		 		encounter = new BattleScene(this,pokemonparty,wildPokemon,items);
		 		stepscount = 0;
		 		try{Thread.sleep(500);}
	    		catch(InterruptedException e){}
		 	}
	 	}
	 }
	 
	 //-----------------------------------------------------------------
	 // Main
	 //-----------------------------------------------------------------
	 
	 public void startgame(boolean continued) {
	 	 loadTileSet();
	 	 loadMap(currentMapName);
	 	 if (continued == true) {
	 	 	loadGame();
	 	 	mainitems[0][3] = potion;
	 	 }
	 	 else {
	 	 	name = "Gold";
	 	 	gold.setName(name);
	 	 	gold.createTrainerID();
	 	 	gold.createSecretID();
	 	 	currentX_loc = 6-7;
			currentY_loc = 67-4;
			posX_tile = currentX_loc+7;
			posY_tile = currentY_loc+4;
	 	 	playerPokemon1.create(25);
	 	 	playerPokemon2.create(0);
	 	 	playerPokemon3.create(0);
	 	 	playerPokemon4.create(0);
	 	 	playerPokemon5.create(0);
	 	 	playerPokemon6.create(0);
	 	 	pokemonparty[0] = playerPokemon1;
	 	 	pokemonparty[1] = playerPokemon2;
	 	 	pokemonparty[2] = playerPokemon3;
	 	 	pokemonparty[3] = playerPokemon4;
	 	 	pokemonparty[4] = playerPokemon5;
	 	 	pokemonparty[5] = playerPokemon6;
	 	 	mainitems[0][3] = potion;
	 	 	money = 2000;
	 	 }
	 	 currentBGM.stop();
		 currentBGM = cherrygrovecity;
		 currentBGM.start();
		 player = playerDown;
		 atTitle = false;
		 atContinueScreen = false;
		 gamestarted = true;
		 movable = true;
		 timePlayed = java.lang.System.currentTimeMillis();
	 }
	 
	 public void saveGame() {
	 	BufferedWriter bufferedWriter = null;
        try {
        	File oldsave = new File("Data/profile.sav");
			oldsave.delete();
			File newsave = new File("Data/profile.sav");
			newsave.createNewFile();
            bufferedWriter = new BufferedWriter(new FileWriter("Data/profile.sav"));
            bufferedWriter.write(gold.getName());
            bufferedWriter.newLine();
            bufferedWriter.write("" + gold.getID());
            bufferedWriter.newLine();
            bufferedWriter.write("" + currentMap);
            bufferedWriter.newLine();
            bufferedWriter.write("" + posX_tile);
            bufferedWriter.newLine();
            bufferedWriter.write("" + posY_tile);
            bufferedWriter.newLine();
            bufferedWriter.write("" + money);
            bufferedWriter.newLine();
            bufferedWriter.write("" + playerPokemon1.getNumber());
            bufferedWriter.newLine();
            bufferedWriter.write("" + playerPokemon2.getNumber());
            bufferedWriter.newLine();
            bufferedWriter.write("" + playerPokemon3.getNumber());
            bufferedWriter.newLine();
            bufferedWriter.write("" + playerPokemon4.getNumber());
            bufferedWriter.newLine();
            bufferedWriter.write("" + playerPokemon5.getNumber());
            bufferedWriter.newLine();
            bufferedWriter.write("" + playerPokemon6.getNumber());
            bufferedWriter.newLine();
            bufferedWriter.write("'s Save File has been loaded.");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
         }
	 }
	 
	 public void loadGame() {
		File file = new File("Data/profile.sav");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			gold.setName(dis.readLine());
			gold.setID(Integer.parseInt(dis.readLine()));
			currentMap = dis.readLine();
			currentX_loc = Integer.parseInt(dis.readLine())-7;
			currentY_loc = Integer.parseInt(dis.readLine())-4;
			posX_tile = currentX_loc+7;
			posY_tile = currentY_loc+4;
			money = Integer.parseInt(dis.readLine());
			playerPokemon1.create(Integer.parseInt(dis.readLine()));
			playerPokemon2.create(Integer.parseInt(dis.readLine()));
			playerPokemon3.create(Integer.parseInt(dis.readLine()));
			playerPokemon4.create(Integer.parseInt(dis.readLine()));
			playerPokemon5.create(Integer.parseInt(dis.readLine()));
			playerPokemon6.create(Integer.parseInt(dis.readLine()));
			pokemonparty[0] = playerPokemon1;
			pokemonparty[1] = playerPokemon2;
			pokemonparty[2] = playerPokemon3;
			pokemonparty[3] = playerPokemon4;
			pokemonparty[4] = playerPokemon5;
			pokemonparty[5] = playerPokemon6;
			System.out.println(gold.getName() + dis.readLine());
			fis.close();
			bis.close();
			dis.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 public void loadTileSet() {
	 	File file = new File("Data/Tiles.tileset");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			for (int i = 0; i < tileset.length; i++) {
				tileset[i] = tk.createImage(getClass().getResource("Graphics/" + dis.readLine()));
			}
			fis.close();
			bis.close();
			dis.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 public void loadMap(String map) {
		try {
			float r = 1;
			float g = 1;
			float b = 1;
			float h = 0;
			float s = 1;
			boolean hasColourEffect = false;
			BufferedReader reader = new BufferedReader(new FileReader(map));
			String line = reader.readLine();
			StringTokenizer tokens = new StringTokenizer(line);
			int width = Integer.parseInt(tokens.nextToken());
			int height = Integer.parseInt(tokens.nextToken());
			mapTilesX = width;
			mapTilesY = height;
			String tileset = tokens.nextToken();
			line = reader.readLine();
			tokens = new StringTokenizer(line);
			if(tokens.nextToken().equalsIgnoreCase("colorization")) {
				hasColourEffect = true;
				r = Float.parseFloat(tokens.nextToken());
				g = Float.parseFloat(tokens.nextToken());
				b = Float.parseFloat(tokens.nextToken());
				h = Float.parseFloat(tokens.nextToken());
				s = Float.parseFloat(tokens.nextToken());
			}
			while(!line.equals(".")) {
				line = reader.readLine();
			}
			for(int layers = 0; layers < 2; layers++) {
				line = reader.readLine();
				tokens = new StringTokenizer(line);
				for(int y = 0; y < (width*height); y++) {
						String code = tokens.nextToken();
						if (layers == 0) {
							currentMap0[y] = Integer.parseInt(code);
						}
						else if (layers == 1) {
							currentMap1[y] = Integer.parseInt(code);
						}
					}
				}
			reader.close();
	 	}
	 	catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	 }
	 
	 public static void wait(int n){
         long t0, t1;
         t0 =  System.currentTimeMillis();
         do {
             t1 = System.currentTimeMillis();
         }
         while ((t1 - t0) < (n * 1000));
     }
     
     public static void main(String [] Args) {
     	 //Create the window
     	 jf = new JFrame("Pokemon: Metallic Silver");
     	 //Create an instance of Pokemon and insert into the window
     	 Pokemon pokemon = new Pokemon();
     	 jf.add(pokemon);
	  	 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	 jf.setResizable(false);
	  	 jf.pack();
	  	 //Center the Game on the Screen
	  	 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	  	 int w = jf.getSize().width;
   		 int h = jf.getSize().height;
   		 int x = (dim.width-w)/2;
    	 int y = (dim.height-h)/2;
	  	 jf.setLocation(x,y);
	  	 //Set focus to the Panel
	  	 jf.setVisible(true);
	  	 pokemon.requestFocus(true);
    }
}