package main;

import block.AirBlock;
import block.BuildingfloorBlock;
import block.PathBlock;
import block.SandstoneBlock;
import block.StoneBlock;
import block.WaterBlock;
import block.WoodBlock;
import block.WoodfloorBlock;

public class Block {
	
	public static final int AIR = 0;
	public static final int WOOD = 1;
	public static final int STONE = 2;
	public static final int BUILDINGFLOOR = 3;
	public static final int SANDSTONE = 4;
	public static final int WOODFLOOR = 5;
	public static final int PATH = 6;
	public static final int WATER = 7;
	public static final int HELLSTONE = 8;
	public static final int TREE = 9;
	public static final int ROCK = 10;
	public static final int LAMP = 11;
	public static final int FLAME = 12;
	public static final int CACTUS = 13;
	public static final int BONES = 14;
	public static final int LAVA = 15;
	public static final int DOOR = 16;
	public static final int PALMTREE = 17;
	public static final int CEMENT = 18;
	public static final int SIDEWALK = 19;
	public static final int TRASH = 20;
	public static final int GLASS = 21;
	public static final int SAND = 23;
	public static final int FENCE = 23;
	
	protected int blockid;
	protected ImgSheet image;
	protected int state = 0;
	protected boolean passable;
	protected boolean oneway;
	protected int onewaydir;
	protected boolean walk;
	protected boolean slow;
	
	//used for determining frames of block anims
	public static int eternal = 0;
	public static void tick(){
		eternal = (eternal+1)%720720;
	}
	
	//when interacted with
	public void oninteract(){
		actroutine();
		update();
	}
	
	public void setstate(int s){
		state = s;
	}
	
	//the actual method to override
	protected void actroutine(){
		
	}
	
	private void update(){
		image.mode = state;
	}
	
	public static Block block(int bid){
		if(bid==Block.AIR){
			return new AirBlock();
		}else if(bid==Block.WOOD){
			return new WoodBlock();
		}else if(bid==Block.STONE){
			return new StoneBlock();
		}else if(bid==Block.BUILDINGFLOOR){
			return new BuildingfloorBlock();
		}else if(bid==Block.SANDSTONE){
			return new SandstoneBlock();
		}else if(bid==Block.WOODFLOOR){
			return new WoodfloorBlock();
		}else if(bid==Block.PATH){
			return new PathBlock();
		}else if(bid==Block.WATER){
			return new WaterBlock();
		}else{
			return new AirBlock();
		}
	}
}
