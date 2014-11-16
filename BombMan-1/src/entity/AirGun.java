package entity;

import java.awt.Graphics;

import config.StaticImage;
import ui.GamePanel;
import util.ImageIndexChanger;

public class AirGun extends AirBoss2{

	private static final int NUM_OF_IMAGE = 14;
	private ImageIndexChanger imgIdxChanger;
	private int w;
	private int h;
	
	public AirGun(GamePanel gp) {
		super(gp);
		this.sleepTime = 3000;
		this.isBoss = false;
	}

	@Override
	protected void init() {
		this.speedX = 0;
		this.speedY = 0;
		this.isBullet = true;
		this.size = GamePanel.PER_RECT;
		this.imageW = GamePanel.PER_RECT<<1;
		this.imageH =GamePanel.PER_RECT<<1;
		this.life = 60;
		this.imageOfEnemy = StaticImage.Enemys.get(7);
		this.imageOfEnemy2 = StaticImage.Enemys.get(7);
		this.w = this.imageOfEnemy.getWidth();
		this.h = this.imageOfEnemy.getHeight()/NUM_OF_IMAGE;
		this.imgIdxChanger = new ImageIndexChanger(NUM_OF_IMAGE);
	}
	
	@Override
	public void drawImageOfEnemy(Graphics g){
		
		if(!this.isDraw){
			return;
		}
		
		if(this.isAlive) {
			this.isHitBulletOrbombMan();
		}
		
//		int imgX = this.x - (this.imageW - this.size >> 1);
//		int imgY = this.y - (this.imageH - this.size >> 1);
		
		if(isDrawAnotherImage && this.imageOfEnemy2!=null){
			g.drawImage(imageOfEnemy2.getSubimage(0, h*this.imgIdxChanger.nextIndexOfImage(), w, h),
					x+4, y+this.shockY, imageW, imageH, this.gp);
			this.timer--;
			this.shockY = -this.shockY;
			if(this.timer<=0){
			this.isDrawAnotherImage = false;
			this.timer = SHINING_TIME;
			}
		}else{
			g.drawImage(imageOfEnemy.getSubimage(0, h*this.imgIdxChanger.nextIndexOfImage(),
					w, h), x, y, imageW, imageH, this.gp);
		}
	}
	
	@Override
	protected void judgeY() {
	}
	
	@Override
	protected void willDeath() {
		this.life--;
		if(this.life <= 0) {
			this.gp.getFirs().add(new Fire(x, y,
					GamePanel.PER_RECT<<1, GamePanel.PER_RECT<<1, 3){
						@Override
						public void changeX(int x) {
						}
			});
			this.isAlive = false;
		}
		this.isDrawAnotherImage = false;
	}

	@Override
	protected void attack() {
		this.gp.getFirs().add(new FireShoot(x-GamePanel.PER_RECT+10, y));
		this.gp.getAirEnemys().add(new AirEnemyBulletTrack(x-10, y+32, gp));
	}

	public void setY(int y){
		this.y = y;
	}
	
}
