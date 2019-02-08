import java.io.*;
import java.util.*;
import java.net.*;

	//Server
public class Server
{	
	// We need to store active clients and that may vary
	static Vector<ClientHandler> clienthandler_array = new Vector<>();
	
	//counter for clients
	static int i = 0;

	public static void main(String[] args) throws IOException
	{
		// create a new server listening on some port
		ServerSocket ss = new ServerSocket(1234);

		Socket s;

		//let's run infinite loop to get client requests
		while(true)
		{
			// Accept any request
			s = ss.accept();

			System.out.println("Request accepted :" +s);

			//obtain input and output streams
			DataInputStream datainputstream = new DataInputStream(s.getInputStream());
			DataOutputStream dataoutputstream = new DataOutputStream(s.getOutputStream());

			// Create a new handler for this client

			ClientHandler clienthandler = new ClientHandler(s, "client"+i, datainputstream, dataoutputstream);

			// create a new thread with the above object
	//			Thread t = new Thread(clienthandler);

			System.out.println("Adding the client to active clients");
			clienthandler_array.add(clienthandler);

			// Start the thread
			clienthandler.run();

			// increment for the new client
			i++;


		
		
		
		}
	
	
	}




}
