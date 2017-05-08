package main;

public class Weapon extends Item{
	
	int weaptype;
	public static final int numeff = 16;
	String[] effnames = {
			"Sharp",
			"Super Sharp"
	};
	int[][] effbuild = {
			{0,0,1,1}
	};
	
	public Weapon(){
		
	}
}
