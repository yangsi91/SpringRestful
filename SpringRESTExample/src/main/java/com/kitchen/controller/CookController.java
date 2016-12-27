package com.kitchen.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kitchen.model.Cook;
import com.kitchen.dao.*;

@RequestMapping(value = "/api/cook")
@Controller
public class CookController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);

	@Inject
	private CookDao cookDao;

	@RequestMapping(value = "/list/{recipe_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<Cook>> getListByRecipeId(@PathVariable("recipe_id") int recipe_id) {
		HashMap<String, List<Cook>> hm = new HashMap<String, List<Cook>>();
		hm.put("list", cookDao.getListByRecipeId(recipe_id));
		return hm;
	}
}

