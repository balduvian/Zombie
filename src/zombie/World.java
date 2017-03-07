package zombie;

public class World {
	
	int seed;
	int rb = 2; //render buffer
	int rz;
	int csize = 8;
	int[][][][] chunks;//cy, cx, y in chunk, x in chunk
	int[][][][] rendered;//the rendered chunks;
	int offy;//offset of chunks
	int offx;
	int rofx;//offset of render
	int rofy;
	int worw;//world width and height
	int worh;
	
	public World(int s){
		seed = s;
		rz = rb*2+1;
		worw = rz;
		worh = rz;
		offy = worh/2;
		offx = worw/2;
		rofy = 0;
		rofx = 0;
		chunks = new int[worh][worw][csize][csize];
		rendered = new int[rz][rz][csize][csize];
		for(int y=0;y<worh;y++){
			for(int x=0;x<worw;x++){
				loadchunk(y,x);
				rcopy(y,x);
			}
		}
	}
	
	public void expand(int dir){
		if(dir == 0){
			int[][][][] neww = new int[worh++][worw][csize][csize];
			offy--;
			for(int ty=0;ty<worh;ty++){
				for(int tx=0;tx<worw;tx++){
					if(ty==0){
						for(int yy=0;yy<csize;yy++){
							for(int xx=0;xx<csize;xx++){
								neww[ty][tx][yy][xx] = (int)(Math.random()*10);
							}
						}
					}else{
						neww[ty][tx] = chunks[ty-1][tx];
					}
				}
			}
			chunks = neww.clone();
		}
		if(dir == 1){
			int[][][][] neww = new int[worh++][worw][csize][csize];
			for(int ty=0;ty<worh;ty++){
				for(int tx=0;tx<worw;tx++){
					if(tx==worw-1){
						for(int yy=0;yy<csize;yy++){
							for(int xx=0;xx<csize;xx++){
								neww[ty][tx][yy][xx] = (int)(Math.random()*10);
							}
						}
					}else{
						neww[ty][tx] = chunks[ty][tx];
					}
				}
			}
			chunks = neww.clone();
		}
		if(dir == 2){
			int[][][][] neww = new int[worh++][worw][csize][csize];
			for(int ty=0;ty<worh;ty++){
				for(int tx=0;tx<worw;tx++){
					if(ty==worh-1){
						for(int yy=0;yy<csize;yy++){
							for(int xx=0;xx<csize;xx++){
								neww[ty][tx][yy][xx] = (int)(Math.random()*10);
							}
						}
					}else{
						neww[ty][tx] = chunks[ty][tx];
					}
				}
			}
			chunks = neww.clone();
		}
		if(dir == 3){
			int[][][][] neww = new int[worh][worw++][csize][csize];
			offx--;
			for(int ty=0;ty<worh;ty++){
				for(int tx=0;tx<worw;tx++){
					if(tx==0){
						for(int yy=0;yy<csize;yy++){
							for(int xx=0;xx<csize;xx++){
								neww[ty][tx][yy][xx] = (int)(Math.random()*10);
							}
						}
					}else{
						neww[ty][tx] = chunks[ty][tx-1];
					}
				}
			}
			chunks = neww.clone();
		}
	}
	
	public void shift(int dir){
		boolean epn = false;
		if(dir == 0){
			rofy--;
			if(offy+rofy-rb<0){//do we need to load more chunks
				epn = true;
			}
		}else if(dir == 1){
			rofx++;
			if(offx+rofx+rb>=worw){//do we need to load more chunks
				epn = true;
			}
		}else if(dir == 2){
			rofy++;
			if(offy+rofy+rb>=worh){//do we need to load more chunks
				epn = true;
			}
		}else if(dir == 3){
			rofx--;
			if(offx+rofx-rb<0){//do we need to load more chunks
				epn = true;
			}
		}
		if(epn){
			expand(dir);
		}
		for(int yy=0;yy<rz;yy++){//then redo the render
			for(int xx=0;xx<rz;xx++){
				rendered[yy][xx] = chunks[yy-rofy][xx-rofx];
			}
		}
	}
	
	public void rcopy(int y, int x){
		for(int yy=0;yy<csize;yy++){
			for(int xx=0;xx<csize;xx++){
				rendered[y][x][yy][xx] = chunks[y][x][yy][xx];
			}
		}
	}
	
	public void loadchunk(int y, int x){
		for(int yy=0;yy<csize;yy++){
			for(int xx=0;xx<csize;xx++){
				//int ay = y*ws+yy;
				//int ax = x*ws+xx;
				chunks[y][x][yy][xx] = (y+x);
				//chunks[y][x][yy][xx] = (int)(Math.random()*10);
			}
		}
	}
	
	//ay*10+ax
	/*public double semrand(int i){
		return Math.random();
		//return Math.abs((Math.sin(i*1000)));
	}*/
}
