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

import com.kitchen.model.Cook;
import com.kitchen.model.Ingredient;
import com.kitchen.model.Product;
import com.kitchen.dao.*;

@RequestMapping(value = "/api/product")
@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);

	@Inject
	private ProductDao productDao;

	// product 이름으로 key값 가져오기 Get
	@RequestMapping(value = "/data/{name}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<Product>> getProductId(@PathVariable("name") String name) {
		HashMap<String, List<Product>> hm = new HashMap<String, List<Product>>();
		hm.put("data", productDao.getProductId(name));
		return hm;
	}

	// product 이름으로 key값 가져오기 Post
	@RequestMapping(value = "/data", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, List<Product>> getProductIdByJSON(@RequestBody String name) {
		HashMap<String, List<Product>> hm = new HashMap<String, List<Product>>();
		hm.put("data", productDao.getProductId(name));
		return hm;
	}
}
