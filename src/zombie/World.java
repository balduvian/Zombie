package zombie;

public class World {
	
	int seed;
	int rb = 2; //render buffer
	int rz;
	int csize = 8;
	int[][][][] rendered;//the rendered chunks
	int[][] chunkregion;//the region for each chunk
	int offy;//offset of chunks
	int offx;
	int rofx;//offset of render
	int rofy;
	
	public World(int s){
		seed = s;
		rz = rb*2+1;
		offy = 0;
		offx = 0;
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
		System.out.println("loading chunk "+(x+offx-rb)+" "+(y+offy-rb));
		int evn = (int)(Math.random()*3);//number of entities on chunk
		if(evn>csize*csize){
			evn = csize*csize;
		}
		int[][] sofar = new int[evn][2];//no dupe spawns
		int favance = 0;
		for(int i=0;i<evn;i++){
			boolean pass = true;
			int chunkx = (x+offx-rb)*csize;
			int chunky = (y+offy-rb)*csize;
			int andx = ((int)(Math.random()*csize));
			int andy = ((int)(Math.random()*csize));
			for(int u=0;u<favance;u++){
				if(sofar[u][0]==andx && sofar[u][1]==andy){
					pass = false;
					//System.out.println("DENIED");
					break;
				}
			}
			if(pass){//if it's not a dupe lol
				Game.create(chunkx+andx, chunky+andy, new DroneEntity());
				sofar[favance][0] = andx;
				sofar[favance][1] = andy;
						favance++;
			}
		}
		chunkregion[y][x] = (int)(Math.random()*Game.spawnmap.numregions);
		for(int yy=0;yy<csize;yy++){
			for(int xx=0;xx<csize;xx++){
				rendered[y][x][yy][xx] = 0;
			}
		}
	}
	
	//ay*10+ax
	/*public double semrand(int i){
		return Math.random();
		//return Math.abs((Math.sin(i*1000)));
	}*/
}
