package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import resource.StaticImageResource;
import controller.Simulator;
import util.Location;
import util.Speed;

public class PlayerPlane extends Plane
{
  private String userName;
  private Integer HP = 80;

  public PlayerPlane(Integer _ID, String _name)
  {
    super(new Location(200, 600), new Speed(0, 0), _ID);
    userName = _name;
  }

  public void setHP(Integer _HP)
  {
    HP = _HP;
  }

  public Integer getHP()
  {
    return HP;
  }

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    g.setColor(Color.RED);
    g.drawString(userName, (int) getLocation().getX(), (int) getLocation()
        .getY());
    g.fillRect((int) getLocation().getX(), (int) getLocation().getY() - 30,
        getHP(), 5);
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
