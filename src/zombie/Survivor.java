package zombie;

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
	public int skin;
	public int race;
	
	public Survivor(int region){
		friendly = true;
		id = Entity.IDSURVIVOR;
		h = 1;
		w = 1;
		skin = (int)(Math.random()*NUMSKINS);
		race = (int)(Math.random()*NUMRACES);
		stats = new Stats(region);
		despawn = false;
	}
}
