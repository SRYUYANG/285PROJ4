package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Location;
import util.Speed;
import model.EnemyLargePlane;
import model.EnemyMediumPlane;
import model.EnemySmallPlane;
import model.PlayerPlane;
import model.SmallBullet;
import model.Stuff;
import model.UserInfo;
import net.ClientSocket;

public class NetHandler implements Runnable
{
  private ClientSocket server;

  public NetHandler(ClientSocket inServer)
  {
    server = inServer;
  }

  public void run()
  {
    String receivedMessage = null;
    String encoding = null;
    while( true )
    {
      receivedMessage = server.receivePakcet();
      String pattern = "(.*)(#0@)(.*)(#1@)(.*)";
      Pattern pt = Pattern.compile(pattern);
      Matcher match = pt.matcher(receivedMessage);
      if( match.find() )
      {
        encoding = match.group(3);
      }
      else
      {
        System.out.println("Matching Not Found");
      }

      switch ( encoding )
      {
        case "00":
          handleLogin(receivedMessage);
          break;
        case "01":
          handleDisconnect(receivedMessage);
          break;
        case "11":
          handleShoot(receivedMessage);
          break;
        case "12":
          handleMove(receivedMessage);
          break;
        case "21":
          handleAddEnemy(receivedMessage);
          break;
        case "22":
          handleDestroy(receivedMessage);
          break;
        case "30":
          handleUpdateHP(receivedMessage);
          break;
        default:
          System.out.println("Unable to decode the message");
      }

    }
  }

  public void handleUpdateHP(String message)
  {
    String pattern = "(.*)(#1@)(.*)(#2@)(.*)(#3@)(.*)";
    Pattern pt = Pattern.compile(pattern);
    Matcher mt = pt.matcher(message);
    if( mt.find() )
    {
      String userName = mt.group(3);
      Integer HP = Integer.parseInt(mt.group(5));
      PlayerPlane buffPlane = Simulator.getPlayerPlane(userName);
      if(buffPlane != null)
      {
        buffPlane.setHP(HP);
      }
    }

  }

  public void handleDestroy(String message)
  {
    /*
     * #0@22#1@193#2@
     */
    String pattern = "(.*)(#1@)(.*)(#2@)(.*)";
    Pattern pt = Pattern.compile(pattern);
    Matcher mt = pt.matcher(message);
    if( mt.find() )
    {
      Integer ID = Integer.parseInt(mt.group(3));
      Stuff buff = Simulator.getStuff(ID);
      if( buff != null )
      {
        buff.destroy();
      }
    }
  }

  public void handleAddEnemy(String message)
  {
    /*
     * String sendingPacket = "#0@21#1@" + this.getType() + "#2@" + this.getID()
     * + "#3@" + getLocation().getX() + "#4@" + getLocation().getY() + "#5@" +
     * getSpeed().getXSpeed() + "#6@" + getSpeed().getYSpeed() + "#7@";
     */
    String pattern = "(.*)(#1@)(.*)(#2@)(.*)(#3@)(.*)(#4@)(.*)(#5@)(.*)(#6@)(.*)(#7@)(.*)";
    Pattern pt = Pattern.compile(pattern);
    Matcher mt = pt.matcher(message);
    if( mt.find() )
    {
      String type = mt.group(3);
      Integer ID = Integer.parseInt(mt.group(5));
      Integer xCord = Integer.parseInt(mt.group(7));
      Integer yCord = Integer.parseInt(mt.group(9));
      Integer xSpeed = Integer.parseInt(mt.group(11));
      Integer ySpeed = Integer.parseInt(mt.group(13));
      if (Simulator.getStuff(ID) == null)
      {
      switch ( type )
      {
        case "small":
          Simulator.addEnemyPlane(new EnemySmallPlane(
              new Location(xCord, yCord), new Speed(xSpeed, ySpeed), ID));
          break;
        case "medium":
          Simulator.addEnemyPlane(new EnemyMediumPlane(new Location(xCord,
              yCord), new Speed(xSpeed, ySpeed), ID));
          break;
        case "large":
          Simulator.addEnemyPlane(new EnemyLargePlane(
              new Location(xCord, yCord), new Speed(xSpeed, ySpeed), ID));
          break;
      }
      }
    }
  }

  public void handleLogin(String message)
  {

    /*
     * return "#0@00#1@" + this.userName + "#2@" + this.userID + "#3@";
     */
    // *****#1@USERNAME#2@IP#3@PORT#4@************
    String pattern = "(.*)(#1@)(.*)(#2@)(.*)(#3@)(.*)";
    Pattern pt = Pattern.compile(pattern);
    Matcher mt = pt.matcher(message);
    if( mt.find() )
    {
      String userName = mt.group(3);
      Integer userID = Integer.parseInt(mt.group(5));
      if( Simulator.getPlayerPlane(userName) == null )
      {
        PlayerPlane newPlane = new PlayerPlane(userID, userName);
        Simulator.addPlayerPlane(newPlane);
        if( userName.equals(UserInfo.UserName) )
          UserInfo.setUserPlane(newPlane);
      }
    }
  }

  public void handleShoot(String message)
  {
    // *********#1@ID#2@**********
    String pattern = "(.*)(#1@)(.*)(#2@)(.*)(#3@)(.*)(#4@)(.*)(#5@)(.*)(#6@)(.*)(#7@)(.*)";
    Pattern pt = Pattern.compile(pattern);
    Matcher mt = pt.matcher(message);
    if( mt.find() )
    {
      String type = mt.group(3);
      Integer ID = Integer.parseInt(mt.group(5));
      Integer xCord = Integer.parseInt(mt.group(7));
      Integer yCord = Integer.parseInt(mt.group(9));
      Integer xSpeed = Integer.parseInt(mt.group(11));
      Integer ySpeed = Integer.parseInt(mt.group(13));

      System.out.println(type);
      if(Simulator.getStuff(ID) == null)
      {
      switch ( type )
      {
        case "small":
          Simulator.addWeapon(new SmallBullet(new Location(xCord, yCord),
              new Speed(xSpeed, ySpeed), ID));
          break;
        default:
          break;
      /*
       * case "medium": Simulator.addEnemyPlane( new EnemyMediumPlane( new
       * Location(xCord, yCord), new Speed(xSpeed, ySpeed), ID)); break; case
       * "large": Simulator.addEnemyPlane( new EnemyLargePlane( new
       * Location(xCord, yCord), new Speed(xSpeed, ySpeed), ID)); break; }
       */
      }
      }
    }
  }

  public void handleMove(String message)
  {
    String pattern = "(.*)(#1@)(.*)(#2@)(.*)(#3@)(.*)(#4@)(.*)(#5@)(.*)(#6@)(.*)";
    Pattern pt = Pattern.compile(pattern);
    Matcher mt = pt.matcher(message);
    if( mt.find() )
    {
      Integer stuffID = Integer.parseInt(mt.group(3));
      Integer xCord = Integer.parseInt(mt.group(5));
      Integer yCord = Integer.parseInt(mt.group(7));
      Integer xSpeed = Integer.parseInt(mt.group(9));
      Integer ySpeed = Integer.parseInt(mt.group(11));

      Stuff tempStuff = Simulator.getStuff(stuffID);
      if( tempStuff == null )
      {
        System.out.println("Unrecognized Stuff");
      }
      else
      {
        tempStuff.setLocation(new Location(xCord, yCord));
        tempStuff.setSpeed(new Speed(xSpeed, ySpeed));
      }
    }
  }

  public void handleDisconnect(String message)
  {

  }

}
