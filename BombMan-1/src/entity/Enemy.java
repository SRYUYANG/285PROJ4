package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Vector;

import ui.GamePanel;

public  abstract class Enemy implements Runnable{
	protected GamePanel gp;
	protected BombMan bombMan;
	protected Random random;
	protected StringBuffer[] map;
	protected int x;
	protected int y;
	protected int direct;
	protected int speed;
	protected boolean canMove;
	private boolean isRunning;
	protected int timer;
	protected int IdxOfImage;
	protected boolean isAlive;
	protected Image[] enemyImage;
	protected Rectangle enemyRectangle;
	
	protected Enemy(int x, int y, GamePanel gp){
		this.x = x;
		this.y = y;
		this.gp = gp;
		this.direct = 0;
		this.isAlive = true;
		this.canMove = false;
		this.enemyRectangle = new Rectangle();
		map = new StringBuffer[gp.getMap().length];
		map = gp.getMap();
//		map = new boolean[9][13];
//		map = gp.getmap();
		bombMan = gp.getBombMan();
		random = new Random();
	}
	
	@Override
	public void run() {
		int distance = 0;
		while(this.isAlive && this.gp.isAlive()){
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			if(this.x > GamePanel.PANEL_W + GamePanel.PER_RECT){
//				continue;
//			}
			
			this.isHitBombMan();
			this.isHitFireOrBombManOrBullet(gp.getFirs(), gp.getBullets());
			
			if(distance == 0){
				this.changeDirect();
			}
			
			if(this.canMove){
				switch(this.direct){
				case 0: this.y -= this.speed;
				break;
				case 1: this.x += this.speed;
				break;
				case 2: this.y += this.speed;
				break;
				case 3: this.x -= this.speed;
				}
			}
			distance+=this.speed;
			if(distance == GamePanel.PER_RECT){ distance = 0; }
		}
	}
	
	protected void changeDirect(){
		int i = (y - GamePanel.ORINGINAL_Y)/GamePanel.PER_RECT;
		int j = (x - GamePanel.ORINGINAL_X)/GamePanel.PER_RECT;
//		System.out.print("i="+i+"j="+j+" ");
		//有可能的方向
		String possibleDirect = "";
		//现在的方向
		char ch = Integer.toString(this.direct).charAt(0);
		//现在的炸弹位置图
			
		if(i-1>=0 && map[i-1].charAt(j)=='_' && map[i-1].charAt(j)!='b'){
			possibleDirect += "00";
			if(i-2>=0 && map[i-2].charAt(j)!='b'){ possibleDirect += "000"; }
		}
		if(i+1<=8 && map[i+1].charAt(j)=='_' && map[i+1].charAt(j)!='b'){
			possibleDirect += "22";
			if(i+2<=8 && map[i+2].charAt(j)!='b'){ possibleDirect += "222"; }
		}
		if(j-1>=0 && map[i].charAt(j-1)=='_' && map[i].charAt(j-1)!='b'){
			possibleDirect += "33";
			if(j-2>=0 && map[i].charAt(j-2)!='b'){ possibleDirect += "333"; }
		}
		if(j+1<=12 && map[i].charAt(j+1)=='_' && map[i].charAt(j+1)!='b'){
			possibleDirect += "11";
			if(j+2<=12 && map[i].charAt(j+2)!='b'){ possibleDirect += "111"; }
		}
		if(possibleDirect.indexOf(ch) != -1){
			possibleDirect += ch;
			possibleDirect += ch;
		}
		//取得当前方向的相反方向
		String oppositeDirectStr = Integer.toString((this.direct+2)%4);
		char oppositeDirectCh = oppositeDirectStr.charAt(0);
		
		//在字符窜中删除一个oppositeDirect，从而减少回头的可能性
		if( possibleDirect.indexOf(oppositeDirectCh) != -1){
			possibleDirect = possibleDirect.replaceFirst(oppositeDirectStr, "");
		}
		int len = possibleDirect.length();
		if(possibleDirect == "") {
//			this.direct = new Random().nextInt(4);
			this.direct = this.random.nextInt(4);
			this.canMove = false;
			}
		else{
//			System.out.print(map[i].charAt(j+1));
			this.canMove = true;
//			每次都重新实例化一个random对象的话估计会消耗更多内存，于是改成只实例化一次
//			ch = possibleDirect.charAt(new Random().nextInt(len));
			ch = possibleDirect.charAt(this.random.nextInt(len));
			this.direct = Integer.parseInt(ch+"");
		}
	}
	
	private boolean isHitFireOrBombManOrBullet(Vector<Fire> fires, Vector<Bullet> bullets){
//		Rectangle enemyRectangle = new Rectangle(this.x, this.y, GamePanel.PER_RECT, GamePanel.PER_RECT);
		this.setRectangle();
//		if(enemyRectangle.intersects(bombMan.getRectangle())){
//			bombMan.afterDeath();
//		}
		for(int i=0; i<fires.size(); i++){
			if(enemyRectangle.intersects(fires.get(i).getRectangle())){
				this.willDeath();
				return true;
			}
		}
		for(int i=0; i<bullets.size(); i++){
			if(enemyRectangle.intersects(bullets.get(i).getRetangle())){
				bullets.get(i).setLive(false);
				this.willDeath();
				this.isAlive = false;
				return true;
			}
		}
		return false;
	}
	
	public void isHitBombMan() {
		this.setRectangle();
		if(enemyRectangle.intersects(bombMan.getRectangle())){
			bombMan.afterDeath();
		}
	}
	//为了到时候可以让子类重写，于是单独写成一个方法
	protected void setRectangle(){
		this.enemyRectangle.setBounds(x, y, GamePanel.PER_RECT, GamePanel.PER_RECT);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void changeX(int x) {
		this.x += x;
		if(!this.isRunning && this.x < GamePanel.PANEL_W - GamePanel.PER_RECT){
			new Thread(this).start();
			this.isRunning = true;
		}
		if(this.x < 0 - GamePanel.PER_RECT){
			this.isAlive = false;
		}
	}

	public abstract void willDeath();
	public abstract void drawEnemy(Graphics g);
}
