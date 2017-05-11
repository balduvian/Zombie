package zombie;

import main.*;

public class BasicZombie extends Zombie{
	
	public static final int[] BZDEF = {
			5,
			5,
			5,
			5,
			5
	};
	
	public BasicZombie(int level){
		super(level);
		img = new ImgSheet(new int[][][]{{{ImageLoader.ZOMBIEE0}, {ImageLoader.ZOMBIEE1}}},60,10);
		zombid = Zombie.BASICZOMBIEID;
		logname = "Basic Zombie";
		init(level);
	}
	
}
