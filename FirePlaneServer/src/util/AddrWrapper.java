package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddrWrapper
{
  private InetAddress ipAddr;
  private int port;
  
  public AddrWrapper(String inIpStr, int inPort)
  {
    try
    {
      ipAddr = InetAddress.getByName(inIpStr);
    }
    catch( UnknownHostException e )
    {
      e.printStackTrace();
    }
    
    port = inPort;
  }
  
  public InetAddress getIP()
  {
    return ipAddr;
  }
  
  public int getPort()
  {
    return port;
  }
}
