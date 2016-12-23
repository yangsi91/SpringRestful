package com.kitchen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kitchen.model.IngredientType;
import com.kitchen.dao.*;

@RequestMapping(value = "/api/ingredientType")
@Controller
public class IngredientTypeController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);

	@Inject
	private IngredientTypeDao ingredientTypeDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<IngredientType>> getList() {
		logger.info("IngredientType getList() method...");
		HashMap<String, List<IngredientType>> hm = new HashMap<String, List<IngredientType>>();
		hm.put("list", ingredientTypeDao.getList());
		return hm;
		
	}
	
	@RequestMapping(value = "/data/{type_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, IngredientType> getData(@PathVariable("type_id") int type_id) {
		HashMap<String, IngredientType> hm = new HashMap<String, IngredientType>();
		hm.put("data", ingredientTypeDao.getData(type_id));
		
		if (hm.get("data") != null) {
			logger.info("IngredientType getData, returned: " + hm.get("data").toString());
		} else {
			logger.info("IngredientType getData, id: " + hm.get("data") + ", NOT FOUND!");
		}
		return hm;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public int createDataByJSON(@RequestBody String body)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		if (body != null) {
			logger.info("Favorite add, adding: " + body);
		} else {
			logger.info("Favorite add...");
		}

		ObjectMapper mapper = new ObjectMapper();
		IngredientType addIngredientType = mapper.readValue(body, IngredientType.class);

		System.out.println(body);
		return ingredientTypeDao.insert(addIngredientType);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public IngredientType delete(@RequestBody String body) throws ParseException, JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		IngredientType delIngredientType = mapper.readValue(body, IngredientType.class);

		ingredientTypeDao.delete(delIngredientType);
		System.out.println(body);
		
		return delIngredientType;
	}
}
