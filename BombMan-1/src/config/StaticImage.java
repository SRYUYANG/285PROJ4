package config;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticImage {

	public static BufferedImage Obstruction = null;
	public static BufferedImage Roof = null;
	public static BufferedImage Letter = null;
	public static BufferedImage Number = null;
	public static BufferedImage Rect = null;
	public static BufferedImage[] BombManPlane = new BufferedImage[2];
	public static BufferedImage[] String = new BufferedImage[2];
	public static BufferedImage[] BombMans = new BufferedImage[4];
	public static BufferedImage[] Bomb = new BufferedImage[3];
	public static BufferedImage[] Bullet = new BufferedImage[1];
	public static BufferedImage[] Cloud = new BufferedImage[4];
	public static BufferedImage[] Fire = new BufferedImage[6];
	public static BufferedImage Reward = null;
	public static List<BufferedImage> Enemys = new ArrayList<BufferedImage>();
	public static List<BufferedImage> bg = new ArrayList<BufferedImage>();
	public static List<BufferedImage> bg_roll = new ArrayList<BufferedImage>();
	public static BufferedImage board = null;
	
	public static void initImage(){
		try {
			Obstruction = ImageIO.read(new File("img/ob.jpg"));
			Reward = ImageIO.read(new File("img/reward.png"));
			board = ImageIO.read(new File("img/board.png"));
			Roof = ImageIO.read(new File("img/roof.png"));
			Letter = ImageIO.read(new File("img/letter.png"));
			Number = ImageIO.read(new File("img/num.png"));
			Rect = ImageIO.read(new File("img/rect.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(int i=0; i<6; i++){
			try {
				Fire[i] = ImageIO.read(new File("img/fire/"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<2; i++){
			try {
				BombManPlane[i] = ImageIO.read(new File("img/bombManPlane/"+(i+1)+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<2; i++){
			try {
				String[i] = ImageIO.read(new File("img/string/"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<4; i++){
			try {
				BombMans[i] = ImageIO.read( new File("img/bombman/"+i+".png") );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<7; i++){
			try {
				bg.add(ImageIO.read( new File("img/bg/"+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<4; i++){
			try {
				bg_roll.add(ImageIO.read(new File("img/bg/roll"+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<3; i++){
			try {
				Bomb[i] = ImageIO.read(new File("img/bomb/"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<1; i++){
			try {
				Bullet[i] = ImageIO.read(new File("img/bullet/"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<4; i++){
			try {
				Cloud[i] = ImageIO.read(new File("img/cloud/"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<16; i++){
			try {
				Enemys.add(ImageIO.read(new File("img/enemy/"+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
