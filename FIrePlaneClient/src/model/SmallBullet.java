package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import resource.StaticImageResource;
import util.Location;
import util.Speed;

public class SmallBullet extends Weapon
{
  public SmallBullet(Location _location, Speed _speed, Integer _ID)
  {
    super(_location, _speed, _ID);
  }

  @Override
  public BufferedImage getImage()
  {
    return StaticImageResource.bulletImages[1];
  }
}
