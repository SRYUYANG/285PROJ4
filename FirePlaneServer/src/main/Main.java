package main;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import util.Signal;
import controller.*;
import net.ServerSocket;

public class Main
{
  public static AICreationHandler aiCreation = new AICreationHandler();
  public static Timer aiCreationTimer = new Timer();
  
  public static AIShootHandler aiShoot = new AIShootHandler();
  public static Timer aiShootTimer = new Timer();
  
  public static MoveHandler moveHandler = new MoveHandler();
  public static Timer moveTimer = new Timer();
  
  public static CollisionHandler collisionHandler = new CollisionHandler();
  public static Timer collisionTimer = new Timer();
  
  public static StatusCheckingHandler statusHandler = new StatusCheckingHandler();
  public static Timer statusTimer = new Timer();
  
  public static BattleHandler  battleHandler = new BattleHandler();
  public static Timer battleTimer = new Timer();
  
  public static void main(String[] args)
  {
    try
    {
      JOptionPane.showMessageDialog(null, InetAddress.getLocalHost().getHostAddress());
    }
    catch( UnknownHostException e1 )
    {
      e1.printStackTrace();
    }
    
    ServerSocket server = null;
    try
    {
      server = new ServerSocket();
    }
    catch( SocketException e )
    {
      e.printStackTrace();
    }
    
    NetHandler netHandler = new NetHandler(server);
    Thread netThread = new Thread(netHandler);
    netThread.start();
    
    while(!Signal.isStart())
    {
      
    }
    
    aiCreationTimer.schedule(aiCreation, 0, 5000);
    aiShootTimer.schedule(aiShoot, 0, 500);
    moveTimer.schedule(moveHandler, 0, 20);
    collisionTimer.schedule(collisionHandler, 0, 200);
    statusTimer.schedule(statusHandler, 0, 10);
    battleTimer.schedule(battleHandler, 0, 20);
  }
}
