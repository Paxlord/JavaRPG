import java.awt.Graphics2D;

public class EntityDrawer {
	
	public RenderManager rm;
	private Grille grille = new Grille(MapData.CELL_SIZE);
	private TileMap CharacterMap;
	private TileMap EnnemyMap;
	
	public EntityDrawer(RenderManager rm) {
		this.CharacterMap = new TileMap("src/assets/charMap.png",MapData.CELL_SIZE,1,3);
		this.EnnemyMap = new TileMap("src/assets/enemyMap.png",MapData.CELL_SIZE,1,5);

		this.rm = rm;
	}
	
	private void drawEntity(int x, int y, int i,TileMap map, Graphics2D g2) {
		
		g2.drawImage(map.getImage(), x, y, x + map.getCell(i).getSize(), y + map.getCell(i).getSize(),
				map.getCell(i).getX(), map.getCell(i).getY(), map.getCell(i).getX() + map.getCell(i).getSize(), map.getCell(i).getY() + map.getCell(i).getSize(), null);
		
	}
	
	public void Draw(Graphics2D g,  int i) {
		
		
		if(!MapData.characters.isEmpty()) {
			
			for(Character c : MapData.characters) {
				
				this.drawEntity((int)c.getPixelPos().getX(),(int) c.getPixelPos().getY(), c.getDrawingIndex(), CharacterMap, g);
				
				if(!c.getRangePos().isEmpty()) {
					for(Vector2 v : c.getRangePos()) {
						rm.drawTileOnGrid(v.getX(), v.getY(), 1, 3, g);
					}
				}
			}
		}
		
		if(!MapData.ennemies.isEmpty()) {
			
			for(Enemy e : MapData.ennemies) {
				
				this.drawEntity((int)e.getPixelPos().getX(),(int) e.getPixelPos().getY(), e.getDrawingIndex(), EnnemyMap, g);
				
			}
		}
	}
	
	

}
