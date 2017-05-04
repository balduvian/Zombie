package zombie;

public class VillageRegion extends Region{
	
	public VillageRegion(){
		color = new int[]{86, 173, 71};
		enemychances = new int[]{Zombie.BASICZOMBIEID,Zombie.BASICZOMBIEID,Zombie.BASICZOMBIEID,Zombie.HALFZOMBIEID,Zombie.SKELETONID};
		levelchances = new int[]{0,0,0,0,1,2};
		spawnchances = new int[]{0,0,0,0,1,1,1,2};
		name = "Sleeping Village";
		lowbound = 0;
		setup();
	}
	
}
