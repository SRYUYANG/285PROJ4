package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import config.StaticImage;
import ui.GamePanel;
import util.ImageIndexChanger;

public class AirBomb extends AirEnemy {

	private ImageIndexChanger imgIdxChanger;
	private int[] changeXY = {0,-1,1};
	
	public AirBomb(int x, int y, GamePanel gp) {
		super(x, -y, gp);
		int temp = this.random.nextInt(6);
		this.x += temp*GamePanel.PER_RECT+(GamePanel.PANEL_W);
		this.firstX = this.x;
		this.firstY = this.y;
		this.imgIdxChanger = new ImageIndexChanger(14*4);
	}

	@Override
	protected void init() {
		this.life = 1;
		this.speedX = -1;
		this.speedY = 6;
		this.imageOfEnemy = StaticImage.Enemys.get(6);
	}

	@Override
	public void willDeath() {
		this.isAlive = false;
		this.gp.getDto().addPoint(5);
		this.gp.getFirs().add(new Fire(x, y));
	}

	@Override
	public void drawImageOfEnemy(Graphics g){
		
		if(!this.isDraw){
			return;
		}
		
		if(this.isAlive) {
			this.isHitBulletOrbombMan();
		
			int w = this.imageOfEnemy.getWidth();
			int h = this.imageOfEnemy.getHeight()/14;
			g.drawImage(imageOfEnemy.getSubimage(0, h*(this.imgIdxChanger.nextIndexOfImage()/4),
					w, h), x, y, GamePanel.PER_RECT, GamePanel.PER_RECT, this.gp);
		}
	}
	
	@Override
	public void move(){
		//上升过程的处理
		if(this.speedY < 0) {
			//位于视野之外，偷懒，马上到达目标位置
			if(this.y < -GamePanel.PER_RECT<<1) {
				this.x = this.firstX;
				this.y = this.firstY;
				this.speedY  = -this.speedY;
				this.speedX = -1;
				return;
			}
			
			if(this.x < this.firstX) {
				this.x += this.speedX;
			}else if(this.x > this.firstX) {
				this.x += -this.speedX;
			}
			
			if(this.y < this.firstY) {
				this.y = this.firstY;
				this.speedY = -this.speedY;
			}else if(this.y > this.firstY) {
				this.y += this.speedY;
			}
			
			return;
		}
		
		this.y += speedY;
		this.x += speedX;
		if(this.x < GamePanel.ORINGINAL_X>>2){
			this.x = GamePanel.ORINGINAL_X>>2;
			if(this.y<-GamePanel.PANEL_H>>1){
				this.x += GamePanel.PER_RECT*(this.random.nextInt(6)+3);
			}
		}
		else if(this.y < -GamePanel.ORINGINAL_Y){
			int dis = this.x - GamePanel.PANEL_W;
			if(dis>400){
				this.x -= GamePanel.PANEL_W;
			}
		}
		if(this.y >= -GamePanel.PER_RECT<<1 &&
				this.y < GamePanel.PANEL_H+(GamePanel.PER_RECT<<1) ){
			this.isDraw = true;
		}else{
			this.isDraw = false;
			if(this.y>GamePanel.PANEL_H){
				this.isAlive = false;
			}
		}
//		System.out.println(this.y+"speed="+this.speedY);
	}

	@Override
	protected Rectangle getRectangle(){
		return new Rectangle(x-8, y+(GamePanel.PER_RECT>>1),
				GamePanel.PER_RECT-8, GamePanel.PER_RECT>>1);
	}
	
	@Override
	public void resetXY() {
		int temp = this.random.nextInt(3);
		this.firstX = this.firstX + GamePanel.PER_RECT*this.changeXY[temp];
//		temp = this.random.nextInt(3);
		this.firstY = this.firstY + GamePanel.PER_RECT*this.changeXY[temp];
		this.isAlive = true;
		
		if(this.gp.getAirBoss() != null) {
//			this.x = this.gp.getAirBoss().x;
			this.x = this.gp.getAirBoss().x + (GamePanel.PER_RECT<<1) + 38;
			
//			this.y = this.gp.getAirBoss().y+GamePanel.PER_RECT;
			this.y = this.gp.getAirBoss().y - (GamePanel.PER_RECT<<2) - 16;
			
//			this.speedY = -this.speedY + this.random.nextInt(6);
//			if(this.speedY >=0 ){
				this.speedY = -8;
//			}
			this.speedX = this.speedX + this.random.nextInt(1);
			this.gp.getAirBoss().isAct = true;
		}else{
			this.x = this.firstX;
			this.y = this.firstY;
		}
	}

	@Override
	protected void attack() {
	}

}
