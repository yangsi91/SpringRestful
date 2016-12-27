package com.kitchen.dao;

import java.util.List;

import com.kitchen.model.Cook;

public interface CookDao {
	public List<Cook> getListByRecipeId(int recipe_id);
}
