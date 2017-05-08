package main;

import java.awt.Color;

public class DroneEntity extends Entity{

	Color[] listc = {Color.RED,Color.BLUE,Color.GREEN,Color.WHITE,Color.BLACK,Color.CYAN};
	Color c;
	boolean immobile = true;
	
	public DroneEntity() {
		w = 1;
		h = 1;
		speed = 2;
		despawn = true;
		c = listc[(int)(Math.random()*6)];
	}
	
	public void tick(){
		super.tick();
		if(!immobile){
			if(mmode == 1){
				if(mdir==0){
					if(exy <= -1){
						mmode = 0;
						exy=0;
						y--;
					}else{
						exy-=movam();
					}
				}else if(mdir==1){
					if(exx >= 1){
						mmode = 0;
						exx=0;
						x++;
					}else{
						exx+=movam();
					}
				}else if(mdir==2){
					if(exy >= 1){
						mmode = 0;
						exy=0;
						y++;
					}else{
						exy+=movam();
					}
				}else if(mdir==3){
					if(exx <= -1){
						mmode = 0;
						exx=0;
						x--;
					}else{
						exx-=movam();
					}
				}
			}else{
				shift((int)(Math.random()*4));
			}
		}
	}

}
