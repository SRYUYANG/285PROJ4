package controller;

import java.util.Iterator;
import java.util.TimerTask;

import util.Location;
import model.EnemyPlane;
import model.Plane;
import model.PlayerPlane;
import model.Weapon;

public class BattleHandler extends TimerTask
{

  @Override
  public void run()
  {
    
    
    Iterator<Plane> itPlane = Simulator.getAllPlanes().iterator();
    while(itPlane.hasNext())
    {
      Plane buffPlane = itPlane.next();
      Iterator<Weapon> itWeapon = Simulator.getAllWeapons().iterator();
      while (itWeapon.hasNext())
      {
        Weapon buffWeapon = itWeapon.next();
        if (buffWeapon.isValid() && buffPlane.isValid())
        {
        if (Location.isContact(buffPlane.getLocation(), buffWeapon.getLocation()))
        {
          buffPlane.getShot(buffWeapon);
        }
        }
      }
    }
  }
}
