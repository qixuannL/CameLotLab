package com.actions;
import com.entities.Character;

public class Die implements IAction{
	private Character character;
	
	public Die(Character enemy) {
		this.character = enemy;
	}
	
	@Override
	public String getName() {
		return "Die";
	}
	
	@Override
	public boolean getShouldWait() {
		return true;
	}
}
