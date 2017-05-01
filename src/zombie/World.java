package zombie;

public class World {
	
	int seed;
	int rb = 2; //render buffer
	int rz;
	int csize = 8;
	int[][][][] rendered;//the rendered chunks
	int[][] chunkregion;//the region for each chunk
	int offy = 0;//offset of chunks
	int offx = 0;
	SpawnMap spawnmap;
	
	public World(int s){
		seed = s;
		rz = rb*2+1;
		spawnmap = new SpawnMap();
		rendered = new int[rz][rz][csize][csize];
		chunkregion = new int[rz][rz];
		for(int y=0;y<rz;y++){
			for(int x=0;x<rz;x++){
				loadchunk(x,y);
			}
		}
	}
	
	public void shift(int dir){
		if(dir == 0){
			offy--;
			for(int ty=rz-1;ty>-1;ty--){
				for(int tx=0;tx<rz;tx++){
					try{
						int[][] temp = rendered[ty][tx];
						rendered[ty][tx] = rendered[ty-1][tx];
						rendered[ty-1][tx] = temp;
						int emp = chunkregion[ty][tx];
						chunkregion[ty][tx] = chunkregion[ty-1][tx];
						chunkregion[ty-1][tx] = emp;
					}catch(Exception ex){
						loadchunk(tx,ty);
					}
				}
			}
		}
		if(dir == 1){
			offx++;
			for(int ty=0;ty<rz;ty++){
				for(int tx=0;tx<rz;tx++){
					try{
						int[][] temp = rendered[ty][tx];
						rendered[ty][tx] = rendered[ty][tx+1];
						rendered[ty][tx+1] = temp;
						int emp = chunkregion[ty][tx];
						chunkregion[ty][tx] = chunkregion[ty][tx+1];
						chunkregion[ty][tx+1] = emp;
					}catch(Exception ex){
						loadchunk(tx,ty);
					}
				}
			}
		}
		if(dir == 2){
			offy++;
			for(int ty=0;ty<rz;ty++){
				for(int tx=0;tx<rz;tx++){
					try{
						int[][] temp = rendered[ty][tx];
						rendered[ty][tx] = rendered[ty+1][tx];
						rendered[ty+1][tx] = temp;
						int emp = chunkregion[ty][tx];
						chunkregion[ty][tx] = chunkregion[ty+1][tx];
						chunkregion[ty+1][tx] = emp;
					}catch(Exception ex){
						loadchunk(tx,ty);
					}
				}
			}
		}
		if(dir == 3){
			offx--;
			for(int ty=0;ty<rz;ty++){
				for(int tx=rz-1;tx>-1;tx--){
					try{
						int[][] temp = rendered[ty][tx];
						rendered[ty][tx] = rendered[ty][tx-1];
						rendered[ty][tx-1] = temp;
						int emp = chunkregion[ty][tx];
						chunkregion[ty][tx] = chunkregion[ty][tx-1];
						chunkregion[ty][tx-1] = emp;
					}catch(Exception ex){
						loadchunk(tx,ty);
					}
				}
			}
		}
	}
	
	public void loadchunk(int x, int y){
		//System.out.println("loading chunk "+(x+offx-rb)+" "+(y+offy-rb));
		int evn = (int)(Math.random()*3);//number of entities on chunk
		if(evn>csize*csize){
			evn = csize*csize;
		}
		chunkregion[y][x] = spawnmap.assignregion(x,y);
		spawnmap.spawnroutine(x, y, chunkregion[y][x]);
		for(int yy=0;yy<csize;yy++){
			for(int xx=0;xx<csize;xx++){
				rendered[y][x][yy][xx] = 0;
			}
		}
	}
	
	public class SpawnMap {
		
		int numregions = 8;
		String[] rnames = {
			"deserted village","dead forest","plain plains","sleeping city","dry desert","decaying swamp","salty flats","underworld"	
		};
		int[][] rcolors = {
			{55,117,56},{17,81,18},{164, 224, 100},{79,84,83},{237, 221, 180},{74, 112, 98},{230, 232, 208},{198,43,43}
		};
		int[][] bounds = {
				{-1,10},
				{5,20},
				{15,30},
				{25,40},
				{35,50},
				{45,60},
				{55,70},
				{65,80}
		};
		// !!!@@@###$$$%%%^^^&&&***((()))---
		int chmax = 16;//16 in the list of probabilities
		int[][] chances = {
				{0,0,0,0,0,0,0,0,0,0,1,1,1,1,2,2},
				{0,0,0,0,0,0,0,0,1,1,1,1,2,2,3,3},
				{0,0,0,1,1,1,1,1,2,2,2,2,2,2,3,4},
				{0,1,2,3,4,9,9,9,9,9,7,7,7,7,7,8},
				{0,1,1,2,2,2,2,2,2,2,3,3,3,3,4,5},
				{0,1,1,2,2,2,2,2,2,2,3,3,3,3,4,5},
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
				{0,0,0,0,0,0,0,1,1,1,1,2,2,2,2,5},
				{0,0,0,0,0,0,0,1,1,1,1,2,2,2,2,5},	
				{0,0,0,0,0,0,0,1,1,1,1,2,2,2,2,5}
		};
		public int assignregion(int cx, int cy){
			int hmy = 0;
			int[] hat = new int[numregions];
			int nowt = Math.abs((cx+offx-rb))+Math.abs((cy+offy-rb));
			for(int i=0;i<numregions;i++){
				if((nowt>=bounds[i][0] || bounds[i][0] == -1) && (nowt<=bounds[i][1] || bounds[i][1] == -1)){
					hat[hmy] = i;
					hmy++;
				}
			}
			return hat[(int)(Math.random()*hmy)];
		}
		public void spawnroutine(int cx, int cy, int region){
			int tryflag = (int)(Math.random()*spmax);
			int tries = spawnchances[region][tryflag];
			int[][] sofar = new int[tries][2];//no dupe spawns
			int favance = 0;
			for(int i=0;i<tries;i++){
				boolean pass = true;
				int spawnflag = (int)(Math.random()*chmax);
				int gen = chances[region][spawnflag];
				int andx = ((int)(Math.random()*csize));
				int andy = ((int)(Math.random()*csize));
				for(int u=0;u<favance;u++){
					if(sofar[u][0]==andx && sofar[u][1]==andy){
						pass = false;
						break;
					}
				}
				if(pass){
					espw((cx+offx-rb)*csize+andx, (cy+offy-rb)*csize+andy, gen);
				}
			}
		}
		
		public void spawnspecificenemy(int cx, int cy, int enemy){
			int andx = ((int)(Math.random()*csize));
			int andy = ((int)(Math.random()*csize));
			espw((cx+offx-rb)*csize+andx, (cy+offy-rb)*csize+andy, enemy);
		}
		
		private void espw(int x, int y, int we){
			if(we>-1){
				Game.create(x, y, new DroneEntity());
			}
		}
	}
}
