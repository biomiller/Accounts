package com.qa.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONUtil {

	private Gson gson;

	public JSONUtil() {
		this.gson = new Gson();
	}

	public String getJSONForObject(Object obj) {
		return gson.toJson(obj);
	}

	public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
		
		return gson.fromJson(jsonString, clazz);
	}

	
/*	public boolean getKeyValue(String jsonstring,String thing) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonstring);

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        return jsonObject.get(thing);

    }*/
}