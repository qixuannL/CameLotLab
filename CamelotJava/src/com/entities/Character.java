package com.entities;

import com.actions.Dance;
import com.entities.Things.ThingNames;

public class Character implements IEntity, IThing<com.entities.Character.BodyTypes> {
	private ThingNames charName;
	private BodyTypes bodyType;
	private Clothing clothing;
	private Hairstyles hairStyle;
	
	public enum BodyTypes {A, B, C, D, E, F, G, H} 
	
	public enum Clothing {
		Bandit, Beggar, LightArmour, HeavyArmour, Merchant, Noble, Peasant, Priest
	}
	
	public enum Hairstyles {Long, Spiky, Short} 
		
	public BodyTypes getTemplate() {
		return bodyType;
	}
	
	public Hairstyles getHairstyles() {
		return hairStyle;
	}
	
	public Character() {
		charName = ThingNames.noname;
		bodyType = BodyTypes.A;
		clothing = Clothing.Bandit;
		hairStyle = Hairstyles.Long;
	}
	
	public Character(ThingNames name, BodyTypes bodytype) {
		charName = name;
		bodyType = bodytype;
	}
	
	public Character(ThingNames name, BodyTypes bodytype, Clothing clothingvar) {
		charName = name;
		bodyType = bodytype;
		clothing = clothingvar;
		//comment
		
	}
	
	public Character(ThingNames name, BodyTypes bodytype, Clothing clothingvar, Hairstyles hairstyle) {
		charName = name;
		bodyType = bodytype;
		clothing = clothingvar;
		hairStyle = hairstyle;
	}


	public String getName() {
		return charName.toString();
	}
}
