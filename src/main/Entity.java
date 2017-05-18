package main;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public static int IDNULL = 0;
	public static int IDSURVIVOR = 1;
	public static int IDZOMBIE = 2;
	public static int IDARROW = 3;
	
	public static int DEFAULTWIDTH = 1;
	public static int DEFAULTHEIGHT = 1;
	
	boolean hover;
	boolean pressed;
	boolean accepting;
	boolean locked;
	boolean docast;
	boolean casted;
	int clickbroadcast;
	
	protected int id = IDNULL;
	int index; //index in the list;
	int y;
	int x;
	double yy;
	double xx;
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
		initroutine();
		setup();
	}
	
	//toovveride tho
	protected void initroutine(){
		
	}
	
	private void setup(){
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
				yy-=0.1;
			}else if(mdir==1){
				xx+=0.1;
			}else if(mdir==2){
				yy+=0.1;
			}else if(mdir==3){
				xx-=0.1;
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
			}else if(x>Game.world.offx+Game.world.rb){
				destroy();
			}else if(y>Game.world.offy+Game.world.rb){
				destroy();
			}else if(x<Game.world.offx-Game.world.rb){
				destroy();
			}
		}
		
		img.tick();
		
		Rectangle mr = new Rectangle(Game.window.moussed2.mx,Game.window.moussed2.my,1,1);
		if(mr.intersects(new Rectangle(Game.geterect(index)))){
			hover = true;
			if(!Game.window.moussed.mdown && accepting){
				locked = true;
				accepting = true;
			}else if(!locked){
				accepting = false;
			}
			if(accepting && Game.window.moussed.mdown){
				pressed = true;
				if(!casted){
					casted = true;
					if(docast){
						Game.broadcast(clickbroadcast);
					}
				}
			}else{
				pressed = false;
			}
		}else{
			casted = false;
			hover = false;
			pressed = false;
			accepting = false;
			locked = false;
		}
		
	}
	
}
