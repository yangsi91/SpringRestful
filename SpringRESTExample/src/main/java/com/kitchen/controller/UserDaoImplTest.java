package com.kitchen.controller;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.inject.Inject;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.kitchen.dao.UserDao;
import com.kitchen.model.User;

public class UserDaoImplTest extends AbstractTest{

	@Inject
	private UserDao dao;
	
	RestTemplate restTemplate;

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
	
//	@Test
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
	
	@Test
	public void testCreate() throws Exception {
	    
		String url = "http://localhost:8080/tutorial/user/addUser2";
		URL object=new URL(url);
		   HttpURLConnection con = (HttpURLConnection) object.openConnection();
		 
		   con.setDoOutput(true);
		   con.setDoInput(true);
		   con.setRequestProperty("Content-Type", "application/json");
		   con.setRequestProperty("Accept", "*/*");
		   con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		   con.setRequestMethod("POST");
		 
		   JSONObject obj = new JSONObject();
		   
		   obj.put("id", "json테스트");
		   obj.put("name", "이름ㅆㅓ");
		   obj.put("passworld", "패스워드");
		
		String body = obj.toString();
		
		OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
		 
		   wr.write(body);
		   wr.flush();
		System.out.println("testCreate---------------------------");
//		restTemplate.postForObject(uri, body, String.class);
	    
		dao.getUser("json테스트");
	    System.out.println("testCreate---------------------------");
	}
	
}// class
