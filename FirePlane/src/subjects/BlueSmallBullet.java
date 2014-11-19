package subjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import resource.StaticImageResource;
import util.Location;
import util.Speed;

public class BlueSmallBullet extends Bullet
{
  private BufferedImage image;

  public BlueSmallBullet(Plane plane)
  {
    super(new Location(plane.getLocation()), new Speed(0.0, -10.0));
    image = StaticImageResource.bulletImages[0];
    // TODO Auto-generated constructor stub
  }

  @Override
  public void paint(Graphics g)
  {
    g.drawImage(getImage(), (int)getLocation().getX(), (int)getLocation().getY(), null);
  }

  @Override
  public BufferedImage getImage()
  {
    // TODO Auto-generated method stub
    return image;
  }

}
