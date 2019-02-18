import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PreviewPanel extends JPanel{
	
	TileMap characterMap;
	
	private int drawingIndex = 0;
	
	public PreviewPanel() {
		
		this.setPreferredSize(new Dimension(300, this.getHeight()));
		characterMap =  new TileMap("src/assets/charMap.png",MapData.CELL_SIZE,1,3);
		
		
		
	}
	
	private void drawEntity(int x, int y, int i,TileMap map, Graphics2D g2) {
		
		g2.drawImage(map.getImage(), x, y, x + map.getCell(i).getSize(), y + map.getCell(i).getSize(),
				map.getCell(i).getX(), map.getCell(i).getY(), map.getCell(i).getX() + map.getCell(i).getSize(), map.getCell(i).getY() + map.getCell(i).getSize(), null);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(MapData.BG_COLOR);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.scale(15, 15);
		
		this.drawEntity(2, 10, drawingIndex, characterMap, g2);
		
	}
	
	public int getDrawingIndex() {
		return drawingIndex;
	}
	
	public void setDrawingIndex(int drawingIndex) {
		this.drawingIndex = drawingIndex;
	}

}
