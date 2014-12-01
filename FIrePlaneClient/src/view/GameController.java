package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import resource.StaticImageResource;
import view.GameFrame;
import view.GamePanel;

public class GameController
{
  private static GameFrame thisGameFrame;

  synchronized public static GameFrame getGameFrame()
  {
    return thisGameFrame;
  }

  public GameController()
  {
    // TODO Auto-generated constructor stub
    thisGameFrame = new GameFrame("Fire Plane");
    JPanel initialJPanel = createInitialPanel();
    thisGameFrame.add(initialJPanel);
    thisGameFrame.setJpanel(initialJPanel);
    thisGameFrame.revalidate();
    // this is extremely important!!!!!!!!
  }

  public static void moveToGamePanel()
  {
    thisGameFrame.remove(thisGameFrame.getCurrentPanel());
    GamePanel nextJPanel = new GamePanel(StaticImageResource.backgroudImages[0]);
    thisGameFrame.add(nextJPanel);
    thisGameFrame.revalidate();
    thisGameFrame.addKeyListener(nextJPanel);
    thisGameFrame.setJpanel(nextJPanel);
  }


  public static JPanel createInitialPanel()
  {
    JPanel currentPanel = new JPanel();
    currentPanel.setBackground(Color.BLACK);
    currentPanel.setLayout(new BorderLayout());
    JButton startButton = new JButton("Start Game!");
    JButton exitButton = new JButton("I am sure I want to quit...");
    ImageIcon logo = new ImageIcon("imgs/other/LOGO.png");
    JLabel logoContainer = new JLabel(logo);

    startButton.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        // TODO Auto-generated method stub
        moveToGamePanel();
      }
    });

    exitButton.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        // TODO Auto-generated method stub
        thisGameFrame.dispose();
      }
    });


    currentPanel.add(startButton, BorderLayout.NORTH);
    currentPanel.add(exitButton, BorderLayout.SOUTH);
    currentPanel.add(logoContainer, BorderLayout.WEST);
    return currentPanel;
  }

}
