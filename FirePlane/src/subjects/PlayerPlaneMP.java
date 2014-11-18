package subjects;

import java.awt.image.BufferedImage;
import java.net.InetAddress;

import util.Location;
import util.Speed;

public class PlayerPlaneMP extends PlayerPlane
{
  public InetAddress ipAddress;
  public int port;
  
  public PlayerPlaneMP(Location startLocation, Speed _speed,
      BufferedImage _planeImage, String _username, InetAddress _ipAddress, int _port)
  {
    super(startLocation, _speed, _planeImage, _username);
    this.ipAddress = _ipAddress;
    this.port = _port;
    // TODO Auto-generated constructor stub
  }

}