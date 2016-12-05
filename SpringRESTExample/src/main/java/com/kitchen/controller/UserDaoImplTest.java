package com.kitchen.controller;

import javax.inject.Inject;

import org.junit.Test;
import com.kitchen.dao.UserDao;
import com.kitchen.model.User;

public class UserDaoImplTest extends AbstractTest{

	@Inject
	private UserDao dao;
	
	@Test
	public void test() {
		System.out.println("test---------------------------");
		logger.info(""+dao);
		System.out.println("test---------------------------");
	}
	
	@Test
	public void getTimeTest() {
		System.out.println("getTimeTest---------------------------");
		logger.info(""+dao.get("바네싸3"));
		System.out.println("getTimeTest---------------------------");
	}
	
	@Test
	public void registerTest(){
		User vo = new User();
		System.out.println("registerTest---------------------------");
		vo.setId("양송이1");
		vo.setName("양송이11");
		vo.setPassword("aaa");
		
		// DB에 넣고 DB테이블에서 직접 확인해봐야 한다.
		dao.register(vo);
		System.out.println("registerTest---------------------------");
	}
	
	@Test
	public void getTest(){
		System.out.println("getTest---------------------------");
		logger.info(""+dao.get("양송이11"));
		System.out.println("getTest---------------------------");
	}
	
	@Test
	public void getListTest(){
		System.out.println("getListTest---------------------------");
		logger.info(""+dao.getList());
		System.out.println("getListTest---------------------------");
	}
	
}// class
