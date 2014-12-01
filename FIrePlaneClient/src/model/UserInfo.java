package model;

import java.net.InetAddress;

public class UserInfo
{
  public static String UserName = new String();
  public static Integer UserPoint = 8950;
  public static PlayerPlane UserPlane = null;
  public static boolean isExist = true;

  public static void setUserName(String inStr)
  {
    UserName = inStr;
  }

  public static void setUserPlane(PlayerPlane inPlane)
  {
    UserPlane = inPlane;
  }

  public static void setGameover()
  {
    isExist = false;
  }

  public static boolean isExist()
  {
    return isExist;
  }
}
