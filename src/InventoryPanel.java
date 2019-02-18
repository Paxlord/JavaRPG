import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class InventoryPanel extends JPanel{
	
	private Inventaire i;
	private InventoryRenderer ir;
	private MouseManager mM;
	private int slotsWidth;
	
	public InventoryPanel(Inventaire i, InventoryRenderer ir) {
		
		this.i= i;
		this.ir = ir;
		this.slotsWidth = (int)Math.sqrt(i.getSlotsNumber());
		this.setPreferredSize(new Dimension((slotsWidth * MapData.CELL_SIZE) *2,slotsWidth * MapData.CELL_SIZE *2));
		this.mM = new MouseManager();
		this.addMouseListener(this.mM);
		this.addMouseMotionListener(this.mM);
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(MapData.BG_COLOR);
		g.fillRect(0, 0, slotsWidth* MapData.CELL_SIZE *2, slotsWidth * MapData.CELL_SIZE *2);
		
		g2.scale(2,2);
		ir.Draw(g2, mM);

	}
	
	public MouseManager getCurrentMouseManager() {
		return this.mM;
	} 

	
}
