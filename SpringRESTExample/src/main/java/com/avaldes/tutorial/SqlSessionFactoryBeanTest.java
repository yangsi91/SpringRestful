package com.avaldes.tutorial;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class SqlSessionFactoryBeanTest extends AbstractTest {
	
	@Inject
	SqlSessionFactoryBean factoryBean;
	
	@Test
	public void test() {
		logger.info("test result1: "+factoryBean);
	}
	
	@Test
	public void sessionTest() {
		try {
			logger.info("test result2: "+factoryBean.getObject().openSession());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
