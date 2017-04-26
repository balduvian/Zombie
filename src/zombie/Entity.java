package zombie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Entity {
	
	int xres;
	int yres;
	int index; //index in the list;
	int y;
	int x;
	double exy;
	double exx;
	double w;
	double h;
	int health;
	BufferedImage face;
	double speed;
	
	int mmode;
	int mdir;
	
	public void destroy(){
		Game.delete(index);
	}
	
	public void drawcolor(Color c){
		face = new BufferedImage(xres,yres,BufferedImage.TYPE_INT_ARGB);
		int rgbc = c.getRGB();
		for(int yy=0;yy<yres;yy++){
			for(int xx=0;xx<xres;xx++){
				face.setRGB(xx, yy, rgbc);
			}
		}
	}
	
	public void drawnoise(){
		face = new BufferedImage(xres,yres,BufferedImage.TYPE_INT_ARGB);
		for(int yy=0;yy<yres;yy++){
			for(int xx=0;xx<xres;xx++){
				int rgbc = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)).getRGB();
				face.setRGB(xx, yy, rgbc);
			}
		}
	}
	
	public Entity(int plx, int ply){
		index = Game.enumm;
		x = plx;
		y = ply;
		mmode = 0;
		mdir = 4;
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
		
	}
	
}
