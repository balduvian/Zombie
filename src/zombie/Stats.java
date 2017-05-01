package zombie;

public class Stats {
	
	public static final int BASETOTAL = 25;
	public static final int NUMSTATS = 5;
	public static final int NUMBARS = 2;
	String type;
	String name;
	int level;
	int[] bases = new int[NUMSTATS];
	//BODY
	//MIND
	//ATTACK
	//DEFENSE
	//SPEED
	int[] stats = new int[NUMSTATS];
	int[] trained = new int[NUMSTATS];
	int[] bars = new int[NUMBARS];
	//HP
	//SANITY
	int[] bmax = new int[NUMBARS];//maxes for the bars
	Weapon weapon;
	//Armor armor;   //LATER
	int imgid;
	int[] abs;//abilities
	
	public void makestats(){//update the actual stats
		for(int i=0;i<NUMSTATS;i++){
			stats[i] = (bases[i]+level+trained[i]);
		}
	}
	
	public void makebars(){//update the bars and maxes
		for(int i=0;i<NUMBARS;i++){
			double oldb = bmax[i];
			bmax[i] = stats[i]*5;
			double crat = bmax[i]/oldb;
			bars[i] = (int)(bars[i]*crat);
		}
		
	}
	public void createbars(){//used only for initializing bars
		for(int i=0;i<NUMBARS;i++){
			bmax[i] = stats[i]*5;
			bars[i] = bmax[i];
		}
	}
	
	public Stats(int level){
		int rem = BASETOTAL;
		while(rem!=0){
			bases[(int)(Math.random()*NUMSTATS)] += 1;
			rem--;
		}
		makestats();
		createbars();
	}
	
	public void updatestats(){
		makestats();
		makebars();
	}
}
