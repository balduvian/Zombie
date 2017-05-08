package main;

public class Region {
	
	protected int tlmax;
	protected int[] tiles;
	
	protected int prmax;
	protected int[] props;
	
	protected int enmax;
	protected int[] enemychances;
	
	protected int spmax;
	protected int[] spawnchances;
	
	protected int lvmax;
	protected int[] levelchances;
	
	protected int srmax;
	protected int[][][] structures;
	
	protected int lowbound;
	protected int highbound;
	
	protected String name;
	
	protected int[] color;
	
	protected int regionid;
	
	public static final int VILLAGEID = 0;
	public static final int FORESTID = 0;
	
	protected void setup(){
		tlmax = tiles.length;
		prmax = props.length;
		enmax = enemychances.length;
		spmax = spawnchances.length;
		lvmax = levelchances.length;
		srmax = structures.length;
	}
}
