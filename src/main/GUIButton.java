package main;

public class GUIButton {
	
	public static final int INACTIVESTATE = 2;
	public static final int ACTIVESTATE = 0;
	public static final int HOVERSTATE = 1;
	public static final int PRESSEDSTATE = 3;
	public static final int[] bbacks = {ImageLoader.BUTTONINACTIVE,ImageLoader.BUTTONACTIVE,ImageLoader.BUTTONHOVER,ImageLoader.BUTTONINPRESSED};
	boolean locked;
	boolean acclock;
	boolean accepting;
	boolean pressed;
	//boolean hover;//doweneedit???
	boolean inactive;
	int bstate = 0;
	String name;
	//int img;
	ImgSheet img;
	int width = 16;
	int height = 16;
	int tocast;
	
	public GUIButton(GUIButton i){
		name = i.name;
		img = i.img;
		tocast = i.tocast;
	}
	
	public GUIButton(String bname, int image, int broadcasting){
		name = bname;
		img = new ImgSheet(new int[][][]{{{ImageLoader.BUTTONACTIVE,image}},{{ImageLoader.BUTTONHOVER,image}},{{ImageLoader.BUTTONINACTIVE,image}},{{ImageLoader.BUTTONINPRESSED,image}}});
		tocast = broadcasting;
	}
	
	public void onclick(){
		Game.broadcast(tocast);
	}
}
