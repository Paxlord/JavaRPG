
public class Item {
	
	private String nom;
	private int drawingIndex;
	
	private Vector2 position;
	private boolean hasBeenPickedUp;
	
	public Item(String n, int i) {
		this.nom = n;
		this.drawingIndex = i;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public Vector2 getPosition() {
		return this.position;
	}
	
	public boolean HasBeenPickedUp() {
		return hasBeenPickedUp;
	}
	
	public void setHasBeenPickedUp(boolean b) {
		this.hasBeenPickedUp = b;
	}
	
	public int getDrawingIndex() {
		return drawingIndex;
	}
	
	public String getName() {
		return nom;
	}
	 
	public String toString() {
		return "Nom : " + this.getName();
	}
	
	public Vector2 getPos() {
		return this.position;
	}
	
	public void setPos(Vector2 v) {
		this.position = v;
	}

}
