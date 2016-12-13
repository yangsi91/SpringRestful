package com.kitchen.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.jws.soap.SOAPBinding.Use;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import com.kitchen.model.Issuer;
import com.kitchen.model.Member;
import com.kitchen.model.User;
import com.mysql.fabric.Response;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	
	@Inject
	private UserDao userdao;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		logger.info("Inside getAllUers() method...");
		
		return userdao.getList();
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	@ResponseBody
	public User getIssuerByTicker(@PathVariable("id") String id) {
		User myUser = userdao.getUser(id); 
		
		if (myUser != null) {
			logger.info("Inside getIssuerByTicker, returned: " + myUser.toString());
		} else {
			logger.info("Inside getIssuerByTicker, id: " + id + ", NOT FOUND!");
		}
		return myUser;
	}
	
	@RequestMapping(value="/user/create",method = RequestMethod.GET)
    public ModelAndView addUser() {
		return new ModelAndView("addUser", "command", new User());
    }
	
	@RequestMapping(value="/user/addUser", method=RequestMethod.POST)
	@ResponseBody
	public User addUser(@ModelAttribute("user") User user) {
		
		if (user != null) {
			logger.info("Inside addUser, adding: " + user.toString());
		} else {
			logger.info("Inside addUser...");
		}
		userdao.insert(user);
		return user;
	}
	
	@RequestMapping(value = "/user/addUser2", method = RequestMethod.POST)
	@ResponseBody
	public String createProductInJSON(@RequestBody String body) throws ParseException, JsonParseException, JsonMappingException, IOException {
		if (body != null) {
			logger.info("Inside addUser2, adding: " + body);
		} else {
			logger.info("Inside addUser2...");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		User addUser = mapper.readValue(body, User.class);
		
		userdao.insert(addUser);
		return ""+addUser;
	}
	  
	@RequestMapping(value="/user/delete/{id}", method=RequestMethod.GET)
	@ResponseBody
	public User deleteIssuerByTicker(@PathVariable("id") String id) {
		User delUser = userdao.getUser(id); 
		userdao.delete(id); 
		if (delUser != null) {
			logger.info("Inside deleteIssuerByTicker, deleted: " + delUser.toString());
		} else {
			logger.info("Inside deleteIssuerByTicker, ticker: " + id + ", NOT FOUND!");
		}
		return delUser;
	}
}
