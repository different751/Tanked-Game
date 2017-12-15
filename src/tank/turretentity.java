package tank;

import jig.Entity;
import jig.ResourceManager;
public class turretentity extends Entity{

	private tankentity parent;

	/*-----------------------------------------------------------------------------------------------------*/
	public turretentity(final float x, final float y, final char team, tankentity parent){
		super(x,y);
		
		this.parent=parent;
		switch(team){
			case 'r':
				ResourceManager.loadImage(Filenames.turret_r);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.turret_r).getScaledCopy(0.25f));
			break;
			
			case 'g':
				ResourceManager.loadImage(Filenames.turret_g);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.turret_g).getScaledCopy(0.25f));
			break;
			
			case 'b':
				ResourceManager.loadImage(Filenames.turret_b);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.turret_b).getScaledCopy(0.25f));
			break;
			
			case 'y':
				ResourceManager.loadImage(Filenames.turret_y);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.turret_y).getScaledCopy(0.25f));
			break;
		}
		
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public void update(final int delta){
		this.setPosition(parent.getPosition());
		this.setRotation(parent.getTurretAngle());
	}
	
}
