package main;
import zombie.BasicZombie;

public class World {
	
	int seed;
	int wsize = 17;
	int rb;
	int ssize;
	Block[][][] world;//the rendered blocks
	Region[][] worldregion;//the region for each chunk
	int offy = 0;//offset of world
	int offx = 0;
	SpawnMap spawnmap;
	
	public World(int s){
		seed = s;
		rb = wsize/2;
		spawnmap = new SpawnMap();
		ssize = 2;
		world = new Block[wsize][wsize][ssize];
		worldregion = new Region[wsize][wsize];
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
	
	public void tick(){
		int dtimes = Game.globaly-offy;
		shift(2,dtimes);
		int ltimes = offx-Game.globalx;
		shift(3,ltimes);
		int utimes = offy-Game.globaly;
		shift(0,utimes);
		int rtimes = Game.globalx-offx;
		shift(1,rtimes);
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
							Region emp = worldregion[ty][tx];
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
							Region emp = worldregion[ty][tx];
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
							Region emp = worldregion[ty][tx];
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
							Region emp = worldregion[ty][tx];
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
		
		worldregion[y][x] = spawnmap.assignregion(x,y,acx,acy);
		
		world[y][x] = spawnmap.build(x,y,acx,acy);
		
		spawnmap.spawnroutine(x,y,acx,acy);
	}
	
	public class SpawnMap {
		
		Region[] regions = {Region.region(Region.VILLAGEID), Region.region(Region.FORESTID)};
		int numregions = regions.length;
		
		public Region assignregion(int x, int y, int cx, int cy){
			int hmy = 0;
			Region[] hat = new Region[numregions];
			int nowt = Math.abs(cx)+Math.abs(cy);
			for(int i=0;i<numregions;i++){
				if((nowt>=regions[i].lowbound || regions[i].lowbound == -1) && (nowt<=regions[i].highbound || regions[i].highbound == -1)){
					hat[hmy] = regions[i];
					hmy++;
				}
			}
			int hsel = (int)(Game.bitrand(cx,cy)*hmy);
			if(hat[hsel]==null){
				return Region.region(Region.NULLID);
			}else{
				return hat[hsel];
			}
		}
		
		public Block[] build(int x, int y, int cx, int cy){
			Block[] tsk = new Block[ssize];
			tsk[0] = Block.block(Block.AIR,0);
			tsk[1] = (worldregion[y][x].groundblock);
			return tsk;
		}
		
		public void spawnroutine(int x, int y, int cx, int cy){
			double chg = Math.random();
			if(Math.abs(cx)+Math.abs(cy)>8){
				if(chg<worldregion[y][x].spawnchance){
					int enemy = worldregion[y][x].enemychances[(int)(Math.random()*worldregion[y][x].enmax)];
					int level = worldregion[y][x].levelchances[(int)(Math.random()*worldregion[y][x].lvmax)];
					spawncharacter(cx,cy,enemy,level);
				}
			}
		}
		
		public void spawncharacter(int x, int y, int we, int le){
			Character ctm = null;
			if(we==Character.SURVIVORCONSTANT){
				ctm = new Survivor();
			}if(we==Zombie.BASICZOMBIEID){
				ctm = new BasicZombie();
			}else if(we==Zombie.CACTUSZOMBIEID){
				ctm = new BasicZombie();
			}else if(we==Zombie.COWBOYZOMBIEID){
				ctm = new BasicZombie();
			}else if(we==Zombie.DEVILID){
				ctm = new BasicZombie();
			}else if(we==Zombie.ELEMENTALID){
				ctm = new BasicZombie();
			}else if(we==Zombie.FLAMESKELETONID){
				ctm = new BasicZombie();
			}else if(we==Zombie.GAPINGZOMBIEID){
				ctm = new BasicZombie();
			}else if(we==Zombie.GHOSTID){
				ctm = new BasicZombie();
			}else if(we==Zombie.GIANTZOMBIEID){
				ctm = new BasicZombie();
			}else if(we==Zombie.HALFZOMBIEID){
				ctm = new BasicZombie();
			}else if(we==Zombie.IMPID){
				ctm = new BasicZombie();
			}else if(we==Zombie.METALZOMBIEID){
				ctm = new BasicZombie();
			}else if(we==Zombie.MUSCLEZOMBIEID){
				ctm = new BasicZombie();
			}else if(we==Zombie.OOZEID){
				ctm = new BasicZombie();
			}else if(we==Zombie.SANDZOMBIEID){
				ctm = new BasicZombie();
			}else if(we==Zombie.SKELETONID){
				ctm = new BasicZombie();
			}else{
				ctm = new BasicZombie();
			}
			ctm.setlevel(le);
			Game.create(x, y, ctm);
		}
	}
}
