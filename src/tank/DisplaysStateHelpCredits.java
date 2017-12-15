package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStateHelpCredits
	{
	static int i;
	/// Spacings:
	public static int marginTitleTop = 25; /// The margin from the top of the screen to the top of the title
	public static int spaceAfterHeading = 25; /// Space between bottom of heading to top of first subheading
	public static int spaceAfterSubheading = 25; /// Space between the subheadings and the text
	public static int marginSides = 50;
	public static int textSpace = 100;
	/// psw (percentage of screen width):
	/// Fonts:
	public static TrueTypeFont headingTextFont = Fonts.fontVerdana25TTF;
	public static TrueTypeFont mainTextFont = Fonts.fontVerdana20TTF;
	/// Colors:
	public static Color headingTextColor = Color.white;
	public static Color mainTextColor = Color.white;
	/// Areas:
	/// Objects:
	public static String authorsString = "";
	public static StringsDisplay authors = new StringsDisplay(Strings.authors, headingTextFont, headingTextColor, 0, 0);
	public static StringsDisplay authorsText = new StringsDisplay(authorsString, mainTextFont, mainTextColor, 0, 0);
	public static StringsDisplay licenses = new StringsDisplay(Strings.licenses, headingTextFont, headingTextColor, 0, 0);
	public static StringsDisplay licensesText[] = new StringsDisplay[Strings.licensesText.length];
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initDisplays()
		{
		for(i = 0; i < licensesText.length; i++)
			{
			licensesText[i] = new StringsDisplay(Strings.licensesText[i], mainTextFont, mainTextColor, 0, 0);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
		DisplaysTitle.positionTitle(marginTitleTop);
		DisplaysNavigationalButtons.positionButtons();
		DisplaysHeading.positionHeading(Strings.helpInfo + " : " + Strings.credits);
		authors.x = authors.centerStringScreenX();
		authors.y = DisplaysHeading.heading.getEndY() + spaceAfterHeading;
		for(i = 0; i < Strings.authorsList.length; i++)
			{
			if(i < Strings.authorsList.length - 1)
				authorsString += Strings.authorsList[i] + ", ";
			else
				authorsString += Strings.authorsList[i];
			}
		authorsText.string = authorsString;
		authorsText.x = authorsText.centerStringScreenX();
		authorsText.y = authors.getEndY() + spaceAfterSubheading;
		licenses.x = licenses.centerStringScreenX();
		licenses.y = authorsText.getEndY() + spaceAfterSubheading;
		for(i = 0; i < licensesText.length; i++)
			{
			licensesText[i].x = licensesText[i].centerStringScreenX();
			licensesText[i].y = licenses.getEndY() + spaceAfterSubheading + (i * licensesText[i].getHeight());
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		DisplaysTitle.renderTitle();
		DisplaysNavigationalButtons.renderNavigationalButtons();
		DisplaysHeading.renderHeading();
		authors.renderString();
		authorsText.renderString();
		licenses.renderString();
		for(i = 0; i < licensesText.length; i++)
			licensesText[i].renderString();
		}
	}
