package main;

import region.NullRegion;
import region.VillageRegion;

public class Region {
	
	protected int tlmax;
	protected int[] tiles;
	
	protected int prmax;
	protected int[] props;
	
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
		}else{
			return new NullRegion();
		}
	}
	
	protected void setup(){
		tlmax = tiles.length;
		prmax = props.length;
		enmax = enemychances.length;
		lvmax = levelchances.length;
		srmax = structures.length;
	}
}
