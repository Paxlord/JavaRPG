
public class Enemy extends Entity {

	private int minDistanceTileIndex = -1;
	private int xpGainedOnKill = 5;
	private int level;
	
	public Enemy(){ 
		super();
		MapData.ennemies.add(this);
	}
	
	public Enemy(String s,int str, int agi, int con, int health, int mR, int aR, int drawingIndex, int level, int xpGainedOnKill) {
		super(s, str, agi, con, mR, aR, drawingIndex);
		this.level = level;
		this.xpGainedOnKill = xpGainedOnKill;
		setHealth(health);
		this.spawn(new Vector2(6,6));
		this.updateStatswithLevel();
		MapData.ennemies.add(this);
	}
	
	public void die(Entity killer) {
		MapData.ennemies.remove(this);
		Character c = (Character) killer;
		c.addToXpPool(this.xpGainedOnKill);
		c.levelUp();
	}
	
	public void attack(Entity foe, TextInfoFrame frame) {
		DiceGen dice = new DiceGen();
		Character f = null;
		int defenseModifier = 0;
		
		if(foe instanceof Character) {
			f = (Character) foe;
			defenseModifier = f.getDefenseModifier();
		}
			
		
		if (foe.getDodge() < dice.rollStat(this.getAgility())) {
				int damage = dice.rollStat(this.getStrength()) - (foe.getDefense() - defenseModifier) ;
				if (damage > 0) {
					foe.setHealth(foe.getHealth() - (damage/3));
					frame.addToConsole("Vous prenez " + (damage/3) + "Degats");
				}else {
					frame.addToConsole("Vous prenez " + 0 + "Degats");
				}
					
		}else {
			frame.addToConsole("Vous esquivez l'attaque");
		}
		if (foe.getHealth() <= 0)
			foe.die(this);
	}
	
	public boolean act(Character c, TextInfoFrame frame) {
		
		double minDistance = 9999;
		//int minDistanceTileIndex = -1;
		
		//On set up la range d'attaque
		//Si et seulement si on est pas deja en train 
		//de bouger, sinon le perso s'arrete d¨¨s qu'un ennemi est dans sa range d'attaque
		
		if(minDistanceTileIndex == -1) {
			
			if(this.getRangePos().isEmpty()) {
				this.setUpRange(this.getARange(), false);
			}
			
			//On parcours cette range pour voir si le joueur y est
			for(int i = 0; i < this.getRangePos().size(); i++) {
				
				if(c.getPos().equals(this.getRangePos().get(i))) {
					this.attack(c, frame);
					this.clearRange();
					return true;
				}
				
				
			}
			
			this.clearRange();
			this.setUpRange(this.getMRange(), true);
			
			for(int i = 0; i< this.getRangePos().size(); i++) {
				
				double distanceFromPlayer = this.getRangePos().get(i).Distance(c.getPos());
				
				if(distanceFromPlayer < minDistance) {
					minDistance = distanceFromPlayer;
					minDistanceTileIndex = i;
					
				}
			}
			
		}else {
			Vector2 target = this.getRangePos().get(minDistanceTileIndex);

			if(this.MoveTo(target)) {
				
				this.clearRange();
				this.minDistanceTileIndex = -1;
				return true;
			}
		}

				
			
		return false;
		
	}
	
	public void updateStatswithLevel() {
		this.health += (level * 2);
		this.strength += (1 * level);
		this.agility += (1 * level);
		this.constitution += (1 * level);
		this.xpGainedOnKill += (level * 4);
	}

	public int getLevel() {
		// TODO Auto-generated method stub
		return this.level;
	}

}
