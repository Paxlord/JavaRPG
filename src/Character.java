import java.util.ArrayList;
import java.util.Scanner;


public class Character extends Entity {
	
	
	private boolean isNew;
	private Inventaire i;
	private int drawingIndex;
	
	private int xpPool = 0;
	private int level = 1;
	private int xpToNextLevel;
	
	private Arme mainGauche;
	private Arme mainDroite;
	
	private Casque helmet;
	private ChestPiece chest;
	private Botte boots;
	
	private int attackModifier;
	private int defenseModifier;
	
	public Character(){ 
		super();
		MapData.characters.add(this);
	}
	
	public Character(String s, int str, int agi, int con, int mR, int aR, int drawingIndex) {
		super(s,str, agi, con, mR, aR, drawingIndex);
		setHealth(6);
		i = new Inventaire(100, this);
		MapData.characters.add(this);
	}
	
	
	// status : new pour creer, quelque chose d'autre pour charger
	//(j'ai juste mis un truc pour creer pour l'instant
	public Character(String status) {
		Scanner reader = new Scanner(System.in);
		int tmp =-1;
		
		if (status.equals("new"));
		
			this.isNew = true;
			System.out.println("So, what is this character name ?");
			setName(reader.nextLine());
			do {
				System.out.println("\nYou have 18 stat points to attribute. Choose wisely!");
				do {
					System.out.println("Please enter your strength :");
					tmp = reader.nextInt();
				} while (tmp < 0 || tmp > 18);
				setStrength(tmp);
				do {
					System.out.println("Please enter your agility :");
					tmp = reader.nextInt();
				} while (tmp < 0 || tmp > 18);
				setAgility(tmp);
				do {
					System.out.println("Please enter your constitution:");
					tmp = reader.nextInt();
				} while (tmp < 0 || tmp > 18);
				setConstitution(tmp);
				if (getStrength() + getAgility() + getConstitution() != 18)
					System.out.println("Your stats don't add up to 18! Don't you know how to add ?");
			} while (getStrength() + getAgility() + getConstitution() != 18);
			reader.close();
			setHealth(6);
			
			setMRange(2);
			setARange(2);
			this.spawn(new Vector2(1,1));
			this.isNew = false;
			
			i = new Inventaire(100, this);
			
			MapData.characters.add(this);
	}
	
	public void affCharacter() {
		System.out.println("name : " + getName());
		System.out.println("\nyour stats : ");
		System.out.print("- STR : " + getStrength()/3 + "D");
			if(getStrength()%3 != 0) {
				System.out.print("+" + getStrength()%3);}
		System.out.print("\n- AGI : " + getAgility()/3 + "D");
			if(getAgility()%3 != 0) {
				System.out.print("+" + getAgility()%3);}
		System.out.print("\n- CON : " + getConstitution()/3 + "D");
			if(getConstitution()%3 != 0) {
				System.out.print("+" + getConstitution()%3);}
	}
	
	public boolean IsNew() {
		return isNew;
	}
	
	public void die(Entity killer) {
		MapData.characters.remove(this);
	}
	
	public void attack(Entity foe, TextInfoFrame frame) {
		DiceGen dice = new DiceGen();
		if (foe.getDodge() < dice.rollStat(this.getAgility())) {
				int damage = dice.rollStat(this.getStrength()) + dice.rollStat(this.getAttackModifier()) - foe.getDefense();
				if (damage > 0) {
					foe.setHealth(foe.getHealth() - damage);
					frame.addToConsole("Vous infligez " + damage + " D¨¦gats");
				}else {
					frame.addToConsole("L'ennemi tank tout vos d¨¦gats ");
				}
		}else {
			frame.addToConsole("L'ennemi esquive votre attaque");
		}
		if (foe.getHealth() <= 0)
			foe.die(this);
	}
	
	public Inventaire getInventory() {
		return i;
	}
	
	public void addToXpPool(int xp) {
		this.xpPool += xp;
	}
	
	public int nextLevel() {
		return (int) Math.pow((level * 5), 3/2);
	}
	
	public void printInfo(TextInfoFrame logConsole) {
		String info = "";
		info += "Name :";
		info += this.getName();
		info += "\n Vie :";
		info += this.getHealth();
		info += "\n Niveau :";
		info += this.level;
		info += "\n Xp avant prochain niveau :";
		info += this.nextLevel() - this.xpPool;
		info += "\n Stats :";
		info += "\n Force :" + this.getStrength() + "  ( +" + this.getAttackModifier() + ")";
		info += "\n Agilit¨¦ :" + this.getAgility();
		info += "\n Consitution :" + this.getConstitution() + "  ( +" + this.getDefenseModifier() + ")";
		
		logConsole.printConsole(info);
	}
	
	public void levelUp() {
		if(xpPool >= this.nextLevel()) {
			this.level += 1;
			this.health = 6;
			this.strength += Math.round(Math.random()) +1;
			this.constitution += Math.round(Math.random()) +1;
			this.agility += Math.round(Math.random()) +1;
			
			this.xpPool = 0;
		}
	}

	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 6;
	}

	public void equiper(Equipement e) {
		
		if(e instanceof Casque) {
			
			if(this.helmet != null) {
				this.helmet = null;
				this.updateEquipement();
			}
			
			this.helmet = (Casque)e;
			
		}else if(e instanceof Botte) {
			
			if(this.boots != null) {
				this.boots = null;
				this.updateEquipement();
			}
			
			this.boots = (Botte)e;
			
		}else if(e instanceof ChestPiece) {
			
			if(this.chest != null) {
				this.chest = null;
				this.updateEquipement();
			}
			
			this.chest = (ChestPiece)e;
			
		}else if(e instanceof Arme) {
			
			if(this.mainDroite != null) {
				this.mainDroite = null;
				this.updateEquipement();
			}
			
			this.mainDroite = (Arme)e;
		}
		
		this.updateEquipement();
		
	}
	
	public void updateEquipement() {
		
		if(this.helmet != null) {
			this.defenseModifier = this.helmet.getValeurDefense();
		}else {
			this.defenseModifier = 0;
		}
		
		if(this.chest != null) {
			this.defenseModifier = this.chest.getValeurDefense();
		}else {
			this.defenseModifier = 0;
		}
		
		if(this.boots != null) {
			this.defenseModifier = this.boots.getValeurDefense();
		}else {
			this.defenseModifier = 0;
		}
		
		if(this.mainDroite != null) {
			
			this.attackModifier = this.mainDroite.getValeurAttaque();
			
		}else {
			this.attackModifier = 0;
		}
		
		/*if(this.mainGauche != null) {
			this.attackModifier += this.mainDroite.getValeurAttaque();
		}else {
			this.attackModifier = 0;
		}*/
		
	}
	
	public int getAttackModifier() {
		return this.attackModifier;
	}
	
	public int getDefenseModifier() {
		return this.defenseModifier;
	}

	public int getLevel() {
		// TODO Auto-generated method stub
		return this.level;
	}
	
	
}
