package entity;

import java.awt.Rectangle;

public class Rock {
	protected int x;
	protected int y;
	protected boolean isAlive;
	protected static int IMAGE_W = 48;
	protected static int IMAGE_H = 48;
	
	public Rock(int x, int y) {
		this.x = x;
		this.y = y;
		this.isAlive = true;
	}
	public Rectangle getRectangle(){
		return new Rectangle(x, y, IMAGE_W, IMAGE_H);
	}
	public void changeX(int x) {
		this.x += x;
		if(this.x < 0){
			this.isAlive = false;
		}
	}
	
}
