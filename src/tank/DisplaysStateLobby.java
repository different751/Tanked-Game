package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStateLobby
	{
	static int i;
	public static int numMessages = 0;
	/// Spacings:
	public static int marginTitleTop = 25; /// The margin from the top of the screen to the top of the title
	public static int spaceAfterHeading = 25; /// Space between the bottom of the lobbyHeading and the top of 1st setting
	public static int spaceColumn = 25; /// The space in-between individual settings and corresponding button(socket)
	public static int spaceBetweenNavigationalButtons = 50;
	public static int marginTop = 15;
	public static int marginBottom = 15;
	public static int marginSides = 50; /// The margins on the left and right sides of screen
	public static int spaceAfterIndividualSettings = 25; /// The space between the last individual setting and the player settings
	public static int spaceBetweenColumns = 30;
	public static int spaceBetweenRows = 15;
	public static int buttonSquareDimension;
	public static int messageAreaHeight = 200;
	public static int messageAreaMargin = 20;
	public static int messageAreaPadding = 10;
	public static int spaceAfterSettings = 25; /// The space between the last setting and the message display
	public static int nameHeight;
	public static int nameWidth;
	/// psw (percentage of screen width):
	public static float pswButtonRect = .15f;
	public static float pswButtonSquare = .05f;
	public static float pswButtonSquareSmall = .04f;
	public static float pswLobbyBackground = 1.5f;
	public static float pswMap = 0.21f;
	public static float pswIcon = .04f;
	/// Fonts:
	public static TrueTypeFont settingTextFont = Fonts.fontVerdana20TTF;
	public static TrueTypeFont buttonTextFont = Fonts.fontVerdana15TTF;
	public static TrueTypeFont messageTextFont = Fonts.fontCourier11BTTF;
	/// Colors:
	public static Color settingTextColor = Color.white;
	public static Color buttonTextColor = Color.black;
	public static Color messageBackgroundColor = Color.black;
	public static Color messageTextColor = Color.white;
	/// Areas:
//	public static Area nameArea[] = new Area[C.MAX_PLAYERS];
	public static Area buttonColor[] = new Area[C.MAX_PLAYERS];
	public static Area messageArea = new Area();
	/// Objects:
	public static Image lobbyBackground = new Image(Filenames.lobbyBackground, 0, 0, pswLobbyBackground);
	public static Image winConditionButton = new Image(Filenames.buttonRectangle, 0, 0, pswButtonRect);
	public static Image highScoreTimerButton = new Image(Filenames.buttonSquare, 0, 0, pswButtonSquareSmall);
	public static Image nameButton = new Image(Filenames.buttonRectangle, 0, 0, pswButtonRect);
	public static Image helpButton = new Image(Filenames.buttonRectangle, 0, 0, pswButtonRect);
	public static Image leaveGameButton = new Image(Filenames.buttonRectangle, 0, 0, pswButtonRect);
	public static Image launchGameButton = new Image(Filenames.buttonRectangle, 0, 0, pswButtonRect);
	public static Image locatorsButton = new Image(Filenames.buttonSquare, 0, 0, pswButtonSquareSmall);
	public static Image powerupIntervalButton = new Image(Filenames.buttonSquare, 0, 0, pswButtonSquareSmall);
	public static Image powerupDurationButton = new Image(Filenames.buttonSquare, 0, 0, pswButtonSquareSmall);
	public static Image prevButton = new Image(Filenames.buttonSquare, 0, 0, pswButtonSquare);
	public static Image prevIcon = new Image(Filenames.arrowPrev, 0, 0, pswIcon);
	public static Image nextButton = new Image(Filenames.buttonSquare, 0, 0, pswButtonSquare);
	public static Image nextIcon = new Image(Filenames.arrowNext, 0, 0, pswIcon);
	public static Image miniMap[] = new Image[Filenames.miniMap.length];

	public static StringsDisplay heading = new StringsDisplay(Strings.lobbyHeading, DisplaysHeading.headingFont, DisplaysHeading.headingColor, 0, 0);
	public static StringsDisplay map = new StringsDisplay(Strings.map, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay help = new StringsDisplay(Strings.helpInfo, buttonTextFont, buttonTextColor, 0, 0);
	public static StringsDisplay launchGame = new StringsDisplay(Strings.launchGame, buttonTextFont, buttonTextColor, 0, 0);
	public static StringsDisplay leaveGame = new StringsDisplay(Strings.leaveGame, buttonTextFont, buttonTextColor, 0, 0);
	public static StringsDisplay players = new StringsDisplay(Strings.players, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay winCondition = new StringsDisplay(Strings.winCondition, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay id = new StringsDisplay(Strings.id, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay settings = new StringsDisplay(Strings.settings, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay locators = new StringsDisplay(Strings.locators, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay powerupInterval = new StringsDisplay(Strings.powerupInterval, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay powerupDuration = new StringsDisplay(Strings.powerupDuration, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay name = new StringsDisplay(Strings.name, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay minimap = new StringsDisplay(Strings.map, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay team = new StringsDisplay(Strings.team, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay pressEnter = new StringsDisplay(Strings.pressEnter, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay minutes = new StringsDisplay(Strings.minutes, settingTextFont, settingTextColor, 0, 0);
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initDisplays()
		{
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			buttonColor[i] = new Area();
//			nameArea[i] = new Area();
			}
		for(i = 0; i < miniMap.length; i++)
			{
			miniMap[i] = new Image(Filenames.miniMap[i], 0, 0, pswMap);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
		/// Spacing:
		spaceBetweenColumns = 30 + ((Settings.currentScreenWidth - Settings.minLobbyScreenWidth) / 7);
//		spaceBetweenRows = 15 + ((Settings.currentScreenHeight - Settings.minLobbyScreenHeight) / 7);
		messageAreaHeight = 200 + ((Settings.currentScreenHeight - Settings.minLobbyScreenHeight) / 1);
		/// Title & lobbyHeading:
		heading.x = heading.centerStringScreenX();
		heading.y = marginTop;
		/// Spacings:
		buttonSquareDimension = winConditionButton.getHeight();
		nameWidth = nameButton.getWidth();
		nameHeight = settingTextFont.getHeight(Settings.sampleMaxName);
//		marginSides = Settings.currentScreenWidth <= 800 ? 100 : 100 + (int)((Settings.currentScreenWidth - 800) * 0.5);
//		spaceBetweenColumns = (Settings.currentScreenWidth - id.getWidth() - name.getWidth() - team.getWidth() - joined.getWidth() - (2 * marginSides)) / 3;
//		spaceBetweenRows = Settings.currentScreenHeight <= 800 ? 25 : 25 + (int)((Settings.currentScreenHeight - 800) / C.MAX_PLAYERS);
		/// Individual settings:
		winCondition.x = (Settings.currentScreenWidth - winCondition.getWidth() - spaceColumn - winConditionButton.getWidth()) / 2;
		winCondition.y = heading.getEndY() + spaceAfterHeading;
		winConditionButton.x = winCondition.getEndX() + spaceColumn;
		winConditionButton.y = winCondition.y;
		highScoreTimerButton.x = winConditionButton.getEndX() + spaceBetweenColumns;
		highScoreTimerButton.y = winCondition.y;
		minutes.x = highScoreTimerButton.getEndX() + spaceBetweenColumns;
		minutes.y = highScoreTimerButton.centerStringY(minutes.trueTypeFont, minutes.string);
		/// Player settings:
		players.y = winConditionButton.getEndY() + spaceAfterIndividualSettings;
		id.x = marginSides;
		id.y = players.getEndY() + spaceAfterIndividualSettings;
		name.x = id.getEndX() + spaceBetweenColumns;
		name.y = id.y;
		players.x = name.x;
		team.x = name.x + nameWidth + spaceBetweenColumns;
		team.y = id.y;
		/// Color settings:
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			buttonColor[i].x = team.x;
			buttonColor[i].y = team.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonSquareDimension);
			buttonColor[i].endX = buttonColor[i].x + buttonSquareDimension;
			buttonColor[i].endY = buttonColor[i].y + buttonSquareDimension;
			}
		/// Areas:
//		for(i = 0; i < C.MAX_PLAYERS; i++)
//			{
//			nameArea[i].x = name.x;
//			nameArea[i].y = name.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonColor[i].getWidth());
//			nameArea[i].endX = nameArea[i].x + nameWidth + (spaceBetweenColumns / 2);
//			nameArea[i].endY = nameArea[i].y + nameHeight;
//			}
		nameButton.x = name.x;
		nameButton.y = team.getEndY() + ((Settings.playerID + 1) * spaceBetweenRows) + (Settings.playerID * buttonSquareDimension);
		pressEnter.x = pressEnter.centerStringScreenX();
		pressEnter.y = buttonColor[C.MAX_PLAYERS - 1].endY + spaceAfterSettings;
		messageArea.x = messageAreaMargin;
		messageArea.endX = Settings.currentScreenWidth - messageAreaMargin;
		messageArea.y = pressEnter.getEndY() + spaceBetweenRows;
		messageArea.endY = messageArea.y + messageAreaHeight;

		/// Minimap:
		for(i = 0; i < Filenames.miniMap.length; i++)
			{
			miniMap[i].x = team.getEndX() + (2 * spaceBetweenColumns) + prevButton.getWidth();
//			miniMap[i].x = team.getEndX() + ((Settings.currentScreenWidth - team.getEndX() - miniMap[i].getWidth()) / 2);
			miniMap[i].y = team.getEndY() + spaceBetweenRows;
			}
		map.x = miniMap[0].x;
		map.y = team.y;
		minimap.x = map.x;
		minimap.y = players.y;
		prevButton.x = miniMap[0].x - prevButton.getWidth() - spaceBetweenColumns;
		prevButton.y = miniMap[0].centerImageY(pswButtonSquare);
		prevIcon.x = prevButton.centerImageX(pswIcon);
		prevIcon.y = prevButton.centerImageY(pswIcon);
		nextButton.x = miniMap[0].getEndX() + spaceBetweenColumns;
		nextButton.y = miniMap[0].centerImageY(pswButtonSquare);
		nextIcon.x = nextButton.centerImageX(pswIcon);
		nextIcon.y = nextButton.centerImageY(pswIcon);

		/// Settings:
		settings.x = nextButton.getEndX() + spaceBetweenColumns;
		settings.y = minimap.y;
		locators.x = settings.x;
		locators.y = map.y;
		locatorsButton.x = Settings.currentScreenWidth - locatorsButton.getWidth() - spaceBetweenColumns;
		locatorsButton.y = locators.y;

		powerupInterval.x = locators.x;
		powerupInterval.y = locatorsButton.getEndY() + spaceBetweenRows;
		powerupIntervalButton.x = locatorsButton.x;
		powerupIntervalButton.y = locatorsButton.getEndY() + spaceBetweenRows;

		powerupDuration.x = locators.x;
		powerupDuration.y = powerupIntervalButton.getEndY() + spaceBetweenRows;
		powerupDurationButton.x = locatorsButton.x;
		powerupDurationButton.y = powerupIntervalButton.getEndY() + spaceBetweenRows;

		/// Navigational buttons:
		helpButton.x = (Settings.currentScreenWidth - helpButton.getWidth() - leaveGameButton.getWidth() - spaceBetweenNavigationalButtons) / 2;
		helpButton.y = Settings.currentScreenHeight - helpButton.getHeight() - marginBottom;
		help.x = helpButton.centerStringX(help.trueTypeFont, help.string);
		help.y = helpButton.centerStringY(help.trueTypeFont, help.string);
		leaveGameButton.x = helpButton.getEndX() + spaceBetweenNavigationalButtons;
		leaveGameButton.y = helpButton.y;
		leaveGame.x = leaveGameButton.centerStringX(leaveGame.trueTypeFont, leaveGame.string);
		leaveGame.y = leaveGameButton.centerStringY(leaveGame.trueTypeFont, leaveGame.string);
		launchGameButton.x = leaveGameButton.getEndX() + spaceBetweenNavigationalButtons;
		launchGameButton.y = helpButton.y;
		launchGame.x = launchGameButton.centerStringX(launchGame.trueTypeFont, launchGame.string);
		launchGame.y = launchGameButton.centerStringY(launchGame.trueTypeFont, launchGame.string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		lobbyBackground.renderImage();
		heading.renderString();
		/// Individual settings:
		winCondition.renderString();
		winConditionButton.renderImage();
		winConditionButton.renderStringInImage(buttonTextFont, Strings.winConditionTypes[Settings.winCondition], buttonTextColor);
		if(Settings.winCondition == C.HIGH_SCORE)
			{
			highScoreTimerButton.renderImage();
			highScoreTimerButton.renderStringInImage(buttonTextFont, Integer.toString(Settings.highScoreTimerOptions[Settings.highScoreTimerIndex]), buttonTextColor);
			minutes.renderString();
			}
		/// Player settings:
		players.renderString();
		minimap.renderString();
		settings.renderString();
		locators.renderString();
		powerupInterval.renderString();
		powerupDuration.renderString();
		locatorsButton.renderImage();
		locatorsButton.renderStringInImage(buttonTextFont, Strings.offOn[Settings.displayLocators], buttonTextColor);
		powerupIntervalButton.renderImage();
		powerupIntervalButton.renderStringInImage(buttonTextFont, Integer.toString(Powerups.powerupIntervalOptions[Powerups.powerupIntervalIndex]), buttonTextColor);
		powerupDurationButton.renderImage();
		powerupDurationButton.renderStringInImage(buttonTextFont, Integer.toString(Powerups.powerupDurationOptions[Powerups.powerupDurationIndex]), buttonTextColor);
		
		id.renderString();
		name.renderString();
		team.renderString();
		nameButton.renderImage();
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			settingTextFont.drawString(id.x, id.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonColor[i].getWidth()), String.format("%d", i), settingTextColor);
			buttonColor[i].colorSection(g, Settings.allColors[Settings.playerTeamColors[i]]);
			if(i < Settings.numberActivePlayers)
				{
				if(i == Settings.playerID)
					buttonTextFont.drawString(nameButton.centerStringX(buttonTextFont, Settings.playerName[i]), nameButton.centerStringY(buttonTextFont, Settings.playerName[i]), Settings.playerName[i], buttonTextColor);
				else
					settingTextFont.drawString(name.x, name.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonColor[i].getWidth()), Settings.playerName[i], settingTextColor);
				}
			}
		/// Minimap:
		map.renderString();
		settingTextFont.drawString(map.getEndX() + 10, map.y, Integer.toString(Settings.mapSelected + 1) + " (" + Filenames.mapSize[Settings.mapSelected][0] + "x" + Filenames.mapSize[Settings.mapSelected][1] + ")", settingTextColor);
		miniMap[Settings.mapSelected].renderImage();
		prevButton.renderImage();
		prevIcon.renderImage();
		nextButton.renderImage();
		nextIcon.renderImage();
		/// Messages:
		pressEnter.renderString();
		messageArea.colorSection(g, messageBackgroundColor);
		for(i = 0; i < Strings.networkMessages.length; i++)
			{
//			System.out.printf("message %d = %socket, (%d, %d)\n", i, Strings.networkMessages[i], messageArea.x + messageAreaPadding, messageArea.y + messageAreaPadding + (i * 10));
			messageTextFont.drawString(messageArea.x + messageAreaPadding, messageArea.y + messageAreaPadding + (i * 10), Strings.networkMessages[i], messageTextColor);
			}
		/// Navigational buttons:
		helpButton.renderImage();
		help.renderString();
		leaveGameButton.renderImage();
		leaveGame.renderString();
		if(Settings.playerType == C.SERVER)
			{
			launchGameButton.renderImage();
			launchGame.renderString();
			}
		/// Popup:
		if(DisplaysPopupBox.popupDisplayed == C.YES) DisplaysPopupBox.renderPopup(g);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void displayMessage(String string)
		{
		if(numMessages < Strings.networkMessages.length)
			{
			Strings.networkMessages[numMessages] = Strings.networkMessagePrompt + " " + string;
			numMessages++;
			}
		else
			{
			int i;
			for(i = 0; i < Strings.networkMessages.length - 1; i++)
				{
				Strings.networkMessages[i] = Strings.networkMessages[i + 1];
				}
			Strings.networkMessages[Strings.networkMessages.length - 1] = Strings.networkMessagePrompt + " " + string;
			}
		}
	}
