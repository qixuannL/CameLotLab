package com.entities;

import com.entities.Things.ThingNames;

public class Item implements IEntity, IThing<com.entities.Item.Items> {
	private ThingNames itemName;
	private Items template;
	
	public enum Items {
		Apple, Bag, BlueBook, BlueCloth, PurpleCloth, GoldCup, BluePotion, Sword, SpellBook
	}
	
	public Item (ThingNames sword, Items templatevar) {
		itemName = sword;
		template = templatevar;
	}
	
	public String getName() {
		return itemName.toString();
	}
	
	public Items getTemplate() {
		return template;
	}

}
