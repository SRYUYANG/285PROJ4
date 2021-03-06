package net;

public class Packet10Shoot extends Packet
{
  private String username;
  public Packet10Shoot(byte[] data)
  {
    super(10);
    this.username = readData(data);
    // TODO Auto-generated constructor stub
  }
  
  public Packet10Shoot(String username)
  {
    super(10);
    this.username = username;
    // TODO Auto-generated constructor stub
  }

  @Override
  public void writeData(Client client)
  {
    // TODO Auto-generated method stub
    client.sendData(getData());
  }

  @Override
  public void writeData(Server server)
  {
    // TODO Auto-generated method stub
    server.sendDataToAllClients(getData());
  }

  @Override
  public byte[] getData()
  {
    // TODO Auto-generated method stub
    return ("10"+ this.username).getBytes();
  }

  public String getUserName()
  {
    // TODO Auto-generated method stub
    return username;
  }
  /*
  public int getX()
  {
    return this.x;
  }
  
  public int getY()
  {
    return this.y;
  }
  
  public int getSpeedX()
  {
    return this.speedX;
  }
  
  public int getSpeedY()
  {
    return this.speedY;
  }*/
}
