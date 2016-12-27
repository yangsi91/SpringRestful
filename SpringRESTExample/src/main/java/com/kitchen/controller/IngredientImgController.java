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
import com.kitchen.model.IngredientImg;
import com.kitchen.model.Issuer;
import com.kitchen.model.Member;
import com.kitchen.model.User;
import com.mysql.fabric.Response;

@RequestMapping(value = "/api/ingredientImg")
@Controller
public class IngredientImgController {
	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	private static final String APPLICATION_NAME = "Google-VisionLabelSample/1.0";
	private static final int MAX_LABELS = 3;

	@Inject
	private IngredientImgDao ingredientImgDao;

	//재료 이미지 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<IngredientImg> getAllUsers() {
		logger.info("Inside getAllUers() method...");
		System.out.println("aaa");
		return ingredientImgDao.getList();
	}

	//재료 이미지 정보
	@RequestMapping(value = "/data/{img_id}", method = RequestMethod.GET)
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

	//재료 이미지 추가
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createProductInJSON(@RequestBody String body)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		if (body != null) {
			logger.info("Inside addUser2, adding: " + body);
		} else {
			logger.info("Inside addUser2...");
		}

		ObjectMapper mapper = new ObjectMapper();
		// IngredientImg addingredientImg = mapper.readValue(body,
		// IngredientImg.class);

		// ingredientImgDao.insert(addingredientImg);
		System.out.println(body);
		return body;
	}

	//재료 이미지 삭제
	@RequestMapping(value = "/delete/{img_id}", method = RequestMethod.GET)
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

	//카메라 서버에서 재료 이미지 파일과 basecode 파일 받고, Google Vision Api로 결과값 받아오기
	//異���: http://www.journaldev.com/2573/spring-mvc-file-upload-example-single-multiple-files
	@RequestMapping(value = "/uploadfiles", method = RequestMethod.POST)
	@ResponseBody
	public void uploadFiles(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2)
			throws IOException {
		PrintWriter pw;
		BufferedReader br;
		Socket sock;
		if (!file1.isEmpty() && !file2.isEmpty()) {
			try {
				byte[] bytes = file1.getBytes();
				File dir = new File("E:/workspace" + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile1 = new File(dir.getAbsolutePath() + File.separator + file1.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile1));
				stream.write(bytes);
				stream.close();

				bytes = file2.getBytes();
				dir = new File("E:/workspace" + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile2 = new File(dir.getAbsolutePath() + File.separator + file2.getOriginalFilename());
				stream = new BufferedOutputStream(new FileOutputStream(serverFile2));
				stream.write(bytes);
				stream.close();

				String[] args = { serverFile2.getAbsolutePath() };

				
				//異���: http://blog.naver.com/PostView.nhn?blogId=cyberhole&logNo=110133796544
				try {

					// 1. ��踰��� IP�� ��踰��� ���� �ы�� 媛�(8080)�� �몄��濡� �ｌ�� socket ����
					sock = new Socket("192.168.100.175", 8080);
					BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

					// 2. ���깅�� Socket�쇰�遺��� InputStream怨� OutputStream�� 援ы��
					OutputStream out = sock.getOutputStream();
					InputStream in = sock.getInputStream();

					// 3. InputStream�� BufferedReader �����쇰� 蹂���
					// OutputStream�� PrintWriter �����쇰� 蹂���
					pw = new PrintWriter(new OutputStreamWriter(out));

					// 4. �ㅻ낫��濡�遺��� �� 以��� ���λ��� BufferedReader 媛�泥� ����
					br = new BufferedReader(new InputStreamReader(in));

					String line = null;

					line = LabelApp.main(args);
					// 6. PrintWriter�� ���� println() 硫�����瑜� �댁�⑺�� ��踰���寃� ����
					pw.println(line);
					pw.flush();
				} finally {
				}

				pw.close();
				br.close();
				sock.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
