package region;

import main.Block;
import main.ImageLoader;
import main.Region;
import main.Zombie;

public class VillageRegion extends Region{
	
	@Override
	protected void initroutine(){
		groundblock = Block.block(Block.GRASS,0);
		enemychances = new int[]{Zombie.BASICZOMBIEID,Zombie.BASICZOMBIEID,Zombie.BASICZOMBIEID,Zombie.HALFZOMBIEID,Zombie.SKELETONID};
		spawnchance = 0.07;
		levelchances = new int[]{0,0,0,0,1,2};
		lowbound = 0;
		highbound = 10;
		name = "Sleeping Village";
		color = new int[]{86, 173, 71};
		regionid = Region.VILLAGEID;
	}
	
}
