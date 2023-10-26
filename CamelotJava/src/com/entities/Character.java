package com.entities;

import com.entities.Things.ThingNames;

public class Character<BodyTypes>{
	private ThingNames charName;
	private BodyType bodyType;
	private Clothing clothing;
	private Hairstyles hairStyle;
	
	public enum BodyType {A, B, C, D, E, F, G, H} 
	
	public enum Clothing {
		Bandit, Beggar, LightArmour, HeavyArmour, Merchant, Noble, Peasant, Priest
	}
	
	public enum Hairstyles {Long, Spiky, Short} 
		
	public Clothing getTemplate() {
		return clothing;
	}
	
	public Hairstyles getHairstyles() {
		return hairStyle;
	}
	
	public Character() {
		charName = ThingNames.noname;
		bodyType = BodyType.A;
		clothing = Clothing.Bandit;
		hairStyle = Hairstyles.Long;
	}
	
	public Character(ThingNames name, BodyType bodytype) {
		charName = name;
		bodyType = bodytype;
	}
	
	public Character(ThingNames name, BodyType bodytype, Clothing clothingvar) {
		charName = name;
		bodyType = bodytype;
		clothing = clothingvar;
		//comment
		
	}
	
	public Character(ThingNames name, BodyType bodytype, Clothing clothingvar, Hairstyles hairstyle) {
		charName = name;
		bodyType = bodytype;
		clothing = clothingvar;
		hairStyle = hairstyle;
	}


	public ThingNames getName() {
		return charName;
	}
}
