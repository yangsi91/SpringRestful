package com.kitchen.model;

public class RecipeFavorite {
	private int recipe_id;
	private String title;
	private String details;
	private int details_id;
	private String img_location;
	private int star;
	
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getDetails_id() {
		return details_id;
	}
	public void setDetails_id(int details_id) {
		this.details_id = details_id;
	}
	public String getImg_location() {
		return img_location;
	}
	public void setImg_location(String img_location) {
		this.img_location = img_location;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	
}
