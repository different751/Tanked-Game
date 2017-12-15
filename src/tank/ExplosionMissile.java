package tank;
import org.newdawn.slick.Animation;

import jig.Entity;
public class ExplosionMissile extends Entity
	{
	private Animation explosion;
	public ExplosionMissile(final float x, final float y)
		{
		super(x, y);
		explosion = new Animation(ResourceManager.getSpriteSheet(Filenames.explosionSmallSprite, 64, 64), 0, 0, 7, 0, true, 50, true);
		addAnimation(explosion);
		explosion.setLooping(false);
		}
	public boolean isActive()
		{
		return !explosion.isStopped();
		}
	}
