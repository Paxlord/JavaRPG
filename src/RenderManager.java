import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RenderManager {
	
	private ArrayList<TileMap> map = new ArrayList<TileMap>();
	private Grille grille;
	private LogicManager lm;
	private MouseManager mM;
	private EntityDrawer ed;
	private int tilesSize = 0;
	private int iTilesSize = 0;
	
	public RenderManager(LogicManager lm, MouseManager mM, Grille grille) {
		map.add(new TileMap("src/assets/tilemap_java.png",MapData.CELL_SIZE,2,5));
		map.add(new TileMap("src/assets/itemMap.png",MapData.CELL_SIZE,1,3));
		map.add(new TileMap("src/assets/charMap.png",MapData.CELL_SIZE,1,2));
		map.add(new TileMap("src/assets/TileSelected.png",MapData.CELL_SIZE,1,2));
		
		this.grille = grille;
		ed = new EntityDrawer(this);
		
		this.lm = lm;
		this.mM = mM;
	}
	
	public void draw(Graphics2D g) {
		this.drawTiles(g);
		ed.Draw(g, 2);
		drawTileOnGrid(mM.mousePosToGridCell().getX(),mM.mousePosToGridCell().getY(), 0, 3, g);

	}
	
	private void drawCell(int x, int y, int i,TileMap map, Graphics2D g2) {
	
		g2.drawImage(map.getImage(), x, y, x + map.getCell(i).getSize(), y + map.getCell(i).getSize(),
				map.getCell(i).getX(), map.getCell(i).getY(), map.getCell(i).getX() + map.getCell(i).getSize(), map.getCell(i).getY() + map.getCell(i).getSize(), null);
		
	}
	
	public void drawTileOnGrid(int c, int r, int i,int index, Graphics2D g2) {
		drawCell(grille.getGridPos(r, c).getX(), grille.getGridPos(r, c).getY(), i, map.get(index), g2 );
	}
	
	public void drawTiles(Graphics2D g2) {
		
			for(int i = 0; i < MapData.tiles.size(); i++) {
				
				Tile t = MapData.tiles.get(i);
				
				switch(t.getId()) {
					case 0:
						this.drawTileOnGrid(t.getPos().getX(), t.getPos().getY(), 3, 0, g2);
						break;
					case 1:
						this.drawTileOnGrid(t.getPos().getX(), t.getPos().getY(), 0, 0, g2);
						break;
					case 2:
						this.drawTileOnGrid(t.getPos().getX(), t.getPos().getY(), 2, 0, g2);
						break;
				}
				
			}
		
			for(int i = 0; i < MapData.iTiles.size(); i++) {
				
				ItemTile t = MapData.iTiles.get(i);
				
				switch(t.getId()) {
					case 0:
						this.drawTileOnGrid(t.getPos().getX(), t.getPos().getY(), 1, 1, g2);
						break;
					case 1:
						this.drawTileOnGrid(t.getPos().getX(), t.getPos().getY(), 0, 1, g2);
						break;
				}
				
			}
			
		
	}
	
	public Grille getGrid() {
		return grille;
	}
	
}
