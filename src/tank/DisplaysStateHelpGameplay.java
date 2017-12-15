package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStateHelpGameplay
	{
	public static String helpText[];
	public static int currentHelpPage = 0;
	public static int totalHelpPages;
	/// Spacings:
	public static int marginBottom = 25;
	public static int marginTitleTop = 25; /// The margin from the top of the screen to the top of the title
	public static int textMarginX = 25; /// The space on the left and right sides of the text to the outer screen border
	public static int textMarginY = 25; /// The space between the bottom of the lobbyHeading and the top of the help text
	/// psw (percentage of screen width):
	/// Fonts:
	/// Colors:
	public static Color textColor = Color.white;
	/// Areas:
	/// Objects:
	public static int textX = textMarginX;
	public static int textY = DisplaysHeading.heading.getEndY() + textMarginY;
	public static int textWidth = Settings.currentScreenWidth - (2 * textMarginX);
	public static int textHeight = (Settings.currentScreenHeight - DisplaysButtonsHelpNavigation.buttonRew.getHeight()) - marginBottom - textMarginY - textY;

	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
		DisplaysButtonsHelpNavigation.positionButtons();
		DisplaysTitle.positionTitle(marginTitleTop);
		DisplaysNavigationalButtons.positionButtons();
		DisplaysHeading.positionHeading(Strings.helpInfo + " : " + Strings.gameplay);
		textY = DisplaysHeading.heading.y + DisplaysHeading.heading.trueTypeFont.getHeight(DisplaysHeading.heading.string) + textMarginY;
		textWidth = Settings.currentScreenWidth - (2 * textMarginX);
		textHeight = DisplaysButtonsHelpNavigation.buttonRew.y - textMarginY - textY;
		}

	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		DisplaysTitle.renderTitle();
		DisplaysNavigationalButtons.renderNavigationalButtons();
		DisplaysHeading.renderHeading();
		DisplaysButtonsHelpNavigation.renderButtons();
		g.setColor(textColor);
		renderHelpText(g, currentHelpPage);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void formatHelpText()
		{
		helpText = Strings.formatString(Strings.gamePlayText, textWidth, textHeight);
		totalHelpPages = helpText.length;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderHelpText(Graphics g, int page)
		{
		g.drawString(helpText[page], textX, textY);
		}
	}
