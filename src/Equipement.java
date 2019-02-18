
abstract class Equipement extends Item implements Equipable{
	
	private boolean equiper;


	public Equipement(String n, int i) {
		super(n, i);
	}

	@Override
	public void equiper(Character c) {
		c.equiper(this);
	}
	
	

	

}
