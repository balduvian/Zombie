package block;

import main.Block;
import main.ImageLoader;
import main.ImgSheet;

public class WoodfloorBlock extends Block{

	public WoodfloorBlock(){
		blockid = Block.WOODFLOOR;
		img = new ImgSheet(new int[][][]{{{ImageLoader.WOODFLOOR}}});
		walk= true;
	}

}
