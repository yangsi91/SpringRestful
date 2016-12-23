package com.kitchen.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitchen.dao.FavoriteDao;
import com.kitchen.model.Favorite;

@Repository
public class FavoriteDaoImpl implements FavoriteDao{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.FavoriteMapper";
	
	@Override
	public void insert(Favorite favorite) {
		sqlSession.insert(namespace + ".insert", favorite);		
	}

	@Override
	public Favorite getData(int favorite_id) {
		return sqlSession.selectOne(namespace + ".getData", favorite_id);
	}

	@Override
	public void delete(Favorite favorite) {
		sqlSession.delete(namespace + ".delete", favorite);
	}

	@Override
	public List<Favorite> getList() {
		return sqlSession.selectList(namespace + ".getList");
	}
}
