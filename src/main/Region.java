package main;

import region.ForestRegion;
import region.NullRegion;
import region.VillageRegion;

public class Region {
	
	protected Block groundblock;
	
	protected int enmax;
	protected int[] enemychances;
	
	protected double spawnchance;
	
	protected int lvmax;
	protected int[] levelchances;
	
	protected double schance;
	protected int srmax;
	protected Structure[][][] structures;
	
	protected int lowbound;
	protected int highbound;
	
	protected String name;
	
	protected int[] color;
	
	protected int regionid;
	
	public static final int NULLID = 0;
	public static final int VILLAGEID = 1;
	public static final int FORESTID = 2;
	
	public Region(){
		initroutine();
		setup();
	}
	
	protected void initroutine(){
		
	}
	
	public static Region region(int r){
		if(r==Region.VILLAGEID){
			return new VillageRegion();
		}else if(r==Region.FORESTID){
			return new ForestRegion();
		}else{
			return new NullRegion();
		}
	}
	
	private void setup(){
		enmax = enemychances.length;
		lvmax = levelchances.length;
	}
}
