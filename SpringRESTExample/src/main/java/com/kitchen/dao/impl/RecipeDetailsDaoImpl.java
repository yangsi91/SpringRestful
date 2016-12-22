package com.kitchen.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitchen.dao.RecipeDetailsDao;
import com.kitchen.model.RecipeDetails;

@Repository
public class RecipeDetailsDaoImpl implements RecipeDetailsDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.RecipeDetailsMapper";

	@Override
	public void insert(RecipeDetails recipeDetails) {
		sqlSession.insert(namespace + ".insert", recipeDetails);
	}

	@Override
	public RecipeDetails getData(int details_id) {
		return sqlSession.selectOne(namespace + ".getData", details_id);
	}

	@Override
	public void delete(int details_id) {
		sqlSession.delete(namespace + ".delete", details_id);
	}

	@Override
	public List<RecipeDetails> getList() {
		return sqlSession.selectList(namespace + ".getList");
	}
}
