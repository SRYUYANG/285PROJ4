package util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageSizeChanger {

	private int x;
	private int y;
	private int totalTime;
	private int useTime;
	private int speedOfChangeSize;
	private int changeOfW;
	
	public ImageSizeChanger(int x, int y,
			int firstW ,int finalW, int totalTime){

		this.x = x;
		this.y = y;
		this.totalTime = totalTime;
		this.useTime = 0;
		this.speedOfChangeSize = (finalW - firstW) / totalTime;
	}
	
//	public ImageSizeChanger(ImageIndexChanger idxChanger, int x, int y,
//			int firstW ,int finalW, int totalTime){
//
//		this.idxChanger = idxChanger;
//		this.x = x;
//		this.y = y;
//		this.totalTime = totalTime;
//		this.useTime = 0;
//		this.speedOfChangeSize = (finalW - firstW) / totalTime;
//	}
	
	public void drawChangingImage(BufferedImage bufferedImage, int speedX, int speedY, Graphics g){
		if(this.useTime < this.totalTime){
			this.useTime++;

			if(this.speedOfChangeSize>0){
				changeOfW = useTime * this.speedOfChangeSize;
			}else{
				changeOfW = -(this.speedOfChangeSize*this.totalTime)+useTime*this.speedOfChangeSize;
			}
			this.x -= (this.speedOfChangeSize >> 1) + speedX;
			this.y -= (this.speedOfChangeSize >> 1) + speedY;
		}
		
		g.drawImage(bufferedImage, x, y, x+changeOfW, y+changeOfW, 
				0, 0, bufferedImage.getWidth(), bufferedImage.getWidth(), null);
	}

	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public double getUseOfTotalTime(){
		return this.useTime*1.0/this.totalTime;
	}
}
