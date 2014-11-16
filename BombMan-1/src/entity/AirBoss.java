package entity;


import ui.GamePanel;
import config.StaticImage;

public class AirBoss extends AirEnemy implements Runnable{

	protected AirGun airGun;
	protected AirEnemyFireBullet airFire;
	protected Fire fire;
	protected int sleepTime;
	protected boolean isAct;
	private static final int LIFE = 60;
	
	public AirBoss(GamePanel gp) {
		super(gp);
		this.isBoss = true;
		new Thread(this).start();
	}

	@Override
	protected void init() {
		this.speedX = 1;
		this.speedY = 1;
		this.setFirstXY(GamePanel.PANEL_W-10,  GamePanel.PANEL_H>>1);
		this.sleepTime = 6000;
		this.size = GamePanel.PER_RECT;
		this.imageH = GamePanel.PER_RECT * 5;
		this.imageW = GamePanel.PER_RECT * 3;
		this.life = LIFE;
		this.imageOfEnemy = StaticImage.Enemys.get(4).getSubimage(0, 0, 90, 128);
		this.imageOfEnemy2 = StaticImage.Enemys.get(4).getSubimage(90, 0, 90, 128);
	}

	@Override
	protected void willDeath() {
		this.life--;
		this.isDrawAnotherImage = true;
		if(this.life<=0){
			this.gp.getFirs().add(new Fire(x, y-(size>>1),
					GamePanel.PER_RECT*3, GamePanel.PER_RECT*3, 2));
			this.isAlive = false;
		}
	}

	@Override
	protected void attack() {
			this.gp.getAirEnemys().add(new AirEnemyBullet(
					x-(this.size+20), y+(this.size>>1), this.gp, GamePanel.PER_RECT<<1));
	}

	@Override
	public void move() {
		super.move();
		if(this.x < GamePanel.PANEL_W - this.imageW - (GamePanel.PER_RECT>>1)){
			this.speedX = 0;
		}
	}

	@Override
	public void run() {
		while(this.isAlive){
			
			if(this.gp.getDto().isPause()) { continue; }
			
			this.attack();
			
			try {
				Thread.sleep(this.sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	public AirGun getAirGun() {
		return airGun;
	}

	public AirEnemyFireBullet getAirFire() {
		return airFire;
	}
	
	protected void setFirstXY(int x, int y) {
		this.x = x;
		this.y = y;
		this.firstX = x;
		this.firstY = y;
	}
}
