package util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ui.GamePanel;

public class StringShower {
	private int totalTime;
	private int useTime;
	private int maxY;
	private int speedOfTimerChange;
	private static final int FIRST_Y_OF_TITLE = -90;
	private boolean isAlive;
	private ImageSizeChanger imageSizeChanger;
//	private ImageSizeChanger imageSizeChanger2;
	private BufferedImage bufferedImage;
	
	public StringShower(int totalTime, int maxY, BufferedImage bufferedImage) {
		this.isAlive = true;
		this.totalTime = totalTime;
		this.maxY = maxY;
		this.bufferedImage = bufferedImage;
		this.speedOfTimerChange = 1;
		this.imageSizeChanger = new ImageSizeChanger(GamePanel.PANEL_W>>1,
				FIRST_Y_OF_TITLE, 0, bufferedImage.getWidth(), totalTime>>1);
//		this.imageSizeChanger2 = new ImageSizeChanger(GamePanel.PANEL_W>>1,
//				FIRST_Y_OF_TITLE, bufferedImage.getWidth(), bufferedImage.getWidth()<<1, totalTime<<2);
	}
	
	public void drawString(Graphics g) {
		if(!this.isAlive){ return; }
		
		int tempY = Function.getYfromFun1(useTime, maxY, this.totalTime);
		if(tempY >= FIRST_Y_OF_TITLE){
//		g.drawImage(bufferedImage, (GamePanel.PANEL_W-bufferedImage.getWidth())>>1,
//				FIRST_Y_OF_TITLE + tempY, null);
		this.imageSizeChanger.setY(FIRST_Y_OF_TITLE+tempY);
//		this.imageSizeChanger2.setX(FIRST_Y_OF_TITLE+tempY);
//		this.imageSizeChanger2.setY(FIRST_Y_OF_TITLE+tempY);
//		if(this.speedOfTimerChange>=0){
			this.imageSizeChanger.drawChangingImage(bufferedImage, 0, 0, g);
//			this.imageSizeChanger2.setX(this.imageSizeChanger.getX());
//			this.imageSizeChanger2.setY(this.imageSizeChanger.getY());
//		}else{
//			this.imageSizeChanger2.drawChangingImage(bufferedImage, 0, 0, g);
//		}
		}
		this.timerChange();
	}
	
	private void timerChange() {
		
		this.useTime += this.speedOfTimerChange;
		
		if(this.useTime >= (this.totalTime >> 2) * 3){
			this.speedOfTimerChange = 0;
		}
		
		if(this.useTime < 0){
			this.isAlive = false;
		}
	}

	public void setSpeedOfTimerChange(int speedOfTimerChange) {
		this.speedOfTimerChange = speedOfTimerChange;
	}
	
	
}
