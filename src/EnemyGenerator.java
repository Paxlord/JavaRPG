import java.util.ArrayList;
import java.util.Random;

public class EnemyGenerator {
	
	private ArrayList<Enemy> ennemiPool = new ArrayList<Enemy>();
	
	public EnemyGenerator() {
		
		ennemiPool.add(EnnemyDatabase.basicImp);
		ennemiPool.add(EnnemyDatabase.basicImp2);
		ennemiPool.add(EnnemyDatabase.basicImp3);
		
		ennemiPool.add(EnnemyDatabase.Orc);
		ennemiPool.add(EnnemyDatabase.Orc1);
		ennemiPool.add(EnnemyDatabase.Orc2);
		
		ennemiPool.add(EnnemyDatabase.ChauveSouris);
		ennemiPool.add(EnnemyDatabase.ChauveSouris2);
		ennemiPool.add(EnnemyDatabase.ChauveSouris1);

		MapData.ennemies.clear();
	}
	
	public void generate(Character c) {
		
		int level = c.getLevel();
		
		
		Random r = new Random();
		int numberOfMonstersToGenerate = r.nextInt(level - 1 +1) + 1;
		
		if(MapData.ennemies.size() == 0) {
			for(int i = 0; i < numberOfMonstersToGenerate; i++) {
				
				int monsterToSpawn = r.nextInt((ennemiPool.size()-1) - 0 +1);
			
				int randX = r.nextInt((17) - 1 +1) + 1;
				int randY = r.nextInt((10) - 1 +1) + 1;
				
				
				Enemy e = ennemiPool.get(monsterToSpawn);
				
				while(e.getLevel() > level) {
					monsterToSpawn = r.nextInt((ennemiPool.size()-1) - 0 +1);
					e = ennemiPool.get(monsterToSpawn);
				}
				
				MapData.ennemies.add(e);				
				e.spawn(new Vector2(randX, randY));
				
				
			}
		}
		
	}

}
