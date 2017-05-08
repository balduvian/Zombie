package main;
import zombie.BasicZombie;

public class World {
	
	int seed;
	int wsize = 64;
	int rb;
	int ssize;
	Block[][][] world;//the rendered blocks
	int[][] worldregion;//the region for each chunk
	int offy = 0;//offset of world
	int offx = 0;
	SpawnMap spawnmap;
	
	public World(int s){
		seed = s;
		rb = wsize/2;
		spawnmap = new SpawnMap();
		ssize = 2;
		world = new Block[wsize][wsize][ssize];
		worldregion = new int[wsize][wsize];
		for(int y=0;y<wsize;y++){
			for(int x=0;x<wsize;x++){
				loadstack(x,y);
			}
		}
	}
	
	public int getacx(int sx){
		return (sx+offx-rb);
	}
	public int getacy(int sy){
		return (sy+offy-rb);
	}
	
	public void shift(int dir, int times){
		for(int ti=0;ti<times;ti++){
			if(dir == 0){
				offy--;
				for(int ty=wsize-1;ty>-1;ty--){
					for(int tx=0;tx<wsize;tx++){
						try{
							Block[] temp = world[ty][tx];
							world[ty][tx] = world[ty-1][tx];
							world[ty-1][tx] = temp;
							int emp = worldregion[ty][tx];
							worldregion[ty][tx] = worldregion[ty-1][tx];
							worldregion[ty-1][tx] = emp;
						}catch(Exception ex){
							loadstack(tx,ty);
						}
					}
				}
			}
			if(dir == 1){
				offx++;
				for(int ty=0;ty<wsize;ty++){
					for(int tx=0;tx<wsize;tx++){
						try{
							Block[] temp = world[ty][tx];
							world[ty][tx] = world[ty][tx+1];
							world[ty][tx+1] = temp;
							int emp = worldregion[ty][tx];
							worldregion[ty][tx] = worldregion[ty][tx+1];
							worldregion[ty][tx+1] = emp;
						}catch(Exception ex){
							loadstack(tx,ty);
						}
					}
				}
			}
			if(dir == 2){
				offy++;
				for(int ty=0;ty<wsize;ty++){
					for(int tx=0;tx<wsize;tx++){
						try{
							Block[] temp = world[ty][tx];
							world[ty][tx] = world[ty+1][tx];
							world[ty+1][tx] = temp;
							int emp = worldregion[ty][tx];
							worldregion[ty][tx] = worldregion[ty+1][tx];
							worldregion[ty+1][tx] = emp;
						}catch(Exception ex){
							loadstack(tx,ty);
						}
					}
				}
			}
			if(dir == 3){
				offx--;
				for(int ty=0;ty<wsize;ty++){
					for(int tx=wsize-1;tx>-1;tx--){
						try{
							Block[] temp = world[ty][tx];
							world[ty][tx] = world[ty][tx-1];
							world[ty][tx-1] = temp;
							int emp = worldregion[ty][tx];
							worldregion[ty][tx] = worldregion[ty][tx-1];
							worldregion[ty][tx-1] = emp;
						}catch(Exception ex){
							loadstack(tx,ty);
						}
					}
				}
			}
		}
	}
	
	public void loadstack(int x, int y){
		
		int acx = getacx(x);
		int acy = getacy(y);
		
		worldregion[y][x] = spawnmap.assignregion(acx,acy);
		
		spawnmap.spawnroutine(acx, acy);
		
		world[y][x] = spawnmap.build(acx,acy);
	}
	
	public class SpawnMap {
		
		int numregions = 4;
		Region[] regions = {Region.region(Region.VILLAGEID), Region.region(Region.FORESTID)};
		
		public int assignregion(int cx, int cy){
			int hmy = 0;
			int[] hat = new int[numregions];
			int nowt = Math.abs(cx)+Math.abs(cy);
			for(int i=0;i<numregions;i++){
				if((nowt>=regions[i].lowbound || regions[i].lowbound == -1) && (nowt<=regions[i].highbound || regions[i].highbound == -1)){
					hat[hmy] = regions[i].regionid;
					hmy++;
				}
			}
			return hat[(int)(Game.bitrand(cx,cy)*hmy)];
		}
		
		public Block[] build(int cx, int cy){
			Block[] tsk = new Block[ssize];
			tsk[1] = Block.block(Block.AIR);
			return tsk;
		}
		
		public void spawnroutine(int cx, int cy){
			if(Math.random()<regions[worldregion[cy][cx]].schance){
				int enemy = regions[worldregion[cy][cx]].enemychances[(int)(Math.random()*regions[worldregion[cy][cx]].enmax)];
				int level = regions[worldregion[cy][cx]].levelchances[(int)(Math.random()*regions[worldregion[cy][cx]].lvmax)];
				espw(cx,cy,enemy,level);
			}
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
