package tank;
/* This class is for displaying the navigation buttons in the help screens */
public class DisplaysButtonsHelpNavigation
	{
	/// Margins:
	public static int spaceAboveNavigationButtons = 25;
	public static int spaceBetweenButtons = 25;
	/// psw = percentage of screen width:
	public static float pswButton = .03f; /// psw = percentage of screen width
	/// Objects:
	public static Image buttonPrev = new Image(Filenames.navPrev, 0, 0, pswButton);
	public static Image buttonRew = new Image(Filenames.navRew, 0, 0, pswButton);
	public static Image buttonFF = new Image(Filenames.navFF, 0, 0, pswButton);
	public static Image buttonNext = new Image(Filenames.navNext, 0, 0, pswButton);
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionButtons()
		{
		buttonRew.x = (Settings.currentScreenWidth / 2) - buttonRew.getWidth() - (spaceBetweenButtons / 2);
		buttonRew.y = (Settings.currentScreenHeight - buttonRew.getHeight()) - spaceAboveNavigationButtons;
		buttonPrev.x = buttonRew.x - buttonPrev.getWidth() - spaceBetweenButtons;
		buttonPrev.y = (Settings.currentScreenHeight - buttonPrev.getHeight()) - spaceAboveNavigationButtons;
		buttonFF.x = buttonRew.x + buttonRew.getWidth() + spaceBetweenButtons;
		buttonFF.y = (Settings.currentScreenHeight - buttonFF.getHeight()) - spaceAboveNavigationButtons;
		buttonNext.x = buttonFF.x + buttonFF.getWidth() + spaceBetweenButtons;
		buttonNext.y = (Settings.currentScreenHeight - buttonNext.getHeight()) - spaceAboveNavigationButtons;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderButtons()
		{
		ResourceManager.getImage(buttonPrev.filename).draw(buttonPrev.x, buttonPrev.y, buttonPrev.scale);
		ResourceManager.getImage(buttonRew.filename).draw(buttonRew.x, buttonRew.y, buttonRew.scale);
		ResourceManager.getImage(buttonFF.filename).draw(buttonFF.x, buttonFF.y, buttonFF.scale);
		ResourceManager.getImage(buttonNext.filename).draw(buttonNext.x, buttonNext.y, buttonNext.scale);
		}
	}
