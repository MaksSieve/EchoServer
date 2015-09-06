package Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	
	public static String welcomeMsg = "Welcome to Sieve-Server!";
	public static String byeMsg = "Sieve-Server is closed! Good bye!";
	
	public static void main(String[] args)throws IOException {
		
		/*
		 * Initialization block
		 */
		
		//ArrayList<User> cientList = new ArrayList<User>();
		
		BufferedReader in = null;
		PrintWriter out = null;
		Scanner sc = null;
		
		int port = 0;
		ServerSocket Listener = null;
		Socket Client = null;
		
		sc = new Scanner(System.in);
		
		System.out.println(welcomeMsg);
		System.out.println("Server started");
		
		/*
		 * Main block 
		 */
		
		// Connecting to port
		while (true){
			try {
				System.out.println("Input number of Server's port");
				port = sc.nextInt();
				Listener = new ServerSocket(port);
				System.out.print(Listener.getInetAddress());
				break;
			}catch(IOException e) {
				System.out.println("Couldn't listen to port " + port + "! Try again.");
			}
		}
		sc.close();
		
		//Waiting client connections
		System.out.println();
		System.out.println("Waiting for clients' connections");
		while (true){
			try {
				Client = Listener.accept();
				System.out.println("Client "+ Client.getInetAddress()+" successfuly connected!");
				break;
			}catch(IOException e) {
				System.out.println("Accept fault! Waiting for another client...");
			}
		}
		
		in = new BufferedReader(new InputStreamReader(Client.getInputStream()));
		out = new PrintWriter(Client.getOutputStream(), true);
		
		String input;
		
		System.out.println("Wait for messages!");
		//out.println("");
		while((input = in.readLine()) != null){
			if (input.equalsIgnoreCase("exit")) break;
			out.println(Client.getInetAddress()+" ::: " + input);
			System.out.println("User "+Client.getInetAddress()+ ": "+input);
		}
		
		System.out.println(byeMsg);
		out.close();
		in.close();
		Client.close();
		Listener.close();
	}
}
