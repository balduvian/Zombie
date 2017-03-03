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
		setrender(640,480,1.0,32);
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
		return (int)(x-(w/2)-Game.globalx);
	}
	public int ydisp(double y, int h){
		return (int)(y-(h/2)-Game.globaly);
	}
	
	public class Canvas extends JPanel{
		private static final long serialVersionUID = 7723498599824735171L;

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			World ga = Game.world;
			for(int y=0;y<cdy;y++){//draw world
				for(int x=0;x<cdx;x++){
					try{
						int yn = (int)Math.round(((Game.globaly+y*ga.csize*tiles)/(ga.ws*ga.csize*tiles))*ga.ws)-1;
						int xn = (int)Math.round(((Game.globalx+x*ga.csize*tiles)/(ga.ws*ga.csize*tiles))*ga.ws)-1;
						for(int yy=0;yy<ga.csize;yy++){//draw world
							for(int xx=0;xx<ga.csize;xx++){
								int cn = ga.chunks[yn][xn][yy][xx]*28;
								g.setColor(new Color(cn,cn,cn));
								g.fillRect(xdisp(xn*tiles*ga.csize+(xx*tiles),tiles),ydisp(yn*tiles*ga.csize+(yy*tiles),tiles),tiles,tiles);
								//g.fillRect(xn*tiles*ga.csize+(xx*tiles)-(int)Game.globalx, yn*tiles*ga.csize+(yy*tiles)-(int)Game.globaly, tiles, tiles);
							}
						}
					}catch(Exception ex){}
				}
			}
			for(int c=0;c<Game.enumm;c++){
				Entity et = Game.entities[c];
				if(et!=null){
					g.drawImage(et.face,xdisp(et.x,et.w),ydisp(et.y,et.h),et.w,et.h,null);
				}
			}
			g.setColor(Color.black);
			g.drawString(Game.globaly+" "+Game.globalx, 20, 20);
			repaint();
		}
	}
	
}
