package com.lexicon.yandex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lexicon.model.Word;

public class Translator {
	
	private final static String API_KEY = "dict.1.1.20160328T172945Z.b478fcf93e96aabb.dc77082aff1fc58d9a97a4cae2b82d261c1bf166";
	private final static String API_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup";
	//private final static String API_URL = "https://google.com";
	
	
	private static String getTranslations(String from, String to, String word) throws Exception{
		String urlString = String.format("%s?key=%s&lang=%s-%s&text=%s", API_URL, API_KEY, from, to, word);
		URL obj = new URL(urlString);
		URLConnection con = obj.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		con.setRequestProperty("Accept-Charset", "UTF-8");
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
	
	public static Word getWord(String from, String to, String name) throws Exception{
		Word word = new Word();
		word.setName(name);
		word.setFrom(from);
		word.setTo(to);
		String json = getTranslations(from, to, name);
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(json);
		if(jsonTree.isJsonObject()) {
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement def = jsonObject.get("def");
			if(def.isJsonArray()) {
				JsonArray defObject = def.getAsJsonArray();
				JsonObject def0 = defObject.get(0).getAsJsonObject();
				JsonElement tr = def0.get("tr");
				if(tr.isJsonArray()) {
					JsonArray translations = tr.getAsJsonArray();
					word.setTranslations(new ArrayList<String>());
					for(JsonElement translation: translations) {
						JsonObject transObj = translation.getAsJsonObject();
						word.getTranslations().add(transObj.get("text").getAsString());
					}
				}
				
			}
			
		}
		return word;
	}
	
	public static void main(String[] args) {
		try {
			String json = new Gson().toJson(getWord("en", "ru", "time"));
			json=json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
