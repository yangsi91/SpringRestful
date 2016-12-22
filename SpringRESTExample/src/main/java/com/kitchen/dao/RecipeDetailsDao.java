package com.kitchen.dao;

import java.util.List;

import com.kitchen.model.RecipeDetails;

public interface RecipeDetailsDao {
	public void insert(RecipeDetails recipeDetails);
	public RecipeDetails getData(int details_id);
//	public void update(Ingredient user);
	public void delete(int details_id);
	public List<RecipeDetails> getList();
}
