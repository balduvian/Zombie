package main;

public class ImgSheet {
	boolean anim;
	
	int modes;
	
	int frametime;
	int timer;
	int vari;
	
	int mode;
	int frame;
	int[][][] sheet;
	
	public ImgSheet(int[][][] images, int time, int variation){
		sheet = images;
		frametime = time;
		vari = variation;
		anim = true;
		baseops();			
	}
	public ImgSheet(int[][][] images, int time){
		sheet = images;
		frametime = time;
		vari = 0;
		anim = true;
		baseops();			
	}
	public ImgSheet(int[][][] images){
		sheet = images;
		baseops();			
	}
	
	public int makeac(){
		return frametime + (int)(Math.random()*(vari*2)-vari);
	}
	
	public int randframe(){
		return (int)(Math.random()*sheet[mode].length);
	}
	
	private void baseops(){
		modes =sheet.length;
		timer = makeac();
		frame = randframe();
	}
}
