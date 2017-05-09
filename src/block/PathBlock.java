package block;

import main.Block;
import main.ImageLoader;
import main.ImgSheet;

public class PathBlock extends Block{

	public PathBlock(){
		blockid = Block.PATH;
		img = new ImgSheet(new int[][][]{{{ImageLoader.PATH}}});
		walk = true;
	}

}
