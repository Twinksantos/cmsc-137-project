import java.net.*;
import java.io.*;
import java.util.*;

public class GameClient {
	public static void main(String[] args){
            boolean connected = true;
		try{
                  while(connected){
                        String serverName = args[0]; //get IP address of server from first param
                        int port = Integer.parseInt(args[1]); //get port from second param

                        /* Open a ClientSocket and connect to ServerSocket */
                        System.out.println("Connecting to " + serverName + " on port " + port);

                        //creating a new socket for client and binding it to a port
                        Socket server = new Socket(serverName, port);

                        System.out.println("Just connected to " + server.getRemoteSocketAddress());

                        /* Receive data from the ServerSocket */
                        InputStream inFromServer = server.getInputStream();
                        DataInputStream in = new DataInputStream(inFromServer);
                        System.out.println("Server says: " + in.readUTF());

                        Scanner userName = new Scanner(System.in);
                        String name = userName.nextLine();

                        /* Send data to the ServerSocket */
                        OutputStream outToServer = server.getOutputStream();
                        DataOutputStream out = new DataOutputStream(outToServer);
                        out.writeUTF("Player's name: " + name);


                        //closing the socket of the client
                        server.close();
                  }
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("Cannot find (or disconnected from) Server");
		}catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java GameClient <server ip> <port no.>");
        }
	}
}