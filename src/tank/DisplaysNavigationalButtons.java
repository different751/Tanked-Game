package tank;
/* This class is for displaying the home and back button on a screen */
public class DisplaysNavigationalButtons
	{
	public static float pswButton = .08f;
	public static float pswIcon = .05f;
	public static int spacingFromTitle = 25; /// The space between the title and the button
//	public static Image buttonHome = new Image(Filenames.buttonSquare,0, 0, pswButton);
//	public static Image buttonHomeIcon = new Image(Filenames.navHome, 0,0, pswIcon);
	public static Image buttonBack = new Image(Filenames.buttonSquare,0, 0, pswButton);
	public static Image buttonBackIcon = new Image(Filenames.navBack, 0,0, pswIcon);
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionButtons()
		{
//		buttonHome.rescaleImage();
//		buttonBack.rescaleImage();
//		buttonHomeIcon.rescaleImage();
//		buttonBackIcon.rescaleImage();
//		buttonHome.x = DisplaysTitle.title.x - spacingFromTitle - buttonHome.getWidth();
//		buttonHome.y = DisplaysTitle.title.getEndY() - buttonHome.getHeight();
//		buttonHomeIcon.x = buttonHome.centerImageX(pswIcon);
//		buttonHomeIcon.y = buttonHome.centerImageY(pswIcon);
		
		buttonBack.x = DisplaysTitle.title.getEndX() + spacingFromTitle;
		buttonBack.y = DisplaysTitle.title.getEndY() - buttonBack.getHeight();
		buttonBackIcon.x = buttonBack.centerImageX(pswIcon);
		buttonBackIcon.y = buttonBack.centerImageY(pswIcon);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderNavigationalButtons()
		{
//		buttonHome.renderImage();
//		buttonHomeIcon.renderImage();
		buttonBack.renderImage();
		buttonBackIcon.renderImage();
		}
	}
