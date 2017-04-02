package zombie;

public class Game {
	
	final static int emax = 256;
	static Entity[] entities = new Entity[emax];
	static int enumm = 0;
	static int square = 32;
	
	static Window window;
	static World world;
	static int globaly;
	static int globalx;
	static double gexy;
	static double gexx;
	
	static int upbind = 87;
	static int leftbind = 65;
	static int downbind = 83;
	static int rightbind = 68;
	
	double pps = 0.1;
	
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
			
			if(globaly<0){
				world.shift(0);
			}
			
			if(globaly>(world.rz)*world.csize){
				world.shift(2);
			}
			
			if(window.pressed.keys[upbind]){
				gexy -= (pps/tpm);
			}
			if(window.pressed.keys[leftbind]){
				gexx -= (pps/tpm);
			}
			if(window.pressed.keys[downbind]){
				gexy += (pps/tpm);
				System.out.println("yag");
			}
			if(window.pressed.keys[rightbind]){
				gexx += (pps/tpm);
				System.out.println("yag");
			}
			
			if(gexy>=1){
				gexy=0;
				globaly++;
			}else if(gexy<=-1){
				gexy=0;
				globaly--;
			}
			
			if(gexx>=1){
				gexx=0;
				globalx++;
			}else if(gexx<=-1){
				gexx=0;
				globalx--;
			}
			
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
