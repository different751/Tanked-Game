package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
/* This class is for displaying the game lobbyHeading under the game title in the setup & help states */
public class DisplaysHeading
	{
	public static int spaceAfterTitle = 20; /// The space between the bottom of the title and the top of the lobbyHeading
	public static TrueTypeFont headingFont = Fonts.fontVerdana30TTF;
	public static Color headingColor = Color.lightGray;
	public static StringsDisplay heading = new StringsDisplay("", headingFont, headingColor, 0, 0);
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionHeading(String string)
		{
		heading.string = string;
		heading.x = (Settings.currentScreenWidth - heading.trueTypeFont.getWidth(heading.string)) / 2;
		heading.y = DisplaysTitle.title.getEndY() + spaceAfterTitle;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderHeading()
		{
		heading.trueTypeFont.drawString(heading.x, heading.y, heading.string, heading.color);
		}
	}
