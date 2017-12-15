package tank;
/* This class holds all the strings in the game */
public class Strings
	{
	public static final String offOn[] = {"Off", "On"};
	/*-----------------------------------------------------------------------------------------------------*/
	/// Splash State:
	public static final String cs447 = "CS447 FINAL PROJECT";
	/*-----------------------------------------------------------------------------------------------------*/
	/// Main State:
	public static final String gameTitle = "Tanked";
	public static final String authorsList[] = {"Samuel Riesterer", "Benjamin DeCamp", "Sage Thongsrisubskul", "Tyler Coy"};
	public static final String hostGame = "Host Game";
	public static final String exitHost = "Exit Host";
	public static final String joinGame = "Join Game";
	public static final String exitJoin = "Exit Join";
	public static final String defaultName = "Player";
	public static final String selectMap = "Select Map";
	public static final String networkMessages[] = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
	public static final String networkMessagePrompt = ">>";
	/*-----------------------------------------------------------------------------------------------------*/
	/// Lobby State:
	public static final String lobbyHeading = gameTitle + " Lobby";
	public static final String players = "Players";
	public static final String id = "ID";
	public static final String name = "Name";
	public static final String team = "Team";
	public static final String joined = "Joined";
	public static final String map = "Map";
	public static final String check = "X";
	public static final String helpInfo = "Help/Info";
	public static final String minimap = "Minimap";
	public static final String settings = "Settings";
	public static final String locators = "Locators";
	public static final String powerupInterval = "PU Wait";
	public static final String powerupDuration = "PU Appear";
	public static final String leaveGame = "Leave Game";
	public static final String launchGame = "Launch Game";
	public static final String pressEnter = "Press <Enter> to send chat message";
	public static final String minutes = "Minutes";
	/*-----------------------------------------------------------------------------------------------------*/
	/// Setup Win Conditions State:
	public static final String winCondition = "Win Condition";
	public static final String deathmatch = "Deathmatch";
	public static final String highScore = "High Score";
	public static final String protectBase = "Protect Base";
	public static final String findRelic = "Find Relic";
//	public static final String winConditionTypes[] = {deathmatch, highScore, protectBase, findRelic};
	public static final String winConditionTypes[] = {deathmatch, highScore};
	/*-----------------------------------------------------------------------------------------------------*/
	/// Popup:
	public static final String enterName = "Enter Name:";
	public static final String enterIPAddress = "Enter IP Address:";
	public static final String enterMessage = "Enter message:";
	public static final String esc = "Esc";
	public static final String max = "Max";
	public static final String clear = "Clear";
	public static final String back = "Back";
	public static final String enter = "Enter";
	/*-----------------------------------------------------------------------------------------------------*/
	// @formatter:off
	/// Select Map State:
	public static final String miniMap[] = {
		"This is a description of map 1.\nThis is a description of map 1.\nThis is a description of map 1.\nThis is a description of map 1.",
		"This is a description of map 2.\nThis is a description of map 2.\nThis is a description of map 2.\nThis is a description of map 2.",
		"This is a description of map 3.\nThis is a description of map 3.\nThis is a description of map 3.\nThis is a description of map 3.",
	};
	/*-----------------------------------------------------------------------------------------------------*/
	/// Play State:
//	public static final String powerups[] = {"Health", "Mines", "Speed", "Power", "Invincible", "Invisible"};
	public static final String powerups[] = {"Health", "Mines", "Speed", "Armor", "Invincible", "Beer"};
	public static final String gamePaused = "Game Paused - Press <Enter> to Send Message";
	public static final String gameOver = "Game Over";
	public static final String colors[] = {"Red", "Yellow", "Green", "Blue"};
	public static final String wins = " Team Wins!";
	/*-----------------------------------------------------------------------------------------------------*/
	/// Help State:
	public static final String gameControls = "Game Controls";
	public static final String gameplay = "Gameplay";
	public static final String credits = "Credits/Licensing";
	public static final String authors = "Authors";
	public static final String licenses = "Licenses";
	public static final String licensesText[] = {
		"https://github.com/bjorn/tiled/blob/master/LICENSE.GPL",
		"http://pngimg.com/download/1308",
		"http://www.fontspace.com/",
		"https://opengameart.org/",
		"http://www.reinerstilesets.de/lizenz/",
		"https://freesound.org/",
		"https://www.iconspng.com/",
		"https://modarchive.org/index.php?faq-licensing"
	};
	/*-----------------------------------------------------------------------------------------------------*/
	public static final String gameControlsText = String.format("" +
		"#    : Number keys change screen size (outside of gameplay screen)\n\n" +

		"W    : Forwards\n" +
		"S    : Reverse\n" +
		"A    : Rotate tank left\n" +
		"D    : Rotate tank right\n" +
		"M    : Toggle minimap display\n" +
		"H    : Toggle players health bar display\n" +
		"Mouse: Aim turret\n" +
		"LMB  : Fire\n\n" +

		"#    : Number keys activate powerups\n" +
		"F#   : Function keys add powerup to inventory (cheat)\n" +
		"F10  : Toggle intro music\n" +
		"F11  : All powerups = max (cheat)\n" +
		"F12  : Debug mode\n\n" +

		"Space: Pause/Unpause game\n" +
		"Enter: Brings up message box (Type slowly :-)\n" +
		"");
	/*-----------------------------------------------------------------------------------------------------*/
	public static final String gamePlayText = String.format("" +
			"GAMEPLAY\n\n" +

			"Welcome to %s!\n" +
			"This game is a networked tank game for up to %d players. Teams can be assigned, represented by a color. " +
			"For controls, see Help section for controls.\n\n" +

			"NETWORK\n\n" +

			"To host a game, simply click on host game." +
			"To join a game, click join game and enter the IP address of the host who is running the game. The host " +
			"can modify the game settings and launch when ready.\n\n" +

			"WIN CONDITIONS\n\n" +

			"%s: Be the last tank/team standing!\n" +
			"%s: Tanks do not die. Have the top score when the timer goes out!\n" +
//			"%s: Destroy your enemies' base. Have the last remaining base standing to win!\n" +
//			"%s: Find the hidden relic to win!\n" +
			"\n" +

			"LOCATORS\n\n" +

			"Locators on will display each tank's location as a dot in the minimap. It will also display a black dot " +
			"representing a powerup spawn on the map\n\n" +

			"POWERUPS\n\n" +

			"%s:     Adds %d health\n" +
			"%s:      Plants a mine on the map of your team color. Does %d damage to enemy tanks.\n" +
			"%s:      Increases tank speed by %d for %d seconds\n" +
			"%s:      Increases tank armor by %d for %d seconds\n" +
			"%s: Cannot be harmed for %d seconds\n" +
			"%s:       Invincible and increased stats for %d seconds but low stats afterwards for %d seconds\n" +
			"            (Cannot activate any other powerups except mines while in beer mode)\n\n" +

			"%s:    The time in seconds a powerup will appear after the previous one disappeared\n" +
			"%s:  The time is seconds a powerup will appear on the screen\n\n" +

			"SCORE\n\n" +

			"Score is calculated as follows:\n\n" +

			"%d    point(s) for every 1 point damage you inflict\n" +
			"%d   point(s) for every 1 point damage you incur\n" +
			"%d  point(s) for every powerup you collect\n" +
			"%d point(s) for every powerup you activate\n" +

			"",
		gameTitle, C.MAX_PLAYERS,
		winConditionTypes[0], winConditionTypes[1],
//		winConditionTypes[2], winConditionTypes[3],
		powerups[0], Powerups.healthIncrease,
		powerups[1], Powerups.mineDamage,
		powerups[2], Powerups.speedBurst, Powerups.speedBurstTime,
		powerups[3], Powerups.armorBurst, Powerups.armorBurstTime,
		powerups[4], Powerups.invincibleBurstTime,
		powerups[5], Powerups.beerTime, Powerups.beerRecoveryTime,
		powerupInterval, powerupDuration,
		GameStats.scoreDamageInflicted, GameStats.scoreDamageIncurred, GameStats.scorePowerupCollected, GameStats.scorePowerupActivated
	);
	// @formatter:on
	/*-----------------------------------------------------------------------------------------------------*/
	/// Keyboard shortcuts:
	/*-----------------------------------------------------------------------------------------------------*/
	public static String[] formatString(String string, int screenWidth, int screenHeight)
		{
		/// Given a string and a screen size, will breakup the string into lines and pages and put the pages
		/// into an array, all based on the screen size and the size of the font
		int currentPos;
		char c;
		int needToContinue = C.NO;
		int pagesCurrent = 0;
		int linesPerPage = screenHeight / 20;
		int charPerLine = screenWidth / 9;
		int linesLeft = linesPerPage;
		int charLeft = charPerLine;
		int firstLetterPos = 0;
		String stringArray[] = new String[20];
		StringBuilder sb = new StringBuilder("");
//		System.out.printf("charPerLine = %d; linesPerPage = %d\n", charPerLine, linesPerPage);
//		System.out.print("\n\n****************************************\n");
		for(currentPos = 0; currentPos < string.length(); currentPos++)
			{
			c = string.charAt(currentPos);
			/// Beginning of new line so record first letter position of line:
			if(charLeft == charPerLine)
				{
//				System.out.printf("*** First letter pos = %d\n\n", currentPos);
//				System.out.print(c);
				firstLetterPos = currentPos;
				}
			/// Newline character is at the beginning of the page so disregard it:
			if(linesLeft == linesPerPage && firstLetterPos == currentPos && c == '\n')
				{
//				System.out.print("*** Newline at beginning of page\n");
//				System.out.print(c);
				continue;
				}
			if(c != '\n' && c != '~')
				{
//				System.out.print(c);
				charLeft--;
				}
			/// No more characters can fit in the current line so append string and increment line:
			if(charLeft == 0)
				{
				for(; currentPos > firstLetterPos; currentPos--) /// Move backwards until a space is encountered
					{
//					System.out.printf("*** CurrentPos = %d\n\n", currentPos);
					c = string.charAt(currentPos);
					if(c == ' ')
						{
						sb.append(string.substring(firstLetterPos, currentPos)); /// Append from firstLetterPos to currentPos, thereby excluding the space
						sb.append('\n');
						linesLeft--;
//						System.out.printf("*** New line: Lines left = %d\n", linesLeft);
						charLeft = charPerLine;
//						needToContinue = C.YES;
						break;
						}
					}
				}
			/// Encountered a newline:
			if(c == '\n')
				{
//				System.out.printf("*** Encountered a newline character\n\n");
				sb.append(string.substring(firstLetterPos, currentPos + 1)); /// Append from firstLetterPos to currentPos + 1, which includes the newline
				linesLeft--;
//				System.out.printf("*** New lineC: Lines left = %d\n", linesLeft);
				charLeft = charPerLine;
				}
			/// Start a new page:
			if(linesLeft == 0 || currentPos == string.length() - 1)
				{
//				System.out.printf("\n\n*** New page: %d\n\n", pagesCurrent);
//				System.out.printf("%d: %socket", pagesCurrent, sb.toString());
				stringArray[pagesCurrent] = sb.toString(); /// Save the entire stringBuilder to array index
				sb = new StringBuilder(""); /// Start a new stringBuilder
				pagesCurrent++;
				charLeft = charPerLine;
				linesLeft = linesPerPage;
				continue;
				}
			/// ~# is a marker in string where # is a number to signify the next number of lines need to stay together.
			if(c == '~')
				{
//				System.out.printf("*** Found ~:\n\n");
				if(Character.getNumericValue(string.charAt(currentPos + 1)) > linesLeft) /// If there is not room in the page to print the next number of lines
					{
//					System.out.printf("%d: %socket", pagesCurrent, sb.toString());
					stringArray[pagesCurrent] = sb.toString(); /// Save the entire stringBuilder to array index
					sb = new StringBuilder(""); /// Start a new stringBuilder
					currentPos++; /// Skip over the number after the '~' marker
					pagesCurrent++;
					charLeft = charPerLine;
					linesLeft = linesPerPage;
					continue;
					}
				else /// There is room to print the next number of liens together
					{
					currentPos++; /// Skip over the number after the '~' marker
					continue;
					}
				}
			}
		String returnArray[] = new String[pagesCurrent];
//		System.out.print("\n********************************************\n");
		for(int i = 0; i < pagesCurrent; i++)
			{
			returnArray[i] = stringArray[i];
//			System.out.printf("\nPage %d:\n\n%socket", i, returnArray[i]);
			}
		return returnArray;
		}
	}
