package config;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Sound implements Runnable{

	private String fileName;
	
//	public static void main(String[] args) {
//		Sound start = new Sound("sound/blast_liupao.wav");
//		new Thread(start).start();
//	}
	
	public Sound(String fileName){
		this.fileName = fileName;
	}
	public void run(){
		File f = new File(this.fileName);
		AudioInputStream ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(f);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		AudioFormat format = ais.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		
		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		auline.start();
		int n = 0;
		byte[] data = new byte[512];
		try {
			while(n != -1){
				n = ais.read(data, 0, data.length);
				if(n>=0)
					auline.write(data, 0, n);
			} 
		}catch (IOException e) {
				e.printStackTrace();
				return;
		}finally{
				auline.drain();
				auline.close();
		}
		}
}
