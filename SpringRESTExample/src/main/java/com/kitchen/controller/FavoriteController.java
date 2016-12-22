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

import com.kitchen.model.Favorite;
import com.kitchen.dao.*;

@RequestMapping(value = "/api/favorite")
@Controller
public class FavoriteController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);

	@Inject
	private FavoriteDao favoriteDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<Favorite>> getList() {
		logger.info("Recipe getList() method...");
		HashMap<String, List<Favorite>> hm = new HashMap<String, List<Favorite>>();
		hm.put("list", favoriteDao.getList());
		return hm;
		
	}
	
	@RequestMapping(value = "/data/{favorite_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Favorite> getData(@PathVariable("favorite_id") int favorite_id) {
		HashMap<String, Favorite> hm = new HashMap<String, Favorite>();
		hm.put("data", favoriteDao.getData(favorite_id));
		
		if (hm.get("data") != null) {
			logger.info("Favorite getData, returned: " + hm.get("data").toString());
		} else {
			logger.info("Favorite getData, id: " + hm.get("data") + ", NOT FOUND!");
		}
		return hm;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createDataByJSON(@RequestBody String body)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		if (body != null) {
			logger.info("Favorite add, adding: " + body);
		} else {
			logger.info("Favorite add...");
		}

		ObjectMapper mapper = new ObjectMapper();
		Favorite addFavorite = mapper.readValue(body, Favorite.class);

		favoriteDao.insert(addFavorite);
		System.out.println(body);
		return body;
	}

	@RequestMapping(value = "/delete/{favorite_id}", method = RequestMethod.GET)
	@ResponseBody
	public Favorite delete(@PathVariable("recipe_id") int favorite_id) {
		Favorite delFavorite = favoriteDao.getData(favorite_id);
		favoriteDao.delete(favorite_id);
		if (delFavorite != null) {
			logger.info("Inside deleteIssuerByTicker, deleted: " + delFavorite.toString());
		} else {
			logger.info("Inside deleteIssuerByTicker, ticker: " + favorite_id + ", NOT FOUND!");
		}
		return delFavorite;
	}
	
}
