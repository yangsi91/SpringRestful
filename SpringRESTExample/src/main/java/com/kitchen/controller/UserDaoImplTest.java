package com.kitchen.controller;

import javax.inject.Inject;
import org.junit.Test;
import com.kitchen.dao.UserDao;
import com.kitchen.model.User;

public class UserDaoImplTest extends AbstractTest{

	@Inject
	private UserDao dao;
	
//	@Test
	public void test() {
		System.out.println("test---------------------------");
		logger.info(""+dao);
		System.out.println("test---------------------------");
	}
	
//	@Test
	public void getTimeTest() {
		System.out.println("getTimeTest---------------------------");
		logger.info(""+dao.getUser("바네싸3"));
		System.out.println("getTimeTest---------------------------");
	}
	
	@Test
	public void registerTest(){
		User vo = new User();
		System.out.println("registerTest---------------------------");
		vo.setId("test11");
		vo.setName("양송이11");
		vo.setPassword("aaa");
		
		// DB에 넣고 DB테이블에서 직접 확인해봐야 한다.
		dao.insert(vo);
		System.out.println("registerTest---------------------------");
	}
	
//	@Test
	public void getTest(){
		System.out.println("getTest---------------------------");
		logger.info(""+dao.getUser("test1"));
		System.out.println("getTest---------------------------");
	}
	
//	@Test
	public void getListTest(){
		System.out.println("getListTest---------------------------");
		logger.info(""+dao.getList());
		System.out.println("getListTest---------------------------");
	}
	
//	@Test
	public void deleteTest(){
		System.out.println("deleteTest---------------------------");
//		logger.info("" + dao.delete("test1"));
		dao.delete("test1");
		System.out.println("deleteTest---------------------------");
	}
	
//	@Test
	public void updateTest(){
		User vo = new User();
		System.out.println("updateTest---------------------------");
		vo.setId("test1");
		vo.setName("수정ㅇ");
		vo.setPassword("ㅜ정수정");
		
		// DB에 넣고 DB테이블에서 직접 확인해봐야 한다.
		dao.update(vo);
		System.out.println("updateTest---------------------------");
	}
	
}// class
