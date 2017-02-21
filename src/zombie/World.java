package zombie;

public class World {
	
	int seed;
	int csize = 8;
	int[][][][] chunks;//cy, cx, y in chunk, x in chunk
	int ylest;
	int xlest;
	int ws = 9;
	
	public World(int s){
		seed = s;
		chunks = new int[ws][ws][csize][csize];
		for(int y=0;y<ws;y++){
			for(int x=0;x<ws;x++){
				loadchunk(y,x);
			}
		}
		ylest = (ws/2);
		xlest = (ws/2);
	}
	
	public void loadchunk(int y, int x){
		for(int yy=0;yy<csize;yy++){
			for(int xx=0;xx<csize;xx++){
				int ay = y*ws+yy;
				int ax = x*ws+xx;
				chunks[y][x][yy][xx] = (int)(semrand(ay*10+ax)*10);
			}
		}
	}
	
	public double semrand(int i){
		return Math.random();
		//return Math.abs((Math.sin(i*1000)));
	}
}
