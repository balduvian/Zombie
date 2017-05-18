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
	int destin;
	boolean link;
	
	public GUIButton(GUIButton i){
		destin = i.destin;
		name = i.name;
		id = i.id;
		img = i.img;
		link = i.link;
	}
	
	public GUIButton(String bname, int triggerid, int image, int destination){
		destin = destination;
		name = bname;
		id = triggerid;
		img = image;
		link = true;
	}
	
	public void onclick(){
		if(link){
			Game.gui.setmode(destin);
		}
	}
}
