package com.entities;

import com.entities.Things.ThingNames;

public class Item implements IEntity, IThing<com.entities.Item.Items> {
	private String itemName;
	private Items template;
	
	public enum Items {
		Apple, Bag, BlueBook, BlueCloth, PurpleCloth, GoldCup, BluePotion, Sword, SpellBook
	}
	
	public Item (String bluepotion, Items templatevar) {
		itemName = bluepotion;
		template = templatevar;
	}
	
	public String getName() {
		return itemName;
	}
	
	public Items getTemplate() {
		return template;
	}

}
