package com.kitchen.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitchen.dao.RecipeDao;
import com.kitchen.model.Recipe;
import com.kitchen.model.RecipeFavorite;

@Repository
public class RecipeDaoImpl implements RecipeDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.RecipeMapper";
	
	@Override
	public void insert(Recipe recipe) {
		sqlSession.insert(namespace + ".insert", recipe);		
	}

	@Override
	public Recipe getRecipe(int recipe_id) {
		return sqlSession.selectOne(namespace + ".getData", recipe_id);
	}

	@Override
	public void delete(int recipe_id) {
		sqlSession.delete(namespace + ".delete", recipe_id);
	}

	@Override
	public List<Recipe> getList() {
		return sqlSession.selectList(namespace + ".getList");
	}

	@Override
	public List<RecipeFavorite> getOuterjoinList(int product_id) {
		return sqlSession.selectList(namespace + ".getOuterjoinList", product_id);
	}
}
