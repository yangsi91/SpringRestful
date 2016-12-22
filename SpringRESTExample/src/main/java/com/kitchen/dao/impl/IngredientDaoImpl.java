package com.kitchen.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitchen.dao.IngredientDao;
import com.kitchen.model.Ingredient;

@Repository
public class IngredientDaoImpl implements IngredientDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.IngredientMapper";

	@Override
	public void insert(Ingredient ingredient) {
		sqlSession.insert(namespace + ".insert", ingredient);
	}
	
	@Override
	public Ingredient getIngredient(int ingredient_id) {
		return sqlSession.selectOne(namespace + ".getIngredient", ingredient_id);
	}
	
	@Override
	public void delete(int ingredient_id) {
		sqlSession.delete(namespace + ".delete", ingredient_id);
	}
	
	@Override
	public HashMap<String, List<Ingredient>> getList(){
		HashMap<String, List<Ingredient>> hm = new HashMap<String, List<Ingredient>>();
		List<Ingredient> list = sqlSession.selectList(namespace + ".getList");
		hm.put("list", list);
		return hm;
	}
}
