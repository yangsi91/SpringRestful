package com.kitchen.controller;

import javax.inject.Inject;
import org.junit.Test;
import com.kitchen.dao.___MemberDAO;
import com.kitchen.model.___Member;

public class MemberDAOImplTest extends AbstractTest{

	@Inject
	private ___MemberDAO dao;
	
//	@Test
	public void test() {
		System.out.println("test---------------------------");
		logger.info(""+dao);
		System.out.println("test---------------------------");
	}
	
//	@Test
	public void getTimeTest() {
		System.out.println("getTimeTest---------------------------");
		logger.info(""+dao.getTime());
		System.out.println("getTimeTest---------------------------");
	}
	
	@Test
	public void registerTest(){
		___Member vo = new ___Member();
		System.out.println("registerTest---------------------------");
		vo.setUserid("양송이2");
		vo.setUserpw("ejpw");
		vo.setUsername("ejg");
		vo.setEmail("hello_song@g.com");
		
		// DB에 넣고 DB테이블에서 직접 확인해봐야 한다.
		dao.update(vo);
		System.out.println("registerTest---------------------------");
	}
	
//	@Test
	public void getTest(){
		System.out.println("getTest---------------------------");
		logger.info(""+dao.get("양송이1"));
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
		logger.info(""+dao.delete("바네싸3"));
		System.out.println("deleteTest---------------------------");
	}
	
	
	
}// class
