package controller;

import java.util.Iterator;
import java.util.TimerTask;

import model.EnemyPlane;
import model.Plane;
import model.PlayerPlane;
import util.Location;

public class CollisionHandler extends TimerTask
{

  @Override
  public void run()
  {
    
    Iterator<PlayerPlane> itPlayer = Simulator.getAllPlayerPlanes().iterator();
    while(itPlayer.hasNext())
    {
      PlayerPlane buffPlayer = itPlayer.next();
      Iterator<EnemyPlane> itEnemy = Simulator.getAllEnemyPlanes().iterator();
      while (itEnemy.hasNext())
      {
        EnemyPlane buffEnemy = itEnemy.next();
        if (buffPlayer.isValid()&& buffEnemy.isValid())
        {
        if (Location.isContact(buffPlayer.getLocation(), buffEnemy.getLocation()))
        {
          buffPlayer.getCollision();
          buffEnemy.getCollision();
        }
        }
      }
    }
  }

}
