package zombie;

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
		imgs = new ImgSheet(new int[][]{{ImageLoader.ZOMBIEE0}, {ImageLoader.ZOMBIEE1}},1,0.3);
		zombid = Zombie.BASICZOMBIEID;
		logname = "Basic Zombie";
		init(level);
	}
	
}
