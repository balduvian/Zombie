package main;

import java.awt.Rectangle;

public class GUI {
	
	public static final int MAIN = 0;
	public static final int MOVEMENT = 1;
	public static final int ATTACKING = 2;
	public static final int ACTION = 3;
	public static final int ENEMYTURN = 4;
	
	public static final GUIButton MOVEBUTTON = new GUIButton("Move",0,ImageLoader.GUIMOVE,Game.GUIGOMOVE);
	public static final GUIButton ATTACKBUTTON = new GUIButton("Attack",1,ImageLoader.GUIATTACK,Game.GUIGOATTACK);
	public static final GUIButton ACTIONBUTTON = new GUIButton("Action",2,ImageLoader.GUIACTION,Game.GUIGOACTION);
	public static final GUIButton STOPBUTTON = new GUIButton("Stop",3,ImageLoader.GUISTOP,Game.ADVANCEPARTYTURN);
	public static final GUIButton MENUBUTTON = new GUIButton("Menu",4,ImageLoader.GUIMENU,Game.GUIOPENMENU);
	public static final GUIButton BACKBUTTON = new GUIButton("Back",5,ImageLoader.GUIBACK,Game.GUIGOMAIN);
	
	int mode = 0;
	
	GUIButton[][] buttons= {
			{new GUIButton(MOVEBUTTON),new GUIButton(ATTACKBUTTON),new GUIButton(ACTIONBUTTON),new GUIButton(STOPBUTTON),new GUIButton(MENUBUTTON)},//main
			{new GUIButton(BACKBUTTON)},//movement
			{new GUIButton(BACKBUTTON)},//attacking 
			{new GUIButton(BACKBUTTON)},//action
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
					if(!Game.window.moussed.mdown && !buttons[mode][i].accepting){
						buttons[mode][i].locked = true;
						buttons[mode][i].accepting = true;
					}else if(!buttons[mode][i].locked){
						buttons[mode][i].accepting = false;
					}
					if(buttons[mode][i].accepting && Game.window.moussed.mdown){
						buttons[mode][i].pressed = true;
						buttons[mode][i].onclick();
					}else{
						buttons[mode][i].pressed = false;
					}
				}else{
					buttons[mode][i].hover = false;
					buttons[mode][i].pressed = false;
					buttons[mode][i].accepting = false;
					buttons[mode][i].locked = false;
				}
			}
		}catch(Exception ex){}
	}
	
	public void setmode(int m){
		mode = m;
		int thro = getbuttons();
		for(int i=0;i<thro;i++){
			buttons[mode][i].hover = false;
			buttons[mode][i].pressed = false;
			buttons[mode][i].accepting = false;
			buttons[mode][i].locked = false;
		}
	}
	
}
