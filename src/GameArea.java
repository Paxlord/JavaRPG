import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GameArea extends JPanel implements Runnable{
	
	private MouseManager mM = new MouseManager(this);
	private Grille grille;
	private LogicManager logicManager;
	private TextInfoFrame frame;
	private RenderManager renderManager;
	private Graphics2D g2d;
	private boolean running;
	private ActionUI ap;
	private InventoryUI iUI;
	Client cli = null;
	int x,y, i;
	Character c; 
	private TextInfoFrame actionFrame;
	

	public GameArea(ActionUI ap, InventoryUI iUI, TextInfoFrame frame, TextInfoFrame actionFrame) {

		
		this.addMouseListener(mM);
		this.addMouseMotionListener(mM);
		this.frame = frame;
		this.actionFrame = actionFrame;
		this.grille = new Grille(MapData.CELL_SIZE);
		this.ap = ap;
		this.iUI = iUI;
		this.logicManager = new LogicManager(this,mM, grille);
		this.renderManager = new RenderManager(logicManager, mM, grille);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(202,220,159));
		g.fillRect(0, 0, this.getWidth(), this.getWidth());
		
		g2d.scale(2, 2);
		renderManager.draw(g2d);
		
		
	}
	
	
	
	
	
	public Graphics2D getGraphicContext() {
		return g2d;
	}

	
	

	public synchronized void start() {
		running = true;
		
		Thread game = new Thread(this);
		game.start();
	}
	
	public synchronized void stop() {
		
		running = false;
		
	}

	public void run() {
		while(running) {
			
			logic();
			render();
			//iUI.repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void logic() {
		
		logicManager.play();
		
	}

	public void render() {
		this.repaint();
		iUI.getSlotsPanel().repaint();
	}
	
	public RenderManager getCurrentRenderManager() {
		return renderManager;
	}
	
	public LogicManager getLogicManager() {
		return logicManager;
	}
	
	public ActionUI getActionPanel() {
		return ap;
	}
	
	public TextInfoFrame getLogConsole() {
		return frame;
	}
	
	public TextInfoFrame getActionConsole() {
		return actionFrame;
	}
} 
