package subjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import resource.StaticImageResource;
import statistic.Statistic;
import ui.GameFrame;
import util.Location;
import util.Speed;

public class EnemyPlane extends Plane
{
  static int nextID = 0;
  private int id;
  private BufferedImage image;
  private int HP = 3;
  private int nextXSpeed; 
  private int counter = 0;
  public EnemyPlane(Location _location)
  {
    super(_location);
    id = nextID;
    nextID++;
    nextXSpeed = 5;
    image = StaticImageResource.playerPlanes[1];
    super.setSpeed((double)nextXSpeed, (double)0);
    // TODO Auto-generated constructor stub
  }
  
  //public abstract void shoot();

  //public abstract String getType();
  
  @Override
  synchronized public void move()
  {
    
    super.setLocation(getLocation().getX() + getSpeed().getXSpeed(), 
        getLocation().getY() + getSpeed().getYSpeed());
    //int nextXSpeed = speedGen.nextInt(50) - 25;
    
    
    if((getLocation().getX() + getSpeed().getXSpeed()) > (GameFrame.WINDOWS_WIDTH) && getSpeed().getXSpeed() > 0)
    {
      nextXSpeed = -nextXSpeed;
      super.setSpeed((double)nextXSpeed, (double)0);
    }
    else if((getLocation().getX() + getSpeed().getXSpeed()) < 0 && getSpeed().getXSpeed() < 0)
    {
      nextXSpeed = -nextXSpeed;
      super.setSpeed((double)nextXSpeed, (double)0);
    }
    for(Bullet s : Stuff.getAllBullets())
    {
      System.out.println(Stuff.getAllBullets().size());
      if(getBounds().intersects(s.getBounds()))
      {
        //HP -= s.getPower();
        Statistic.setScore(Statistic.getScore() + 5);
        //s.setPower(0);
        //if(HP == 0)
        //{
        
          setAlive(false);
          break;
        //}
      }
    }
    if(!isAlive())
    {
          if (counter < 16)
          {
            image = StaticImageResource.explosionImages[counter];
            counter++;
          }
          else
          {
            image = null;
            Stuff.deleteEnemyPlane(this);
          }
    }
    
    //super.move();
  }
  
  public BufferedImage getImage()
  {
    // TODO Auto-generated method stub
    return image;
  }

  @Override
  public void paint(Graphics g)
  {
    // TODO Auto-generated method stub
    g.drawImage(getImage(), (int)getLocation().getX(), (int)getLocation().getY(), null);
    if(image != null)
      g.drawRect((int)getLocation().getX(), (int)(getLocation().getY()+image.getHeight() + 3), HP*10, 2);
  }
  
  public Rectangle getBounds()
  {
    return new Rectangle((int)getLocation().getX(), (int)getLocation().getY(), getImage().getWidth(), getImage().getHeight());
  }

  
}
