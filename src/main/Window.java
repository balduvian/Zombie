package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

public class Window extends JFrame{
	private static final long serialVersionUID = 2645703612186155260L;
	
	int width;
	int height;
	int cdy;
	int cdx;
	Misen pressed;
	BufferStrategy bb;
	Graphics g;
	ExecutorService exe;
	
	public void render(){
		do {
			requestFocus();
			try{
				g = bb.getDrawGraphics();
				if(Game.activity==1){
					try{
						World wo = Game.world;
						for(int z=0;z<wo.ssize;z++){
							for(int y=0;y<wo.wsize;y++){//draw chunk
								for(int x=0;x<wo.wsize;x++){
									int acx = ((x+wo.offx-wo.rb));
									int acy = ((y+wo.offy-wo.rb));

									int[] lo = ldr(acx,0,acy,0,1,1);
									g.fillRect(lo[0],lo[1],lo[2],lo[3]);
									
									ImgSheet tlo = wo.world[y][x][z].img;
									//tlo.tick();
									if(tlo.anim){
										tlo.frame = (Block.empire/tlo.frametime)%tlo.getframes();
									}
									int nay = tlo.getlayers();
									int[] nua = tlo.getcurrent();
									for(int l=0;l<nay;l++){
										g.drawImage(Game.images[nua[l]],lo[0],lo[1],lo[2],lo[3],null);
									}

								}
							}
						}
						for(int c=0;c<Game.enumm;c++){
							Entity et = Game.entities[c];
							if(et!=null){
								int[] lo = ldr(et.x,et.exx,et.y,et.exy,et.w,et.h);
								int[] la = et.img.getcurrent();
								for(int l=0;l<et.img.sheet[et.img.mode][et.img.frame].length;l++){
									//g.drawImage(Game.images[et.img.sheet[et.img.mode][et.img.frame][l]], lo[0],lo[1],lo[2],lo[3], null);
									g.drawImage(Game.images[la[l]], lo[0],lo[1],lo[2],lo[3], null);
								}
							}
						}
						
						//black border
						g.setColor(Color.BLACK);
						g.fillRect(0,0,width/2-(int)((8+Game.cax)*Game.square),height);
						g.fillRect(width/2+(int)((8+Game.cax)*Game.square),0,width/2-(int)((8-Game.cax)*Game.square),height);
						
						
						
						g.setColor(Color.red);
						g.drawString(Game.globaly+" | "+Math.floor(Game.gexy*100)/100+" | "+Game.globalx+" | "+Math.floor(Game.gexx*100)/100+" | "+Game.world.seed, 20, 30);
						g.drawRect(width/2-2, height/2-2, 2, 2);
					}catch(Exception ex){
						ex.printStackTrace();
					}
					}else{
						//OH YEAH MR KRABS
					}
			}finally{
				g.dispose();
			}
			bb.show();
			
		} while (bb.contentsLost());
	}
	
	public class PaintRun implements Runnable{
		public void run() {
			while(true){
				render();
			}
		}
	}
	
	public Window(){
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		width = ss.width;
		height = ss.height;
		this.setBounds(0, 0, width, height);
		setUndecorated(true);
		setResizable(true);
		pressed = new Misen();
		addKeyListener(pressed);
		setTitle("Zombie");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		createBufferStrategy(2);
		bb = getBufferStrategy();
		exe = Executors.newFixedThreadPool(1);
		exe.execute(new PaintRun());
		requestFocus();
	}
	
	public class Misen implements KeyListener{
		boolean[] keys = new boolean[100];
		public void keyPressed(KeyEvent ex){
			int y = ex.getKeyCode();
			if(y<100){
				keys[y] = true;
			}
		}
		public void keyReleased(KeyEvent ex){
			int y = ex.getKeyCode();
			if(y<100){
				keys[y] = false;
			}
		}
		public void keyTyped(KeyEvent arg0){}
	}
	
	public int[] ldr(int x, double xx, int y, double yy, double w, double h){
		int d1 = xdisp(x,xx,w);
		int d2 = ydisp(y,yy,h);
		int d3 = (int)(w*Game.square);
		int d4 = (int)(h*Game.square);
		return new int[]{d1,d2,d3,d4};
	}
	
	public int xdisp(int x, double off, double w){
		return (int)((((x+off)-(w/2.0))-Game.gexx-Game.globalx-Game.cax)*Game.square+width/2);
	}
	
	public int ydisp(int y, double off, double h){
		return (int)((((y+off)-(h/2.0))-Game.gexy-Game.globaly-Game.cay)*Game.square+height/2);
	}
	
}
