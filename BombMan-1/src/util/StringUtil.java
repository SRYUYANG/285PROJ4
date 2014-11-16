package util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import config.StaticImage;

public class StringUtil {
	
	public static BufferedImage addStringToImage(String str, BufferedImage paper, int numOfRect){
		BufferedImage img = paper;
		BufferedImage letterImg = StaticImage.Letter;
		int paperW = img.getWidth()/numOfRect;
		int paperH = img.getHeight();
		int perW = letterImg.getWidth()/26;
		int perH = letterImg.getHeight();
		
		Graphics gi = img.getGraphics();
		str = str.toUpperCase();
		char[] chs = str.toCharArray();
		for(int i=0; i<chs.length; i++){
			if(chs[i] == ' '){continue;}
			int id = (int)chs[i] - 65;
			gi.drawImage(letterImg.getSubimage(id*perW, 0, perW, perH),
					i*paperW, 0, paperW, paperH, null);
		}
		return img;
	}
	
	public static BufferedImage addOneNumberToImage(int num, BufferedImage paper, int numOfRect){
		BufferedImage numberImg = StaticImage.Number;
		Graphics gi = paper.getGraphics();
		int paperH = paper.getHeight();
		int paperW = paper.getWidth();
		int paperPerW = paperW/numOfRect;
		int perW = numberImg.getWidth()/12;
		int perH = numberImg.getHeight();
		gi.drawImage(numberImg.getSubimage(num*perW, 0, perW, perH),
				paperW-paperPerW, 0, paperPerW, paperH, null);
		return paper;
	}
}
