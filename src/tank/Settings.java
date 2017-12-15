package tank;
import org.newdawn.slick.Color;
/* This class contains all the game settings */
public class Settings
	{
	public static int playMusic = C.YES;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Gameplay:
	/*-----------------------------------------------------------------------------------------------------*/
	/// Network:
	public static int portnumber = 1201;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Players:
	public static int playerType = C.UNDECIDED; /// Choices are undecided, socket, and socketClient
	public static int numberActivePlayers = 0; /// As players join game, this variable will change
	public static int numberTeams = 0;
	public static int playerID = C.NO_ID; /// ID of the player
	public static int activeIDs[] = new int[C.MAX_PLAYERS];
	public static String playerName[] = new String[C.MAX_PLAYERS]; /// As players join, they can log in with a name
	public static int maxCharactersName = 12;
	public static String sampleMaxName = "123456789012";
	/*-----------------------------------------------------------------------------------------------------*/
	/// Map:
	public static int mapSelected = 0;
	public static int displayMiniMap = C.YES;
	public static int displayLocators = C.YES;
	public static int displayPowerupSpawn = C.YES;
	public static int displayTankLocation = C.YES;
	public static int displayPlayersHealth = C.YES;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Sounds:
	public static int playButtonClick = C.YES;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Screen:
	public static int targetFrameRate = 60;
	public static int screenAdjustment = 100; /// When user presses key to resize screen, this is the increment
	/// Screen dimensions (Main):
	public static int mainScreenWidthStart = 1000;
	public static int mainScreenHeightStart = 750;
	public static int mainScreenWidth = mainScreenWidthStart;
	public static int mainScreenHeight = mainScreenHeightStart;
	public static int minMainScreenWidth = 700;
	public static int minMainScreenHeight = 600;
	public static int maxMainScreenWidth; /// Max screen dimensions are set by the container at launch
	public static int maxMainScreenHeight;
	/// Screen dimensions (Lobby):
	public static int lobbyScreenWidth = 1000;
	public static int lobbyScreenHeight = 750;
	public static int minLobbyScreenWidth = 1000;
	public static int minLobbyScreenHeight = 750;
	public static int maxLobbyScreenWidth;
	public static int maxLobbyScreenHeight;
	/// Screen dimensions (Play):
	public static int playScreenWidth = 1000;
	public static int playScreenHeight = 750;
	public static int minPlayScreenWidth = 800;
	public static int minPlayScreenHeight = 600;
	public static int maxPlayScreenWidth;
	public static int maxPlayScreenHeight;
	/// Screen dimensions current:
	public static int currentScreenHeight = mainScreenHeight;
	public static int currentScreenWidth = mainScreenWidth;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Player colors:
	public static Color allColors[] = {Color.red, Color.yellow, Color.green, Color.blue};
	public static int playerTeamColors[] = new int[C.MAX_PLAYERS];
	/*-----------------------------------------------------------------------------------------------------*/
	public static int winCondition = C.DEATHMATCH;
	public static int highScoreTimerIndex = 3;
	public static int highScoreTimerOptions[] = {1, 5, 10, 15, 20, 30}; /// Minutes
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initSettings()
		{
		int i;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			playerName[i] = Strings.defaultName + (i);
			playerTeamColors[i] = i;
			activeIDs[i] = C.NO;
			}
		}
	}
