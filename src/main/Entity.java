package main;

import java.awt.Rectangle;

public class Entity {
	
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	
	public static final int IDNULL = 0;
	public static final int IDSURVIVOR = 1;
	public static final int IDZOMBIE = 2;
	public static final int IDARROW = 3;
	public static final int IDSELECTOR = 4;
	
	public static final int DEFAULTWIDTH = 1;
	public static final int DEFAULTHEIGHT = 1;
	
	public static final int INACTIVESTATE = 0;
	public static final int ACTIVESTATE = 1;
	public static final int HOVERSTATE = 2;
	public static final int PRESSEDSTATE = 3;
	
	protected String name;
	
	boolean selected;
	boolean eselectable;
	
	boolean pressed;
	boolean accepting;
	boolean locked;
	boolean active = true;
	boolean acclock;
	int cast;
	int bstate = 0;
	
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

	boolean shifting = false;
	int mdir = 4;
	int shiftsleft = 0;
	
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
	
	public void inactivate(){
		active = false;
	}
	
	public void shift(int dir, int times){
		if(!shifting){
			shiftsleft = times;
			mdir = dir;
			shifting = true;
		}
	}
	
	protected void onclick(){
		if(eselectable){
			if(!selected){
				Game.select(index);
			}else{
				Game.deselect(index);
			}
		}
	}
	
	protected void finishshift(){
		shiftsleft--;
			if(shiftsleft==0){
			shifting = false;
			if(Game.arrowwait){
				Game.broadcast(Game.CREATEMOVEARROWS);
				Game.arrowwait = false;
			}
		}
	}
	
	public void tick(){
		
		if(shifting){
			if(mdir==0){
				yy-=0.1;
				if(yy<=-1){
					yy=0;
					y--;
					finishshift();
				}
			}else if(mdir==1){
				xx+=0.1;
				if(xx>=1){
					xx=0;
					x++;
					finishshift();
				}
			}else if(mdir==2){
				yy+=0.1;
				if(yy>=1){
					yy=0;
					y++;
					finishshift();
				}
			}else if(mdir==3){
				xx-=0.1;
				if(xx<=-1){
					xx=0;
					x--;
					finishshift();
				}
			}
		}
		
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
		
		boolean md = Game.window.moussed.mdown;
		boolean on = Game.getmouserect().intersects(Game.geterect(index));
		if(active){
			if(on || locked){
				if(locked){
					if(on){
						bstate = PRESSEDSTATE;
						if(!md){
							locked = false;
							onclick();
						}
					}else{
						bstate = HOVERSTATE;
					}
				}else{
					bstate = HOVERSTATE;
				}
				if(!md && !accepting){
					accepting = true;
					acclock = true;
				}else{
					accepting = false;
				}
				if(md && acclock){
					locked = true;
				}else{
					locked = false;
				}
			}else{
				acclock = false;
				bstate = ACTIVESTATE;
				locked = false;
				accepting = false;
			}
		}else{
			bstate = INACTIVESTATE;
		}
		
	}
	
}
