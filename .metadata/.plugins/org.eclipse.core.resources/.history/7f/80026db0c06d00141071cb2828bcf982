package net;

public class Packet00Login extends Packet
{
  private String username;
  public Packet00Login(byte[] data)
  {
    super(00);
    this.username = readData(data);
    // TODO Auto-generated constructor stub
  }
  
  public Packet00Login(String username)
  {
    super(00);
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
    return ("00" + this.username).getBytes();
  }
  
}
