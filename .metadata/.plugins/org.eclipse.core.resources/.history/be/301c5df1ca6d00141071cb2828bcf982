package main;

import imageResource.StaticImageResource;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import net.Client;
import net.Server;
import ui.GameFrame;

public class Main 
{
 //paul ulisse
  
  
  
	public static void main(String[] args)
	{
		
		System.out.println("Game Start!");
		//here we only place a short initialization functions
		GameFrame gf = new GameFrame("FireFlight");
		
	  try
    {
	    Server server = new Server();
      Client client = new Client("localhost");
      server.start();
      client.sendData("ping".getBytes());
      client.start();
    }
    catch( IOException e )
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(7);
    }
	}
}
