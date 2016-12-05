package com.kitchen.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import com.kitchen.model.User;

// GenericDAO 인터페이스의 추상클래스들을 오버라이딩 해주어야 한다.
// 즉, 여기서 DB와 연결을 하고 SQL문을 처리해주어야 하므로 sessionTemplate을 인스턴스 변수로 갖고서 
// 이를 통해 CRUD 작업을 처리해주면 된다.
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
	public void delete(String id) {
		sqlSession.delete(namespace + ".delete", id);
		
	}

	@Override
	public String getUser(String id) {
		return sqlSession.selectOne(namespace + ".getUser", id);
	}
	
	@Override
	public List<User> getList() {
		return sqlSession.selectList(namespace + ".getList");
	}

}// class
