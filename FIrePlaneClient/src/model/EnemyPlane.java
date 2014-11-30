package model;
import controller.Simulator;
import util.Location;
import util.Speed;

public abstract class EnemyPlane extends Plane
{
  public EnemyPlane(Location _location, Speed _speed, Integer _ID)
  {
    super(_location, _speed, _ID);
  }
}
