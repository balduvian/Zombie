package main;

import java.awt.Rectangle;

public class GUI {
	
	public static final int MAINBUTTONS = 6;
	
	public static final int MAIN = 0;
	public static final int MOVEMENT = 1;
	public static final int ATTACKING = 2;
	public static final int ACTION = 3;
	public static final int ENEMYTURN = 4;
	
	public static final int MAINMOVE = 0;
	public static final int MAINATTACK = 1;
	public static final int MAINACTION = 2;
	public static final int MAINSTOP = 3;
	public static final int MAINBAG = 4;
	
	public static final GUIButton MOVEBUTTON = new GUIButton("Move",ImageLoader.GUIMOVE,Game.GUIGOMOVE);
	public static final GUIButton ATTACKBUTTON = new GUIButton("Attack",ImageLoader.GUIATTACK,Game.GUIGOATTACK);
	public static final GUIButton ACTIONBUTTON = new GUIButton("Action",ImageLoader.GUIACTION,Game.GUIGOACTION);
	public static final GUIButton STOPBUTTON = new GUIButton("Stop",ImageLoader.GUISTOP,Game.ADVANCEPARTYTURN);
	public static final GUIButton BAGBUTTON = new GUIButton("Bag",ImageLoader.GUIBAG,Game.GUIOPENBAG);
	public static final GUIButton BACKBUTTON = new GUIButton("Back",ImageLoader.GUIBACK,Game.GUIGOMAIN);
	
	int mode = 0;
	
	GUIButton[][] buttons= {
			{new GUIButton(MOVEBUTTON),new GUIButton(ATTACKBUTTON),new GUIButton(ACTIONBUTTON),new GUIButton(STOPBUTTON),new GUIButton(BAGBUTTON)},//main
			{new GUIButton(BACKBUTTON)},//movement
			{new GUIButton(BACKBUTTON)},//attacking 
			{new GUIButton(BACKBUTTON)},//action
			{}//enemy turn
	};
	
	Rectangle[] bounds = new Rectangle[getbuttons()];
	
	public int getbuttons(){
		return buttons[mode].length;
	}
	
	public GUI(){
		mode = MAIN;
	}
	
	public void enableall(){
		for(int i=0;i<MAINBUTTONS;i++){
			buttons[MAIN][0].inactive = false;
		}
	}
	
	public void tick(){
		Rectangle mr = new Rectangle(Game.window.moussed2.mx,Game.window.moussed2.my,1,1);
		int thro = getbuttons();
		try{
			for(int i=0;i<thro;i++){
				
				GUIButton bu = buttons[mode][i];
				boolean md = Game.window.moussed.mdown;
				boolean on = mr.intersects(bounds[i]);
				if(!bu.inactive){
					if(on || bu.locked){
						if(bu.locked){
							if(on){
								bu.bstate = GUIButton.PRESSEDSTATE;
								if(!md){
									bu.locked = false;
									bu.onclick();
								}
							}else{
								bu.bstate = GUIButton.HOVERSTATE;
							}
						}else{
							bu.bstate = GUIButton.HOVERSTATE;
						}
						if(!md && !bu.accepting){
							bu.accepting = true;
							bu.acclock = true;
						}else{
							bu.accepting = false;
						}
						if(md && bu.acclock){
							bu.locked = true;
						}else{
							bu.locked = false;
						}
					}else{
						bu.acclock = false;
						bu.bstate = GUIButton.ACTIVESTATE;
						bu.locked = false;
						bu.accepting = false;
					}
				}else{
					bu.bstate = GUIButton.INACTIVESTATE;
				}
			}
		}catch(Exception ex){}
	}
	
	public void setmode(int m){
		mode = m;
	}
	
}
