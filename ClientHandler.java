// Run this class with Server.java otherwise the code won't work
import java.io.*;
import java.util.*;
import java.net.*;
class ClientHandler implements Runnable
{
	Scanner scn = new Scanner(System.in);
	private String name;
	final DataInputStream datainputstream;
	final DataOutputStream dataoutputstream;
	Socket s;
	boolean isloggedin;

	public ClientHandler(Socket s, String name, DataInputStream datainputstream, DataOutputStream dataoutputstream)
	{
		this.datainputstream = datainputstream;
		this.dataoutputstream = dataoutputstream;
		this.name = name;
		this.s = s;
	}

	// Overriding the run method
	public void run()
	{
		String str_received;
		while(true)
		{
			try
			{
				// receive the string
				str_received = datainputstream.readUTF();
				System.out.println(str_received);

				// if received data is a particular message, then close the server
				if(str_received.equals("logout"))
				{
					this.isloggedin = false;
					this.s.close();
					break;
				}

				// breaking thre string into message and recipent part
				// so, in this program the specific structure of messaging is to be followed
				StringTokenizer str_tokenizer = new StringTokenizer(str_received, " ");
				String str_msgtosend = str_tokenizer.nextToken();
				String str_recipent = str_tokenizer.nextToken();

				// search for the recipent in the connected devices list
				for(ClientHandler mc : Server.clienthandler_array)
				{
					// if the recipent is found, write on its output stream
					if(mc.name.equals(str_recipent) && mc.isloggedin == true)
					{
						mc.dataoutputstream.writeUTF(this.name+":"+str_msgtosend);
						break;
					}

				}

			
			}catch(IOException e){
				e.printStackTrace();	
			}
		}
		try
		{
			this.datainputstream.close();
			this.dataoutputstream.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}

	}


}
