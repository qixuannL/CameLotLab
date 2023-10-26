package com.actions;

public class Attack implements IAction{
	private Character attacker;
	private Character victim;
	private boolean attacked;
	
	public Attack(com.entities.Character charlotte, com.entities.Character enemy, boolean attacked) {
		this.attacker = charlotte;
		this.victim = enemy;
		this.attacked = attacked;
	}
	
	@Override
	public String getName() {
		return "Attack";
	}
	
	public boolean getShouldWait() {
		return true;
	}

}
