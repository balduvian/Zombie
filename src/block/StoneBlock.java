package block;

import main.Block;
import main.ImageLoader;
import main.ImgSheet;

public class StoneBlock extends Block{

	public StoneBlock(){
		blockid = Block.STONE;
		image = new ImgSheet(new int[][]{{ImageLoader.STONE},{ImageLoader.MOSSSTONE}});
		solid = true;
	}

}
