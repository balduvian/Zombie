package zombie;

public class World {
	
	int seed;
	int rb = 2; //render buffer
	int rz;
	int csize = 8;
	int[][][][] rendered;//the rendered chunks;
	int offy;//offset of chunks
	int offx;
	int rofx;//offset of render
	int rofy;
	
	public World(int s){
		seed = s;
		rz = rb*2+1;
		offy = rz/2;
		offx = rz/2;
		rendered = new int[rz][rz][csize][csize];
		for(int y=0;y<rz;y++){
			for(int x=0;x<rz;x++){
				loadchunk(y,x);
			}
		}
	}
	
	public void shift(int dir){
		System.out.println("EXPANDING DONGER");
		if(dir == 0){
			offy--;
			for(int ty=0;ty<rz;ty++){
				for(int tx=0;tx<rz;tx++){
					if(ty==0){
						loadchunk(ty,tx);
					}else{
						try{
							rendered[ty][tx] = rendered[ty-1][tx];
						}catch(Exception ex){}
					}
				}
			}
		}
		if(dir == 1){
			offx++;
			for(int ty=0;ty<rz;ty++){
				for(int tx=0;tx<rz;tx++){
					if(tx==rz-1){
						loadchunk(ty,tx);
					}else{
						try{
							rendered[ty][tx] = rendered[ty][tx+1];
						}catch(Exception ex){}
					}
				}
			}
		}
		if(dir == 2){
			offy++;
			for(int ty=0;ty<rz;ty++){
				for(int tx=0;tx<rz;tx++){
					if(ty==rz-1){
						loadchunk(ty,tx);
					}else{
						try{
							rendered[ty][tx] = rendered[ty+1][tx];
						}catch(Exception ex){}
					}
				}
			}
		}
		if(dir == 3){
			offx--;
			for(int ty=0;ty<rz;ty++){
				for(int tx=0;tx<rz;tx++){
					if(tx==0){
						loadchunk(ty,tx);
					}else{
						try{
							rendered[ty][tx] = rendered[ty][tx-1];
						}catch(Exception ex){}
					}
				}
			}
		}
	}
	
	public void loadchunk(int y, int x){
		for(int yy=0;yy<csize;yy++){
			for(int xx=0;xx<csize;xx++){
				//int ay = y*ws+yy;
				//int ax = x*ws+xx;
				rendered[y][x][yy][xx] = (Math.abs((y-offy)%5+(x-offx)%5)+(int)(Math.random()*2))%10;
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
