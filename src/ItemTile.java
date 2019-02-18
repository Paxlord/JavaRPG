
public class ItemTile extends Tile implements Rammassable{
	
	private Item item;

	public ItemTile(String n, Vector2 pos, boolean s, int id, Item item) {
		super(n, pos, s, id);
		this.item = item;
	}
	
	

	@Override
	public void pickUp(Character c, TextInfoFrame actionConsole) {
		
		Inventaire i = c.getInventory();
		if(i.getSlotsUsed() < i.getSlotsNumber()) {
			
			actionConsole.addToConsole("Vous trouvez une " + item.getName());
			i.add(item);
			
		}
	}
	
	public void displayInfo(TextInfoFrame frame) {
		String s = item.toString();
		frame.printConsole(s);
	}


}
