package model;

import java.awt.Graphics;

import util.Location;
import util.Speed;

public abstract class Weapon extends Stuff
{
  public Weapon(Location _location, Speed _speed, Integer _ID)
  {
    super(_location, _speed, _ID);
  }
  
  public void paint(Graphics g)
  {
    
  }
}
