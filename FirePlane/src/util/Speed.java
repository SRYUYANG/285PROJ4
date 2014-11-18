package util;

public class Speed
{
  private double xSpeed, ySpeed;
  public Speed(double _xSpeed, double _ySpeed)
  {
    xSpeed = _xSpeed;
    ySpeed = _ySpeed;
  }
  
  public double getXSpeed()
  {
    return xSpeed;
  }
  
  public double getYSpeed()
  {
    return ySpeed;
  }
  
  public void setXSpeed(double inX)
  {
    xSpeed = inX;
  }
  
  public void setYSpeed(double inY)
  {
    ySpeed = inY;
  }
  
  public static Speed createSpeed(double _xSpeed, double _ySpeed)
  {
    return new Speed(_xSpeed, _ySpeed);
  }
}
