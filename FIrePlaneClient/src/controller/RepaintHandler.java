package controller;

import java.util.Iterator;
import java.util.TimerTask;

import model.Stuff;
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
    GameController.getGameFrame().repaint();
  }

}
