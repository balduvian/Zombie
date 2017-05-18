package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	//16x16 images
	public static final int TABLE = 0;
	public static final int CHAIR = 1;
	public static final int TV = 2;
	public static final int RUG = 3;
	public static final int GRASS = 4;
	public static final int TALLGRASS = 5;
	public static final int PALMTREE0 = 6;
	public static final int PALMTREE1 = 7;
	public static final int FLAME0 = 8;
	public static final int FLAME1 = 9;
	public static final int BONES0 = 10;
	public static final int WATER0 = 11;
	public static final int WATER1 = 12;
	public static final int WATER2 = 13;
	public static final int WATER3 = 14;
	public static final int LAVA0 = 15;
	public static final int LAVA1 = 16;
	public static final int LAVA2 = 17;
	public static final int LAVA3 = 18;
	public static final int TREE0 = 19;
	public static final int TREE1 = 20;
	public static final int TREE2 = 21;
	public static final int SWAMPTREE = 22;
	public static final int CEMENT = 23;
	public static final int SIDEWALK = 24;
	public static final int HELLFLOOR = 25;
	public static final int BARREL = 26;
	public static final int TRASH = 27;
	public static final int GLASS = 28;
	public static final int DOORUP = 29;
	public static final int DOORRIGHT = 30;
	public static final int DOORLEFT = 31;
	public static final int DOORDOWN = 32;
	public static final int PATH = 33;
	public static final int STONE = 34;
	public static final int MOSSSTONE = 35;
	public static final int BUILDINGFLOOR = 36;
	public static final int SAND = 37;
	public static final int WOODFLOOR = 38;
	public static final int FENCE = 39;
	public static final int LAMP = 40;
	public static final int HELLBLOCK = 41;
	public static final int ROCK0 = 42;
	public static final int ROCK1 = 43;
	public static final int CACTUS = 44;
	public static final int BONES1 = 45;
	public static final int SANDSTONE = 46;
	public static final int WOODPLANKS = 47;
	
	public static final int ZOMBIEE0 = 48;
	public static final int ZOMBIEE1 = 49;
	public static final int HALFE0 = 50;
	public static final int HALFE1 = 51;
	public static final int MUSCLEE0 = 52;
	public static final int MUSCLEE1 = 53;
	public static final int CACTUSE0 = 54;
	public static final int CACTUSE1 = 55;
	public static final int COWBOYE0 = 56;
	public static final int COWBOYE1 = 57;
	public static final int GAPINGE0 = 58;
	public static final int GAPINGE1 = 59;
	public static final int FLAMESKELETONE0 = 60;
	public static final int FLAMESKELETONE1 = 61;
	public static final int OOZEE0 = 62;
	public static final int OOZEE1 = 63;
	public static final int SKELETONE0 = 64;
	public static final int SKELETONE1 = 65;
	public static final int GIANTE0 = 66;
	public static final int GIANTE1 = 67;
	public static final int DEVILE0 = 68;
	public static final int DEVILE1 = 69;
	public static final int IMPE0 = 70;
	public static final int IMPE1 = 71;
	public static final int METALE0 = 72;
	public static final int METALE1 = 73;
	public static final int GHOSTE0 = 74;
	public static final int GHOSTE1 = 75;
	public static final int SANDE0 = 76;
	public static final int SANDE1 = 77;
	public static final int ELEMENTALE0 = 78;
	public static final int ELEMENTALE1 = 79;
	public static final int CHESTENTITY0 = 80;
	public static final int CHESTENTITY1 = 81;
	public static final int CHESTENTITY2 = 82;
	public static final int LOOTENTITY0 = 83;
	public static final int LOOTENTITY1 = 84;
	public static final int LOOTENTITY2 = 85;
	public static final int PLAYEROUTLINE = 86;
	public static final int RACE0 = 87;
	public static final int RACE1 = 88;
	public static final int RACE2 = 89;
	public static final int RACE3 = 90;
	public static final int SKIN0 = 91;
	public static final int SKIN1 = 92;
	public static final int SKIN2 = 93;
	public static final int GUIBKGUL = 94;
	public static final int GUIBKGUR = 95;
	public static final int GUIBKGDL = 96;
	public static final int GUIBKGDR = 97;
	public static final int ARROWUP = 98;
	public static final int ARROWRIGHT = 99;
	public static final int ARROWDOWN = 100;
	public static final int ARROWLEFT = 101;
	public static final int GUIMOVE = 102;
	public static final int GUIATTACK = 103;
	public static final int GUIACTION = 104;
	public static final int GUIBACK = 105;
	public static final int GUISTOP = 106;
	public static final int GUIMENU = 107;
	public static final int BUTTONACTIVE = 108;
	public static final int BUTTONINACTIVE = 109;
	public static final int BUTTONHOVER = 110;
	public static final int BUTTONINPRESSED = 111;
	public static final int TARGET = 112;
	public static final int MENUSELECT = 113;
	public static final int STICKMASK = 114;
	public static final int BROADSWORDMASK = 115;
	public static final int SWORDMASK = 116;
	public static final int AXEMASK = 117;
	public static final int sadf = 118;
	public static final int saddf = 119;
	public static final int hdfa = 120;
	public static final int Masd = 121;
	public static final int MddDf = 122;
	public static final int SKIN3 = 123;
	public static final int SKIN4 = 124;
	public static final int SKIN5 = 125;
	public static final int TSTATUS = 126;
	public static final int GSTATUS = 127;
	
	public static final int GUIBKGU = 142;
	public static final int GUIBKGD = 143;
	
	public static final int FORESTGRASS = 144;
	public static final int TUMBLEWEED = 145;
	public static final int ASPHALT = 146;
	public static final int SWAMPGRASS = 147;
	
	static void loadimages(){
		BufferedImage b = null;
		try{
			b = ImageIO.read(ImageLoader.class.getResource("/zimages/spritesheet16x16.png"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		int cuo = 16;
		int avance = 0;
		int wu = b.getWidth()/cuo;
		int hu = b.getHeight()/cuo;
		for(int y=0;y<hu;y++){
			for(int x=0;x<wu;x++){
				BufferedImage lod = new BufferedImage(cuo,cuo,BufferedImage.TYPE_INT_ARGB);
				for(int yy=0;yy<cuo;yy++){
					for(int xx=0;xx<cuo;xx++){
						int lay = y*cuo+yy;
						int lax = x*cuo+xx;
						lod.setRGB(xx, yy, b.getRGB(lax, lay));	
					}
				}
				Game.images[avance] = lod;
				avance++;
			}
		}
	}
}
