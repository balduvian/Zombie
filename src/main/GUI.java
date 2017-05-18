package main;

import java.awt.Rectangle;

public class GUI {
	
	public static final GUIButton MOVEBUTTON = new GUIButton("Move",0,ImageLoader.GUIMOVE,1);
	public static final GUIButton ATTACKBUTTON = new GUIButton("Attack",1,ImageLoader.GUIATTACK,2);
	public static final GUIButton ACTIONBUTTON = new GUIButton("Action",2,ImageLoader.GUIACTION,3);
	public static final GUIButton STOPBUTTON = new GUIButton("Stop",3,ImageLoader.GUISTOP,4);
	public static final GUIButton MENUBUTTON = new GUIButton("Menu",4,ImageLoader.GUIMENU,0);
	public static final GUIButton BACKBUTTON = new GUIButton("Back",5,ImageLoader.GUIBACK,0);
	
	int mode = 0;
	
	GUIButton[][] buttons= {
			{new GUIButton(MOVEBUTTON),new GUIButton(ATTACKBUTTON),new GUIButton(ACTIONBUTTON),new GUIButton(STOPBUTTON),new GUIButton(MENUBUTTON)},
			{new GUIButton(BACKBUTTON)},
			{new GUIButton(BACKBUTTON)},
			{new GUIButton(BACKBUTTON)},
			{}//enemy turn
	};
	
	Rectangle[] bounds = new Rectangle[getbuttons()];
	
	public int getbuttons(){
		return buttons[mode].length;
	}
	
	public GUI(){}//eh tho... whatever
	
	public void tick(){
		Rectangle mr = new Rectangle(Game.window.moussed2.mx,Game.window.moussed2.my,1,1);
		int thro = getbuttons();
		try{
			for(int i=0;i<thro;i++){
				if(mr.intersects(bounds[i])){
					buttons[mode][i].hover = true;
					if(Game.window.moussed.mdown){
						buttons[mode][i].pressed = true;
					}else{
						buttons[mode][i].pressed = false;
					}
				}else{
					buttons[mode][i].hover = false;
					buttons[mode][i].pressed = false;
				}
			}
		}catch(Exception ex){}
	}
	
	public void setmode(int m){
		mode = m;
	}
	
}
