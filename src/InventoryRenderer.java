import java.awt.Graphics2D;

public class InventoryRenderer {
	
	private TileMap itemMap;
	
	private Grille grille = new Grille(MapData.CELL_SIZE);
	private Inventaire inventaire;
	
	public InventoryRenderer(Inventaire i) {
		
		this.inventaire = i;
		this.itemMap = new TileMap("src/assets/itemMap.png",MapData.CELL_SIZE,1,3);
		
		
	}
	
	private void drawCell(int x, int y, int i,TileMap map, Graphics2D g2) {
		
		g2.drawImage(map.getImage(), x, y, x + map.getCell(i).getSize(), y + map.getCell(i).getSize(),
				map.getCell(i).getX(), map.getCell(i).getY(), map.getCell(i).getX() + map.getCell(i).getSize(), map.getCell(i).getY() + map.getCell(i).getSize(), null);
		
	}
	
	public void drawTileOnGrid(int c, int r, int i,TileMap map, Graphics2D g2) {
		drawCell(grille.getGridPos(r, c).getX(), grille.getGridPos(r, c).getY(), i, map, g2 );
	}
	
	public void Draw(Graphics2D g, MouseManager mM) {
		for(int j = 0; j < inventaire.getSlotsUsed(); j++ ) {
			
			Item currentItem = inventaire.getItem(j);
			this.drawTileOnGrid(currentItem.getPosition().getX(), currentItem.getPosition().getY(), currentItem.getDrawingIndex(), itemMap, g);
			
		}
		
		drawTileOnGrid(mM.mousePosToGridCell().getX(),mM.mousePosToGridCell().getY(), 0, mM.getMouseMap(), g);

	}

}
