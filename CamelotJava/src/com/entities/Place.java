package com.entities;

import com.entities.Things.ThingNames;

public class Place<Places> implements IThing<Places>{
	private String name;
	private T template;
	private Furniture furniture;

	public enum Places{
		AlchemyShop,Blacksmith,Bridge,Camp,CastleBedroom,CastleCrossroads,City,Cottage,Courtyard,DiningRoom,Dungeon,Farm,ForestPath,
		GreatHall,Hallway,Library,Port,Ruins,SpookyPath,Storage,Tavern
	}
	
	public Place(ThingNames city, Places template) {
		this.name = city;
		this.template = template;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public T getTemplate() {
		return template;
	}

	public Furniture getFurniture(String name) {
		furniture = new Furniture(name);
		return furniture;
		
	}

}
