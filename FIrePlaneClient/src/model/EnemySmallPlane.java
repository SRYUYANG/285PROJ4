package model;

import java.awt.image.BufferedImage;

import resource.StaticImageResource;
import controller.Simulator;
import util.Location;
import util.Speed;

public class EnemySmallPlane extends EnemyPlane
{

  public EnemySmallPlane(Location _location, Speed _speed, Integer _ID)
  {
    super(_location, _speed, _ID);
    // TODO Auto-generated constructor stub
  }

  @Override
  public BufferedImage getImage()
  {
    return StaticImageResource.playerPlanes[1];
  }
}
