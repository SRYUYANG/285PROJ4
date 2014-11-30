package model;

public class UserInfo
{
  public static String UserName = new String();
  public static Integer UserPoint = 8930;
  public static PlayerPlane UserPlane = null;
  
  public static void setUserName (String inStr){
    UserName = inStr;
  }
  
  public static void setUserPlane(PlayerPlane inPlane)
  {
    UserPlane = inPlane;
  }
}
