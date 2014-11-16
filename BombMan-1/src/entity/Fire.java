package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ui.GamePanel;
import util.ImageIndexChanger;
import config.StaticImage;

public class Fire {
	protected int x;
	protected int y;
	protected int imageH;
	protected int imageW;
	protected boolean isAlive;
	protected int timer;
	protected int timesOfFire;
	protected Rectangle fireRectangel;
	protected ImageIndexChanger imgIdxChanger;
	protected BufferedImage fireImage;
	protected int numOfImage = 24;
	protected boolean isHighImage;
	protected int w;
	protected int h;
	
	public Fire(int x, int y){
		this.x = x;
		this.y = y;
		this.init();
	}
	
	protected void init(){
		this.isHighImage = true;
		this.timesOfFire = 2;
		this.numOfImage = 24;
		this.imageW = GamePanel.PER_RECT + 10;
		this.imageH = GamePanel.PER_RECT + 10;
		this.fireRectangel = new Rectangle(x,y,GamePanel.PER_RECT,GamePanel.PER_RECT);
		this.timer = 0;
		this.imgIdxChanger = new ImageIndexChanger(numOfImage);
		this.isAlive = true;
		this.fireImage = StaticImage.Fire[0];
		this.w = this.fireImage.getWidth();
		this.h = this.fireImage.getHeight()/numOfImage;
	}
	
	public Fire(int x, int y, int imageW, int imageH, int timesOfFire) {
		this(x, y);
		this.timesOfFire = timesOfFire;
		this.imageW = imageW;
		this.imageH = imageH;
	}
	
	public void drawFire(Graphics g){
		if(this.timesOfFire >=0 ) {
			this.timer++;
		}
		if(this.timesOfFire >=0 && this.timer>(numOfImage*this.timesOfFire)-1){
			this.isAlive = false;
			return;
		}
		
		g.drawImage(this.fireImage.getSubimage(0, h*this.imgIdxChanger.nextIndexOfImage(), w, h),
				x, y, this.imageW, this.imageH, null);
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	public Rectangle getRectangle(){
		return this.fireRectangel;
	}
	public void changeX(int x) {
		this.x += x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
