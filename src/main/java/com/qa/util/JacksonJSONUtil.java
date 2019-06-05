package com.qa.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJSONUtil {
	
	ObjectMapper mapper; 
	
	public JacksonJSONUtil() {
		this.mapper = new ObjectMapper(); 
	}
	
	
	public <T> T jacksonGetObjectForJSON(String jsonstring,Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonstring, clazz);
	}
	
	public String jacksonGetJSONForObject(Object obj) throws JsonProcessingException {
		
		return mapper.writeValueAsString(obj);
	}
	

	
	
	

	
	
}
