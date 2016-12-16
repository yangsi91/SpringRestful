package com.kitchen.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kitchen.dao.IngredientDao;
import com.kitchen.dao.IngredientImgDao;
import com.kitchen.dao.UserDao;
import com.kitchen.model.IngredientImg;
import com.kitchen.model.Issuer;
import com.kitchen.model.Member;
import com.kitchen.model.User;
import com.mysql.fabric.Response;

@RequestMapping(value="/api/ingredientImg")
@Controller
public class IngredientImgController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	
	@Inject
	private IngredientImgDao ingredientImgDao;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public List<IngredientImg> getAllUsers() {
		logger.info("Inside getAllUers() method...");
		System.out.println("aaa");
		return ingredientImgDao.getList();
	}
	
	@RequestMapping(value="/data/{img_id}", method=RequestMethod.GET)
	@ResponseBody
	public IngredientImg getIssuerByTicker(@PathVariable("img_id") int img_id) {
		IngredientImg ingredientImg = ingredientImgDao.getIngredientImg(img_id); 
		
		if (ingredientImg != null) {
			logger.info("Inside getIssuerByTicker, returned: " + ingredientImg.toString());
		} else {
			logger.info("Inside getIssuerByTicker, id: " + ingredientImg + ", NOT FOUND!");
		}
		return ingredientImg;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createProductInJSON(@RequestBody String body) throws ParseException, JsonParseException, JsonMappingException, IOException {
		if (body != null) {
			logger.info("Inside addUser2, adding: " + body);
		} else {
			logger.info("Inside addUser2...");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		IngredientImg addingredientImg = mapper.readValue(body, IngredientImg.class);
		
		ingredientImgDao.insert(addingredientImg);
		return ""+addingredientImg;
	}
	  
	@RequestMapping(value="/delete/{img_id}", method=RequestMethod.GET)
	@ResponseBody
	public IngredientImg deleteIssuerByTicker(@PathVariable("img_id") int img_id) {
		IngredientImg delIngredientImg = ingredientImgDao.getIngredientImg(img_id); 
		ingredientImgDao.delete(img_id); 
		if (delIngredientImg != null) {
			logger.info("Inside deleteIssuerByTicker, deleted: " + delIngredientImg.toString());
		} else {
			logger.info("Inside deleteIssuerByTicker, ticker: " + img_id + ", NOT FOUND!");
		}
		return delIngredientImg;
	}
	
	@RequestMapping(value = "/testcurl", method = RequestMethod.POST)
	@ResponseBody
	public String curltest(@RequestBody String body) throws ParseException, JsonParseException, JsonMappingException, IOException {
		if (body != null) {
			logger.info("txt...: \n" + body);
		} else {
			logger.info("Inside addUser2...");
		}
		System.out.println(body);
//		ObjectMapper mapper = new ObjectMapper();
//		Ingredient addingredient = mapper.readValue(body, Ingredient.class);
//		
//		ingredientDao.insert(addingredient);
		return "OK";
	}
	
	@RequestMapping(value="/curl/upload", method=RequestMethod.POST)
	@ResponseBody
	public String uploadFileHandler(@RequestBody MultipartFile file) {
		if(!file.isEmpty()) {
			return "yes";
		}
		else
			return "no";
    	/*String fileName = null;
    	if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File("C:/Users/Songyi/git/SpringRestful/SpringRESTExample/tmp/" + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                return "You have successfully uploaded " + fileName;
            } catch (Exception e) {
                return "You failed to upload " + fileName + ": " + e.getMessage();
            }
        } else {
            return "Unable to upload. File is empty.";
        }*/
    	
		
		/*String name = null;
		if (!file.isEmpty()) {
			name = file.getName();
			try {
				
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("user.dir");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}*/
	}
}
