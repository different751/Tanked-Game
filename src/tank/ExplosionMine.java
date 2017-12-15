package tank;
import org.newdawn.slick.Animation;

import jig.Entity;
public class ExplosionMine extends Entity
	{
	private Animation explosion;
	public ExplosionMine(final float x, final float y)
		{
		super(x, y);
		explosion = new Animation(ResourceManager.getSpriteSheet(Filenames.explosionSprite, 64, 64), 0, 0, 4, 4, true, 50, true);
		addAnimation(explosion);
		explosion.setLooping(false);
		}
	public boolean isActive()
		{
		return !explosion.isStopped();
		}
	}
