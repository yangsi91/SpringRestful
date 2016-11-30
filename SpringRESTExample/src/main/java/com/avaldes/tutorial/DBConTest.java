package com.avaldes.tutorial;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring" + "/**/*-context.xml"})
public class DBConTest {
	
	@Inject
//	private DataSource ds;
	private TimeDao td;
	
	@Test
	public void test() throws Exception{
		System.out.println("before");
//		System.out.println(ds.getConnection());
		System.out.println(td.getTime());
		System.out.println("after");
		
	}

}
