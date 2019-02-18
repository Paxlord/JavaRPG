
public class Cell {
	
	private int x;
	private int y;
	
	private int size;
	
	public Cell(int x, int y, int size) {
		
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
}
