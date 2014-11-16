package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {

	public static StringBuffer[] map;
	public static int lenOfMap = 9;
	private int speedXofRollMap;
	private int speedYofRollMap;
	
	public StringBuffer[] getMap(int idx){
		
		FileReader fr = null;
		BufferedReader br = null;
		map = new StringBuffer[lenOfMap];
		try {
			fr = new FileReader("map/"+idx+".txt");
			br = new BufferedReader(fr);
			
			String str = "";
			int i = 0;
			str = br.readLine();
			String string[] = str.split(" ");
			this.speedXofRollMap = Integer.parseInt(string[0]);
			this.speedYofRollMap = Integer.parseInt(string[1]);
	
			while((str=br.readLine())!=null){
				map[i++] = new StringBuffer(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		return map;
	}

	public int getSpeedXofRollMap() {
		return speedXofRollMap;
	}

	public int getSpeedYofRollMap() {
		return speedYofRollMap;
	}
	
}
