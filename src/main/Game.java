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
	
	static int activity = 0;
	
	//ticking variables
	private long goal = 0;
	private int loopcount = 0;
	static double tpm = 0;//ticks per millisecond
	
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
	
	public static void ccreate(Camera c){
		if(cnumm<cmax){
			cameras[cnumm] = c;
			cnumm++;
		}
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
		//System.out.println("destroyed"+enumm);
	}
	
	public Game(){
		ImageLoader.loadimages();
		
		window = new Window();
		world = new World((int)(Math.random()*1000+100));
		
		globalx = 0;
		globaly = 0;
		
		ccreate(new Camera(0,0,2));
		ccreate(new Camera(0,0,1,0));
		
		create(0, 1, new Survivor(0));
		
		activity = 1;
		
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
	
	private void tick(){	
		globalx = (int)Math.floor(cameras[currentcamera].cx);
		gexx = cameras[currentcamera].cx-globalx;
		globaly = (int)Math.floor(cameras[currentcamera].cy);
		gexy = cameras[currentcamera].cy-globaly;
		
		if(globaly<world.offy){
			world.shift(0,1);
		}
		if(globalx>world.offx){
			world.shift(1,1);
		}
		if(globaly>world.offy){
			world.shift(2,1);
		}
		if(globalx<world.offx){
			world.shift(3,1);
		}
		
		if(window.pressed.keys[Binds.ENDBIND]){
			System.exit(0);
		}
		
		for(int i=0;i<cnumm;i++){
			cameras[i].tick();
		}
		for(int i=0;i<enumm;i++){
			entities[i].tick();
		}
		Block.tick();
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
