package zombie;

public class Game {
	
	static Window window;
	static World world;
	static double globaly;
	static double globalx;
	
	static int upbind = 87;
	static int leftbind = 65;
	static int downbind = 83;
	static int rightbind = 68;
	
	int pps = 10;
	
	private long goal = 0;
	private long seccount = 0;
	private int loopcount = 0;
	private long dong = System.currentTimeMillis();
	private double tps = 0;//ticks per second
	
	public Game(){
		world = new World((int)(Math.random()*1000+100));
		window = new Window();
		while(true){
			
			if(seccount==0){
				loopcount = 0;
				goal = System.currentTimeMillis();
			}else{
				loopcount++;
				seccount = System.currentTimeMillis()-goal;
				if(seccount>=1000){
					seccount = 0;
					tps = ((double)loopcount/seccount);
				}
			}
			
			System.out.println("lop: "+loopcount);
			System.out.println("sec: "+seccount);
			System.out.println("tps: "+tps);
			
			if(window.pressed.keys[upbind]){
				globaly -= pps*tps;
			}
			if(window.pressed.keys[leftbind]){
				globalx -= pps*tps;
			}
			if(window.pressed.keys[downbind]){
				globaly += pps*tps;
			}
			if(window.pressed.keys[rightbind]){
				globalx += pps*tps;
			}
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
