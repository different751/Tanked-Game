package tank;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class NetworkClientMain extends Thread
	{
	Socket socket; // declare a socket, represents the socket
	NetworkClientReadThread readThread; // declare a read thread
	Scanner input;
	public static PrintWriter printWriter;
	String ipAddress = null;
	/*-----------------------------------------------------------------------------------------------------*/
	NetworkClientMain(String ipAddress)
		{
		this.ipAddress = ipAddress;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void run() // start the socketClient
	{
	try
		{
		socket = new Socket(ipAddress, Settings.portnumber); // attempt to connect to the IP on portnumber
		readThread = new NetworkClientReadThread(socket); // initialize the read thread and pass the socket socket
		readThread.start(); // start the read thread
		printWriter = new PrintWriter(socket.getOutputStream(), true);
//		System.out.printf("Client joined game\n");
		}
	catch (Exception e)  // if there was a problem...
		{
		System.out.println("ClientMain Error:\n" + e.toString());
		e.printStackTrace();
		return;
		}
	}
	}