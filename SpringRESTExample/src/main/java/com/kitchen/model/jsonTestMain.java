package com.kitchen.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class jsonTestMain {
	public static void main(String[] args) throws JSONException, IOException {
		
	JSONObject json = new JSONObject();
	json.put("test", "test");
	json.put("test1", "test1");
	json.put("test2", "test2");
	json.put("test3", "test3");
	json.put("test4", "test3");
	json.put("test5", "test3");
	json.put("test6", "test3");
	String body = json.toString();
	System.out.println(body);
	URL postUrl = new URL("http://localhost:8080/tutorial/user/create");
	HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
	connection.setDoOutput(true); 				// xml내용을 전달하기 위해서 출력 스트림을 사용
	connection.setInstanceFollowRedirects(false);  //Redirect처리 하지 않음
	connection.setRequestMethod("POST");
	connection.setRequestProperty("Content-Type", "application/json");
	OutputStream os= connection.getOutputStream();
	os.write(body.getBytes());
	os.flush();
	System.out.println("Location: " + connection.getHeaderField("Location"));
	
	  BufferedReader br = new BufferedReader(new InputStreamReader(
			  (connection.getInputStream())));
			   
			  String output;
			  System.out.println("Output from Server .... \n");
			  while ((output = br.readLine()) != null) {
			  System.out.println(output);
			  }
	connection.disconnect();
	//81page~
	}
}