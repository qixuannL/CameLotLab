package com.actions;

import com.entities.Item;

public class Give implements IAction{
	private Character character;
	private Item thing;

	public Give(com.entities.Character charlotte, Item bluepotion, com.entities.Character villager) {
		// TODO Auto-generated constructor stub
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
