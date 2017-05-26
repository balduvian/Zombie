package zombie;

import main.*;

public class BasicZombie extends Zombie{
	
	protected void zombinit(){
		pre = true;
		prebase = Zombie.BASICZOMBIESTATS; 
		img = new ImgSheet(new int[][][]{{{ImageLoader.ZOMBIEE0}, {ImageLoader.ZOMBIEE1}}},60,10);
		zombid = Zombie.BASICZOMBIEID;
		name = "Basic Zombie";
	}
	
}
