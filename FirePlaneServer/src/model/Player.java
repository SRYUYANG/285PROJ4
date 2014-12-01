package model;

import net.ServerSocket;
import controller.Simulator;
import util.AddrWrapper;
import util.Location;
import util.Speed;

public class Player
{
  private PlayerPlane playerPlane;
  private String userName = null;
  private Integer userID = null;
  private static Integer nextID = 0;
  //private Integer points = 0;
  private AddrWrapper playerAddr = null;
  
  public Player(String inUserName, String inAddr, int inPort)
  {
    userName = inUserName;
    playerAddr = new AddrWrapper(inAddr, inPort);
    userID = nextID;
    nextID++;
    playerPlane = new PlayerPlane(new Location(200, 600), new Speed(0, 0), this);
    Simulator.addPlayer(this);
    Simulator.addPlayerPlane(this.getPlayerPlane());
    ServerSocket.addClient(playerAddr);
    for (Player p: Simulator.getAllPlayers())
    {
      ServerSocket.sendPacket(p.loginString());
    }
    ServerSocket.sendPacket(this.loginString());
  }
  
  synchronized public PlayerPlane getPlayerPlane()
  {
    return playerPlane;
  }
  
  synchronized public String loginString()
  {
    return "#0@00#1@" + this.userName + "#2@" + this.userID + "#3@";
  }
  
  synchronized public String getUserName()
  {
    return userName;
  }
  /*
  public void addPoints()
  {
    
  }
  */
}
