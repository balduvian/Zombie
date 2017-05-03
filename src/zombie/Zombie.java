package zombie;

public class Zombie extends Character{
	
	public static final int BASICZOMBIEID = 0;
	public static final int HALFZOMBIEID = 1;
	public static final int SKELETONZOMBIEID = 2;
	public static final int MUSCLEZOMBIEID = 3;
	public static final int CACTUSZOMBIEID = 4;
	
	int zombid;
	
	public Zombie(int level){
		id = Entity.IDZOMBIE;
		friendly = false;
		despawn = true;
	}
}
