
public class Arme extends Equipement implements FaireDegats {
	
	private int attackVal;

	public Arme(String n, int i, int attackVal) {
		super(n, i);
		// TODO Auto-generated constructor stub
		
		this.attackVal = attackVal;
	}

	@Override
	public int getValeurAttaque() {
		// TODO Auto-generated method stub
		return attackVal;
	}

}
