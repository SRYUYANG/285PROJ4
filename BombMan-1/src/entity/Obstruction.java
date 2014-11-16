package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import config.StaticImage;

public class Obstruction extends Rock{
	
	private Image obImage = null;
	private Reward reward = null;
	private int isCreateAReward = 0;  //随机地赋值，如果此变量等于1，就将会在被炸掉之后生成一个reward
	private static final int MaxRandomNumber = 5;
	
	public Obstruction(int x, int y){
		super(x,y);
		this.isAlive = true;
		this.obImage = StaticImage.Obstruction;
		this.isCreateAReward = new Random().nextInt(MaxRandomNumber);
		
	}
	public void drawObstruction(Graphics g){
		g.drawImage(obImage, x, y, IMAGE_W, IMAGE_H, null);
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		if(!isAlive && this.isCreateAReward == 1){
			this.reward = new Reward(this.x, this.y);
		}
		this.isAlive = isAlive;
	}
	public Reward getReward() {
		return reward;
	}
	
}
