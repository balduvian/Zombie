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
		
		int numregions = 4;
		Region[] regions = {new VillageRegion(), new ForestRegion(), new PlainsRegion(), new SwampRegion(), new DesertRegion()};
		
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
			if(Game.bitrand(cx,cy)>0.8){
				int times = (int)(Game.bitrand(cx*3, cy*2)*(csize));
				for(int e=0;e<times;e++){
					blt = structures[(int)(Game.bitrand(cx+cx+e, cy+cy+e+1)*numstructures)].clone();
				}
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
