package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import java.util.ArrayList;
import java.util.Iterator;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStatePlay
	{
	static int i;
	public static int healthStartY = 0;
	public static int numMessages = 0;
	/// Spacings:
	public static int margin = 10;
	public static int miniMapBorder = 5;
	public static int healthBarWidth = 15;
	public static int powerupBorder = 1;
	public static int powerupWidth;
	public static int powerupHeight = 100;
	public static int powerupPadding = 2;
	public static int messageAreaHeight = 200;
	public static int messageAreaMargin = 20;
	public static int messageAreaPadding = 10;
	public static int pausePopupY = 400;
	public static int messageY = pausePopupY + 100;
	public static int highScorePadding = 20;
	public static int minimapDotWidth = 5;
	/// psw (percentage of screen width):
	public static float pswMiniMap = .2f;
	public static float pswMap = 1.2f;
	public static float pswIcon = .045f;
	public static int playersHealthBarWidth;
	public static int playersHealthBarPadding = 2;
	/// Fonts:
	public static TrueTypeFont mainFont = Fonts.fontCourier11BTTF;
	public static TrueTypeFont timeFont = Fonts.fontCourier10BTTF;
	public static TrueTypeFont scoreFont = Fonts.fontCourier13BTTF;
	public static TrueTypeFont messageTextFont = Fonts.fontCourier11BTTF;
	/// Colors:
	public static Color beerRecoveryColor = Color.red;
	public static Color mainColor = Color.white;
	public static Color timeColor = Color.white;
	public static Color backgroundColor = Color.black;
	public static Color miniMapColor = Color.white;
	public static Color healthBarColor = Color.white;
	public static Color healthColor = Color.red;
	public static Color invincibleHealthColor = Color.blue;
	public static Color powerupBackgroundColor = Color.white;
	public static Color powerupColor = Color.black;
	public static Color scoreColor = Color.green;
	public static Color powerColor = Color.red;
	public static Color speedColor = Color.red;
	public static Color messageBackgroundColor = Color.black;
	public static Color messageTextColor = Color.white;
	public static Color powerupSpawnColor = Color.black;
	/// Areas:
	public static Area messageArea = new Area();
	public static Area mapArea = new Area();
	public static Area healthBarArea = new Area();
	public static Area miniMapArea = new Area();
	public static Area bottomMargin = new Area();
	public static Area rightMargin = new Area();
	public static Area powerupTotalArea = new Area();
	public static Area powerupArea[] = new Area[Strings.powerups.length + 1];
	public static Area playersHealthBars[];
	/// Objects:
	public static Image miniMap = new Image(Filenames.miniMap[Settings.mapSelected], 0, 0, pswMiniMap);
	public static Image powerupIcon[] = new Image[Filenames.powerupIcons.length];
	public static StringsDisplay time = new StringsDisplay("", timeFont, timeColor, 0, 0);
	public static StringsDisplay powerupStrings[] = new StringsDisplay[Strings.powerups.length];
	public static StringsDisplay score = new StringsDisplay("", scoreFont, scoreColor, 0, 0);
	public static StringsDisplay power = new StringsDisplay("", mainFont, powerColor, 0, 0);
	public static StringsDisplay speed = new StringsDisplay("", mainFont, speedColor, 0, 0);
	public static StringsDisplay winCondition = new StringsDisplay("", scoreFont, scoreColor, 0, 0);
	/// Tiled Map:
	public static Camera camera;
	/// Explosions:
	public static ArrayList<ExplosionMine> explosionsMine = new ArrayList<ExplosionMine>();
	public static boolean explodeMine = false;
	public static int explosionMineX;
	public static int explosionMineY;
	public static ArrayList<ExplosionMissile> explosionsMissile = new ArrayList<ExplosionMissile>();
	public static boolean explodeMissile = false;
	public static int explosionMissileX;
	public static int explosionMissileY;
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initDisplays()
		{
		for(i = 0; i < powerupArea.length; i++)
			powerupArea[i] = new Area();
		for(i = 0; i < powerupStrings.length; i++)
			powerupStrings[i] = new StringsDisplay(Strings.powerups[i], mainFont, mainColor, 0, 0);
		for(i = 0; i < powerupIcon.length; i++)
			powerupIcon[i] = new Image(Filenames.powerupIcons[i], 0, 0, pswIcon);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initPlayerHealthBars()
		{
		playersHealthBars = new Area[Settings.numberActivePlayers];
		for(i = 0; i < Settings.numberActivePlayers; i++)
			playersHealthBars[i] = new Area();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
		/// Borders:
		mapArea.x = margin;
		mapArea.y = (2 * margin) + (2 * powerupBorder) + powerupHeight;
		mapArea.endX = Settings.currentScreenWidth - margin;
		mapArea.endY = Settings.currentScreenHeight - margin;
		bottomMargin.x = 0;
		bottomMargin.y = Settings.currentScreenHeight - margin;
		bottomMargin.endX = Settings.currentScreenWidth;
		bottomMargin.endY = Settings.currentScreenHeight;
		rightMargin.x = Settings.currentScreenWidth - margin;
		rightMargin.y = 0;
		rightMargin.endX = Settings.currentScreenWidth;
		rightMargin.endY = Settings.currentScreenHeight;
		/// Minimap:
		miniMapArea.x = Settings.currentScreenWidth - margin - (2 * miniMapBorder) - miniMap.getWidth();
		miniMapArea.y = margin;
		miniMapArea.endX = miniMapArea.x + (2 * miniMapBorder) + miniMap.getWidth();
		miniMapArea.endY = miniMapArea.y + (2 * miniMapBorder) + miniMap.getHeight();
		miniMap.x = miniMapArea.x + miniMapBorder;
		miniMap.y = miniMapArea.y + miniMapBorder;
		miniMap.filename = Filenames.miniMap[Settings.mapSelected];
		/// Healthbar:
		healthBarArea.x = Settings.currentScreenWidth - margin - healthBarWidth;
		healthBarArea.y = miniMapArea.endY + 1;
		healthBarArea.endX = Settings.currentScreenWidth - margin;
		healthBarArea.endY = Settings.currentScreenHeight - margin;
		/// Powerups:
		powerupTotalArea.x = margin;
		powerupTotalArea.y = margin;
		powerupTotalArea.endX = miniMapArea.x - margin;
		powerupTotalArea.endY = margin + (2 * powerupBorder) + powerupHeight;
		powerupWidth = (powerupTotalArea.getWidth() - ((powerupArea.length + 1) * powerupBorder)) / powerupArea.length;
		powerupArea[0].x = powerupTotalArea.x + powerupBorder;
		powerupArea[0].y = powerupTotalArea.y + powerupBorder;
		powerupArea[0].endX = powerupArea[0].x + powerupWidth;
		powerupArea[0].endY = powerupArea[0].y + powerupHeight;
		powerupStrings[0].x = powerupArea[0].centerStringX(mainFont, powerupStrings[0].string);
		powerupStrings[0].y = powerupArea[0].y + (2 * powerupPadding) + mainFont.getHeight();
		for(i = 1; i < powerupArea.length; i++)
			{
			powerupArea[i].x = powerupArea[i - 1].endX + powerupBorder + 1;
			powerupArea[i].y = powerupArea[0].y;
			powerupArea[i].endX = powerupArea[i].x + powerupWidth;
			powerupArea[i].endY = powerupArea[0].endY;
			}
		powerupArea[powerupArea.length - 1].endX = powerupTotalArea.endX - powerupBorder;
		for(i = 0; i < powerupStrings.length; i++)
			{
			powerupStrings[i].x = powerupArea[i].centerStringX(mainFont, powerupStrings[i].string);
			powerupStrings[i].y = powerupStrings[0].y;
			}
		for(i = 0; i < powerupIcon.length; i++)
			{
			powerupIcon[i].x = powerupArea[i].centerImageX(powerupIcon[i].getWidth());
			powerupIcon[i].y = powerupStrings[i].getEndY() + powerupPadding;
			}
		/// Time:
		time.x = powerupArea[powerupArea.length - 1].centerStringX(timeFont, "00:00:00");
		time.y = powerupArea[powerupArea.length - 1].endY - powerupPadding - mainFont.getHeight();
		/// Score, power, speed:
		score.x = powerupArea[powerupArea.length - 1].centerStringX(scoreFont, "12345");
		score.y = powerupArea[powerupArea.length - 1].y + powerupPadding;
		power.x = powerupArea[powerupArea.length - 1].centerStringX(mainFont, "12/12");
		power.y = score.getEndY() + powerupPadding;
		speed.x = powerupArea[powerupArea.length - 1].centerStringX(mainFont, "12/12");
		speed.y = power.getEndY() + powerupPadding;
		/// Message area:
		messageArea.x = messageAreaMargin;
		messageArea.endX = Settings.currentScreenWidth - messageAreaMargin;
		messageArea.y = messageY;
		messageArea.endY = messageArea.y + messageAreaHeight;
		/// Win condition:
		winCondition.string = Strings.winConditionTypes[Settings.winCondition];
		winCondition.x = powerupArea[powerupArea.length - 1].centerStringX(scoreFont, winCondition.string);
		winCondition.y = speed.getEndY() + powerupPadding;
		/// Player's health bars:
		playersHealthBarWidth = (Settings.currentScreenWidth - margin - healthBarWidth - 5) / Settings.numberActivePlayers;
//		System.out.printf("screen width = %d, bar width = %d\n", Settings.currentScreenWidth, playersHealthBarWidth);
		for(i = 0; i < Settings.numberActivePlayers; i++)
			{
			playersHealthBars[i].x = (i * playersHealthBarWidth);
			playersHealthBars[i].y = Settings.currentScreenHeight - healthBarWidth;
			playersHealthBars[i].endX = ((i + 1) * playersHealthBarWidth);
			playersHealthBars[i].endY = Settings.currentScreenHeight;
//			System.out.printf("(%d,%d) to (%d,%d)\n", playersHealthBars[i].x, playersHealthBars[i].y, playersHealthBars[i].endX, playersHealthBars[i].endY);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		/// Border & maps:
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Settings.currentScreenWidth, Settings.currentScreenHeight);
		bottomMargin.colorSection(g, backgroundColor);
		/// Begin World Rendering:
		g.translate(camera.xPos + camera.pixelOffsetX, camera.yPos + camera.pixelOffsetY);
		g.setClip(camera.viewport);
		camera.render(g);
		//Mines
		if(StatePlay.mines.isEmpty() == false)
			{
			try
				{
				for(projectile mine : StatePlay.mines)
					{
					mine.render(g);
					}
				}
			catch (Exception e) {}
			}
		/// Shots:
		if(StatePlay.shots.isEmpty() == false)
			{
			try
				{
				for(projectile i : StatePlay.shots)
					{
					i.render(g);
					}
				}
			catch (Exception e)
				{
				}
			}
		/// Tanks:
		for(int i = 0; i < Settings.numberActivePlayers; i++)
			{
			StatePlay.tanks[i].render(g);
			StatePlay.tanks[i].getTurret().render(g);
			}
		/// Mine Explosion:
		for(ExplosionMine b : explosionsMine)
			b.render(g);
		for(ExplosionMissile b : explosionsMissile)
			b.render(g);
		/// Powerups:
		if(Powerups.powerupFlag == true)
			{
			//render power up at location
			//g.drawImage(ResourceManager.getImage(Filenames.powerupIcons[powerupIndex]).getScaledCopy(.35f), powerx, powery);
			StatePlay.powerupEntity.render(g);
			//powerupEntity.
			}
		//g.translate(-camera.pixelOffsetX, -camera.pixelOffsetY);
		g.clearClip();
		g.resetTransform();
		/// End World Rendering
		if(Tank.DEBUG)
			{
			g.setColor(Color.black);
			g.drawString("Debug: " + Tank.DEBUG, 10, 140);
			g.drawString("Mouse: " + (Inputs.xMouse[Settings.playerID] - camera.pixelOffsetX)
					+ ", " + (Inputs.yMouse[Settings.playerID] - camera.pixelOffsetY), 10, 160);

			g.drawString("TankX: " + StatePlay.tanks[Settings.playerID].getX(), 10, 180);
			g.drawString("TankY: " + StatePlay.tanks[Settings.playerID].getY(), 10, 200);

			g.drawString("TileLoc: " + camera.map.getTileLocation(
					StatePlay.tanks[Settings.playerID]), 10, 220);

			g.drawString("pixelOffset: " + (camera.xPos + (float) camera.pixelOffsetX)
					+ ", " + (camera.yPos + (float) camera.pixelOffsetY), 10, 240);

			g.drawString("world W/H: " + camera.worldWitdth + ", " + camera.worldHeight, 10, 260);
			g.drawString("Edge: " + StatePlay.tanks[Settings.playerID].collideWorldEdge(), 10, 280);
			g.drawString("TankLastX: " + StatePlay.tanks[Settings.playerID].getLastPos().getX(), 10, 300);
			g.drawString("TankLastY: " + StatePlay.tanks[Settings.playerID].getLastPos().getY(), 10, 320);
			}
		rightMargin.colorSection(g, backgroundColor);
		if(Settings.displayMiniMap == C.YES)
			{
			miniMapArea.colorSection(g, miniMapColor);
			miniMap.renderImage();
			if(Settings.displayPowerupSpawn == C.YES && Powerups.powerupVisible == C.YES)
				{
				g.setColor(powerupSpawnColor);
				g.fillRect(miniMapArea.x + Powerups.powerMinimapX, miniMapArea.y + Powerups.powerMinimapY, minimapDotWidth, minimapDotWidth);
				}
			}
		/// Healthbars:
		healthBarArea.colorSection(g, healthBarColor);
		setHealthStartY();
		if(Powerups.isInvincible[Settings.playerID] == C.YES) g.setColor(invincibleHealthColor);
		else g.setColor(healthColor);
		g.fillRect(healthBarArea.x, healthStartY, healthBarWidth + 1, healthBarArea.endY - healthStartY + 1);
		if(Settings.displayPlayersHealth == C.YES)
			{
			g.setColor(backgroundColor);
			g.fillRect(0, Settings.currentScreenHeight - healthBarWidth, Settings.currentScreenWidth, healthBarWidth);
			for(i = 0; i < Settings.numberActivePlayers; i++)
				{
				g.setColor(healthBarColor);
				g.fillRect(playersHealthBars[i].x + playersHealthBarPadding, playersHealthBars[i].y + playersHealthBarPadding, playersHealthBarWidth - (2 * playersHealthBarPadding), healthBarWidth - (2 * playersHealthBarPadding));
				if(Powerups.isInvincible[i] == C.YES) g.setColor(invincibleHealthColor);
				else g.setColor(healthColor);
				g.fillRect(playersHealthBars[i].x + playersHealthBarPadding, playersHealthBars[i].y + playersHealthBarPadding, getHealthEndX(i) - (2 * playersHealthBarPadding), healthBarWidth - (2 * playersHealthBarPadding));
				g.setColor(backgroundColor);
				g.drawString(Settings.playerName[i], playersHealthBars[i].x + (2 * playersHealthBarPadding), playersHealthBars[i].y - 1);
				}
			}
		/// Powerups:
		powerupTotalArea.colorSection(g, powerupBackgroundColor);
		for(i = 0; i < powerupArea.length; i++)
			powerupArea[i].colorSection(g, powerupColor);
		for(i = 0; i < powerupStrings.length; i++)
			{
			mainFont.drawString(powerupArea[i].centerStringX(mainFont, Integer.toString(i + 1)), powerupArea[i].y + powerupPadding, Integer.toString(i + 1), mainColor);
			powerupStrings[i].renderString();
			mainFont.drawString(powerupIcon[i].getEndX() + powerupPadding, powerupIcon[i].getEndY() - powerupPadding - mainFont.getHeight(), "x " + Integer.toString(Powerups.numPowerups[Settings.playerID][i]), mainColor);
			if(Powerups.powerupType[i] == C.TIMED)
				{
				if(i == C.POWERUP_BEER && Powerups.beerMode[Settings.playerID] == C.BEER_RECOVERY)
					mainFont.drawString(powerupArea[i].centerStringX(mainFont, Integer.toString(Powerups.timePowerup[Settings.playerID][i])), powerupArea[i].endY - powerupPadding - mainFont.getHeight(), Integer.toString(Powerups.timePowerup[Settings.playerID][i]), beerRecoveryColor);
				else
					mainFont.drawString(powerupArea[i].centerStringX(mainFont, Integer.toString(Powerups.timePowerup[Settings.playerID][i])), powerupArea[i].endY - powerupPadding - mainFont.getHeight(), Integer.toString(Powerups.timePowerup[Settings.playerID][i]), mainColor);
				}
			}
		for(i = 0; i < powerupIcon.length; i++)
			powerupIcon[i].renderImage();
		/// Time:
		timeFont.drawString(time.x, time.y, String.format("%02d:%02d:%02d", StatePlay.hours, StatePlay.minutes, StatePlay.seconds), timeColor);
		if(Settings.winCondition == C.HIGH_SCORE)
			{
			timeFont.drawString(powerupArea[powerupArea.length - 1].centerStringX(timeFont, Strings.gameOver + ":"), time.y - (2 * timeFont.getHeight()), Strings.gameOver + ":", timeColor);
			timeFont.drawString(powerupArea[powerupArea.length - 1].centerStringX(timeFont, Integer.toString(StatePlay.highScoreTimer)), time.y - timeFont.getHeight(), Integer.toString(StatePlay.highScoreTimer), timeColor);
			}
		/// Score, power, speed:
		score.trueTypeFont.drawString(powerupArea[powerupArea.length - 1].centerStringX(scoreFont, Integer.toString(GameStats.score[Settings.playerID])), score.y, Integer.toString(GameStats.score[Settings.playerID]), score.color);
		power.trueTypeFont.drawString(power.x, power.y, Strings.powerups[C.POWERUP_ARMOR].charAt(0) + ": " + Integer.toString(GameStats.power[Settings.playerID]) + "/" + Integer.toString(GameStats.maxArmor), power.color);
		speed.trueTypeFont.drawString(speed.x, speed.y, Strings.powerups[C.POWERUP_SPEED].charAt(0) + ": " + Integer.toString(GameStats.speed[Settings.playerID]) + "/" + Integer.toString(GameStats.maxSpeed), speed.color);
		/// Win condition
		winCondition.renderString();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static int getHealthEndX(int playerID)
		{
		double r = 1.0 - ((double) GameStats.health[playerID] / (double) GameStats.maxHealth[playerID]);
		int n = (int) (playersHealthBarWidth * r);
//		System.out.printf("health = %d, maxHealth = %d, r = %f, n = %d\n", GameStats.health[playerID], GameStats.maxHealth[playerID], r, n);
		return playersHealthBarWidth - n;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setHealthStartY()
		{
		double r = 1.0 - ((double) GameStats.health[Settings.playerID] / (double) GameStats.maxHealth[Settings.playerID]);
		healthStartY = healthBarArea.y + (int) (healthBarArea.getHeight() * r);
//		System.out.printf("healthMax = %d, health = %d; healthbar = (%d,%d) to (%d,%d) h=%d; r = %f, start = %d, end = %d\n", GameStats.maxHealth[Settings.playerTeamColor], GameStats.health[Settings.playerTeamColor], healthBarArea.x, healthBarArea.y, healthBarArea.endX, healthBarArea.endY, healthBarArea.getHeight(), r, healthStartY, healthBarArea.getHeight() - healthStartY);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void drawMineExplosion(int playerID)
		{
		ResourceManager.getSound(Filenames.explosion2).play(1, Inputs.volumeMineDetonation);
		explodeMine = true;
		explosionMineX = (int) StatePlay.tanks[playerID].getX();
		explosionMineY = (int) StatePlay.tanks[playerID].getY();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void drawMissileExplosion(int playerID)
		{
		ResourceManager.getSound(Filenames.explosion).play(1, Inputs.volumeExplosion);
		explodeMissile = true;
		explosionMissileX = (int) StatePlay.tanks[playerID].getX();
		explosionMissileY = (int) StatePlay.tanks[playerID].getY();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void updateExplosions()
		{
		if(explodeMine) explosionsMine.add(new ExplosionMine(explosionMineX, explosionMineY));
		for(Iterator<ExplosionMine> i = explosionsMine.iterator(); i.hasNext(); )
			{
			if(!i.next().isActive()) i.remove();
			}
		explodeMine = false;
		if(explodeMissile) explosionsMissile.add(new ExplosionMissile(explosionMissileX, explosionMissileY));
		for(Iterator<ExplosionMissile> i = explosionsMissile.iterator(); i.hasNext(); )
			{
			if(!i.next().isActive()) i.remove();
			}
		explodeMissile = false;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static int convertToMinimapX(float x)
		{
		return (int) ((x / (float) Filenames.mapSize[Settings.mapSelected][0]) * miniMapArea.getWidth());
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static int convertToMinimapY(float y)
		{
		return (int) ((y / (float) Filenames.mapSize[Settings.mapSelected][1]) * miniMapArea.getHeight());
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}
