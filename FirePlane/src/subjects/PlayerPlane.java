package subjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.Location;
import util.Speed;

public class PlayerPlane extends Plane
{
  private String username;
  private final double playerPlaneXSpeed = 0.2;
  private final double playerPlaneYSpeed = 0.2;
  private Speed playerPlaneSpeed = Speed.createSpeed
      (
          playerPlaneXSpeed, playerPlaneYSpeed
      );
  
  public PlayerPlane()
  {
    super();
  }
  
  public String getUserName()
  {
    return this.username;
  }
  
  public Speed getSpeed()
  {
    return playerPlaneSpeed;
  }
  
  public BufferedImage getImage()
  {
    // TODO Auto-generated method stub
    return null;
  }
}
