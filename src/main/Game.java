package main;

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
	public static int turn;
	
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
	private int loopcount = 0;
	static double tpm = 0;//ticks per millisecond
	
	//GUI
	static GUI gui;
	
	//random
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
		create(0, 1, new Survivor(0));
		activity = 1;
		gui = new GUI();
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
	
	public void gamegoto(int x, int y, double xx, double yy){
		globalx = x;
		globaly = y;
		gexx = xx;
		gexy = yy;
	}
	public void egoto(int eid){
		Entity en = entities[eid];
		globalx = en.x;
		globaly = en.y;
		gexx = en.exx;
		gexy = en.exy;
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
	
	public static void main(String[] args) {
		new Game();
	}

}
