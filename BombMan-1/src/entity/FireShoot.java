package entity;

import java.awt.Graphics;

import ui.GamePanel;
import util.ImageIndexChanger;
import config.StaticImage;

public class FireShoot extends Fire {

	public FireShoot(int x, int y) {
		super(x, y);
		this.fireImage = StaticImage.Fire[3];
		this.imgIdxChanger = new ImageIndexChanger(6*2);
	}

	@Override
	public void drawFire(Graphics g) {
		if(this.timer>6){
			this.isAlive = false;
			return;
		}
		
		int w = this.fireImage.getWidth()/6;
		int h = this.fireImage.getHeight();
		g.drawImage(this.fireImage.getSubimage(w*(this.imgIdxChanger.nextIndexOfImage()>>1), 0, w, h),
				x, y, GamePanel.PER_RECT<<1, GamePanel.PER_RECT<<1, null);
		this.timer++;
	}

}
