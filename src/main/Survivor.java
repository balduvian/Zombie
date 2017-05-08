package main;

public class Survivor extends Character{
	
	public static final int NUMRACES = 4;
	public static final int[] RACEIDS = {
		ImageLoader.RACE0,
		ImageLoader.RACE1,
		ImageLoader.RACE2,
		ImageLoader.RACE3
	};
	public static final int NUMSKINS = 6;
	public static final int[] SKINIDS = {
		ImageLoader.SKIN0,
		ImageLoader.SKIN1,
		ImageLoader.SKIN2,
		ImageLoader.SKIN3,
		ImageLoader.SKIN4,
		ImageLoader.SKIN5
	};
	public static final int NUMNAMES = 30;
	public static final String[] NAMES = {
		"Jackson","Vincent","Emmett","Henry","Sam","Soren","Ben","Noah","Alexander","Matthew","Luke","Joseph","David","Ryan","Corbin","Muhammad","Michael","Daniel","John","Dylan","Robert","Cameron","Ethan","Brian","Charles","Steven","Adam","Joshua","Nicholas","Austin","Barasu"
	};
	public int skin;
	public int race;
	public int nameindex;
	
	public Survivor(int level){
		
		pre = false;
		defbase = 25;
		
		nameindex = (int)(Math.random()*NUMNAMES);
		logname = NAMES[nameindex];
		
		friendly = true;
		id = Entity.IDSURVIVOR;
		skin = (int)(Math.random()*NUMSKINS);
		race = (int)(Math.random()*NUMRACES);
		imgs = new ImgSheet(new int[][]{{RACEIDS[race],SKINIDS[skin],ImageLoader.PLAYEROUTLINE}});
		stats = new Stats(level,25);
		despawn = false;
		init(level);
	}
}
