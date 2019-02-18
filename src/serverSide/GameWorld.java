package serverSide;

import java.io.File;
import java.util.ArrayList;

public class GameWorld {

	private String map;
	private ArrayList<Layer> layerList = new ArrayList<Layer>();
	private int i = 0;
	
	public GameWorld() {
	}
	
	public void add(String m) {
		
		layerList.add(new Layer(m, i));
		i++;
		
	}

	public String getMap(int index) {
		return layerList.get(index).getMap();
	}
	
	public int getSize() {
		return layerList.size();
	}
}
