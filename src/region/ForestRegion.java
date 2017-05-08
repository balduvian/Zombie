package region;

import main.ImageLoader;
import main.Region;
import main.Zombie;

public class ForestRegion extends Region{
	
	@Override
	protected void initroutine(){
		enemychances = new int[]{Zombie.BASICZOMBIEID,Zombie.SKELETONID,Zombie.MUSCLEZOMBIEID,Zombie.HALFZOMBIEID,Zombie.SKELETONID};
		spawnchance = 0.07;
		levelchances = new int[]{1,1,1,1,2,3};
		lowbound = 10;
		highbound = 20;
		name = "Silent Forest";
		color = new int[]{16, 102, 12};
		regionid = Region.FORESTID;
	}
	
}
