package serverSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
			
		ServerSocket server;
		GameWorld world;
		
		public Serveur(int port) {
			
			world = new GameWorld();
			world.add("0##################"
					+ ":#................#"
					+ ":#................#"
					+ ":#................#"
					+ ":#................#"
					+ ":#................#"
					+ ":#................#"
					+ ":#................#"
					+ ":#................#"
					+ ":#................#:"
					+ "##################/");
			
			world.add("1..................:"
					+ "..................:"
					+ "..................:"
					+ "..s...............:"
					+ "..................:"
					+ "..................:"
					+ "..................:"
					+ "..................:"
					+ "..................:"
					+ "..................:"
					+ "................../");
			
			
			try {
				
				server = new ServerSocket(port);
				System.out.println("Serveur à l'écoute...");
				Thread t = new Thread(new Authentification(server, world));
				t.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
}
