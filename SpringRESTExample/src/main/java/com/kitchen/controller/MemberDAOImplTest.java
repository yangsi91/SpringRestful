package com.kitchen.controller;

import javax.inject.Inject;

import org.junit.Test;

import com.kitchen.dao.MemberDAO;
import com.kitchen.model.Member;

public class MemberDAOImplTest extends AbstractTest{

	@Inject
	private MemberDAO dao;
	
	@Test
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
	
//	@Test
	public void registerTest(){
		Member vo = new Member();
		System.out.println("registerTest---------------------------");
		vo.setUserid("양송이4");
		vo.setUserpw("ejpw");
		vo.setUsername("ejg");
		vo.setEmail("ej@g.com");
		
		// DB에 넣고 DB테이블에서 직접 확인해봐야 한다.
		dao.register(vo);
		System.out.println("registerTest---------------------------");
	}
	
	@Test
	public void getTest(){
		System.out.println("getTest---------------------------");
		logger.info(""+dao.get("양송이1"));
		System.out.println("getTest---------------------------");
	}
	
	@Test
	public void getListTest(){
		System.out.println("getListTest---------------------------");
		logger.info(""+dao.getList());
		System.out.println("getListTest---------------------------");
	}
	
}// class
