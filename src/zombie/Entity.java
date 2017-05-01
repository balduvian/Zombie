package zombie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Entity {
	
	public static int IDNULL = 0;
	public static int IDSURVIVOR = 1;
	public static int IDZOMBIE = 2;
	
	int id;
	int index; //index in the list;
	int y;
	int x;
	double exy;
	double exx;
	double w;
	double h;
	double speed;
	boolean despawn = false;
	int imgid;
	int layers;
	int[] layeredimgid;
	int mmode = 0;
	int mdir = 4;
	
	public void destroy(){
		Game.delete(index);
	}
	
	public Entity(){
		index = Game.enumm;
	}
	
	public void spawnpos(int spx, int spy){
		x = spx;
		y = spy;
	}
	
	public void shift(int dir){
		if(mmode == 0){
			mdir = dir;
			mmode = 1;
			if(mdir==0){
				exy-=0.1;
			}else if(mdir==1){
				exx+=0.1;
			}else if(mdir==2){
				exy+=0.1;
			}else if(mdir==3){
				exx-=0.1;
			}
		}
	}
	
	public double movam(){
		return speed/Game.tpm;
	}
	
	public void tick(){
		if(despawn){
			if(y<(Game.world.rb*Game.world.csize*-1)+Game.world.csize*Game.world.offy){
				destroy();
			}else if(x>(Game.world.csize*(Game.world.rb+1))+(Game.world.csize*Game.world.offx)){
				destroy();
			}else if(y>(Game.world.csize*(Game.world.rb+1))+(Game.world.csize*Game.world.offy)){
				destroy();
			}else if(x<(Game.world.rb*Game.world.csize*-1)+Game.world.csize*Game.world.offx){
				destroy();
			}
		}
	}
	
}
