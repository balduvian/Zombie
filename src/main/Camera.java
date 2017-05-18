package main;

public class Camera{
	
	public static final int MOVETOMODE = 0;
	public static final int ENTITYMODE = 1;
	public static final int CONTROLMODE = 2;
	
	double cx;
	double cy;
	int target;
	double cspeed = 0.15;
	int mode = 0;
	double[] destin;
	int tleft;
	int index;
	
	//modes = 0: static, 1:following, 2: movable
	public Camera(double nx, double ny){
		index = Game.cnumm;
		cx = nx;
		cy = ny;
		mode = MOVETOMODE;
	}
	
	public Camera(double nx, double ny, int m){
		index = Game.cnumm;
		cx = nx;
		cy = ny;
		mode = m;
	}
	
	public void setmode(int m){
		mode = m;
	}
	
	public void setfollow(int eid){
		target = eid;
		mode = ENTITYMODE;
	}
	
	public void destroy(){
		Game.cdelete(index);
	}
	
	public void tick(){
		if(mode==MOVETOMODE){
			if(Math.abs(cy-destin[0])<=cspeed){//fix rounding errors in movement
				cy = destin[0];
			}
			if(Math.abs(cx-destin[0])<=cspeed){
				cx = destin[0];
			}
			if(cy < destin[0]){
				cy += cspeed;
			}else if(cy > destin[0]){
				cy -= cspeed;
			}
			if(cx < destin[1]){
				cx += cspeed;
			}else if(cx > destin[1]){
				cx -= cspeed;
			}
		}else if(mode==ENTITYMODE){
			Entity fiii = Game.entities[target];
			if(fiii!=null){
				cx = fiii.x+fiii.exx;
				cy = fiii.y+fiii.exy;
			}
		}else if(mode==CONTROLMODE){
			if(Game.window.pressed.keys[Binds.UPBIND]){
				cy -= cspeed;
			}
			if(Game.window.pressed.keys[Binds.RIGHTBIND]){
				cx += cspeed;
			}
			if(Game.window.pressed.keys[Binds.DOWNBIND]){
				cy += cspeed;
			}
			if(Game.window.pressed.keys[Binds.LEFTBIND]){
				cx -= cspeed;
			}
			if(cy>4){
				cy=4;
			}
			if(cx>4){
				cx=4;
			}
			if(cy<-4){
				cy=-4;
			}
			if(cx<-4){
				cx=-4;
			}
		}
	}
	
	public void travel(double y, double x){
		destin = new double[]{y,x};
	}
}