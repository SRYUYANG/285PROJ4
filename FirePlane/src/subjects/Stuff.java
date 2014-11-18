package subjects;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import util.Location;
import util.Speed;

public abstract class Stuff
{
  private String typeName;
  private String subTypeName;
  private Integer ID;
  private Location location;
  private Speed speed;
  BufferedImage stuffImage;
  
  
  private static ArrayList<Stuff> allStuffs = new ArrayList<Stuff>();
  
  public Stuff(String inStr)
  {
    
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
  
  public BufferedImage getImage()
  {
  }

  public String toString()
  {
    
  }
}

