package com.actions;


public class SetNarration implements IAction{
	
	public SetNarration(String string) {
		
	}
	//123
	@Override
	public String getName() {
		return "Dance";
	}
	
	@Override
	public boolean getShouldWait() {
		return true;
	}
}
