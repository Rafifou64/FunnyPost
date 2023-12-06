package application.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import application.entite.Post;

public class Webservice {
	
	public ArrayList<Post> getPostAPI() throws IOException
	{
		URL url = new URL("https://jsonplaceholder.typicode.com/posts");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader (connection.getInputStream()));
		
		StringBuilder responseBody = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            responseBody.append(inputLine).append("\n");
        }      
        
		in.close();
		return this.convertStringToListPost(responseBody.toString());		
	}
	
	private ArrayList<Post> convertStringToListPost(String jsonString)
	{
		ArrayList<Post> lstPostRes = new ArrayList<Post>();
		JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                Post post = new Post(Integer.parseInt(jsonObject.get("id").toString()), (String) jsonObject.get("title"), (String) jsonObject.get("body"));

                lstPostRes.add(post);
            }
            
            return lstPostRes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

}
