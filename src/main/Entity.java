package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Entity {
	
	public static int IDNULL = 0;
	public static int IDSURVIVOR = 1;
	public static int IDZOMBIE = 2;
	
	public static int DEFAULTWIDTH = 1;
	public static int DEFAULTHEIGHT = 1;
	
	protected int id = IDNULL;
	int index; //index in the list;
	int y;
	int x;
	double exy;
	double exx;
	double w = DEFAULTWIDTH;
	double h = DEFAULTHEIGHT;
	double speed;
	protected boolean despawn = false;
	
	protected ImgSheet img;

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
			if(y<Game.world.offy-Game.world.rb){
				destroy();
			}else if(x>Game.world.offx+Game.world.rb-1){
				destroy();
			}else if(y>Game.world.offy+Game.world.rb-1){
				destroy();
			}else if(x<Game.world.offx-Game.world.rb){
				destroy();
			}
		}
		img.tick();
	}
	
}
