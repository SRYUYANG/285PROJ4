package subjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.Location;
import interfaces.Movable;

public class Plane implements Movable
{
  private Location planeLocation;
  private int speed;
  private boolean isAlive;
  BufferedImage planeImage;
  
  public Plane(Location startLocation, int _speed, BufferedImage _planeImage)
  {
    speed = _speed;
    planeLocation = startLocation;
    isAlive = true;
    planeImage = _planeImage;
  }
  
  //this is very important, we have to have a function to paint them
  
  public void paintPlane(Graphics g)
  {
    // TODO draw the plane
    g.drawImage(planeImage, planeLocation.getX(), planeLocation.getY(), null);
  }
  
  @Override
  public void move()
  {
  }

  @Override
  public void moveLeft()
  {
    // TODO Auto-generated method stub
    planeLocation.setX(planeLocation.getX() - speed);
  }

  @Override
  public void moveRight()
  {
    // TODO Auto-generated method stub
    planeLocation.setX(planeLocation.getX() + speed);
  }

  @Override
  public void moveUp()
  {
    // TODO Auto-generated method stub
    planeLocation.setY(planeLocation.getY() - speed);
  }

  @Override
  public void moveDown()
  {
    // TODO Auto-generated method stub
    planeLocation.setY(planeLocation.getY() + speed);
  }

  
  public void setSpeed(int _speed)
  {
    speed = _speed;
  }
  
  public int getSpeed()
  {
    return speed;
  }
  
  public boolean isAlive()
  {
    return isAlive;
  }
  
  
  
}
