package com.kitchen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

// 지금 이 클래스는 스프링 프로젝트의 기본 패키지가 아니므로 Spring이 관리하는 패키지가 아니다. 그래서 따로 설정해주어야 한다.
//@Repository
public class ___TimeDao {

//	@Inject
	DataSource ds;// DataSource타입의 bean이 현재는 root-context에 하나밖에 없으므로  bean의 id와
				    // 인스턴스 변수 이름이 다르더라도 주입할 수있다.
	public String getTime() throws Exception{
		
		Connection con = ds.getConnection();
		PreparedStatement pstmt = con.prepareStatement("select now()");
//		PreparedStatement pstmt = con.prepareStatement("select * from books where name='홍길동전'");
		
		
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		return rs.getString(1);// 현재 날짜가 반환된다.
	}//getTime()

	public void setDs(DataSource ds) {
		this.ds = ds;
	}
}// class
