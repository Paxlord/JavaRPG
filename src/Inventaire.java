import java.util.ArrayList;

public class Inventaire {

	private ArrayList<Item> items;
	private ArrayList<Equipement> itemEquiper;
	private Character owner;
	private int slotsNumber;
	private int slotsUsed;
	private Vector2 positionToAdd = new Vector2(-1,-1);
	private Item selectedItem;
	
	public Inventaire(int slotsNumber, Character owner) {
		this.slotsNumber = slotsNumber;
		items = new ArrayList<Item>(slotsNumber);
		itemEquiper = new ArrayList<Equipement>();
		this.owner = owner;
		
	}
	
	public void add(Item i) {
		
		items.add(i);
		setUpPosition(i);
		
		this.positionToAdd = new Vector2(-1,-1);
		slotsUsed++;
		
	}
	
	public void remove(Item i) {
		
		items.remove(i);
		this.positionToAdd = i.getPosition();
		slotsUsed--;
		
	}
	
	public int getSlotsNumber() {
		return slotsNumber;
	}
	
	public int getSlotsUsed() {
		return slotsUsed;
	}
	
	public void setUpPosition(Item i) {
		
		if(this.positionToAdd.equals(new Vector2(-1,-1))) {
			
			int separator = (int) (this.slotsUsed / Math.floor(Math.sqrt(this.slotsNumber)));
			i.setPosition(new Vector2(this.slotsUsed - (separator*(int) Math.floor(Math.sqrt(this.slotsNumber))), separator));
			
		}else {
			i.setPosition(this.positionToAdd);
		}
				
	}
	
	public Item getItem(int index) {
		
		return items.get(index);
		
	}
	
	public boolean isItemSelected(MouseManager mM) {
		
		Vector2 posSelect = mM.mousePosToGridCellSelected();
		
		for(int i = 0; i < this.items.size(); i++) {
			if(this.items.get(i).getPos().equals(posSelect)) {
				this.selectedItem = this.items.get(i);
				return true;
			}
		}
		
		return false;
		
	}
	
	public Item getSelectedItem() {
		return selectedItem;
	}
	
	public void addEquipmentItem(Equipement e) {
		this.itemEquiper.add(e);
	}
	
	public void printItem() {
		System.out.println(this.items.get(0).getPos());
	}
	
	public Character getCharacter() {
		return owner;
	}
	
}
