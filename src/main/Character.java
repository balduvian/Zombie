package main;

public class Character extends Entity{
	
	public final static int SURVIVORCONSTANT = -1;
	
	Stats stats;
	protected boolean friendly;
	
	protected boolean pre;
	protected int[] prebase;
	int defbase;
	
	protected String logname;
	
	protected void initroutine(){
		charinit();
	}
	
	//to override
	protected void charinit(){}
	
	public void setlevel(int level){
		if(pre){
			stats = new Stats(level, prebase);
		}else{
			stats = new Stats(level, defbase);
		}
	}
}
