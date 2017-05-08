package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	Misen pressed;
	BufferStrategy bb;
	Graphics g;
	ExecutorService exe;
	
	public void setrender(int winw, int winh, double pfac, int ts){
		tiles = ts;
		width = winw;
		height = winh;
		pixelw = (int)(winw*pfac);
		pixelh = (int)(winh*pfac);
		//cdy = (int)Math.ceil(pixelh/(Game.world.csize*tiles))+3;//chunks displayed at a time
		//cdx = (int)Math.ceil(pixelw/(Game.world.csize*tiles))+2;
		setSize(width,height);
	}
	
	public class PaintLoop implements Runnable{
		public void run() {
			while(true){
				do {
					try{
						g = bb.getDrawGraphics();
						if(Game.activity==1){
							World wo = Game.world;
							for(int z=0;z<wo.ssize;z++){
								for(int y=0;y<wo.wsize;y++){//draw chunk
									for(int x=0;x<wo.wsize;x++){
										int acx = ((x+wo.offx-wo.rb)*wo.wsize);
										int acy = ((y+wo.offy-wo.rb)*wo.wsize);

										int[] lo = ldr(acx,0,acy,0,1,1);
										g.fillRect(lo[0],lo[1],lo[2],lo[3]);
										
										g.drawImage(Game.images[wo.world],lo[0],lo[1],lo[2],lo[3],null);

									}
								}
							}
							for(int c=0;c<Game.enumm;c++){
								Entity et = Game.entities[c];
								if(et!=null){
									int[] lo = ldr(et.x,et.exx,et.y,et.exy,et.w,et.h);
									for(int l=0;l<et.imgs.sheet[et.imgs.mode][et.imgs.frame].length;l++){
										g.drawImage(Game.images[et.imgs.sheet[et.imgs.mode][et.imgs.frame][l]], lo[0],lo[1],lo[2],lo[3], null);
									}
								}
							}
							g.setColor(Color.red);
							g.drawString(Game.globaly+" | "+Math.floor(Game.gexy*100)/100+" | "+Game.globalx+" | "+Math.floor(Game.gexx*100)/100+" | "+Game.world.seed, 20, 30);
							g.drawRect(width/2-2, height/2-2, 2, 2);
						}else{
							//OH YEAH MR KRABS
						}
					} finally {
						g.dispose();
					}
					bb.show();
				} while (bb.contentsLost());
			}
		}
	}
	
	public Window(){
		setrender(1600,800,1.0,Game.square);
		this.setBounds(100, 100, width+100, height+100);
		setUndecorated(true);
		pressed = new Misen();
		addKeyListener(pressed);
		setTitle("Zombie");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		createBufferStrategy(2);
		bb = getBufferStrategy();
		exe = Executors.newFixedThreadPool(1);
		exe.execute(new PaintLoop());
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
	
	public int[] ldr(int x, double xx, int y, double yy, double w, double h){
		int d1 = xdisp(x,xx,w);
		int d2 = ydisp(y,yy,h);
		int d3 = (int)(w*Game.square);
		int d4 = (int)(h*Game.square);
		return new int[]{d1,d2,d3,d4};
	}
	
	public int xdisp(int x, double off, double w){
		return (int)((((x+off)-(w/2.0))-Game.gexx-Game.globalx)*Game.square+width/2);
	}
	
	public int ydisp(int y, double off, double h){
		return (int)((((y+off)-(h/2.0))-Game.gexy-Game.globaly)*Game.square+height/2);
	}
	
}
