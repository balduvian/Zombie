package zombie;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	//16x16 images
	public static final int VILLAGEPROP0 = 0;
	public static final int VILLAGEPROP1 = 1;
	public static final int VILLAGEPROP2 = 2;
	public static final int VILLAGEPROP3 = 3;
	public static final int FORESTPROP0 = 4;
	public static final int FORESTPROP1 = 5;
	public static final int FORESTPROP2 = 6;
	public static final int FORESTPROP3 = 7;
	public static final int PLAINSPROP0 = 8;
	public static final int PLAINSPROP1 = 9;
	public static final int PLAINSPROP2 = 10;
	public static final int PLAINSPROP3 = 11;
	public static final int CITYPROP0 = 12;
	public static final int CITYPROP1 = 13;
	public static final int CITYPROP2 = 14;
	public static final int CITYPROP3 = 15;
	public static final int DESERTPROP0 = 16;
	public static final int DESERTPROP1 = 17;
	public static final int DESERTPROP2 = 18;
	public static final int DESERTPROP3 = 19;
	public static final int SWAMPPROP0 = 20;
	public static final int SWAMPPROP1 = 21;
	public static final int SWAMPPROP2 = 22;
	public static final int SWAMPPROP3 = 23;
	public static final int SALTPROP0 = 24;
	public static final int SALTPROP1 = 25;
	public static final int SALTPROP2 = 26;
	public static final int SALTPROP3 = 27;
	public static final int HELLPROP0 = 28;
	public static final int HELLPROP1 = 29;
	public static final int HELLPROP2 = 30;
	public static final int HELLPROP3 = 31;
	public static final int VILLAGEWALL = 32;
	public static final int VILLAGEPATH = 33;
	public static final int FORESTWALL = 34;
	public static final int FORESTPATH = 35;
	public static final int PLAINSWALL = 36;
	public static final int PLAINSPATH = 37;
	public static final int CITYWALL = 38;
	public static final int CITYPATH = 39;
	public static final int DESERTWALL = 40;
	public static final int DESERTPATH = 41;
	public static final int SWAMPWALL = 42;
	public static final int SWAMPPATH = 43;
	public static final int SALTWALL = 44;
	public static final int SALTPATH = 45;
	public static final int HELLWALL = 46;
	public static final int HELLPATH = 47;
	public static final int ZOMBIEENEMY0 = 48;
	public static final int ZOMBIEENEMY1 = 49;
	public static final int HALFENEMY0 = 50;
	public static final int HALFENEMY1 = 51;
	public static final int SKELETONENEMY0 = 52;
	public static final int SKELETONENEMY1 = 53;
	public static final int MUSCLEENEMY0 = 54;
	public static final int MUSCLEENEMY1 = 55;
	public static final int CACTUSENEMY0 = 56;
	public static final int CACTUSENEMY1 = 57;
	public static final int SHAMANENEMY0 = 58;
	public static final int SHAMANENEMY1 = 59;
	public static final int OOZEENEMY0 = 60;
	public static final int OOZEENEMY1 = 61;
	public static final int FLAMEENEMY0 = 62;
	public static final int FLAMEENEMY1 = 63;
	public static final int SANDENEMY0 = 64;
	public static final int SANDENEMY1 = 65;
	public static final int IMPENEMY0 = 66;
	public static final int IMPENEMY1 = 67;
	public static final int DEVILENEMY0 = 68;
	public static final int DEVILENEMY1 = 69;
	public static final int ELEMENTALENEMY0 = 70;
	public static final int ELEMENTALENEMY1 = 71;
	public static final int METALENEMY0 = 72;
	public static final int METALENEMY1 = 73;
	public static final int GHOSTENEMY0 = 74;
	public static final int GHOSTENEMY1 = 75;
	public static final int BEASTENEMY0 = 76;
	public static final int BEASTENEMY1 = 77;
	public static final int GIANTENEMY0 = 78;
	public static final int GIANTENEMY1 = 79;
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
	public static final int GUISKIP = 105;
	public static final int GUISTOP = 106;
	public static final int GUIMENU = 107;
	public static final int CLOSE = 108;
	public static final int BUTTONACTIVE = 109;
	public static final int BUTTONINACTIVE = 110;
	public static final int BUTTONHOVER = 111;
	public static final int BUTTONINPRESSED = 112;
	public static final int TARGET = 113;
	public static final int MENUSELECT = 114;
	public static final int SWORDICON = 115;
	public static final int ARMORICON = 116;
	public static final int BODYICON = 117;
	public static final int FIRESTATUS = 118;
	public static final int MATERIALLAVA = 119;
	public static final int MATERIALAQUA = 120;
	public static final int MATERIALLEAVES = 121;
	public static final int MATERIALWOOD = 122;
	public static final int SKIN3 = 123;
	public static final int SKIN4 = 124;
	public static final int SKIN5 = 125;
	public static final int TSTATUS = 126;
	public static final int GSTATUS = 127;
	//64x64 images
	/*public static final int ZOMBIEPROFILE = 128;
	public static final int HALFPROFILE = 129;
	public static final int SKELETONPROFILE = 130;
	public static final int MUSCLEPROFILE = 131;
	public static final int CACTUSPROFILE = 132;
	public static final int SHAMANPROFILE = 133;
	public static final int OOZEPROFILE = 134;
	public static final int FLAMEPROFILE = 135;
	public static final int SANDPROFILE = 136;
	public static final int IMPPROFILE = 137;
	public static final int DEVILPROFILE = 138;
	public static final int ELEMETNALPROFILE = 139;
	public static final int METALPROFILE = 140;
	public static final int GHOSTPROFILE = 141;
	public static final int BEASTPROFILE = 142;
	public static final int GIANTPROFILE = 143;
	public static final int PLAYER0PROFILE = 144;
	public static final int PLAYER1PROFILE = 145;
	public static final int PLAYER2PROFILE = 146;
	public static final int PLAYER3PROFILE = 147;
	public static final int PLAYER4PROFILE = 148;
	public static final int PLAYER5PROFILE = 149;
	public static final int PLAYER6PROFILE = 150;
	public static final int PLAYER7PROFILE = 151;*/
	//8*8 images
	public static final int GLYPHA = 164;
	
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
				System.out.println("loaded "+(avance+1)+" out of "+Game.pmax);
				avance++;
			}
		}
	}
}
