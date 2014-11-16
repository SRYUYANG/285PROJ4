package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import ui.GamePanel;
import util.ImageIndexChanger;
import config.StaticImage;

public class AirEnemyFireBullet extends AirEnemy {

	private ImageIndexChanger imgIdxChanger;
//	private Fire fire;
	private static final int NUM_OF_IMAGE = 47;
	private int w;
	private int h;
	private int timer;
	private boolean isAttacking;
	public AirEnemyFireBullet(int x, int y, GamePanel gp) {
		super(x, y, gp);
		this.isBullet = true;
	}

	@Override
	protected void init() {
		this.imgIdxChanger = new ImageIndexChanger(NUM_OF_IMAGE);
		this.imgIdxChanger.setLimit(0, NUM_OF_IMAGE-1);
		this.imgIdxChanger.setHowSlowToChange(2);
		this.imageOfEnemy = StaticImage.Enemys.get(10);
		this.imageW = (GamePanel.PANEL_W >> 1) + (GamePanel.PER_RECT*3)+256;
		this.imageH = this.imageW / 5;
		this.w = this.imageOfEnemy.getWidth();
		this.h = this.imageOfEnemy.getHeight() / NUM_OF_IMAGE;
	}

	@Override
	public void drawImageOfEnemy(Graphics g) {
		if(this.isAlive){
			this.timer = this.imgIdxChanger.nextIndexOfImage();
			if(timer == NUM_OF_IMAGE-1) {
				this.imgIdxChanger.setLimit(0, 0);
//				this.fire = new Fire(x-(GamePanel.PER_RECT<<1)+16,y,
//						GamePanel.PER_RECT<<1, GamePanel.PER_RECT*3,){
//					@Override
//					public void changeX(int x) {
//					}
//		};
//				this.gp.getFirs().add(this.fire);
			}
			int tempH = 0;
			int tempY = 0;
			if(this.timer == 0){
				tempH = 80;
				tempY = 32;
			}
			g.drawImage(imageOfEnemy.getSubimage(0, this.timer*h,w,h),
					this.x - this.imageW - 4, this.y - tempY, this.imageW, this.imageH+tempH, null);
		}
	}

	@Override
	public void move() {
		this.isHitBulletOrbombMan();
//		if(this.fire!=null){
//			this.fire.setY(y-32);
//		}
	}

	@Override
	protected Rectangle getRectangle() {
		if(this.timer < NUM_OF_IMAGE>>2) {
			return new Rectangle(-1,-1, 0, 0);
		}
		if(this.timer < (NUM_OF_IMAGE>>2)*3) {
			return new Rectangle(this.x - this.imageW, this.y+16,
					this.imageW, this.imageH-16);
		}
		else {
			return new Rectangle(this.x - this.imageW/10, this.y+16,
					this.imageW/10, this.imageH-16);
		}
	}

	@Override
	protected void willDeath() {
	}

	@Override
	protected void attack() {
		if(!this.isAttacking) {
			this.imgIdxChanger.setLimit(13, 19);
			this.imgIdxChanger.setHowSlowToChange(1);
			this.isAttacking = true;
		}else {
			this.imgIdxChanger.reSetLimit();
			this.imgIdxChanger.setHowSlowToChange(3);
			this.isAttacking = false;
		}
	}

	public void setY(int y){
		this.y = y;
	}
}
