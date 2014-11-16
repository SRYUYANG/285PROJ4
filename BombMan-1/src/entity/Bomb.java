package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ui.GamePanel;
import config.StaticImage;

public class Bomb {
	private GamePanel gp;
	private StringBuffer[] map;
	private BufferedImage[] bombImage;
	private Rectangle bombRect;
	private int x;
	private int y;
	private int xj;
	private int yi;
	private int lenOfFire;
	private boolean isAlive;
	private int liveTime;
	private int InAndOut;
	private int jump;
	private int idxOfImage;
	private int timer;
	private static final int IMAGE_W = 48;
	private static final int IMAGE_H = 48;
	private static final int EARLY_TO_PLAYSOUND = 4;
	
	public Bomb(int xj, int yi, int lenOfFire, int liveTime, GamePanel gp){
		this.gp = gp;
		this.map = gp.getMap();
		this.xj = xj;
		this.yi = yi;
		this.x = xj * GamePanel.PER_RECT + GamePanel.ORINGINAL_X;
		this.y = yi * GamePanel.PER_RECT + GamePanel.ORINGINAL_Y;
		this.bombRect = new Rectangle(this.x, this.y, GamePanel.PER_RECT, GamePanel.PER_RECT);
		this.lenOfFire = lenOfFire;
		this.liveTime = liveTime;
		this.isAlive = true;
		this.idxOfImage = 0;
		this.timer = 0;
		this.bombImage = StaticImage.Bomb;
		this.map[this.yi].replace(this.xj, this.xj+1, "b");
	}
	
	public void drawBomb(Graphics g){
		//炸弹爆炸后，产生火焰
		if(this.timer>=this.liveTime){
			this.addFire(this.x, this.y);
			int tempX;
			int tempY;
			int t;
			boolean continueUp = true;
			boolean continueDown = true;
			boolean continueLeft = true;
			boolean continueRight = true;
			
			for(int i=1; i<=this.lenOfFire; i++){
				if(continueRight){
				tempX = this.x+i*GamePanel.PER_RECT;
				tempY = this.y;
				t = this.canSetFire(tempX, tempY);
				if(t==1||t==2){ this.addFire(tempX, tempY); }
				if(t==0||t==1){continueRight = false;}
				}
				if(continueLeft){
				tempX = this.x-i*GamePanel.PER_RECT;
				tempY = this.y;
				t = this.canSetFire(tempX, tempY);
				if(t==1||t==2){ this.addFire(tempX, tempY); }
				if(t==0||t==1){continueLeft = false;}
				}
				if(continueDown){
				tempX = this.x;
				tempY = this.y+i*GamePanel.PER_RECT;
				t = this.canSetFire(tempX, tempY);
				if(t==1||t==2){ this.addFire(tempX, tempY); }
				if(t==0||t==1){continueDown = false;}
				}
				if(continueUp){
				tempX = this.x;
				tempY = this.y-i*GamePanel.PER_RECT;
				t = this.canSetFire(tempX, tempY);
				if(t==1||t==2){ this.addFire(tempX, tempY); }
				if(t==0||t==1){continueUp = false;}
				}
			}
			
			this.isAlive = false;
			this.map[this.yi].replace(this.xj, this.xj+1, "_");
			return;
		}
		
		//判断是否被其他火焰引爆
		synchronized (this.gp.getFirs()) {
			this.isHitFire();
		}
		
		if(this.timer < this.liveTime>>1){
			this.idxOfImage = 0;
		}else if(this.timer < this.liveTime-20){
			this.jump = -2;
			this.idxOfImage = 1;
		}else if(this.timer < this.liveTime){
			this.jump = -this.jump;
			this.idxOfImage = 2;
			
			if(this.timer == this.liveTime - EARLY_TO_PLAYSOUND){
//				new Thread(StaticSound.blastOfBomb).start();
			}
		}
		g.drawImage(this.bombImage[this.idxOfImage], x, y+jump, IMAGE_W, IMAGE_H, null);
		this.timer++;
	}

	private int canSetFire(int x, int y){
		if(x<GamePanel.ORINGINAL_X || x>gp.getWidth()-GamePanel.ORINGINAL_X - 10
				|| y<GamePanel.ORINGINAL_Y || y>gp.getHeight()-GamePanel.ORINGINAL_Y - 10){
			return 0;
		}
		for(int i=0; i<gp.getRocks().size(); i++){
			Rock rock = gp.getRocks().get(i);
			if(rock.x == x && rock.y == y){
				return 0;
			}
		}
		for(int i=0; i<gp.getObs().size(); i++){
			Obstruction ob = gp.getObs().get(i);
			if(ob.x == x && ob.y ==y){
				synchronized (gp.getObs()) {
					ob.setAlive(false);
					int yi = (ob.y-GamePanel.ORINGINAL_Y)/GamePanel.PER_RECT;
					int xj = (ob.x-GamePanel.ORINGINAL_X)/GamePanel.PER_RECT;
					//这句把所有的charAt（xj）都换掉了，坑
//					map[yi] = map[yi].replace(map[yi].charAt(xj), '_');
//					String temp1 = map[yi].substring(0,xj);
//					String temp2 = map[yi].substring(xj);
//					temp2 = temp2.replaceFirst("0", "_");
//					map[yi] = temp1 + temp2;
					//后来学会了用StringBuffer，再也不用那么麻烦了，噢耶~
					map[yi].replace(xj, xj+1, "_");
					return 1;
				}
			}
		}
		return 2;
	}
	
	private void isHitFire(){
		for(int i=0; i<gp.getFirs().size(); i++){
			if(this.bombRect.intersects(gp.getFirs().get(i).getRectangle())){
				if(this.timer > this.liveTime - EARLY_TO_PLAYSOUND - 1){
					this.timer = this.liveTime - EARLY_TO_PLAYSOUND - 1;
//					new Thread(StaticSound.blastOfBombss).start();
				}
			}
		}
	}
	
	private void addFire(int x, int y){
		this.gp.getFirs().add(new Fire(x, y));
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public int getXj() {
		return xj;
	}

	public int getYi() {
		return yi;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getInAndOut() {
		return InAndOut;
	}

	public void setInAndOut(int inAndOut) {
		InAndOut = inAndOut;
	}

	public void changeX(int x) {
		this.x += x;
	}
	
}
