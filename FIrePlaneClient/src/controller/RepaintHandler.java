package controller;

import java.util.Iterator;
import java.util.TimerTask;

import model.Stuff;
import model.UserInfo;
import view.GameController;

public class RepaintHandler extends TimerTask
{

  @Override
  public void run()
  {
    Iterator<Stuff> itStuff = Simulator.getAllStuffs().iterator();
    while (itStuff.hasNext())
    {
      Stuff tempStuff = itStuff.next();
      tempStuff.move();
    }
    
    if (UserInfo.UserPlane != null)
    {
    if (UserInfo.UserPlane.getHP() <= 1)
    {
      {
        UserInfo.setGameover();
      }
    }
    }
    
    GameController.getGameFrame().repaint();
  }

}
