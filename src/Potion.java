
public class Potion extends Item implements Consommable{
	

	public Potion(String n, int i) {
		super(n, i);
	}

	@Override
	public void use(Character user) {
		user.setHealth(user.getHealth() + 3);
		
		if(user.getHealth() > user.getMaxHealth()) {
			user.setHealth(user.getMaxHealth());
		}
	}

}
