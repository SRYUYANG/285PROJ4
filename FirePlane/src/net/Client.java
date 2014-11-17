package net;

import imageResource.StaticImageResource;

import java.awt.Panel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.SeekableByteChannel;

import net.Packet.PacketTypes;

import org.omg.CORBA.PRIVATE_MEMBER;

import subjects.PlayerPlaneMP;
import subjects.Stuff;
import ui.GamePanel;
import util.Location;
import util.Speed;

//client type
public class Client extends Thread 
{
  private InetAddress ipAddress;
  private static DatagramSocket clientSocket;
  private ObjectOutputStream outputStream;
  private ObjectInputStream inputStream;
  private String username;
  
  public Client(String ipAddress) throws IOException
  {
    //System.out.println("CLient: Attempting connecting....");
    clientSocket = new DatagramSocket();
    this.ipAddress = InetAddress.getByName(ipAddress);
    //System.out.println("Client Connected!");
    /*
    while(true)
    {
      Object o;
      try
      {
        outputStream.writeObject("Client has connected the server!");
        o = inputStream.readObject();
        System.out.println((String)o);
      }
      catch( ClassNotFoundException e )
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }*/
  }
  
  public void run()
  {
    while(true)
    {
      byte[] data = new byte[1024];
      DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, Settings.SERVER_PORT);
      try
      {
        clientSocket.receive(packet);
      }
      catch( Exception e )
      {
        // TODO: handle exception
        e.printStackTrace();
      }
      parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
      //System.out.println("SERVER > " + new String(packet.getData()));
    }
  }
  
  
  public void sendData(byte[] data)
  {
    DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, Settings.SERVER_PORT);
    try
    {
      clientSocket.send(packet);
    }
    catch( Exception e )
    {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
  
  private void parsePacket(byte[] data, InetAddress address, int port)
  {
    // TODO Auto-generated method stub
    String message = new String(data).trim();
    PacketTypes type = Packet.getPacketType(message.substring(0, 2));
    Packet packet = null;
    switch ( type )
    {
      case INVALID:
        break;
      case LOGIN:
        /*
        Stuff.getAllStuffs().add(newPlayer);
        if(pLogin.getUserName().equals(username))
          GamePanel.setMyPlayer(newPlayer);*/
        packet = new Packet00Login(data);//super(00), username got!
        System.out.println("[" + address.getHostAddress() + "." + port + "]"
            + ((Packet00Login)packet).getUserName() + " has joined the game...");
        PlayerPlaneMP newPlayer 
        = new PlayerPlaneMP(new Location(100, 100), new Speed(0, 0),
            StaticImageResource.playerPlanes[0],
            ((Packet00Login)packet).getUserName(), address, port);
        Stuff.getAllStuffs().add(newPlayer);
        break;
      case DISCONNECT:
        break;
      default:
        break;
    }
  }
  
  public void setClientName(String name)
  {
    username = name;
  }
  /*
  public void setupStreams(Socket s) throws IOException
  {
    outputStream = new ObjectOutputStream(s.getOutputStream());
    inputStream = new ObjectInputStream(s.getInputStream());
  }*/
}
