package zombie;

import java.awt.Color;

public class SpawnMap {
	
	public SpawnMap(){
		
	}
	
	int numregions = 6;
	String[] rnames = {
		"deserted village","dead forest","sleeping city","dry desert","decaying swamp","hell"	
	};
	int[][] rcolors = {
		{55,117,56},{17,81,18},{79,84,83},{28,19,45},{244,235,168},{198,43,43}
	};
	int chmax = 16;//16 in the list of probabilities
	int[][] chances = {
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,2,2},
			{0,0,0,0,0,0,0,0,1,1,1,1,2,2,3,3},
			{0,0,0,1,1,1,1,1,2,2,2,2,2,2,3,4},
			{0,1,2,3,4,9,9,9,9,9,7,7,7,7,7,8},
			{0,1,1,2,2,2,2,2,2,2,3,3,3,3,4,5},
			{0,1,1,2,2,2,2,2,2,2,3,3,3,3,4,5}
	};
	int spmax = 16;
	int[][] spawnchances = {
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,2,2},
			{0,0,0,0,0,0,0,0,0,1,1,1,2,2,2,3},
			{0,0,0,0,0,0,0,0,1,1,1,2,2,2,2,3},
			{0,0,0,0,0,0,0,1,1,1,1,2,2,2,2,3},
			{0,0,0,0,0,0,0,1,1,1,1,2,2,2,2,4},
			{9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9}
	};
	public void regionspawnroutine(int cx, int cy, int region){
		if(Game.world!=null){
			int tryflag = (int)(Math.random()*spmax);
			int tries = spawnchances[region][tryflag];
			int[][] sofar = new int[tries][2];//no dupe spawns
			int favance = 0;
			for(int i=0;i<tries;i++){
				boolean pass = true;
				int spawnflag = (int)(Math.random()*chmax);
				int gen = chances[region][spawnflag];
				int andx = ((int)(Math.random()*Game.world.csize));
				int andy = ((int)(Math.random()*Game.world.csize));
				for(int u=0;u<favance;u++){
					if(sofar[u][0]==andx && sofar[u][1]==andy){
						pass = false;
						break;
					}
				}
				if(pass){
					espw((cx+Game.world.offx-Game.world.rb)*Game.world.csize, (cy+Game.world.offy-Game.world.rb)*Game.world.csize, gen);
				}
			}
		}
	}
	
	public void spawnspecificenemy(int x, int y, int enemy){
		espw(x, y, enemy);
	}
	
	private void espw(int x, int y, int we){
		if(we>-1){
			Game.create(x, y, new DroneEntity());
		}
	}
}
