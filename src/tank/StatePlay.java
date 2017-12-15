package tank;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import jig.Vector;

import static tank.DisplaysStatePlay.camera;

import java.util.ArrayList;
import java.util.Iterator;
public class StatePlay extends BasicGameState
	{
	Tank tank;
	PlayGame playGame = new PlayGame();
	static int i;
	public static int elapsedTime;
	public static int hours;
	public static int minutes;
	public static int seconds;
	public static int highScoreTimer; /// Seconds
	public static tankentity tanks[] = new tankentity[C.MAX_PLAYERS];
	public static ArrayList<projectile> mines = new ArrayList<projectile>();
	public static ArrayList<projectile> shots = new ArrayList<projectile>();
	//	public static boolean powerupFlag=false;
//	public static int powerx=0;//power ups x location
//	public static int powery=0;//power ups y location
//	public static int powerupIndex=0;
	public static Powerups powerupEntity;
	//	public static int powerupElapsedTime = 0;
	public static int x = 0;
	public static int y = 0;
	public int timer = 0;
	public static int gamePaused = C.NO;
	public static int shotnumber = 0;
	public static int minenumber = 0;
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public int getID()
		{
		return StateControl.STATE_PLAY;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
		{
		tank = (Tank) game;
		DisplaysStatePlay.initDisplays();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
		{
		int i;
		DisplaysStatePlay.initPlayerHealthBars();
		StateMain.music.fade(1000, 0, true);
		AppGameContainer gc = (AppGameContainer) container;
		gc.setDisplayMode(Settings.playScreenWidth, Settings.playScreenHeight, false);
		StateControl.addCurrentState(getID());
		Settings.currentScreenWidth = Settings.playScreenWidth;
		Settings.currentScreenHeight = Settings.playScreenHeight;
		DisplaysStatePlay.positionDisplays();
		camera = new Camera(Filenames.maps[Settings.mapSelected], 10, 125);
		GameStats.initGameStats();
		elapsedTime = hours = minutes = seconds = 0;
		for(i = 0; i < Settings.numberActivePlayers; i++)
			{
			if(Settings.playerTeamColors[i] == C.RED)
				{
				if(i == 0) tanks[i] = new tankentity(200, 200, 'r');
				else if(i == 1) tanks[i] = new tankentity(DisplaysStatePlay.camera.worldWitdth - 200, 200, 'r');
				else if(i == 2) tanks[i] = new tankentity(200, DisplaysStatePlay.camera.worldHeight - 200, 'r');
				else if(i == 3)
					tanks[i] = new tankentity(DisplaysStatePlay.camera.worldWitdth - 200, DisplaysStatePlay.camera.worldHeight - 200, 'r');
				}
			else if(Settings.playerTeamColors[i] == C.BLUE)
				{
				if(i == 0) tanks[i] = new tankentity(200, 200, 'b');
				else if(i == 1) tanks[i] = new tankentity(DisplaysStatePlay.camera.worldWitdth - 200, 200, 'b');
				else if(i == 2) tanks[i] = new tankentity(200, DisplaysStatePlay.camera.worldHeight - 200, 'b');
				else if(i == 3)
					tanks[i] = new tankentity(DisplaysStatePlay.camera.worldWitdth - 200, DisplaysStatePlay.camera.worldHeight - 200, 'b');
				}
			else if(Settings.playerTeamColors[i] == C.GREEN)
				{
				if(i == 0) tanks[i] = new tankentity(200, 200, 'g');
				else if(i == 1) tanks[i] = new tankentity(DisplaysStatePlay.camera.worldWitdth - 200, 200, 'g');
				else if(i == 2) tanks[i] = new tankentity(200, DisplaysStatePlay.camera.worldHeight - 200, 'g');
				else if(i == 3)
					tanks[i] = new tankentity(DisplaysStatePlay.camera.worldWitdth - 200, DisplaysStatePlay.camera.worldHeight - 200, 'g');
				}
			else if(Settings.playerTeamColors[i] == C.YELLOW)
				{
				if(i == 0) tanks[i] = new tankentity(200, 200, 'y');
				else if(i == 1) tanks[i] = new tankentity(DisplaysStatePlay.camera.worldWitdth - 200, 200, 'y');
				else if(i == 2) tanks[i] = new tankentity(200, DisplaysStatePlay.camera.worldHeight - 200, 'y');
				else if(i == 3)
					tanks[i] = new tankentity(DisplaysStatePlay.camera.worldWitdth - 200, DisplaysStatePlay.camera.worldHeight - 200, 'y');
				}
			}
		GameStats.recordNumberTeams();
		highScoreTimer = 60 * Settings.highScoreTimerOptions[Settings.highScoreTimerIndex];
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
		DisplaysStatePlay.renderDisplays(g);
		/// Display tank locations:
		if(Settings.displayMiniMap == C.YES && Settings.displayTankLocation == C.YES)
			{
			for(i = 0; i < Settings.numberActivePlayers; i++)
				{
				g.setColor(Settings.allColors[Settings.playerTeamColors[i]]);
				g.fillRect(DisplaysStatePlay.miniMapArea.x + DisplaysStatePlay.convertToMinimapX(StatePlay.tanks[i].getX()), DisplaysStatePlay.miniMapArea.y + DisplaysStatePlay.convertToMinimapY(StatePlay.tanks[i].getY()), DisplaysStatePlay.minimapDotWidth, DisplaysStatePlay.minimapDotWidth);
				}
			}
		if(GameStats.gameOver == C.YES)
			{
			DisplaysMessagePopup.renderMessage(g, Strings.colors[GameStats.winningTeam] + Strings.wins, C.CENTER, C.CENTER, 10, Fonts.fontCourier20BTTF, Color.black, Color.white);
			}
		if(StatePlay.gamePaused == C.YES || GameStats.gameOver == C.YES)
			{
			DisplaysMessagePopup.renderMessage(g, Strings.gamePaused, C.CENTER, DisplaysStatePlay.pausePopupY, 10, Fonts.fontCourier15BTTF, Color.black, Color.white);
			DisplaysStatePlay.messageArea.colorSection(g, DisplaysStatePlay.messageBackgroundColor);
			for(i = 0; i < Strings.networkMessages.length; i++)
				{
				DisplaysStatePlay.messageTextFont.drawString(DisplaysStatePlay.messageArea.x + DisplaysStatePlay.messageAreaPadding, DisplaysStatePlay.messageArea.y + DisplaysStatePlay.messageAreaPadding + (i * 10), Strings.networkMessages[i], DisplaysStatePlay.messageTextColor);
				}
			if(DisplaysPopupBox.popupDisplayed == C.YES)
				{
				DisplaysPopupBox.renderPopup(g);
				}
			}
		else if(GameStats.health[Settings.playerID] <= 0)
			{
			DisplaysMessagePopup.renderMessage(g, Strings.gameOver, C.CENTER, C.CENTER, 10, Fonts.fontCourier20BTTF, Color.black, Color.white);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
		{
		Input input = container.getInput();
		Inputs.processKeyboardInput(input);
		if(DisplaysPopupBox.popupDisplayed == C.YES)
			{
			if(DisplaysPopupBox.charactersEntered < DisplaysPopupBox.maxCharacters)
				{
				DisplaysPopupBox.getPopupInput(input);
				}
			}
		input.clearKeyPressedRecord();

		DisplaysStatePlay.updateExplosions();
		//Inputs.xMouse[Settings.playerTeamColor]=input.getMouseX();
		//Inputs.yMouse[Settings.playerTeamColor]=input.getMouseY();
		if(GameStats.gameOver == C.NO && GameStats.health[Settings.playerID] > 0)
			{
			if(timer >= 10)
				{
				NetworkControl.sendToAll("~PX" + Settings.playerID + (input.getMouseX() - camera.xPos - camera.pixelOffsetX));
				NetworkControl.sendToAll("~PY" + Settings.playerID + (input.getMouseY() - camera.yPos - camera.pixelOffsetY));
				x = (int) tanks[Settings.playerID].getX();
				y = (int) tanks[Settings.playerID].getY();
				NetworkControl.sendToAll("~PV" + Settings.playerID + x);
				NetworkControl.sendToAll("~PB" + Settings.playerID + y);
				timer = 0;
				}
			timer += delta;
			if(gamePaused == C.NO)
				{
				updateTime(delta);
				for(int i = 0; i < Settings.numberActivePlayers; i++)
					{
					Inputs.vectors[i] = new Vector(Inputs.xpos[i], Inputs.ypos[i]);
					}
				//System.out.println(Settings.playerTeamColor);
				for(int i = 0; i < Settings.numberActivePlayers; i++)
					{
					if(i == Settings.playerID)
						{
						tanks[i].control(Inputs.movement[i], Inputs.rotation[i]);
						tanks[i].aimTurret(Inputs.xMouse[i], Inputs.yMouse[i]);
						tanks[i].update(delta, i);
						}
					if(i != Settings.playerID)
						{
						tanks[i].control(Inputs.movement[i], Inputs.rotation[i]);
						tanks[i].setRotation(Inputs.hullangle[i]);
						tanks[i].aimTurret(Inputs.xMouse[i], Inputs.yMouse[i]);
						tanks[i].setPosition(Inputs.vectors[i]);
						tanks[i].update(delta, i);
						}
					}
				for(projectile i : shots)
					{
					i.update(delta);
					}
				Powerups.sendPowerupStatus();
				Powerups.checkPowerupCollision();
				Powerups.checkMineCollision(delta);
				CheckProjectileCollision(delta);
				}
			}
		camera.update(tanks[Settings.playerID], delta);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void mouseClicked(int button, int x, int y, int numClicked)
		{
		Inputs.localxMouse = x;
		Inputs.localyMouse = y;
		Inputs.processMouseInput();
		Inputs.localxMouse = -1;
		Inputs.localyMouse = -1;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void updateTime(int delta)
		{
		elapsedTime += delta;
		if(elapsedTime >= 1000)
			{
			seconds++;
			if(Settings.winCondition == C.HIGH_SCORE)
				{
				highScoreTimer--;
				if(highScoreTimer == 0) GameStats.checkWinCondition();
				}
			processPowerupTime();
			elapsedTime -= 1000;
			}
		if(seconds == 60)
			{
			minutes++;
			seconds = 0;
			}
		if(minutes == 60)
			{
			hours++;
			minutes = 0;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This will method will be called every second and will decrement any activated timed powerups by one second */
	public void processPowerupTime()
		{
		int i, j;
		Powerups.powerupElapsedTime++;
		for(i = 0; i < C.MAX_PLAYERS; i++) /// Cycle through all players
			{
			for(j = 0; j < Strings.powerups.length; j++) /// Cycle through all powerups
				{
				if(Powerups.timePowerup[i][j] > 0) /// If there is at least one second on the timer still
					{
					if(Powerups.timePowerup[i][j] == 1) /// If the powerup is about to expire
						Powerups.powerupDeactivation(i, j); /// Deactivate powerup
					Powerups.timePowerup[i][j]--; /// Decrement one second off the powerup's time
					}
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void removemines(int i)
		{
		int deletemine = i;
		for(Iterator<projectile> iterator = StatePlay.mines.iterator(); iterator.hasNext(); )
			{
			projectile whichmine = iterator.next();
			if(whichmine.minenumber == deletemine)
				{
				iterator.remove();
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void CheckProjectileCollision(int delta)
		{

		try
			{
			for(Iterator<projectile> iterator = StatePlay.shots.iterator(); iterator.hasNext(); )
				{
				projectile shotTest = iterator.next();

				if(shotTest.collidesWithSolid())
					{
						//removeshot(shotTest.shotnumber);
						iterator.remove();
					}

				if(shotTest.collides(StatePlay.tanks[Settings.playerID]) != null)
					{
					if(shotTest.playerTeamColor != Settings.playerTeamColors[Settings.playerID])
						{
						//removeshot(shotID);
						//StatePlay.shots.remove(shotID);
						//sendMineCollision(Settings.playerID, shotID);
							int damage = 0;
							if(Powerups.isInvincible[Settings.playerID] == C.YES)
								damage = 0;
							else if(GameStats.power[Settings.playerID] == 1)
								damage = 2 * GameStats.missileDamage;
							else if(GameStats.power[Settings.playerID] == 2)
								damage = (int)(1.75 * GameStats.missileDamage);
							else if(GameStats.power[Settings.playerID] == 3)
								damage = (int)(1.50 * GameStats.missileDamage);
							else if(GameStats.power[Settings.playerID] == 4)
								damage = (int)(1.25 * GameStats.missileDamage);
							else if(GameStats.power[Settings.playerID] == 5)
								damage = GameStats.missileDamage;
							else if(GameStats.power[Settings.playerID] == 6)
								damage = (int)(.83 * GameStats.missileDamage);
							else if(GameStats.power[Settings.playerID] == 7)
								damage = (int)(.67 * GameStats.missileDamage);
							else if(GameStats.power[Settings.playerID] == 8)
								damage = (int)(.5 * GameStats.missileDamage);
							else if(GameStats.power[Settings.playerID] == 9)
								damage = (int)(.33 * GameStats.missileDamage);
							else if(GameStats.power[Settings.playerID] == 10)
								damage = (int)(.17 * GameStats.missileDamage);

//							System.out.printf("power = %d, damage = %d\n", GameStats.power[Settings.playerID], damage);
							NetworkControl.sendToAll("~RS" + shotTest.shotnumber);
							GameStats.sendPlayerDamageCommand(shotTest.playerTeamColor, Settings.playerID, damage);
						}
					}

				shotTest.lifetime -= delta;
				if(shotTest.lifetime <= 0)
					{
					//removeshot(shotTest.shotnumber);
					iterator.remove();
					}
				}
			}
		catch (Exception e)
			{
				System.out.println("Something Happened: " + e.toString());
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void removeshot(int i)
		{
		int deleteshot = i;
		for(Iterator<projectile> iterator = StatePlay.shots.iterator(); iterator.hasNext(); )
			{
			projectile whichshot = iterator.next();
			if(whichshot.shotnumber == deleteshot)
				{
				iterator.remove();
				}
			}
		}
	}
