package entity;

import config.StaticImage;

public class Fire_Black extends Fire {

	public Fire_Black(int x, int y) {
		super(x, y);
		this.fireImage = StaticImage.Fire[0];
	}

//	@Override
//	protected void init() {
//		this.isHighImage = true;
//		this.timesOfFire = 1;
//		this.numOfImage = 16;
//		this.imageW = GamePanel.PER_RECT + 40;
//		this.imageH = GamePanel.PER_RECT + 10;
//		this.fireRectangel = new Rectangle(x,y,GamePanel.PER_RECT,GamePanel.PER_RECT);
//		this.timer = 0;
//		this.imgIdxChanger = new ImageIndexChanger(numOfImage);
//		this.imgIdxChanger.setHowSlowToChange(8);
//		this.isAlive = true;
//		this.fireImage = StaticImage.Fire[5];
//		this.w = this.fireImage.getWidth();
//		this.h = this.fireImage.getHeight()/numOfImage;
//	}

}
