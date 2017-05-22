package main;

public class GUIButton {
	
	public static final int INACTIVESTATE = 0;
	public static final int ACTIVESTATE = 1;
	public static final int HOVERSTATE = 2;
	public static final int PRESSEDSTATE = 3;
	public static final int[] bbacks = {ImageLoader.BUTTONINACTIVE,ImageLoader.BUTTONACTIVE,ImageLoader.BUTTONHOVER,ImageLoader.BUTTONINPRESSED};
	boolean locked;
	boolean acclock;
	boolean accepting;
	boolean pressed;
	//boolean hover;//doweneedit???
	boolean inactive;
	int bstate = 0;
	int id;
	String name;
	int img;
	int width = 16;
	int height = 16;
	int tocast;
	
	public GUIButton(GUIButton i){
		name = i.name;
		id = i.id;
		img = i.img;
		tocast = i.tocast;
	}
	
	public GUIButton(String bname, int triggerid, int image, int broadcasting){
		name = bname;
		id = triggerid;
		img = image;
		tocast = broadcasting;
	}
	
	public void onclick(){
		Game.broadcast(tocast);
	}
}
