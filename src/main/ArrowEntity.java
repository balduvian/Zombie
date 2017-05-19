package main;

public class ArrowEntity extends Entity{
	
	int arrowdir;
	
	public static final int UPARROW = 0;
	public static final int RIGHTARROW = 1;
	public static final int DOWNARROW = 2;
	public static final int LEFTARROW = 3;
	
	public static final int[] ARROWIMAGES = {ImageLoader.ARROWUP,ImageLoader.ARROWRIGHT,ImageLoader.ARROWDOWN,ImageLoader.ARROWLEFT};
	public static final int[] BROADCASTS = {Game.MOVEUP,Game.MOVERIGHT,Game.MOVEDOWN,Game.MOVELEFT};
			
	public void initroutine(){
		id = Entity.IDARROW;
		img = new ImgSheet(new int[][][]{{{ARROWIMAGES[UPARROW]}},{{ARROWIMAGES[RIGHTARROW]}},{{ARROWIMAGES[DOWNARROW]}},{{ARROWIMAGES[LEFTARROW]}}});
	}
	
	public void setarrowtype(int type){
		arrowdir = type;
		img.mode = arrowdir;
	}
}
