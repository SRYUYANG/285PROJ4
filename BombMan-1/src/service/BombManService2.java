package service;

import entity.BombMan;

public class BombManService2 implements GameService{

	private BombMan bombMan;
	
	public BombManService2(BombMan bombMan) {
		this.bombMan = bombMan;
	}
	@Override
	public boolean keyUp() {
		this.bombMan.setSpeedU(0);
		return false;
	}

	@Override
	public boolean keyDown() {
		this.bombMan.setSpeedD(0);
		return false;
	}

	@Override
	public boolean keyLeft() {
		this.bombMan.setSpeedL(0);
		return false;
	}

	@Override
	public boolean keyRight() {
		this.bombMan.setSpeedR(0);
		return false;
	}

	@Override
	public boolean keyUp2p() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDown2p() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyLeft2p() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyRight2p() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean GameFun1() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean GameFun2() {
		// TODO Auto-generated method stub
		return false;
	}

}
