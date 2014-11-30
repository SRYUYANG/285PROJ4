package util;

public class Signal
{
  public static boolean START_SIGNAL = false;
  
  public static void setStartSignal(boolean b)
  {
    START_SIGNAL = b;
  }
  
  synchronized public static boolean isStart()
  {
    return START_SIGNAL;
  }
}
