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

import com.kitchen.model.RecipeDetails;
import com.kitchen.dao.*;

@RequestMapping(value = "/api/recipe/details")
@Controller
public class RecipeDetailsController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);

	@Inject
	private RecipeDetailsDao recipeDetailsDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<RecipeDetails>> getList() {
		logger.info("RecipeDetails getList() method...");
		HashMap<String, List<RecipeDetails>> hm = new HashMap<String, List<RecipeDetails>>();
		hm.put("list", recipeDetailsDao.getList());
		return hm;
		
	}
	
	@RequestMapping(value = "/data/{details_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, RecipeDetails> getData(@PathVariable("details_id") int details_id) {
		HashMap<String, RecipeDetails> hm = new HashMap<String, RecipeDetails>();
		hm.put("data", recipeDetailsDao.getData(details_id));
		
		if (hm.get("data") != null) {
			logger.info("RecipeDetails getData, returned: " + hm.get("data").toString());
		} else {
			logger.info("RecipeDetails getData, id: " + hm.get("data") + ", NOT FOUND!");
		}
		return hm;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createDataByJSON(@RequestBody String body)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		if (body != null) {
			logger.info("RecipeDetails add, adding: " + body);
		} else {
			logger.info("RecipeDetails add...");
		}

		ObjectMapper mapper = new ObjectMapper();
		RecipeDetails addRecipeDetail = mapper.readValue(body, RecipeDetails.class);

		recipeDetailsDao.insert(addRecipeDetail);
		System.out.println(body);
		return body;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RecipeDetails delete(@RequestBody String body) throws ParseException, JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		RecipeDetails delRecipeDetails = mapper.readValue(body, RecipeDetails.class);

		recipeDetailsDao.delete(delRecipeDetails.getDetails_id());
		System.out.println(body);
		
		return delRecipeDetails;
	}
}
