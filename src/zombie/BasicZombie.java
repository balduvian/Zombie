package zombie;

public class BasicZombie extends Zombie{
	
	public BasicZombie(){
		imgframes = new int[]{ImageLoader.ZOMBIEENEMY0, ImageLoader.ZOMBIEENEMY1};
		anim = true;
		zombid = Zombie.BASICZOMBIEID;
		frametime = 100;
		sinceframe = frametime;
	}
	
}
