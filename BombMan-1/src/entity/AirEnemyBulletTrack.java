package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import config.StaticImage;
import ui.GamePanel;
import util.ImageIndexChanger;

public class AirEnemyBulletTrack extends AirEnemy {

	protected ImageIndexChanger IndexChanger;
	protected int numOfImage;
	protected int nowIdOfImage;
	
	public AirEnemyBulletTrack(int x, int y, GamePanel gp) {
		super(x, y, gp);
	}

	@Override
	protected void init() {
		this.isBullet = true;
		this.speedY = 2;
		this.speedX = -1;
		this.life = 4;
		this.size = GamePanel.PER_RECT>>1;
		this.imageW = GamePanel.PER_RECT;
		this.imageH = GamePanel.PER_RECT>>1;
		this.imageOfEnemy = StaticImage.Enemys.get(9);
		this.imageOfEnemy2 = StaticImage.Enemys.get(9);
		this.numOfImage = 11;
		this.IndexChanger = new ImageIndexChanger(this.numOfImage);
	}

	
	
	@Override
	public void drawImageOfEnemy(Graphics g) {

		if(!this.isDraw){
			return;
		}
		
		if(this.isAlive) {
			this.isHitBulletOrbombMan();
		}
		
		int w = this.imageOfEnemy.getWidth()/this.numOfImage;
		int h = this.imageOfEnemy.getHeight();
		this.nowIdOfImage = this.IndexChanger.nextIndexOfImage();
//		g.drawImage(imageOfEnemy.getSubimage(w*this.IndexChanger.nextIndexOfImage(),0,
//				w, h), x, y, imageW, imageH, this.gp);
		
		if(isDrawAnotherImage && this.imageOfEnemy2!=null){
			g.drawImage(imageOfEnemy.getSubimage(w*this.nowIdOfImage,0,
					w, h), x+4, y+this.shockY, imageW, imageH, this.gp);
			this.timer--;
			this.shockY = -this.shockY;
			if(this.timer<=0){
			this.isDrawAnotherImage = false;
			this.timer = SHINING_TIME;
			}
		}else{
			g.drawImage(imageOfEnemy.getSubimage(w*this.nowIdOfImage,0,
					w, h), x, y, imageW, imageH, this.gp);
		}
	}

	@Override
	public void move() {
		this.x += speedX;
		
		if(this.x >= -GamePanel.PER_RECT<<1 &&
				this.x < GamePanel.PANEL_W+(this.size<<1) ){
			this.isDraw = true;
		}else{
			this.isDraw = false;
			if(this.x < -GamePanel.PER_RECT<<2){
				this.isAlive = false;
			}
		}
		
		if(this.gp.getBombMan().getY() > this.y) {
			this.y += this.speedY;
		}else {
			this.y -= this.speedY;
		}
	}

	@Override
	protected Rectangle getRectangle() {
		return super.getRectangle();
	}

	@Override
	protected void willDeath() {
		this.life--;
		this.isDrawAnotherImage = true;
		if(this.life<0) {
			this.isAlive = false;
			this.gp.getFirs().add(new Fire(this.x, this.y));
		}
	}

	@Override
	protected void attack() {
	}


}
