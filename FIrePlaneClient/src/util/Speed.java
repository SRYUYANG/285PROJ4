package util;

public class Speed
{
  private Integer xSpeed, ySpeed;
  public Speed(Integer _xSpeed, Integer _ySpeed)
  {
    xSpeed = _xSpeed;
    ySpeed = _ySpeed;
  }
  
  public Speed(Speed inSpeed)
  {
    xSpeed = inSpeed.getXSpeed();
    ySpeed = inSpeed.getYSpeed();
  }
  
  public Integer getXSpeed()
  {
    return xSpeed;
  }
  
  public Integer getYSpeed()
  {
    return ySpeed;
  }
  
  public void setXSpeed(Integer inX)
  {
    xSpeed = inX;
  }
  
  public void setYSpeed(Integer inY)
  {
    ySpeed = inY;
  }
}
