package net;

public class Packet10Shoot extends Packet
{
  
  public Packet10Shoot(byte[] data)
  {
    super(10);
    String[] dataArray = readData(data).split("#");
    Integer ID = Integer.parseInt(dataArray[0]);
  }

  @Override
  public void writeData(Client client)
  {
    
  }

  @Override
  public void writeData(Server server)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public byte[] getData()
  {
  }

}
