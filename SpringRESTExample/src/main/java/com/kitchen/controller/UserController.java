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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kitchen.dao.UserDao;
import com.kitchen.model.Issuer;
import com.kitchen.model.User;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	
	@Inject
	private UserDao userdao;
	
	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		logger.info("Inside getAllUers() method...");
		
		return userdao.getList();
	}
}
