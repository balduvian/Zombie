package block;

import main.Block;
import main.ImageLoader;
import main.ImgSheet;

public class SandstoneBlock extends Block{

	public SandstoneBlock(){
		blockid = Block.SANDSTONE;
		img = new ImgSheet(new int[][][]{{{ImageLoader.SANDSTONE}}});
	}

}
