package util;

public class Signal
{
  public static int counter = 0;

  public static void setStartSignal()
  {
    counter++;
  }

  synchronized public static boolean isStart()
  {
    return counter >= 2;
  }
}
