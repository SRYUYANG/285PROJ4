package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import ui.GamePanel;
import config.StaticImage;

public class Reward {
	private int x;
	private int y;
	public static int SUM_OF_REWARDTYPES = 3;
	private Rectangle rewardRect;
	private int type;
	private boolean isAlive;
	private Image rewardIamge[] = new Image[SUM_OF_REWARDTYPES];
	
	public Reward(int x, int y){
		this.x = x;
		this.y = y;
		this.rewardRect = new Rectangle(this.x, this.y, GamePanel.PER_RECT, GamePanel.PER_RECT);
		this.type = new Random().nextInt(SUM_OF_REWARDTYPES);
		this.isAlive = true;
		for(int i=0; i<SUM_OF_REWARDTYPES; i++){
			this.rewardIamge[i] = StaticImage.Reward.getSubimage(i*16, 0, 16, 16);
		}
	}
	
	public void drawReward(Graphics g){
		g.drawImage(this.rewardIamge[type], x, y, GamePanel.PER_RECT, GamePanel.PER_RECT, null);
	}
	
	public void effect(BombMan bombMan){
		if(!bombMan.getRectangle().intersects(this.rewardRect)){ return; }
		switch(this.type){
		case 0: bombMan.setNumberOfBombCanSet(bombMan.getNumberOfBombCanSet()+1);
		break;
		case 1: if(bombMan.getSpeed()<=8){ bombMan.setSpeed(bombMan.getSpeed()+2); } 
		break;
		case 2: bombMan.setLenOfFireCanSet(bombMan.getLenOfFireCanSet()+1);
		}
		this.isAlive = false;
	}

	public boolean isAlive() {
		return isAlive;
	}
	
	public void changeX(int x){
		this.x += x;
	}
}
