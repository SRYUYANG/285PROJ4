package service;

import dto.GameDto;
import entity.BombMan;

public class BombManService implements GameService{

	private BombMan bombMan;
	private GameDto dto;
	
	public BombManService(BombMan bombMan, GameDto dto) {
		this.bombMan = bombMan;
		this.dto = dto;
	}
	
	@Override
	public boolean keyUp() {
		this.bombMan.moveUp();
		return false;
	}

	@Override
	public boolean keyDown() {
		this.bombMan.moveDown();
		return false;
	}

	@Override
	public boolean keyLeft() {
		this.bombMan.moveLeft();
		return false;
	}

	@Override
	public boolean keyRight() {
		this.bombMan.moveRight();
		return false;
	}

	@Override
	public boolean keyUp2p() {
		// TODO Auto-generated method stub
		this.dto.setPause();
		return false;
	}

	@Override
	public boolean keyDown2p() {
		this.dto.setBombManLife(10);
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
		this.bombMan.setCanShoot(true);
		return false;
	}

	@Override
	public boolean GameFun2() {
		// TODO Auto-generated method stub
		return false;
	}

}
