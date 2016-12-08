package com.kitchen.model;

public class Ingredient {
	private int ingredient_id;
	private String kor_name;
	private String eng_names;
	private String img_location;
	private int type_id;
	
	public int getIngredient_id() {
		return ingredient_id;
	}
	public void setIngredient_id(int ingredient_id) {
		this.ingredient_id = ingredient_id;
	}
	public String getKor_name() {
		return kor_name;
	}
	public void setKor_name(String kor_name) {
		this.kor_name = kor_name;
	}
	public String getEng_names() {
		return eng_names;
	}
	public void setEng_names(String eng_names) {
		this.eng_names = eng_names;
	}
	public String getImg_location() {
		return img_location;
	}
	public void setImg_location(String img_location) {
		this.img_location = img_location;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	
}
