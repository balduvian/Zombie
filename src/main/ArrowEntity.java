package main;

public class ArrowEntity extends Entity{
	
	int arrowdir;
	
	public static final int UPARROW = 0;
	public static final int RIGHTARROW = 1;
	public static final int DOWNARROW = 2;
	public static final int LEFTARROW = 3;
	
	public static final int[] BROADCASTS = {Game.MOVEUP,Game.MOVERIGHT,Game.MOVEDOWN,Game.MOVELEFT};
			
	protected void initroutine(){
		active = true;
		id = Entity.IDARROW;
		img = new ImgSheet(new int[][][]{{{ImageLoader.ARROWUP0},{ImageLoader.ARROWUP1}},{{ImageLoader.ARROWRIGHT0},{ImageLoader.ARROWRIGHT1}},{{ImageLoader.ARROWDOWN0},{ImageLoader.ARROWDOWN1}},{{ImageLoader.ARROWLEFT0},{ImageLoader.ARROWLEFT1}}});
	}
	
	public void setarrowtype(int type){
		cast = BROADCASTS[type];
		arrowdir = type;
		img.mode = arrowdir;
	}
	
	protected void onclick(){
		Game.broadcast(cast);
	}
	
	public void tick(){
		super.tick();
		if(bstate >= HOVERSTATE){
			img.frame = 1;
		}else{
			img.frame = 0;
		}
	}
}
