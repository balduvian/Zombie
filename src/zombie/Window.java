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
		setrender(1200,480,1.0,Game.square);
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
	
	public int[] ldr(int x, double xx, int y, double yy, int w, int h){
		int d1 = xdisp(x,xx,w);
		int d2 = ydisp(y,yy,h);
		int d3 = w*Game.square;
		int d4 = h*Game.square;
		return new int[]{d1,d2,d3,d4};
	}
	
	public int xdisp(int x, double off, int w){
		return (int)((((x+off)-(w/2.0))-Game.gexx-Game.globalx)*Game.square+width/2);
		//return (int)(x-(w/2)-Game.globalx)-(width/2);
	}
	public int ydisp(int y, double off, int h){
		//return (int)(y-(h/2)-Game.globaly)-(height/2);
		return (int)((((y+off)-(h/2.0))-Game.gexy-Game.globaly)*Game.square+height/2);
	}
	
	public class Canvas extends JPanel{
		private static final long serialVersionUID = 7723498599824735171L;

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			World ga = Game.world;
			for(int y=0;y<ga.rz;y++){//draw chunk
				for(int x=0;x<ga.rz;x++){
					for(int yy=0;yy<ga.csize;yy++){//draw within
						for(int xx=0;xx<ga.csize;xx++){
							
							int ccp = ga.rendered[y][x][yy][xx]*18;
							g.setColor(new Color(ccp,ccp,ccp));
							
							int[] lo = ldr(((x+ga.offx-ga.rb)*ga.csize)+xx,0,((y+ga.offy-ga.rb)*ga.csize)+yy,0,1,1);
							g.fillRect(lo[0],lo[1],lo[2],lo[3]);
						}
					}
				}
			}
			for(int c=0;c<Game.enumm;c++){
				Entity et = Game.entities[c];
				if(et!=null){
					g.setColor(Color.red);
					int[] lo = ldr(et.x,et.exx,et.y,et.exy,et.w,et.h);
					g.fillRect(lo[0],lo[1],lo[2],lo[3]);
					//odr((int)(et.x),(int)(et.y),tiles,tiles,g);
					//g.drawImage(et.face,xdisp(et.x,et.w),ydisp(et.y,et.h),et.w,et.h,null);
				}
			}
			g.setColor(Color.red);
			g.drawRect(0, 0, width-1, height-1);
			g.setColor(Color.black);
			g.drawString(Game.globaly+" "+Game.gexy+" "+Game.globalx+" "+Game.gexx, 20, 20);
			g.drawRect(width/2-2, height/2-2, 2, 2);
			repaint();
		}
	}
	
}
