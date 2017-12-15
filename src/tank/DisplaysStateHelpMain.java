package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStateHelpMain
	{
	static int i;
	/// Spacings:
	public static int marginTitleTop = 25; /// The margin from the top of the screen to the top of the title
	public static int spaceAfterTitle = 200; /// Space between bottom of title to top of first button
	public static int spaceBetweenButtons = 25;
	/// psw (percentage of screen width):
	public static float pswButtons = .3f;
	/// Fonts:
	public static TrueTypeFont authorTextFont = Fonts.fontVerdana25TTF;
	public static TrueTypeFont buttonTextFont = Fonts.fontVerdana20TTF;
	/// Colors:
	public static Color authorTextColor = Color.green;
	public static Color buttonTextColor = Color.black;
	/// Areas:
	/// Objects:
	public static Image buttons[] = new Image[3];
	public static StringsDisplay buttonText[] = new StringsDisplay[3];
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initDisplays()
		{
		for(i = 0; i < buttons.length; i++)
			buttons[i] = new Image(Filenames.buttonRectangle, 0, 0, pswButtons);
		for(i = 0; i < buttonText.length; i++)
			buttonText[i] = new StringsDisplay("", buttonTextFont, buttonTextColor, 0, 0);
		buttonText[0].string = Strings.gameplay;
		buttonText[1].string = Strings.gameControls;
		buttonText[2].string = Strings.credits;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
		spaceAfterTitle = (Settings.currentScreenHeight / 2) - marginTitleTop - DisplaysTitle.title.getHeight() - DisplaysHeading.spaceAfterTitle - DisplaysHeading.heading.getHeight();
		DisplaysTitle.positionTitle(marginTitleTop);
		DisplaysNavigationalButtons.positionButtons();
		DisplaysHeading.positionHeading(Strings.helpInfo);
		for(i = 0; i < buttons.length; i++)
			{
			buttons[i].x = buttons[i].centerImageScreenX();
			buttons[i].y = DisplaysTitle.title.getEndY() + spaceAfterTitle + (i * buttons[i].getHeight()) + (i * spaceBetweenButtons);
			}
		for(i = 0; i < buttonText.length; i++)
			{
			buttonText[i].x = buttons[i].centerStringX(buttonText[i].trueTypeFont, buttonText[i].string);
			buttonText[i].y = buttons[i].centerStringY(buttonText[i].trueTypeFont, buttonText[i].string);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		DisplaysTitle.renderTitle();
		DisplaysNavigationalButtons.renderNavigationalButtons();
		DisplaysHeading.renderHeading();
		for(i = 0; i < buttons.length; i++)
			buttons[i].renderImage();
		for(i = 0; i < buttonText.length; i++)
			buttonText[i].renderString();
		}
	}
