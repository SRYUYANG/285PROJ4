package main;

import java.net.SocketException;
import java.util.Timer;

import javax.swing.JOptionPane;

import controller.NetHandler;
import controller.RepaintHandler;
import view.GameController;
import model.UserInfo;
import net.ClientSocket;

public class Main
{
  public static void main(String[] args)
  {
    
    System.out.println("Game Start!");
    String buffServerIP = null;
    Integer buffClientPort = null;
    //here we only place a short initialization functions
    try
    {
      buffServerIP = JOptionPane.showInputDialog("Server Address");
      buffClientPort = Integer.parseInt(JOptionPane.showInputDialog("Port Num"));
    }
    catch (Exception e)
    {
      System.exit(1);
    }
      
    
    ClientSocket server = null;
    try
    {
      UserInfo.UserPoint = buffClientPort;
      server = new ClientSocket(buffServerIP, buffClientPort);
    }
    catch( SocketException e )
    {
      e.printStackTrace();
    }
    
    GameController gc = new GameController();
    
    NetHandler netHan = new NetHandler(server);
    RepaintHandler repaintHan = new RepaintHandler();
    
    Thread netThread = new Thread(netHan);
    netThread.start();
    
    Timer repaintTimer = new Timer();
    repaintTimer.schedule(repaintHan, 0, 20);
  }
}
