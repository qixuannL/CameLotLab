package com.actions;

public class Wave implements IAction {
	private Character character;
	
	public Wave(Character character){
		this.character = character;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getShouldWait() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
