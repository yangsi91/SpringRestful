package com.kitchen.dao;

import java.util.HashMap;
import java.util.List;

import com.kitchen.model.Recipe;
import com.kitchen.model.RecipeFavorite;

public interface RecipeDao {
	public void insert(Recipe recipe);
	public Recipe getRecipe(int recipe_id);
//	public void update(Ingredient user);
	public void delete(int recipe_id);
	public List<Recipe> getList();
	public List<RecipeFavorite> getOuterjoinList(int product_id);
	public List<RecipeFavorite> getLeftouterjoinList(int product_id);
	public List<Recipe> getListByVision(String resultStr);
}
