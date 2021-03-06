package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.SeekableByteChannel;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Client extends Thread 
{
  private InetAddress ipAddress;
  private static DatagramSocket clientSocket;
  private ObjectOutputStream outputStream;
  private ObjectInputStream inputStream;
  
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
      System.out.println("SERVER > " + new String(packet.getData()));
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
  
  
  public static void main(String[] args)
  {
    try
    {
      Client c = new Client("localhost");
      c.sendData("ping".getBytes());
      c.start();
    }
    catch( IOException e1 )
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }
  /*
  public void setupStreams(Socket s) throws IOException
  {
    outputStream = new ObjectOutputStream(s.getOutputStream());
    inputStream = new ObjectInputStream(s.getInputStream());
  }*/
}
