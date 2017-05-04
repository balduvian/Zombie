package zombie;

public class Region {
	
	int tlmax;
	int[] tiles;
	
	int prmax;
	int[] props;
	
	int enmax;
	int[] enemychances;
	
	int spmax;
	int[] spawnchances;
	
	int lvmax;
	int[] levelchances;
	
	int srmax;
	int[][][] structures;
	
	int[] bounds;
	
	String name;
	
	int[] color;
	
	protected void setup(){
		tlmax = tiles.length;
		prmax = props.length;
		enmax = enemychances.length;
		spmax = spawnchances.length;
		lvmax = levelchances.length;
		srmax = structures.length;
	}
	
	/*{ImageLoader.VILLAGEPROP0,ImageLoader.VILLAGEPROP1,ImageLoader.VILLAGEPROP2,ImageLoader.VILLAGEPROP3},
	{ImageLoader.FORESTPROP0,ImageLoader.FORESTPROP1,ImageLoader.FORESTPROP2,ImageLoader.FORESTPROP3},
	{ImageLoader.PLAINSPROP0,ImageLoader.PLAINSPROP1,ImageLoader.PLAINSPROP2,ImageLoader.PLAINSPROP3},
	{ImageLoader.CITYPROP0,ImageLoader.CITYPROP1,ImageLoader.CITYPROP2,ImageLoader.CITYPROP3},
	{ImageLoader.DESERTPROP0,ImageLoader.DESERTPROP1,ImageLoader.DESERTPROP2,ImageLoader.DESERTPROP3},
	{ImageLoader.SWAMPPROP0,ImageLoader.SWAMPPROP1,ImageLoader.SWAMPPROP2,ImageLoader.SWAMPPROP3},
	{ImageLoader.SALTPROP0,ImageLoader.SALTPROP1,ImageLoader.SALTPROP2,ImageLoader.SALTPROP3},
	{ImageLoader.HELLPROP0,ImageLoader.HELLPROP1,ImageLoader.HELLPROP2,ImageLoader.HELLPROP3,},
};
int[][] tiles = {
	{ImageLoader.VILLAGEWALL,ImageLoader.VILLAGEPATH},
	{ImageLoader.FORESTWALL,ImageLoader.FORESTPATH},
	{ImageLoader.PLAINSWALL,ImageLoader.PLAINSPATH},
	{ImageLoader.CITYWALL,ImageLoader.CITYPATH},
	{ImageLoader.DESERTWALL,ImageLoader.DESERTPATH},
	{ImageLoader.SWAMPWALL,ImageLoader.SWAMPPATH},
	{ImageLoader.SALTWALL,ImageLoader.SALTPATH},
	{ImageLoader.HELLWALL,ImageLoader.HELLPATH},*/
}
