package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStateMain
	{
	static int i;
	/// Spacings:
	public static int marginTitleTop = 25; /// The margin from the top of the screen to the top of the title
	public static int logoOffsetX = 0; /// The offset position of the logo relative to the tile
	public static int logoOffsetY = 20; /// The offset position of the logo relative to the title
	public static int spaceAfterTitle = 50; /// The space from the bottom of the title to the top of the 1st author
	public static int spaceBetweenAuthors = 5;
	public static int spaceAfterAuthors = 50;
	public static int spaceBetweenButtons = 15;
	/// psw (percentage of screen width):
	public static float pswLogo = .4f;
	public static float pswTitle = .6f;
	public static float pswButtons = .3f;
	public static float pswCamo = 2f;
	/// Fonts:
	public static TrueTypeFont authorTextFont = Fonts.fontVerdana25TTF;
	public static TrueTypeFont buttonTextFont = Fonts.fontVerdana20TTF;
	/// Colors:
	public static Color backgroundColor = Color.darkGray;
	public static Color authorTextColor = Color.black;
	public static Color buttonTextColor = Color.black;
	/// Areas:
	/// Objects:
	public static Image logo = new Image(Filenames.logo, 0, 0, pswLogo);
	public static Image camoBackground = new Image(Filenames.camoBackground, 0, 0, pswCamo);
	public static Image buttonHost = new Image(Filenames.buttonRectangle, 0, 0, pswButtons);
	public static Image buttonJoin = new Image(Filenames.buttonRectangle, 0, 0, pswButtons);
	//	public static Image buttons[] = new Image[Strings.mainButtonTypes.length];
	public static StringsDisplay buttonHostText = new StringsDisplay(Strings.hostGame, buttonTextFont, buttonTextColor, 0, 0);
	public static StringsDisplay buttonExitHostText = new StringsDisplay(Strings.exitHost, buttonTextFont, buttonTextColor, 0, 0);
	public static StringsDisplay buttonJoinText = new StringsDisplay(Strings.joinGame, buttonTextFont, buttonTextColor, 0, 0);
	public static StringsDisplay buttonExitJoinText = new StringsDisplay(Strings.exitJoin, buttonTextFont, buttonTextColor, 0, 0);
	//	public static StringsDisplay buttonTextStartup[] = new StringsDisplay[2];
//	public static StringsDisplay buttonText[] = new StringsDisplay[Strings.mainButtonTypes.length];
	public static StringsDisplay authors[] = new StringsDisplay[Strings.authorsList.length];
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initDisplays()
		{
		for(i = 0; i < Strings.authorsList.length; i++)
			authors[i] = new StringsDisplay(Strings.authorsList[i], authorTextFont, authorTextColor, 0, 0);
//		for(i = 0; i < buttons.length; i++)
//			buttons[i] = new Image(Filenames.buttonRectangle, 0, 0, pswButtons);
//		for(i = 0; i < buttonText.length; i++)
//			buttonText[i] = new StringsDisplay(Strings.mainButtonTypes[i], buttonTextFont, buttonTextColor, 0, 0);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
		/// Spacing:
//		spaceAfterHeading = Settings.currentScreenHeight < 800 ? 35 : 35 + (int) ((Settings.currentScreenHeight - 700) * 0.2);
//		spaceAfterAuthors = spaceAfterHeading;
//		spaceBetweenButtons = Settings.currentScreenHeight < 800 ? 15 : (int) ((Settings.currentScreenHeight - 700) * 0.15);
		/// Title & logo:
		DisplaysTitle.positionTitle(marginTitleTop);
		logo.x = DisplaysTitle.title.centerImageX(pswLogo) + logoOffsetX;
		logo.y = DisplaysTitle.title.y - ((logo.getHeight() - DisplaysTitle.title.getHeight()) / 2) + logoOffsetY;
		/// Authors:
		for(i = 0; i < Strings.authorsList.length; i++)
			{
			authors[i].x = authors[i].centerStringScreenX();
			authors[i].y = DisplaysTitle.title.getEndY() + spaceAfterTitle + (i * authors[i].getHeight()) + spaceBetweenAuthors;
			}
		/// Buttons:
		buttonHost.x = buttonHost.centerImageScreenX();
		buttonHost.y = authors[Strings.authorsList.length - 1].getEndY() + spaceAfterAuthors;
		buttonJoin.x = buttonJoin.centerImageScreenX();
		buttonJoin.y = authors[Strings.authorsList.length - 1].getEndY() + spaceAfterAuthors + buttonHost.getHeight() + spaceBetweenButtons;
		buttonHostText.x = buttonHost.centerStringX(buttonHostText.trueTypeFont, buttonHostText.string);
		buttonHostText.y = buttonHost.centerStringY(buttonHostText.trueTypeFont, buttonHostText.string);
		buttonExitHostText.x = buttonHost.centerStringX(buttonExitHostText.trueTypeFont, buttonExitHostText.string);
		buttonExitHostText.y = buttonHost.centerStringY(buttonExitHostText.trueTypeFont, buttonExitHostText.string);
		buttonJoinText.x = buttonJoin.centerStringX(buttonJoinText.trueTypeFont, buttonJoinText.string);
		buttonJoinText.y = buttonJoin.centerStringY(buttonJoinText.trueTypeFont, buttonJoinText.string);
		buttonExitJoinText.x = buttonJoin.centerStringX(buttonExitJoinText.trueTypeFont, buttonExitJoinText.string);
		buttonExitJoinText.y = buttonJoin.centerStringY(buttonExitJoinText.trueTypeFont, buttonExitJoinText.string);
//		for(i = 0; i < buttons.length; i++)
//			{
//			buttons[i].x = buttons[i].centerImageScreenX();
//			buttons[i].y = authorsText[Strings.authorsText.length - 1].getEndY() + spaceAfterAuthors + ((i+1) * buttons[i].getHeight()) + ((i+1) * spaceBetweenButtons);
//			buttonText[i].x = buttons[i].centerStringX(buttonText[i].trueTypeFont, buttonText[i].string);
//			buttonText[i].y = buttons[i].centerStringY(buttonText[i].trueTypeFont, buttonText[i].string);
//			}
		DisplaysPopupBox.positionPopup();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Settings.currentScreenWidth, Settings.currentScreenHeight);
		camoBackground.renderImage();
		logo.renderImage();
		DisplaysTitle.renderTitle();
		for(i = 0; i < authors.length; i++)
			authors[i].renderString();
		buttonHost.renderImage();
		buttonHostText.renderString();
		buttonJoin.renderImage();
		buttonJoinText.renderString();
		if(DisplaysPopupBox.popupDisplayed == C.YES)
			{
			DisplaysPopupBox.renderPopup(g);
			}
		}
	}
