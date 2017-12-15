package tank;
import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;

import jig.ResourceManager;
public class StateSplash extends BasicGameState
	{
	Tank tank;
	Font font;
	TrueTypeFont truetype;
	private int timer = 0;
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public int getID()
		{
		return StateControl.STATE_SPLASH;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
		{
		tank = (Tank) game;
//		DisplaysStatePlay.initDisplays();
		ResourceManager.loadImage(Filenames.WSUV);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
		{
		StateControl.addCurrentState(getID());
		timer = 10000;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
		g.drawImage(ResourceManager.getImage(Filenames.WSUV), (Settings.mainScreenWidth / 3) - 60, Settings.mainScreenHeight / 3);
		font = new Font(g.getFont().toString(), Font.BOLD, 32);
		truetype = new TrueTypeFont(font, true);
		truetype.drawString((Settings.mainScreenWidth / 2) - 180, (Settings.mainScreenHeight / 2) + 25, Strings.cs447);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
		{
		timer -= delta;
		if(timer <= 0) StateControl.enterState(StateControl.STATE_MAIN);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void mouseClicked(int button, int x, int y, int numClicked)
		{
		Inputs.localxMouse = x;
		Inputs.localyMouse = y;
		Inputs.processMouseInput();
		}
	}