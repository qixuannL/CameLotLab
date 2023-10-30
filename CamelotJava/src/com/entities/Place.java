package com.entities;

import com.entities.Things.ThingNames;

public class Place implements IThing<com.entities.Place.Places>{
	private ThingNames name;
	private Places template;
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
	public ThingNames getName() {
		return name.toString();
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
