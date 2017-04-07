package zombie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Entity {
	
	int index; //index in the list;
	int y;
	int x;
	double exy;
	double exx;
	int w;
	int h;
	int health;
	BufferedImage face;
	double speed;
	
	int mmode;
	int mdir;
	
	public void destroy(){
		Game.delete(index);
	}
	
	public void drawcolor(Color c){
		face = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		int rgbc = c.getRGB();
		for(int yy=0;yy<h;yy++){
			for(int xx=0;xx<w;xx++){
				face.setRGB(xx, yy, rgbc);
			}
		}
	}
	
	public Entity(int plx, int ply, int plw, int plh, int plhealth){
		index = Game.enumm;
		x = plx;
		y = ply;
		w = plw;
		h = plh;
		health = plhealth;
		mmode = 0;
		mdir = 4;
		drawcolor(Color.red);
		speed = 2;
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
		if(mmode == 1){
			if(mdir==0){
				if(exy <= -1){
					mmode = 0;
					exy=0;
					y--;
				}else{
					exy-=movam();
				}
			}else if(mdir==1){
				if(exx >= 1){
					mmode = 0;
					exx=0;
					x++;
				}else{
					exx+=movam();
				}
			}else if(mdir==2){
				if(exy >= 1){
					mmode = 0;
					exy=0;
					y++;
				}else{
					exy+=movam();
				}
			}else if(mdir==3){
				if(exx <= -1){
					mmode = 0;
					exx=0;
					x--;
				}else{
					exx-=movam();
				}
			}
		}else{
			shift((int)(Math.random()*4));
		}
		
	}
	
}
