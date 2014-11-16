package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame{

	private static final long serialVersionUID = -1731679911424005114L;
	private GamePresentComponent mc;
	private static final int FRAME_W = 740-10;
	private static final int FRAME_H = 575-8;

	private GamePanel gp;
	private GameStartPanel gsp;
	private JPanel presetnPanel;
	
	public GameFrame(){
		this.setTitle("BombGame ver_2.0");
		this.setSize(FRAME_W, FRAME_H);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screen.width - FRAME_W >> 1, (screen.height - FRAME_H >> 1) - 32);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
//		this.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent e) {
//				super.windowOpened(e);
//				new Thread(mc).start();
//			}
//		});

//		dto = new GameDto();
//		dto.initDto();

		this.setVisible(true);
	}
	
	public void createPresentPanel(GamePresentComponent mc) {
		this.presetnPanel = new JPanel();
		this.presetnPanel.add(mc);
		this.presetnPanel.setBackground(Color.BLACK);
		this.setContentPane(this.presetnPanel);
		this.addKeyListener(mc);
	}
	
	public GamePanel getGp() {
		return gp;
	}

	public GameStartPanel getGsp() {
		return gsp;
	}

	public void setGp(GamePanel gp) {
		this.gp = gp;
	}

	public void setGsp(GameStartPanel gsp) {
		this.gsp = gsp;
	}

	public GamePresentComponent getMc() {
		return mc;
	}

//	public void setMc(GamePresentComponent mc) {
//		this.mc = mc;
//	}

}
