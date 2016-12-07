package com.kitchen.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import com.kitchen.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.UserMapper";

	@Override
	public void insert(User user) {
		sqlSession.insert(namespace + ".insert", user);
	}

	@Override
	public User getUser(String userid) {
		return sqlSession.selectOne(namespace + ".getUser", userid);
	}

	@Override
	public List<User> getList() {
		return sqlSession.selectList(namespace + ".getList");
	}

	@Override
	public void update(User user) {
		sqlSession.update(namespace + ".updateName", user);
		
	}

	@Override
	public void delete(String userid) 
	{
		sqlSession.delete(namespace + ".delete", userid);
	}

}// class
