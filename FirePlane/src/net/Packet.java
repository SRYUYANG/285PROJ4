package net;

public abstract class Packet
{
  public static enum PacketTypes
  {
    INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(99);
    private int packetID;
    private PacketTypes(int _packetID)
    {
      this.packetID = _packetID;
    }
    
    public int getID()
    {
      return packetID; 
    }
  }
  
  public byte packetID;
  
  public Packet(int packetID)
  {
    this.packetID = (byte)packetID;
  }
  
  public abstract void writeData(Client client);
  public abstract void writeData(Server server);
  public abstract byte[] getData();
  
  public String readData(byte[] data)
  {
    String message = new String(data).trim();
    return message.substring(2);
  }
  
  public static PacketTypes getPacketType(int id)
  {
    for(PacketTypes p : PacketTypes.values())
    {
      if(p.getID() == id)
      {
        return p;
      }
    }
    return PacketTypes.INVALID;
  }
  
  public static PacketTypes getPacketType(String packetId)
  {
    try
    {
      return getPacketType(Integer.parseInt(packetId));
    }
    catch( NumberFormatException e )
    {
      // TODO: handle exception
      return PacketTypes.INVALID;
    }
   
  }
}
