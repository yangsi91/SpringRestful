package com.avaldes.tutorial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.kitchen.model.Ingredient;;

public class main2 {

public static void main(String args[]) {
		String url="http://192.168.0.20:8080/tutorial/api/ingredientImg/create";
	  try {
	   URL object=new URL(url);
	 
	   HttpURLConnection con = (HttpURLConnection) object.openConnection();
	 
	   con.setDoOutput(true);
	   con.setDoInput(true);
	   con.setRequestProperty("Content-Type", "application/json");
	   con.setRequestProperty("Accept", "*/*");
	   con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
	   con.setRequestMethod("POST");
	 
	   JSONObject obj = new JSONObject();
	   
/*	   obj.put("id", "java2");
	   obj.put("name", "hello");
	   obj.put("password", "OH");*/
	   
	   
	   obj.put("kor_name", "ingrediKor");
	   obj.put("eng_names", "ingredientEngs");
	   obj.put("img_location", "/user/img.jpg");
	   obj.put("type_id", "2");
	   	      
/*	// Array List 만들기
	   JSONObject params = new JSONObject();
	   params.put("password", "*******");
	   JSONArray list1 = new JSONArray();
	   list1.put(params);
	   
	   JSONObject data = new JSONObject();
	   data.put("id","Authentication");
	   data.put("name", "login");
	   data.put("password", "aa");
	 */
	   OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
	 
	   wr.write(obj.toString());
//	   wr.write("hello");
	   wr.flush();
	 
	   System.out.println(obj.toString());
	   
	   ObjectMapper mapper = new ObjectMapper();
//	   String jsonInString = "{\"id\":\"jsontest\",\"name\":\"your name\",\"password\":\"PPAP\"}";
//	   User user = mapper.readValue(obj.toString(), User.class);
	   Ingredient user = mapper.readValue(obj.toString(), Ingredient.class);
	   System.out.println(user);

		
		
	   //display what returns the POST request
	   StringBuilder sb = new StringBuilder();  
	 
	   int HttpResult =con.getResponseCode(); 
	 
	   if(HttpResult ==HttpURLConnection.HTTP_OK){
	 
	       BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));  
	 
	       String line = null;  
	 
	       while ((line = br.readLine()) != null) {  
	        sb.append(line + "\n");  
	       }  
	 
	       br.close();  
	 
	       System.out.println(""+sb.toString());  
	 
	   }else{
	       System.out.println(con.getResponseMessage());  
	   }  
	  }
	  catch (Exception e) {
	   e.printStackTrace();
	  }
	  finally {
	   
	  }
	  
    }
}