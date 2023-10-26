package com.actions;

public class ShowDialog implements IAction{

	public ShowDialog(boolean b) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "ShowDialog";
	}
	
	@Override
	public boolean getShouldWait() {
		return true;
	}
}
