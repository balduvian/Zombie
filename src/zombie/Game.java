package zombie;

public class Game {
	
	final static int emax = 256;
	static Entity[] entities = new Entity[emax];
	static int enumm = 0;
	static int square = 32;
	
	static Window window;
	static World world;
	static double globaly;
	static double globalx;
	
	static int upbind = 87;
	static int leftbind = 65;
	static int downbind = 83;
	static int rightbind = 68;
	
	int pps = 1000;
	
	private long goal = 0;
	private long seccount = 0;
	private int loopcount = 0;
	private long dong = System.currentTimeMillis();
	private double tpm = 0;//ticks per millisecond
	
	public static void create(Entity e){
		entities[enumm] = e;
		enumm++;
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
		
		create(new Entity(0,0,28,28,100));
		while(true){
			
			if(seccount==0){
				loopcount = 0;
				seccount++;
				goal = System.currentTimeMillis();
			}else{
				loopcount++;
				seccount = System.currentTimeMillis()-goal;
				if(seccount>=1000){
					tpm = (((double)loopcount/seccount*1000));
					System.out.println("tps: "+tpm);
					seccount = 0;
				}
			}
			
			int yn = ((int)globaly/square);
			int target = ((world.rb*-1)-world.rofy)-world.csize;
			System.out.println("yn: "+yn);
			System.out.println("target: "+target);
			
			if(yn<target){
				System.out.println("OH YEAH");
				world.shift(0);
				//int y =1/0;
			}
			/*if((globalx/square)<(world.worw*world.csize)-world.rz-world.rofx){
				System.out.println("AAECHING");
				world.shift(3);
			}*/
			
			if(window.pressed.keys[upbind]){
				globaly -= (pps/tpm);
			}
			if(window.pressed.keys[leftbind]){
				globalx -= (pps/tpm);
			}
			if(window.pressed.keys[downbind]){
				globaly += (pps/tpm);
			}
			if(window.pressed.keys[rightbind]){
				globalx += (pps/tpm);
			}
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
