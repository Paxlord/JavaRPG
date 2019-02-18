
public class Tile {
	
	private String name;
	private Vector2 position;
	private boolean solid;
	private int id;
	
	public Tile(String n, Vector2 pos, boolean s, int id) {
		this.name = n;
		this.position = pos;
		this.solid = s;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public Vector2 getPos() {
		return position;
	}
	
	public boolean getSolid() {
		return solid;
	}
	
	public void setPos(Vector2 pos) {
		this.position = pos;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void displayInfo(TextInfoFrame frame) {
		String s = "" + this.getName() + "\n Solide : " + this.getSolid();
		frame.printConsole(s);
	}
	
}
