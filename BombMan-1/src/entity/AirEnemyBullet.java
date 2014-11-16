package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import ui.GamePanel;
import util.ImageIndexChanger;
import util.ImageSizeChanger;
import config.StaticImage;

public class AirEnemyBullet extends AirEnemy {

	private int changeSizeTime;
	private ImageSizeChanger changer;
	private ImageIndexChanger IndexChanger;
	
	public AirEnemyBullet(int x, int y, GamePanel gp, int changeSizeTime) {
		super(x, y, gp);
		this.isBullet = true;
		this.changeSizeTime = changeSizeTime;
		this.IndexChanger = new ImageIndexChanger(4);
		this.changer = new ImageSizeChanger( x, y,0, GamePanel.PER_RECT<<2, changeSizeTime);
	}

	@Override
	protected void init() {
		this.speedY = 0;
		this.speedX = 0;
		this.size = this.changeSizeTime;
		this.life = 1;
		this.imageOfEnemy = StaticImage.Enemys.get(5);
		this.timer = 0;
	}

	@Override
	public void drawImageOfEnemy(Graphics g) {

		if(this.isAlive) {
			this.isHitBulletOrbombMan();
		int h = this.imageOfEnemy.getWidth();
		this.changer.drawChangingImage(this.imageOfEnemy.getSubimage(0, h*this.IndexChanger.nextIndexOfImage(),
				                       h, h),
				this.speedX, this.speedY, g);
		if(this.changer.getUseOfTotalTime() >= 1){
			this.changer.setX(this.x);
			this.changer.setY(this.y);
			this.speedY = 4;
		}
		else if(this.changer.getUseOfTotalTime()>=0.6){
			this.speedX = 6;
			this.x = this.changer.getX();
			this.y = this.changer.getY();
		}
		else{
			this.x = this.changer.getX();
			this.y = this.changer.getY();
		}
		
		}
	}

	@Override
	protected Rectangle getRectangle() {
		return new Rectangle(x+GamePanel.PER_RECT, y+GamePanel.PER_RECT,
				GamePanel.PER_RECT<<2, GamePanel.PER_RECT<<1);
	}

	@Override
	protected void judgeY() {
	}
	
	@Override
	protected void willDeath() {
		
	}

	@Override
	protected void attack() {
	}

}
