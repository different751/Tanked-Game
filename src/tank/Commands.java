package tank;
public class Commands
	{
	/*-----------------------------------------------------------------------------------------------------*/
	/* Use this method to control all the commands in the game.
	 * To issue a command, call NetworkControl.sendToAll(String string). This will send the command string to every user.
	 * Every user will then call this method on the respective system.
	 * Format of a command: ~ x y ~
	 * (x = a 2-letter acronym of the command, y = any string of characters that further describe the command, the last ~ is optional)
	 * Upon receiving a command/message via the socket, this method is called to process the command.
	 * This method is called for both server and client, as all machines variables will need to be in sync.
	 * If you need a distinction between server and client, you need to include an if statement in the processing of the command.
	 * For multi-line command process, please include a separate method and call that method from here.
	 * See examples below.
	 * */
	public static void processCommand(String string)
		{
		int n;
//		System.out.printf("processCommand: %s\n", string);
		if(string.charAt(0) == '~') /// String is a command
			{
			/// Network commands:
			if(string.charAt(1) == 'P' && string.charAt(2) == 'J') /// Player joins
				playerJoins(string);
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'E') /// Server exits
				NetworkControl.exitServer();
			else if(string.charAt(1) == 'C' && string.charAt(2) == 'E') /// Client exits
				NetworkControl.exitClient(Character.getNumericValue(string.charAt(3)));
			else if(string.charAt(1) == 'N' && string.charAt(2) == 'C') /// Name change
				Settings.playerName[Character.getNumericValue(string.charAt(3))] = string.substring(4, string.length());
			/// Setup commands:
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'D') /// Setup powerup interval
				setPowerupDuration(Character.getNumericValue(string.charAt(3)));
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'I') /// Setup powerup duration
				setPowerupInterval(Character.getNumericValue(string.charAt(3)));
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'L') /// Setup locators
				setLocators(Character.getNumericValue(string.charAt(3)));
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'N') /// Setup names
				setNames(string.substring(4, string.length()));
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'C') /// Setup colors
				setColors(string.substring(3, string.length()));
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'W') /// Setup win conditions
				{
				Settings.winCondition = Character.getNumericValue(string.charAt(3));
				Settings.highScoreTimerIndex = Character.getNumericValue(string.charAt(4));
				}
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'M') /// Setup map selected
				Settings.mapSelected = Character.getNumericValue(string.charAt(3));
			/// General game commands:
			else if(string.charAt(1) == 'L' && string.charAt(2) == 'G') /// Launch game
				launchGame();
			else if(string.charAt(1) == 'G' && string.charAt(2) == 'O') /// Game over
				{
				GameStats.winningTeam = Character.getNumericValue(string.charAt(3));
				GameStats.gameOver = C.YES;
				}
			else if(string.charAt(1) == 'G' && string.charAt(2) == 'P') /// Game paused
				{
				StatePlay.gamePaused = 1 - StatePlay.gamePaused; /// Toggles
				ResourceManager.getSound(Filenames.engine).stop();
				if(DisplaysPopupBox.popupDisplayed == C.YES) DisplaysPopupBox.popupEnd();
				}
			/// Powerups:
			else if(string.charAt(1) == 'C' && string.charAt(2) == 'M') /// Cheat mode
				Powerups.setMaxPowerups(Character.getNumericValue(string.charAt(3)));
			else if(string.charAt(1) == 'M' && string.charAt(2) == 'A' && string.charAt(3) == 'X') /// Mine x coordinate
				Powerups.minex = Integer.parseInt(string.substring(4, string.length()));
			else if(string.charAt(1) == 'M' && string.charAt(2) == 'A' && string.charAt(3) == 'Y') /// Mine y coordinate
				Powerups.miney = Integer.parseInt(string.substring(4, string.length()));
			else if(string.charAt(1) == 'M' && string.charAt(2) == 'A' && string.charAt(3) == 'P') /// Mine player ID
				Powerups.minePlayer = Character.getNumericValue(string.charAt(4));
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'A') /// Powerup activated
				Powerups.powerupActivation(Character.getNumericValue(string.charAt(3)), Character.getNumericValue(string.charAt(4)));
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'C') /// Powerup collision
				Powerups.powerupCollision(Character.getNumericValue(string.charAt(3)), Character.getNumericValue(string.charAt(4)));
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'T') /// Powerup true
				Powerups.powerupTrueCommand(string.substring(3, string.length()));
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'F') /// Powerup false
				Powerups.powerupFalseCommand();
			/// Movement:
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'M') /// Player movement
				Inputs.movement[Character.getNumericValue(string.charAt(3))] = Character.getNumericValue(string.charAt(4));
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'H') /// Player hull angle
				Inputs.hullangle[Character.getNumericValue(string.charAt(3))] = Integer.parseInt(string.substring(4, string.length()));
			/// Coordinates:
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'R') /// Player rotation
				Inputs.rotation[Character.getNumericValue(string.charAt(3))] = Character.getNumericValue(string.charAt(4));
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'V') /// Player x position
				Inputs.xpos[Character.getNumericValue(string.charAt(3))] = Integer.parseInt(string.substring(4, string.length()));
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'B') /// Player y position
				Inputs.ypos[Character.getNumericValue(string.charAt(3))] = Integer.parseInt(string.substring(4, string.length()));
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'X') /// Player x mouse position
				Inputs.xMouse[Character.getNumericValue(string.charAt(3))] = Integer.parseInt(string.substring(4, string.length()));
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'Y') /// Player y mouse position
				Inputs.yMouse[Character.getNumericValue(string.charAt(3))] = Integer.parseInt(string.substring(4, string.length()));
			/// Gameplay:
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'D') /// Player damage
				GameStats.playerDamage(Character.getNumericValue(string.charAt(3)), Character.getNumericValue(string.charAt(4)), Integer.parseInt(string.substring(5, string.length())));
			else if(string.charAt(1) == 'M' && string.charAt(2) == 'C') /// Mine collision
				{ /// Mine collision
				Powerups.mineCollision(Character.getNumericValue(string.charAt(3)));
				StatePlay.removemines(Integer.parseInt(string.substring(4, string.length())));
				}
			else if(string.charAt(1) == 'P' && string.charAt(2) == 'S') /// Player shot
				{
				float px = StatePlay.tanks[Character.getNumericValue(string.charAt(3))].getX();
				float py = StatePlay.tanks[Character.getNumericValue(string.charAt(3))].getY();
				double pr = StatePlay.tanks[Character.getNumericValue(string.charAt(3))].getTurretAngle();
				float mv = 10;
				//int l=5000;
				StatePlay.shots.add(new projectile(px, py, pr, mv, Settings.playerTeamColors[Character.getNumericValue(string.charAt(3))]));
				}
			else if(string.charAt(1) == 'R' && string.charAt(2) == 'S') /// Remove shot
				{
				StatePlay.removeshot(Integer.parseInt(string.substring(3, string.length())));
				}
			}
		else /// String is a chat message
			{
			NetworkControl.displayMessage(string);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendClientExitsCommand(int clientID)
		{
		NetworkControl.sendToAll("~CE" + Integer.toString(clientID));
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendServerExitsCommand()
		{
		NetworkControl.sendToClients("~SE");
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void playerJoins(String string)
		{
		int i;
		int activeIDs[] = new int[C.MAX_PLAYERS];
		int firstOpenID = -1;
		Settings.numberActivePlayers = 1;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			activeIDs[i] = Character.getNumericValue(string.charAt(i + 3));
			if(activeIDs[i] == C.YES) Settings.numberActivePlayers++;
			}
//		Settings.numberActivePlayers = activeIDs + 1;
		if(Settings.playerType == C.UNDECIDED) Settings.playerType = C.CLIENT;
		for(i = 1; i < C.MAX_PLAYERS; i++)
			{
			if(activeIDs[i] == C.NO)
				{
				firstOpenID = i;
				Settings.activeIDs[i] = C.YES;
				if(Settings.playerID == C.NO_ID) Settings.playerID = i;
				break;
				}
			}
		Settings.activeIDs[0] = C.YES;
		if(StateControl.currentState != StateControl.STATE_LOBBY)
			{
			StateControl.enterState(StateControl.STATE_LOBBY);
			}
		NetworkControl.displayMessage(Settings.playerName[firstOpenID] + " has joined the game");
//		System.out.printf("activePlayers = %d, active[0] = %d, active[1] = %d, active[2] = %d, active[3] = %d, Player type = %d, player ID = %d, player name = %s\n", Settings.numberActivePlayers, Settings.activeIDs[0], Settings.activeIDs[1], Settings.activeIDs[2], Settings.activeIDs[3], Settings.playerType, Settings.playerTeamColor, Settings.playerName[Settings.playerTeamColor]);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void launchGame()
		{
		StateControl.enterState(StateControl.STATE_PLAY);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setColors(String string)
		{
		int i;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			Settings.playerTeamColors[i] = Character.getNumericValue(string.charAt(i));
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setNames(String string)
		{
//		System.out.printf("setNames: %s\n", string);
		int names = 0; /// Number of names
		int characters = 0; /// Number of characters in name
		int current = 0; /// Current index position
		int start = 0; /// Starting index of name
		int end = 0; /// Ending index of name
		while(true)
			{
			characters = (Character.getNumericValue(string.charAt(current)) * 10) + Character.getNumericValue(string.charAt(current + 1));
			start = current + 2;
			end = start + characters;
//			System.out.printf("char = %d, start = %d, end = %d, name = %s\n", characters, start, end, string.substring(start, end));
			Settings.playerName[names] = string.substring(start, end);
			names++;
			current = end;
			if(string.charAt(current) == '~') break;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendLaunchGameCommand()
		{
		NetworkControl.sendToAll("~LG");
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendPlayerJoinedCommand()
		{
		int i;
		String string = "~PJ";
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			string += Settings.activeIDs[i];
			}
		NetworkControl.sendToAll(string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendNameChangeCommand(String string)
		{
		NetworkControl.sendToAll("~NC" + string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendSetNamesCommand()
		{
		int i;
		String string = "~";
		string += "SN";
		string += (Settings.numberActivePlayers + 1);
		for(i = 0; i < Settings.numberActivePlayers; i++)
			{
			if(Settings.playerName[i].length() < 10) /// Adds a leading zero if name length is less than 10
				string += "0";
			string += Settings.playerName[i].length();
			string += Settings.playerName[i];
			}
		string += "~";
		NetworkControl.sendToAll(string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendSetColorsCommand()
		{
		int i;
		String temp = "~SC";
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			temp += Settings.playerTeamColors[i];
			}
		NetworkControl.sendToAll(temp);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendPowerupIntervalCommand()
		{
		NetworkControl.sendToAll("~SI" + Powerups.powerupIntervalIndex);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setPowerupInterval(int index)
		{
		Powerups.powerupIntervalIndex = index;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendPowerupDurationCommand()
		{
		NetworkControl.sendToAll("~SD" + Powerups.powerupDurationIndex);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setPowerupDuration(int index)
		{
		Powerups.powerupDurationIndex = index;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendLocatorsCommand()
		{
		NetworkControl.sendToAll("~SL" + Settings.displayLocators);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setLocators(int setting)
		{
		Settings.displayLocators = setting;
		Settings.displayTankLocation = setting;
		Settings.displayPowerupSpawn = setting;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendSetMapCommand()
		{
		NetworkControl.sendToAll("~SM" + Settings.mapSelected);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendSetWinConditionCommand()
		{
		NetworkControl.sendToAll("~SW" + Settings.winCondition + Settings.highScoreTimerIndex);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static int charactersToInteger(char... c)
		{
		if(c.length == 0)
			{
			return C.INVALID;
			}
		int i;
		int multiplier = 1;
		int result = 0;
//		for(i = 0; i < c.length; i++)
//			System.out.printf("CharToInteger: %c\n", c[i]);
		for(i = c.length - 1; i >= 0; i--)
			{
			result += multiplier * Character.getNumericValue(c[i]);
			multiplier = multiplier * 10;
			}
//		System.out.printf("\n result = %d\n", result);
		return result;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}
