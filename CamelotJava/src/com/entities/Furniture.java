package com.entities;

public class Furniture implements IEntity{
	private String name;
	private Furniture furniture;
	
	public Furniture(String name) {
		this.name = name;
	}

	public Furniture getFurniture(String name) {
		furniture = new Furniture(name);
		return furniture;
		
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	

}
