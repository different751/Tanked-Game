package tank;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Camera
    {

    Map map;
    int worldWitdth;
    int worldHeight;
    Rectangle viewport;
    int viewportWidth = 960;
    int viewportHeight = 616;

    int xPos;
    int yPos;

    int tileOffsetX;
    int tileOffsetY;

    public int pixelOffsetX;
    public int pixelOffsetY;

    public Camera(String mapPath, final int x, final int y) throws SlickException
        {
        xPos = x;
        yPos = y;
        map = new Map(mapPath);

        worldWitdth = map.getMapWidth();
        worldHeight = map.getMapHeight();

        viewport = new Rectangle(xPos, yPos, viewportWidth, viewportHeight);

        tileOffsetX = 0;
        tileOffsetY = 0;

        pixelOffsetX = 0;
        pixelOffsetY = 0;

        }

    public void update(tankentity player, int delta)
        {
        int x = -(int)player.getX() + (viewportWidth / 2);
        int y = -(int)player.getY() + (viewportHeight / 2);
        x = Math.min(0, x);
        y = Math.min(0, y);

        x = Math.max(x, -(worldWitdth - viewportWidth));
        y = Math.max(y, -(worldHeight - viewportHeight));

        pixelOffsetX = x;
        pixelOffsetY = y;
        }


    public void render(Graphics g)
        {

        //map.render(xPos + pixelOffsetX, yPos + pixelOffsetY, tileOffsetX, tileOffsetY, viewportTileWidth + 1, viewportTileHeight + 1);
        //map.render(xPos + pixelOffsetX, yPos + pixelOffsetY);
        map.render(0, 0);


        }


    }
