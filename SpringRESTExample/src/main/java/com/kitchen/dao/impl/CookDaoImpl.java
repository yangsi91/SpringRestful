package com.kitchen.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitchen.dao.CookDao;
import com.kitchen.model.Cook;

@Repository
public class CookDaoImpl implements CookDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.CookMapper";

	@Override
	public List<Cook> getListByRecipeId(int recipe_id) {
		return sqlSession.selectList(namespace + ".getListByRecipeId", recipe_id);
	}
}
