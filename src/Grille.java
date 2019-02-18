
public class Grille {

	private int rows;
	private int columns;
	private int cellSize;
	
	public Grille(int cellSize) {

		this.cellSize = cellSize;
		
	}
	
	public Vector2 getGridPos(int row, int column) {
		
		return new Vector2(column * cellSize, row * cellSize);
		
	}
	
	public Vector2 posToGridCell(Vector2 vec) {
		
		return new Vector2((int)Math.floor(vec.getX()/cellSize), (int)Math.floor(vec.getY()/cellSize));
		
	}
	
}
