import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TileMap {
	
	private Image tilemap;
	private BufferedImage scaledTileMap;
	
	private int cellSizeX;
	private int cellSizeY;
	
	private int columns;
	private int rows;
	
	private ArrayList<Cell> cells;
	
	public TileMap(String chemin, int cellSize, int row, int columns){
		
		cells = new ArrayList<Cell>();
		
		Image temp = null;
		try {
			temp = ImageIO.read(new File(chemin));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tilemap = temp;
		cellSizeX = cellSize;
		cellSizeY = cellSize;
		
		this.rows = row;
		this.columns = columns;
		
		
		initTileMap();
	}
	
	void initTileMap() {
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0;j < columns; j++) {

				cells.add(new Cell(j * cellSizeX, i * cellSizeY, cellSizeX));
				
			}
		}
		
		
	}
	
	public Image getImage() {
		return tilemap;
	}
	
	
	public Cell getCell(int index) {
		return cells.get(index);
	}
	
	

}
