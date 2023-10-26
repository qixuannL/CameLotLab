package com.actions;

public class Drink implements IAction {
	Character character;

	public Drink(com.entities.Character charlotte) {
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
