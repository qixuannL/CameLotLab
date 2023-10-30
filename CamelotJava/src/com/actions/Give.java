package com.actions;

import com.entities.Item;
import com.entities.Character;

public class Give implements IAction{
	private Character character;
	private Item thing;

	public Give(Character charlotte, Item bluepotion, Character villager) {
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
