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
	
	public Game(){
		world = new World((int)(Math.random()*1000+100));
		window = new Window();
		while(true){
			lag();
			if(window.pressed.keys[upbind]){
				globaly -= 0.01;
			}
			if(window.pressed.keys[leftbind]){
				globalx -= 0.01;
			}
			if(window.pressed.keys[downbind]){
				globaly += 0.01;
			}
			if(window.pressed.keys[rightbind]){
				globalx += 0.01;
			}		
		}
	}
	
	public void lag(){
		double f = 0;
		for(int i=0;i<89;i++){
			f+= Math.random();
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
