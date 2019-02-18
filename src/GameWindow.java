import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame{
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 800;
	
	private Thread game;
	
	private GameArea map;
	private JPanel mainPanel = new JPanel();
	private GridLayout gl = new GridLayout(2,2, 20,20);
	private BorderLayout bl = new BorderLayout();
	private Character c;
	private Enemy e;
	private InventoryUI inventoryPanel;
	private ActionUI actionPanel = new ActionUI();
	private JPanel equipmentPanel = new JPanel();
	private TextInfoFrame logConsole;
	private TextInfoFrame actionConsole;
	
	private String serverQuery;
	
	private Client cli;


	public GameWindow() {
		
		Character c = MapData.characters.get(0);
		
		
		inventoryPanel = new InventoryUI(c.getInventory());
		logConsole = new TextInfoFrame();
		actionConsole = new TextInfoFrame();
		map = new GameArea(actionPanel, inventoryPanel, logConsole, actionConsole);
		map.start();
		
		mainPanel.setLayout(gl);
		mainPanel.add(map);
		
		mainPanel.add(inventoryPanel);
		mainPanel.add(actionPanel);
		mainPanel.add(equipmentPanel);
		
		

		this.setTitle("Projet Java");
		this.setSize(WIDTH, HEIGHT);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
		
	}




	
	
}
