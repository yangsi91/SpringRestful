package com.kitchen.dao;

import org.springframework.stereotype.Repository;
import com.kitchen.model.Member;

@Repository
public class MemberDAOImpl extends GenericDAOImpl<Member, String> implements MemberDAO {

}
