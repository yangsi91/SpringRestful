package com.kitchen.dao;

import java.util.List;

import com.kitchen.model.IngredientImg;

public interface IngredientImgDao {
	public void insert(IngredientImg ingredientImg);
	public IngredientImg getIngredientImg(int img_id);
//	public void update(Ingredient user);
	public void delete(int img_id);
	public List<IngredientImg> getList();
}
