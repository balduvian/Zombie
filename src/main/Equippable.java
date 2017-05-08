package main;

public class Equippable extends Item{
	
	static int[][] materialstats = {
			{},//Wood
			{},
			{}
	};
	int material;
	int[] buffs = new int[Stats.NUMSTATS];
	int numeffects;
	int[] effects;
	boolean enchanted;
	
}
