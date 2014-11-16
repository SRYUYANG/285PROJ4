package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ui.GamePanel;
import util.Function;
import util.ImageIndexChanger;
import config.StaticImage;

public class AirBoss2 extends AirBoss {

	private BufferedImage imageOfRoof;
	private static final int LIFE = 80;
	private static final int NUM_OF_IMAGE_OF_ROOF = 10;
	private int timerOfFalling = 0;
	private ImageIndexChanger imgIdxChangerOfRoof;
	
	public AirBoss2(GamePanel gp) {
		super(gp);
	}

	@Override
	protected void init() {
		this.speedX = 0;
		this.speedY = 0;
		this.sleepTime = 6000;
		this.setFirstXY((GamePanel.PANEL_W>>1)-22, -GamePanel.PANEL_H>>1);
		this.size = GamePanel.PER_RECT;
		this.imageW = GamePanel.PANEL_W-(GamePanel.ORINGINAL_X<<1);
		this.imageH =GamePanel.PANEL_H;
		this.life = LIFE;
		this.imageOfEnemy = StaticImage.Enemys.get(8);
		this.imageOfEnemy2 = StaticImage.Enemys.get(8);
		this.airGun = new AirGun(gp);
		this.airGun.setFirstXY(this.x+46, this.y-130);
		this.imageOfRoof = StaticImage.Roof;
		this.imgIdxChangerOfRoof = new ImageIndexChanger(NUM_OF_IMAGE_OF_ROOF*3);
		this.airFire = new AirEnemyFireBullet(this.x+(GamePanel.PER_RECT>>1)*5, this.y-32, this.gp);
//		this.fire = new Fire(this.x, this.y,
//				GamePanel.PER_RECT<<1, GamePanel.PER_RECT<<1, 20){
//					@Override
//					public void changeX(int x) {
//					}
//		};
	}

	@Override
	protected void attack() {
		this.airFire.attack();
	}

	private void fall() {
		if(this.timerOfFalling<49) {
			this.y = Function.getYfromFun1(
					this.timerOfFalling,
					GamePanel.PANEL_H-GamePanel.PER_RECT+(GamePanel.PER_RECT<<2),
					60);
			this.y -= GamePanel.PER_RECT<<2;
			this.timerOfFalling++;
		}else {
			this.speedY = 1;
		}
	}
	
	@Override
	public void move() {
		super.move();
//		if(this.speedY == 8){
//			this.y += this.speedY;
//		}
//		if(this.y > GamePanel.PANEL_H>>1){
//			this.speedY = 1;
//		}
		this.fall();
		
		if(this.airGun!=null){
			this.airGun.setY(this.y - 140);
		}
		if(this.airFire!=null){
			this.airFire.setY(this.y-32);
		}
//		if(this.fire!=null){
//			this.fire.setY(this.y);
//		}
	}

	@Override
	public void drawImageOfEnemy(Graphics g) {
		if(!this.isDraw){
			return;
		}
		
		if(this.isAlive) {
			this.isHitBulletOrbombMan();
		}
		
		int roofW = this.imageOfRoof.getWidth()/NUM_OF_IMAGE_OF_ROOF;
		int roofH = this.imageOfRoof.getHeight();
		int imgIdxOfRoof;
		if(this.isAct) {
			int temp = this.imgIdxChangerOfRoof.nextIndexOfImage();
			imgIdxOfRoof = temp/3;
			if(temp >= NUM_OF_IMAGE_OF_ROOF*3 - 1) {
				this.isAct = false;
			}
		}else {
			imgIdxOfRoof = 0;
		}
		g.drawImage(this.imageOfRoof.getSubimage(
				roofW*imgIdxOfRoof,
				0,
				roofW, 
				roofH),
				x+(GamePanel.PER_RECT*3)-16, y-(GamePanel.PER_RECT*5)-8,
				(GamePanel.PER_RECT<<1)+8,
				GamePanel.PER_RECT<<1,
				null);
		
		int imgX = this.x - (this.imageW - this.size >> 1);
		int imgY = this.y - (this.imageH - this.size >> 1);
		
		if(isDrawAnotherImage && this.imageOfEnemy2!=null){
			g.drawImage(imageOfEnemy2, imgX+4, imgY+this.shockY, imageW, imageH, this.gp);
			this.timer--;
			this.shockY = -this.shockY;
			if(this.timer<=0){
			this.isDrawAnotherImage = false;
			this.timer = SHINING_TIME;
			}
		}else{
			g.drawImage(imageOfEnemy, imgX, imgY, imageW, imageH, this.gp);
		}
	}

	@Override
	protected void willDeath() {
		this.life--;
		this.isDrawAnotherImage = true;
		if(this.airGun!=null){
			this.airGun.isDrawAnotherImage = true;
		}
		if(this.life<=0) {
			if(this.airGun!=null) {
				this.airGun.isAlive = false;
			}
			if(this.airFire!=null) {
				this.airFire.isAlive = false;
			}
			this.isAlive = false;
			this.gp.getFirs().add(new Fire(x+GamePanel.PER_RECT, y-32,
					GamePanel.PER_RECT<<2, GamePanel.PER_RECT<<2, 3){
				@Override
				public void changeX(int x) {
				}
			});
			this.gp.getFirs().add(new Fire(x, y-(GamePanel.PER_RECT<<1),
					GamePanel.PER_RECT*3, GamePanel.PER_RECT*3, 2){
				@Override
				public void changeX(int x) {
				}
			});
			this.gp.getFirs().add(new Fire(x+(GamePanel.PER_RECT<<1), y-(GamePanel.PER_RECT<<1)-32,
					GamePanel.PER_RECT*3, GamePanel.PER_RECT*3, 4){
				@Override
				public void changeX(int x) {
				}
			});
		}
	}
	
	@Override
	protected void judgeY() {
		//不让Y坐标超出活动范围
		if(this.y < GamePanel.ORINGINAL_Y*3 && this.speedY<4){
			this.y = GamePanel.ORINGINAL_Y*3;
		}
		else if(this.y > GamePanel.PANEL_H - GamePanel.ORINGINAL_Y - this.size){
			this.y = GamePanel.PANEL_H - GamePanel.ORINGINAL_Y - this.size;
		}
	}

	@Override
	protected Rectangle getRectangle() {
		return new Rectangle(x+(GamePanel.PER_RECT<<1)-4, y, this.size<<1, this.size<<1);
	}
}
