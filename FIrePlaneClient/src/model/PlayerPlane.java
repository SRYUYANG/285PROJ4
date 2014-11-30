package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import resource.StaticImageResource;
import controller.Simulator;
import util.Location;
import util.Speed;

public class PlayerPlane extends Plane
{
  private String userName;

  public PlayerPlane(Integer _ID, String _name)
  {
    super(new Location(200, 600), new Speed(0 ,0), _ID);
    userName = _name;
  }

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    g.drawString(userName, (int)getLocation().getX(), (int)getLocation().getY());
  }

  @Override
  public BufferedImage getImage()
  {
    return StaticImageResource.playerPlanes[0];
  }
  
  public String getUserName()
  {
    return userName;
  }
  
}
