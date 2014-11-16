package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerControl implements KeyListener{

	private GameControler gc;
	
	public PlayerControl(GameControler gameControl){
		this.gc = gameControl;
	}
	/**
	 * 键盘按下事件
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		this.gc.actionByKeyCodeWhenPress(e.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		this.gc.actionByKeyCodeWhenReleass(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
