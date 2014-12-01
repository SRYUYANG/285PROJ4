package controller;

import java.util.Random;
import java.util.TimerTask;

import main.Main;
import model.EnemyLargePlane;
import model.EnemyMediumPlane;
import model.EnemySmallPlane;
import util.Constants;
import util.Location;
import util.Speed;

public class AICreationHandler extends TimerTask
{
  Random AINumberGen = new Random();
  Random AITypeGen = new Random();
  Random xCordGen = new Random();
  Random yCordGen = new Random();

  @Override
  public void run()
  {
    int num = AINumberGen.nextInt(18);
    for( int i = 0; i < num; i++ )
    {
      switch ( AITypeGen.nextInt(3) )
      {
        case 0:
          Simulator.addEnemyPlane(new EnemySmallPlane(new Location(xCordGen
              .nextInt(Constants.MAX_WIDTH), yCordGen.nextInt(700) - 700),
              new Speed(0, 5)));
          break;
        case 1:
          Simulator.addEnemyPlane(new EnemyMediumPlane(new Location(xCordGen
              .nextInt(Constants.MAX_WIDTH), yCordGen.nextInt(700) - 700),
              new Speed(0, 5)));
          break;
        case 2:
          Simulator.addEnemyPlane(new EnemyLargePlane(new Location(xCordGen
              .nextInt(Constants.MAX_WIDTH), yCordGen.nextInt(700) - 700),
              new Speed(0, 5)));
      }

    }
  }

}
