package zombie;

import java.awt.Color;

public class SpawnMap {
	
	public SpawnMap(){
		
	}
	
	int numregions = 5;
	String[] rnames = {
		"deserted village","dead forest","sleeping city","decaying swamp","hell"	
	};
	int[][] rcolors = {
		{55,117,56},{17,81,18},{79,84,83},{28,19,45},{198,43,43}
	};
	int chmax = 16;//16 in the list of probabilities
	int[][] chances = {
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,2,2},
			{0,0,0,0,0,0,0,0,1,1,1,1,2,2,3,3},
			{0,0,0,1,1,1,1,1,2,2,2,2,2,2,3,4},
			{0,1,1,2,2,2,2,2,2,2,3,3,3,3,4,5}
	};
	
	public void spawnregionenemy(int x, int y, int region){
		int spawnflag = (int)(Math.random()*chmax);
		int gen = chances[region][spawnflag];
		espw(x, y, gen);
	}
	
	public void spawnspecificenemy(int x, int y, int enemy){
		espw(x, y, enemy);
	}
	
	private void espw(int x, int y, int we){
		if(we==0){
			Game.create(x, y, new DroneEntity());
		}
	}
}
