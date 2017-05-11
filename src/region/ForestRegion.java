package region;

import main.Block;
import main.ImageLoader;
import main.Region;
import main.Zombie;

public class ForestRegion extends Region{
	
	@Override
	protected void initroutine(){
		groundblock = Block.block(Block.GRASS,1);
		enemychances = new int[]{Zombie.BASICZOMBIEID,Zombie.SKELETONID,Zombie.MUSCLEZOMBIEID,Zombie.HALFZOMBIEID,Zombie.SKELETONID};
		levelchances = new int[]{1,1,1,1,2,3};
		spawnchance = 0.02;
		lowbound = 6;
		highbound = 20;
		name = "Silent Forest";
		color = new int[]{16, 102, 12};
		regionid = Region.FORESTID;
	}
	
}
