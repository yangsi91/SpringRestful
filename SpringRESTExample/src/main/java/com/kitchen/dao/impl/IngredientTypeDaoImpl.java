package com.kitchen.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitchen.dao.IngredientTypeDao;
import com.kitchen.model.IngredientType;

@Repository
public class IngredientTypeDaoImpl implements IngredientTypeDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.IngredientTypeMapper";

	@Override
	public int insert(IngredientType ingredientType) {
		return sqlSession.insert(namespace + ".insert", ingredientType);			
	}

	@Override
	public IngredientType getData(int type_id) {
		return sqlSession.selectOne(namespace + ".getData", type_id);
	}

	@Override
	public void delete(IngredientType ingredientType) {
		sqlSession.delete(namespace + ".delete", ingredientType);		
	}

	@Override
	public List<IngredientType> getList() {
		return sqlSession.selectList(namespace + ".getList");
	}
}
