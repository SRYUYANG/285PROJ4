package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteOrder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import resource.StaticImageResource;

public class GameFrame extends JFrame
{
  final private static int WINDOWS_WIDTH = 550;
  final private static int WINDOWS_HEIGHT = 700;
  // private static JButton startButton;
  // private static JButton exitButton;
  private static JPanel currentPanel;

  public GameFrame(String title)
  {
    super(title);
    StaticImageResource.initializeImages();
    // currentGamePanel = new GamePanel(StaticImageResource.backgroudImages[0]);
    // Thread thread = new Thread(currentGamePanel);
    // thread.start();
    // this.add(currentGamePanel);
    // this.addKeyListener(currentGamePanel);
    this.setMinimumSize(new Dimension(WINDOWS_WIDTH, WINDOWS_HEIGHT));
    this.setMaximumSize(new Dimension(WINDOWS_WIDTH, WINDOWS_HEIGHT));
    this.setSize(new Dimension(WINDOWS_WIDTH, WINDOWS_HEIGHT));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }


  public static JPanel getCurrentPanel()
  {
    return currentPanel;
  }

  public void setJpanel(JPanel jp)
  {
    currentPanel = jp;
  }

}
