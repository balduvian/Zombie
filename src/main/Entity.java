package main;

import java.awt.Rectangle;

public class Entity {

	public static int IDNULL = 0;
	public static int IDSURVIVOR = 1;
	public static int IDZOMBIE = 2;
	public static int IDARROW = 3;
	
	public static int DEFAULTWIDTH = 1;
	public static int DEFAULTHEIGHT = 1;
	
	public static final int INACTIVESTATE = 0;
	public static final int ACTIVESTATE = 1;
	public static final int HOVERSTATE = 2;
	public static final int PRESSEDSTATE = 3;
	
	boolean pressed;
	boolean accepting;
	boolean locked;
	boolean active;
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
	
	protected void onclick(){
		
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
		
		boolean md = Game.window.moussed.mdown;
		boolean on = Game.getmouserect().intersects(Game.geterect(index));
		if(active){
			if(on || locked){
				if(locked){
					if(on){
						bstate = GUIButton.PRESSEDSTATE;
						if(!md){
							locked = false;
							onclick();
						}
					}else{
						bstate = GUIButton.HOVERSTATE;
					}
				}else{
					bstate = GUIButton.HOVERSTATE;
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
				bstate = GUIButton.ACTIVESTATE;
				locked = false;
				accepting = false;
			}
		}else{
			bstate = GUIButton.INACTIVESTATE;
		}
		
	}
	
}
