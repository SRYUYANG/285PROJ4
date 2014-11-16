package entity;

import java.awt.Graphics;
import java.awt.Image;

import ui.GamePanel;
import config.StaticImage;

public class Enemy_Robot extends Enemy{

	private static final int THINNER = 6;
	private static final int SHORTER = 8;
	
	public Enemy_Robot(int x, int y, GamePanel gp) {
		super(x, y, gp);
		this.speed = 2;
		this.enemyImage = new Image[4];
		for(int i=0; i<4; i++) {
			this.enemyImage[i] = StaticImage.Enemys.get(0).getSubimage(0, i*160, 25, 160);
		}
		new Thread(this).start();
	}

	@Override
	public void drawEnemy(Graphics g) {
		int tempIdx = this.IdxOfImage>>1;
		g.drawImage(this.enemyImage[this.direct],
				this.x, this.y-10, this.x+GamePanel.PER_RECT, this.y+GamePanel.PER_RECT,
				0,32*tempIdx,25,32*tempIdx+32,null);
		this.IdxOfImage++;
		if(this.IdxOfImage == 8) {
			this.IdxOfImage = 0;
		}
	}

	
	
	@Override
	protected void setRectangle() {
		this.enemyRectangle.setBounds(this.x+THINNER,y+SHORTER, 
				GamePanel.PER_RECT-THINNER, GamePanel.PER_RECT-SHORTER);
	}

	@Override
	public void willDeath() {
		//TODO
//		new Thread(StaticSound.blastOfEnemyRobot).start();
		this.gp.getFirs().add(new Fire_Black(x, y));
		this.isAlive = false;
	}

}
