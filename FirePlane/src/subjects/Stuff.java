package subjects;

//import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;


//import java.util.ArrayList;
import util.Location;
import util.Speed;

public abstract class Stuff
{
  private Location location = new Location(0, 0);
  private Speed speed;
  private Integer ID;
  private static Integer nextID = 0;

  private static ArrayList<Stuff> allStuffs = new ArrayList<Stuff>();
  public Stuff(Location _location, Speed _speed)
  {
    this.speed = _speed;
    ID = nextID;
    ++nextID;   
    this.location = _location;

  }
  
  synchronized public static ArrayList<Stuff> getAllStuffs()
  {
    return allStuffs;
  }
  
  synchronized public void move()
  {
    getLocation().setX(getLocation().getX() + getSpeed().getXSpeed());
    getLocation().setY(getLocation().getY() + getSpeed().getYSpeed());
  }

  
  synchronized public void setLocation(double x, double y)
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
  
  
  synchronized public void setSpeed(double speedX, double speedY)
  {
    speed.setXSpeed(speedX);
    speed.setYSpeed(speedY);
  }
  
 
  public abstract void paint(Graphics g);

  public abstract BufferedImage getImage();
  
  synchronized public static void addStuff(Stuff inStuff)
  {
    allStuffs.add(inStuff);
  }
  
  synchronized public static void deleteStuff(Stuff inStuff)
  {
    for(int i = 0; i < allStuffs.size(); i++)
    {
      if (allStuffs.get(i).equals(inStuff))
      {
        allStuffs.remove(i);
        break;
      }
    }
  }
  

}

