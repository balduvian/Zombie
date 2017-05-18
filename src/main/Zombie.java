package main;

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
	
	public static final int[] BASICZOMBIESTATS = {5,5,5,5,5};
	
	protected int zombid;
	
	protected  void charinit(){
		zombinit();
		setup();
	}
	
	//to override again!
	protected void zombinit(){}
	
	private void setup(){
		id = Entity.IDZOMBIE;
		friendly = false;
		despawn = true;
	}
}
