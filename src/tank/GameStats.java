package tank;
/* This class is for holding the game stats information */
public class GameStats
	{
	/// To add a powerup:
	/// add the icon image to the res folder (100x100),
	/// add the filename of the image in Filenames class,
	/// load the resource in Tank class,
	/// add the type in GameStats.powerType array,
	/// add string to Strings.powerupIcons array
	/*-----------------------------------------------------------------------------------------------------*/
	public static int gameOver = C.NO;
	public static int winningTeam = -1;
	public static int maxHealthBase = 1000;
	public static int maxArmor = 10;
	public static int maxSpeed = 10;
//	public static int armorBase = 10;
	public static int powerBase = 5;
	public static int speedBase = 5;
	public static int scoreBase = 0;
	public static int levelBase = 1;
	///
	public static int scoreDamageInflicted = 1;
	public static int scoreDamageIncurred = -1;
	public static int scorePowerupCollected = 500;
	public static int scorePowerupActivated = -250;
	///
	public static int maxHealth[] = new int[C.MAX_PLAYERS];
	public static int health[] = new int[C.MAX_PLAYERS];
//	public static int armor[] = new int[C.MAX_PLAYERS];
	public static int power[] = new int[C.MAX_PLAYERS];
	public static int speed[] = new int[C.MAX_PLAYERS];
	public static int score[] = new int[C.MAX_PLAYERS];
	public static int level[] = new int[C.MAX_PLAYERS];

	public static int missileDamage = 300;
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initGameStats()
		{
		int i;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			maxHealth[i] = maxHealthBase;
			health[i] = maxHealthBase;
//			armor[i] = armorBase;
			power[i] = powerBase;
			speed[i] = speedBase;
			score[i] = scoreBase;
			level[i] = levelBase;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a player's health drops to 0 */
	public static void checkWinCondition()
		{
		int i;
		int winTeam = -1;
		if(Settings.winCondition == C.DEATHMATCH)
			{
			int numActive = 0;
			int playerDead[] = new int[Settings.numberActivePlayers];
			int activePlayers[] = new int[Settings.numberActivePlayers];
			/// Flags dead and active players:
			for(i = 0; i < Settings.numberActivePlayers; i++)
				{
				if(health[i] != 0) /// If player is alive
					{
					activePlayers[numActive] = i; /// Stores the ID of the active player
					numActive++;
					if(numActive >= (Settings.numberActivePlayers + 2) - Settings.numberTeams) /// Formula to determine impossibility of game being over
						return;
					}
				else /// player is dead
					playerDead[i] = C.YES;
				}
			/// Selects lone survivor if any:
			if(numActive == 1)
				{
				for(i = 0; i < Settings.numberActivePlayers; i++)
					{
					if(playerDead[i] == C.NO)
						{
						NetworkControl.sendToAll("~GO" + Settings.playerTeamColors[i]);
						return;
						}
					}
				}
			/// Selects lone surviving team if any:
			else
				{
				for(i = 0; i < numActive; i++)
					{
					if(i == numActive - 1) break;
					if(Settings.playerTeamColors[activePlayers[0]] != Settings.playerTeamColors[activePlayers[i + 1]])
						{
//					System.out.printf("Game active: comparison: color[%d] %d vs. color[%d] %d\n", activePlayers[0], Settings.playerTeamColors[activePlayers[0]], activePlayers[i+1], Settings.playerTeamColors[activePlayers[i+1]]);
						return; /// Comparison of team colors do not match, game is still active
						}
					else
						{
//					System.out.printf("Possible Win: comparison: color[%d] %d vs. color[%d] %d\n", activePlayers[0], Settings.playerTeamColors[activePlayers[0]], activePlayers[i+1], Settings.playerTeamColors[activePlayers[i+1]]);
						winTeam = Settings.playerTeamColors[activePlayers[0]]; /// Records matching color in case all comparisons are equal
						}
					}
				NetworkControl.sendToAll("~GO" + winTeam);
				}
			}
		else if(Settings.winCondition == C.HIGH_SCORE)
			{
			int highScore = -1;
			for(i = 0; i < Settings.numberActivePlayers; i++)
				{
				if(highScore < GameStats.score[i])
					{
					highScore = GameStats.score[i];
					winTeam = i;
					}
				}
			NetworkControl.sendToAll("~GO" + winTeam);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* A player calls this method when he receives damage */
	public static void sendPlayerDamageCommand(int attackerID, int defenderID, int damage)
		{
//		System.out.printf("sending playerDamage %d against %d for %d damage\n", attackerID, defenderID, damage);
		NetworkControl.sendToAll("~PD" + attackerID + defenderID + damage);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called via the network to process a player taking damage */
	public static void playerDamage(int attackerID, int defenderID, int damage)
		{
//		System.out.printf("playerDamage %d against %d for %d damage\n", attackerID, defenderID, damage);
		ResourceManager.getSound(Filenames.explosion).play(1, Inputs.volumeExplosion);
		DisplaysStatePlay.drawMissileExplosion(defenderID);
		if(Settings.winCondition == C.DEATHMATCH)
			{
			GameStats.health[defenderID] -= damage;
			if(GameStats.health[defenderID] <= 0)
				{
				GameStats.health[defenderID] = 0;
				GameStats.checkWinCondition();
				}
			}
		else if(Settings.winCondition == C.HIGH_SCORE)
			{
			/// No damage done in high score mode
			}
		score[defenderID] += scoreDamageIncurred * damage;
		if(attackerID != C.MINE) /// If damage came from a player and not a mine
			score[attackerID] += scoreDamageInflicted * damage;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void recordNumberTeams()
		{
		int i;
		int numTeams = 0;
		int colors[] = new int[Settings.allColors.length];
		for(i = 0; i < Settings.numberActivePlayers; i++)
			{
			colors[Settings.playerTeamColors[i]]++;
			}
		for(i = 0; i < colors.length; i++)
			{
			if(colors[i] > 0)
				numTeams++;
			}
		Settings.numberTeams = numTeams;
		}
	}
