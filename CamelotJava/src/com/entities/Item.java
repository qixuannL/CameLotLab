package com.entities;

public class Item<Items> {
	private String itemName;
	private Items template;
	
	public enum Items {
		Apple, Bag, BlueBook, BlueCloth
	}
	
	public Item (String name, Items templatevar) {
		itemName = name;
		template = templatevar;
	}
	
	public String getName() {
		return itemName;
	}
	
	public Items getTemplate() {
		return template;
	}

}
