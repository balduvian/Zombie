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
		cdy = (int)Math.ceil(pixelh/(Game.world.csize*tiles))+1;//chunks displayed at a time
		cdx = (int)Math.ceil(pixelw/(Game.world.csize*tiles))+1;
		setSize(width,height);
	}
	
	public Window(){
		setrender(640,480,1.0,32);
		setResizable(false);
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
	
	public class Canvas extends JPanel{
		private static final long serialVersionUID = 7723498599824735171L;

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			//g.fillRect(32,32,16,16);
			World ga = Game.world;
			for(int y=0;y<cdy;y++){//draw world
				for(int x=0;x<cdx;x++){
					//System.out.println(cdy+" "+cdx);
					try{
						int yn = (int)(((Game.globaly+y*ga.csize*tiles)/(ga.ws*ga.csize*tiles))*ga.ws);
						int xn = (int)(((Game.globalx+x*ga.csize*tiles)/(ga.ws*ga.csize*tiles))*ga.ws);
						for(int yy=0;yy<ga.csize;yy++){//draw world
							for(int xx=0;xx<ga.csize;xx++){
								int cn = ga.chunks[yn][xn][yy][xx]*28;
								g.setColor(new Color(cn,cn,cn));
								g.fillRect(xn*tiles*ga.csize+(xx*tiles)-(int)Game.globalx, yn*tiles*ga.csize+(yy*tiles)-(int)Game.globaly, tiles, tiles);
							}
						}
					}catch(Exception ex){}
				}
			}
			repaint();
		}
	}
	
}
