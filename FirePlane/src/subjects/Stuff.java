package subjects;

import interfaces.Movable;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import util.Location;
import util.Speed;

public abstract class Stuff
{
  private Location location;
  private Speed speed;
  private String typeName;
  private String subTypeName;
  private Integer ID;
  private static Integer nextID;
  BufferedImage stuffImage;
  private static ArrayList<Stuff> allStuffs = new ArrayList<Stuff>();
  
  public Stuff()
  {
    ID = nextID;
    ++nextID;   
  }
  
  public Stuff creatStuff(String s)
  {
    String type =
    String str = 
    switch (type)
    {
      case "PLANE":
         return new Plane(s);
      case "BULLET":
         return new Bullet(str);
    }
    
  }
  
  public void move()
  {
    getLocation().setX(getLocation().getX() + getSpeed().getXSpeed());
    getLocation().setY(getLocation().getY() + getSpeed().getYSpeed());
  }

 
  public void addStuff(Stuff s)
  {
    getAllStuffs().add(s);
  }
  
  public abstract void paint(Graphics g);
  
  public Location getLocation()
  {
    return location;
  }
  
  public void setLocation(Location l)
  {
    location = l;
  }
  
  public Speed getSpeed()
  {
    return speed;
  }
  
  public void setSpeed(Speed s)
  {
    speed = s;
  }
  
  public static ArrayList<Stuff> getAllStuffs()
  {
    return allStuffs;
  }
  
  public static void setAllStuffs(ArrayList<Stuff> allStuffs)
  {
    Stuff.allStuffs = allStuffs;
  }

  
  public BufferedImage getImage()
  {
    return stuffImage;
  }

  
}

