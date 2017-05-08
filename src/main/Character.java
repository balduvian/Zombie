package main;

public class Character extends Entity{
	
	Stats stats;
	protected boolean friendly;
	
	boolean pre;
	int[] prebase;
	int defbase;
	
	protected String logname;
	
	public void init(int level){
		if(pre){
			stats = new Stats(level, prebase);
		}else{
			stats = new Stats(level, defbase);
		}
	}
}
