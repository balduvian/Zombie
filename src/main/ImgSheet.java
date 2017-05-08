package main;

public class ImgSheet {
	boolean anim;
	boolean randstart;
	int frames;
	int layers;
	
	int frametime;
	int timer;
	int vari;
	
	int frame;
	int[][] sheet;
	
	public ImgSheet(int[][] images, int time, int variation){
		sheet = images.clone();
		frametime=time;
		vari = variation;
		anim = true;
		randstart = true;
		baseops();			
	}
	
	public ImgSheet(int[][] images){
		sheet = images.clone();
		baseops();
	}
	
	public ImgSheet(int[][] images, boolean rs){
		randstart = rs;
		sheet = images.clone();
		baseops();
	}
	
	public int makeac(){
		return frametime + (int)(Math.random()*(vari*2)-vari);
	}
	
	public int randframe(){
		return (int)(Math.random()*frames);
	}
	
	private void baseops(){
		frame = randframe();
		frames = sheet.length;
		layers = sheet[0].length;
		timer = makeac();
		if(randstart){
			frame = (int)(Math.random()*frames);
		}
	}
}
