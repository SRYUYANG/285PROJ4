package entity;

import java.awt.Graphics;
import java.awt.Image;

import config.StaticImage;

import ui.GamePanel;

public class Enemy_Ghost extends Enemy {

	public Enemy_Ghost(int x, int y, GamePanel gp) {
		super(x, y, gp);
		this.timer = 0;
		this.speed = 1;
		this.canMove = true; //可以穿墙，所以canmove标志一直为true
		this.enemyImage = new Image[6];
		for(int i=0; i<3; i++) {
			this.enemyImage[i] = StaticImage.Enemys.get(1).getSubimage(i*32, 0, 32, 32);
		}
		for(int i=3; i<6; i++) {
			this.enemyImage[i] = StaticImage.Enemys.get(1).getSubimage((i-3)*32, 32, 32, 32);
		}
		new Thread(this).start();
	}

	@Override
	public void willDeath() {
		// TODO Auto-generated method stub
		if(this.IdxOfImage==0){
			this.IdxOfImage += 3;
		}
		try {
			Thread.sleep(28000);
			this.IdxOfImage = 0;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void drawEnemy(Graphics g) {
		g.drawImage(this.enemyImage[this.timer/5 + this.IdxOfImage], this.x, this.y,
				GamePanel.PER_RECT, GamePanel.PER_RECT, null);
		this.timer++;
		if(this.timer == 10){
			this.timer = 0;
		}
	}

	@Override
	protected void changeDirect() {
		
		String possibleDirect = "";
		
		if(this.x>GamePanel.ORINGINAL_X && this.bombMan.getX() <= this.x) {
			possibleDirect += 3;
		}else {
			if(this.x < 12*GamePanel.PER_RECT+GamePanel.ORINGINAL_X){
				possibleDirect += 1;
			}
		}
		
		if(this.y>GamePanel.ORINGINAL_Y && this.bombMan.getY() <= this.y) {
			possibleDirect += 0;
		}else {
			if(this.y < 8*GamePanel.PER_RECT+GamePanel.ORINGINAL_Y){
				possibleDirect += 2;
			}
		}
		
		int len = possibleDirect.length();
		char ch = possibleDirect.charAt(random.nextInt(len));
		this.direct = Integer.parseInt(ch+"");
	}

	@Override
	protected void setRectangle() {
		this.enemyRectangle.setBounds(x+8, y+8, GamePanel.PER_RECT-8, GamePanel.PER_RECT-8);
	}
	
}
