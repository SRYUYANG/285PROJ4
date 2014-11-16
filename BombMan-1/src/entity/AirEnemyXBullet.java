package entity;

import ui.GamePanel;
import util.ImageIndexChanger;
import config.StaticImage;

public class AirEnemyXBullet extends AirEnemyBulletTrack {

	public AirEnemyXBullet(int x, int y, GamePanel gp) {
		super(x, y, gp);
	}

	@Override
	protected void init() {
		this.isBullet = false;
		this.speedY = 0;
		this.speedX = -3;
		this.life = 30;
		this.size = GamePanel.PER_RECT>>1;
		this.imageW = GamePanel.PER_RECT;
		this.imageH = GamePanel.PER_RECT>>1;
		this.imageOfEnemy = StaticImage.Enemys.get(11);
		this.imageOfEnemy2 = StaticImage.Enemys.get(11);
		this.numOfImage = 12;
		this.IndexChanger = new ImageIndexChanger(this.numOfImage);
		this.IndexChanger.setHowSlowToChange(2);
	}

	@Override
	public void move() {
		this.x += this.speedX;
		if(this.x >= -GamePanel.PER_RECT<<1 &&
				this.x < GamePanel.PANEL_W+(this.size<<1) ){
			this.isDraw = true;
		}else{
			this.isDraw = false;
			if(this.x < -GamePanel.PER_RECT<<2){
				this.isAlive = false;
			}
		}
	}

	@Override
	protected void willDeath() {
		this.life--;
		this.speedX--;
		this.gp.getDto().addPoint(10);
		if(this.life <=0 ){
			this.isAlive = false;
			this.gp.getFirs().add(new Fire(x,y));
		}
	}

	@Override
	public void resetXY() {
		super.resetXY();
		if(this.speedX<-10) {
			this.speedX = -1;
		}
	}

}
