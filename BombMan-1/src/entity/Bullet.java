package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import ui.GamePanel;
import config.StaticImage;
import dto.GameDto;

public class Bullet implements Runnable{

	private int x;
	private int y;
	private int size;
	private int type;
	private int speed;
	private boolean isLive;
	private GameDto dto;
	
	public Bullet(int x, int y, int type, int speed, int size, GameDto dto){
		this.x = x;
		this.y = y;
		this.size = size;
		this.type = type;
		this.speed = speed;
		this.isLive = true;
		this.dto = dto;
		new Thread(this).start();
	}
	
	public void drawBullet(Graphics g){
		g.drawImage(StaticImage.Bullet[type], x, y-(size>>1), null);
	}
	
	@Override
	public void run() {
		while(this.isLive){
			
			if(this.dto.isPause()) { continue; }
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.x += this.speed;
			if(this.x>GamePanel.PANEL_W){
				this.isLive = false;
			}
		}
	}
	
	public void changeX(int x){
		this.x += x;
	}
	
	public Rectangle getRetangle(){
		return new Rectangle(x, y-(size>>1), size, size);
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	
}
