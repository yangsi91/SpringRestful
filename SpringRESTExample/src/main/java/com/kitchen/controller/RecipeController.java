package com.kitchen.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionScopes;
import com.kitchen.controller.LabelApp;
import com.kitchen.dao.IngredientDao;
import com.kitchen.dao.IngredientImgDao;
import com.kitchen.dao.UserDao;
import com.kitchen.model.Ingredient;
import com.kitchen.model.IngredientImg;
import com.kitchen.model.Issuer;
import com.kitchen.model.Member;
import com.kitchen.model.Recipe;
import com.kitchen.model.RecipeFavorite;
import com.kitchen.model.User;
import com.mysql.fabric.Response;
import com.kitchen.dao.*;

@RequestMapping(value = "/api/recipe")
@Controller
public class RecipeController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);

	@Inject
	private RecipeDao recipeDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<Recipe>> getList() {
		logger.info("Recipe getList() method...");
		HashMap<String, List<Recipe>> hm = new HashMap<String, List<Recipe>>();
		hm.put("list", recipeDao.getList());
		return hm;
		
	}

	@RequestMapping(value = "/favorite/list/{product_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<RecipeFavorite>> getLeftOuterjoinList(@PathVariable("product_id") int product_id) {
		logger.info("Recipe getOuterjoinList() method...");
		HashMap<String, List<RecipeFavorite>> hm = new HashMap<String, List<RecipeFavorite>>();
		hm.put("list", recipeDao.getLeftouterjoinList(product_id));
		return hm;
	}
	
	@RequestMapping(value = "/myfavorite/list/{product_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<RecipeFavorite>> getOuterjoinList(@PathVariable("product_id") int product_id) {
		logger.info("Recipe getOuterjoinList() method...");
		HashMap<String, List<RecipeFavorite>> hm = new HashMap<String, List<RecipeFavorite>>();
		hm.put("list", recipeDao.getOuterjoinList(product_id));
		return hm;
	}
	
	@RequestMapping(value = "/data/{recipe_id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Recipe> getData(@PathVariable("recipe_id") int recipe_id) {
		HashMap<String, Recipe> hm = new HashMap<String, Recipe>();
		hm.put("data", recipeDao.getRecipe(recipe_id));
		
		if (hm.get("data") != null) {
			logger.info("Recipe getData, returned: " + hm.get("data").toString());
		} else {
			logger.info("Recipe getData, id: " + hm.get("data") + ", NOT FOUND!");
		}
		return hm;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createDataByJSON(@RequestBody String body)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		if (body != null) {
			logger.info("Recipe add, adding: " + body);
		} else {
			logger.info("Recipe add...");
		}

		ObjectMapper mapper = new ObjectMapper();
		 Recipe addRecipe = mapper.readValue(body, Recipe.class);

		 recipeDao.insert(addRecipe);
		System.out.println(body);
		return body;
	}

	@RequestMapping(value = "/delete/{recipe_id}", method = RequestMethod.GET)
	@ResponseBody
	public Recipe delete(@PathVariable("recipe_id") int recipe_id) {
		Recipe delRecipe = recipeDao.getRecipe(recipe_id);
		recipeDao.delete(recipe_id);
		if (delRecipe != null) {
			logger.info("Inside deleteIssuerByTicker, deleted: " + delRecipe.toString());
		} else {
			logger.info("Inside deleteIssuerByTicker, ticker: " + recipe_id + ", NOT FOUND!");
		}
		return delRecipe;
	}
	
}
