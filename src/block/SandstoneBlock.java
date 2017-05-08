package block;

import main.Block;
import main.ImageLoader;
import main.ImgSheet;

public class SandstoneBlock extends Block{

	public SandstoneBlock(){
		blockid = Block.SANDSTONE;
		image = new ImgSheet(new int[][]{{ImageLoader.SANDSTONE}});
		solid = true;
	}

}
