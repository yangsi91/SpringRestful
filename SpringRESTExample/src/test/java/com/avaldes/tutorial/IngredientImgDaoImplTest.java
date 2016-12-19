package com.avaldes.tutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.kitchen.dao.IngredientImgDao;
import com.kitchen.model.IngredientImg;

public class IngredientImgDaoImplTest extends AbstractTest {

	// private UserDao dao;
	@Inject
	private IngredientImgDao dao;

	RestTemplate restTemplate;

	@Test
	public void testDao() {
		System.out.println("test---------------------------");
		logger.info("" + dao);
		System.out.println("test---------------------------");
	}

	 @Test
	public void test() throws IOException {
		System.out.println("send file test---------------------------");
		final String FILENAME = "C:/Users/Songyi/git/SpringRestful/SpringRESTExample/src/test/java/com/avaldes/tutorial/test.json";
		File file = new File(FILENAME);
		String UPLOAD_URL = "http://localhost:8080/tutorial/api/ingredientImg/curl/upload";
		int BUFFER_SIZE = 4096;
		BufferedReader br = null;
		FileReader fr = null;
		
		/*System.out.println("test---------------------------");
		logger.info("" + dao);
		String workingDir = System.getProperty("user.dir");
		System.out.println("Current working directory : " + workingDir);

		// takes file path from first program's argument
		String filePath = "C:/Users/Songyi/Desktop/hellotest.txt";
		File uploadFile = new File(filePath);

		System.out.println("File to upload: " + filePath);

		

		try {

			fr = new FileReader(filePath);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filePath));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}*/

		// creates a HTTP connection
		URL url = new URL(UPLOAD_URL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

		httpConn.setRequestProperty("Content-Type", "multipart/form-data");
		httpConn.setRequestProperty("Accept", "*/*");
		httpConn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true);
		httpConn.setRequestMethod("POST");
		// sets file name as a HTTP header
//		httpConn.setRequestProperty("fileName", FILENAME.getName());

		// opens output stream of the HTTP connection for writing data
		OutputStream outputStream = httpConn.getOutputStream();

		// Opens input stream of the file for reading data
		FileInputStream inputStream = new FileInputStream(file);

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		System.out.println("Start writing data...");

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}

		System.out.println("Data was written.");
		outputStream.close();
		inputStream.close();

		// always check HTTP response code from server
		int responseCode = httpConn.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			// reads server's response
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			String response = reader.readLine();
			System.out.println("Server's response: " + response);
		} else {
			System.out.println("Server returned non-OK code: " + responseCode);
		}
		
		// display what returns the POST request
		StringBuilder sb = new StringBuilder();

		int HttpResult = httpConn.getResponseCode();

		if (HttpResult == HttpURLConnection.HTTP_OK) {

			br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf-8"));

			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			br.close();

			System.out.println("" + sb.toString());

		} else {
			System.out.println(httpConn.getResponseMessage());
		}
		System.out.println("send file test---------------------------");
	}

//	@Test
	public void registerTest() throws FileNotFoundException {
		final String FILENAME = "C:/Users/Songyi/git/SpringRestful/SpringRESTExample/src/test/java/com/avaldes/tutorial/test.json";
		
		//console에 출력
		/*BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}*/
		
		//file 내용을 디비에 저장
		File file = new File(FILENAME);
		FileInputStream  fis = new FileInputStream(file);
		IngredientImg vo = new IngredientImg();
		
		vo.setIngredient_id(1);
		vo.setBasecode(fis);
		vo.setTags("yes");

		// DB에 넣고 DB테이블에서 직접 확인해봐야 한다.
		dao.insert(vo);
		System.out.println("registerTest---------------------------");
	}

	// @Test
	public void getTest() {
		System.out.println("getTest---------------------------");
		// logger.info(""+dao.getUser("test1"));
		System.out.println("getTest---------------------------");
	}

	// @Test
	public void getListTest() {
		System.out.println("getListTest---------------------------");
		logger.info("" + dao.getList());
		System.out.println("getListTest---------------------------");
	}

	// @Test
	public void deleteTest() {
		System.out.println("deleteTest---------------------------");
		// logger.info("" + dao.delete("test1"));
		// dao.delete("test1");
		System.out.println("deleteTest---------------------------");
	}

	

	// @Test
	public void testCreate() throws Exception {

		String url = "http://localhost:8080/tutorial/user/addUser2";
		URL object = new URL(url);
		HttpURLConnection con = (HttpURLConnection) object.openConnection();

		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "*/*");
		con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		con.setRequestMethod("POST");

		JSONObject obj = new JSONObject();

		obj.put("id", "json테스트");
		obj.put("name", "이름ㅆㅓ");
		obj.put("passworld", "패스워드");

		String body = obj.toString();

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

		wr.write(body);
		wr.flush();
		System.out.println("testCreate---------------------------");
		// restTemplate.postForObject(uri, body, String.class);

		// dao.getUser("json테스트");
		System.out.println("testCreate---------------------------");
	}

}
