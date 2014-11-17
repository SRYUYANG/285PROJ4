package main;

import imageResource.StaticImageResource;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import net.Client;
import net.Packet;
import net.Packet00Login;
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
	}
}
