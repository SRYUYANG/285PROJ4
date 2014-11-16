package ui;

import java.awt.Graphics;
import java.awt.Image;

import config.StaticImage;

public class BackGround {

	private int widthOfShow;
	private int hightOfShow;
	
	private Image bgImage = null;
	
	private static int x;
	private int y;
	private int stage;
	private int speedX;
	private int speedY;
	private boolean isStopRolling;
	private boolean iswillStop;
	private boolean isRollBackground;
	
	public BackGround(int stage, int speedX, int speedY){
		this.stage = stage;
		this.speedX = speedX;
		this.speedY = speedY;
		this.hightOfShow = GamePanel.PANEL_H;
		this.widthOfShow = GamePanel.PANEL_W;
		
		if(speedX == 0 && speedY == 0) {
			this.isRollBackground = false;
			this.bgImage = StaticImage.bg.get(stage-1);
		}else {
			this.isRollBackground = true;
			this.bgImage = StaticImage.bg_roll.get(stage-1);
		}
	}
	
	public void drawBackGround(Graphics g, GamePanel gf){
		if(!isRollBackground){
			g.drawImage(bgImage, 0, 0, GamePanel.PANEL_W, GamePanel.PANEL_H, null);
			return;
		}
		
		if(this.speedX != 0){
			if(x>=this.bgImage.getWidth(null)){
				x = 0;
			}
			int tempY = 0;
			if(this.stage == 1){ tempY = 100; }
			g.drawImage(bgImage, 0, 0, GamePanel.PANEL_W, GamePanel.PANEL_H,
					x, tempY, x+this.widthOfShow, bgImage.getHeight(null), gf);
			//xÓëÍ¼Æ¬Ä©¶ËµÄ¾àÀë
			int distance = this.bgImage.getWidth(null) - x;
			if(distance <= widthOfShow){
				g.drawImage(bgImage, distance, 0, GamePanel.PANEL_W, GamePanel.PANEL_H,
						0, tempY, this.widthOfShow-distance, bgImage.getHeight(null), gf);
			}
		}
		
		if(this.speedY != 0){
			if(y>=this.bgImage.getHeight(null)){
				y = 0;
			}
			g.drawImage(bgImage, 0, 0, GamePanel.PANEL_W, GamePanel.PANEL_H,
					0, y, bgImage.getWidth(null), y+hightOfShow, gf);
			//yÓëÍ¼Æ¬µ×¶ËµÄ¾àÀë
			int distance = this.bgImage.getHeight(null) - y;
			if(distance <= hightOfShow){
				g.drawImage(bgImage, 0, distance, GamePanel.PANEL_W, GamePanel.PANEL_H,
						0, 0, bgImage.getWidth(null), hightOfShow-distance, gf);
			}
		}
	}

	public void move() {
		if(this.isStopRolling){
			return;
		}
		
		if(this.iswillStop){
			if(x+2 > this.bgImage.getWidth(null)-this.widthOfShow){
				this.isStopRolling = true;
			}
		}
		
		x += speedX;
		this.y += speedY;
	}
	
	public int getX(){
		return x;
	}

	public void setX(int a) {
		x = a;
	}

	public void setWidthOfShow(int widthOfShow) {
		this.widthOfShow = widthOfShow;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setStopRolling(boolean isStopRolling) {
		this.isStopRolling = isStopRolling;
		this.iswillStop = true;
	}
	
}
