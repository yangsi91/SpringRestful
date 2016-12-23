package com.kitchen.model;

public class IngredientType {
	private int type_id;
	private String name;
	private String img_location;
	private String created;
	private String updated;
	
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getImg_location() {
		return img_location;
	}
	public void setImg_location(String img_location) {
		this.img_location = img_location;
	}
}
