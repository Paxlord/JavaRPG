
public class Armure extends Equipement implements Defendre {
	
	private int defenseVal;

	public Armure(String n, int i, int defenseVal) {
		super(n, i);
		// TODO Auto-generated constructor stub
		this.defenseVal = defenseVal;
	}

	@Override
	public int getValeurDefense() {
		// TODO Auto-generated method stub
		return defenseVal;
	}

}
