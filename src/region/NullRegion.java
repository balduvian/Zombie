package region;

import main.ImageLoader;
import main.Region;
import main.Zombie;

public class NullRegion extends Region{
	
	@Override
	protected void initroutine(){
		enemychances = new int[]{Zombie.ELEMENTALID};
		levelchances = new int[]{20,21};
		lowbound = 0;
		highbound = 0;
		name = "why are you here?";
		color = new int[]{100,100,100};
		regionid = Region.NULLID;
	}
	
}
