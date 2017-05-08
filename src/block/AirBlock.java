package block;

import main.Block;
import main.ImgSheet;

public class AirBlock extends Block{

	public AirBlock(){
		blockid = Block.AIR;
		image = new ImgSheet(new int[][][]{{{}}});
		passable = true;
	}

}
