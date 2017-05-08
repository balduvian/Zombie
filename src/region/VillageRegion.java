package region;

import main.ImageLoader;
import main.Region;
import zombie.Zombie;

public class VillageRegion extends Region{
	
	public VillageRegion(){
		
		enemychances = new int[]{Zombie.BASICZOMBIEID,Zombie.BASICZOMBIEID,Zombie.BASICZOMBIEID,Zombie.HALFZOMBIEID,Zombie.SKELETONID};
		spawnchances = new int[]{0,0,0,0,1,1,1,2};
		levelchances = new int[]{0,0,0,0,1,2};
		structures = new int[][][]{
			{
				{0,0,0,0,0,0,0,0},
				{0,1,1,2,2,1,1,0},
				{0,1,2,2,2,2,1,0},
				{0,1,2,2,2,2,1,0},
				{0,1,2,2,2,2,1,0},
				{0,1,2,2,2,2,1,0},
				{0,1,1,1,1,1,1,0},
				{0,0,0,0,0,0,0,0},
			},
			{
				{0,0,0,2,2,0,0,0},
				{0,1,2,2,2,2,1,0},
				{0,2,2,2,2,2,2,0},
				{2,2,2,1,1,2,2,2},
				{2,2,2,1,1,2,2,2},
				{0,2,2,2,2,2,2,0},
				{0,1,2,2,2,2,1,0},
				{0,0,0,2,2,0,0,0},
			},
		};
		lowbound = 0;
		highbound = 10;
		name = "Sleeping Village";
		color = new int[]{86, 173, 71};
		regionid = Region.VILLAGEID;
		setup();
	}
	
}
