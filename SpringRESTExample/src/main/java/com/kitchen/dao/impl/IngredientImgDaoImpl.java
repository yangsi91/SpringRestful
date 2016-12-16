package com.kitchen.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitchen.dao.IngredientDao;
import com.kitchen.dao.IngredientImgDao;
import com.kitchen.model.Ingredient;
import com.kitchen.model.IngredientImg;

@Repository
public class IngredientImgDaoImpl implements IngredientImgDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.IngredientImgMapper";

	@Override
	public void insert(IngredientImg ingredientImg) {
		sqlSession.insert(namespace + ".insert", ingredientImg);		
	}

	@Override
	public IngredientImg getIngredientImg(int img_id) {
		return sqlSession.selectOne(namespace + ".getData", img_id);
	}

	@Override
	public void delete(int img_id) {
		sqlSession.delete(namespace + ".delete", img_id);
		
	}

	@Override
	public List<IngredientImg> getList() {
		return sqlSession.selectList(namespace + ".getList");
	}

}
