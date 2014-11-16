package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import ui.GamePanel;
import config.StaticImage;

public class BombManPlane extends BombMan {
	
	private BufferedImage imageOfBombManPlane;
	
	public BombManPlane(int type){
		this.x = GamePanel.PER_RECT+32;
		this.y = -GamePanel.PER_RECT<<2;
		this.fallFromY = this.y;
		this.canShoot = true;
		if(type==0){
			this.setSpeed(8);
		}else{
			this.setSpeed(6);
		}
		this.life = 10;
		this.canMove = true;
		this.imageOfBombManPlane = StaticImage.BombManPlane[type];
	}
	
	@Override
	public void drawBombMan(Graphics g) {
		if(this.isShock ){
			if(this.shockTimer%4!=0){
				g.drawImage(this.imageOfBombManPlane,
					x, y+this.shockY,
					this.imageOfBombManPlane.getWidth(),
					this.imageOfBombManPlane.getHeight(), null);
			}
			this.shockY = -this.shockY;
			this.shockTimer--;
			if(this.shockTimer<=0){
				this.shockTimer = SHOCK_TIME;
				this.isShock = false;
			}
		}
		else{
			g.drawImage(this.imageOfBombManPlane,
					x, y, 
					this.imageOfBombManPlane.getWidth(),
					this.imageOfBombManPlane.getHeight(), null);
		}
		
		this.fall(this.fallFromY, 25,(-this.fallFromY)+(GamePanel.PANEL_H>>1), 36, 1);
		
	}

	@Override
	public void moveUp() {
		this.speedU = -this.speed;
	}

	@Override
	public void moveDown() {
		this.speedD = this.speed;
	}

	@Override
	public void moveLeft() {
		this.speedL = -this.speed;
	}

	@Override
	public void moveRight() {
		this.speedR = this.speed;
	}

	@Override
	public void afterDeath() {
		this.life--;
		this.gp.getDto().addBombManLife(-1);
		if(this.gp.getDto().getBombManLife()>0){
			this.isShock = true;
			this.isHitting = true;
		}else{
			this.isLive = false;
			this.gp.getDto().setGameOver(true);
		}
		this.gp.getFirs().add(new Fire_White(x+GamePanel.PER_RECT, y));
	}

	@Override
	protected void isHitFire(Vector<Fire> fires) {
		return;
	}
	
}
