package com.avaldes.tutorial;

import org.springframework.stereotype.Repository;
import com.avaldes.domain.MemberVO;

@Repository
public class MemberDAOImpl extends GenericDAOImpl<MemberVO, String> implements MemberDAO {

}
