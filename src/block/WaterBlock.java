package block;

import main.Block;
import main.ImageLoader;
import main.ImgSheet;

public class WaterBlock extends Block{

	public WaterBlock(){
		blockid = Block.WATER;
		image = new ImgSheet(new int[][][]{{{ImageLoader.WATER0},{ImageLoader.WATER1},{ImageLoader.WATER2},{ImageLoader.WATER3}}},500);
		passable = true;
		slow = true;
	}

}
