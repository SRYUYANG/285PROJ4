package main;

import ui.GameFrame;

public class Main 
{
	public static void main(String[] args)
	{
		System.out.println("Game Start!");
		//here we only place a short initialization functions
		GameFrame gameFrame = new GameFrame("FirePlane");
		while(true)
    {
		  gameFrame.getCurrentPanel().update();
		  gameFrame.getCurrentPanel().repaint();
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
	}
}
