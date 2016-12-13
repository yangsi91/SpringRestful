package com.kitchen.model;

public class Ingredient_handling {
	private int handling_id;
	private int ingredient_id;
	private String name;
	private String img_location;
	private String created;
	private String updated;
	
	public int getHandling_id() {
		return handling_id;
	}
	public void setHandling_id(int handling_id) {
		this.handling_id = handling_id;
	}
	public int getIngredient_id() {
		return ingredient_id;
	}
	public void setIngredient_id(int ingredient_id) {
		this.ingredient_id = ingredient_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg_location() {
		return img_location;
	}
	public void setImg_location(String img_location) {
		this.img_location = img_location;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
}
