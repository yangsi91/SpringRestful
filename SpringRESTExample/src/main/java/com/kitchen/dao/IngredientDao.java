package com.kitchen.dao;

import java.util.HashMap;
import java.util.List;

import com.kitchen.model.Ingredient;

public interface IngredientDao {
	public void insert(Ingredient ingredient);
	public Ingredient getIngredient(int ingredient_id);
//	public void update(Ingredient user);
	public void delete(int ingredient_id);
	public List<Ingredient> getList();
	public List<Ingredient> getListByType();
	public List<Ingredient> searchIngredient(String ingredientStr);
}
