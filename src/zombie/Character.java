package zombie;

public class Character extends Entity{
	
	Stats stats;
	boolean friendly;
	
	boolean pre;
	int[] prebase;
	int defbase;
	
	String logname;
	
	public void init(int level){
		if(pre){
			stats = new Stats(level, prebase);
		}else{
			stats = new Stats(level, defbase);
		}
	}
}
