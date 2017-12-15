package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
/* This class defines a single string display. Every string in the game that is displayed should use an
instance of this class. */
public class StringsDisplay
	{
	String string;
	Color color;
	TrueTypeFont trueTypeFont;
	int width;
	int height;
	int x;
	int y;
	/*-----------------------------------------------------------------------------------------------------*/
	public StringsDisplay(String string, TrueTypeFont trueTypeFont, Color color, int x, int y)
		{
		this.string = string;
		this.color = color;
		this.trueTypeFont = trueTypeFont;
		width = trueTypeFont.getWidth(string);
		height = trueTypeFont.getHeight(string);

		if(x == C.CENTER)
			this.x = ((Settings.currentScreenWidth - width) / 2);
		else
			this.x = x;

		if(y == C.CENTER)
			this.y = ((Settings.currentScreenHeight - height) / 2);
		else
			this.y = y;
//		System.out.printf("String = %socket, x = %d, screenWidth = %d, stringWidth = %d, stringHeight = %d\n", string, this.x, Settings.currentScreenWidth, width, height);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void renderString()
		{
		trueTypeFont.drawString(x, y, string, color);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerStringScreenX()
		{
		return (Settings.currentScreenWidth - getWidth()) / 2;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerStringScreenY(Area area)
		{
		return (Settings.currentScreenHeight - getHeight()) / 2;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerStringX(Area area)
		{
		return area.x + ((area.getWidth() - trueTypeFont.getWidth(string)) / 2);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerStringY(Area area)
		{
		return area.y + ((area.getHeight() - trueTypeFont.getHeight(string)) / 2);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getWidth()
		{
		return trueTypeFont.getWidth(string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getHeight()
		{
		return trueTypeFont.getHeight(string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getEndX()
		{
		return x + getWidth() - 1;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getEndY()
		{
		return y + getHeight() - 1;
		}
	}
