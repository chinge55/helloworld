import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
	//private int ServerPort = 1234;
	//private InetAddress ip = InetAddress.getByName("localhost");
	Socket s = null;
	DataInputStream datainputstream = null;
	DataOutputStream dataoutputstream = null;

	public Client()
	{
		Scanner scanner = new Scanner(System.in);
		try
		{
			s= new Socket("127.0.0.1", 1234);
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			datainputstream = new DataInputStream(s.getInputStream());
			dataoutputstream = new DataOutputStream(s.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		Thread sendmessage = new Thread(new Runnable()
                        {
                                //Override
                                public void run()
                                {
                                        while(true)
                                        {
                                                // read the message to del$
                                                String str_msg = scanner.nextLine();
                                                try
                                                {
                                                        dataoutputstream.writeUTF(str_msg);
                                                }catch(IOException e)
                                                {
                                                        e.printStackTrace();
                                                }
                                        }
                                }       

                        });

	

 		Thread readMessage = new Thread(new Runnable()
                        {
                                //Override
                                public void run()
                                {
                                        while(true)
                                        {
                                                // read the message to be $
                                                String str_msg_taken = "";
                                                try{
                                                        str_msg_taken = datainputstream.readUTF();
                                                        System.out.println(str_msg_taken);
                                                }catch(IOException e)
                                                {
                                                        e.printStackTrace();
                                                }
                                        }
                                }
                        });
        sendmessage.run();
        readMessage.run();
	}
	public static void main(String[] args)
	{
		Client client = new Client();	
	}

}


