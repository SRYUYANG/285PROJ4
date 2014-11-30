package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import controller.GameController;
import net.Client;
import net.Packet;
import net.Packet00Login;
import net.Server;
import resource.StaticImageResource;
import subjects.*;
import ui.GameFrame;
import ui.GamePanel;

public class Main 
{
 //paul ulisse
  
  
  
	public static void main(String[] args)
	{
		
		System.out.println("Game Start!");
		//here we only place a short initialization functions
		
		GameController gc = new GameController();

	}
}
