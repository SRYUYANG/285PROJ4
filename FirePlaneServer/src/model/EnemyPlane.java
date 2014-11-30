package model;

import java.util.Random;

import net.ServerSocket;
import util.Location;
import util.Speed;

public abstract class EnemyPlane extends Plane
{

  public EnemyPlane(Location _location, Speed _speed)
  {
    super(_location, _speed);
    String sendingPacket = "#0@21#1@" + this.getType() + "#2@" + this.getID() + "#3@" + getLocation().getX()
        + "#4@" + getLocation().getY() + "#5@" + getSpeed().getXSpeed() + "#6@"
        + getSpeed().getYSpeed() + "#7@";
    ServerSocket.sendPacket(sendingPacket);
    // TODO Auto-generated constructor stub
  }
  
  public abstract void shoot();

  public abstract String getType();
  
  @Override
  synchronized public void move()
  {
    if (isExist())
    {
    Random speedGen = new Random();
    Integer nextXSpeed = speedGen.nextInt(50) - 25;
    Integer nextYSpeed = speedGen.nextInt(60);
    this.setSpeed(new Speed(nextXSpeed, nextYSpeed));
    //super.move();
    }
  }

  @Override
  synchronized public boolean getStatus()
  {
    // TODO Auto-generated method stub
    return false;
  }

}
