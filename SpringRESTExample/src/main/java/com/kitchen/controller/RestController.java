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
import com.kitchen.model.___Issuer;
import com.kitchen.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RestController {
	
	@Inject
	private UserDao userdao;
	
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	private Map<String, ___Issuer> issuers = new HashMap<String, ___Issuer>();
	
	public RestController() {
		// pre-initialize the list of issuers available ...
		issuers.put("안녕", new ___Issuer("안녕", "안녕 안녕", "안녕", "안녕"));
		issuers.put("ATEN", new ___Issuer("ATEN", "A10 Networks Inc", "corp", "USA"));
		issuers.put("AAPL", new ___Issuer("AAPL", "Apple Inc", "corp", "USA"));
		issuers.put("T", new ___Issuer("T", "AT&T", "corp", "USA"));
		issuers.put("CSCO", new ___Issuer("CSCO", "Cisco Systems, Inc.", "corp", "USA"));
		issuers.put("CTXS", new ___Issuer("CTXS", "Citrix Systems, Inc.", "corp", "USA"));
		issuers.put("GOOGL", new ___Issuer("GOOGL", "Google Inc", "corp", "USA"));
		issuers.put("IBM", new ___Issuer("IBM", "IBM", "corp", "USA"));
		issuers.put("JNPR", new ___Issuer("JNPR", "Juniper Networks, Inc.", "corp", "USA"));
		issuers.put("MSFT", new ___Issuer("MSFT", "Microsoft Corporation", "corp", "USA"));
		issuers.put("ORCL", new ___Issuer("ORCL", "Oracle Corporation", "corp", "USA"));
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "status";
	}
	
/*	@RequestMapping(value="/users", method=RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		logger.info("Inside getAllUers() method...");
		
		return userdao.getList();
	}*/
	
	@RequestMapping(value="/issuers", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, ___Issuer> getAllIssuers() {
		logger.info("Inside getAllIssuers() method...");
		
		return issuers;
	}
	
	@RequestMapping(value="/issuer/{ticker}", method=RequestMethod.GET)
	@ResponseBody
	public ___Issuer getIssuerByTicker(@PathVariable("ticker") String ticker) {
		___Issuer myIssuer = issuers.get(ticker); 
		
		if (myIssuer != null) {
			logger.info("Inside getIssuerByTicker, returned: " + myIssuer.toString());
		} else {
			logger.info("Inside getIssuerByTicker, ticker: " + ticker + ", NOT FOUND!");
		}
		return myIssuer; 
	}

	@RequestMapping(value="/issuer/delete/{ticker}", method=RequestMethod.GET)
	@ResponseBody
	public ___Issuer deleteIssuerByTicker(@PathVariable("ticker") String ticker) {
		___Issuer myIssuer = issuers.remove(ticker); 
		
		if (myIssuer != null) {
			logger.info("Inside deleteIssuerByTicker, deleted: " + myIssuer.toString());
		} else {
			logger.info("Inside deleteIssuerByTicker, ticker: " + ticker + ", NOT FOUND!");
		}
		return myIssuer;
	}

	@RequestMapping(value="/issuer/create", method=RequestMethod.GET)
	public ModelAndView addIssuer() {
		
		return new ModelAndView("addIssuer", "command", new ___Issuer());
	}
	
	@RequestMapping(value="/issuer/addIssuer", method=RequestMethod.POST)
	@ResponseBody
	public ___Issuer addIssuer(@ModelAttribute("issuer") ___Issuer issuer) {
		
		if (issuer != null) {
			logger.info("Inside addIssuer, adding: " + issuer.toString());
		} else {
			logger.info("Inside addIssuer...");
		}
		issuers.put(issuer.getTicker(), issuer);
		return issuer;
		
	}
	
}
