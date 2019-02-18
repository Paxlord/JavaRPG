import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	GameArea ga;
	Grille g = new Grille(MapData.MODIFIED_CELL_SIZE);
	private TileMap mouseMap;
	private Vector2 mousePos;
	private Vector2 mousePosSelected = new Vector2(-1,-1);
	
	public MouseManager(GameArea ga) {
		this.ga = ga;
		mousePos = new Vector2(0,0);
	}

	public MouseManager() {
		// TODO Auto-generated constructor stub
		this.mouseMap = new TileMap("src/assets/TileSelected.png",MapData.CELL_SIZE,1,2);
		mousePos = new Vector2(0,0);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		mousePos.setPos(arg0.getX(), arg0.getY());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		mousePos.setPos(arg0.getX(), arg0.getY());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mousePosSelected = new Vector2(arg0.getX() , arg0.getY());
	}

	
	public Vector2 getMousePos() {
		return mousePos;
	}
	
	public Vector2 getMousePosSelected() {
		return mousePosSelected;
	}
	
	public void resetSelectedTile() {
		this.mousePosSelected = new Vector2(-1,-1);
	}
	
	public Vector2 mousePosToGridCell(){
		return g.posToGridCell(this.mousePos);
	}
	
	public Vector2 mousePosToGridCellSelected() {
		return g.posToGridCell(this.mousePosSelected);
	}
	
	public TileMap getMouseMap() {
		return mouseMap;
	}

}
