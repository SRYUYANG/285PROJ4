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
  private Integer ID;
  private static Integer nextID;
  private static ArrayList<Stuff> allStuffs = new ArrayList<Stuff>();
  
  public Stuff()
  {
    ID = nextID;
    ++nextID;   
  }
  
  public void receiveInstruction(String instr)
  { 
    String temp = instr.substring(0,0);
    
    
  }
  
  public Stuff creatStuff(String s)
  {
    typeName = s.split("#");
    switch (type)
    {
      case "PLANE":
         return new Plane(s);
      case "BULLET":
         return new Bullet(s);
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
  
  public abstract Location getLocation();
 
  public abstract Speed getSpeed();
 
  
  
  public static ArrayList<Stuff> getAllStuffs()
  {
    return allStuffs;
  }
  
  public static void setAllStuffs(ArrayList<Stuff> allStuffs)
  {
    Stuff.allStuffs = allStuffs;
  }

  
  public abstract BufferedImage getImage();

  
}

