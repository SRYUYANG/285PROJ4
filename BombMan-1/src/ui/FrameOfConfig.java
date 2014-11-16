package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

public class FrameOfConfig extends JFrame{

	private static final long serialVersionUID = 1L;

	private static final String PATH = "config/control.cfg";

	private JButton btnOk = new JButton ("确定");
	
	private JButton btnCancel = new JButton("取消");
	
	private JButton btnUser = new JButton("应用");
	
	private TextCtrl[] keyTexts = new TextCtrl[10];
	
	private JLabel errorMsg = new JLabel();
	
//	public static void main(String[] args) {
//		FrameOfConfig test = new FrameOfConfig();
//		test.setVisible(true);
//	}
	
	private static final String[] keyInfo = {
		"1p右移","1p上移","1p左移","1p下移","1p发射子弹","2p右移","2p上移","2p左移","2p下移","2p发射子弹"
	};
	
//	private  static JLabel[] info;
//	static {
//		info = new JLabel[4];
//		info[0] = new JLabel("向上移动");
//		info[1] = new JLabel("向下移动");
//		info[2] = new JLabel("向左移动");
//		info[3] = new JLabel("向右移动");
//	}
	
//	private JLabel test = new JLabel("hahaha");
	
//	private GameControl gameControl;
	
	private final static String[] METHOD_NAMES = {
		"keyRight" , "keyUp" , "keyLeft" , "keyDown","GameFun1",
		"keyLeft2p" , "keyUp2p" , "keyRight2p" , "keyDown2p", "GameFun2"
	};
	
	public FrameOfConfig(){
		
		//设置布局管理器为“边界布局”
		this.setLayout(new BorderLayout());
		//设置标题
		this.setTitle("设置");
		//初始化按键输入框
		this.initKeyTexts();
		//添加主面板
		this.add(createMainPanel(), BorderLayout.CENTER);
		//添加按钮面板
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		//设置面板大小
		this.setSize(400, 300);
		//使居中
//		FrameUtil.setFrameCenter(this);
	}

	private void initKeyTexts() {
		int w = 64;
		int h = 20;
		int x = 14;
		int y = 50;
		
		for(int i = 0; i < 5; i++){
			keyTexts[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 30;
//			info[i].setForeground(Color.RED);
//			info[i].setBounds(x+68, y, w, h);
		}
		x = 300;
		y = 50;
		for(int i = 5;i < 10; i++){
			keyTexts[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 30;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
			
			@SuppressWarnings("unchecked")
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>)ois.readObject();
			
			Set<Entry<Integer, String>> entrySet = cfgSet.entrySet();
			
			for(Entry<Integer, String> e : entrySet){
				for(TextCtrl tc : keyTexts){
					if(tc.getMethodName().equals(e.getValue())){
						tc.setKeyCode(e.getKey());
					}
				}
				//e.getKey();
				//e.getValue();
			}

			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	private JPanel createMainPanel(){
		JPanel jp = new JPanel(){
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g){
				int x = 14;
				int y = 50;
				
				for(int i = 0; i < 5; i++){
					y += 30;
					g.drawString(keyInfo[i], x+72, y-16);
				}
				x = 300;
				y = 50;
				for(int i = 5;i < 10; i++){
					y += 30;
					g.drawString(keyInfo[i],x-60, y-16);
			
				}
			}
		};
		//设置布局管理器
		jp.setLayout(null);
		for(int i = 0; i< keyTexts.length; i++){
			jp.add(keyTexts[i]);
		}
		return jp;
	}
	//创建按钮面板
	private JPanel createButtonPanel(){
		//创建按钮面板，采用流式布局
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		//确认按钮（写入，并关闭窗口）
		this.btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//设置成功才允许关闭窗口
				if(writeConfig()){
					setVisible(false);
				}
			}
		});

		//添加出错提示语句
		this.errorMsg.setForeground(Color.RED);
		jp.add(this.errorMsg);

		jp.add(this.btnOk);

		//取消按钮（不写入，只关闭窗口）
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		jp.add(this.btnCancel);

		//应用按钮（写入，但不关闭窗口）
		this.btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeConfig();
			}
		});
		jp.add(this.btnUser);

		return jp;
	}
	
	private boolean writeConfig(){
		HashMap<Integer, String> keySet = new HashMap<Integer, String>();
		for(int i = 0; i < this.keyTexts.length; i++){
			int keyCode = this.keyTexts[i].getKeyCode();
			if(keyCode == 0){
				this.errorMsg.setText("无效按键");
				return false;
			}
			
			keySet.put(keyCode, this.keyTexts[i].getMethodName());
		}
		
		if(keySet.size() != 10) {
			this.errorMsg.setText("按键重复");
			return false;
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
			//System.out.println("111");
		} catch (Exception e) {
			e.printStackTrace();
			this.errorMsg.setText(e.getMessage());
			return false;
		}
		
		this.errorMsg.setText(null);
		return true;
	}
}

class TextCtrl extends JTextArea{

	private static final long serialVersionUID = 1L;
	
	private int keyCode;
	private String methodName;
	
	public TextCtrl(int x, int y, int w, int h, String methodName){
		//设置文本框位置
		this.setBounds(x, y, w, h);
		//初始化方法名
		this.methodName = methodName;
		//添加事件监听
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				//匿名内部类里面不加this
				setKeyCode(e.getKeyCode());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}
	
	public void setKeyCode(int keyCode){
		this.keyCode = keyCode;
		this.setText( KeyEvent.getKeyText(keyCode) );
	}
	
	public int getKeyCode(){
		return this.keyCode;
	}
	
	public String getMethodName(){
		return this.methodName;
	}
}
