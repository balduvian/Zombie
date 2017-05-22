package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Game {
	
	//setting up list of entities of cameras and entities and related values
	public final static int cmax = 20;
	public static int cnumm = 0;
	public static Camera[] cameras = new Camera[cmax];
	
	public final static int emax = 256;
	public static int enumm = 0;
	public static Entity[] entities = new Entity[emax];
	
	public static int currentcamera = 0;
	
	public static final int PARTYMAX = 6;
	public static Survivor[] party = new Survivor[PARTYMAX];
	public static int inparty = 0;
	
	//setting up a list of images
	public final static int Imax = 256;
	public static BufferedImage[] images = new BufferedImage[Imax];
	
	//display size
	public static int square = 64;
	
	//window
	public static Window window;
	
	//world
	public static World world;
	
	//IT'S DA TURN
	public static final int PLAYERTURN = 0;
	public static final int ENEMYTURN = 1;
	public static int turn;
	public static int partyselect = 0;
	
	public static boolean arrowsdeployed = false;
	public static int moved;
	public static int maxmoved;
	
	//global display positioning
	public static int globaly;
	public static int globalx;
	public static double gexy;
	public static double gexx;
	public static double cay;
	public static double cax;
	
	static int activity = 0;
	
	//ticking variables
	private long goal = 0;
	@SuppressWarnings("unused")
	private int loopcount = 0;
	static double tpm = 0;//ticks per millisecond
	
	//GUI
	static GUI gui;
	
	//random
	
	public static Rectangle getmouserect(){
		return new Rectangle(window.moussed2.mx,window.moussed2.my,1,1);
	}
	
	public static Survivor getpartymember(int er){
		return party[er];
	}
	public static void addtoparty(int eta){
		if(inparty<PARTYMAX){
			party[inparty] = (Survivor)entities[eta];
			inparty++;
		}
	}
	public static void removefromparty(int pindex){
		party[pindex] = null;
		for(int n=pindex;n<inparty-1;n++){
			Survivor temp = party[n+1];
			party[n+1] = party[n];
			party[n] = temp;
			//party[n].index--;
		}
		inparty--;
	}
	
	public static Rectangle geterect(int eid){
		try{
			Entity eng = entities[eid];
			int[] bin = window.ldr(eng.x, eng.xx, eng.y, eng.yy, eng.w, eng.h);
			return new Rectangle(bin[0], bin[1], bin[2], bin[3]);
		}catch(Exception ex){
			return new Rectangle(1,1,1,1);
		}
	}
	
	public static double bitrand(int s, int o){
		return(Math.abs(((s+s*o*o)^(o*s+o-s)^((s-1)*o+(o-1)*s))*Math.PI)%1);
	}
	
	//game methods
	public static void create(int x, int y, Entity e){
		if(enumm<emax){
			entities[enumm] = e;
			entities[enumm].spawnpos(x, y);
			enumm++;
		}
	}
	
	//open the menu
	public static void openmenu(){
		
	}
	
	public static void ccreate(Camera c){
		if(cnumm<cmax){
			cameras[cnumm] = c;
			cnumm++;
		}
	}
	
	public static void cdelete(int i){
		if(currentcamera==cameras[i].index && currentcamera==cnumm-1){
			currentcamera--;
		}
		cameras[i] = null;
		for(int n=i;n<cnumm-1;n++){
			Camera temp = cameras[n+1];
			cameras[n+1] = cameras[n];
			cameras[n] = temp;
			cameras[n].index--;
		}
		cnumm--;
	}
	
	public static void delete(int i){
		entities[i] = null;
		for(int n=i;n<enumm-1;n++){
			Entity temp = entities[n+1];
			entities[n+1] = entities[n];
			entities[n] = temp;
			entities[n].index--;
		}
		enumm--;
	}
	
	//destroys everything
	private void cleanup(){
		while(true){
			if(enumm==0){
				break;
			}
			delete(0);
		}
		while(true){
			if(cnumm==0){
				break;
			}
			cdelete(0);
		}
	}
	
	public void changecamera(int c){
		currentcamera = c;
	}
	
	public void spawnworld(){
		cleanup();
		world = new World((int)(Math.random()*1000+100));
		globalx = 0;
		globaly = 0;
		ccreate(new Camera(0,0,Camera.CONTROLMODE));
		world.spawnmap.spawncharacter(0, 0, Character.SURVIVORCONSTANT, 0);
		addtoparty(enumm-1);
		gui = new GUI();
		activity = 1;
		broadcast(STARTPLAYERTURN);
	}
	
	public Game(){
		ImageLoader.loadimages();
		window = new Window();

		spawnworld();
		//game loop
		tick();
		goal = System.currentTimeMillis();
		loopcount=0;
		while(true){
			int ovr = (int)(System.currentTimeMillis()-goal);
			if(ovr>=16){
				goal = System.currentTimeMillis();
				loopcount=0;
				tick();
			}
			loopcount++;
		}
	}
	
	public void ggoto(int x, int y, double xx, double yy){
		globalx = x;
		globaly = y;
		gexx = xx;
		gexy = yy;
	}
	public void cgoto(int cid){
		Camera en = cameras[cid];
		globalx = (int)Math.floor(en.cx);
		gexx = en.cx-globalx;
		globaly = (int)Math.floor(en.cy);
		gexy = en.cy-globaly;
	}
	
	private void tick(){
		
		cay = cameras[currentcamera].cy;
		cax = cameras[currentcamera].cx;
		
		if(window.pressed.keys[Binds.ENDBIND]){
			System.exit(0);
		}
		
		world.tick();
		for(int i=0;i<cnumm;i++){
			cameras[i].tick();
		}
		for(int i=0;i<enumm;i++){
			entities[i].tick();
		}
		Block.tick();
		gui.tick();
	}
	
	public static final int NOACTION = -1;
	public static final int CREATEMOVEARROWS = 0;
	public static final int MOVEUP = 1;
	public static final int MOVERIGHT = 2;
	public static final int MOVEDOWN = 3;
	public static final int MOVELEFT = 4;
	public static final int DELETEMOVEARROWS = 5;
	public static final int GUIGOMAIN = 6;
	public static final int GUIGOMOVE = 7;
	public static final int GUIGOATTACK = 8;
	public static final int GUIGOACTION = 9;
	public static final int ADVANCEPARTYTURN = 10;
	public static final int STARTPLAYERTURN = 11;
	public static final int STARTENEMYTURN = 12;
	public static final int GUIOPENMENU = 13;
	public static final int CAMERATOPARTY = 14;
	public static final int CLEARACTIONS = 15;
	public static final int BEGINAI = 16;
	
	//all other nontimed events happen here
	
	public static void broadcast(int signal){
		if(signal==NOACTION){
			
		}else if(signal==CREATEMOVEARROWS){
			broadcast(DELETEMOVEARROWS);
			if(moved==maxmoved){
				gui.buttons[GUI.MAIN][GUI.MAINMOVE].inactive = true;
				//gui.setmode(GUI.MAIN);
			}else{
				Entity pm = getpartymember(partyselect);
				int alx = pm.x;
				int aly = pm.y;
				
				ArrowEntity ae = new ArrowEntity();
				ae.setarrowtype(ArrowEntity.UPARROW);
				create(alx,aly-1,ae);
				
				ae = new ArrowEntity();
				ae.setarrowtype(ArrowEntity.RIGHTARROW);
				create(alx+1,aly,ae);
	
				ae = new ArrowEntity();
				ae.setarrowtype(ArrowEntity.DOWNARROW);
				create(alx,aly+1,ae);
				
				ae = new ArrowEntity();
				ae.setarrowtype(ArrowEntity.LEFTARROW);
				create(alx-1,aly,ae);
				
				arrowsdeployed = true;
			}
		}else if(signal==DELETEMOVEARROWS){
			if(arrowsdeployed){
				int i=0;
				while(i<enumm){
					Entity enn = entities[i];
					if(enn.id==Entity.IDARROW){
						enn.destroy();
					}else{
						i++;
					}
				}
				arrowsdeployed = false;
			}
		}else if(signal==MOVEUP){
			globaly--;
			getpartymember(partyselect).y--;
			moved++;
			broadcast(CREATEMOVEARROWS);
		}else if(signal==MOVERIGHT){
			globalx++;
			getpartymember(partyselect).x++;
			moved++;
			broadcast(CREATEMOVEARROWS);
		}else if(signal==MOVEDOWN){
			globaly++;
			getpartymember(partyselect).y++;
			moved++;
			broadcast(CREATEMOVEARROWS);
		}else if(signal==MOVELEFT){
			globalx--;
			getpartymember(partyselect).x--;
			moved++;
			broadcast(CREATEMOVEARROWS);
		}else if(signal==GUIGOMAIN){
			gui.setmode(GUI.MAIN);
			Game.broadcast(DELETEMOVEARROWS);
		}else if(signal==GUIGOMOVE){
			gui.setmode(GUI.MOVEMENT);
			broadcast(CREATEMOVEARROWS);
		}else if(signal==GUIGOATTACK){
			gui.setmode(GUI.ATTACKING);
		}else if(signal==GUIGOACTION){
			gui.setmode(GUI.ACTION);
		}else if(signal==ADVANCEPARTYTURN){
			partyselect++;
			if(partyselect==inparty){
				Game.broadcast(ENEMYTURN);
			}else{
				Game.broadcast(CLEARACTIONS);
				Game.broadcast(CAMERATOPARTY);
			}
		}else if(signal==CLEARACTIONS){
			maxmoved = getpartymember(partyselect).stats.stats[Stats.SPEED];
			moved = 0;
			gui.enableall();
		}else if(signal==STARTPLAYERTURN){
			turn=PLAYERTURN;
			gui.setmode(GUI.MAIN);
			partyselect = 0;
			Game.broadcast(CLEARACTIONS);
			Game.broadcast(CAMERATOPARTY);
		}else if(signal==STARTENEMYTURN){
			turn=ENEMYTURN;
			gui.setmode(GUI.ENEMYTURN);
			Game.broadcast(BEGINAI);
		}else if(signal==BEGINAI){
			
		}else if(signal==CAMERATOPARTY){
			
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
