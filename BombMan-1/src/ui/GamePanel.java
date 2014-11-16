package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;

import util.StringShower;
import util.StringUtil;
import control.GameControler;
import dto.GameDto;
import entity.AirBomb;
import entity.AirBoss;
import entity.AirBoss2;
import entity.AirBoss3;
import entity.AirEnemy;
import entity.AirEnemyXBullet;
import entity.AirEnemy_BombMan0;
import entity.Bomb;
import entity.BombMan;
import entity.Bullet;
import entity.Cloud;
import entity.Enemy;
import entity.Enemy_Ghost;
import entity.Enemy_Robot;
import entity.Fire;
import entity.Obstruction;
import entity.Reward;
import entity.Rock;

public class GamePanel extends JPanel implements Runnable,KeyListener{
	
	private static final long serialVersionUID = -5194744397408657473L;

	public static final int PER_RECT = 48;
	public static final int ORINGINAL_X = 50;
	public static final int ORINGINAL_Y = 50;
	public static final int PANEL_W = 724;
	public static final int PANEL_H = 532;

	protected GameDto dto;
//	protected GameFrame gf;
	protected GameControler gc;
	protected BombMan bombMan;
	protected BackGround bg;
	protected BackGround bg2;
	protected AirBoss airBoss;
	protected Vector<Obstruction> obs = new Vector<Obstruction>();
	protected Vector<Rock> rocks = new Vector<Rock>();
	protected Vector<Bomb> bombs = new Vector<Bomb>();
	protected Vector<Fire> firs = new Vector<Fire>();
	protected Vector<Enemy> enemys = new Vector<Enemy>();
	protected Vector<AirEnemy> airEnemys = new Vector<AirEnemy>();
	protected Vector<AirEnemy> reAirEnemys = new Vector<AirEnemy>();
	protected Vector<Bullet> bullets = new Vector<Bullet>();
	protected Vector<Cloud> clouds = new Vector<Cloud>();
	protected Vector<Reward> rewards = new Vector<Reward>();
	protected StringBuffer[] map = null;
	protected StringShower stringShower;
	//这里要在方法外部初始化，getBombMap才不会返回空针，不明白为甚么
	//private boolean[][] bombMap = new boolean[9][13];
	//为了让每次只能摁下一个方向键，所以设置一个标志
	//TODO
	private boolean isPressingDirect;
	private int nowDirectKeyCode;
	protected int timer;
	protected boolean isAlive;
	
	//滚轴地图专属的x坐标
	protected int xOfRollMap;
	
	public GamePanel(GameDto dto, BombMan bombMan, StringBuffer[] map,
			BackGround bg, BackGround bg2, GameControler gc){
		this.gc = gc;
		//设置布局管理器为自由布局
		this.setLayout(null);
		this.setSize(PANEL_W, PANEL_H);
		this.setBackground(Color.BLACK);
		this.dto = dto;
		this.map = map;
		this.isAlive = true;
		this.bombMan = bombMan;
		this.bombMan.setGp(this);
		this.createMap();
		this.bg = bg;
		this.bg2 = bg2;
		
		BufferedImage paper = new BufferedImage(540, 70, BufferedImage.TYPE_4BYTE_ABGR);
		paper = StringUtil.addStringToImage("GAME OVER", paper, 9);
		this.stringShower =  new StringShower(16, 400, paper);
		
		new Thread(this).start();
		
		this.addListener();
	}
	
	protected void addListener(){
		this.addKeyListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g0) {
		// TODO 自动生成的方法存根
		super.paintComponent(g0);
		
		BufferedImage image = new BufferedImage(GamePanel.PANEL_W+20, GamePanel.PANEL_H+20,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = image.getGraphics();
		
		if(bg2!=null){
			bg2.drawBackGround(g, this);
		}
		//绘制背景图片
		bg.drawBackGround(g, this);
		
		//绘制箱子障碍物
		for(int i=0; i<obs.size(); i++){
			Obstruction ob = obs.get(i);
			if(ob.isAlive()){
				ob.drawObstruction(g);
			}else{
				if(ob.getReward() != null){
					this.rewards.add(ob.getReward());
				}
				obs.remove(i);
			}
		}
		//会抛ConcurrentModificationException这个异常，暂时不用这个方法
//		Iterator<Obstruction> itOfOb = this.obs.iterator();
//		while(itOfOb.hasNext()){
//			synchronized (this) {
//				Obstruction ob = itOfOb.next();
//				if(ob.isAlive()){
//					ob.drawObstruction(g);
//				}else{
//					this.obs.remove(ob);
//				}
//			}
//		}
//		Iterator<Bomb> itOfBomb = this.bombs.iterator();
//		while(itOfBomb.hasNext()){
//			synchronized (this.bombs) {
//				Bomb bomb = itOfBomb.next();
//				if(!bomb.isAlive()){
//					itOfBomb.remove();
//					this.bombs.remove(bomb);
//					continue;
//				}
//				bomb.drawBomb(g);
//			}
//		}
		
		//绘制奖励能力的图标
		for(int i=0; i<this.rewards.size(); i++){
			Reward reward = rewards.get(i);
			if(reward.isAlive()){
				reward.drawReward(g);
				reward.effect(bombMan);
			}else{
				rewards.remove(i);
			}
		}
		
		//绘制炸弹
		for(int i=0; i<this.bombs.size(); i++){
			Bomb bomb = this.bombs.get(i);
			
			if(!bomb.isAlive()){
				this.bombs.remove(bomb);
				this.bombMan.setNumberOfBombCanSet(bombMan.getNumberOfBombCanSet()+1);
				continue;
			}
			
			bomb.drawBomb(g);
		}
		
		//绘制敌人
		for(int i=0; i<this.enemys.size(); i++){
			Enemy enemy = this.enemys.get(i);
			if(!enemy.isAlive()){
				this.dto.setNumOfEnemy(dto.getNumOfEnemy() - 1);
				this.enemys.remove(enemy);
			}else{
				enemy.drawEnemy(g);
			}
		}
		//绘制空中敌人
		for(int i=0; i<this.airEnemys.size(); i++){
			AirEnemy airEn = this.airEnemys.get(i);
			if(airEn.isAlive()){
				airEn.drawImageOfEnemy(g);
			}else{
				this.airEnemys.remove(i);
				this.reAirEnemys.add(airEn);
			}
		}
		//绘制炸弹人
		if(bombMan.isLive()){
			bombMan.drawBombMan(g);
		}else{
			//bombMan.resetAfterDeath();
		}
		//绘制子弹
		for(int i=0; i<this.bullets.size(); i++){
			Bullet bllt = this.bullets.get(i);
			if(bllt.isLive()){
				bllt.drawBullet(g);
			}
			else{
				this.bullets.remove(bllt);
			}
		}
		
		//绘制炸弹产生的火焰
		for(int i=0; i<this.firs.size(); i++){
			Fire fire = this.firs.get(i);
			if(!fire.isAlive()){
				this.firs.remove(fire);
			}else{
				fire.drawFire(g);
			}
		}
		
		//绘制计分板
		Board.drawBoard(g, dto);
		
		if(this.dto.isGameOver()){
			this.stringShower.drawString(g);
		}
		
		g0.drawImage(image, 0, 0, this);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(!isPressingDirect && !this.dto.isGameOver()){
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: this.bombMan.moveUp();
								this.isPressingDirect=true;
								this.nowDirectKeyCode = e.getKeyCode();
			break;
			case KeyEvent.VK_DOWN: this.bombMan.moveDown();
								this.isPressingDirect=true;
								this.nowDirectKeyCode = e.getKeyCode();
			break;
			case KeyEvent.VK_LEFT: this.bombMan.moveLeft();
								this.isPressingDirect=true; 
								this.nowDirectKeyCode = e.getKeyCode();
			break;
			case KeyEvent.VK_RIGHT: this.bombMan.moveRight(); 
								this.isPressingDirect=true;
								this.nowDirectKeyCode = e.getKeyCode();
			break;
		}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			this.bombMan.setABomb();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == this.nowDirectKeyCode && !this.dto.isGameOver()){
			this.bombMan.stop();
			this.isPressingDirect = false;
		}
	}

	@Override
	public void run() {
		while(this.isAlive){
			
			if(this.dto.isPause()) { continue; }
			
			this.repaint();
			
			synchronized (this.dto) {
				
			if(	this.airBoss == null && this.dto.getPoint() >= dto.getTargerPoint()){
//				this.isAlive = false;
//				this.clearVector();
//				this.gf.newStage();
//				break;
				if(this.dto.getStage()==1){
					this.airBoss = new AirBoss(this);
					this.airEnemys.add(airBoss);
				}
				else if(this.dto.getStage()==2){
					this.airEnemys.clear();
					this.airBoss = new AirBoss2(this);
					this.airEnemys.add(airBoss);
					this.airEnemys.add(this.airBoss.getAirGun());
					this.airEnemys.add(this.airBoss.getAirFire());
				}
				else if(this.dto.getStage()==3 || this.dto.getStage()==4){
//					this.airEnemys.clear();
					this.airBoss = new AirBoss3(this);
					this.airEnemys.add(airBoss);
				}
			}
			
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void createMap(){
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length(); j++){
				char ch = map[i].charAt(j);
				switch(ch){
				case '_':
					break;
				case '0':
					this.obs.add(new Obstruction(ORINGINAL_X+PER_RECT*j, ORINGINAL_Y+PER_RECT*i));
					break;
				case '1':
					this.rocks.add(new Rock(ORINGINAL_X+PER_RECT*j, ORINGINAL_Y+PER_RECT*i));
					break;
				case 'r':
					this.enemys.add(new Enemy_Robot(ORINGINAL_X+PER_RECT*j, ORINGINAL_Y+PER_RECT*i, this));
					this.dto.setNumOfEnemy(dto.getNumOfEnemy()+1);
					map[i] = map[i].replace(j, j+1, "_");
					break;
				case 'g':
					this.enemys.add(new Enemy_Ghost(ORINGINAL_X+PER_RECT*j, ORINGINAL_Y+PER_RECT*i, this));
//					this.dto.setNumOfEnemy(dto.getNumOfEnemy()+1);
					map[i] = map[i].replace(j, j+1, "_");
					break;
				case 'c':
					this.clouds.add(new Cloud(ORINGINAL_X+PER_RECT*j, ORINGINAL_Y+PER_RECT*i));
					break;
				case 'e':
					this.airEnemys.add(new AirEnemy_BombMan0(ORINGINAL_X+PER_RECT*j, ORINGINAL_Y+PER_RECT*i, this));
					break;
				case 'a':
					this.airEnemys.add(new AirBomb(ORINGINAL_Y+PER_RECT*i, ORINGINAL_X+PER_RECT*j, this));
					break;
				case 'x':
					this.airEnemys.add(new AirEnemyXBullet(ORINGINAL_X+PER_RECT*j, ORINGINAL_Y+PER_RECT*i, this));
				}
			}
		}
	}
	
	public Vector<Obstruction> getObs() {
		return obs;
	}

	public Vector<Rock> getRocks() {
		return rocks;
	}

	public Vector<Bomb> getBombs() {
		return bombs;
	}

	public Vector<Fire> getFirs() {
		return firs;
	}

	public StringBuffer[] getMap() {
		return map;
	}

	public BombMan getBombMan() {
		return bombMan;
	}
	
	public Vector<Bullet> getBullets() {
		return bullets;
	}

	public GameDto getDto() {
		return dto;
	}

//	public void setGf(GameFrame gf) {
//		this.gf = gf;
//	}

	public boolean isAlive() {
		return isAlive;
	}

	public AirBoss getAirBoss() {
		return airBoss;
	}

	public Vector<AirEnemy> getAirEnemys() {
		return airEnemys;
	}

	protected void clearVector(){
		this.bombs.removeAllElements();
		this.firs.removeAllElements();
		this.obs.removeAllElements();
		this.rewards.removeAllElements();
		this.enemys.removeAllElements();
		this.airEnemys.removeAllElements();
	}

//	private void newStage(){
//		this.bombMan.resetXY();
//		this.dto.addStage(1);
//		this.map = MapLoader.getMap(dto.getStage(),false);
//		this.clearVector();
//		this.createMap();
//		this.bg.setBackGroundImage(dto.getStage(), false);
//	}

	public void setBg2(BackGround bg2) {
		this.bg2 = bg2;
	}

	public static Point computeXjAndYi(int x, int y){
		int j = (x - ORINGINAL_X)/PER_RECT;
		int i = (y - ORINGINAL_Y)/PER_RECT;
		return new Point(i,j);
	}

}
