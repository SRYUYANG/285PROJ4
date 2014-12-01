package controller;

import java.util.TimerTask;

import model.Plane;

public class UpdateHandler extends TimerTask
{
  @Override
  public void run()
  {
    for( Plane p : Simulator.getAllPlanes() )
    {
      p.update();
    }
  }
}
