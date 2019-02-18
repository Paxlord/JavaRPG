import java.awt.Color;
import java.util.ArrayList;

public interface MapData {
	
	
	public static final int SCALE = 2;
	public static final int CELL_SIZE = 16;
	public static final int MODIFIED_CELL_SIZE = SCALE * CELL_SIZE;
	public static final Color BG_COLOR = new Color(202,220,159);
	
	
	public static final ArrayList<Character> characters = new ArrayList<Character>();
	public static final ArrayList<Enemy> ennemies = new ArrayList<Enemy>();
	
	
	public static final ArrayList<Tile> tiles = new ArrayList<Tile>();
	public static final ArrayList<ItemTile> iTiles = new ArrayList<ItemTile>();


}
