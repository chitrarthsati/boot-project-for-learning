package com.boot.features.utils;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProjectUtils {

	public List<HashMap<String,Object>> strToJson(String string) {
		JSONArray jArr = new JSONArray(string);
		
//		JSONObject json = new JSONObject(string);
		String str = jArr.toString();
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,Object>> map = null;
		JsonNode node = null;
		try {
			node = mapper.readTree(str);
			map = mapper.convertValue(node, List.class);
			System.out.println(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
}