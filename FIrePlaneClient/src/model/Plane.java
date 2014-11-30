package model;

import java.awt.Graphics;

import util.Location;
import util.Speed;

public abstract class Plane extends Stuff
{
  public Plane(Location _location, Speed _speed, Integer _ID)
  {
    super(_location, _speed, _ID);
  }
}
