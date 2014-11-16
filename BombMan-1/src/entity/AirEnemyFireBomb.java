package entity;

import java.awt.Rectangle;

import config.StaticImage;
import ui.GamePanel;
import util.Function;
import util.ImageIndexChanger;

public class AirEnemyFireBomb extends AirEnemyBulletTrack {

	private int timerToUp = 10;
	
	public AirEnemyFireBomb(int x, int y, GamePanel gp) {
		super(x, y, gp);
//		this.isBullet = false;
	}

	@Override
	protected void init() {
		this.isBullet = true;
		this.isDraw = true;
		this.gp.getFirs().add(
				new Fire(x, y, GamePanel.PER_RECT, GamePanel.PER_RECT, 1));
		this.speedX = -2;
//		this.speedY = 10;
		this.imageH = GamePanel.PER_RECT>>1;
		this.imageW = GamePanel.PER_RECT>>1;
		this.numOfImage = 1;
		this.IndexChanger = new ImageIndexChanger(this.numOfImage);
		this.imageOfEnemy = StaticImage.Enemys.get(14);
	}

	@Override
	protected void willDeath() {
	}

	public void becomeFire() {
//		this.isBullet = true;
		this.speedY = 0;
		this.speedX = -8;
		this.imageW = GamePanel.PER_RECT<<1;
		this.imageH = GamePanel.PER_RECT<<3;
		this.y -= this.imageH - GamePanel.PER_RECT;
		this.numOfImage = 30;
		this.IndexChanger.setNumOfImage(this.numOfImage);
		this.IndexChanger.setHowSlowToChange(1);
		this.imageOfEnemy = StaticImage.Fire[4];
	}
	
	@Override
	protected void attack() {
	}

	@Override
	public void move() {
		this.x += this.speedX;
		this.y += this.speedY;
		if(this.timerToUp!=-1){
			int tempY = Function.getYfromFun1(this.timerToUp++, this.firstY+64, 32);
			this.y = tempY + (this.firstY-tempY<<1);
		}
		if(this.y > GamePanel.PANEL_H-GamePanel.ORINGINAL_Y-32) {
			this.timerToUp = -1;
			this.becomeFire();
		}
		
		if(this.x < -this.imageW || this.y < -this.imageH){
			this.isBullet = true;
			this.isAlive = false;
		}
	}

	@Override
	protected Rectangle getRectangle() {
		if(this.nowIdOfImage>7 && this.nowIdOfImage<18) {
			return new Rectangle(x, y, this.imageW, this.imageH);
		}else {
			return new Rectangle(0,0,0,0);
		}
	}
}
