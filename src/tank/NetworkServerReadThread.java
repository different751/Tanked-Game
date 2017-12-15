package tank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
public class NetworkServerReadThread extends Thread // class for reading input from an input stream
	{
	Socket socket; // declare a socket
	String stringTemp; // declare a temporary string
	BufferedReader bufferedReader; // declare a buffered reader
	public static boolean terminated = false; // declare a flag indicating whether the socket (that buffered reader is reading from has closed
	/*-----------------------------------------------------------------------------------------------------*/
	NetworkServerReadThread(Socket s) // constructor for the class
		{
		this.socket = s; // assign the socket to the socket given in the argument for the constructor
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void run() // upon starting the thread...
		{
		try
			{
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // initialize buffered reader
			while(terminated == false && (stringTemp = bufferedReader.readLine()) != null) // while the socketClient has not logged out...
				{
				Commands.processCommand(stringTemp);
				NetworkControl.sendToClients(stringTemp);
//			NetworkControl.processCommandServer(stringTemp);
				}
			closeSocket();
			}
		catch (Exception e) // if there was a problem...
			{
			//System.out.printf("ServerReadThread Error:\n" + e.toString());
			//e.printStackTrace();
			Commands.sendClientExitsCommand(Settings.playerID);
			System.out.println("ServerReadThread: Client Disconnected");
			try
				{
				//closeSocket();
				bufferedReader.close();
				}
			catch (IOException e1)
				{
				e1.printStackTrace();
				}
			terminated = true;
			return;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void closeSocket() throws IOException
		{
		terminated = true; // set terminated to true, the socketClient has logged out
		socket.close(); // close the socket
		bufferedReader.close(); // close the buffered reader
		}
	}

