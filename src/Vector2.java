
public class Vector2 {

	private int x;
	private int y;
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "La position de l'entite est " + this.x + "::" + this.y;
	}
	
	public int Magnitude() {
		
		return (Math.abs(x) + Math.abs(y));
		
	}
	
	public double Distance(Vector2 v) {
		
		//Distance de Manhattan
		return Math.abs((this.getX() - v.getX())) + Math.abs((this.getY() - v.getY()));
		
		//Distance euclidienne 
		//return Math.sqrt( ((this.getX() - v.getX()) * (this.getX() - v.getX())) + ((this.getY() - v.getY()) * (this.getY() - v.getY())) );
	}
	
	public boolean equals(Vector2 v) {
		
		if(v.getX() == this.getX() && v.getY() == this.getY()) {
			return true;
		}
		
		return false;
		
	}
	
}
