import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class InventoryUI extends JPanel{
	
	
	private JToggleButton[] inventorySlots = new JToggleButton[4*4];
	private Grille g = new Grille(MapData.CELL_SIZE);
	private InventoryRenderer ir;
	private InventoryPanel slotsPanel = null;
	private JPanel actionPanel = new JPanel();
	
	private JButton equiper = new JButton("Equiper");
	private JButton inspecter = new JButton("Inspecter");
	private JButton utiliser = new JButton("Utiliser");
	private JButton jeter = new JButton("Jeter");
	
	private Inventaire i;
	private int slotsNumber;
	
	public InventoryUI(Inventaire i) {
		
		
		actionPanel.setLayout(new GridLayout(4,1, 10,10));
		
		equiper.setEnabled(true);
		inspecter.setEnabled(false);
		utiliser.setEnabled(true);
		jeter.setEnabled(true);
		
		
		actionPanel.add(equiper);
		actionPanel.add(inspecter);
		actionPanel.add(utiliser);
		actionPanel.add(jeter);
		
		
		
		this.i = i;
		this.slotsNumber = (int)Math.sqrt(i.getSlotsNumber());
		this.ir = new InventoryRenderer(this.i);
		this.slotsPanel = new InventoryPanel(i, ir);
		this.add(actionPanel, BorderLayout.EAST);
		this.add(slotsPanel, BorderLayout.WEST);
		
		utiliser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(i.isItemSelected(slotsPanel.getCurrentMouseManager())) {
					
					if(i.getSelectedItem() instanceof Potion) {
						Potion p = (Potion) i.getSelectedItem();
						p.use(i.getCharacter());
					}
					
					i.remove(i.getSelectedItem());
					
				}
			}
			
		});
		
		equiper.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(i.isItemSelected(slotsPanel.getCurrentMouseManager())) {
					
					if(i.getSelectedItem() instanceof Equipement) {
						Equipement p = (Equipement) i.getSelectedItem();
						p.equiper(i.getCharacter());
					}
					
					i.remove(i.getSelectedItem());
					
				}
			}
		});
		
		jeter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				i.add(new Arme("dummy", 0, 5));
			}
		});
		
		
	}
	
	public InventoryPanel getSlotsPanel() {
		return slotsPanel;
	}
}
