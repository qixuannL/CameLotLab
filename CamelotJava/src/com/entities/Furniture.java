package com.entities;

public class Furniture implements IEntity{
	private String name;
	
	
	public Furniture(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	

}
