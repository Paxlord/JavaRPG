import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextArea;

public abstract class Entity {

	
	int strength;
	int agility;
	int constitution;
	int health;
	String name;
	private Vector2 pos;
	private Vector2 pixelPos = new Vector2(0,0);
	
	
	private int Mrange;
	private int Arange;
	private int drawingIndex;
	private ArrayList<Vector2> rangePos = new ArrayList<Vector2>();
	
	public Entity(){ 
		setStrength(0);
		setAgility(0);
		setConstitution(0);
		setHealth(0);
		setMRange(2);
		setARange(2);
		this.drawingIndex = 0;
	}

	public Entity(String name, int str, int agi, int con, int movementRange, int attackRange, int drawingIndex) {
		setName(name);
		setStrength(str);
		setAgility(agi);
		setConstitution(con);
		setMRange(movementRange);
		setARange(attackRange);
		this.drawingIndex = drawingIndex;
	}

	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAgility() {
		return agility;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	public int getConstitution() {
		return constitution;
	}
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}
	public int getDodge() {
		return getAgility();
	}
	public int getDefense() {
		return getConstitution();
	}
	public void spawn(Vector2 pos) {
		this.setPos(pos);
	}
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 vec) {
		this.pos = vec;
		this.pixelPos = new Vector2(MapData.CELL_SIZE * vec.getX(), MapData.CELL_SIZE * vec.getY());
	}
	
	public void setUpRange(int range, boolean moving) {
		
		Vector2 vec = this.pos;
		
		if(moving) {
			for ( int i = 0 ; i < range*2+1; i ++) {
				for(int j = 0 ; j < range*2+1; j++) {
					
					Vector2 v = new Vector2(j + (this.pos.getX() - range), i + (this.pos.getY() - range));
					if(vec.Distance(v) <= range  && vec.Distance(v) > 0 && !isEntityInTheWay(v)) {
						for(Tile t: MapData.tiles) {
							
							if(v.equals(t.getPos())) {
								if(!t.getSolid() ) {
									rangePos.add(v);
								}
							}
						}
						
					}
					
					
				}
			}
		}else {
			for ( int i = 0 ; i < range*2+1; i ++) {
				for(int j = 0 ; j < range*2+1; j++) {
					
					Vector2 v = new Vector2(j + (this.pos.getX() - range), i + (this.pos.getY() - range));
					if(vec.Distance(v) <= range  && vec.Distance(v) > 0) {
						for(Tile t: MapData.tiles) {
							
							if(v.equals(t.getPos())) {
								if(!t.getSolid() ) {
									rangePos.add(v);
								}
							}
						}
						
					}
					
					
				}
			}
		}
		
	}
	
	public boolean isEntityInTheWay(Vector2 v) {
		
		for(int i = 0; i < MapData.characters.size(); i++) {
			if(v.equals(MapData.characters.get(i).getPos())) {
				return true;
			}
		}
		
		for(int i = 0; i < MapData.ennemies.size(); i++) {
			if(v.equals(MapData.ennemies.get(i).getPos())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public void clearRange() {
		rangePos.clear();
	}
	public ArrayList<Vector2> getRangePos() {
		return rangePos;
	}
	
	public int getMRange() {
		return Mrange;
	}
	
	public void setMRange(int range) {
		this.Mrange = range;
	}
	
	public int getARange() {
		return Arange;
	}
	
	public void setARange(int range) {
		this.Arange = range;
	}
	
	public boolean contains(Vector2 v) {
		
		for(Vector2 vec: this.rangePos) {
			if(vec.equals(v)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public void printInfo(TextInfoFrame logConsole) {
		String info = "";
		info += "Name :";
		info += this.getName();
		info += "\n Vie :";
		info += this.getHealth();
		info += "\n Stats :";
		info += "\n Force :" + this.getStrength();
		info += "\n Agilit¨¦ :" + this.getAgility();
		info += "\n Consitution :" + this.getConstitution();
		
		logConsole.printConsole(info);
	}
	
	public Vector2 getPixelPos() {
		
		return this.pixelPos;
		
	}
	
	public void setPixelPos(Vector2 pixPos) {
		this.pixelPos = pixPos;
		this.pos = new Vector2((int)Math.floor(this.pixelPos.getX()/ MapData.CELL_SIZE),(int) Math.floor(this.pixelPos.getY()/ MapData.CELL_SIZE));
	}

	public void Move(boolean xAxis, int speed, int dir){
		
		if(xAxis) {
			this.setPixelPos(new Vector2(this.getPixelPos().getX() + (speed * dir), this.getPixelPos().getY()));
		}else {
			this.setPixelPos(new Vector2(this.getPixelPos().getX(), this.getPixelPos().getY() + (speed * dir)));
		}
		
	}
	
	public boolean MoveTo(Vector2 target) {
		Vector2 pixelTarget = new Vector2(MapData.CELL_SIZE * target.getX(), MapData.CELL_SIZE * target.getY());
		
		if(this.getPixelPos().getX() != pixelTarget.getX()) {
			
			int dir = this.getPixelPos().getX() < pixelTarget.getX() ? 1 : -1;
			
			this.Move(true, 1, dir);
			
		}else if(this.getPixelPos().getY() != pixelTarget.getY()) {
			
			int dir = this.getPixelPos().getY() < pixelTarget.getY() ? 1 : -1;
			
			this.Move(false, 1, dir);
		}else {
			return true;
		}
		
		return false;
	}
	
	public int getDrawingIndex() {
		return this.drawingIndex;
	}
	
	public abstract void die(Entity killer);
	public abstract void attack(Entity foe, TextInfoFrame frame);
	

}
