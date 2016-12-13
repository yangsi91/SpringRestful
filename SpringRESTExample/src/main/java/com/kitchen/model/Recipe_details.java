package com.kitchen.model;

public class Recipe_details {
	private int details_id;
	private int recipe_id;
	private int step;
	
	public int getDetails_id() {
		return details_id;
	}
	public void setDetails_id(int details_id) {
		this.details_id = details_id;
	}
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	private String contents; //이미지와 관련 설명이 들어가는 부분. 파일경로 링크 넘겨주기
	
}
