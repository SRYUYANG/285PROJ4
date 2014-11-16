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

	private JButton btnOk = new JButton ("ȷ��");
	
	private JButton btnCancel = new JButton("ȡ��");
	
	private JButton btnUser = new JButton("Ӧ��");
	
	private TextCtrl[] keyTexts = new TextCtrl[10];
	
	private JLabel errorMsg = new JLabel();
	
//	public static void main(String[] args) {
//		FrameOfConfig test = new FrameOfConfig();
//		test.setVisible(true);
//	}
	
	private static final String[] keyInfo = {
		"1p����","1p����","1p����","1p����","1p�����ӵ�","2p����","2p����","2p����","2p����","2p�����ӵ�"
	};
	
//	private  static JLabel[] info;
//	static {
//		info = new JLabel[4];
//		info[0] = new JLabel("�����ƶ�");
//		info[1] = new JLabel("�����ƶ�");
//		info[2] = new JLabel("�����ƶ�");
//		info[3] = new JLabel("�����ƶ�");
//	}
	
//	private JLabel test = new JLabel("hahaha");
	
//	private GameControl gameControl;
	
	private final static String[] METHOD_NAMES = {
		"keyRight" , "keyUp" , "keyLeft" , "keyDown","GameFun1",
		"keyLeft2p" , "keyUp2p" , "keyRight2p" , "keyDown2p", "GameFun2"
	};
	
	public FrameOfConfig(){
		
		//���ò��ֹ�����Ϊ���߽粼�֡�
		this.setLayout(new BorderLayout());
		//���ñ���
		this.setTitle("����");
		//��ʼ�����������
		this.initKeyTexts();
		//��������
		this.add(createMainPanel(), BorderLayout.CENTER);
		//��Ӱ�ť���
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		//��������С
		this.setSize(400, 300);
		//ʹ����
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
		//���ò��ֹ�����
		jp.setLayout(null);
		for(int i = 0; i< keyTexts.length; i++){
			jp.add(keyTexts[i]);
		}
		return jp;
	}
	//������ť���
	private JPanel createButtonPanel(){
		//������ť��壬������ʽ����
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		//ȷ�ϰ�ť��д�룬���رմ��ڣ�
		this.btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//���óɹ�������رմ���
				if(writeConfig()){
					setVisible(false);
				}
			}
		});

		//��ӳ�����ʾ���
		this.errorMsg.setForeground(Color.RED);
		jp.add(this.errorMsg);

		jp.add(this.btnOk);

		//ȡ����ť����д�룬ֻ�رմ��ڣ�
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		jp.add(this.btnCancel);

		//Ӧ�ð�ť��д�룬�����رմ��ڣ�
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
				this.errorMsg.setText("��Ч����");
				return false;
			}
			
			keySet.put(keyCode, this.keyTexts[i].getMethodName());
		}
		
		if(keySet.size() != 10) {
			this.errorMsg.setText("�����ظ�");
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
		//�����ı���λ��
		this.setBounds(x, y, w, h);
		//��ʼ��������
		this.methodName = methodName;
		//����¼�����
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				//�����ڲ������治��this
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
