package serverSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Authentification implements Runnable {

	private ServerSocket ss;
	private Socket acceptedConnection;
	BufferedReader in = null;
	PrintWriter out = null;
	String username = null;
	private GameWorld world;
	
	
	public Authentification(ServerSocket ss, GameWorld world) {
		
		this.ss = ss;
		this.world = world;
	
	}
	

	@Override
	public void run() {

		try {
			
			while(true) {
				
				acceptedConnection = ss.accept();
				System.out.println("Un client s'est connecté au serveur");
				
				setupStreams();
				askForName();
				
				Thread t = new Thread(new GameDataExchange(acceptedConnection, world));
				System.out.println("AUthantifié initialisation de l'échange...");
				t.start();
				
			}
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}


	private void askForName() {
		
		try {
			
			do{
				
				sendMessage("Veuillez rentrez votre username");
				username = in.readLine();

			}while(username == null);
			
			sendMessage("Bienvenue " + username);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void setupStreams() {
		try {
			in = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
			out = new PrintWriter(acceptedConnection.getOutputStream());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String msg) {
		out.println(msg);
		out.flush();
	}

}
