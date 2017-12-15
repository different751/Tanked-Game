package tank;
import java.io.IOException;
import java.io.PrintWriter;
/* This class is to handle the networking of the game. */
public class NetworkControl
	{
	public static NetworkServerMain serverMain;
	public static NetworkClientMain clientMain;
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a player clicks on button to host a game */
	public static void setupServer()
		{
//		System.out.printf("setupServer\n");
		serverMain = new NetworkServerMain();
		serverMain.start();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a player clicks on button to join a game and enters an ip address to connect to*/
	public static void setupClient(String ipAddress)
		{
//		System.out.printf("setupClient: ipAddress = %s\n", ipAddress);
		clientMain = new NetworkClientMain(ipAddress);
		clientMain.start();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a server clicks on button to leave game or closes application */
	public static void exitServer()
		{
		StateControl.exitProgram();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a client clicks on button to leave game or closes application */
	public static void exitClient(int clientID)
		{
//		System.out.printf("exitClient: %d\n", clientID);
		StateControl.exitProgram();
//		System.out.printf("activePlayers = %d, active[0] = %d, active[1] = %d, active[2] = %d, active[3] = %d, Player type = %d, player ID = %d, player name = %s\n", Settings.numberActivePlayers, Settings.activeIDs[0], Settings.activeIDs[1], Settings.activeIDs[2], Settings.activeIDs[3], Settings.playerType, Settings.playerTeamColor, Settings.playerName[Settings.playerTeamColor]);
//		displayMessage(Settings.playerName[clientID] + " has left the game");
//
//		if(Settings.playerTeamColor == clientID)
//			{
//			StateControl.exitProgram();
//			}
//		else if(Settings.playerType == C.SERVER)
//			{
//			NetworkServerWriteThread.closeWriter(clientID);
//			}
//
//		Settings.activeIDs[clientID] = C.NO;
//		Settings.playerName[clientID] = Strings.defaultName + clientID;
//		Settings.numberActivePlayers--;
//		System.out.printf("activePlayers = %d, active[0] = %d, active[1] = %d, active[2] = %d, active[3] = %d, Player type = %d, player ID = %d, player name = %s\n", Settings.numberActivePlayers, Settings.activeIDs[0], Settings.activeIDs[1], Settings.activeIDs[2], Settings.activeIDs[3], Settings.playerType, Settings.playerTeamColor, Settings.playerName[Settings.playerTeamColor]);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* The server will all this method when he is successful in hosting a game */
	public static void successServer()
		{
//		System.out.printf("successServer\n");
		Settings.playerType = C.SERVER;
		Settings.numberActivePlayers++;
		Settings.playerID = 0;
		Settings.activeIDs[0] = C.YES;
		StateControl.enterState(StateControl.STATE_LOBBY);
		displayMessage(Settings.playerName[Settings.playerID] + " is hosting the game");
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* The server will call this method when a client successful joins but there is no more room in the game */
	public static void rejectClientJoin()
		{
//		System.out.printf("rejectClientJoin\n");
//		Commands.sendGetPlayersCommand(Settings.numberActivePlayers);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* The server will call this method when a client successful joins the game and there is room */
	public static void successClient()
		{
//		System.out.printf("successClient\n");
		Commands.sendPlayerJoinedCommand();
		Commands.sendSetNamesCommand();
		Commands.sendSetWinConditionCommand();
		Commands.sendSetMapCommand();
		Commands.sendSetColorsCommand();
		Commands.sendLocatorsCommand();
		Commands.sendPowerupIntervalCommand();
		Commands.sendPowerupDurationCommand();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called to display a chat message on the screen */
	public static void displayMessage(String string)
		{
		DisplaysStateLobby.displayMessage(string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Call this method to send a command or a chat message to everyone */
	public static void sendToAll(String string)
		{
//		System.out.printf("sendToAll: %s\n", string);
		if(Settings.playerType == C.SERVER)
			{
			/// A server will process the command on his side and then send the command to all clients
			sendToClients(string);
			Commands.processCommand(string);
			}
		else
			{
			/// A client will simply send the command to the server, who will in turn process the command and send it to all clients
			sendToServer(string);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called to send commands and messages to all the clients */
	public static void sendToClients(String string)
		{
//		System.out.printf("sendToClients: %s\n", string);
		for(PrintWriter writer : NetworkServerMain.writers)
			{
			if(writer.checkError()==false)
				writer.println(string);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called to send commands and messages to the server */
	public static void sendToServer(String string)
		{
//		System.out.printf("sendToServer: %s\n", string);
		NetworkClientMain.printWriter.println(string);
		}
	}
