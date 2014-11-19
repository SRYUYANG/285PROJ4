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
<<<<<<< HEAD
  BufferedImage stuffImage;
  
  
  private static ArrayList<Stuff> allStuffs = new ArrayList<Stuff>();
  
  public Stuff(String inStr)
=======
  private Integer ID;
  private static Integer nextID;
  private static ArrayList<Stuff> allStuffs = new ArrayList<Stuff>();
  
  public Stuff(double x, double y)
  {
    ID = nextID;
    ++nextID;   
    setLocation(x,y);
  }
  
  
  public void receiveInstruction(String instr)
  { 
    String temp = instr.substring(0,1);
    instr = instr.substring(2);
    switch(temp)
    {
      case "+":
      /*instruction type
       * "+#PLANE#B#Xcord#Ycord"
       */
        addStuff(createStuff(instr));
        break;
      case "-":
      /*instruction type
       * "-#ID"
       */
        deleteStuff(instr);
        break;
      case "?":
      /*instruction type
       *"?#ID#Xspeed#Yspeed"  
       */
        
        break;
      default:
        break;
    }
  }
  
  
  public Stuff createStuff(String s)
>>>>>>> 98a06519faf76768b649f9a4f21974bc4b235eb1
  {
    int i = 0;
    String str[];
    str = new String[4];
    double xCord;
    double yCord;
    
    for (String temp: s.split("#"))
    {
      str[i] = temp;
      ++i;
    }
    String type = str[0]+str[1];
    try
    {
      xCord = Double.valueOf(str[2]).doubleValue();
      yCord = Double.valueOf(str[3]).doubleValue();
    }
    catch( NumberFormatException excep )
    {
      System.out.println("Wrong create instr");
    }
        
    switch (type)
    {
      case "PLANE":
         return new Plane(xCord,yCord);
      case "BULLET":
        // return new Bullet();
      default:
         return null;
    }  
  }
<<<<<<< HEAD
=======
  
  
  int keyID;
  boolean isFind;
  public void deleteStuff(String s)
  {
    try
    {
      keyID = Integer.valueOf(s).intValue();
    }
    catch( NumberFormatException excep )
    {
      System.out.println("Wrong create instr");
    }
    
    isFind = false;
    for (Stuff stuff: allStuffs)
    {
      if (stuff.getID() == keyID)
      {
        allStuffs.remove(stuff);
        isFind = true;
        break;
      }
    }
    if (!isFind)
    {
      System.out.println("Wrong ID!");
    }   
  }
>>>>>>> 98a06519faf76768b649f9a4f21974bc4b235eb1
  

 
  public void move()
  {
    getLocation().setX(getLocation().getX() + getSpeed().getXSpeed());
    getLocation().setY(getLocation().getY() + getSpeed().getYSpeed());
  }

 
  public void addStuff(Stuff s)
  {
    getAllStuffs().add(s);
  }
  
  
  public void setLocation(double x, double y)
  {
    getLocation().setX(x);
    getLocation().setY(y);
  }
  
  
  public Location getLocation()
  {
    return location;
  }
  
 
  public Integer getID()
  {
    return ID;
  }
  
  
  public Speed getSpeed()
  {
    return speed;
  }
  
  
  public void setSpeed(double x, double y)
  {
    getSpeed().setXSpeed(x);
    getSpeed().setYSpeed(y);
  }
  
  
  public static ArrayList<Stuff> getAllStuffs()
  {
    return allStuffs;
  }
  
<<<<<<< HEAD
  public BufferedImage getImage()
  {
  }

  public String toString()
  {
    
  }
=======
  public static void setAllStuffs(ArrayList<Stuff> allStuffs)
  {
    Stuff.allStuffs = allStuffs;
  }

  
  public abstract void paint(Graphics g);
  public abstract BufferedImage getImage();
  
>>>>>>> 98a06519faf76768b649f9a4f21974bc4b235eb1
}

