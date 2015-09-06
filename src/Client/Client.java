package Client;

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Client {
	
	public static String welcomeMsg = "Welcome to Sieve-Client!";
	public static String connectionClosedMsg = "Connection is closed!";
	public static String byeMsg = "Client is closed. Good bye!";
	
	@SuppressWarnings("resource")
	public static void main(String[] args)throws IOException  {
		Socket Server = null;
		int port;
		Scanner sc;
		BufferedReader serverIn = null;
		BufferedReader userIn = null;
		PrintWriter out = null;
		
		
		System.out.println(welcomeMsg);
		/*
		if (args.length==0){
			System.out.println("use: client hostname");
			System.exit(-1);
		}
		*/
		System.out.println("Input number of server's port:");
		sc = new Scanner(System.in);
		port = sc.nextInt();
		
		System.out.println();
		System.out.println("Connecting to localhost"+/*args[0]+*/":"+port+" ...");
		
		Server = new Socket("localhost"/*args[0]*/,port);
		serverIn = new BufferedReader(new InputStreamReader(Server.getInputStream()));
		out = new PrintWriter(Server.getOutputStream(), true);
		userIn = new BufferedReader(new InputStreamReader(System.in));
		
		String userMsg, serverMsg;
		System.out.println("Connection sucessed!");
		while ((userMsg = userIn.readLine())!=null){
			out.println(userMsg);
			serverMsg  = serverIn.readLine();
			System.out.println(serverMsg);
			if(userMsg.equalsIgnoreCase("close")) break;
			if(userMsg.equalsIgnoreCase("exit")) break;
		}
		
		out.close();
		userIn.close();
		serverIn.close();
		Server.close();
		System.out.println(connectionClosedMsg);
		System.out.println(byeMsg);
	}

}
