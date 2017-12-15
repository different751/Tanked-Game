package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
/* This class is for a single defined area. Use it to define an area bounded by coordinates */
public class Area
	{
	int x;
	int y;
	int endX;
	int endY;

	/*-----------------------------------------------------------------------------------------------------*/
	public int getWidth()
		{
		return endX - x + 1;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getHeight()
		{
		return endY - y + 1;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getCenterX() {return x + ((endX - x) / 2);}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getCenterY() {return y + ((endY - y) / 2);}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerStringX(TrueTypeFont trueTypeFont, String string)
		{
		return x  + (getWidth() - trueTypeFont.getWidth(string)) / 2;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerStringY(TrueTypeFont trueTypeFont, String string)
		{
		return y + (getHeight() - trueTypeFont.getHeight(string)) / 2;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerImageX(int imageWidth)
		{
		return x  + (getWidth() - imageWidth) / 2;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerImageY(int imageHeight)
		{
		return x  + (getWidth() - imageHeight) / 2;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void colorSection(Graphics g, Color color)
		{
		g.setColor(color);
		g.fillRect(x, y, getWidth(), getHeight());
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void renderStringInArea(TrueTypeFont trueTypeFont, String string, Color color)
		{
		trueTypeFont.drawString(centerStringX(trueTypeFont, string), centerStringY(trueTypeFont, string), string, color);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}
