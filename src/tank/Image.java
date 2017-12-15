package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
/* This class defines a single displayable image (from a file) on the screen */
public class Image
	{
	String filename;
	int width;
	int height;
	int x;
	int y;
	float scale;
	float psw; /// Percentage of screen width. Set this variable to adjust the size of the image relative to the screen width
	/*-----------------------------------------------------------------------------------------------------*/
	public Image(String filename)
		{
		this.filename = filename;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public Image(String filename, int x, int y, float psw)
		{
		this.filename = filename;
		width = ResourceManager.getImage(filename).getWidth();
		height = ResourceManager.getImage(filename).getHeight();
		this.psw = psw;
		this.scale = (Settings.currentScreenWidth * psw) / width;

		if(x == C.CENTER)
			this.x = (int)((Settings.currentScreenWidth - (width * this.scale)) / 2);
		else
			this.x = x;

		if(y == C.CENTER)
			this.y = (int)((Settings.currentScreenHeight - (height * this.scale)) / 2);
		else
			this.y = y;
//		System.out.printf("String = %socket, x = %d, screenWidth = %d, Width = %d, Height = %d, scale = %.2f, width*scale = %d\n", filename, this.x, Settings.currentScreenWidth, width, height, this.scale, (int)(width * this.scale));
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void rescaleImage()
		{
		scale = (Settings.currentScreenWidth * psw) / width;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getWidth()
		{
		return (int) (width * scale);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getHeight()
		{
		return (int) (height * scale);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getEndX() {return x + getWidth();}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getEndY() {return y + getHeight();}
	/*-----------------------------------------------------------------------------------------------------*/
	public void renderImage()
		{
		ResourceManager.getImage(filename).draw(x, y, scale);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerImageScreenX()
		{
		return (int)((Settings.currentScreenWidth - (width * this.scale)) / 2);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerImageScreenY()
		{
		return (int)((Settings.currentScreenHeight - (height * this.scale)) / 2);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getCenterX() {return x + ((getEndX() - x) / 2);}
	/*-----------------------------------------------------------------------------------------------------*/
	public int getCenterY() {return y + ((getEndY() - y) / 2);}
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
	public int centerImageX(float pswChild)
		{
//		System.out.printf("x = %d, width1 = %d, scale1 = %f, scale2 = %f\n", x, getWidth(), this.scale, pswChild);
		return (int)(x + ((getWidth() - (getWidth() / (this.psw / pswChild)))) / 2);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public int centerImageY(float pswChild)
		{
		return (int)(y + ((getHeight() - (getHeight() / (this.psw / pswChild)))) / 2);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void renderStringInImage(TrueTypeFont trueTypeFont, String string, Color color)
		{
		trueTypeFont.drawString(centerStringX(trueTypeFont, string), centerStringY(trueTypeFont, string), string, color);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}