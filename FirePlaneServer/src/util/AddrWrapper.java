package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddrWrapper
{
  private InetAddress ipAddr;
  private int port;
  
  public AddrWrapper(InetAddress inAddr, int inPort)
  {
    ipAddr = inAddr;
    
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
