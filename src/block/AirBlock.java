package block;

import main.Block;
import main.ImgSheet;

public class AirBlock extends Block{

	public AirBlock(){
		blockid = Block.AIR;
		img = new ImgSheet(new int[][][]{{{}}});
		passable = true;
	}

}
