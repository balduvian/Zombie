package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

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
	
	public void setrender(int winw, int winh, double pfac, int ts){
		tiles = ts;
		width = winw;
		height = winh;
		pixelw = (int)(winw*pfac);
		pixelh = (int)(winh*pfac);
		setSize(width,height);
	}
	
	public void render(){
		//do {
			try{
				g = bb.getDrawGraphics();
				if(Game.activity==1){
					//try{
						World wo = Game.world;
						for(int z=0;z<wo.ssize;z++){
							for(int y=0;y<wo.wsize;y++){//draw chunk
								for(int x=0;x<wo.wsize;x++){
									int acx = ((x+wo.offx-wo.rb));
									int acy = ((y+wo.offy-wo.rb));

									int[] lo = ldr(acx,0,acy,0,1,1);
									g.fillRect(lo[0],lo[1],lo[2],lo[3]);
									
									ImgSheet tlo = wo.world[y][x][z].img;
									tlo.tick();
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
								for(int l=0;l<et.imgs.sheet[et.imgs.mode][et.imgs.frame].length;l++){
									g.drawImage(Game.images[et.imgs.sheet[et.imgs.mode][et.imgs.frame][l]], lo[0],lo[1],lo[2],lo[3], null);
								}
							}
						}
						g.setColor(Color.red);
						g.drawString(Game.globaly+" | "+Math.floor(Game.gexy*100)/100+" | "+Game.globalx+" | "+Math.floor(Game.gexx*100)/100+" | "+Game.world.seed, 20, 30);
						g.drawRect(width/2-2, height/2-2, 2, 2);
					//}catch(Exception ex){}
					}else{
						//OH YEAH MR KRABS
					}
			}finally{
				g.dispose();
			}
			bb.show();
			
		//} while (bb.contentsLost());
	}
	
	public Window(){
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		setrender(ss.width-200,ss.height-200,1.0,Game.square);
		this.setBounds(50, 50, width+50, height+50);
		setUndecorated(true);
		pressed = new Misen();
		addKeyListener(pressed);
		setTitle("Zombie");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		createBufferStrategy(2);
		bb = getBufferStrategy();
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
		return (int)((((x+off)-(w/2.0))-Game.gexx-Game.globalx)*Game.square+width/2);
	}
	
	public int ydisp(int y, double off, double h){
		return (int)((((y+off)-(h/2.0))-Game.gexy-Game.globaly)*Game.square+height/2);
	}
	
}
