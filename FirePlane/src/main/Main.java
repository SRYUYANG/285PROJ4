package main;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import ui.GameFrame;

public class Main 
{
  private static ServerSocket serverSocket;
  private static Socket clientSocket;
  
	public static void main(String[] args)
	{
		System.out.println("Game Start!");
		//here we only place a short initialization functions
		
		/*while(true)
    {
      gameFrame.getCurrentPanel().update();
      gameFrame.getCurrentPanel().repaint();
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }*/
		
	}
}
