package zombie;

import main.Character;
import main.Entity;
import main.*;

public class Zombie extends Character{
	
	public static final int BASICZOMBIEID = 0;
	public static final int HALFZOMBIEID = 1;
	public static final int MUSCLEZOMBIEID = 2;
	public static final int CACTUSZOMBIEID = 3;
	public static final int COWBOYZOMBIEID = 4;
	public static final int GAPINGZOMBIEID = 5;
	public static final int FLAMESKELETONID = 6;
	public static final int OOZEID = 7;
	public static final int SKELETONID = 8;
	public static final int GIANTZOMBIEID = 9;
	public static final int DEVILID = 10;
	public static final int IMPID = 11;
	public static final int GHOSTID = 12;
	public static final int METALZOMBIEID = 13;
	public static final int SANDZOMBIEID = 14;
	public static final int ELEMENTALID = 15;
	
	int zombid;
	
	public Zombie(int level){
		id = Entity.IDZOMBIE;
		friendly = false;
		despawn = true;
	}
}
