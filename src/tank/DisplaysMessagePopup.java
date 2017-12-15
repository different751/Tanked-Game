package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
/* This class contains a method for displaying a popup message in the middle of the current screen*/
public class DisplaysMessagePopup
	{
	public static void renderMessage(Graphics g, String string, int x, int y, int padding, TrueTypeFont trueTypeFont, Color backgroundColor, Color textColor)
		{
		/// Padding is the spacing from the text to the outside border; used for all sides
		if(x == C.CENTER)
			x = (Settings.currentScreenWidth - (trueTypeFont.getWidth(string) + (2 * padding))) / 2;
		if(y == C.CENTER)
			y = (Settings.currentScreenHeight - (trueTypeFont.getHeight(string) + (2 * padding))) / 2;
		g.setColor(Color.black);
		g.fillRect(x -  padding - 1, y - padding - 1, trueTypeFont.getWidth(string) + (2 * padding) + 2, trueTypeFont.getHeight() + (2 * padding) + 2);
		g.setColor(backgroundColor);
		g.fillRect(x -  padding, y - padding, trueTypeFont.getWidth(string) + (2 * padding), trueTypeFont.getHeight() + (2 * padding));
		trueTypeFont.drawString(x, y, string, textColor);
		}
	}
