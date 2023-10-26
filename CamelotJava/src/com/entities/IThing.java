package com.entities;

import com.entities.Place.Places;
import com.entities.Things.ThingNames;

public interface IThing<T> {
	public String getName();
	public T getTemplate();
}
