package main;

public class Camera{
	
	double cx;
	double cy;
	int target;
	double cspeed = 0.15;
	int mode = 0;
	double[] destin;
	int tleft;
	int index;
	
	//modes = 0: static, 1:following, 2: movable
	public Camera(double nx, double ny, int md){
		index = Game.cnumm;
		cx = nx;
		cy = ny;
		mode = md;
	}
	
	public Camera(double nx, double ny, int md, int entitytofollow){
		index = Game.cnumm;
		cx = nx;
		cy = ny;
		mode = md;
		target = entitytofollow;
	}
	
	public void destroy(){
		Game.cdelete(index);
	}
	
	public void tick(){
		if(mode==0){
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
		}else if(mode==1){
			Entity fiii = Game.entities[target];
			if(fiii!=null){
				cx = fiii.x+fiii.exx;
				cy = fiii.y+fiii.exy;
			}
		}else if(mode==2){
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