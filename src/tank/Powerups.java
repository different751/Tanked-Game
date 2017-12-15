package tank;
import jig.Entity;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
/// Powerups:
/// To change, add, or remove powerups, change the 3 arrays:
/// 1) Filenames.powerupIcons
/// 2) Strings.powerups
/// 3) Powerups.powerupType
/// Adjust static final variables in C class
/// Change the inputs in Inputs.processKeyboardInput for StatePlay
/// Adjust gameplay text in Strings class
/// Add the functionality in Powerups.powerupActivation and Powerups.powerupDeactivation
public class Powerups extends Entity
	{
	public static int maxPowerups = 99;
	/// Powerup display:
	public static boolean powerupFlag = false; /// Controls the presence of the powerup on the screen
	public static float iconScale = .2f;
	public static int powerupVisible;
	public static int powerMinimapX;
	public static int powerMinimapY;
	public static int powerx = 0; // powerups x location
	public static int powery = 0; // powerups y location
	public static int minex = 0;
	public static int miney = 0;
	public static int minePlayer = 0;
	/// Powerup life cycle:
	public static int powerupElapsedTime = 0; /// The time in seconds the powerup is on its cycle
//	public static int powerupInterval = 20; /// The interval in seconds a powerup will appear after the previous disappeared
//	public static int powerupDuration = 20; /// The seconds the powerup will remain on the screen
	public static int powerupIntervalIndex = 2;
	public static int powerupDurationIndex = 2;
	public static int powerupIntervalOptions[] = {5, 10, 20, 30, 60}; /// Seconds
	public static int powerupDurationOptions[] = {5, 10, 20, 30, 60}; /// Seconds
	/// Powerup coord
	public static int powerupIndex = 0; /// The index of the powerup image in Filenames
	/// Powerup statuses:
//	public static int powerupType[] = {C.CONSUMABLE, C.CONSUMABLE, C.TIMED, C.TIMED, C.TIMED, C.TIMED}; /// Every powerup has a type (CONSUMABLE, TIMED, OR PERMANENT)
	public static int powerupType[] = {C.CONSUMABLE, C.CONSUMABLE, C.TIMED, C.TIMED, C.TIMED, C.TIMED}; /// Every powerup has a type (CONSUMABLE, TIMED, OR PERMANENT)
	public static int numPowerups[][] = new int[C.MAX_PLAYERS][Strings.powerups.length]; /// The amount of collectible powerupIcons the player has
	public static int timePowerup[][] = new int[C.MAX_PLAYERS][Strings.powerups.length]; /// How many seconds are left till timed powerup is unactive
	//	public static int numActivatedPowerups[][] = new int[C.MAX_PLAYERS][Strings.powerups.length]; /// The number of activated powerups at any given time
	/// Powerup modes:
	public static int isInvincible[] = new int[C.MAX_PLAYERS]; /// This variable will determine if the player takes damage or not
	public static int invinciblePowerupActivated[] = new int[C.MAX_PLAYERS]; /// This variable is a switch if the player activated invincible (as opposed to the beer powerup)
	public static int beerMode[] = new int[C.MAX_PLAYERS]; /// Beer is either off, on, or in recovery
	public static int speedIncreaseCumulative[] = new int[C.MAX_PLAYERS];
	public static int powerIncreaseCumulative[] = new int[C.MAX_PLAYERS];
	public static int beerSpeedIncreaseCumulative[] = new int[C.MAX_PLAYERS];
	public static int beerPowerIncreaseCumulative[] = new int[C.MAX_PLAYERS];
	/// Powerup times:
	public static int speedBurstTime = 30; /// Speed powerup time in seconds
	public static int armorBurstTime = 30; /// Power powerup time in seconds
	public static int invincibleBurstTime = 15; /// Invincible powerup time in seconds
	public static int beerTime = 20; /// Beer powerup time in seconds
	public static int beerRecoveryTime = 20; ///
	/// Powerup increases/decreases:
	public static int healthIncrease = (int) (GameStats.maxHealthBase * 0.10); /// How much health is restored when activating the health powerup
	public static int mineDamage = (int) (GameStats.maxHealthBase * 0.10); /// How much damage colliding with a mine will do
	public static int speedBurst = 3; /// How much additional speed when activating the speed powerup
	public static int armorBurst = 3; /// How much additional power when activating the power powerup
	public static int beerOnSpeedBurst = 3;
	public static int beerOnPowerBurst = 3;
	public static int beerRecoverySpeedDrop = 3;
	public static int beerRecoveryPowerDrop = 3;
	/*-----------------------------------------------------------------------------------------------------*/
	public Powerups(final float x, final float y)
		{
		super(x, y);
		ResourceManager.loadImage(Filenames.powerupIcons[powerupIndex]);
		addImageWithBoundingBox(ResourceManager.getImage(Filenames.powerupIcons[powerupIndex]).getScaledCopy(iconScale));
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Sends powerup display command if user is the server and the powerup display cycle has begun */
	public static void sendPowerupStatus()
		{
		if(Settings.playerType == C.SERVER)
			{
			if((powerupElapsedTime == powerupIntervalOptions[powerupIntervalIndex]) && powerupFlag == false)
				{
				int xcoord = ThreadLocalRandom.current().nextInt(200, (DisplaysStatePlay.camera.worldWitdth - 200) + 1);
				int ycoord = ThreadLocalRandom.current().nextInt(200, (DisplaysStatePlay.camera.worldHeight - 200) + 1);
				int index = ThreadLocalRandom.current().nextInt(0, Filenames.powerupIcons.length);
				NetworkControl.sendToAll("~PT" + xcoord + "," + ycoord + "," + index);
				}
			if((powerupElapsedTime == powerupIntervalOptions[powerupIntervalIndex] + powerupDurationOptions[powerupDurationIndex]) && powerupFlag == true)
				{
				NetworkControl.sendToAll("~PF");
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Checks for collision with the displayed powerup */
	public static void checkPowerupCollision()
		{
		for(int i = 0; i < Settings.numberActivePlayers; i++)
			{
			if(powerupFlag == true && StatePlay.powerupEntity.collides(StatePlay.tanks[i]) != null)
				{
				tank.ResourceManager.getSound(Filenames.ding).play(1f, Inputs.volumePowerupCollision);
				NetworkControl.sendToAll("~PC" + Settings.playerID + powerupIndex);
				NetworkControl.sendToAll("~PF");
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called for every user to process someone colliding with a powerup */
	public static void powerupCollision(int playerID, int powerupIndex)
		{
		GameStats.score[playerID] += GameStats.scorePowerupCollected;
		if(numPowerups[playerID][powerupIndex] < maxPowerups) /// Only adds to inventory if not at cap
			{
			numPowerups[playerID][powerupIndex]++;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* When a player activates a powerup, that player should call this method */
	public static void sendPowerupActivation(int powerupIndex)
		{
		if(numPowerups[Settings.playerID][powerupIndex] > 0) /// Only activates if player has one in their inventory
			{
			if(powerupIndex == C.POWERUP_SPEED && ResourceManager.getSound(Filenames.engine).playing()) /// Stops engine sound so pitch can recalibrate
				ResourceManager.getSound(Filenames.engine).stop();
			else if(powerupIndex == C.POWERUP_MINE) Powerups.sendMineCoordinates();
			NetworkControl.sendToAll("~PA" + Settings.playerID + powerupIndex);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called for every user to process someone activating a powerup */
	public static void powerupActivation(int playerID, int powerupIndex)
		{
		if(powerupIndex == C.POWERUP_HEALTH)
			{
			/// Add health but not to exceed max health:
			GameStats.health[playerID] = (GameStats.health[playerID] + healthIncrease) > GameStats.maxHealth[playerID] ? GameStats.maxHealth[playerID] : (GameStats.health[playerID] + healthIncrease);
			}
		else if(powerupIndex == C.POWERUP_MINE)
			{
			StatePlay.mines.add(new projectile(minex, miney, 0, 0, minePlayer));
			}
		else if(powerupIndex == C.POWERUP_SPEED)
			{
			/// Increase speed but not to exceed max speed:
			int n = GameStats.maxSpeed - GameStats.speed[playerID] < speedBurst ? GameStats.maxSpeed - GameStats.speed[playerID] : speedBurst;
			speedIncreaseCumulative[playerID] += n;
			GameStats.speed[playerID] += n;
			timePowerup[playerID][powerupIndex] += speedBurstTime;
//			numActivatedPowerups[playerTeamColor][powerupIndex]++;
			}
		else if(powerupIndex == C.POWERUP_ARMOR)
			{
			/// Increase power but not to exceed max power:
			int n = GameStats.maxArmor - GameStats.power[playerID] < armorBurst ? GameStats.maxArmor - GameStats.power[playerID] : armorBurst;
			powerIncreaseCumulative[playerID] += n;
			GameStats.power[playerID] += n;
			timePowerup[playerID][powerupIndex] += armorBurstTime;
//			numActivatedPowerups[playerTeamColor][powerupIndex]++;
			}
		else if(powerupIndex == C.POWERUP_INVINCIBLE)
			{
			isInvincible[playerID] = C.YES;
			timePowerup[playerID][powerupIndex] += invincibleBurstTime;
			invinciblePowerupActivated[playerID] = C.YES;
//			numActivatedPowerups[playerTeamColor][powerupIndex]++;
			}
		else if(powerupIndex == C.POWERUP_BEER)
			{
			isInvincible[playerID] = C.YES;
//			numActivatedPowerups[playerTeamColor][powerupIndex]++;
			int s = GameStats.maxSpeed - GameStats.speed[playerID] < beerOnSpeedBurst ? GameStats.maxSpeed - GameStats.speed[playerID] : beerOnSpeedBurst;
			beerSpeedIncreaseCumulative[playerID] += s;
			GameStats.speed[playerID] += s;
			int p = GameStats.maxArmor - GameStats.power[playerID] < beerOnPowerBurst ? GameStats.maxArmor - GameStats.power[playerID] : beerOnPowerBurst;
			beerPowerIncreaseCumulative[playerID] += p;
			GameStats.power[playerID] += p;
//			GameStats.speed[playerTeamColor] = (GameStats.speed[playerTeamColor] + beerOnSpeedBurst) > GameStats.maxSpeed ? GameStats.maxSpeed : (GameStats.speed[playerTeamColor] + beerOnSpeedBurst);
//			GameStats.power[playerTeamColor] = (GameStats.power[playerTeamColor] + beerOnPowerBurst) > GameStats.maxArmor ? GameStats.maxArmor : (GameStats.power[playerTeamColor] + beerOnPowerBurst);
			if(beerMode[playerID] == C.BEER_RECOVERY) /// If beer is drunk while in recovery mode
				{
				timePowerup[playerID][powerupIndex] = beerTime;
				GameStats.speed[playerID] += beerRecoverySpeedDrop;
				GameStats.power[playerID] += beerRecoveryPowerDrop;
				}
			else
				{
				timePowerup[playerID][powerupIndex] += beerTime;
				}
			beerMode[playerID] = C.BEER_ON;
			}
		numPowerups[playerID][powerupIndex]--; /// Remove one from inventory
		GameStats.score[playerID] += GameStats.scorePowerupActivated;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method will be called for every user once an activated powerup's timer hits zero */
	public static void powerupDeactivation(int playerID, int powerupIndex)
		{
		if(powerupIndex == C.POWERUP_SPEED)
			{
			/// Decrement powerup by the number of activated powerups but not less than the base
			GameStats.speed[playerID] -= speedIncreaseCumulative[playerID];
			speedIncreaseCumulative[playerID] = 0;
//			GameStats.speed[playerTeamColor] = GameStats.speed[playerTeamColor] < GameStats.speedBase ? GameStats.speedBase : GameStats.speed[playerTeamColor];
			}
		else if(powerupIndex == C.POWERUP_ARMOR)
			{
			/// Decrement powerup by the number of activated powerups but not less than the base
			GameStats.power[playerID] -= powerIncreaseCumulative[playerID];
			powerIncreaseCumulative[playerID] = 0;
//			GameStats.power[playerTeamColor] = GameStats.power[playerTeamColor] < GameStats.powerBase ? GameStats.powerBase : GameStats.power[playerTeamColor];
			}
		else if(powerupIndex == C.POWERUP_INVINCIBLE)
			{
			invinciblePowerupActivated[playerID] = C.NO;
			if(beerMode[playerID] != C.ON) isInvincible[playerID] = C.NO;
			}
		else if(powerupIndex == C.POWERUP_BEER)
			{
			if(beerMode[playerID] == C.BEER_ON) /// Beer mode on ends, go to recovery
				{
				GameStats.speed[playerID] -= beerSpeedIncreaseCumulative[playerID] + beerRecoverySpeedDrop;
				GameStats.power[playerID] -= beerPowerIncreaseCumulative[playerID] + beerRecoveryPowerDrop;
				beerSpeedIncreaseCumulative[playerID] = 0;
				beerPowerIncreaseCumulative[playerID] = 0;
//				GameStats.speed[playerTeamColor] = GameStats.speed[playerTeamColor] - beerRecoverySpeedDrop < 1 ? 1 : GameStats.speed[playerTeamColor] - beerRecoverySpeedDrop;
//				GameStats.power[playerTeamColor] = GameStats.power[playerTeamColor] - beerRecoveryPowerDrop < 1 ? 1 : GameStats.power[playerTeamColor] - beerRecoveryPowerDrop;
				beerMode[playerID] = C.BEER_RECOVERY;
				if(invinciblePowerupActivated[playerID] == C.NO) /// If the only invincible activation came from the beer
					isInvincible[playerID] = C.NO;
				timePowerup[playerID][powerupIndex] = beerRecoveryTime;
				}
			else if(beerMode[playerID] == C.BEER_RECOVERY) /// Beer mode recovery ends, go to normal
				{
				GameStats.speed[playerID] += beerRecoverySpeedDrop;
				GameStats.power[playerID] += beerRecoveryPowerDrop;
//				GameStats.speed[playerTeamColor] = GameStats.speed[playerTeamColor] + beerRecoverySpeedDrop > GameStats.maxSpeed ? GameStats.maxSpeed : GameStats.speed[playerTeamColor] + beerRecoverySpeedDrop;
//				GameStats.power[playerTeamColor] = GameStats.power[playerTeamColor] + beerRecoveryPowerDrop > GameStats.maxArmor ? GameStats.maxArmor : GameStats.power[playerTeamColor] + beerRecoveryPowerDrop;
				beerMode[playerID] = C.BEER_OFF;
				}
			}
//		numActivatedPowerups[playerTeamColor][powerupIndex] = 0;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method should be called by the player that runs over an enemy mine */
	public static void sendMineCollision(int playerID, int mineID)
		{
		//StatePlay.mines.remove(mineID);
		/// Decrease health but not to exceed zero:
		NetworkControl.sendToAll("~MC" + playerID + mineID);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method should be called for every user when someone collides with an enemy mine */
	public static void mineCollision(int playerID)
		{
		if(Powerups.isInvincible[Settings.playerID] == C.NO)
			GameStats.sendPlayerDamageCommand(C.MINE, playerID, mineDamage);
		DisplaysStatePlay.drawMineExplosion(playerID);
		ResourceManager.getSound(Filenames.explosion2).play(1, Inputs.volumeMineDetonation);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setMaxPowerups(int playerID)
		{
		int i;
		for(i = 0; i < Strings.powerups.length; i++)
			{
			numPowerups[playerID][i] = maxPowerups;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Process the powerup command from the network. Used to display of the powerup and its status */
	public static void powerupTrueCommand(String string)
		{
		String xcoord = "";
		String ycoord = "";
		int index = 0;
		int stringsplit = 0;
		for(int i = 0; i < string.length(); i++)
			{//grab x coord from string
			if(string.charAt(i) != ',')
				{
				xcoord = xcoord + string.charAt(i);
				}
			else
				{
				stringsplit = i + 1;
				break;
				}
			}
		for(int i = stringsplit; i < string.length(); i++)
			{//grab y coord from string
			if(string.charAt(i) != ',')
				{
				ycoord = ycoord + string.charAt(i);
				}
			else
				{
				stringsplit = i + 1;
				break;
				}
			}
		index = Character.getNumericValue(string.charAt(stringsplit));
		//System.out.println("X:" +xcoord + " Y:" + ycoord + " Index:" + index );
		powerupVisible = C.YES;
		powerx = Integer.parseInt(xcoord); //get int from string
		powery = Integer.parseInt(ycoord); //get int from string

		powerMinimapX = DisplaysStatePlay.convertToMinimapX(powerx);
		powerMinimapY = DisplaysStatePlay.convertToMinimapY(powery);

		powerupIndex = index;
		StatePlay.powerupEntity = new Powerups(Powerups.powerx, Powerups.powery);
		powerupFlag = true;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Process the powerup command from the network. Used to erase the display of the powerup and its status */
	public static void powerupFalseCommand()
		{
		powerupFlag = false;
		powerupElapsedTime = 0;
		powerupVisible = C.NO;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendMineCoordinates()
		{
		int x = (int) StatePlay.tanks[Settings.playerID].getX();
		int y = (int) StatePlay.tanks[Settings.playerID].getY();
		NetworkControl.sendToAll("~MAX" + x);
		NetworkControl.sendToAll("~MAY" + y);
		NetworkControl.sendToAll("~MAP" + Settings.playerTeamColors[Settings.playerID]);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void checkMineCollision(int delta)
		{
		int mineID = 0;
		try
			{
			for(Iterator<projectile> iterator = StatePlay.mines.iterator(); iterator.hasNext(); )
				{
				projectile mineTest = iterator.next();
				if(mineTest.collides(StatePlay.tanks[Settings.playerID]) != null)
					{
					if(mineTest.playerTeamColor != Settings.playerTeamColors[Settings.playerID])
						{
//					StatePlay.removemines(mineID);
						//StatePlay.mines.remove(mineID);
						sendMineCollision(Settings.playerID, mineTest.minenumber);
						}
					}
				mineID++;
				mineTest.lifetime -= delta;
				if(mineTest.lifetime <= 0)
					{
					StatePlay.removemines(mineTest.minenumber);
					}
				}
			}
		catch (Exception e)
			{
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}

