package com.actions;

import com.entities.Character;
import com.entities.Furniture;

public class OpenFurniture implements IAction{
	private Character character;
	private Furniture furniture;
	
	public OpenFurniture(Character character, Furniture furniture) {
		this.character = character;
		this.furniture = furniture;
	}
	
	@Override
	public String getName() {
		return "OpenFurniture";
	}
	
	@Override
	public boolean getShouldWait() {
		return true;
	}

}
