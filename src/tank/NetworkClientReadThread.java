package tank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
public class NetworkClientReadThread extends Thread // class for reading input from an input stream
	{
	Socket socket; // declare a socket
	String stringTemp = null; // declare a temporary string
	BufferedReader bufferedReader; // declare a buffered reader
	public static boolean terminated = false; // declare a flag indicating whether the socketClient wants to logout
	/*-----------------------------------------------------------------------------------------------------*/
	NetworkClientReadThread(Socket socket) // constructor for the class
	{
	this.socket = socket; // assign the socket to the socket given in the argument for the constructor
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public void run() // upon starting the thread...
	{
	try
		{
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // initialize the buffered reader
		while(terminated == false && (stringTemp = bufferedReader.readLine()) != null) // while the client has not logged out...
			{
			Commands.processCommand(stringTemp);
//			NetworkControl.processCommandClient(stringTemp);
			}
		}
	catch (Exception e) // if there was a problem...
		{
//		System.out.printf("ClientReadThread Error:\n" + e.toString());
//		e.printStackTrace();
//		terminated = true;
		NetworkControl.exitServer();
		return;
		}
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public void interrupt() // method to interrupt the thread
	{
	terminated = true; // set the flag to true indicating the client wants to logout
	}
	}