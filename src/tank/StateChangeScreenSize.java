package tank;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
/* This class is simply to change the screensize. Upon entering this state, the game will automatically
* enter the previous state */
public class StateChangeScreenSize extends BasicGameState
	{
	Tank tank;
	@Override
	public int getID()
		{
		return StateControl.STATE_CHANGE_SCREEN_SIZE;
		}
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
		{
		tank = (Tank) game;
		}
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
		{
		tank.enterState(StateControl.currentState);
		}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
		}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
		{
		}
	}
