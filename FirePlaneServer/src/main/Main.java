package main;

import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

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
    //aiShootTimer.schedule(aiShoot, 0, 1000);
    moveTimer.schedule(moveHandler, 0, 2000);
    //collisionTimer.schedule(collisionHandler, 0, 1000);
    statusTimer.schedule(statusHandler, 0, 1000);
    //battleTimer.schedule(battleHandler, 0, 1000);
  }
  
  public static void pauseAll()
  {
    moveTimer.cancel();
    collisionTimer.cancel();
    statusTimer.cancel();
    battleTimer.cancel();
  }
  
  public static void resumeAll()
  {
    moveTimer = new Timer();
    collisionTimer = new Timer();
    statusTimer = new Timer();
    battleTimer = new Timer();
    moveHandler = new MoveHandler();
    collisionHandler = new CollisionHandler();
    statusHandler = new StatusCheckingHandler();
    battleHandler = new BattleHandler();
    moveTimer.schedule(moveHandler, 20, 50);
    collisionTimer.schedule(collisionHandler, 30, 50);
    statusTimer.schedule(statusHandler, 40, 50);
    battleTimer.schedule(battleHandler, 50, 50);
  }
}
