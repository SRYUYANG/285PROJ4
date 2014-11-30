package controller;

import java.util.Iterator;
import java.util.TimerTask;

import main.Main;
import model.EnemyPlane;
import model.Plane;

public class AIShootHandler extends TimerTask
{

  @Override
  public void run()
  {
    Iterator<EnemyPlane> itPlane = Simulator.getAllEnemyPlanes().iterator();
    while(itPlane.hasNext())
    {
      EnemyPlane buffEnemy = itPlane.next();
      if(buffEnemy.isValid()){
        buffEnemy.shoot();
      }
    }
  }
}
