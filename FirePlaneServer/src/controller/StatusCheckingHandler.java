package controller;

import java.util.Iterator;
import java.util.TimerTask;

import model.Stuff;

public class StatusCheckingHandler extends TimerTask
{

  @Override
  public void run()
  {
    Iterator<Stuff> itStuff = Simulator.getAllStuffs().iterator();
    while (itStuff.hasNext())
    {
      Stuff buff = itStuff.next();
      if (buff.isValid())
      {
        buff.destroy();
      }
    }
  }
}
