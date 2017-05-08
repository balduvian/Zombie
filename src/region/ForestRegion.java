package region;

import main.ImageLoader;
import main.Region;
import zombie.Zombie;

public class ForestRegion extends Region{
	
	public ForestRegion(){
		
		enemychances = new int[]{Zombie.BASICZOMBIEID,Zombie.SKELETONID,Zombie.MUSCLEZOMBIEID,Zombie.HALFZOMBIEID,Zombie.SKELETONID};
		spawnchances = new int[]{0,0,0,0,0,1,1,1,2,2};
		levelchances = new int[]{1,1,1,1,2,3};
		structures = new int[][][]{
			{
				{3,3,3,3,3,3,3,3},
				{0,2,2,2,2,2,2,0},
				{0,2,2,2,2,2,2,0},
				{0,2,2,1,1,2,2,0},
				{0,2,2,1,1,2,2,0},
				{0,2,2,2,2,2,2,0},
				{0,2,2,2,2,2,2,0},
				{3,3,3,3,3,3,3,3},
			},
			{
				{0,0,0,2,1,0,0,0},
				{0,2,2,2,1,2,2,0},
				{0,2,2,2,1,2,2,0},
				{1,1,1,3,3,2,2,2},
				{2,2,2,3,3,1,1,1},
				{0,2,2,1,2,2,2,0},
				{0,2,2,1,2,2,2,0},
				{0,0,0,1,2,0,0,0},
			},
		};
		lowbound = 10;
		highbound = 20;
		name = "Silent Forest";
		color = new int[]{16, 102, 12};
		regionid = Region.FORESTID;
		setup();
	}
	
}
