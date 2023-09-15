package com.entities;

public class Character<BodyTypes> {
	private String charName;
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
		charName = "no name";
		bodyType = BodyType.A;
		clothing = Clothing.Bandit;
		hairStyle = Hairstyles.Long;
	}
	
	public Character(String name, BodyType bodytype) {
		charName = name;
		bodyType = bodytype;
	}
	
	public Character(String name, BodyType bodytype, Clothing clothingvar) {
		charName = name;
		bodyType = bodytype;
		clothing = clothingvar;
		//comment
		
	}
	
	public Character(String name, BodyType bodytype, Clothing clothingvar, Hairstyles hairstyle) {
		charName = name;
		bodyType = bodytype;
		clothing = clothingvar;
		hairStyle = hairstyle;
	}

}
