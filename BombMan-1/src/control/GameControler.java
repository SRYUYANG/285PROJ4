package control;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import service.BombManService;
import service.BombManService2;
import ui.BackGround;
import ui.FrameOfConfig;
import ui.GameFrame;
import ui.GamePanel;
import ui.GamePresentComponent;
import ui.GameRollPanel;
import ui.GameStartPanel;
import dto.GameDto;
import entity.BombMan;

public class GameControler{

	private GameFrame gf;
	private FrameOfConfig frameOfConfig;
	private GameDto dto;
	private BombManService gameService;
	private BombManService2 gameService2;
	//��Ϸ��Ϊ�����֮���ӳ��
	private Map<Integer,Method> actionList;
	private Map<Integer,Method> actionList2;
	
	public GameControler() {
		this.dto = new GameDto();
		this.frameOfConfig = new FrameOfConfig();
		this.gf = new GameFrame();
		this.gf.createPresentPanel(new GamePresentComponent(this));
		//TODO
//		dto.addStage(1);
	}

	public void setNewStagePanel() {
		dto.addStage(1);
		dto.setTargerPoint(dto.getPoint()+500);
		if(this.gf.getMc() != null){
			this.gf.remove(this.gf.getMc());
		}
		if(this.gf.getGp() != null){
			this.gf.remove(this.gf.getGp());
		}
		this.gf.setGsp(new GameStartPanel(dto.getStage(), 16, this));
		this.gf.setContentPane(this.gf.getGsp());
		//�б�Ҫˢ��һ�£���Ȼ�Ļ�����ʾ,ʵ��֤����repaintЧ���ǳ���frame����ʲôҲ��������
		//this.repaint();
		this.gf.setVisible(true);	
		this.gf.addKeyListener(this.gf.getGsp());
		this.gf.requestFocus();
	}
	
	public void newStage(boolean isRollPanel,BombMan bombMan, StringBuffer[] map,
			BackGround bg, BackGround bg2){
		this.gf.remove(this.gf.getGsp());
		if(isRollPanel) {
			this.gf.setGp(new GameRollPanel(dto, bombMan, map, bg, bg2, this));
		}else {
			this.gf.setGp(new GamePanel(dto, bombMan, map, bg, bg2, this));
		}
		this.gameService = new BombManService(bombMan, dto);
		this.gameService2 = new BombManService2(bombMan);
		this.setControlConfig();
		this.gf.setContentPane(this.gf.getGp());
		this.gf.addKeyListener(new PlayerControl(this));
		//���ǳ���Ҫ�����ȱ�٣���ô�������갴��֮��gamePanelʧȥ���㣬bombMan���޷��ƶ�
		this.gf.getGp().requestFocus();
	}
	
	//��ʾ���ð�ť�����
	public void showFrameOfConfig() {
		this.frameOfConfig.setVisible(true);
	}
	
	//��ȡ�û���������
	private void setControlConfig(){
		//�����������뷽����֮���ӳ������
		this.actionList = new HashMap<Integer, Method>();
		this.actionList2 = new HashMap<Integer, Method>();

		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("config/control.cfg"));
			@SuppressWarnings("unchecked")
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>)ois.readObject();
			Set<Entry<Integer, String>> entrySet = cfgSet.entrySet();
			for(Entry<Integer, String> e : entrySet){
				actionList.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
				actionList2.put(e.getKey(), this.gameService2.getClass().getMethod(e.getValue()));
			}
			ois.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//������ҿ�����������Ϊ
	public void actionByKeyCodeWhenPress(int keyCode) {
		if(!this.gf.getGp().getBombMan().isCanMove() || this.dto.isGameOver()) {
			return;
		}
		try{
			if(this.actionList.containsKey(keyCode)){
				this.actionList.get(keyCode).invoke(this.gameService);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void actionByKeyCodeWhenReleass(int keyCode) {
		if(!this.gf.getGp().getBombMan().isCanMove() || this.dto.isGameOver()) {
			return;
		}
		try{
			if(this.actionList.containsKey(keyCode)){
				this.actionList2.get(keyCode).invoke(this.gameService2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
