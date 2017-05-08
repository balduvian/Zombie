package main;

import block.AirBlock;
import block.StoneBlock;
import block.WoodBlock;

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
	public static final int BARREL = 20;
	public static final int TRASH = 21;
	public static final int GLASS = 22;
	public static final int SAND = 23;
	public static final int FENCE = 24;
	
	protected int blockid;
	protected ImgSheet image;
	protected int state = 0;
	protected boolean solid;
	protected boolean oneway;
	protected int onewaydir;
	boolean walk;
	
	//used for determining frames of block anims
	public static int eternal = 0;
	public static void tick(){
		eternal = (eternal+1)%720720;
	}
	
	//when interacted with
	public void oninteract(){
		
	}
	
	public Block block(int bid){
		if(bid==Block.AIR){
			return new AirBlock();
		}if(bid==Block.WOOD){
			return new WoodBlock();
		}if(bid==Block.STONE){
			return new StoneBlock();
		}if(bid==Block.BUILDINGFLOOR){
			return new BuildingfloorBlock();
		}if(bid==Block.SANDSTONE){
			return new SandstoneBlock();
		}if(bid==Block.WOODFLOOR){
			return new WoodfloorBlock();
		}if(bid==Block.PATH){
			return new PathBlock();
		}if(bid==Block.WATER){
			return new WaterBlock();
		}if(bid==Block.HELLSTONE){
			return new HellBlock();
		}if(bid==Block.TREE){
			return new AirBlock();
		}if(bid==Block.ROCK){
			return new AirBlock();
		}if(bid==Block.LAMP){
			return new AirBlock();
		}if(bid==Block.FLAME){
			return new AirBlock();
		}if(bid==Block.CACTUS){
			return new AirBlock();
		}if(bid==Block.BONES){
			return new AirBlock();
		}if(bid==Block.LAVA){
			return new AirBlock();
		}if(bid==Block.DOOR){
			return new AirBlock();
		}if(bid==Block.PALMTREE){
			return new AirBlock();
		}if(bid==Block.CEMENT){
			return new AirBlock();
		}if(bid==Block.SIDEWALK){
			return new AirBlock();
		}if(bid==Block.BARREL){
			return new AirBlock();
		}if(bid==Block.TRASH){
			return new AirBlock();
		}if(bid==Block.GLASS){
			return new AirBlock();
		}if(bid==Block.SAND){
			return new AirBlock();
		}if(bid==Block.FENCE){
			return new AirBlock();
		}else{
			return new AirBlock();
		}
	}
}
