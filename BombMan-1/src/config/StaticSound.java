package config;

public class StaticSound {
	
	public static Sound blastOfBomb;
	public static Sound blastOfBombss;
	public static Sound blastOfBombMan;
	public static Sound blastOfEnemyRobot;
	
	public static void initSound(){
//		blast = new Sound("sound/blast_liupao.wav");
//		blast = new Sound("sound/blast_water.wav");
		blastOfBomb = new Sound("sound/blast_zombi.wav");
		blastOfBombss = new Sound("sound/blast_long.wav");
		blastOfEnemyRobot = new Sound("sound/blast_water.wav");
	}
}
