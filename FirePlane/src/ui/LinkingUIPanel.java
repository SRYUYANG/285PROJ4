package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import resource.StaticImageResource;

public class LinkingUIPanel extends JFrame
{
  private static JTextField ipAddress;
  private static JButton link, exitProgram;
  private static GamePanel nextJPanel;
  public LinkingUIPanel()
  {
    this.setLayout(new BorderLayout());
    ipAddress = new JTextField();
    ipAddress.setPreferredSize(new Dimension(200, 20));
    JPanel ipAddressJPanel = new JPanel();
    ipAddressJPanel.add(ipAddress);
    ipAddressJPanel.setBorder(BorderFactory.createEmptyBorder(200, 20, 200, 20));
    add(ipAddressJPanel, BorderLayout.CENTER);
    JPanel buttonJPanel = new JPanel();
    buttonJPanel.setLayout(new FlowLayout());
    link = new JButton("Link");
    link.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent e)
      {
        // TODO Auto-generated method stub
        
        LinkingUIPanel.this.setVisible(false);
        
      }
    });
    exitProgram = new JButton("Exit");
    exitProgram.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        // TODO Auto-generated method stub
        LinkingUIPanel.this.dispose();
      }
    });
    buttonJPanel.add(link);
    buttonJPanel.add(exitProgram);
    this.add(buttonJPanel, BorderLayout.SOUTH);
  }
}
