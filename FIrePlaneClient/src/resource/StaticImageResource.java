package resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StaticImageResource
{
  //here we save different images into public static BufferedImage
  final static int myPlaneTypeNum = 2;
  final static int backGroundTypeNum = 1;
  final static int enemyPlaneTypeNum = 2;
  final static int bulletTypeNum = 2;
  final static int explosionTypeNum = 16;
  
  public static BufferedImage logo;
  
  public static BufferedImage[] backgroudImages = new BufferedImage[backGroundTypeNum];
  public static BufferedImage[] playerPlanes = new BufferedImage[myPlaneTypeNum];
  public static BufferedImage[] enemyPlanes = new BufferedImage[enemyPlaneTypeNum];
  public static BufferedImage[] bulletImages = new BufferedImage[bulletTypeNum];
  public static BufferedImage[] explosionImages = new BufferedImage[explosionTypeNum];
  
  public static void initializeImages()
  {
    try
    {
      logo = ImageIO.read(new FileInputStream("imgs/other/LOGO.png"));
    }
    catch( FileNotFoundException e1 )
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    catch( IOException e1 )
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    for(int i = 0; i < backgroudImages.length; i++)
    {
      try
      {
        backgroudImages[i] = ImageIO.read(new FileInputStream("imgs/bg" + i + ".jpg"));
      }
      catch( IOException e )
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    for(int i = 0; i < playerPlanes.length; i++)
    {
      try
      {
        playerPlanes[i] = ImageIO.read(new FileInputStream("imgs/plane/plane" + i + ".gif"));
      }
      catch( IOException e )
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    for (int i = 0; i < bulletImages.length; i++)
    {
      try
      {
        bulletImages[i] = ImageIO.read(new FileInputStream("imgs/bullet/bullet" + i + ".png"));
      }
      catch ( IOException e)
      {
        e.printStackTrace();
      }
    }
    
    for (int i = 0; i < explosionImages.length; i++)
    {
      try
      {
        explosionImages[i] = ImageIO.read(new FileInputStream("imgs/explosion/e" + i + ".gif"));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
