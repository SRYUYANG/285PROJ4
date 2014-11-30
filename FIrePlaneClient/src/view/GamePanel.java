package view;


import resource.StaticImageResource;
import model.*;
import net.ClientSocket;
import net.Settings;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Simulator;
import resource.StaticImageResource;
import util.Location;
import util.Speed;

public class GamePanel extends JPanel implements KeyListener
{ 
  private boolean keyActivated = true;
  
  BufferedImage currentBackGroundBufferedImage;
  public GamePanel(BufferedImage bg)
  {
    currentBackGroundBufferedImage = bg;
    this.addListener();
    UserInfo.UserName = JOptionPane.showInputDialog(this, "Please enter Username");
    
    String loginPacket = "#0@00#1@" + UserInfo.UserName + "#2@" + "#3@" + "#4@";
    
    ClientSocket.sendPacket(loginPacket);
  }
  
  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    g.drawImage(currentBackGroundBufferedImage, 0, 0, null);
    for (int i = 0; i < Simulator.getAllStuffs().size(); i++)
    {
      Simulator.getAllStuffs().get(i).paint(g);
    }
  }
  
  @Override
  public void keyTyped(KeyEvent e)
  {
 
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    if (getControlString(e.getKeyCode()) != null && keyActivated)
    {
      keyActivated = false;
      ClientSocket.sendPacket(getControlString(e.getKeyCode()));
    }
  }
  
  public String getControlString(int keyCode){
    if (keyCode == KeyEvent.VK_UP)
    {
      UserInfo.UserPlane.setSpeed(new Speed(0, -10));
      return "#0@11#1@" + UserInfo.UserName + "#2@0#3@-10#4@";
      
    }
    if (keyCode == KeyEvent.VK_DOWN)
    {
      UserInfo.UserPlane.setSpeed(new Speed(0, 10));
      return "#0@11#1@" + UserInfo.UserName + "#2@0#3@10#4@";
    }
    if (keyCode == KeyEvent.VK_LEFT)
    {
      UserInfo.UserPlane.setSpeed(new Speed(-10, 0));
      return "#0@11#1@" + UserInfo.UserName + "#2@-10#3@0#4@";
    }
    if (keyCode == KeyEvent.VK_RIGHT)
    {
      UserInfo.UserPlane.setSpeed(new Speed(10, 0));
      return "#0@11#1@" + UserInfo.UserName + "#2@10#3@0#4@";
    }
    if (keyCode == KeyEvent.VK_SPACE)
    {
      return "#0@10#1@" + UserInfo.UserName + "#2@";
    }
    
    return null;
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    keyActivated = true;
    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN
        ||e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      ClientSocket.sendPacket("#0@11#1@" + UserInfo.UserName + "#2@0#3@0#4@");
      UserInfo.UserPlane.setSpeed(new Speed(0, 0));
    }
  }

  protected void addListener()
  {
    this.addKeyListener(this);
  }
  
}
