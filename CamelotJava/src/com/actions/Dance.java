package com.actions;
import com.entities.Character;

public class Dance implements IAction{
	public Dance(Character charlotte) {
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
