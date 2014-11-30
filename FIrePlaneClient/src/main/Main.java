package main;

import java.net.SocketException;
import java.util.Timer;

import controller.ClientNetHandler;
import controller.RepaintHandler;
import view.GameController;
import net.ClientSocket;

public class Main
{
  public static void main(String[] args)
  {
    ClientSocket server = null;
    try
    {
      server = new ClientSocket();
    }
    catch( SocketException e )
    {
      e.printStackTrace();
    }
    
    
    System.out.println("Game Start!");
    //here we only place a short initialization functions
    GameController gc = new GameController();
    
    ClientNetHandler netHan = new ClientNetHandler(server);
    RepaintHandler repaintHan = new RepaintHandler();
    
    Thread netThread = new Thread(netHan);
    netThread.start();
    
    Timer repaintTimer = new Timer();
    repaintTimer.schedule(repaintHan, 0, 20);
  }
}
