package ui;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import control.GameControler;

public class GamePresentComponent extends JComponent implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	private float alpha = 0.0f;
	private Image image = new ImageIcon("img/string/1.png").getImage();
	private GameControler gc;
	private boolean isAlive;
	
	public GamePresentComponent(GameControler gc) {
		this.gc = gc;
		this.isAlive = true;
		this.setPreferredSize(new Dimension(GamePanel.PANEL_W, GamePanel.PANEL_H));
		new Thread(this).start();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2d.setComposite(comp);
		g2d.drawImage(image,
				GamePanel.PANEL_W - this.image.getWidth(null) >> 1,
				(GamePanel.PANEL_H - this.image.getHeight(null) >> 1) - 10,
				this);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		while(true) {
			if(!this.isAlive){
				this.gc.setNewStagePanel();
				break;
			}
			if(this.alpha == 0){
				for(int i=0; i<=50 && this.isAlive; i++) {
					this.alpha = (float)i / 50;
					Thread.sleep(60);
					this.repaint();
				}
			}else {
				for(int i=110; i>=0 && this.isAlive; i--) {
					if(i <= 50) {
						this.alpha = (float)i / 50;
					}else {
						this.alpha = 1;
					}
					Thread.sleep(50);
					this.repaint();
				}
				this.isAlive = false;
				this.gc.setNewStagePanel();
				break;
			}
		}//while
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.isAlive = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
}