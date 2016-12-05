package com.kitchen.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitchen.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.UserMapper";

	@Override
	public void register(User vo) {
		sqlSession.insert(namespace + ".register", vo);
	}

	@Override
	public User get(String userid) {
		return sqlSession.selectOne(namespace + ".get", userid);
	}

	@Override
	public List<User> getList() {
		return sqlSession.selectList(namespace + ".getList");
	}

}// class
