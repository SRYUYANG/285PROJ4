package entity;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;
import ui.GamePanel;
import util.Function;
import config.StaticImage;

public class BombMan implements Runnable{
	protected GamePanel gp;
	private Rectangle rectangle = new Rectangle();
	private StringBuffer[] map;
	protected int x;
	protected int y;
	protected int life;
	protected int speedX;
	protected int speedY;
	protected int speed;
	private int numberOfBombCanSet;
	private int lenOfFireCanSet;
	private int direct;
	private int idxOfImage = 0;
	protected int shockY;
	protected int hitTimer;
	protected boolean isHitting;
	protected int shockTimer;
	protected boolean isShock;
	private int shootTimer;
	private boolean isMoving;
	protected boolean isLive;
	protected int fallFromY;
	protected int fallType;
	protected boolean canMove;
	protected boolean canShoot;
	protected int timerOfFalling;
	private static final int SHORTER = 32;
	private static final int IMAGE_W = 36;
	private static final int IMAGE_H = 64;
	protected static final int SHOCK_Y = 3;
	protected static final int SHOCK_TIME = 100;
	private static final int HIT_TIME = 60;
	private static final int SHOOT_TIME = 5;

	protected int speedL;
	protected int speedR;
	protected int speedU;
	protected int speedD;
	
	public BombMan(){
		this.speed = 4;
		this.life = 2;
		this.numberOfBombCanSet = 4;
		this.lenOfFireCanSet = 1;
		this.isLive = true;
		this.canMove = true;
		this.shockY = SHOCK_Y;
		this.shockTimer = SHOCK_TIME;
		this.hitTimer = HIT_TIME;
		this.shootTimer = SHOOT_TIME;
	}
	
	//面板上的每个物体都自带的draw方法
	public void drawBombMan(Graphics g){
		
		this.idxOfImage = this.isMoving ? this.idxOfImage : 0;
		
		g.drawImage(StaticImage.BombMans[this.direct].getSubimage(18*(idxOfImage/3), 0, 18, 32), x, y, IMAGE_W, IMAGE_H, null);
		
		if(this.isMoving){
			this.idxOfImage++;
			if(this.idxOfImage == 9){
				this.idxOfImage = 0;
			}
		}
		
		if( this.fallFromY!=0 ){
			this.fall(this.fallFromY, 25, -this.fallFromY+(GamePanel.PANEL_H>>1), 36, 1);
		}
	}
/*------------------------------------一系列动作-------------------------------*/
	//放炸弹
	public void setABomb(){
		if(this.numberOfBombCanSet<=0){ return; }
		Point p = new Point();
		p = this.getBombLocation();
		//如果当前位置已经放了一个炸弹，则在它爆炸之前都不能再放
		if(this.map[p.y].charAt(p.x)=='b'){ return; }
		
		synchronized (gp.getBombs()) {
			gp.getBombs().add(new Bomb(p.x, p.y, this.lenOfFireCanSet, 128, this.gp));
			this.numberOfBombCanSet--;
		}
	}

	public void fire(int type){
		this.gp.getBullets().add(new Bullet(x+GamePanel.PER_RECT, y+(GamePanel.PER_RECT>>1),
				type, 8, 8, this.gp.getDto()));
	}
	
	public void moveUp(){
		this.speedU = -this.speed;
		this.direct = 0;
		this.isMoving = true;
		this.speedY = -this.speed;
	}

	public void moveDown(){
		this.speedD = this.speed;
		this.direct = 1;
		this.isMoving = true;
		this.speedY = this.speed;
	}
	
	public void moveLeft(){
		this.speedL = -this.speed;
		this.direct = 2;
		this.isMoving = true;
		this.speedX = -this.speed;
	}
	
	public void moveRight(){
		this.speedR = this.speed;
		this.direct = 3;
		this.isMoving = true;
		this.speedX = this.speed;
	}
	
	public void stop(){
		this.isMoving = false;
		this.speedX = 0;
		this.speedY = 0;
	}
	
	public void jump(){
	}
	
	protected void fall(int firstY, int timeOfStop, int MaxY, int totalTime, int type){
			if(this.timerOfFalling<timeOfStop && this.fallFromY!=0) {
				int temp =0;
				if(this.fallType==0) {
					temp = Function.getYfromFun1(this.timerOfFalling, MaxY, totalTime);
				}else {
					temp = Function.getYfromFun2(
							this.timerOfFalling, MaxY+(GamePanel.PANEL_H<<1), totalTime);
				}
				this.y = firstY + temp;
				this.timerOfFalling++;
			} else {
				this.fallFromY = 0;
			}
	}
/*----------------------------------碰撞处理------------------------------------*/
	
	//速度分为两个Vx和Vy，如果不按方向键，Vx和Vy都是0，仍会叠加上去，但bombMan不会动
	@Override
	public void run() {
		while(true){
			if(!this.isLive){ break; }
			
			if(this.gp.getDto().isPause()) { continue; }
			
			if(this.isHitting){
				this.hitTimer--;
				if(this.hitTimer<=0){
					this.hitTimer = HIT_TIME;
					this.isHitting = false;
				}
			}
			
			if(this.shootTimer<=0){
				this.fire(0);
				this.shootTimer = SHOOT_TIME;
			}
			if(this.canMove && this.canShoot){
				this.shootTimer--;
			}
			
				this.x+=this.speedX;
				this.y+=this.speedY;
				
				if(this instanceof BombManPlane){
					this.x += this.speedL;
					this.x += this.speedR;
					this.y += this.speedU;
					this.y += this.speedD;
				}
				
				this.isHitObstruction(gp.getObs());
				this.isHitRock(gp.getRocks());
				this.isHitBomb(gp.getBombs());
				this.isHitFire(gp.getFirs());
			
			this.limitXY();
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void limitXY(){
		if(this.fallFromY != 0 || this.fallType==1) {
			return;
		}
		
		if(this.x <= GamePanel.ORINGINAL_X) {
			this.x = GamePanel.ORINGINAL_X;
		}
		else if(this.x+IMAGE_W >= GamePanel.PANEL_W - GamePanel.ORINGINAL_X) {
			this.x = GamePanel.PANEL_W - GamePanel.ORINGINAL_X - IMAGE_W;
		}
		
		if(this.y+SHORTER <= GamePanel.ORINGINAL_Y){
			this.y = GamePanel.ORINGINAL_Y-SHORTER; 
		}
		else if(this.y+IMAGE_H >= GamePanel.PANEL_H - GamePanel.ORINGINAL_Y) {
			this.y = GamePanel.PANEL_H - GamePanel.ORINGINAL_Y - IMAGE_H;
		}
	}
	
	private void isHitObstruction(Vector<Obstruction> obs){
		this.setRectangle();
		for(int i=0; i<obs.size(); i++){
			Rectangle obsRectangle = obs.get(i).getRectangle();
			if(this.rectangle.intersects(obsRectangle)){
				this.afterHit(obsRectangle);
			}
		}
	}
	
	private void isHitRock(Vector<Rock> obs){
		this.setRectangle();
		for(int i=0; i<obs.size(); i++){
			Rectangle obsRectangle = obs.get(i).getRectangle();
			if(this.rectangle.intersects(obsRectangle)){
				this.afterHit(obsRectangle);
			}
		}
	}
	
	private void isHitBomb(Vector<Bomb> bombs){
		this.setRectangle();
		
		for(int i=0; i<bombs.size(); i++){
			Bomb bomb = bombs.get(i);
			Rectangle bombRectangle = new Rectangle(bomb.getX(),bomb.getY(),
					GamePanel.PER_RECT, GamePanel.PER_RECT);
			if(bomb.getInAndOut()==0 && this.rectangle.intersects(bombRectangle)){
				bomb.setInAndOut(1);
			}
			if(bomb.getInAndOut()==1 && !this.rectangle.intersects(bombRectangle)){
				bomb.setInAndOut(2);
			}
			if(bomb.getInAndOut()==2){
				this.afterHit(bombRectangle);
			}
		}
	}
	
	protected void isHitFire(Vector<Fire> fires){
//		this.setRectangle();
		for(int i=0; i<fires.size(); i++){
			if(this.rectangle.intersects(fires.get(i).getRectangle())){
				this.afterDeath();
			}
		}
	}
	
	//碰到障碍物之后，的处理方法，不是让bombMan静止不动，而是让其后退，不过是后退到距离障碍物一个像素的位置
	//尽量让人感觉不到有后退
	private void afterHit(Rectangle obsRectangle){
		Point p = GamePanel.computeXjAndYi(obsRectangle.x, obsRectangle.y);
		int i = p.x;
		int j = p.y;
//		System.out.println("i="+i+" j="+j);
//		System.out.println("右上"+this.map[i-1].charAt(j)+"上"+this.map[i-1].charAt(j-1));
		
		switch(this.direct){
		case 0: if(obsRectangle.contains(this.x, this.y+SHORTER) || obsRectangle.contains(this.x+IMAGE_W, this.y+SHORTER)){
			//如果左上，左边都没有障碍物，而且bombMan偏左，就让bombMan往左移
			if(j-1>=0 && i+1<=8 && this.map[i].charAt(j-1)=='_' && this.map[i+1].charAt(j-1)=='_'
					&& !obsRectangle.contains(this.x ,this.y+SHORTER)){
				this.x-=2;
			}
			//如果右上，右边都没有障碍物，而且bombMan偏右，就让bombMan往右移
			else if(j+1<=12 && i+1<=8 && this.map[i].charAt(j+1)=='_' && this.map[i+1].charAt(j+1)=='_'
					&& !obsRectangle.contains(this.x+IMAGE_W, this.y+SHORTER)){
				this.x+=2;
			}
			this.y = (int)(obsRectangle.getY() + obsRectangle.getHeight() + 1) - SHORTER;
		}
		break;
		
		case 1: if(obsRectangle.contains(this.x, this.y+IMAGE_H) || obsRectangle.contains(this.x+IMAGE_W, this.y+IMAGE_H)){
			//如果左下，左边都没有障碍物，而且bombMan偏左，就让bombMan往左移
			if(j-1>=0 && i-1>=0 && this.map[i].charAt(j-1)=='_' && this.map[i-1].charAt(j-1)=='_'
					&& !obsRectangle.contains(this.x ,this.y+IMAGE_H)){
				this.x-=2;
			}
			//如果右下，右边都没有障碍物，而且bombMan偏右，就让bombMan往右移
			else if(j+1<=12 && i-1>=0 && this.map[i].charAt(j+1)=='_' && this.map[i-1].charAt(j+1)=='_'
					&& !obsRectangle.contains(this.x+IMAGE_W, this.y+IMAGE_H)){
				this.x+=2;
			}
			this.y = (int)(obsRectangle.getY() - 1) - IMAGE_H;
		}
		break;
		case 2: if(obsRectangle.contains(this.x, this.y+SHORTER) || obsRectangle.contains(this.x, this.y+IMAGE_H)){
			//如果左上，上边都没有障碍物，而且bombMan偏上，就让bombMan往上移
			if(i-1>=0 && j+1<=12 && this.map[i-1].charAt(j)=='_' && this.map[i-1].charAt(j+1)=='_'
					&& !obsRectangle.contains(this.x, this.y+SHORTER)){
				this.y-=2;
			}
			//如果左下，下边都没有障碍物，而且bombMan偏下，就让bombMan往下移
			else if(i+1<=8 && j+1<=12 && this.map[i+1].charAt(j)=='_' && this.map[i+1].charAt(j+1)=='_'
					&& !obsRectangle.contains(this.x, this.y+IMAGE_H)){
				this.y+=2;
			}
			this.x = (int)(obsRectangle.getX() + obsRectangle.getWidth() + 1);
		}
		break;
		
		case 3: if(obsRectangle.contains(this.x+IMAGE_W, this.y+SHORTER) || obsRectangle.contains(this.x+IMAGE_W, this.y+IMAGE_H)){
			//如果右上，上边都没有障碍物，而且bombMan偏上，就让bombMan往上移
			if(i-1>=0 && j-1>=0 && this.map[i-1].charAt(j)=='_' && this.map[i-1].charAt(j-1)=='_'
					&& !obsRectangle.contains(this.x+IMAGE_W, this.y+SHORTER)){
				this.y-=2;
			}
			//如果右下，下边都没有障碍物，而且bombMan偏下，就让bombMan往下移
			else if(i+1<=8 && j-1>=0 && this.map[i+1].charAt(j)=='_' && this.map[i+1].charAt(j-1)=='_'
					&& !obsRectangle.contains(this.x+IMAGE_W, this.y+IMAGE_H)){
				this.y+=2;
			}
			this.x = (int)(obsRectangle.getX() - IMAGE_W -1);
		}
		break;
		}
	}
	
	//被敌人碰中之后的处理方法
	public void afterDeath(){
		this.speed = 0;
		this.life--;
		this.gp.getDto().addBombManLife(-1);
		if(this.life<=0){
			this.isLive = false;
			this.gp.getDto().setGameOver(true);
			this.gp.getFirs().add(new Fire_White(x,y));
			this.x = -100;
			this.y = -100;
		}
	}
	
	//重新生成bombMand的方法，包括重设其坐标等
	public void resetAfterDeath(){
		this.x = 0+GamePanel.ORINGINAL_X;
		this.y = 0+GamePanel.ORINGINAL_Y;
		this.isLive = true;
		this.numberOfBombCanSet = 1;
		this.lenOfFireCanSet = 1;
		new Thread(this).start();
	}
	
/*------------------------------以下全是Getter和Setter------------------------------*/
	
	public Point getBombLocation(){
		int bombX = this.x + (IMAGE_W>>1) - GamePanel.ORINGINAL_X;
		int bombY = this.y + SHORTER + (IMAGE_H-SHORTER>>1) - GamePanel.ORINGINAL_Y;
		bombX = bombX /GamePanel.PER_RECT;
		bombY = bombY /GamePanel.PER_RECT;
		return new Point(bombX, bombY);
	}

	public int getNumberOfBombCanSet() {
		return numberOfBombCanSet;
	}

	public void setNumberOfBombCanSet(int numberOfBombCanSet) {
		this.numberOfBombCanSet = numberOfBombCanSet;
	}

	public int getLenOfFireCanSet() {
		return lenOfFireCanSet;
	}

	public void setLenOfFireCanSet(int lenOfFireCanSet) {
		this.lenOfFireCanSet = lenOfFireCanSet;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public boolean isLive() {
		return isLive;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public int getDirect() {
		return direct;
	}
	
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	private void setRectangle(){
		this.rectangle.setBounds(this.x , this.y+SHORTER, IMAGE_W, IMAGE_H-SHORTER);
	}
	
	public Rectangle getRectangle(){
		if(!this.isHitting){
			this.setRectangle();
		}
		else{
			this.rectangle.setLocation(-GamePanel.PER_RECT, -GamePanel.PER_RECT);
		}
		
		if(!this.isLive) {
			this.rectangle.setLocation(-GamePanel.PER_RECT, -GamePanel.PER_RECT);
		}
		return this.rectangle;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public void setGp(GamePanel gp) {
		this.gp = gp;
		if(this.gp.getDto().getTotalBombManLife()==0){
			this.gp.getDto().setTotalBombManLife(this.life);
		}else {
			this.life = this.gp.getDto().getBombManLife();
		}
		this.map = gp.getMap();
		new Thread(this).start();
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}

	public void setFallFromY(int fallFromY, int type) {
		this.fallFromY = fallFromY;
		this.fallType = type;
		this.timerOfFalling = 0;
	}

	public void setSpeedL(int speedL) {
		this.speedL = speedL;
	}

	public void setSpeedR(int speedR) {
		this.speedR = speedR;
	}

	public void setSpeedU(int speedU) {
		this.speedU = speedU;
	}

	public void setSpeedD(int speedD) {
		this.speedD = speedD;
	}
	
}
