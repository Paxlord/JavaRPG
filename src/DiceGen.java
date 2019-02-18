

import java.util.Random;

public class DiceGen {
	private Random rng;
	
	public DiceGen() {
		this.rng = new Random();
	}
	
	public int d6() {
		return rng.nextInt(6) + 1;
	}
	
	public int xD6 (int i) {
		int res = 0;
		
		for(; i > 0; i--) {
			res += d6();
		}
		return res;
	}
	
	public int xDx (int nbDice, int diceRange) {
		int res = 0;
		
		for(; nbDice > 0; nbDice--) {
			res += rng.nextInt(diceRange) + 1;
		}
		return res;
	}
	
	public int rollStat(int stat) {
		int res = stat % 3;
		
		res += xD6(stat/3);
		return res;  
	}
	
}
