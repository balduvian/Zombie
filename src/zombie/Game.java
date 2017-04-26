package zombie;

public class Game {
	
	//setting up list of entities of cameras and entities and related values
	public final static int emax = 256;
	public final static int cmax = 20;
	public static Camera[] cameras = new Camera[cmax];
	public static Entity[] entities = new Entity[emax];
	public static int enumm = 0;
	public static int cnumm = 0;
	public static int currentcamera = 0;
	
	//display size
	public static int square = 48;
	
	//window
	public static Window window;
	
	//world
	public static World world;
	
	//global display positioning
	public static int globaly;
	public static int globalx;
	public static double gexy;
	public static double gexx;
	
	//desbugging nocamera movespeed
	private double spd = 4;//movespeed
	
	//ticking variables
	private long goal = 0;
	private long seccount = 0;
	private int loopcount = 0;
	private boolean ready = false;
	private int tacklen = 100;
	private int[] tickstack = new int[tacklen];
	private int tackadd = 0;
	private int tackfill = 0;
	private int pretpm = 0;
	static double tpm = 0;//ticks per millisecond
	
	//game methods
	public static void create(Entity e){
		entities[enumm] = e;
		enumm++;
	}
	
	public static void ccreate(Camera c){
		cameras[cnumm] = c;
		cnumm++;
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
	
	public Game(){
		world = new World((int)(Math.random()*1000+100));
		window = new Window();
		
		globalx = 0;//(world.worw*world.csize*square)/2*-1;
		globaly = 0;//(world.worh*world.csize*square)/2*-1;
		
		create(new DroneEntity(0,0));
		create(new DroneEntity(0,0));
		create(new DroneEntity(0,0));
		create(new DroneEntity(0,0));
		create(new DroneEntity(0,0));
		ccreate(new Camera(0,0,2));
		ccreate(new Camera(0,0,1,0));
		
		gameloop: while(true){//LOOOOOOOOOOOOOOOP
			
			if(seccount==0){
				loopcount = 0;
				goal = System.currentTimeMillis();
				seccount = 1;
			}else{
				loopcount++;
				int ovr = (int)(System.currentTimeMillis()-goal);
				if(ovr>0){
					seccount = 0;
					if(tackfill<tacklen){
						tackfill++;
					}else{
						pretpm -= tickstack[tackadd];
					}
					tickstack[tackadd] = loopcount/ovr;
					pretpm += tickstack[tackadd];
					tpm = (pretpm/tackfill)*1000;
					//System.out.println(tpm);
					tackadd++;
					tackadd%=tacklen;
					loopcount = 0;
				}
			}
			
			if(ready){
				
				globalx = (int)Math.floor(cameras[currentcamera].cx);
				gexx = cameras[currentcamera].cx-globalx;
				globaly = (int)Math.floor(cameras[currentcamera].cy);
				gexy = cameras[currentcamera].cy-globaly;
				if(globaly<0-(world.offy*world.csize)){
					world.shift(0);
					System.out.println("up");
					int sdf = 7/0;
				}
				
				/*if(globaly>world.offy-((world.rz)*world.csize)){
					world.shift(2);
					System.out.println("down");
				}*/
				//TICK
				for(int i=0;i<cnumm;i++){
					cameras[i].tick();
				}
				for(int i=0;i<enumm;i++){
					entities[i].tick();
				}
			}else if(tpm>0){
				ready=true;
			}
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
