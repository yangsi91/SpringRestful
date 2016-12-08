package com.kitchen.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kitchen.dao.UserDao;
import com.kitchen.model.___Issuer;
import com.kitchen.model.___Member;
import com.kitchen.model.User;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	String a;
	
	@Inject
	private UserDao userdao;
	
	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		logger.info("Inside getAllUers() method...");
		
		return userdao.getList();
	}
	
	@RequestMapping(value="/user/create",method = RequestMethod.POST)
    public String testpost(@RequestBody String body) throws Exception
    {
//		model.addAttribute("body",body);
    	return body;
/*		User vo = new User();
		vo.setUserid("양송이4");
		vo.setUserpw("ejpw");
		vo.setUsername("ejg");
		vo.setEmail("ej@g.com");
		
		// DB에 넣고 DB테이블에서 직접 확인해봐야 한다.
		userdao.register(vo);	*/
    }
	
	/*@RequestMapping(value="/user/addUser", method=RequestMethod.POST)
	@ResponseBody
	public Issuer addIssuer(@ModelAttribute("issuer") Issuer issuer) {
		
		if (issuer != null) {
			logger.info("Inside addIssuer, adding: " + issuer.toString());
		} else {
			logger.info("Inside addIssuer...");
		}
		issuers.put(issuer.getTicker(), issuer);
		return issuer;
		
	}*/
	
}
