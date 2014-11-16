package entity;

import config.StaticImage;

public class Fire_White extends Fire {

	public Fire_White(int x, int y) {
		super(x, y);
//		this.initImage(StaticImage.Fire_White);
		this.fireImage = StaticImage.Fire[0];
	}

}
