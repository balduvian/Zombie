package region;

import main.Block;
import main.ImageLoader;
import main.Region;
import main.Zombie;

public class NullRegion extends Region{
	
	@Override
	protected void initroutine(){
		groundblock = Block.block(Block.WATER,0);
		enemychances = new int[]{Zombie.ELEMENTALID};
		levelchances = new int[]{20,21};
		spawnchance = 0.02;
		lowbound = -1;
		highbound = -1;
		name = "Why Are You Here?";
		color = new int[]{100,100,100};
		regionid = Region.NULLID;
	}
	
}
