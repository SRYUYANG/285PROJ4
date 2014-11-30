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
    Integer buffClientNum = null;
    //here we only place a short initialization functions
    try
    {
    buffClientNum = Integer.parseInt(JOptionPane.showInputDialog("Port Num"));
    }
    catch (Exception e)
    {
      System.exit(1);
    }
    
    
    UserInfo.UserPoint = buffClientNum;
    ClientSocket server = null;
    try
    {
      server = new ClientSocket(buffClientNum);
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
