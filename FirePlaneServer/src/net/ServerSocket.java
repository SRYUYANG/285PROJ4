package net;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.net.DatagramPacket;

import util.AddrWrapper;

public class ServerSocket
{
  private int portNum;
  private static DatagramSocket inSocket;
  private static DatagramSocket outSocket;
  private static ArrayList<AddrWrapper> clients; 
  
  public ServerSocket() throws SocketException
  {
    System.out.println("System Start!");
    outSocket = new DatagramSocket(8900);
    clients = new ArrayList<AddrWrapper>();
    inSocket = new DatagramSocket(8910);
  }
  
  synchronized public static void sendPacket(String inStr)
  {
    System.out.print("Server Sent:");
    System.out.println(inStr);
    byte[] buffer = inStr.getBytes();
    if (getAllClients() == null) return;
    for (AddrWrapper client : getAllClients())
    {
      DatagramPacket outPacket = 
          new DatagramPacket(buffer, buffer.length, client.getIP(), client.getPort());
      //System.out.println(client.getIP().toString() +  (new Integer(client.getPort())).toString());
      try
      {
        outSocket.send(outPacket);
      }
      catch( IOException e )
      {
        e.printStackTrace();
      }
    }
  }
  
  public String receivePakcet()
  {
    System.out.print("Server Received:");
    
    byte[] buffer = new byte[100];
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
    try
    {
      inSocket.receive(packet);
      System.out.println(new String(buffer));
      return new String(buffer);
      //TODO How to handle the received packet
    }
    catch( IOException e )
    {
      e.printStackTrace();
    }
    return null;
  }
  
  synchronized public static ArrayList<AddrWrapper> getAllClients()
  {
    return clients;
  }
  
  synchronized public static void addClient(AddrWrapper inAddr)
  {
    getAllClients().add(inAddr);
  }
}
