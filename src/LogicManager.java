import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;

public class LogicManager {
	
	private JButton deplacement;
	private JButton attaquer;
	private GameArea ga;
	private TextInfoFrame frame;
	private TextInfoFrame actionframe;
	private Grille grille;
	private int tour = 1;
	private int ennemyIterator = 0;
	
	private boolean isMoving = false;
	private boolean currentlyMoving = false;
	private boolean isAttacking = false;
	private boolean endAction = false;
	
	private EnemyGenerator eg = new EnemyGenerator();
	
	private Vector2 targetPos;
	
	private Character currentCharacter;
	private ArrayList<String> m = new ArrayList<String>();
	private MouseManager mM;
	
	public LogicManager(GameArea ga,MouseManager mM, Grille grille) {
		this.ga = ga;
		this.mM = mM;
		this.frame = ga.getLogConsole();
		this.actionframe = ga.getActionConsole();
		this.grille = grille;
		this.deplacement = ga.getActionPanel().getDeplacement();
		this.attaquer = ga.getActionPanel().getAttack();
		this.currentCharacter = MapData.characters.get(0);	
		this.deplacement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setIsMoving(true);
				mM.resetSelectedTile();
			}
			
		});
		
		this.attaquer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setIsAttacking(true);
				mM.resetSelectedTile();
			}
			
			
		});
	}
	
	public void play() {
		
		this.initMapArray();
		
		if(!isMoving && !isAttacking) {
			deplacement.setEnabled(true);
			attaquer.setEnabled(true);
		}
		
		if(tour == 1) {
			
			if(isMoving) {
				move();
			}else if(isAttacking) {
				attaquer();
			}
			
		}else {
			
			deplacement.setEnabled(false);
			attaquer.setEnabled(false);
			isMoving = true;	
			
			if(ennemyIterator >= MapData.ennemies.size()) {
				tour *= -1;
				isMoving = false;
				ennemyIterator=0;
			}else {
				if(MapData.ennemies.get(ennemyIterator).act(currentCharacter, actionframe)) {
					
					ennemyIterator++;
						
				}
			}
			
			
			
			
		}
		
		if(endAction) {
			isMoving = false;
			isAttacking = false;
			endAction = false;

			tour*=-1;
		}
		
		this.walkingOnItemTile();
		this.pollInfo(frame);
		eg.generate(currentCharacter);
	}
	
	public void move() {
		
		deplacement.setEnabled(false);
		attaquer.setEnabled(false);
		
		if(!MapData.characters.isEmpty()) {
			
			
			if(currentCharacter.getRangePos().isEmpty()) {
				currentCharacter.setUpRange(currentCharacter.getMRange(), true);
			}
			
			
			if(this.getSelectedTile().getX() > 0 && currentCharacter.contains(this.getSelectedTile())){
				
				
				if(!currentlyMoving)
					targetPos = this.getSelectedTile();
				
				currentlyMoving = true;
				
				
				if(currentCharacter.MoveTo(targetPos)) {
					currentCharacter.clearRange();
					this.endAction = true;
					this.currentlyMoving = false;
				}
				
				
			}
		}
		
	}
	
	public void attaquer() {
		
		deplacement.setEnabled(false);
		attaquer.setEnabled(false);
			
		if(!MapData.characters.isEmpty()) {
			
			if(currentCharacter.getRangePos().isEmpty()) {
				currentCharacter.setUpRange(currentCharacter.getARange(), false);
			}
			
			
			if(this.getSelectedTile().getX() > 0 && currentCharacter.contains(this.getSelectedTile())){
					
					for(int i = 0; i < MapData.ennemies.size(); i++) {
						if(this.getSelectedTile().equals(MapData.ennemies.get(i).getPos())) {
							
							currentCharacter.attack(MapData.ennemies.get(i), actionframe);
							currentCharacter.clearRange();
							
							this.endAction = true;
						}
					}
			}
		}
		
	
	}
	
	public boolean IsMoving() {
		return isMoving;
	}
	
	public boolean IsAttacking() {
		return isAttacking;
	}
	
	public boolean hasEnded() {
		return endAction;
	}
	
	public void setIsMoving(boolean b) {
		this.isMoving = b;
	}
	
	public void setIsAttacking(boolean b) {
		this.isAttacking = b;
	}
	
	public void setEndOfAction(boolean b) {
		this.endAction = b;
	}
	
	void initMapArray() {
		

		if(m.isEmpty()) {
			try {
				BufferedReader bf = new BufferedReader(new FileReader("src/assets/map.txt"));
				
				try {
					String s = bf.readLine();
					
					while(s != null) {
						m.add(s);
						this.FillTiles(s);
						s = bf.readLine();
					}
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	void FillTiles(String str) {
		
		/*
		 * # = mur
		 * . = Herbe
		 * : = changement de ligne
		 * e = eau
		 * 0 = end Of Map
		 * 
		 */
		int rowNumber = 0;
		int i = 1;
		int j = 0;
		int h = 0;
		
		do {
			if(str.charAt(0) == '0') {
				switch (str.charAt(i)){
				
					case '#':
						MapData.tiles.add(new Tile("Mur", new Vector2(h, j), true, 0));
						h++;
	
						break;
					
					case '.':
						MapData.tiles.add(new Tile("Herbe", new Vector2(h, j), false, 1));
						h++;
	
						break;
						
					case 'e':
						MapData.tiles.add(new Tile("Eau", new Vector2(h, j), true, 2));
						h++;
	
						break;
					
					case ':':
						j++;
						h=0;
						break;
				}
			}else if(str.charAt(0) == '1') {
				switch (str.charAt(i)){
				
				case 's':
					MapData.iTiles.add(new ItemTile("Sword", new Vector2(h,j), false, 0, new Item("¨¦p¨¦e en fer", 1)));
					h++;

					break;
				
				case '.':
					h++;

					break;
					
				case 'h':
					MapData.iTiles.add(new ItemTile("Helmet", new Vector2(h,j), false, 1,new Item("casque En fer", 0)));
					h++;

					break;
				
				case ':':
					j++;
					h=0;
					break;
				}
			}
			
			i++;
			
			
		}while(str.charAt(i) != '/');
		
	}
	
	public Vector2 getSelectedTile() {
		if(mM.getMousePosSelected().getX() >= 0){
			return mM.mousePosToGridCell();
		}else {
			return new Vector2(-1,-1);
		}
		
	}
	
	public void walkingOnItemTile() {
		
		for(int i = 0; i < MapData.iTiles.size(); i++) {
			
			if(currentCharacter.getPos().equals(MapData.iTiles.get(i).getPos())){
				MapData.iTiles.get(i).pickUp(currentCharacter, actionframe);
				MapData.iTiles.remove(i);
			}
			
		}

	}
	
	void pollInfo(TextInfoFrame frame) {
		
		for(int j = 0; j < MapData.tiles.size(); j++) {
			if(mM.mousePosToGridCell().equals(MapData.tiles.get(j).getPos())) {
				MapData.tiles.get(j).displayInfo(frame);
			}
		}
		
		
		for(int i = 0; i < MapData.characters.size(); i++) {
			if(mM.mousePosToGridCell().equals(MapData.characters.get(i).getPos())) {
				MapData.characters.get(0).printInfo(frame);
				
			}
		}
		
		for(int i = 0; i < MapData.ennemies.size(); i++) {
			if(mM.mousePosToGridCell().equals(MapData.ennemies.get(i).getPos())) {
				MapData.ennemies.get(i).printInfo(frame);
			}
		}
		
		for(int i = 0; i < MapData.iTiles.size(); i++) {
			if(mM.mousePosToGridCell().equals(MapData.iTiles.get(i).getPos())) {
				MapData.iTiles.get(i).displayInfo(frame);
			}
		}
		
		
	}
}
