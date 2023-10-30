package com.actions;

import com.entities.Effect;

public class CreateEffect implements IAction{
	private com.entities.Character character;
	private Effect effect;
	public enum Effect {Aura, Blood, Die, Poof, Blackflame, Death, Magic, Happy}
	
	public CreateEffect(com.entities.Character character, Effect effect) {
		this.character = character;
		this.effect = effect;
	}
	
	@Override
	public String getName() {
		return "CreateEffect";
	}
	
	@Override
	public boolean getShouldWait() {
		return true;
	}

}
