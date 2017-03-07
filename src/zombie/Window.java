package zombie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Window extends JFrame{
	private static final long serialVersionUID = 2645703612186155260L;
	
	int tiles;
	int width;
	int height;
	int pixelh;
	int pixelw;
	int cdy;
	int cdx;
	Canvas canvas;
	Misen pressed;
	
	public void setrender(int winw, int winh, double pfac, int ts){
		tiles = ts;
		width = winw;
		height = winh;
		pixelw = (int)(winw*pfac);
		pixelh = (int)(winh*pfac);
		cdy = (int)Math.ceil(pixelh/(Game.world.csize*tiles))+3;//chunks displayed at a time
		cdx = (int)Math.ceil(pixelw/(Game.world.csize*tiles))+2;
		setSize(width,height);
	}
	
	public Window(){
		setrender(640,480,1.0,Game.square);
		setResizable(true);
		pressed = new Misen();
		addKeyListener(pressed);
		canvas = new Canvas();
		add(canvas);
		setTitle("Zombie");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public class Misen implements KeyListener{
		boolean[] keys = new boolean[100];
		public void keyPressed(KeyEvent ex) {
			int y = ex.getKeyCode();
			if(y<100){
				keys[y] = true;
			}
		}
		public void keyReleased(KeyEvent ex) {
			int y = ex.getKeyCode();
			if(y<100){
				keys[y] = false;
			}
		}
		public void keyTyped(KeyEvent ex) {}
	}
	
	public int xdisp(double x, int w){
		return (int)(x-(w/2)-Game.globalx)-(width/2);
	}
	public int ydisp(double y, int h){
		return (int)(y-(h/2)-Game.globaly)-(height/2);
	}
	
	public class Canvas extends JPanel{
		private static final long serialVersionUID = 7723498599824735171L;

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			World ga = Game.world;
			for(int y=0;y<ga.rz;y++){//draw world
				for(int x=0;x<ga.rz;x++){
					for(int yy=0;yy<ga.csize;yy++){//draw world
						for(int xx=0;xx<ga.csize;xx++){
							int ccp = ga.rendered[y][x][yy][xx]*18;
							g.setColor(new Color(ccp,ccp,ccp));
							g.fillRect(((x*ga.csize*tiles)-((ga.csize*tiles)/2))+((xx*tiles)-(tiles/2))-(int)(Game.globalx%(ga.csize*tiles))-(width/2), ((y*ga.csize*tiles)-((ga.csize*tiles)/2))+((yy*tiles)-(tiles/2))-(int)(Game.globaly%(ga.csize*tiles))-(height/2), tiles, tiles);
						}
					}
				}
			}
			for(int c=0;c<Game.enumm;c++){
				Entity et = Game.entities[c];
				if(et!=null){
					g.drawImage(et.face,xdisp(et.x,et.w),ydisp(et.y,et.h),et.w,et.h,null);
				}
			}
			g.setColor(Color.red);
			g.drawRect(0, 0, width-1, height-1);
			g.setColor(Color.black);
			g.drawString(Game.globaly+" "+Game.globalx, 20, 20);
			repaint();
		}
	}
	
}
