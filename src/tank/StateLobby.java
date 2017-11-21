package tank;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class StateLobby extends BasicGameState
	{
	Tank tank;
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public int getID()
		{
		return StateControl.STATE_LOBBY;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
		{
		tank = (Tank) game;
		DisplaysStateLobby.initDisplays();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
		{
		AppGameContainer gc = (AppGameContainer) container;
		gc.setDisplayMode(Settings.playScreenWidth, Settings.playScreenHeight, false);
		StateControl.addCurrentState(getID());
		Settings.currentScreenHeight = Settings.playScreenHeight;
		Settings.currentScreenWidth = Settings.playScreenWidth;
		DisplaysStateLobby.positionDisplays();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
		{
		Input input = container.getInput();
		Inputs.processKeyboardInput(input);
		if(DisplaysPopupBox.popupDisplayed == C.YES)
			{
			if(DisplaysPopupBox.charactersEntered < DisplaysPopupBox.maxCharacters)
				{
				DisplaysPopupBox.getPopupInput(input);
				}
			}
		input.clearKeyPressedRecord();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
		DisplaysStateLobby.renderDisplays(g);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void mouseClicked(int button, int x, int y, int numClicked)
		{
		Inputs.xMouse = x;
		Inputs.yMouse = y;
		Inputs.processMouseInput();
		//Playing around with sending messages
		//Send mouse coords over the sever to client
		//NetworkClientMain cm=new NetworkClientMain();
		//cm.displayMessageToServer(" " + x + " , " +y);
		//NetworkServerWriteThread sw = new NetworkServerWriteThread();
		//sw.displayMessageToClients(" " + x + " , " +y);
		}
	}
