package com.kitchen.model;

public class Cook {
	private int recipe_id;
	private int ingredient_id;
	private int amount;
	private int handling_id;
	private String measurement;
	private String created;
	private String updated;
	
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public int getIngredient_id() {
		return ingredient_id;
	}
	public void setIngredient_id(int ingredient_id) {
		this.ingredient_id = ingredient_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getHandling_id() {
		return handling_id;
	}
	public void setHandling_id(int handling_id) {
		this.handling_id = handling_id;
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
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
}
