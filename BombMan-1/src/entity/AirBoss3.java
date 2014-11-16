package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import config.StaticImage;
import ui.GamePanel;
import util.ImageIndexChanger;

public class AirBoss3 extends AirBoss {

	private static final int LIFE = 80;
	private static final int NUM_OF_IMAGE = 48;
	private int idOfNowImage;
	private ImageIndexChanger imgIdxChanger;
	private boolean isFire;
//	private AirEnemyXBullet xBullet;
	
	public AirBoss3(GamePanel gp) {
		super(gp);
	}
	
	@Override
	protected void init() {
		this.speedX = -2;
		this.speedY = 0;
		this.setFirstXY(GamePanel.PANEL_W, GamePanel.PANEL_H/7);
		this.sleepTime = 6000;
		this.size = GamePanel.PER_RECT;
		this.imageH = GamePanel.PER_RECT *9;
		this.imageW = GamePanel.PER_RECT *9;
		this.life = LIFE;
		this.isDraw = true;
		this.imageOfEnemy = StaticImage.Enemys.get(13);
		this.imgIdxChanger = new ImageIndexChanger(NUM_OF_IMAGE);
		this.imgIdxChanger.setHowSlowToChange(2);
		this.imgIdxChanger.setInverted(true);
		this.imageOfEnemy2 = StaticImage.Enemys.get(15);
//		this.initXBullet();
	}

	@Override
	public void drawImageOfEnemy(Graphics g) {

		if(this.isAlive) {
			this.isHitBulletOrbombMan();
		int w = this.imageOfEnemy.getWidth()/NUM_OF_IMAGE;
		int h = this.imageOfEnemy.getHeight();
		this.idOfNowImage = this.imgIdxChanger.nextIndexOfImage();
		
		if(this.idOfNowImage > 18 && this.idOfNowImage < 35 && !this.isFire){
			this.gp.getAirEnemys().add(new AirEnemyFireBomb(x+64, y+80, gp){
				@Override
				protected void init() {
					super.init();
					this.imageH*=3;
					this.imageW*=3;
					this.speedX = -10;
				}

				@Override
				public void becomeFire() {
					this.gp.getFirs().add(new Fire(this.x, this.y-this.imageH,
							this.imageW<<1, this.imageH<<1, 1));
					this.speedY = -40;
					if(this.gp.getBombMan().getX()<this.x){
						this.speedX = -8;
					}else{
						this.speedX = 8;
					}
//					this.isBullet = true;
				}

				@Override
				protected Rectangle getRectangle() {
					return new Rectangle(this.x, this.y, this.imageW, this.imageH);
				}
				
			});
			this.isFire = true;
		}
		
		if(isDrawAnotherImage && this.imageOfEnemy2!=null){
			g.drawImage(this.imageOfEnemy2.getSubimage(w*this.idOfNowImage,0,w,h),
					x+4, y+this.shockY, this.imageW, this.imageH, null);
			this.timer--;
			this.shockY = -this.shockY;
			if(this.timer<=0){
			this.isDrawAnotherImage = false;
			this.timer = SHINING_TIME;
			}
		}else{
			g.drawImage(this.imageOfEnemy.getSubimage(w*this.idOfNowImage,0,w,h),
					x, y, this.imageW, this.imageH, null);
			}
		}
	}

	@Override
	public void move() {
//		System.out.println(this.xBullet.x);
//		if(this.xBullet != null && this.xBullet.x<-GamePanel.PER_RECT<<1) {
//			if(this.idOfNowImage>=24 && this.idOfNowImage<=30) {
//				this.gp.getFirs().add(new Fire(
//						x+(GamePanel.PER_RECT*3),
//						y+GamePanel.PER_RECT*3-30,
//						GamePanel.PER_RECT<<1,
//						GamePanel.PER_RECT<<1,
//						1));
//				this.xBullet.isAlive = true;
//				this.xBullet.isDraw = true;
//				
//				this.xBullet.x = this.x+GamePanel.PER_RECT;
//				this.xBullet.speedX = -10;
//				this.xBullet.life = 100;
//			}
//		}
		
		this.x += this.speedX;
		
		int temp = this.random.nextInt(3);
		
		if(this.x <= GamePanel.PANEL_W/5 - temp*GamePanel.PER_RECT &&
				this.speedX<0) {
			this.speedX = 4;
		}else if(this.x > GamePanel.PANEL_W-(GamePanel.PANEL_W>>2) + temp*GamePanel.PER_RECT &&
				this.speedX>0) {
			this.speedX = -2;
		}
	}

	@Override
	protected Rectangle getRectangle() {
		int tempX = this.imageW>>2;
		int tempY = this.imageH/3;
		return new Rectangle(x+tempX, y+this.imageH-tempY,
				tempX, tempY);
	}

	@Override
	protected void attack() {
		int ranNumber = this.random.nextInt(15);
		int temp = ranNumber%5;
		int leftDY = 10;
		int leftDX = 26;
		int rightDY = -20;
		int rightDX = 6;
		
		this.isFire = !this.isFire;
		
		if(temp==0){
			this.gp.getAirEnemys().add(new AirEnemyFireBomb(
					x+(GamePanel.PER_RECT*3)+leftDX,
					y+leftDY,
					this.gp));
			this.gp.getAirEnemys().add(new AirEnemyFireBomb(
					x+(GamePanel.PER_RECT*6)+rightDX,
					y+rightDY,
					this.gp));
		}else if(temp<=2){
			this.gp.getAirEnemys().add(new AirEnemyFireBomb(
					x+(GamePanel.PER_RECT*3)+leftDX,
					y+leftDY,
					this.gp));
		}else {
			this.gp.getAirEnemys().add(new AirEnemyFireBomb(
					x+(GamePanel.PER_RECT*6)+rightDX,
					y+rightDY,
					this.gp));
		}
		this.speedX += -temp+1;
	}

	@Override
	protected void willDeath() {
		super.willDeath();
		if(this.life%10==0){
			this.gp.getFirs().add(new Fire(x+(this.imageW>>2),y+(this.imageH>>1),
				this.imageW>>2, this.imageH>>2, 2));
		}
	}
	
//	private void initXBullet() {
//		this.gp.getFirs().add(new Fire(
//				x+(GamePanel.PER_RECT*3),
//				y+GamePanel.PER_RECT*3-30,
//				GamePanel.PER_RECT<<1,
//				GamePanel.PER_RECT<<1,
//				1));
//		
//		this.xBullet = new AirEnemyXBullet(x+GamePanel.PER_RECT,
//				y+GamePanel.PER_RECT*3-10,
//				this.gp){
//
//			@Override
//			protected void init() {
//				super.init();
//				this.isBullet = false;
//				this.speedX = -10;
//				this.imageH += this.imageH;
//				this.imageW += this.imageW;
//			}
//
//			@Override
//			public void resetXY() {
//				return;
//			}
//			
//		};
//		this.gp.getAirEnemys().add(this.xBullet);
//	}
}
