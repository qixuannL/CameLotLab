package com.actions;

public class Dance implements IAction{
	public Dance(com.entities.Character charlotte) {
	}
	
	@Override
	public String getName() {
		return "Dance";
	}
	
	@Override
	public boolean getShouldWait() {
		return true;
	}
}
