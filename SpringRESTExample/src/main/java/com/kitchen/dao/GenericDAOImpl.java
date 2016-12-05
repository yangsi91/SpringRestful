package com.kitchen.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;

// GenericDAO 인터페이스의 추상클래스들을 오버라이딩 해주어야 한다.
// 즉, 여기서 DB와 연결을 하고 SQL문을 처리해주어야 하므로 sessionTemplate을 인스턴스 변수로 갖고서 
// 이를 통해 CRUD 작업을 처리해주면 된다.
public abstract class GenericDAOImpl<E, K> implements GenericDAO<E, K> {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = 
			"com.kitchen.mapper.MemberMapper";
	
	@Override
	public K getTime() {
		return sqlSession.selectOne(namespace + ".getNow");
//		return sqlSession.selectOne(namespace);
	}

	@Override
	public void register(E vo) {
		sqlSession.insert(namespace + ".register", vo);
	}

	@Override
	public E get(K userid) {
		return sqlSession.selectOne(namespace + ".get", userid);
	}

	@Override
	public List<E> getList() {
		return sqlSession.selectList(namespace + ".getList");
	}

}// class
