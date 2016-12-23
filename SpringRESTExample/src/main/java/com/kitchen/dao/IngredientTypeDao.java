package com.kitchen.dao;

import java.util.List;

import com.kitchen.model.IngredientType;

public interface IngredientTypeDao {
	public int insert(IngredientType ingredientType);
	public IngredientType getData(int type_id);
//	public void update(Ingredient user);
	public void delete(IngredientType ingredientType);
	public List<IngredientType> getList();
}
