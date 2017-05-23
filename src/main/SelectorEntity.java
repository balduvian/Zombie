package main;

public class SelectorEntity extends Entity{
	
	boolean sizeup = false;
	public static final double smin = 1.1;
	public static final double smax = 1.4;
	
	protected void initroutine(){
		img = new ImgSheet(new int[][][]{{{ImageLoader.SELECTOR0},{ImageLoader.SELECTOR1},{ImageLoader.SELECTOR2},{ImageLoader.SELECTOR3},{ImageLoader.SELECTOR4},{ImageLoader.SELECTOR5},{ImageLoader.SELECTOR6},{ImageLoader.SELECTOR7}},{{ImageLoader.SELECTORNULL}}},15);
		id = Entity.IDSELECTOR;
		h = smin;
		w = smin;
	}
	
	public void tick(){
		super.tick();
		int mx =  Game.window.moussed2.mx;
		int my =  Game.window.moussed2.my;
		x = (int)Math.round(((mx-Game.window.width/2.0)/Game.square)+Game.globalx+Game.gexx+Game.cax);
		//xx = (((mx-Game.window.width/2.0)/Game.square)+Game.globalx+Game.gexx+Game.cax)%1;
		y = (int)Math.round(((my-Game.window.height/2.0)/Game.square)+Game.globaly+Game.gexy+Game.cay);
		//yy = (((my-Game.window.height/2.0)/Game.square)+Game.globaly+Game.gexy+Game.cay)%1;
		System.out.println(x+" "+y);
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
