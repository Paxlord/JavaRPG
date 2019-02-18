import java.util.ArrayList;

public interface EnnemyDatabase {
	
	
	public static final Enemy basicImp = new Enemy("Diablotin Basique", 3, 5, 3, 5, 3,2, 0, 1, 2);
	public static final Enemy basicImp2 = new Enemy("Diablotin Basique", 3, 5, 3, 5, 3,2, 0, 2, 4);
	public static final Enemy basicImp3 = new Enemy("Diablotin Basique", 3, 5, 3, 5, 3,2, 0, 3, 6);
	
	public static final Enemy Orc = new Enemy("Orc", 8, 2, 5, 15, 1,1, 1, 4, 10);
	public static final Enemy Orc1 = new Enemy("Orc", 8, 2, 5, 15, 1,1, 1, 5, 12);
	public static final Enemy Orc2 = new Enemy("Orc", 8, 2, 5, 15, 1,1, 1, 6, 16);
	
	public static final Enemy ChauveSouris = new Enemy("Chauve-Souris", 4, 10, 2, 10, 5,1, 2, 5, 12);
	public static final Enemy ChauveSouris1 = new Enemy("Chauve-Souris", 4, 10, 2, 10, 5,1, 2, 6, 15);
	public static final Enemy ChauveSouris2 = new Enemy("Chauve-Souris", 4, 10, 2, 10, 5,1, 2, 8, 17);

}
