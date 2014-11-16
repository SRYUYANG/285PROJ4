package entity;

import java.util.Random;

import ui.GamePanel;
import config.StaticImage;

public class AirEnemy_BombMan0 extends AirEnemy {


	public AirEnemy_BombMan0(int x, int y, GamePanel gp) {
		super(x, y, gp);
	}

	@Override
	public void willDeath() {
		this.isAlive = false;
		this.gp.getDto().addPoint(10);
		this.gp.getFirs().add(new Fire(x, y));
	}

	@Override
	protected void attack() {}

	@Override
	protected void init() {
		this.life = 1;
		this.speedX = 6;
		this.speedY = 1;
		this.size = GamePanel.PER_RECT;
		this.imageH = GamePanel.PER_RECT;
		this.imageW = GamePanel.PER_RECT;
		this.imageOfEnemy = StaticImage.Enemys.get(new Random().nextInt(2)+2);
	}

}
