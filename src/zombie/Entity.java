package zombie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Entity {
	
	public static int IDNULL = 0;
	public static int IDSURVIVOR = 1;
	public static int IDZOMBIE = 2;
	
	public static int DEFAULTWIDTH = 1;
	public static int DEFAULTHEIGHT = 1;
	
	int id = IDNULL;
	int index; //index in the list;
	int y;
	int x;
	double exy;
	double exx;
	double w = DEFAULTWIDTH;
	double h = DEFAULTHEIGHT;
	double speed;
	boolean despawn = false;
	
	ImgSheet imgs;

	int mmode = 0;
	int mdir = 4;
	
	public class ImgSheet{
		boolean anim;
		int frames;
		int layers;
		double frametime;
		double timer;
		double vari;
		int frame;
		int[][] sheet;
		
		public ImgSheet(int[][] imagse, double time, double variation){
			sheet = imagse.clone();
			frametime=time;
			vari = variation;
			baseops();			
		}
		
		public ImgSheet(){
			sheet = new int[][]{{ImageLoader.CLOSE}};
			frametime = 1;
			vari = 0;
			baseops();
			anim = false;
		}
		
		public double makeac(){
			return frametime + (Math.random()*(vari*2)-vari);
		}
		
		public int randframe(){
			return (int)(Math.random()*frames);
		}
		
		private void baseops(){
			frame = randframe();
			frames = sheet.length;
			layers = sheet[0].length;
			timer = makeac();
			anim = true;
		}
	}
	
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
		if(imgs.anim){
			imgs.timer -= (1.0/Game.tpm);
			if(imgs.timer<=0){
				imgs.timer = imgs.makeac();
				imgs.frame = (imgs.frame+1)%imgs.frames;
			}
		}
	}
	
}
