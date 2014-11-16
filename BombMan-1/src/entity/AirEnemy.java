package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import ui.GamePanel;

public abstract class AirEnemy {
	
	protected GamePanel gp;
	protected BufferedImage imageOfEnemy;
	protected BufferedImage imageOfEnemy2;
	protected Random random;
	protected int x;
	protected int y;
	protected int s;
	protected int firstX;
	protected int firstY;
	protected int size;
	protected int life;
	protected int timer;
	protected int shockY;
	protected int speedX;
	protected int speedY;
	protected int imageW;
	protected int imageH;
	protected boolean isAlive;
	protected boolean isDraw;
	protected boolean isBoss;
	protected boolean isBullet;
	private boolean upOrDown;
	private boolean isUppingOrDowning;
	protected boolean isDrawAnotherImage;
	protected static final int SHINING_TIME = 6;
	protected static final int SHOCK_Y = 4;
	
	public AirEnemy(int x, int y, GamePanel gp){
		this.x = x;
		this.y = y;
		this.s = GamePanel.PER_RECT;
		this.firstX = x;
		this.firstY = y;
		this.gp = gp;
		this.random = new Random();
		this.isAlive = true;
		this.isDraw  = false;
		this.timer = SHINING_TIME;
		this.shockY = SHOCK_Y;
		this.isDrawAnotherImage = false;
		this.init();
	}
	
	public AirEnemy(GamePanel gp){
		this.s = GamePanel.PER_RECT;
		this.gp = gp;
		this.random = new Random();
		this.isAlive = true;
		this.isDraw  = false;
		this.timer = SHINING_TIME;
		this.shockY = SHOCK_Y;
		this.isDrawAnotherImage = false;
		this.init();
	}
	
	public void drawImageOfEnemy(Graphics g){
		
		if(!this.isDraw){
			return;
		}
		
		if(this.isAlive) {
			this.isHitBulletOrbombMan();
		}
		
		int imgX = this.x - (this.imageW - this.size >> 1);
		int imgY = this.y - (this.imageH - this.size >> 1);
		
		if(isDrawAnotherImage && this.imageOfEnemy2!=null){
			g.drawImage(imageOfEnemy2, imgX+4, imgY+this.shockY, imageW, imageH, this.gp);
			this.timer--;
			this.shockY = -this.shockY;
			if(this.timer<=0){
			this.isDrawAnotherImage = false;
			this.timer = SHINING_TIME;
			}
		}else{
			g.drawImage(imageOfEnemy, imgX, imgY, imageW, imageH, this.gp);
		}
	}
	
	private void judgeUpOrDown(){
		if(this.isUppingOrDowning) {
			return;
		}
		
		if(this.random.nextInt(80)!=0){
			return;
		}
		
		this.isUppingOrDowning = true;
		int YofBombMan = this.gp.getBombMan().y;
		
		if( YofBombMan > this.y){
			this.upOrDown = false;
		}else if(YofBombMan < this.y){
			this.upOrDown = true;
		}
		
	}
	
	private void doUpOrDown(){
		if(!this.isUppingOrDowning) return;
		if(this.upOrDown){
			this.y -= this.speedY;
		}else{
			this.y += this.speedY;
		}
		
		this.judgeY();
		
		this.s -= this.speedY;
		
		if(this.s<=0){
			this.s = GamePanel.ORINGINAL_X;
			this.isUppingOrDowning = false;
		}
	}
	
	protected void isHitBulletOrbombMan(){
		
		for(int i=0; i<this.gp.getBullets().size(); i++){
			Bullet bllt = this.gp.getBullets().get(i);
			if(bllt.getRetangle().intersects(this.getRectangle())){
				this.willDeath();
				this.gp.getBullets().get(i).setLive(false);
			}
		}
		BombMan bombMan = this.gp.getBombMan();
		if(bombMan.getRectangle().intersects(this.getRectangle())){
			bombMan.afterDeath();
			this.willDeath();
		}
	}
	
	protected void judgeY(){
		//不让Y坐标超出活动范围
		if(this.y < GamePanel.ORINGINAL_Y){
			this.y = GamePanel.ORINGINAL_Y;
		}
		else if(this.y > GamePanel.PANEL_H - GamePanel.ORINGINAL_Y - this.size){
			this.y = GamePanel.PANEL_H - GamePanel.ORINGINAL_Y - this.size;
		}
	}
	
	public void move(){
		this.x -= speedX;
		
		if(this.x >= -GamePanel.PER_RECT<<1 &&
				this.x < GamePanel.PANEL_W+(this.size<<1) ){
			this.isDraw = true;
		}else{
			this.isDraw = false;
			if(this.x < -GamePanel.PER_RECT<<2){
				this.isAlive = false;
			}
		}
		this.doUpOrDown();
		this.judgeUpOrDown();
	}
	
	
	public boolean isAlive() {
		return isAlive;
	}

	public void resetXY() {
		this.isAlive = true;
		this.x = this.firstX;
		this.y = this.firstY;
	}
	
	public boolean isBoss() {
		return isBoss;
	}

	public boolean isBullet() {
		return isBullet;
	}

	protected Rectangle getRectangle(){
		return new Rectangle(x, y, size, size);
	}

	protected abstract void init();
	protected abstract void willDeath();
	protected abstract void attack();
}
