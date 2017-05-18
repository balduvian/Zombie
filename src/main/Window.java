package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;

public class Window extends JFrame{
	private static final long serialVersionUID = 2645703612186155260L;
	
	int width;
	int height;
	int tilesup;
	int tilesacr;
	Kisen pressed;
	Misen moussed;
	Misen2 moussed2;
	BufferStrategy bb;
	Graphics g;
	ExecutorService exe;
	Rectangle[] buttonbounds;
	
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
								int[] lo = ldr(et.x,et.xx,et.y,et.yy,et.w,et.h);
								int[] la = et.img.getcurrent();
								for(int l=0;l<et.img.sheet[et.img.mode][et.img.frame].length;l++){
									//g.drawImage(Game.images[et.img.sheet[et.img.mode][et.img.frame][l]], lo[0],lo[1],lo[2],lo[3], null);
									g.drawImage(Game.images[la[l]], lo[0],lo[1],lo[2],lo[3], null);
								}
							}
						}
						
						//black border
						g.setColor(Color.BLACK);
						//int wid = width/2-(int)((7.5+Game.cax)*Game.square);
						g.fillRect(0,0,width/2-(int)((7.5+Game.cax)*Game.square),height);
						g.fillRect(0,0,width,height/2-(int)((7.5+Game.cay)*Game.square));
						g.fillRect(width/2+(int)((7.5-Game.cax)*Game.square),0,1000,height);
						g.fillRect(0,height/2+(int)((7.5-Game.cay)*Game.square),width,1000);
						
						g.setColor(Color.red);
						g.drawString(Game.globaly+" | "+Math.floor(Game.gexy*100)/100+" | "+Game.globalx+" | "+Math.floor(Game.gexx*100)/100+" | "+Game.world.seed, 20, 30);
						g.drawRect(width/2-2, height/2-2, 2, 2);
						
						int acr = (width/Game.square);
						for(int x=1;x<acr;x++){
							g.drawImage(Game.images[ImageLoader.GUIBKGU], x*Game.square,height-(2*Game.square),Game.square,Game.square,null);
							g.drawImage(Game.images[ImageLoader.GUIBKGD], x*Game.square,height-Game.square,Game.square,Game.square,null);
						}
						g.drawImage(Game.images[ImageLoader.GUIBKGUL],0,height-(2*Game.square),Game.square,Game.square,null);
						g.drawImage(Game.images[ImageLoader.GUIBKGDL],0,height-Game.square,Game.square,Game.square,null);
						g.drawImage(Game.images[ImageLoader.GUIBKGUR],width-Game.square,height-(2*Game.square),Game.square,Game.square,null);
						g.drawImage(Game.images[ImageLoader.GUIBKGDR],width-Game.square,height-Game.square,Game.square,Game.square,null);
						
						GUI gui = Game.gui;
						int bnum = gui.getbuttons();
						for(int i=0;i<bnum;i++){
							int wax = ((width/(bnum+1))*(i+1))-Game.square/2;
							int way = height-(int)(1.5*Game.square);
							
							gui.bounds[i] = new Rectangle(wax,way,Game.square,Game.square);
							
							GUIButton button = gui.buttons[gui.mode][i];
							
							int bimage = 0;
							if(button.inactive){
								bimage = ImageLoader.BUTTONINACTIVE;
							}else{
								if(button.hover){
									if(button.pressed){
										bimage = ImageLoader.BUTTONINPRESSED;
									}else{
										bimage = ImageLoader.BUTTONHOVER;
									}
								}else{
									bimage = ImageLoader.BUTTONACTIVE;
								}
							}
							g.drawImage(Game.images[bimage], wax, way, Game.square, Game.square, null);
							g.drawImage(Game.images[gui.buttons[gui.mode][i].img], wax, way, Game.square, Game.square, null);
						}
						
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
		
		pressed = new Kisen();
		addKeyListener(pressed);
		moussed = new Misen();
		addMouseListener(moussed);
		moussed2 = new Misen2();
		addMouseMotionListener(moussed2);
		
		setTitle("Zombie");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		createBufferStrategy(2);
		bb = getBufferStrategy();
		exe = Executors.newFixedThreadPool(1);
		exe.execute(new PaintRun());
		requestFocus();
	}
	
	public class Misen implements MouseListener{
		boolean mdown = false;
		public void mouseClicked(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
			mdown = true;
		}
		public void mouseReleased(MouseEvent e) {
			mdown = false;
		}
	}
	
	public class Misen2 implements MouseMotionListener{
		public int mx = 0;
		public int my = 0;
		public void mouseDragged(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
		}
		public void mouseMoved(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
		}	
	}
	
	public class Kisen implements KeyListener{
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
