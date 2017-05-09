package block;

import main.Block;
import main.ImageLoader;
import main.ImgSheet;

public class GrassBlock extends Block{

	public GrassBlock(){
		blockid = Block.GRASS;
		img = new ImgSheet(new int[][][]{{{ImageLoader.GRASS}},{{ImageLoader.FORESTGRASS}},{{ImageLoader.SWAMPGRASS}}});
	}

}
