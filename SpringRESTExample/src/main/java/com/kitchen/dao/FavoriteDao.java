package com.kitchen.dao;

import java.util.List;

import com.kitchen.model.Favorite;

public interface FavoriteDao {
	public void insert(Favorite favorite);
	public Favorite getData(int favorite_id);
//	public void update(Ingredient user);
	public void delete(int favorite_id);
	public List<Favorite> getList();
}
