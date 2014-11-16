package main;

import config.StaticImage;
import control.GameControler;

public class Main {
	public static void main(String[] args) {
		StaticImage.initImage();
//		StaticSound.initSound();
		
		new GameControler();
	}
}
