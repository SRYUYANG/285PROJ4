package ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.StringShower;
import util.StringUtil;
import config.MapLoader;
import control.GameControler;
import entity.BombMan;
import entity.BombManPlane;

public class GameStartPanel extends JPanel implements Runnable,KeyListener{

	private static final long serialVersionUID = 1L;
	
	private GameControler gc;
	private int totalTime;
	private int useTime;
	private int stage;
	private boolean isAlive;
	private boolean isContinue;
	private StringShower stringShower;
	private BackGround bg;
	private BackGround bg2;
	private StringBuffer[] map;
	private MapLoader mapLoader;
	private JButton jbConfig;
	private JButton jbStart;
	
	public GameStartPanel(int stage, int totalTime, GameControler gc){
		this.isAlive = true;
		this.totalTime = totalTime;
		this.stage = stage;
		this.gc = gc;
		this.mapLoader = new MapLoader();
		this.map = new StringBuffer[9];
		this.map = this.mapLoader.getMap(stage);
		
		this.bg = new BackGround(stage, this.mapLoader.getSpeedXofRollMap(), this.mapLoader.getSpeedYofRollMap());
		if(stage == 2){
			this.bg2 = new BackGround(1, 0, 0);
		}else if(stage == 3){
			this.bg2 = new BackGround(1, 0, 0);
		}else if(stage == 4){
			this.bg2 = new BackGround(1, 0, 0);
			this.bg.setWidthOfShow(315);
			this.bg.setStopRolling(true);
		}
		BufferedImage paper = new BufferedImage(540,70,BufferedImage.TYPE_4BYTE_ABGR);
		paper = StringUtil.addStringToImage("MISSION", paper, 9);
		paper = StringUtil.addOneNumberToImage(stage, paper, 9);
		this.stringShower = new StringShower(16, 400, paper);
		
		this.initButton();
		
		new Thread(this).start();
	}
	
	private void initButton(){
		this.jbConfig = new JButton("config");
		this.jbConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gc.showFrameOfConfig();
			}
		});
		this.jbConfig.setBounds(0,0,
				GamePanel.PER_RECT,GamePanel.PER_RECT);
		this.add(jbConfig);
		
		this.jbStart = new JButton("start");
		this.jbConfig.setBounds(0,60,
				GamePanel.PER_RECT,GamePanel.PER_RECT);
		this.jbStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				keyPressed(null);
			}
		});
		this.add(jbStart);
	}
	
	@Override
	public void paintComponent(Graphics g0) {
		super.paintComponent(g0);
		BufferedImage image = new BufferedImage(GamePanel.PANEL_W+20, GamePanel.PANEL_H+20,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = image.getGraphics();
		if(this.bg2!=null) {
			this.bg2.drawBackGround(g, null);
		}
		this.bg.drawBackGround(g, null);
		bg.move();
		
		this.stringShower.drawString(g);
		
		g0.drawImage(image, 0, 0, this);
	}

	@Override
	public void run() {
		while(this.isAlive){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.repaint();
			
			if(this.useTime >= this.totalTime){
				this.isAlive = false;
				this.newStage();
			}
			
			if(this.isContinue){
				this.useTime++;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.isContinue = true;
		this.stringShower.setSpeedOfTimerChange(-1);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	public void newStage(){
		if(this.mapLoader.getSpeedXofRollMap()==0 && this.mapLoader.getSpeedYofRollMap()==0){
			this.gc.newStage(false, new BombMan(), this.map, this.bg, null);
		}else{
			//TODO
			//this.bg.setX(0);
			if(this.stage == 2){
				this.gc.newStage(true, new BombManPlane(0), this.map, this.bg, this.bg2);
			}
			else {
				if(this.stage == 4) { this.bg.setStopRolling(false); }
				this.gc.newStage(true, new BombManPlane(1), this.map, this.bg, this.bg2);
			}
		}
	}
}
