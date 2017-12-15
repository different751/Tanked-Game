package tank;
/* This class is for holding all the final constant variables in the game */
public class C
	{
	/*-----------------------------------------------------------------------------------------------------*/
	public static final int NO_ID = -1;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Game variables:
	public static final int MAX_PLAYERS = 4;
	public static final int MINE = 9; /// Single digit number outside of the MAX_PLAYERS to indicate a mine
	/*-----------------------------------------------------------------------------------------------------*/
	/// Universal switches:
	public static final int INVALID = -1;
	public static final int OFF = 0;
	public static final int ON = 1;
	public static final int FALSE = 0;
	public static final int TRUE = 1;
	public static final int NO = 0;
	public static final int YES = 1;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Player colors:
	public static final int RED = 0;
	public static final int YELLOW = 1;
	public static final int GREEN = 2;
	public static final int BLUE = 3;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Area:
	public static final int X_COORDINATE = 0;
	public static final int Y_COORDINATE = 1;
	/*-----------------------------------------------------------------------------------------------------*/
	/// StringsDisplay:
	public static final int CENTER = -1;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Popup types:
	public static final int POPUP_NAME = 0;
	public static final int POPUP_IP_ADDRESS = 1;
	public static final int POPUP_CHAT = 2;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Win condition:
	public static final int DEATHMATCH = 0;
	public static final int HIGH_SCORE = 1;
	public static final int PROTECT_BASE = 2;
	public static final int FIND_RELIC = 3;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Player type:
	public static final int UNDECIDED = 0;
	public static final int SERVER = 1;
	public static final int CLIENT = 2;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Powerup type:
	public static final int CONSUMABLE = 0;
	public static final int TIMED = 1;
	public static final int PERMANENT = 2;

	public static final int POWERUP_HEALTH = 0;
	public static final int POWERUP_MINE = 1;
	public static final int POWERUP_SPEED = 2;
	public static final int POWERUP_ARMOR = 3;
	public static final int POWERUP_INVINCIBLE = 4;
	public static final int POWERUP_BEER = 5;
//	public static final int POWERUP_INVISIBLE = 6;

	public static final int BEER_OFF = 0;
	public static final int BEER_ON = 1;
	public static final int BEER_RECOVERY = 2;
	}
