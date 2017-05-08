package block;

import main.Block;
import main.ImageLoader;
import main.ImgSheet;

public class WoodBlock extends Block{

	public WoodBlock(){
		blockid = Block.WOOD;
		image = new ImgSheet(new int[][][]{{{ImageLoader.WOODPLANKS}}});
	}

}
