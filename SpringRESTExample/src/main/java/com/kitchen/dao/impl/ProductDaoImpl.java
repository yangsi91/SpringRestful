package com.kitchen.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitchen.dao.CookDao;
import com.kitchen.dao.ProductDao;
import com.kitchen.model.Cook;
import com.kitchen.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.ProductMapper";

	@Override
	public List<Product> getProductId(String name) {
		return sqlSession.selectList(namespace + ".getProductId", name);
	}
}
