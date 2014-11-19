package subjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.Location;
import util.Speed;
import interfaces.Movable;

public abstract class Plane extends Stuff
{
  
  private boolean isAlive;
  
  public Plane(double x, double y)
  {
    super(x, y);
    isAlive = true;
  }

   
}
