package zombie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Entity {
	
	int index; //index in the list;
	double x;
	double y;
	int w;
	int h;
	int health;
	BufferedImage face;
	
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
	
	public Entity(double plx, double ply, int plw, int plh, int plhealth){
		index = Game.enumm;
		x = plx;
		y = ply;
		w = plw;
		h = plh;
		health = plhealth;
		drawcolor(Color.red);
	}
}
