import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {
	
		Socket client;
		PrintWriter out;
		BufferedReader in;
		String message = null;
		Scanner sc;
		//GameWindow gw;
		
		String map;
		private boolean authentified = false;
		private boolean received = false;
		private boolean windowCreated = false;
		private boolean running = false;
		private boolean canGoNext = false;

		
		public Client(String adresse, int port) {
			try {
				client = new Socket(adresse, port);
				setupStreams();
				sc = new Scanner(System.in);
				
				
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		private void setupStreams() {
			try {
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void sendData(String str) {
			out.println(str);
			out.flush();
		}
		
		private void receiveData() {
			try {
				message = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		@Override
		public void run() {
				
			while(running) {
				
				if(!authentified) {
					receiveData();
					System.out.println(message);
					
					sendData(sc.nextLine());
					
					receiveData();
					System.out.println(message);
					authentified = true;
				}
				
				if(!windowCreated) {
					//gw = new GameWindow(this);
					windowCreated = true;
				}	
					
				receiveData();
					
				if(!received) {
					
					
					if(!message.equals("end")) {
						map += message;
						sendData("recu");
						System.out.println("mapReçu");
						sendData("sendNL");
						System.out.println("requete prochain layer envoyé");
						
						
					}
								
				}
					

				}
					
				
		}
				
		
		
		public void start() {
			running = true;
			Thread t = new Thread(this);
			t.start();
		}
		
		public void stop() {
			running = false;
			
		}
		
		public String returnMap() {
			return map;
		}
		
		public boolean getReceived() {
			return received;
		}
		
		public boolean getCanGoNext() {
			return canGoNext;
		}
		
		public void setCanGoNext(boolean b) {
			this.canGoNext = b;
		}
}
