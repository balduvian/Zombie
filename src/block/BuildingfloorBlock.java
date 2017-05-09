package block;

import main.Block;
import main.ImageLoader;
import main.ImgSheet;

public class BuildingfloorBlock extends Block{

	public BuildingfloorBlock(){
		blockid = Block.BUILDINGFLOOR;
		img = new ImgSheet(new int[][][]{{{ImageLoader.BUILDINGFLOOR}}});
		walk = true;
	}

}
