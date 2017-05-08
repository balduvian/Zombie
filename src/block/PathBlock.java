package block;

import main.Block;
import main.ImageLoader;
import main.ImgSheet;

public class PathBlock extends Block{

	public PathBlock(){
		blockid = Block.PATH;
		image = new ImgSheet(new int[][]{{ImageLoader.PATH}});
		solid = true;
	}

}
