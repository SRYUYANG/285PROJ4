package subjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.Location;
import util.Speed;

public abstract class Plane extends Stuff
{
  
  //this is very important, we have to have a function to paint them
  @Override
  public void paint(Graphics g)
  {
    // TODO draw the plane
    g.drawImage(getImage(), (int)getLocation().getX(), (int)getLocation().getY(), null);
  }
  
  @Override
  public abstract Speed getSpeed();

  @Override
  public abstract BufferedImage getImage();
}
