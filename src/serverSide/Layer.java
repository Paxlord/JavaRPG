package serverSide;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Layer {
	
	private String map;
	private int id;
	
	public Layer(String map, int id) {
		
		this.map = map;
		this.id = id;
		
	}
	
	public int getId() {
		return id;
	}
	
	public String getMap() {
		return map;
	}
	
	public void update(String m) {
		this.map = m;
	}
	
	

}
