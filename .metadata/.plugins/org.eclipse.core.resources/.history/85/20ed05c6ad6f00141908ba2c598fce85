package subjects;

//import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
//import java.util.ArrayList;
import util.Location;
import util.Speed;

public abstract class Stuff
{
  private Location location = new Location(0, 0);
  private BufferedImage myImage;
  private Speed speed;
  private Integer ID;
  private static Integer nextID = 0;
<<<<<<< HEAD
  private static ArrayList<Stuff> allStuffs = new ArrayList<Stuff>();
=======
  //private static ArrayList<Stuff> allStuffs = new ArrayList<Stuff>();
>>>>>>> FETCH_HEAD
  
  public Stuff(double x, double y, Speed _speed, BufferedImage _image)
  {
    this.myImage = _image;
    this.speed = _speed;
    ID = nextID;
    ++nextID;   
    setLocation(x,y);
  }
  
  
<<<<<<< HEAD
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
         //return new Plane(xCord,yCord);
      case "BULLET":
        // return new Bullet();
      default:
         return null;
    }  
  }
  
  
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
  

 
=======
>>>>>>> FETCH_HEAD
  public void move()
  {
    getLocation().setX(getLocation().getX() + getSpeed().getXSpeed());
    getLocation().setY(getLocation().getY() + getSpeed().getYSpeed());
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
  
  
  public void setSpeed(Speed speed_in)
  {
    speed = speed_in;
  }
  
 
  public abstract void paint(Graphics g);
<<<<<<< HEAD
  public BufferedImage getImage()
  {
    return myImage;
  }
  
=======
  public abstract BufferedImage getImage();
>>>>>>> FETCH_HEAD
}

