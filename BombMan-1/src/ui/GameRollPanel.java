package ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

import control.GameControler;
import control.PlayerControl;
import dto.GameDto;
import entity.AirEnemy;
import entity.BombMan;
import entity.Cloud;
import entity.Enemy;
import entity.Fire;

public class GameRollPanel extends GamePanel{

	private static final long serialVersionUID = 1L;
	
	private static final int TIME_TO_CHANGE_PANEL = 50*4;
	private int s = GamePanel.PANEL_W;
	private Vector<Cloud> reClouds = new Vector<Cloud>();
	
	public GameRollPanel(GameDto dto, BombMan bombMan, StringBuffer[] map,
			BackGround bg, BackGround bg2, GameControler gc) {
		super(dto, bombMan, map, bg, bg2, gc);
	}

	@Override
	protected void addListener() {
		this.addKeyListener(new PlayerControl(this.gc));
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	private void moveOfObjects(int speed){
	
		int tempX = speed;
		
		for(Fire f : this.firs){
			f.changeX(tempX);
		}
		for(Enemy e : this.enemys){
			e.changeX(tempX);
		}
		for(AirEnemy airEn : this.airEnemys){
			synchronized (this.airEnemys) {
				airEn.move();	
			}
		}
		for(Cloud c : this.clouds){
			c.move();
		}
		bg.move();
		
		this.s += tempX;
		if(s<=0){
			this.resetAirEnemysAndClouds();
			s=GamePanel.PANEL_W<<1;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.moveOfObjects(-3);
		for(int i=0; i<clouds.size(); i++){
			Cloud c = clouds.get(i);
			if(c.isLive()){
				if(c.getX()<GamePanel.PANEL_W+GamePanel.PER_RECT){
					c.drawCloud(g);
				}
			}else{
				this.reClouds.add(c);
				this.clouds.remove(c);
			}
		}
		
		if(this.timer > 0){
			this.timer++;
		}
		
		if(this.timer == 3*(TIME_TO_CHANGE_PANEL >> 2)) {
			this.bombMan.setFallFromY(this.bombMan.getY(), 1);
		}
		
		if(this.timer > TIME_TO_CHANGE_PANEL) {
			this.isAlive = false;
//			this.gf.setNewStagePanel();
			this.gc.setNewStagePanel();
		}
	}
	
	private void resetAirEnemysAndClouds(){
		for(AirEnemy airEn : this.reAirEnemys){
			if(airEn.isBoss()){
				this.clearVector();
				this.changeDirectOfCloud();
				this.bombMan.setCanMove(false);
				this.timer = 1;
				this.reAirEnemys.clear();
				return;
			}
			if(!airEn.isBullet()){
				airEn.resetXY();
				this.airEnemys.add(airEn);
			}
		}
		this.reAirEnemys.clear();
		
		for(Cloud c : this.reClouds){
			c.resetXY(true);
			this.clouds.add(c);
		}
		
		this.reClouds.clear();
	}

	private void changeDirectOfCloud(){
		for(Cloud c : this.clouds) {
			c.resetXY(false);
		}
		for(Cloud c : this.reClouds) {
			c.resetXY(false);
			this.clouds.add(c);
		}
	}
}
