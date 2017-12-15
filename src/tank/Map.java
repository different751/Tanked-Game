package tank;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import jig.Vector;
import jig.Entity;
public class Map extends TiledMap {
	private int pixelWidth;
	private int pixelHeight;
	
	int permanentBlocks;
	int breakableBlocks;

	public Map(String ref) throws SlickException {
		super(ref);

		setup();
	}
	
	public Map(String ref, String tileRef) throws SlickException {
		super(ref, tileRef);

		setup();

	}

	private void setup() {
		pixelWidth = super.width*super.tileWidth;
		pixelHeight = super.height*super.tileHeight;

		permanentBlocks = this.getLayerIndex("PermanentBlocks");
		breakableBlocks = this.getLayerIndex("BreakableBlocks");
	}

	/**
	 * Gives the pixel width of the map
	 * @return  Width of the map in pixels
	 */
	public int getMapWidth() { return pixelWidth; }

	/**
	 * Gives the pixel height of the map
	 * @return  Height of the map in pixels
	 */
	public int getMapHeight() { return pixelHeight; }

	/**
	 * Gives the Tile location of a tank entity
	 * @return  A vector of the tile location of the tank entity
	 */
	public Vector getTileLocation(Entity e) {
		float x = (float) (Math.floor((e.getX()) / this.tileWidth));
		float y = (float) (Math.floor((e.getY()) / this.tileHeight));
		
		return new Vector(x,y);
	}

	public float getTileXMin(final int x) { return (x*this.tileWidth); }
	
	public float getTileXMax(final int x) {
		return (x+1)*this.tileWidth;
	}
	
	public float getTileYMin(final int y) {
		return (y*this.tileHeight);
	}
	
	public float getTileYMax(final int y) {
		return (y+1)*this.tileHeight;
	}
	
	/**
	 * Gives the pixel coordinate the center of a specified tile
	 * @param   x horizontal tile position  y vertical tile position
	 * @return  Vector of the center of the tile in pixels
	 */
	public Vector getTileCenter(final int x, final int y) {
		return new Vector(getTileXMax(x)-tileWidth/2, getTileYMax(y)-tileHeight/2);
	}
	
	public Vector getTileCenter(Vector v) {
		return new Vector(getTileXMax((int)v.getX())-tileWidth/2, getTileYMax((int)v.getY())-tileHeight/2);
	}
	
	public boolean isTilePermanent(final float x, final float y) {
		if(x >= this.width || y >= this.height || x < 0 || y < 0)
			return true;

		return getTileProperty(getTileId((int)x, (int)y, this.permanentBlocks), "Permanent", "bool").equals("true");
	}
	
	public boolean isTileBreakable(final float x, final float y) {
		return getTileId((int)x, (int)y, this.breakableBlocks) != 0;
	}
	
	public boolean isTileBlocked(final float x, final float y) {
		return isTilePermanent(x,y) || isTileBreakable(x,y); 
	}

	public Vector getTileCenter(float x, float y) {
		return this.getTileCenter((int)x, (int)y);
	}
	
	public ArrayList<Vector> getTileNeighbors(Vector v) {
		ArrayList<Vector> neighbors = new ArrayList<Vector>();
		
		if(v.getX() > 0)
			neighbors.add(new Vector(v.getX() - 1, v.getY()));
		if(v.getX() < this.width - 1)
			neighbors.add(new Vector(v.getX() + 1, v.getY()));
		if(v.getY() > 0)
			neighbors.add(new Vector(v.getX(), v.getY() - 1));
		if(v.getY() < this.height - 1)
			neighbors.add(new Vector(v.getX(), v.getY() + 1));
		
		return neighbors;
	}

}
