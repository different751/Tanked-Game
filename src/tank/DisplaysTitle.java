package tank;
/* This class is for displaying the game title */
public class DisplaysTitle
	{
	public static float pswTitle = 0.6f;
	public static Image title = new Image(Filenames.title, 0, 0, pswTitle);
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionTitle(int marginTop)
		{
		/// marginTop is the spacing from the top of the screen to the top of the title
		/// pswTitle is the size of the title relative to the screen width
		title.x = title.centerImageScreenX();
		title.y = marginTop;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderTitle()
		{
		ResourceManager.getImage(title.filename).draw(title.x, title.y, title.scale);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}
