package dto;


public class GameDto {
	private int point;
	private int stage;
	private int bombManLife;
	private int totalBombManLife;
	private int numOfEnemy;
	private boolean isGameOver;
	private boolean isPause;
	private int targerPoint;
	
	public void setBombManLife(int bombManLife) {
		this.bombManLife = bombManLife;
	}

	public int getTargerPoint() {
		return targerPoint;
	}

	public void setTargerPoint(int targerPoint) {
		this.targerPoint = targerPoint;
	}

	public GameDto(){
		this.initDto();
	}
	
	public void initDto(){
		this.point = 0;
		this.numOfEnemy = 0;
		this.stage = 0;
	}

	public void addPoint(int point){
		this.point += point;
	}
	
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getStage() {
		return stage;
	}

	public void addStage(int stage) {
		this.stage += stage;
	}

	public void setNumOfEnemy(int numOfEnemy) {
		this.numOfEnemy = numOfEnemy;
	}

	public int getNumOfEnemy() {
		return numOfEnemy;
	}

	public int getBombManLife() {
		return bombManLife;
	}

	public void setTotalBombManLife(int totalBombManLife) {
		if(this.bombManLife==0){
			this.bombManLife = totalBombManLife;
		}
		this.totalBombManLife = totalBombManLife;
	}

	public void addBombManLife(int addLife) {
		if(this.bombManLife + addLife>=0){
			this.bombManLife += addLife;
		}
	}

	public int getTotalBombManLife() {
		return totalBombManLife;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause() {
		this.isPause = !this.isPause;
	}
	
}
