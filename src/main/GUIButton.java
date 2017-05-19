package main;

public class GUIButton {
	
	boolean locked;
	boolean accepting;
	boolean pressed;
	boolean hover;
	boolean inactive;
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
