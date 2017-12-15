package tank;
import jig.Collision;
import jig.Entity;
import jig.Vector;
public class tankentity extends Entity
	{
	public enum Edge
		{
			Top, Bottom, Left, Right, TopRight, TopLeft, BottomLeft, BottomRight, None
		}
	;
	private float tankSpeed = 0.02f;
	private Vector velocity;
	private Vector worldpos;
	private Vector lastpos;
	private double hullangle;
	private int angle = 0;
	private double turretangle;
	private int movement;
	private int rotation;
	private float reload;
	private turretentity turret;
	private Collision collisionResult;
	
	/*-----------------------------------------------------------------------------------------------------*/
	public tankentity(final float x, final float y, final char team)
		{
		super(x, y);
		switch (team)
			{
			case 'r':
				ResourceManager.loadImage(Filenames.tank_r);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.tank_r).getScaledCopy(0.25f));
				break;
			case 'g':
				ResourceManager.loadImage(Filenames.tank_g);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.tank_g).getScaledCopy(0.25f));
				break;
			case 'b':
				ResourceManager.loadImage(Filenames.tank_b);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.tank_b).getScaledCopy(0.25f));
				break;
			case 'y':
				ResourceManager.loadImage(Filenames.tank_y);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.tank_y).getScaledCopy(0.25f));
				break;
			}
		turret = new turretentity(x, y, team, this);
		velocity = new Vector(0.0f, 0.0f);
		lastpos=new Vector(this.getX(),this.getY());
		hullangle = 0;
		reload=1000;
		turretangle = 0;
		//addImageWithBoundingBox(ResourceManager.getImage(Filenames.turret_r).getScaledCopy(0.05f));
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void update(final int delta, int i)
		{
		//updates lastpos, used in collision checks
		
		//why doesn't this work?
		//lastpos.setX(this.getX());
		//lastpos.setY(this.getY());
		lastpos=new Vector(this.getX(),this.getY());
		
		if(reload<1000){
			reload+=delta;
		}
		
		translate(velocity.scale(delta * (float) (tankSpeed * GameStats.speed[Settings.playerID])));
		this.setRotation(this.getRotation() + (float) rotation * GameStats.speed[Settings.playerID] / 2);
		hullangle = this.getRotation();
		angle = (int) this.getRotation();
		NetworkControl.sendToAll("~PH" + i + angle);
		
		//handle collision
		Vector tanktile=DisplaysStatePlay.camera.map.getTileLocation(this);
		int tileid=DisplaysStatePlay.camera.map.getTileId((int)tanktile.getX(),(int)tanktile.getY(),0);
		String solid=DisplaysStatePlay.camera.map.getTileProperty(tileid, "Solid", "false");
		String water=DisplaysStatePlay.camera.map.getTileProperty(tileid, "Water", "false");
		if(water.equals("true")||solid.equals("true")){
			this.setPosition(lastpos);
		}
		
		turret.update(delta);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public double getTurretAngle()
		{
		return turretangle;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void aimTurret(float x2, float y2)
		{
		float x1 = this.getX();
		float y1 = this.getY();
		float x = x2 - x1;
		float y = y2 - y1;
		turretangle = (180f / Math.PI) * Math.atan2(x, -y);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void control(int mov, int rot)
		{
		this.movement = mov;
		this.rotation = rot;
		//why aren't these working?
		//velocity.setX(movement*(float)Math.sin(rotation*(180/Math.PI)));
		//velocity.setY(movement*(float)Math.cos(rotation*(180/Math.PI)));
		//velocity=new Vector(movement*(float)Math.sin(hullangle*(Math.PI/180.0f)),-movement*(float)Math.cos(hullangle*(Math.PI/180.0f)));
		float x = movement * (float) Math.sin(hullangle * (Math.PI / 180.0f));
		float y = -movement * (float) Math.cos(hullangle * (Math.PI / 180.0f));
		switch (collideWorldEdge())
			{
			case Top:
				if(y < 0) y = 0;
				break;
			case TopLeft:
				if(y < 0) y = 0;
				if(x < 0) x = 0;
				break;
			case TopRight:
				if(y < 0) y = 0;
				if(x > 0) x = 0;
				break;
			case Bottom:
				if(y > 0) y = 0;
				break;
			case BottomLeft:
				if(y > 0) y = 0;
				if(x < 0) x = 0;
				break;
			case BottomRight:
				if(y > 0) y = 0;
				if(x > 0) x = 0;
				break;
			case Left:
				if(x < 0) x = 0;
				break;
			case Right:
				if(x > 0) x = 0;
				break;
			}
		velocity = new Vector(x, y);
		for(int i = 0; i < Settings.numberActivePlayers; i++)
			{
			if(i != Settings.playerID)
				{
				collisionResult = this.collides(StatePlay.tanks[i]);
				if(collisionResult != null)
					{
					Vector v = collisionResult.getMinPenetration();
					velocity = v.project(velocity);
					}
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public turretentity getTurret()
		{
		return turret;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public Vector getLastPos()
		{
		return lastpos;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public float getReload()
		{
		return reload;
		}
	public void fire(){
		reload=0;
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public Edge collideWorldEdge()
		{
		if(this.getX() >= (DisplaysStatePlay.camera.worldWitdth - this.getCoarseGrainedWidth() / 2))
			{
			if(this.getY() >= (DisplaysStatePlay.camera.worldHeight - this.getCoarseGrainedHeight() / 2))
				return Edge.BottomRight;
			else if(this.getY() <= (this.getCoarseGrainedHeight() / 2)) return Edge.TopRight;
			else return Edge.Right;
			}
		else if(this.getX() <= (this.getCoarseGrainedWidth() / 2))
			{
			if(this.getY() >= (DisplaysStatePlay.camera.worldHeight - this.getCoarseGrainedHeight() / 2))
				return Edge.BottomLeft;
			else if(this.getY() <= (this.getCoarseGrainedHeight() / 2)) return Edge.TopLeft;
			else return Edge.Left;
			}
		else if(this.getY() >= (DisplaysStatePlay.camera.worldHeight - this.getCoarseGrainedHeight() / 2))
			return Edge.Bottom;
		else if(this.getY() <= (this.getCoarseGrainedHeight() / 2)) return Edge.Top;
		else return Edge.None;
		}
	}
