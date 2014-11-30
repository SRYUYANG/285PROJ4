package controller;

import java.util.ArrayList;
import java.util.Iterator;

import net.ServerSocket;
import model.EnemyPlane;
import model.Plane;
import model.Player;
import model.PlayerPlane;
import model.Stuff;
import model.Weapon;

public class Simulator
{
  private static ArrayList<Weapon> allWeapons = new ArrayList<Weapon>();
  private static ArrayList<Plane> allPlanes = new ArrayList<Plane>();
  private static ArrayList<Player> allPlayers = new ArrayList<Player>();
  private static ArrayList<Stuff> allStuffs = new ArrayList<Stuff>();
  private static ArrayList<EnemyPlane> allEnemyPlanes = new ArrayList<EnemyPlane>();
  private static ArrayList<PlayerPlane> allPlayerPlanes = new ArrayList<PlayerPlane>();
  
  synchronized public static void addEnemyPlane(EnemyPlane inPlane)
  {
    allEnemyPlanes.add(inPlane);
    addPlane(inPlane);
  }
  
  synchronized public static void addPlayerPlane(PlayerPlane inPlane)
  {
    allPlayerPlanes.add(inPlane);
    addPlane(inPlane);
  }
  
  synchronized private static void addStuff(Stuff inStuff)
  {
    allStuffs.add(inStuff);
  }
  
  synchronized public static void addWeapon(Weapon inWeapon)
  {
    allWeapons.add(inWeapon);
    addStuff(inWeapon);
  }
  
  synchronized public static void addPlayer(Player inPlayer)
  {
    allPlayers.add(inPlayer);
  }
  
  synchronized private static void addPlane(Plane inPlane)
  {
    allPlanes.add(inPlane);
    addStuff(inPlane);
  }
  
  synchronized public static void deleteStuff(Stuff inStuff)
  { 
    Iterator<Weapon> itWeapon = allWeapons.iterator();
    while(itWeapon.hasNext())
    {
      Weapon buff = itWeapon.next();
      if (buff.getID() == inStuff.getID())
        itWeapon.remove();
    }
    
    Iterator<Plane> itPlane = allPlanes.iterator();
    while(itPlane.hasNext())
    {
      Plane buff = itPlane.next();
      if(buff.getID() == inStuff.getID())
        itPlane.remove();
    }
    
    Iterator<EnemyPlane> itEnemy = allEnemyPlanes.iterator();
    while(itEnemy.hasNext())
    {
      Plane buff = itEnemy.next();
      if(buff.getID() == inStuff.getID())
        itEnemy.remove();
    }
    
    Iterator<Stuff> itStuff = allStuffs.iterator();
    while(itStuff.hasNext())
    {
      Stuff buff = itStuff.next();
      if(buff.getID() == inStuff.getID())
        itStuff.remove();
    }
    
    Iterator<PlayerPlane> itPlayer = allPlayerPlanes.iterator();
    while(itPlayer.hasNext())
    {
      Plane buff = itPlayer.next();
      if(buff.getID() == inStuff.getID())
        itPlayer.remove();
    }
  }
  
  synchronized public static void deletePlayer(Player player)
  {
    for (Player p: allPlayers)
    {
      if (p.equals(player))
      {
        allPlayers.remove(p);
        return;
      }
    }
  }
  
  synchronized public static ArrayList<Weapon> getAllWeapons()
  {
    return new ArrayList<Weapon>(allWeapons);
  }
  
  synchronized public static ArrayList<Plane> getAllPlanes()
  {
    return new ArrayList<Plane>(allPlanes);
  }
  
  synchronized public static ArrayList<Player> getAllPlayers()
  {
    return new ArrayList<Player>(allPlayers);
  }
  
  synchronized public static ArrayList<PlayerPlane> getAllPlayerPlanes()
  {
    return new ArrayList<PlayerPlane>(allPlayerPlanes);
  }
  
  synchronized public static ArrayList<EnemyPlane> getAllEnemyPlanes()
  {
    return new ArrayList<EnemyPlane>(allEnemyPlanes);
  }
  
  synchronized public static ArrayList<Stuff> getAllStuffs()
  {
    return new ArrayList<Stuff>(allStuffs);
  }
  
  synchronized public static Stuff getStuff(int inID)
  {
    for (Stuff s: getAllStuffs())
    {
      if (s.getID() == inID)
      {
        return s;
      }
    }
    return null;
  }
  
  synchronized public static PlayerPlane getPlayerPlane(String inUserName)
  {
    for (PlayerPlane p: getAllPlayerPlanes())
    {
      if (p.getPlayer().getUserName() == inUserName )
      {
        return p;
      }
    }
    return null;
  }
}
