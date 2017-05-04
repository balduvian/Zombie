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
	
	public int getacx(int cx){
		return (cx+offx-rb);
	}
	public int getacy(int cy){
		return (cy+offy-rb);
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
		
		int acx = getacx(x);
		int acy = getacy(y);
		
		chunkregion[y][x] = spawnmap.assignregion(acx,acy);
		
		spawnmap.spawnroutine(acx, acy, chunkregion[y][x]);
		
		rendered[y][x] = spawnmap.build(acx,acy);
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
		int numprops = 4;
		int[][] props = {
				{ImageLoader.VILLAGEPROP0,ImageLoader.VILLAGEPROP1,ImageLoader.VILLAGEPROP2,ImageLoader.VILLAGEPROP3},
				{ImageLoader.FORESTPROP0,ImageLoader.FORESTPROP1,ImageLoader.FORESTPROP2,ImageLoader.FORESTPROP3},
				{ImageLoader.PLAINSPROP0,ImageLoader.PLAINSPROP1,ImageLoader.PLAINSPROP2,ImageLoader.PLAINSPROP3},
				{ImageLoader.CITYPROP0,ImageLoader.CITYPROP1,ImageLoader.CITYPROP2,ImageLoader.CITYPROP3},
				{ImageLoader.DESERTPROP0,ImageLoader.DESERTPROP1,ImageLoader.DESERTPROP2,ImageLoader.DESERTPROP3},
				{ImageLoader.SWAMPPROP0,ImageLoader.SWAMPPROP1,ImageLoader.SWAMPPROP2,ImageLoader.SWAMPPROP3},
				{ImageLoader.SALTPROP0,ImageLoader.SALTPROP1,ImageLoader.SALTPROP2,ImageLoader.SALTPROP3},
				{ImageLoader.HELLPROP0,ImageLoader.HELLPROP1,ImageLoader.HELLPROP2,ImageLoader.HELLPROP3,},
		};
		int[][] tiles = {
				{ImageLoader.VILLAGEWALL,ImageLoader.VILLAGEPATH},
				{ImageLoader.FORESTWALL,ImageLoader.FORESTPATH},
				{ImageLoader.PLAINSWALL,ImageLoader.PLAINSPATH},
				{ImageLoader.CITYWALL,ImageLoader.CITYPATH},
				{ImageLoader.DESERTWALL,ImageLoader.DESERTPATH},
				{ImageLoader.SWAMPWALL,ImageLoader.SWAMPPATH},
				{ImageLoader.SALTWALL,ImageLoader.SALTPATH},
				{ImageLoader.HELLWALL,ImageLoader.HELLPATH},
		};
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
		int lvmax = 16;
		int[][] levelchances = {
				{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,2},
				{0,0,1,1,1,1,1,1,1,1,1,2,2,2,2,3},
				{1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,4},
				{1,1,2,2,2,2,2,2,3,3,3,3,4,4,4,5},
				{2,2,2,3,3,3,3,4,4,4,4,4,5,5,5,6},
				{3,3,3,3,3,4,4,4,5,5,5,5,6,6,6,7},
				{4,4,4,4,4,4,5,5,5,5,6,6,6,7,7,8},
				{6,6,6,6,6,6,7,7,7,7,8,8,8,9,9,10},
				
		};
		int numstructures = 3;
		int[][][] structures = {
				{
					{0,0,0,2,2,0,0,0},
					{0,1,1,2,2,1,1,0},
					{0,1,2,2,2,2,1,0},
					{0,1,2,2,2,2,1,0},
					{0,1,2,2,2,2,1,0},
					{0,1,2,2,2,2,1,0},
					{0,1,1,2,2,1,1,0},
					{0,0,0,2,2,0,0,0},
				},
				{
					{0,0,0,0,0,0,0,0},
					{0,0,2,2,2,2,0,0},
					{0,2,2,2,2,2,2,0},
					{0,2,2,2,2,2,2,0},
					{1,1,1,1,1,1,1,1},
					{0,2,2,2,2,2,2,0},
					{0,0,2,2,2,2,0,0},
					{0,0,0,0,0,0,0,0},
				},
				{
					{0,0,0,2,2,0,0,0},
					{0,1,2,2,2,2,1,0},
					{0,2,2,2,2,2,2,0},
					{2,2,2,1,1,2,2,2},
					{2,2,2,1,1,2,2,2},
					{0,2,2,2,2,2,2,0},
					{0,1,2,2,2,2,1,0},
					{0,0,0,2,2,0,0,0},
				}
		};
		public int assignregion(int cx, int cy){
			int hmy = 0;
			int[] hat = new int[numregions];
			int nowt = Math.abs(cx)+Math.abs(cy);
			for(int i=0;i<numregions;i++){
				if((nowt>=bounds[i][0] || bounds[i][0] == -1) && (nowt<=bounds[i][1] || bounds[i][1] == -1)){
					hat[hmy] = i;
					hmy++;
				}
			}
			return hat[(int)(Game.bitrand(cx,cy)*hmy)];
		}
		
		public int[][] build(int cx, int cy){
			int[][] blt = new int[csize][csize];
			if(Game.bitrand(cx+cx,cy+cy)>0.8){
				blt = structures[(int)(Game.bitrand(cx+cx, cy+cy)*numstructures)].clone();
			}else{
				int rx = (int)(Game.bitrand(cx+cx,cy+cy)*csize);
				int ry = (int)(Game.bitrand(cx+cx+23,cy+cy+89)*csize);
				blt[ry][rx] = 3;
			}
			return blt;
		}
		
		public void spawnroutine(int cx, int cy, int region){
			int tryflag = (int)(Math.random()*spmax);
			int tries = spawnchances[region][tryflag];
			int[][] sofar = new int[tries][2];//no dupe spawns
			int favance = 0;
			for(int i=0;i<tries;i++){
				boolean pass = true;
				int andx = ((int)(Math.random()*csize));
				int andy = ((int)(Math.random()*csize));
				for(int u=0;u<favance;u++){
					if(sofar[u][0]==andx && sofar[u][1]==andy){
						pass = false;
						break;
					}
				}
				if(pass){
					int spawnflag = (int)(Math.random()*chmax);
					int gen = chances[region][spawnflag];
					int levflag = (int)(Math.random()*lvmax);
					int level = levelchances[region][levflag];
					espw(cx*csize+andx, cy*csize+andy, gen, level);
				}
			}
		}
		
		public void spawnspecificenemy(int cx, int cy, int enemy, int level){
			int andx = ((int)(Math.random()*csize));
			int andy = ((int)(Math.random()*csize));
			espw((cx+offx-rb)*csize+andx, (cy+offy-rb)*csize+andy, enemy, level);
		}
		
		private void espw(int x, int y, int we, int le){
			if(we==0){
				Game.create(x, y, new BasicZombie(le));
			}
			if(we==1){
				Game.create(x, y, new BasicZombie(le));
			}
			if(we==2){
				Game.create(x, y, new BasicZombie(le));
			}
			if(we==3){
				Game.create(x, y, new BasicZombie(le));
			}
			if(we==4){
				Game.create(x, y, new BasicZombie(le));
			}
			if(we==5){
				Game.create(x, y, new BasicZombie(le));
			}
			if(we==6){
				Game.create(x, y, new BasicZombie(le));
			}
		}
	}
}
