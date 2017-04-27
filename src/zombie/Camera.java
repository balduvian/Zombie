package zombie;

public class Camera{
	
	double cx;
	double cy;
	int target;
	int cspeed = 10;
	int mode = 0;
	double[] destin;
	int tleft;
	
	//modes = 0: static, 1:following, 2: movable
	public Camera(double nx, double ny, int md){
		cx = nx;
		cy = ny;
		mode = md;
	}
	
	public Camera(double nx, double ny, int md, int entitytofollow){
		cx = nx;
		cy = ny;
		mode = md;
		target = entitytofollow;
	}
	
	public void tick(){
		if(mode==0){
			if(cx != destin[1] && cy != destin[0]){
				
			}
		}else if(mode==1){
			Entity fiii = Game.entities[target];
			if(fiii!=null){
				cx = fiii.x+fiii.exx;
				cy = fiii.y+fiii.exy;
			}
		}else if(mode==2){
			if(Game.window.pressed.keys[Binds.upbind]){
				cy -= (cspeed/Game.tpm);
			}
			if(Game.window.pressed.keys[Binds.rightbind]){
				cx += (cspeed/Game.tpm);
			}
			if(Game.window.pressed.keys[Binds.downbind]){
				cy += (cspeed/Game.tpm);
			}
			if(Game.window.pressed.keys[Binds.leftbind]){
				cx -= (cspeed/Game.tpm);
			}
		}
	}
	
	public void travel(double y, double x, int tim){
		destin = new double[]{y,x};
		tleft = tim;
	}
}