package main;

public class SelectorEntity extends Entity{
	
	boolean sizeup = false;
	public static final double smin = 1.1;
	public static final double smax = 1.4;
	
	protected void initroutine(){
		img = new ImgSheet(new int[][][]{{{ImageLoader.SELECTOR00},{ImageLoader.SELECTOR01},{ImageLoader.SELECTOR02},{ImageLoader.SELECTOR03},{ImageLoader.SELECTOR04},{ImageLoader.SELECTOR05},{ImageLoader.SELECTOR06},{ImageLoader.SELECTOR07}},{{ImageLoader.SELECTOR10},{ImageLoader.SELECTOR11},{ImageLoader.SELECTOR12},{ImageLoader.SELECTOR13},{ImageLoader.SELECTOR14},{ImageLoader.SELECTOR15},{ImageLoader.SELECTOR16},{ImageLoader.SELECTOR17}}},15);
		id = Entity.IDSELECTOR;
		h = smin;
		w = smin;
		img.mode = Game.selectormode;
	}
	
	public void tick(){
		super.tick();
		int mx =  Game.window.moussed2.mx;
		int my =  Game.window.moussed2.my;
		x = (int)Math.round(((mx-Game.window.width/2.0)/Game.square)+Game.globalx+Game.globalxx+Game.cax);
		//xx = (((mx-Game.window.width/2.0)/Game.square)+Game.globalx+Game.gexx+Game.cax)%1;
		y = (int)Math.round(((my-Game.window.height/2.0)/Game.square)+Game.globaly+Game.globalyy+Game.cay);
		//yy = (((my-Game.window.height/2.0)/Game.square)+Game.globaly+Game.gexy+Game.cay)%1;
		if(sizeup){
			h+=0.02;
			w+=0.02;
			if(h>=smax){
				sizeup=false;
			}
		}else{
			h-=0.02;
			w-=0.02;
			if(h<=smin){
				sizeup=true;
			}
		}
	}
}
