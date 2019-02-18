package serverSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameDataExchange implements Runnable {
	
	
	Socket currentConnection;
	PrintWriter out;
	BufferedReader in;
	String incomingMessage = null;
	GameWorld world;
	GDDProtocol protocol;
	int i = 0;
	
	public GameDataExchange(Socket acceptedConnection, GameWorld world) {
		
		currentConnection = acceptedConnection;
		this.world = world;
		setupStreams();

	}

	
	@Override
	public void run() {
		
		System.out.println("Protocole d'échange de donnés initié");
		System.out.println("Envoi de la map...");
		sendData(world.getMap(i));
		i++;
		
		while(true) {
			receiveData();
			if(incomingMessage.equals("recu")) {
				System.out.println("Map envoyé !");
			}
			
			if(incomingMessage.equals("sendNL")){
				
				if(i != world.getSize()) {
					System.out.println("Envoi de la prochaine map");
					sendData(world.getMap(i));
					System.out.println("Map envoyé");
					i++;
				}else {
					sendData("end");
				}
				
			}
		}
		
		
	}
	
	private void sendData(String str) {
		out.println(str);
		out.flush();
	}
	
	private void receiveData() {
		try {
			incomingMessage = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void setupStreams() {
		try {
			in = new BufferedReader(new InputStreamReader(currentConnection.getInputStream()));
			out = new PrintWriter(currentConnection.getOutputStream());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
