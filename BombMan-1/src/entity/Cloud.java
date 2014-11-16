package entity;

import java.awt.Graphics;
import java.util.Random;

import ui.GamePanel;
import config.StaticImage;

public class Cloud {
	private int x;
	private int y;
	private int speedX;
	private int speedY;
	private int firstX;
	private int firstY;
	private int h;
	private int w;
	private boolean isLive;
	private int type;
	private Random random;
	private static final int SUM_TYPE = 4;
	private static final int MAX_SPEED = 5;

	public Cloud(int x, int y){
		this.x = x;
		this.y = y;
		this.random = new Random();
		this.firstX = x;
		this.firstY = y;
		this.speedX = -(random.nextInt(MAX_SPEED)+1);
		this.type = random.nextInt(SUM_TYPE);
		this.h = random.nextInt(2)+2;
		this.w = random.nextInt(3)+4;
		this.isLive = true;
	}

	public void drawCloud(Graphics g){
		g.drawImage(StaticImage.Cloud[type], x, y, GamePanel.PER_RECT*w, GamePanel.PER_RECT*h, null);
	}

	public void move(){
		this.x += speedX;
		this.y += speedY;
		if(this.x < -GamePanel.PER_RECT<<3){
			this.isLive = false;
		}
	}

	public boolean isLive() {
		return isLive;
	}

	public int getX() {
		return x;
	}

	public void resetXY(boolean isXY) {
		this.type = new Random().nextInt(SUM_TYPE);
		this.h = new Random().nextInt(2)+2;
		this.w = new Random().nextInt(3)+4;
		this.isLive = true;
		if(isXY){
			this.x = firstX;
			this.y = firstY;
		}else{
			this.speedY = -(this.random.nextInt(MAX_SPEED) + 4);
			this.speedX = 0;
			this.x = firstY;
			this.y = firstX;
		}
	}
}
