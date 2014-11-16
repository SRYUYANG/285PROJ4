package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import config.StaticImage;
import dto.GameDto;

public class Board {
	private static final int HEIGHT = 26;
	private static final int LEN = 400;
	private static final Font font = new Font("ºÚÌå", Font.BOLD, 20);
	
	public static void drawBoard(Graphics g, GameDto dto){
//		g.setColor(Color.BLACK);
//		g.fillRect(0, GamePanel.PANEL_H-HEIGHT, GamePanel.PANEL_W, HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("point "+dto.getPoint(),5,GamePanel.PANEL_H-HEIGHT+20);
		
		double tempPercent = ((double)dto.getBombManLife())/dto.getTotalBombManLife();
		int id =(int)(tempPercent * 225);
		id = id==0?1:id;
		g.drawImage(StaticImage.Rect.getSubimage(id, 0, 1, StaticImage.Rect.getHeight()),
				200, GamePanel.PANEL_H-(HEIGHT-10), (int)(tempPercent*LEN)+1, 10, null);
		
		g.drawImage(StaticImage.board, 4, GamePanel.PANEL_H-HEIGHT+4, GamePanel.PANEL_W, HEIGHT, null);
	}
}
