package util;

public class ImageIndexChanger {
	
	private int nowIndex;
	private int numOfImage;
	private int minIndex;
	private int maxIndex;
	private int timer;
	private int speedOfChange;
	private boolean isInverted;
	
	public ImageIndexChanger(int numOfImage){
		this.isInverted = false;
		this.numOfImage = numOfImage;
		this.minIndex = 0;
		this.timer = 0;
		this.speedOfChange = 1;
		this.reSetLimit();
	}
	
	public int nextIndexOfImage(){
		if(this.nowIndex > this.maxIndex){
			this.nowIndex = this.minIndex;
		}
		int tempIndex = this.nowIndex;
		this.changeNowIndex();
		
		if(!this.isInverted) {
			return tempIndex;
		}else {
			return this.numOfImage - tempIndex - 1;
		}
	}
	
	public void changeNowIndex(){
		if(this.timer == 0) {
			this.nowIndex++;
		}
		
		this.timer++;
		if(this.timer == this.speedOfChange) {
			this.timer = 0;
		}
	}
	
	public void setLimit(int begin, int end){
		this.minIndex = begin;
		this.maxIndex = end;
	}
	
	public void reSetLimit() {
		this.timer = 0;
		this.setLimit(0, this.numOfImage - 1);
	}
	
	public void setHowSlowToChange(int speed) {
		this.timer = 0;
		this.speedOfChange = speed;
	}

	public void setInverted(boolean isInverted) {
		this.isInverted = isInverted;
	}

	public void setNumOfImage(int numOfImage) {
		this.numOfImage = numOfImage;
		this.reSetLimit();
	}

}
